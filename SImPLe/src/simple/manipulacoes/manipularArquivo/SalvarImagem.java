package simple.manipulacoes.manipularArquivo;
/*
 * SalvarImagem
 * 
 * @version 1.0
 * 
 * Date: 28/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import simple.manipulacoes.util.MyBufferedImage;

import simple.excecoes.ImagemNaoExisteException;
import simple.excecoes.ImagemNaoPodeSalvarException;
import simple.excecoes.ImagemNaoSelecionadaException;
import simple.excecoes.NomeInvalidoException;

/**
 * Classe destinada a salvar uma imagem em disco.
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class SalvarImagem {
	
	
	private static JFileChooser fc = null;
	private static File imageFile = null;
	public static String caminho = "";
	public static String caminhoImagem;
	public static String nome = "";
	
	/**
	 * Método que implementa a função Salvar Como
	 * @param frameAtual o frame selecionado no programa.
	 * @throws ImagemNaoSelecionadaException
	 * @throws ImagemNaoPodeSalvarException
	 * @throws ImagemNaoExisteException
	 * @throws NomeInvalidoException 
	 * @see pihm.manipularArquivo.JanelasArquivo 
	 */
	public static int salvarComo(Image imagemASalvar) throws ImagemNaoSelecionadaException, ImagemNaoPodeSalvarException, ImagemNaoExisteException, NomeInvalidoException{
		
		fc = new JFileChooser(new File(caminhoImagem));
		fc.addChoosableFileFilter(new GIFFilter());
		fc.addChoosableFileFilter(new PNGFilter());
		fc.addChoosableFileFilter(new BMPFilter());
		fc.addChoosableFileFilter(new JPGFilter());
		fc.setFileView(new ImageFileView());
		fc.setAccessory(new ImagePreview(fc));
//		File f;
//        try {
//                f = new File(new File(caminho).getCanonicalPath());
//                fc.setSelectedFile(f);
//        } catch (IOException e) {}
		
		int returnVal = fc.showDialog(new JFrame(), "Salvar Como");
		if (returnVal == JFileChooser.CANCEL_OPTION) return 0;
		imageFile = fc.getSelectedFile();
		trataErro();
		imageFile = new File(imageFile.getAbsolutePath()+"." + fc.getFileFilter().getDescription());
		caminhoImagem = imageFile.getAbsolutePath();
		nome = imageFile.getName();
		String formato = getFormato(imageFile);
		salvarArquivo(imageFile,formato,imagemASalvar);
		return 1;
	}
	
	/**
	 * Método que verifica se caminho do arquvo já contém o filtro da imagem
	 */
	private static void trataErro() {
		if ((imageFile+"").endsWith(".jpg"))
			imageFile = new File((imageFile+"").replace(".jpg",""));
		if ((imageFile+"").endsWith(".gif"))
			imageFile = new File((imageFile+"").replace(".gif",""));
		if ((imageFile+"").endsWith(".png"))
			imageFile = new File((imageFile+"").replace(".png",""));
		if ((imageFile+"").endsWith(".bmp"))
			imageFile = new File((imageFile+"").replace(".bmp",""));
		return;
	}
	
	/**
	 * Método que retorna o formato da imagem a ser salva, que pode ser jpeg(ou jpg),gif,png ou bmp
	 * @param imageFile arquivo onde o formato sera procurado
	 * @return o formato da imagem que pode ser jpeg(ou jpg),gif,png ou bmp
	 */
	private static String getFormato(File imageFile) {
		String formato = "";
		if (((imageFile.getAbsolutePath().endsWith("jpeg")) || (imageFile.getAbsolutePath().endsWith("jpg")))) 
			formato = "jpg";
		else if (imageFile.getAbsolutePath().endsWith("gif")) 
			formato = "gif";
		else if (imageFile.getAbsolutePath().endsWith("png")) 
			formato = "png";
		else if (imageFile.getAbsolutePath().endsWith("bmp")) 
			formato = "bmp";
		return formato;
	}
	
	/**
	 * Método que implementa a função salvarImagem
	 * @param frameAtual o frame selecionado no programa.
	 * @throws ImagemNaoSelecionadaException
	 * @throws ImagemNaoPodeSalvarException
	 * @throws ImagemNaoExisteException
	 * @throws NomeInvalidoException 
	 * @see pihm.manipularArquivo.JanelasArquivo 
	 */
	public static void salvarArquivo(File imageFile, String formato, Image imagemASalvar) throws NomeInvalidoException, ImagemNaoExisteException {
		String nomeArquivo = "";
		if (fc != null) {
			nomeArquivo = (imageFile.toString()).replace(fc.getCurrentDirectory().toString(),"");
			nomeArquivo = nomeArquivo.replaceFirst("\\\\","");  //no windows eh //// e no linux eh \
		}
		else nomeArquivo = imageFile.toString();
		
		if (imageFile == null || imageFile.getName().equals("") || nomeInvalido(nomeArquivo)) 
			throw new NomeInvalidoException("O nome " + nomeArquivo + " é inválido");
		
		try {
			salvar(imageFile,formato,imagemASalvar);
		} catch(Exception e) {
			throw new ImagemNaoExisteException("A imagem selecionada não existe!");
		}
	}   
	
	
	/**
	 * Verifica se o nome do arquivo não contém caracteres inválidos.  
	 * @param nomeArquivo O nome do arquivo a ser salvo
	 * @return true se o nome é válido
	 */
	private static boolean nomeInvalido(String nomeArquivo){
		if (nomeArquivo.contains("/") || nomeArquivo.contains(":") || 
				nomeArquivo.contains("\\") || nomeArquivo.contains("<") || 
				nomeArquivo.contains(">") || nomeArquivo.contains("|")) return true;
		return false;
	}
	
	/**
	 * Método que salva o arquivo em disco com o formato especificado
	 * @param imageFile arquivo que deseja salvar em disco
	 * @param formato string do formato do arquivo (gif, jpg, bmp ou png) 
	 * @param imagemASalvar a imagem para ser salva
	 */
	public static void salvar(File imageFile, String formato, Image imagemASalvar) throws Exception {
		ImageIO.write(MyBufferedImage.makeBufferedImage(imagemASalvar), formato , imageFile);
	}	
}