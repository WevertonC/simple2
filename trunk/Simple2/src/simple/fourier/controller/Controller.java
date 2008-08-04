package simple.fourier.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import simple.fourier.core.FourierImagem;
import simple.fourier.exceptions.FourierException;
import simple.fourier.filtering.Filtragem;

/**
 * Controlador para o m�dulo Fourier. O controlador permite, de uma forma mais ligada ao
 * dom�nio da aplica��o, o acesso �s funcionalidades deste m�dulo.
 * 
 * @author Elloa B. Guedes - elloa@dsc.ufcg.edu.br
 * @author Odilon F. Lima Junior - odilonflj@dsc.ufcg.edu.br
 *
 */
public class Controller {

	/**
	 * Imagem de entrada passada como par�metro.
	 */
	private File inputFile;

	/**
	 * Construtor vazio default.
	 */
	public Controller() {

	}

	/**
	 * Realiza a Transformada R�pida de Fourier em uma imagem.
	 * 
	 * @param imagePath O caminho do arquivo da imagem de entrada
	 * @param outputFileName O caminho do arquivo da imagem de sa�da
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

		// Espectro de fourier como sa�da
		resultadoFFT = computeSpectrum(src);

		// Persist�ncia em arquivo com formato bmp
		File outputFile = new File(outputFileName);
		try {
			ImageIO.write(resultadoFFT, "JPG", outputFile);
		} catch (IOException e) {
			throw new FourierException(FourierException.ERRO_SALVAR_SAIDA);
		}

		return true;

	}


	/**
	 * Transforma a imagem de entrada no dom�nio do espa�o em uma imagem no dom�nio da freq��ncia com a realiza��o da transformada
	 * r�pida de Fourier.
	 * 
	 * @param src a imagem no dom�nio do espa�o passada como par�metro
	 * @return a imagem no dom�nio da freq��ncia
	 * @throws FourierException caso existam problemas no processo de mudan�a de dom�nio
	 */
	public BufferedImage computeSpectrum(BufferedImage src) throws FourierException {

		FourierImagem fft = new FourierImagem(src);
		fft.transform();

		return fft.getEspectro();

	}

	/**
	 * Retorna um arquivo contendo a imagem a ser mudada de dom�nio como par�metro
	 * @returna imagem a ser mudada de dom�nio como par�metro
	 */
	public File getFile() {
		return inputFile;
	}

	/**
	 * Modifica o arquivo a ser mudado de dom�nio.
	 * @param file o novo arquivo a ser mudado de dom�nio.
	 */
	public void setFile(File file) {
		this.inputFile = file;
	}

	/**
	 * Realiza a opera��o de filtragem com o filtro passa alta.
	 * 
	 * @param inputFileName
	 * @param outputFileName
	 * @param raio raio do filtro
	 * @return
	 * @throws FourierException
	 */
	public boolean passaAlta(String inputFileName, String outputFileName, double raio) throws FourierException{

		setFile(new File(inputFileName));	// Define objeto BufferedImage para encapsular a imagem
		BufferedImage src = null, resultadoEspaco = null, resultadoFreq;


		// Armazena arquivo imagem numa BufferedImage
		try {
			src = ImageIO.read(inputFile);
		} catch (IOException e1) {
			throw new FourierException(FourierException.ARQUIVO_N_EXISTE);
		}

		// Filtragem no dom�nio da freq��ncia
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.passaAlta(fr, raio);
		resultadoFreq = fr.getEspectro();
		
		// Voltar para o dom�nio do espa�o
		fr.transform();
		resultadoEspaco = fr.toImage(fr.getGrayImage());

		// Persist�ncia em arquivo com formato bmp
		File outputFileEspaco = new File(outputFileName + "-espaco.bmp");
		File outputFileFreq = new File(outputFileName + "-freq.bmp");
		try {
			ImageIO.write(resultadoFreq, "BMP", outputFileFreq);
			ImageIO.write(resultadoEspaco, "BMP", outputFileEspaco);
		} catch (IOException e) {
			throw new FourierException(FourierException.ERRO_SALVAR_SAIDA);
		}

		return true;
	}

	public boolean passaBaixa(String inputFileName, String outputFileName, double raio) throws FourierException{

		setFile(new File(inputFileName));	// Define objeto BufferedImage para encapsular a imagem
		BufferedImage src = null, resultado = null, resultadoFreq = null, resultadoEspaco;


		// Armazena arquivo imagem numa BufferedImage
		try {
			src = ImageIO.read(inputFile);
		} catch (IOException e1) {
			throw new FourierException(FourierException.ARQUIVO_N_EXISTE);
		}

		// Filtragem no dom�nio da freq��ncia
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.passaBaixa(fr, raio);
		resultadoFreq = fr.getEspectro();
		
		// Voltar para o dom�nio do espa�o
		fr.transform();
		resultadoEspaco = fr.toImage(fr.getGrayImage());

		// Persist�ncia em arquivo com formato bmp
		File outputFileEspaco = new File(outputFileName + "-espaco.bmp");
		File outputFileFreq = new File(outputFileName + "-freq.bmp");
		try {
			ImageIO.write(resultadoFreq, "BMP", outputFileFreq);
			ImageIO.write(resultadoEspaco, "BMP", outputFileEspaco);
		} catch (IOException e) {
			throw new FourierException(FourierException.ERRO_SALVAR_SAIDA);
		}

		return true;
	}
	
	public boolean passaFaixa(String inputFileName, String outputFileName, double raioInterno, double raioExterno) throws FourierException{

		setFile(new File(inputFileName));	// Define objeto BufferedImage para encapsular a imagem
		BufferedImage src, resultadoFreq,resultadoEspaco = null;


		// Armazena arquivo imagem numa BufferedImage
		try {
			src = ImageIO.read(inputFile);
		} catch (IOException e1) {
			throw new FourierException(FourierException.ARQUIVO_N_EXISTE);
		}

		// Filtragem no dom�nio da freq��ncia
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.passaFaixa(fr, raioInterno,raioExterno);
		resultadoFreq = fr.getEspectro();
		
		// Voltar para o dom�nio do espa�o
		fr.transform();
		resultadoEspaco = fr.toImage(fr.getGrayImage());

		// Persist�ncia em arquivo com formato bmp
		File outputFileEspaco = new File(outputFileName + "-espaco.bmp");
		File outputFileFreq = new File(outputFileName + "-freq.bmp");
		try {
			ImageIO.write(resultadoFreq, "BMP", outputFileFreq);
			ImageIO.write(resultadoEspaco, "BMP", outputFileEspaco);
		} catch (IOException e) {
			throw new FourierException(FourierException.ERRO_SALVAR_SAIDA);
		}

		return true;
	}

}

