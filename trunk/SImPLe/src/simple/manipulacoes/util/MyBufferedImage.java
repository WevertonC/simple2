/*
 * MyBufferedImage
 * 
 * @version 1.0
 * 
 * Date: 28/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

package simple.manipulacoes.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import javax.swing.ImageIcon;

/**
 * Classe que faz conversoes entre tipos de imagens
 * @version 1.0 20/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */ 

public class MyBufferedImage {
		
	/**
	 * Metodo makeBufferedImage que cria um BufferedImage a partir de uma imagem
	 * @param image qual voce deseja fazer um BufferedImage
	 * @return BufferedImage A imagem bufferred
	 */
	public static BufferedImage makeBufferedImage(Image image) {
		return makeBufferedImage(image, BufferedImage.TYPE_INT_RGB);
	}
	/**
	 * Metodo makeBufferedImage que cria um BufferedImage a partir de uma imagem e o seu tipo
	 * @param image a image a qual voce deseja fazer um BufferedImage
	 * @param imageType O tipo da imagem
	 * @return BufferedImage A imagem bufferred
	 */
	public static BufferedImage makeBufferedImage(Image image, int imageType) {
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),image.getHeight(null),imageType);
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.drawImage(image, null, null);
		return bufferedImage;
	}	
	/**
	 * Metodo makeBufferedImage que cria um BufferedImage a partir de uma RenderedImage
	 * @param image a RenderedImage a qual voce deseja fazer uma BufferedImage
	 * @return BufferedImage A imagem bufferred
	 */
	public static BufferedImage makeBufferedImage(RenderedImage render) {
		BufferedImage bufferedImage = new BufferedImage(render.getWidth(),render.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.drawRenderedImage(render,new AffineTransform());
		return bufferedImage;		
	}	
	/**
	 * Metodo makeImage que cria uma Image a partir de uma RenderedImage
	 * @param image a RenderedImage a qual voce deseja fazer uma Image
	 * @return Image A imagem Image
	 */
	public static Image makeImage(RenderedImage render){
		return new ImageIcon(makeBufferedImage(render)).getImage();
	}
	/**
	 * Metodo makeImage que cria uma Image a partir de uma BufferedImage
	 * @param image a BufferedImage a qual voce deseja fazer uma Image
	 * @return Image A imagem Image
	 */
	public static Image makeImage(BufferedImage buffer){
		return new ImageIcon(buffer).getImage();
	}	
}
