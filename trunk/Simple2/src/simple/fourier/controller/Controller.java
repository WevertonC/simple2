package simple.fourier.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import simple.fourier.core.FourierImagem;
import simple.fourier.exceptions.FourierException;
import simple.fourier.filtering.Filtragem;

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

	/**
	 * Construtor vazio default.
	 */
	public Controller() {

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
	 * Realiza a operação de filtragem com o filtro passa alta.
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

		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.passaAlta(fr, raio);
		resultadoFreq = fr.getEspectro();
		
		// Voltar para o domínio do espaço
		fr.transform();
		resultadoEspaco = fr.toImage(fr.getGrayImage());

		// Persistência em arquivo com formato bmp
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

		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.passaBaixa(fr, raio);
		resultadoFreq = fr.getEspectro();
		
		// Voltar para o domínio do espaço
		fr.transform();
		resultadoEspaco = fr.toImage(fr.getGrayImage());

		// Persistência em arquivo com formato bmp
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

		// Filtragem no domínio da freqüência
		FourierImagem fr = new FourierImagem(src);
		fr.transform();
		Filtragem.passaFaixa(fr, raioInterno,raioExterno);
		resultadoFreq = fr.getEspectro();
		
		// Voltar para o domínio do espaço
		fr.transform();
		resultadoEspaco = fr.toImage(fr.getGrayImage());

		// Persistência em arquivo com formato bmp
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

