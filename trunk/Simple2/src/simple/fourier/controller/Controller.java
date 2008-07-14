package simple.fourier.controller;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import simple.fourier.core.FourierImage;
import simple.fourier.exceptions.FourierException;

/**
 * Controlador para o módulo Fourier. O controlador permite, de uma forma mais ligada ao
 * domínio da aplicação, o acesso às funcionalidades deste módulo.
 * 
 * @author Elloa B. Guedes - elloa@dsc.ufcg.edu.br
 * @author Odilon F. Lima Jr. - odilon@dsc.ufcg.edu.br
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
			ImageIO.write(resultadoFFT, "BMP", outputFile);
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

		FourierImage fft = new FourierImage(src);
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

}
