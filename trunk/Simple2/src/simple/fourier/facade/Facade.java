package simple.fourier.facade;

import simple.fourier.controller.Controller;
import simple.fourier.exceptions.FourierException;

/**
 * Facade para o módulo Fourier. Provê acesso às funcionalidades deste pacote.
 * 
 * @author Elloa B. Guedes - elloa@dsc.ufcg.edu.br
 * @author Odilon F. Lima Junior - odilonflj@dsc.ufcg.edu.br
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

	public void passaBaixa(String imagePath, double raio){
		int pos = imagePath.substring(1).indexOf(".");
		String outputFileName = imagePath.substring(0, pos+1) + "-output-passaBaixa" + ".jpg";

		try {
			if (controller.passaBaixa(imagePath, outputFileName,raio)){
				System.out.println("Operação realizada com sucesso.");
			}
		} catch (FourierException e) {
			e.printStackTrace();
		}		
	}
	
	public void passaFaixa(String imagePath, double raioInterno,double raioExterno){
		int pos = imagePath.substring(1).indexOf(".");
		String outputFileName = imagePath.substring(0, pos+1) + "-output-passaFaixa" + ".jpg";

		try {
			if (controller.passaFaixa(imagePath, outputFileName,raioInterno,raioExterno)){
				System.out.println("Operação realizada com sucesso.");
			}
		} catch (FourierException e) {
			e.printStackTrace();
		}		
	}

	public static void main(String[] args) {
		Facade facade = new Facade();
		try {
			if(args[0].equalsIgnoreCase("-fft")){
				facade.fft(args[1]);
			} else if(args[0].equalsIgnoreCase("-passaAlta")){
				facade.passaAlta(args[1],0.5);
			}else if(args[0].equalsIgnoreCase("-passaBaixa")){
				facade.passaBaixa(args[1],0.5);
			}
			else if(args[0].equalsIgnoreCase("-passaFaixa")){
				facade.passaFaixa(args[1],0.3,0.2);
			}



		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
