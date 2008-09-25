package simple.modules.fourier.facade;

import java.awt.image.BufferedImage;

import simple.modules.fourier.controller.Controller;
import simple.modules.fourier.exceptions.FourierException;


/**
 * Facade para o m�dulo Fourier. Prov� acesso �s funcionalidades deste pacote.
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
	 * Realiza a Transformada R�pida de Fourier em uma imagem.
	 * 
	 * @param imagePath O caminho do arquivo da imagem de entrada
	 * @param outputFileName O caminho do arquivo da imagem de sa�da
	 */
	public void fft(String imagePath){
		int pos = imagePath.substring(1).indexOf(".");
		String outputFileName = imagePath.substring(0, pos+1) + "-output" + ".jpg";

		try {
			if (controller.fft(imagePath, outputFileName)){
				System.out.println("Opera��o realizada com sucesso.");
			}
		} catch (FourierException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Aplica��o do filtro Passa Alta com raio dado pelo usu�rio
	 * 
	 * @param img a imagem a ser filtrada
	 * @param raio o raio da imagem
	 * @return a imagem filtrada no dom�nio da frequ�ncia
	 * @throws FourierException caso a imagem n�o possa ser filtrada, por exemplo, se n�o � quadrada.
	 */
	public BufferedImage passaAltaFreq(BufferedImage img, double raio) throws FourierException{
		return controller.passaAltaFreq(img,raio);
	}


	/**
	 * Aplica��o do filtro Passa Alta com raio dado pelo usu�rio
	 * 
	 * @param img a imagem a ser filtrada
	 * @param raio o raio da imagem
	 * @return a imagem filtrada no dom�nio do espaco
	 * @throws FourierException caso a imagem n�o possa ser filtrada, por exemplo, se n�o � quadrada.
	 */
	public BufferedImage passaAltaEsp(BufferedImage img, double raio) throws FourierException{
		return controller.passaAltaEsp(img,raio);
	}

	/**
	 * Aplica��o do filtro Passa Baixa com raio dado pelo usu�rio
	 * 
	 * @param img a imagem a ser filtrada
	 * @param raio o raio da imagem
	 * @return a imagem filtrada no dom�nio da frequ�ncia
	 * @throws FourierException caso a imagem n�o possa ser filtrada, por exemplo, se n�o � quadrada.
	 */
	public BufferedImage passaBaixaFreq(BufferedImage img, double raio) throws FourierException{
		return controller.passaBaixaFreq(img,raio);
	}

	/**
	 * Aplica��o do filtro Passa Baixa com raio dado pelo usu�rio
	 * 
	 * @param img a imagem a ser filtrada
	 * @param raio o raio da imagem
	 * @return a imagem filtrada no dom�nio do espaco
	 * @throws FourierException caso a imagem n�o possa ser filtrada, por exemplo, se n�o � quadrada.
	 */
	public BufferedImage passaBaixaEsp(BufferedImage img, double raio) throws FourierException{
		return controller.passaBaixaEsp(img,raio);
	}

	/**
	 * Aplica��o do filtro Passa Faixa com raio dado pelo usu�rio
	 * 
	 * @param img a imagem a ser filtrada
	 * @param raio o raio da imagem
	 * @return a imagem filtrada no dom�nio da frequ�ncia
	 * @throws FourierException caso a imagem n�o possa ser filtrada, por exemplo, se n�o � quadrada.
	 */
	public BufferedImage passaFaixaFreq(BufferedImage img, double raioInterno,double raioExterno) throws FourierException{
		return controller.passaFaixaFreq(img,raioInterno,raioExterno);
	}
	
	/**
	 * Aplica��o do filtro Passa Faixa com raio dado pelo usu�rio
	 * 
	 * @param img a imagem a ser filtrada
	 * @param raio o raio da imagem
	 * @return a imagem filtrada no dom�nio do espaco
	 * @throws FourierException caso a imagem n�o possa ser filtrada, por exemplo, se n�o � quadrada.
	 */
	public BufferedImage passaFaixaEsp(BufferedImage img, double raioInterno,double raioExterno) throws FourierException{
		return controller.passaFaixaFreq(img,raioInterno,raioExterno);
	}
	

}
