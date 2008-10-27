package simple.modules.fourier.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import simple.modules.fourier.core.FourierImagem;
import simple.modules.fourier.exceptions.FourierException;
import simple.modules.fourier.filtering.Filtragem;
import simple.modules.fourier.filtering.FiltragemHomomorfica;


/**
 * Controlador para o módulo Fourier. O controlador permite, de uma forma mais ligada ao
 * domínio da aplicação, o acesso às funcionalidades deste módulo.
 * 
 * @author Elloa B. Guedes - elloa@dsc.ufcg.edu.br
 * @author Odilon F. Lima Junior - odilonflj@dsc.ufcg.edu.br
 *
 */
public class Controller {

	/**
	 * Imagem de entrada passada como parâmetro.
	 */
	private File inputFile;
	
	private FiltragemHomomorfica filtragemHomomorfica;

	/**
	 * Construtor vazio default.
	 */
	public Controller() {
		filtragemHomomorfica = new FiltragemHomomorfica();
	}

	/**
	 * Realiza a Transformada Rápida de Fourier em uma imagem.
	 * 
	 * @param imagePath O caminho do arquivo da imagem de entrada
	 * @param outputFileName O caminho do arquivo da imagem de saída
	 * @throws FourierException 
	 */
	public boolean fft(String inputFileName, String outputFileName) throws FourierException{

		setFile(new File(inputFileName));	// Define objeto BufferedImage para encapsular a imagem
		BufferedImage src = null, resultadoFFT = null;

		// Armazena arquivo imagem numa BufferedImage
		try {
			src = ImageIO.read(inputFile);
		} catch (IOException e1) {
			throw new FourierException(FourierException.ARQUIVO_N_EXISTE);
		}

		// Espectro de fourier como saída
		resultadoFFT = computeSpectrum(src);

		// Persistência em arquivo com formato bmp
		File outputFile = new File(outputFileName);
		try {
			ImageIO.write(resultadoFFT, "JPG", outputFile);
		} catch (IOException e) {
			throw new FourierException(FourierException.ERRO_SALVAR_SAIDA);
		}

		return true;

	}


	/**
	 * Transforma a imagem de entrada no domínio do espaço em uma imagem no domínio da freqüência com a realização da transformada
	 * rápida de Fourier.
	 * 
	 * @param src a imagem no domínio do espaço passada como parâmetro
	 * @return a imagem no domínio da freqüência
	 * @throws FourierException caso existam problemas no processo de mudança de domínio
	 */
	public BufferedImage computeSpectrum(BufferedImage src) throws FourierException {

		FourierImagem fft = new FourierImagem(src);
		fft.transform();

		return fft.getEspectro();

	}

	/**
	 * Retorna um arquivo contendo a imagem a ser mudada de domínio como parâmetro
	 * @returna imagem a ser mudada de domínio como parâmetro
	 */
	public File getFile() {
		return inputFile;
	}

	/**
	 * Modifica o arquivo a ser mudado de domínio.
	 * @param file o novo arquivo a ser mudado de domínio.
	 */
	public void setFile(File file) {
		this.inputFile = file;
	}


	/**
	 * 
	 * Realiza a operação de filtragem com o filtro passa alta, retornando a imagem resultante no domínio da 
	 * freqüência
	 * 
	 * @param src Image de entrada
	 * @param raio o raio do filtro passa alta
	 * @return a imagem filtrada
	 * @throws FourierException caso não seja possível realizar a operação ou existam erros
	 * no procedimento
	 */
	public static BufferedImage passaAltaFreq(BufferedImage src, double raio) throws FourierException{

		BufferedImage resultadoFreq = null;

		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.passaAlta(fr, raio);
		resultadoFreq = fr.getEspectro();

		return resultadoFreq;
	}

	/**
	 * 
	 * Realiza a operação de filtragem com o filtro passa alta, retornando a imagem resultante no domínio do 
	 * espaço, onde ficam mais evidentes os efeitos filtragem
	 * 
	 * @param src Image de entrada
	 * @param raio o raio do filtro passa alta
	 * @return a imagem filtrada
	 * @throws FourierException caso não seja possível realizar a operação ou existam erros
	 * no procedimento
	 */
	public static BufferedImage passaAltaEsp(BufferedImage src, double raio) throws FourierException{

		BufferedImage resultadoEspaco;

		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.passaAlta(fr, raio);

		// Voltar para o domínio do espaço
		fr.transform();
		resultadoEspaco = fr.toImage(fr.getGrayImage());

		return resultadoEspaco;
	}

	/**
	 * 
	 * Realiza a operação de filtragem com o filtro passa alta, retornando a imagem resultante no domínio da 
	 * freqüência
	 * 
	 * @param src Image de entrada
	 * @param raio o raio do filtro passa alta
	 * @return a imagem filtrada
	 * @throws FourierException caso não seja possível realizar a operação ou existam erros
	 * no procedimento
	 */
	public static BufferedImage passaBaixaFreq(BufferedImage src, double raio) throws FourierException{

		BufferedImage resultadoFreq = null;

		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.passaBaixa(fr, raio);
		resultadoFreq = fr.getEspectro();

		return resultadoFreq;
	}

	/**
	 * 
	 * Realiza a operação de filtragem com o filtro passa baixa, retornando a imagem resultante no domínio do 
	 * espaço, onde ficam mais evidentes os efeitos filtragem
	 * 
	 * @param src Image de entrada
	 * @param raio o raio do filtro passa alta
	 * @return a imagem filtrada
	 * @throws FourierException caso não seja possível realizar a operação ou existam erros
	 * no procedimento
	 */
	public static BufferedImage passaBaixaEsp(BufferedImage src, double raio) throws FourierException{

		BufferedImage resultadoEspaco;


		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.passaBaixa(fr, raio);

		// Voltar para o domínio do espaço
		fr.transform();
		resultadoEspaco = fr.toImage(fr.getGrayImage());

		return resultadoEspaco;
	}

	/**
	 * 
	 * Realiza a operação de filtragem com o filtro passa faixa, retornando a imagem resultante no domínio da 
	 * freqüência
	 * 
	 * @param src Image de entrada
	 * @param raioInterno o raio interno do filtro
	 * @param raioExterno o raio externo do filtro
	 * @return a imagem filtrada
	 * @throws FourierException caso não seja possível realizar a operação ou existam erros no procedimento
	 */
	public BufferedImage passaFaixaFreq(BufferedImage src, double raioInterno, double raioExterno) throws FourierException{

		if ((raioInterno < 0) ||(raioInterno > 1) || (raioExterno <0) || (raioExterno >1)) {
			throw new FourierException(FourierException.RAIO_FAIXA);
		}

		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.passaFaixa(fr, raioInterno,raioExterno);
		return fr.getEspectro();


	}

	/**
	 * Realiza a operação de filtragem com o filtro passa faixa, retornando a imagem resultante no domínio do 
	 * espaço, onde ficam mais evidentes os efeitos filtragem
	 * 
	 * @param src Image de entrada
	 * @param raioInterno o raio interno do filtro
	 * @param raioExterno o raio externo do filtro
	 * @return a imagem filtrada
	 * @throws FourierException caso não seja possível realizar a operação ou existam erros no procedimento
	 */
	public BufferedImage passaFaixaEsp(BufferedImage src, double raioInterno, double raioExterno) throws FourierException{

		BufferedImage resultadoEspaco = null;

		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.passaFaixa(fr, raioInterno,raioExterno);

		// Voltar para o domínio do espaço
		fr.transform();
		resultadoEspaco = fr.toImage(fr.getGrayImage());
		return resultadoEspaco;
	}
	
	/**
	 * 
	 * Realiza a operação de filtragem com o filtro rejeita faixa, retornando a imagem resultante no domínio da
	 * freqüência, onde ficam mais evidentes os efeitos filtragem
	 * 
	 * @param src Image de entrada
	 * @param raioInterno o raio interno do filtro
	 * @param raioExterno o raio externo do filtro
	 * @return a imagem filtrada
	 * @throws FourierException caso não seja possível realizar a operação ou existam erros no procedimento
	 */
	public BufferedImage rejeitaFaixaFreq(BufferedImage src, double raioInterno, double raioExterno) throws FourierException{

		if ((raioInterno < 0) ||(raioInterno > 1) || (raioExterno <0) || (raioExterno >1)) {
			throw new FourierException(FourierException.RAIO_FAIXA);
		}

		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.rejeitaFaixa(fr, raioInterno,raioExterno);
		return fr.getEspectro();
	}
	
	/**
	 * Realiza a operação de filtragem com o filtro rejeita faixa, retornando a imagem resultante no domínio do 
	 * espaço, onde ficam mais evidentes os efeitos filtragem
	 * 
	 * @param src Image de entrada
	 * @param raioInterno o raio interno do filtro
	 * @param raioExterno o raio externo do filtro
	 * @return a imagem filtrada
	 * @throws FourierException caso não seja possível realizar a operação ou existam erros no procedimento
	 */
	public BufferedImage rejeitaFaixaEsp(BufferedImage src, double raioInterno, double raioExterno) throws FourierException{

		
		BufferedImage resultadoEspaco = null;

		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.rejeitaFaixa(fr, raioInterno,raioExterno);
		

		// Voltar para o domínio do espaço
		fr.transform();
		resultadoEspaco = fr.toImage(fr.getGrayImage());
		return resultadoEspaco;
	}
	
	
	
	public BufferedImage reflectanciaFreq(BufferedImage src, double corte) throws FourierException{
		return filtragemHomomorfica.reflectanciaFreq(src, corte);
	}
	
	public BufferedImage reflectanciaEsp(BufferedImage src, double corte) throws FourierException{
		return filtragemHomomorfica.reflectanciaEsp(src, corte);
	}
	
	public BufferedImage iluminacaoFreq(BufferedImage src, double corte) throws FourierException{
		return filtragemHomomorfica.iluminacaoFreq(src, corte);
	}
	
	public BufferedImage iluminacaoEsp(BufferedImage src, double corte) throws FourierException{
		return filtragemHomomorfica.iluminacaoEsp(src, corte);
	}
	
	/**
	 * 
	 * Realiza a operação de filtragem com o filtro passa baixa gaussiano, retornando a imagem resultante no domínio da 
	 * freqüência
	 * 
	 * @param src Image de entrada
	 * @param raio o raio do filtro passa alta
	 * @return a imagem filtrada
	 * @throws FourierException caso não seja possível realizar a operação ou existam erros
	 * no procedimento
	 */
	public static BufferedImage gaussianoPassaBaixaFreq(BufferedImage src, double raio) throws FourierException{

		BufferedImage resultadoFreq = null;

		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.filtroGaussianoPassaBaixa(fr, raio);
		resultadoFreq = fr.getEspectro();

		return resultadoFreq;
	}
	
	/**
	 * 
	 * Realiza a operação de filtragem com o filtro passa baixa gaussiano, retornando a imagem resultante no domínio do 
	 * espaço, onde ficam mais evidentes os efeitos filtragem
	 * 
	 * @param src Image de entrada
	 * @param raio o raio do filtro passa alta
	 * @return a imagem filtrada
	 * @throws FourierException caso não seja possível realizar a operação ou existam erros
	 * no procedimento
	 */
	public static BufferedImage gaussianoPassaBaixaEsp(BufferedImage src, double raio) throws FourierException{

		BufferedImage resultadoEspaco;


		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.filtroGaussianoPassaBaixa(fr, raio);

		// Voltar para o domínio do espaço
		fr.transform();
		resultadoEspaco = fr.toImage(fr.getGrayImage());

		return resultadoEspaco;
	}
	
	/**
	 * 
	 * Realiza a operação de filtragem com o filtro passa alt  gaussiano, retornando a imagem resultante no domínio da 
	 * freqüência
	 * 
	 * @param src Image de entrada
	 * @param raio o raio do filtro passa alta
	 * @return a imagem filtrada
	 * @throws FourierException caso não seja possível realizar a operação ou existam erros
	 * no procedimento
	 */
	public static BufferedImage gaussianoPassaAltaFreq(BufferedImage src, double raio) throws FourierException{

		BufferedImage resultadoFreq = null;

		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.filtroGaussianoPassaAlta(fr, raio);
		resultadoFreq = fr.getEspectro();

		return resultadoFreq;
	}
	
	/**
	 * 
	 * Realiza a operação de filtragem com o filtro passa alta gaussiano, retornando a imagem resultante no domínio do 
	 * espaço, onde ficam mais evidentes os efeitos filtragem
	 * 
	 * @param src Image de entrada
	 * @param raio o raio do filtro passa alta
	 * @return a imagem filtrada
	 * @throws FourierException caso não seja possível realizar a operação ou existam erros
	 * no procedimento
	 */
	public static BufferedImage gaussianoPassaAltaEsp(BufferedImage src, double raio) throws FourierException{

		BufferedImage resultadoEspaco;


		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.filtroGaussianoPassaAlta(fr, raio);

		// Voltar para o domínio do espaço
		fr.transform();
		resultadoEspaco = fr.toImage(fr.getGrayImage());

		return resultadoEspaco;
	}
	
	/**
	 * 
	 * Realiza a operação de filtragem com o filtro passa baixa butterworth, retornando a imagem resultante no domínio da 
	 * freqüência
	 * 
	 * @param src Image de entrada
	 * @param raio o raio do filtro passa alta
	 * @return a imagem filtrada
	 * @throws FourierException caso não seja possível realizar a operação ou existam erros
	 * no procedimento
	 */
	public static BufferedImage butterworthPassaBaixaFreq(BufferedImage src, double raio, int n) throws FourierException{

		BufferedImage resultadoFreq = null;

		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.filtroButterworthPassaBaixa(fr,n, raio);
		resultadoFreq = fr.getEspectro();

		return resultadoFreq;
	}
	
	/**
	 * 
	 * Realiza a operação de filtragem com o filtro passa baixa butterworth, retornando a imagem resultante no domínio do 
	 * espaço, onde ficam mais evidentes os efeitos filtragem
	 * 
	 * @param src Image de entrada
	 * @param raio o raio do filtro passa alta
	 * @return a imagem filtrada
	 * @throws FourierException caso não seja possível realizar a operação ou existam erros
	 * no procedimento
	 */
	public static BufferedImage butterworthPassaBaixaEsp(BufferedImage src, double raio, int n) throws FourierException{

		BufferedImage resultadoEspaco;


		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.filtroButterworthPassaBaixa(fr, n, raio);

		// Voltar para o domínio do espaço
		fr.transform();
		resultadoEspaco = fr.toImage(fr.getGrayImage());

		return resultadoEspaco;
	}
	
	/**
	 * 
	 * Realiza a operação de filtragem com o filtro passa alta butterworth, retornando a imagem resultante no domínio da 
	 * freqüência
	 * 
	 * @param src Image de entrada
	 * @param raio o raio do filtro passa alta
	 * @return a imagem filtrada
	 * @throws FourierException caso não seja possível realizar a operação ou existam erros
	 * no procedimento
	 */
	public static BufferedImage butterworthPassaAltaFreq(BufferedImage src, double raio, int n) throws FourierException{

		BufferedImage resultadoFreq = null;

		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.filtroButterworthPassaAlta(fr,n, raio);
		resultadoFreq = fr.getEspectro();

		return resultadoFreq;
	}
	
	/**
	 * 
	 * Realiza a operação de filtragem com o filtro passa alta butterworth, retornando a imagem resultante no domínio do 
	 * espaço, onde ficam mais evidentes os efeitos filtragem
	 * 
	 * @param src Image de entrada
	 * @param raio o raio do filtro passa alta
	 * @return a imagem filtrada
	 * @throws FourierException caso não seja possível realizar a operação ou existam erros
	 * no procedimento
	 */
	public static BufferedImage butterworthPassaAltaEsp(BufferedImage src, double raio, int n) throws FourierException{

		BufferedImage resultadoEspaco;


		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.filtroButterworthPassaAlta(fr,n, raio);

		// Voltar para o domínio do espaço
		fr.transform();
		resultadoEspaco = fr.toImage(fr.getGrayImage());

		return resultadoEspaco;
	}
	
	

}

