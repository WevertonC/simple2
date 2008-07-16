package simple.fourier.facade;

import simple.fourier.controller.Controller;
import simple.fourier.exceptions.FourierException;

/**
 * Facade para o módulo Fourier. Provê acesso às funcionalidades deste pacote.
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
	 * Realiza a Transformada Rápida de Fourier em uma imagem.
	 * 
	 * @param imagePath O caminho do arquivo da imagem de entrada
	 * @param outputFileName O caminho do arquivo da imagem de saída
	 */
	public void fft(String imagePath){
		int pos = imagePath.substring(1).indexOf(".");
		String outputFileName = imagePath.substring(0, pos+1) + "-output" + ".jpg";
		
		try {
			if (controller.fft(imagePath, outputFileName)){
				System.out.println("Operação realizada com sucesso.");
			}
		} catch (FourierException e) {
			e.printStackTrace();
		}		
	}
	
	public void passaAlta(String imagePath, double raio){
		int pos = imagePath.substring(1).indexOf(".");
		String outputFileName = imagePath.substring(0, pos+1) + "-output-passaAlta" + ".jpg";
		
		try {
			if (controller.passaAlta(imagePath, outputFileName,raio)){
				System.out.println("Operação realizada com sucesso.");
			}
		} catch (FourierException e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) {
		Facade facade = new Facade();
		try {
			facade.passaAlta("./images/lena.bmp",0.5);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
