package simple.tests;
/*
 * TestRequantizar
 * 
 * @version 1.0
 * 
 * Date: 12/11/05
 * 
 * Copyright FEDPI all rights reserved
 */
import java.awt.Image;
import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;

import junit.framework.TestCase;

/**
 * Classe que testa a classe Requantizador
 * @version 1.0 12/11/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class TestRequantizar extends TestCase {
	
	protected Image i, j, k, l;
		
	public void setUp(){
		i = new ImageIcon("Imagens/EuPri Meu.jpg").getImage();
		j = new ImageIcon("Imagens/EuPri Outro.jpg").getImage();
		k = new ImageIcon("Imagens/Eu e Maneu OR Meu.jpg").getImage();
		l = new ImageIcon("Imagens/Eu e Maneu OR Outro.jpg").getImage();
	}
	
	public void testMaskAND(){
		
		int[] pixelMeu = new int[i.getWidth(null)*i.getHeight(null)];
		int[] pixelOutro = new int[i.getWidth(null)*i.getHeight(null)];
		
		PixelGrabber pg1 = new PixelGrabber(i, 0, 0, i.getHeight(null),i.getWidth(null),pixelMeu, 0, i.getWidth(null));		
		PixelGrabber pg2 = new PixelGrabber(j, 0, 0, i.getHeight(null),i.getWidth(null),pixelOutro, 0, i.getWidth(null));
		
		try {
			pg1.grabPixels();
			pg2.grabPixels();
		} catch (InterruptedException e) {}
		for (int x = 0; x < i.getHeight(null) * i.getWidth(null); x++) {
			assertEquals(x + ":", pixelMeu[x], pixelOutro[x]);
		}
	}
	
	public void testMaskOR(){
		
		int[] pixelMeu = new int[k.getWidth(null)*k.getHeight(null)];
		int[] pixelOutro = new int[k.getWidth(null)*k.getHeight(null)];
		
		PixelGrabber pg1 = new PixelGrabber(k, 0, 0, k.getHeight(null),k.getWidth(null),pixelMeu, 0, k.getWidth(null));		
		PixelGrabber pg2 = new PixelGrabber(l, 0, 0, k.getHeight(null),k.getWidth(null),pixelOutro, 0, k.getWidth(null));
		
		try {
			pg1.grabPixels();
			pg2.grabPixels();
		} catch (InterruptedException e) {}
		for (int x = 0; x < k.getHeight(null) * k.getWidth(null); x++) {
			assertEquals(x + ":", pixelMeu[x], pixelOutro[x]);
		}
	}	
}
