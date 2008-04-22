package radiometricas;
/*
 * EscalaCinza
 * 
 * @version 1.0
 * 
 * Date: 28/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.Image;
import java.awt.Label;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;

/**
 * Classe que faz a Escala de Cinza da Imagem
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class Binariza {

	/**
	 * Metodo nivelCinza que faz a transfomacao da Imagem em Nivel de Cinza 
	 * @param imagem A image a ser transformada
	 * @return A imagem em nivel de cinza
	 */
	public static Image binarizacao(ImageIcon imagem){
		int[] pixelsOriginal = new int[imagem.getIconWidth()*imagem.getIconHeight()];
		PixelGrabber pg = new PixelGrabber(imagem.getImage().getScaledInstance(imagem.getIconWidth(),imagem.getIconHeight(),0)
				,0,0,imagem.getIconWidth(),imagem.getIconHeight(),pixelsOriginal,0,imagem.getIconWidth());
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
		}
		int[] alpha = new int[imagem.getIconWidth()*imagem.getIconHeight()];
		int[] red = new int[imagem.getIconWidth()*imagem.getIconHeight()];
		int[] green = new int[imagem.getIconWidth()*imagem.getIconHeight()];
		int[] blue = new int[imagem.getIconWidth()*imagem.getIconHeight()];
		int[] media = new int[imagem.getIconWidth()*imagem.getIconHeight()];
		int[] cinza = new int[imagem.getIconWidth()*imagem.getIconHeight()];
		for (int i = 0; i < imagem.getIconWidth()*imagem.getIconHeight(); i++) {
			alpha[i] = ((pixelsOriginal[i] & 0xff000000) >> 24);
			red[i]   = ((pixelsOriginal[i] & 0xff0000) >> 16); 
			green[i] = ((pixelsOriginal[i] & 0x00ff00) >> 8);
			blue[i]  = (pixelsOriginal[i] & 0xff);
			media[i] = (red[i] + blue[i] + green[i])/3;
			if(media[i] > 128)
				media[i] = 255;
			else
				media[i] = 0;
		}
		for(int n = 0; n < imagem.getIconWidth()*imagem.getIconHeight(); n++) 
			cinza[n] = (((media[n]) << 16) | ((media[n]) << 8) | (media[n]) | (alpha[n]  <<  24));
		return (new Label()).createImage(new MemoryImageSource(imagem.getIconWidth(),imagem.getIconHeight(),cinza,0,imagem.getIconWidth()));
	}
}