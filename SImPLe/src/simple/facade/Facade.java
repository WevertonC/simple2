package simple.facade;
/*
 * Facade
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */



import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;





import simple.manipulacoes.compressorImagens.CompressorImagens;
import simple.manipulacoes.manipularArquivo.AbrirImagem;
import simple.manipulacoes.manipularArquivo.SalvarImagem;
import simple.manipulacoes.util.Editar;
import simple.manipulacoes.util.MyBufferedImage;
import simple.manipulacoes.util.MyImage;
import simple.manipulacoes.util.MyJInternalFrame;
import simple.manipulacoes.util.MyScrollPane;
import simple.modules.classificacao.segmentacao.SegmentarImagem;
import simple.modules.operacoes.aritmeticas.Adicao;
import simple.modules.operacoes.aritmeticas.Divisao;
import simple.modules.operacoes.aritmeticas.Multiplicacao;
import simple.modules.operacoes.aritmeticas.Pessoal;
import simple.modules.operacoes.aritmeticas.Subtracao;
import simple.modules.operacoes.filtro.FiltraImagem;
import simple.modules.operacoes.filtro.Roberts;
import simple.modules.operacoes.geometrica.Redimensionar;
import simple.modules.operacoes.geometrica.Rotacionar;
import simple.modules.operacoes.logica.And;
import simple.modules.operacoes.logica.Nand;
import simple.modules.operacoes.logica.Nor;
import simple.modules.operacoes.logica.Not;
import simple.modules.operacoes.logica.Or;
import simple.modules.operacoes.logica.Xor;
import simple.modules.operacoes.morfologia.Abertura;
import simple.modules.operacoes.morfologia.Dilatacao;
import simple.modules.operacoes.morfologia.Erosao;
import simple.modules.operacoes.morfologia.Fechamento;
import simple.modules.operacoes.radiometricas.Binariza;
import simple.modules.operacoes.radiometricas.EscalaCinza;
import simple.modules.propriedades.decomporCanais.*;
import simple.modules.propriedades.histograma.Histograma;
import simple.modules.propriedades.pseudoColor.PseudoColor;
import simple.ui.janelas.SImPLe;


import simple.excecoes.*;

/**
 * Classe que controla as funcionalidades
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class Facade{
	
	private static Facade unicaInstancia = null;
	private Histograma histograma;
	private CompressorImagens compressorImagens;
	private int posicao;	
	private Editar edit = null;
	private boolean modificou = false;
	public double tamanhoKbytes = 0;
	public double tamanhoBytes = 0;
	public String tipo = "";
	
	/**
	 * Construtor da classe com Desing Pattern Singleton.
	 */
	private Facade(){
		histograma = null;		
		posicao = 0;
	}
	/**
	 * Método responsável por criar uma única instância da Façada.
	 * @return A instancia da classe
	 */
	public static Facade getInstance(){
		if(unicaInstancia == null){
			unicaInstancia = new Facade();			
		}
		return unicaInstancia;
	}	
	/**
	 * Método responsável para criar um deslocamento na posicao dos frames
	 */
	public void incrementaPosicao(){
		posicao+= 30;
	}
	/**
	 * Método responsável para criar um deslocamento na posicao dos frames
	 */
	public void decrementaPosicao(){
		posicao-= 30;
	}
	/**
	 * Método que retorna o valor da atual posicao dos frames
	 */
	public int getPosicao(){
		return posicao;
	}
	/**
	 * Metodo que seta o novo valor da posicao
	 * @param posicao
	 */
	public void setPosicao(int posicao){
		this.posicao = posicao;
	}
				
	//***********************************************************************//
	//****************      Metodos de Manipular Arquivo     ****************//
	//***********************************************************************//
		
	/**
	 * Metodo abrir que abre uma imagem 
	 * @return O internalFrame a aberto
	 * @throws ImagemNaoExisteException Caso a imagem nao existir
	 * @throws NomeInvalidoException 
	 */
	public Object[] abrir() throws ImagemNaoExisteException, NomeInvalidoException{
		AbrirImagem.abrir();
		Object[] array = new Object[2];
		array[0] = AbrirImagem.getImagem();
		array[1] = AbrirImagem.getNome();
		tamanhoBytes = AbrirImagem.tamanhoBytes;
		tamanhoKbytes = AbrirImagem.tamanhoKbytes;
		tipo = AbrirImagem.tipo;
		return array;
	}
	
	/**
	 * Metodo salvarComo que salva uma imagem em um diretorio selecionado.
	 * @param frame A ser salvo.
	 * @throws ImagemNaoSelecionadaException Caso a imagem nao for selecionada
	 * @throws ImagemNaoPodeSalvarException Caso o internalFrame seja null
	 * @throws ImagemNaoExisteException Caso a imagem nao exista
	 * @throws NomeInvalidoException Caso o nome da imagem seja invalido
	 */
	public int salvarComo(Image imagem) throws ImagemNaoSelecionadaException, ImagemNaoPodeSalvarException, ImagemNaoExisteException, NomeInvalidoException{
		return SalvarImagem.salvarComo(imagem);
	}
	
	/**
	 * Metodo salvar que salva uma imagem.
	 * @param frame A ser salvo
	 * @throws Exception
	 */
	public void salvar(MyJInternalFrame frame) throws Exception{
		SalvarImagem.salvar(new File(frame.getCaminhoImagem()),"jpg",frame.getImage());
	}
	
	//***********************************************************************//
	//****************        Metodos de Modificadores       ****************//
	//***********************************************************************//
	/**
	 * Metodo emPorcnetagemBICUBIC que faz chamada ao mesmo metodo da classe Redimensionar
	 * @param imagem A image desejada 
	 * @param largura A nova largura em porcentagem
	 * @param altura A nova altura em porcentagem
	 * @return A nova imagem modificada
	 * @throws RedimensionarException Caso os valores estejam fora do intervalo (valores obrigatoriamente > 0)
	 */
	public Image emPorcentagemBICUBIC(ImageIcon imagem, double largura, double altura) throws RedimensionarException{
		return Redimensionar.emPorcentagemBICUBIC(imagem,largura,altura);
	}
	/**
	 * Metodo emPorcnetagemBILINEAR que faz chamada ao mesmo metodo da classe Redimensionar
	 * @param imagem A image desejada 
	 * @param largura A nova largura em porcentagem
	 * @param altura A nova altura em porcentagem
	 * @return A nova imagem modificada
	 * @throws RedimensionarException Caso os valores estejam fora do intervalo (valores obrigatoriamente > 0)
	 */
	public Image emPorcentagemBILINEAR(ImageIcon imagem, double largura, double altura) throws RedimensionarException{
		return Redimensionar.emPorcentagemBILINEAR(imagem,largura,altura);
	}
	/**
	 * Metodo emPorcnetagemNEAREST_NEIGHBOR que faz chamada ao mesmo metodo da classe Redimensionar
	 * @param imagem A image desejada  
	 * @param largura A nova largura em porcentagem
	 * @param altura A nova altura em porcentagem
	 * @return A nova imagem modificada
	 * @throws RedimensionarException Caso os valores estejam fora do intervalo (valores obrigatoriamente > 0)
	 */
	public Image emPorcentagemNEAREST_NEIGHBOR(ImageIcon imagem, double largura, double altura) throws RedimensionarException{
		return Redimensionar.emPorcentagemNEAREST_NEIGHBOR(imagem,largura,altura);
	}
	/**
	 * Metodo emPixelsBICUBIC que faz chamada ao mesmo metodo da classe Redimensionar
	 * @param imagem A image desejada 
	 * @param largura A nova largura em pixels
	 * @param altura A nova altura em pixels
	 * @return A nova imagem modificada
	 * @throws RedimensionarException Caso os valores estejam fora do intervalo (valores obrigatoriamente > 0)
	 */
	public Image emPixelsBICUBIC(ImageIcon imagem, int largura, int altura) throws RedimensionarException{
		return Redimensionar.emPixelsBICUBIC(imagem,largura,altura);
	}
	/**
	 * Metodo emPixelsBILINEAR que faz chamada ao mesmo metodo da classe Redimensionar
	 * @param imagem A image desejada 
	 * @param largura A nova largura em pixels
	 * @param altura A nova altura em pixels
	 * @return A nova imagem modificada
	 * @throws RedimensionarException Caso os valores estejam fora do intervalo (valores obrigatoriamente > 0)
	 */
	public Image emPixelsBILINEAR(ImageIcon imagem, int largura, int altura) throws RedimensionarException{
		return Redimensionar.emPixelsBILINEAR(imagem,largura,altura);
	}
	/**
	 * Metodo emPixelsNEAREST_NEIGHBOR que faz chamada ao mesmo metodo da classe Redimensionar
	 * @param imagem A image desejada 
	 * @param largura A nova largura em pixels
	 * @param altura A nova altura em pixels
	 * @return A nova imagem modificada
	 * @throws RedimensionarException Caso os valores estejam fora do intervalo (valores obrigatoriamente > 0)
	 */
	public Image emPixelsNEAREST_NEIGHBOR(ImageIcon imagem, int largura, int altura) throws RedimensionarException{
		return Redimensionar.emPixelsNEAREST_NEIGHBOR(imagem,largura,altura);
	}
	/**
	 * Metodo rodar que faz chamada ao mesmo metodo da classe Rotacionar
	 * @param imagem A imagem desejada
	 * @param graus O valor dos graus desejado
	 * @return A imagem rotacionada
	 * @throws RotacionarException Caso haja problema na transformacao
	 */
	public BufferedImage rotacionar(RenderedImage imagem, int graus) throws RotacionarException{
		return Rotacionar.rotacionar(imagem,graus);
	}
	/**
	 * Metodo zoom que faz chamada ao metodo setZoom da classe MyImage
	 * @param imagem A MyImage desejada
	 * @param porcentagem O valor do zoom em porcentagem
	 * @return A imagem com zoom
	 * @throws ZoomException Caso os valores estejam fora do intervalo (valores obrigatoriamente > 0)
	 */
	public RenderedImage zoom(MyImage imagem,int porcentagem) throws ZoomException{
		imagem.setZoom(porcentagem);
		return imagem.getRenderedImage();
	}
	/**
	 * Metodo zoomMais que chamada ao metodo setZoom da classe MyImage com valor fixo de 10%
	 * @param imagem A MyImage desejada
	 * @return A imagem com zoom
	 * @throws ZoomException Caso os valores estejam fora do intervalo (valores obrigatoriamente > 0)
	 */
	public RenderedImage zoomMais(MyImage imagem) throws ZoomException{
		imagem.setZoom(0.1f,0.1f);
		return imagem.getRenderedImage();
	}
	/**
	 * Metodo zoomMenos que chamada ao metodo setZoom da classe MyImage com valor fixo de -10%
	 * @param imagem A MyImage desejada
	 * @return A imagem com zoom
	 * @throws ZoomException Caso os valores estejam fora do intervalo (valores obrigatoriamente > 0)
	 */
	public RenderedImage zoomMenos(MyImage imagem) throws ZoomException{
		imagem.setZoom(-0.1f,-0.1f);
		return imagem.getRenderedImage();
	}
	
	//***********************************************************************//
	//****************         Metodos de Histograma         ****************//
	//***********************************************************************//
	/**
	 * Metodo criarHistograma que cria um histograma da imagem desejada
	 * @param imagem A imagem desejada
	 */
	public void criarHistograma(Image imagem){
		this.histograma = new Histograma(imagem); 
	}
	/**
	 * Metodo getRedHistograma que retorna o histograma da banda vermelha da imagem corrente
	 * @return O histograma da banda desejada
	 */
	public int[] getRedHistograma(){
		return this.histograma.getHistogram(Histograma.REDHISTOGRAMA);
	}
	/**
	 * Metodo getGreenHistograma que retorna o histograma da banda verde da imagem corrente
	 * @return O histograma da banda desejada
	 */
	public int[] getGreenHistograma(){
		return this.histograma.getHistogram(Histograma.GREENHISTOGRAMA);
	}
	/**
	 * Metodo getBlueHistograma que retorna o histograma da banda azul da imagem corrente
	 * @return O histograma da banda desejada
	 */
	public int[] getBlueHistograma(){
		return this.histograma.getHistogram(Histograma.BLUEHISTOGRAMA);
	}
	/**
	 * Metodo getGrayHistograma que retorna o histograma da banda em nivel de cinza da imagem corrente
	 * @return O histograma da banda desejada
	 */
	public int[] getGrayHistograma(){
		return this.histograma.getGrayHistograma();
	}
	/**
	 * Metodo getDesvioPadrao que retorna o valor do desvio padrao do histograma da banda desejada
	 * @param histograma O histograma da banda desejada
	 * @return O valor do desvio padrao da banda
	 */
	public double getDesvioPadrao(int[] histograma){
		return this.histograma.getDesvioPadrao(histograma);
	}
	/**tentativa de acesso a dado inválido ( Histograma ainda não inicializado ) 

	 * Metodo getVariancia que retorna o valor da variancia do histograma da banda desejada
	 * @param histograma O histograma da banda desejada
	 * @return O valor da variancia da banda
	 */
	public double getVariancia(int[] histograma){
		return this.histograma.getVariancia(histograma);
	}
	/**
	 * Metodo getMedia que retorna o valor da media dos valores do histograma da banda desejada
	 * @param histograma O histograma da banda desejada
	 * @return O valor da media dos valores da banda
	 */
	public double getMedia(int[] histograma){
		return this.histograma.getMedia(histograma);
	}
	/**
	 * Metodo getMaiorValor que retorna o maior valor do histograma da banda desejada
	 * @param histograma O histograma da banda desejada
	 * @return O maior valor da banda
	 */
	public int getMaiorValor(int[] histograma){
		return this.histograma.getMaxYValue(histograma);
	}
	
	//***********************************************************************//
	//****************        Métodos de Radiometria         ****************//
	//***********************************************************************//
	/**
	 * Metodo escalaCinza que transforma a imagem em nivel de cinza
	 * @param imagem A imagem desejada
	 * @return A imagem em nivel de cinza
	 */
	public Image escalaCinza(Image imagem){
		return EscalaCinza.nivelCinza(new ImageIcon(imagem));		
	}
	
	/**
	 * Metodo binzariza que transforma a imagem em preto e branco
	 * @param imagem A imagem desejada
	 * @return A imagem binzarizada
	 */
	public Image binarizaImagem(Image imagem){
		return Binariza.binarizacao(new ImageIcon(imagem));		
	}

	
	//***********************************************************************//
	//****************   Métodos de Operações Aritméticas    ****************//
	//***********************************************************************//
	/**
	 * Metodo adicao que adiciona duas imagens gerando uma outra com a soma
	 * @param imagensIcon lista com as imagens
	 * @return Imagem com a adicao
	 * @throws OperacaoAritmeticaException Caso nao seja possivel capturar os pixels da imagem
	 */
	public Image adicao(List<ImageIcon> lista) throws OperacaoAritmeticaException{
		return Adicao.soma(lista);
	}
	/**
	 * Metodo divide que faz a divisao entre duas imagens gerando uma outra com o resultado
	 * @param primeiraImage A primeira imagem a ser dividida
	 * @param segundaImage A segunda imagem a ser dividida
	 * @return Imagem com a divisao
	 * @throws OperacaoAritmeticaException Caso nao seja possivel capturar os pixels da imagem
	 */
	public Image divide(ImageIcon primeiraImage, ImageIcon segundaImage) throws OperacaoAritmeticaException{
		return Divisao.divide(primeiraImage,segundaImage);
	}
	/**
	 * Metodo multiplica que faz a multiplicacao entre duas imagens
	 * @param imagensIcon lista com as imagens
	 * @return Imagem com a multiplicacao
	 * @throws OperacaoAritmeticaException Caso nao seja possivel capturar os pixels da imagem
	 */
	public Image multiplica(List<ImageIcon> lista) throws OperacaoAritmeticaException{
		return Multiplicacao.multiplica(lista);
	}
	/**
	 * Metodo subtracao que faz a subtracao entre duas imagens
	 * @param primeiraImage A primeira imagem a ser subtraida
	 * @param segundaImage A segunda para subtrair a primeira
	 * @return Image com a subtracao
	 * @throws OperacaoAritmeticaException Caso nao seja possivel capturar os pixels da imagem
	 */
	public Image subtracao(ImageIcon primeiraImage, ImageIcon segundaImage) throws OperacaoAritmeticaException{
		return Subtracao.subtracao(primeiraImage,segundaImage);
	}
	/**
	 * Método aritmeticaPessoal que faz a operacao de acordo com os valores do gain e do offset
	 * @param image A imagem para a operação
	 * @param gain O valor do gain
	 * @param offset O valor do offset
	 * @return A uma imagem com a operação desejada
	 */
	public Image aritmeticaPessoal(Image image, double gain, double offset) {
		return Pessoal.aritmeticaPessoal(image,gain,offset);
	}
	
	//***********************************************************************//
	//****************      Métodos de Operações Lógicas     ****************//
	//***********************************************************************//
	/**
	 * Metodo and que faz a operacao add duas imagens gerando uma outra com o resultado
	 * @param imagensIcon lista com as imagens
	 * @return Imagem com a operacao
	 * @throws OperacaoLogicaException Caso nao seja possivel capturar os pixels da imagem
	 */
	public Image and(List<ImageIcon> lista) throws OperacaoLogicaException {
		return And.and(lista);
	}
	/**
	 * Metodo nand que faz a operacao nadd duas imagens gerando uma outra com o resultado
	 * @param imagensIcon lista com as imagens
	 * @return Imagem com a operacao
	 * @throws OperacaoLogicaException Caso nao seja possivel capturar os pixels da imagem
	 */	
	public Image nand(List<ImageIcon> lista) throws OperacaoLogicaException{
		return Nand.nand(lista);
	}
	/**
	 * Metodo nor que faz a operacao nor duas imagens gerando uma outra com o resultado
	 * @param imagensIcon lista com as imagens
	 * @return Imagem com a operacao
	 * @throws OperacaoLogicaException Caso nao seja possivel capturar os pixels da imagem
	 */
	public Image nor(List<ImageIcon> lista) throws OperacaoLogicaException{
		return Nor.nor(lista);
	}
	/**
	 * Metodo not que faz a operacao not duas imagens gerando uma outra com o resultado
	 * @param umaImage A primeira imagem para operar
	 * @param outraImage A segunda imagem para operar
	 * @return Imagem com a operacao
	 * @throws OperacaoAritmeticaException Caso nao seja possivel capturar os pixels da imagem
	 */
	public Image not(ImageIcon imagem) throws OperacaoLogicaException {
		return Not.not(imagem);
	}
	/**
	 * Metodo or que faz a operacao Or duas imagens gerando uma outra com o resultado
	 * @param imagensIcon lista com as imagens
	 * @return Imagem com a operacao
	 * @throws OperacaoAritmeticaException Caso nao seja possivel capturar os pixels da imagem
	 */
	public Image or(List<ImageIcon> lista) throws OperacaoLogicaException{
		return Or.or(lista);
	}
	/**
	 * Metodo xor que faz a operacao xor duas imagens gerando uma outra com o resultado
	 * @param imagensIcon lista com as imagens
	 * @return Imagem com a operacao
	 * @throws OperacaoAritmeticaException Caso nao seja possivel capturar os pixels da imagem
	 */
	public Image xor(List<ImageIcon> lista) throws OperacaoLogicaException{
		return Xor.xor(lista);
	}
	
	//***********************************************************************//
	//****************     Métodos de Pseudo Colorização     ****************//
	//***********************************************************************//
	/**
	 * Metodo pseudo que faz a pseudo colorizacao 
	 * @param pretaBranco A imagem a ser colorizada
	 * @param colorida A imagem que servira de base para a colorizacao
	 * @return Imagem pseudo-colorizada
	 * @throws PseudoColorException Caso nao seja possivel pegar os pixels da imagem
	 */
	public Image pseudocolorizacao(ImageIcon pretoBranco, ImageIcon coloridaBase) throws PseudoColorException{
		return PseudoColor.pseudo(pretoBranco, coloridaBase);
	}
	
	//***********************************************************************//
	//****************     Métodos de Desfazer e Refazer     ****************//
	//***********************************************************************//
	/**
	 * Metodo desfazer que retorna a ultima modificacao feita em myimage
	 * @param A MyImage desejada
	 * @return A imagem correspondente a ultima modificacao
	 */
	public RenderedImage desfazer(MyImage imagem){
		return imagem.desfazer();
	}
	/**
	 * Metodo refazer que retorna a ultima modificacao feita em myimage
	 * @param A MyImage desejada
	 * @return A imagem correspondente a ultima modificacao
	 */
	public RenderedImage refazer(MyImage imagem){
		return imagem.refazer();
	}
	
	//***********************************************************************//
	//****************      Métodos de Decompor Canais       ****************//
	//***********************************************************************//
	/**
	 * Metodo que decompoe a imagem em tres canais, R, G e B
	 * @param frame O frame a ser decomposto
	 * @return Um Array de frames.
	 */
	public Object[] decomporRGB(MyJInternalFrame frame){
		DecompositorRGB rgb = new DecompositorRGB(MyBufferedImage.makeBufferedImage(frame.getImage()), frame.getName());
		Image[] imagens = rgb.getCanaisRGB();
		Object[] array = new Object[imagens.length + 1];
		for (int i = 0; i < array.length -1; i++) array[i] = imagens[i];
		array[array.length -1] = rgb.getNome();		
		return array;
	}
	/**
	 * Metodo que decompoe a imagem em tres canais, X, Y e Z
	 * @param frame O frame a ser decomposto
	 * @return Um Array de frames.
	 */
	public Object[] decomporXYZ(MyJInternalFrame frame){
		DecompositorXYZ xyz = new DecompositorXYZ(MyBufferedImage.makeBufferedImage(frame.getImage()), frame.getName());
		Image[] imagens = xyz.getCanaisXYZ();
		Object[] array = new Object[imagens.length + 1];
		for (int i = 0; i < array.length -1; i++) array[i] = imagens[i];
		array[array.length -1] = xyz.getNome();		
		return array;
	}
	/**
	 * Metodo que decompoe a imagem em tres canais, H, S e V
	 * @param frame O frame a ser decomposto
	 * @return Um Array de frames.
	 */
	public Object[] decomporHSV(MyJInternalFrame frame){
		DecompositorHSV hsv = new DecompositorHSV(MyBufferedImage.makeBufferedImage(frame.getImage()), frame.getName());
		Image[] imagens = hsv.getHSVChannels();
		Object[] array = new Object[imagens.length + 1];
		for (int i = 0; i < array.length -1; i++) array[i] = imagens[i];
		array[array.length -1] = hsv.getNome();		
		return array;
	}
	/**
	 * Metodo que decompoe a imagem em tres canais, Y, Cr e Cb
	 * @param frame O frame a ser decomposto
	 * @return Um Array de frames.
	 */
	public Object[] decomporYCrCb(MyJInternalFrame frame){
		DecompositorYCrCb ycbcr = new DecompositorYCrCb(MyBufferedImage.makeBufferedImage(frame.getImage()), frame.getName());
		Image[] imagens = ycbcr.getCanaisYCrCb();
		Object[] array = new Object[imagens.length + 1];
		for (int i = 0; i < array.length -1; i++) array[i] = imagens[i];
		array[array.length -1] = ycbcr.getNome();		
		return array;
	}
	/**
	 * Metodo que decompoe a imagem em tres canais, C, M e Y
	 * @param frame O frame a ser decomposto
	 * @return Um Array de frames.
	 */
	public Object[] decomporCMY(MyJInternalFrame frame){
		DecompositorCMY cmy = new DecompositorCMY(MyBufferedImage.makeBufferedImage(frame.getImage()), frame.getName());
		Image[] imagens = cmy.getCanaisCMY();
		Object[] array = new Object[imagens.length + 1];
		for (int i = 0; i < array.length -1; i++) array[i] = imagens[i];
		array[array.length -1] = cmy.getNome();		
		return array;
	}
	/**
	 * Metodo que decompoe a imagem em quatro canais, C, M , Y e K
	 * @param frame O frame a ser decomposto
	 * @return Um Array de frames.
	 */
	public Object[] decomporCMYK(MyJInternalFrame frame){
		DecompositorCMYK cmyk = new DecompositorCMYK(MyBufferedImage.makeBufferedImage(frame.getImage()), frame.getName());
		Image[] imagens = cmyk.getCanaisCMYK();
		Object[] array = new Object[imagens.length + 1];
		for (int i = 0; i < array.length -1; i++) array[i] = imagens[i];
		array[array.length -1] = cmyk.getNome();		
		return array;
	}	
	
	//***********************************************************************//
	//****************          COMPRIMIR IMAGENS            ****************//
	//***********************************************************************//
	
	
	/**
	 * Metodo que comprimi uma imagem em quatro em formatos (GIF, JPG, PNG e BMP) de acordo com o 
	 * valor digitado pelo o usuario	
	 * @param formato (1-PNG, 2-BMP, 3-GIF ou 4-JPG)
	 * @param caminho o que voce deseja salvar o arquivo comprimido
	 * @param bf Um buffer contendo a imagem original
	 * @throws FormatoInvalidoException
	 * @throws InterruptedException
	 */
	public void comprimirImagem(String formato,String caminho, BufferedImage bf) throws FormatoInvalidoException, InterruptedException{
		compressorImagens = new CompressorImagens(bf, new File(""));
		int form = compressorImagens.reconheceFormato(formato);
		compressorImagens.converteImagem(caminho,form);
	}
	
	//***********************************************************************//
	//****************             Ilusão de Ótica           ****************//
	//***********************************************************************//	
	
	/**
	 * Metodo que exibe uma ilusao de otica
	 */
	public int ilusaoOtica(int numero){
		return numero;
	}
	
	//***********************************************************************//
	//****************           Segementar Imagem           ****************//
	//***********************************************************************//
	public Image segmentacaoGlobal(Image image, int limiar) throws ImageProcessingException{
		return SegmentarImagem.segmentacaoGlobal(MyBufferedImage.makeBufferedImage(image),limiar+"");
	}
	
	public Image segmentacaoAdaptativa(Image image, int dimensao) throws ImageProcessingException{
		return SegmentarImagem.segmentacaoAdaptativa(MyBufferedImage.makeBufferedImage(image),dimensao+"");
	}
	
	
	//***********************************************************************//
	//****************            Filtrar Imagem             ****************//
	//***********************************************************************//
	/**
	 * Metodo que aplica o filtro gaussian na imagem passada como parametro
	 * @param image imagem a ser processada pelo filtro
	 * @param maskLargura largura da mascara
	 * @param maskAltura alturada mascara
	 * @param desvio desvio
	 * @return imagem com o filtro gaussian aplicada
	 * @throws ImageProcessingException
	 */
	public Image gaussian(Image image,int maskLargura, int maskAltura, double desvio) throws ImageProcessingException {
		return MyBufferedImage.makeImage(FiltraImagem.gaussian(
				        MyBufferedImage.makeBufferedImage(image),maskLargura,maskAltura,desvio));		
	}
	
	/**
	 * Metodo que aplica o filtro mean na imagem passada como parametro
	 * @param image imagem a ser processada pelo filtro
	 * @param maskLargura largura da mascara
	 * @param maskAltura alturada mascara
	 * @return imagem com o filtro mean aplicada
	 * @throws ImageProcessingException
	 */
	public Image mean(Image image,int maskLargura, int maskAltura) throws ImageProcessingException {
		return MyBufferedImage.makeImage(FiltraImagem.mean(MyBufferedImage.makeBufferedImage(image),maskLargura,maskAltura));
	}
	
	/**
	 * Metodo que aplica o filtro sobel na imagem passada como parametro
	 * @param image imagem a ser processada pelo filtro
	 * @param direcao direcao de aplicao do filtro, horizontal(x) ou vertical(y)
	 * @return imagem com o filtro sobel aplicada
	 * @throws ImageProcessingException
	 */
	public Image sobel(Image image, String direcao) throws ImageProcessingException {
		return MyBufferedImage.makeImage(FiltraImagem.sobel(MyBufferedImage.makeBufferedImage(image),direcao));	
	}
	
	/**
	 * Metodo que aplica o filtro laplace na imagem passada como parametro
	 * @param image imagem a ser processada pelo filtro
	 * @return imagem com o filtro laplace aplicada
	 * @throws ImageProcessingException
	 */
	public Image laplace(Image image) throws ImageProcessingException {
		return MyBufferedImage.makeImage(FiltraImagem.laplace(MyBufferedImage.makeBufferedImage(image)));
	}
	
	/**
	 * Metodo que aplica o filtro prewitt na imagem passada como parametro
	 * @param image imagem a ser processada pelo filtro
	 * @return imagem com o filtro prewitt aplicada
	 * @throws ImageProcessingException
	 */
	public Image prewit(Image image) throws ImageProcessingException {
		return MyBufferedImage.makeImage(FiltraImagem.prewitt(MyBufferedImage.makeBufferedImage(image)));
	}
	
	/**
	 * Metodo que aplica o filtro prewitt na imagem passada como parametro
	 * @param image imagem a ser processada pelo filtro
	 * @return imagem com o filtro prewitt aplicada
	 * @throws ImageProcessingException
	 */
	public Image roberts(Image image) throws ImageProcessingException {
		return Roberts.roberts(new ImageIcon(image));
	}
	
	/**
	 * Metodo que aplica o filtro median na imagem passada como parametro
	 * @param image imagem a ser processada pelo filtro
	 * @return imagem com o filtro median aplicada
	 * @throws ImageProcessingException
	 */
	public Image median(Image image) throws ImageProcessingException {
		return MyBufferedImage.makeImage(FiltraImagem.median(MyBufferedImage.makeBufferedImage(image)));	
	}
	
	/**
	 * Metodo que aplica o filtro da moda na imagem passada como parametro
	 * @param image imagem a ser processada pelo filtro
	 * @return imagem com o filtro da moda aplicada
	 * @throws ImageProcessingException
	 */
	public Image moda(Image image) throws ImageProcessingException {
		return MyBufferedImage.makeImage(FiltraImagem.moda(MyBufferedImage.makeBufferedImage(image)));	
	}
	
	
    //	***********************************************************************//
	//****************      Metodos de Editar Imagem      ********************//
	//***********************************************************************//
	/**
	 * Metodo que recorta a parte da imagem limitada pelos pontos passados como parametro(x1,y1,x2,y2)
	 * @param daVez imagem a ser recortada
	 * @param x1 coordenada x superior esquerda do recorte
	 * @param y1 coordenada y superior esquerda do recorte
	 * @param x2 coordenada x inferior direita do recorte
	 * @param y2 coordenada y inferior direita do recorte
	 */
	public boolean recortar(SImPLe fepdi,Image daVez) {
		modificou = true;
		edit = new Editar(fepdi,daVez,MyScrollPane.x1,MyScrollPane.y1,
				MyScrollPane.x2,MyScrollPane.y2,MyScrollPane.px,MyScrollPane.py);
		return edit.recortar();
	}
	
	public Image getImagemRecortada(){
		return edit.recortaImagem();
	}
	
	/**
	 * Metodo que copia a parte da imagem limitada pelos pontos passados como parametro(x1,y1,x2,y2)
	 * @param daVez imagem a ser recortada
	 * @param x1 coordenada x  superior esquerda do recorte
	 * @param y1 coordenada y superior esquerda do recorte
	 * @param x2 coordenada x inferior direita do recorte
	 * @param y2 coordenada y inferior direita do recorte
	 */
	public boolean copiar(SImPLe fepdi, Image daVez) {
		edit = new Editar(fepdi,daVez,MyScrollPane.x1,MyScrollPane.y1,
				MyScrollPane.x2,MyScrollPane.y2,MyScrollPane.px,MyScrollPane.py);
		return edit.copiar();
	}
	
	/**
	 * Metodo que retorna a imagem recortada ou copiada
	 * @return
	 */
	public Image colar() {
		modificou = true;
		return edit.colar();
	}
	
	public boolean modificouImagem() {
		return modificou;
	}	
	
//	***********************************************************************//
	//****************           Morfologia                  ****************//
	//***********************************************************************//
	
	/**
	 * 
	 * @param image
	 * @return
	 */
	public Image erosao4(Image image) {
		return Erosao.erosao4(new ImageIcon(image));
	}
	
	/**
	 * 
	 * @param image
	 * @return
	 */
	public Image erosao8(Image image) {
		return Erosao.erosao8(new ImageIcon(image));
	}
	
	/**
	 * 
	 * @param image
	 * @return
	 */
	public Image dilatacao4(Image image) {
		return Dilatacao.dilatacao4(new ImageIcon(image));
	}
	
	/**
	 * 
	 * @param image
	 * @return
	 */
	public Image dilatacao8(Image image) {
		return Dilatacao.dilatacao8(new ImageIcon(image));
	}
	
	/**
	 * 
	 * @param image
	 * @return
	 */
	public Image abertura4(Image image) {
		return Abertura.abertura4(new ImageIcon(image));
	}
	
	/**
	 * 
	 * @param image
	 * @return
	 */
	public Image abertura8(Image image) {
		return Abertura.abertura8(new ImageIcon(image));
	}
	
	/**
	 * 
	 * @param image
	 * @return
	 */
	public Image fechamento4(Image image) {
		return Fechamento.fechamento4(new ImageIcon(image));
	}
	
	/**
	 * 
	 * @param image
	 * @return
	 */
	public Image fechamento8(Image image) {
		return Fechamento.fechamento8(new ImageIcon(image));
	}
}