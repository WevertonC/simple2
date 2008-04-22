package util;
/*
 * MyObject
 *  
 * @version 1.0
 * 
 * Date: 28/09/05
 * 
 * Copyright FEDPI all rights reserved
 */



import java.awt.image.RenderedImage;

/**
 * Classe que representa um obejto com uma RenderedImage 
 * e um array com os valores de zoom
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class MyObject {
	
	private RenderedImage renderedImage;
	private float[] array;
	
	/**
	 * Construtor da classe com uma RenderedImage e um array com o valor do zoom
	 * @param renderedImage A imagem
	 * @param array Com os valores do zoom
	 */
	public MyObject(RenderedImage renderedImage, float[] array){
		this.renderedImage = renderedImage;
		this.array = array;
	}
	/**
	 * Metodo getArray que retorna o array com os valores do zoom
	 * @return Os valores do zoom
	 */	
	public float[] getArray() {
		return array;
	}
	/**
	 * Metodo setArray que seta os valores do zoom
	 * @param array O novo array
	 */
	public void setArray(float[] array) {
		this.array = array;
	}
	/**
	 * Metodo getRenderedImage que retorna a imagem da classe
	 * @return A imagem Rendered da classe
	 */
	public RenderedImage getRenderedImage() {
		return renderedImage;
	}
	/**
	 * Metodo setRenderedImage que seta a nova imagem
	 * @param renderedImage A nova imagem Rendered
	 */
	public void setRenderedImage(RenderedImage renderedImage) {
		this.renderedImage = renderedImage;
	}
}