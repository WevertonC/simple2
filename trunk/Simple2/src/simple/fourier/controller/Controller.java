package simple.fourier.controller;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import simple.fourier.core.FourierImage;
import simple.fourier.exceptions.FourierException;

/**
 * Controlador para o m�dulo Fourier. O controlador permite, de uma forma mais ligada ao
 * dom�nio da aplica��o, o acesso �s funcionalidades deste m�dulo.
 * 
 * @author Elloa B. Guedes - elloa@dsc.ufcg.edu.br
 * @author Odilon F. Lima Jr. - odilon@dsc.ufcg.edu.br
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
			ImageIO.write(resultadoFFT, "BMP", outputFile);
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

		FourierImage fft = new FourierImage(src);
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

}
