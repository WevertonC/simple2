package simple.modules.fourier.facade;

import java.awt.image.BufferedImage;

import simple.modules.fourier.controller.Controller;
import simple.modules.fourier.exceptions.FourierException;


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

	/**
	 * Aplicação do filtro Passa Alta com raio dado pelo usuário
	 * 
	 * @param img a imagem a ser filtrada
	 * @param raio o raio da imagem
	 * @return a imagem filtrada no domínio da frequência
	 * @throws FourierException caso a imagem não possa ser filtrada, por exemplo, se não é quadrada.
	 */
	public BufferedImage passaAltaFreq(BufferedImage img, double raio) throws FourierException{
		return controller.passaAltaFreq(img,raio);
	}


	/**
	 * Aplicação do filtro Passa Alta com raio dado pelo usuário
	 * 
	 * @param img a imagem a ser filtrada
	 * @param raio o raio da imagem
	 * @return a imagem filtrada no domínio do espaco
	 * @throws FourierException caso a imagem não possa ser filtrada, por exemplo, se não é quadrada.
	 */
	public BufferedImage passaAltaEsp(BufferedImage img, double raio) throws FourierException{
		return controller.passaAltaEsp(img,raio);
	}

	/**
	 * Aplicação do filtro Passa Baixa com raio dado pelo usuário
	 * 
	 * @param img a imagem a ser filtrada
	 * @param raio o raio da imagem
	 * @return a imagem filtrada no domínio da frequência
	 * @throws FourierException caso a imagem não possa ser filtrada, por exemplo, se não é quadrada.
	 */
	public BufferedImage passaBaixaFreq(BufferedImage img, double raio) throws FourierException{
		return controller.passaBaixaFreq(img,raio);
	}

	/**
	 * Aplicação do filtro Passa Baixa com raio dado pelo usuário
	 * 
	 * @param img a imagem a ser filtrada
	 * @param raio o raio da imagem
	 * @return a imagem filtrada no domínio do espaco
	 * @throws FourierException caso a imagem não possa ser filtrada, por exemplo, se não é quadrada.
	 */
	public BufferedImage passaBaixaEsp(BufferedImage img, double raio) throws FourierException{
		return controller.passaBaixaEsp(img,raio);
	}

	/**
	 * Aplicação do filtro Passa Faixa com raio dado pelo usuário
	 * 
	 * @param img a imagem a ser filtrada
	 * @param raio o raio da imagem
	 * @return a imagem filtrada no domínio da frequência
	 * @throws FourierException caso a imagem não possa ser filtrada, por exemplo, se não é quadrada.
	 */
	public BufferedImage passaFaixaFreq(BufferedImage img, double raioInterno,double raioExterno) throws FourierException{
		return controller.passaFaixaFreq(img,raioInterno,raioExterno);
	}
	
	/**
	 * Aplicação do filtro Passa Faixa com raio dado pelo usuário
	 * 
	 * @param img a imagem a ser filtrada
	 * @param raio o raio da imagem
	 * @return a imagem filtrada no domínio do espaco
	 * @throws FourierException caso a imagem não possa ser filtrada, por exemplo, se não é quadrada.
	 */
	public BufferedImage passaFaixaEsp(BufferedImage img, double raioInterno,double raioExterno) throws FourierException{
		return controller.passaFaixaFreq(img,raioInterno,raioExterno);
	}
	

}
