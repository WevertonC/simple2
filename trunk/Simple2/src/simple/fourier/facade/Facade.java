package simple.fourier.facade;

import simple.fourier.controller.Controller;
import simple.fourier.exceptions.FourierException;

/**
 * Facade para o m�dulo Fourier. Prov� acesso �s funcionalidades deste pacote.
 * 
 * @author Elloa B. Guedes - elloa@dsc.ufcg.edu.br
 * @author Odilon F. Lima Jr. - odilon@dsc.ufcg.edu.br
 *
 */
public class Facade {
	
	private Controller controller;
	
	/**
	 * Construtor vazio default.
	 */
	public Facade(){
		this.controller = new Controller();
	}
	
	/**
	 * Realiza a Transformada R�pida de Fourier em uma imagem.
	 * 
	 * @param imagePath O caminho do arquivo da imagem de entrada
	 * @param outputFileName O caminho do arquivo da imagem de sa�da
	 */
	public void fft(String imagePath, String outputFileName){
		try {
			if (controller.fft(imagePath, outputFileName)){
				System.out.println("Opera��o realizada com sucesso.");
			}
		} catch (FourierException e) {
			e.printStackTrace();
		}		
	}
	
}
