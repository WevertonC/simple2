package testesUnidade;
/*
 * TestFiltroGaussian
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */


import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Classe que testa a classe FiltroGaussian
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class TestFiltroGaussian extends TestCase {
	
	protected ImageIcon imagem1, imagem2, imagem3, imagem4, imagem5, imagem6, imagem7, imagem8;
	protected int[] pixelsMeu, pixelsOutro;
	protected PixelGrabber pg1, pg2; 
	
	/**
	 * Construtor do teste
	 */
	public TestFiltroGaussian(String st) {
		super(st);
		
	}
	/**
	 * Metodo suite que cria o suite de testes
	 * @return O suite da classe
	 */
	public static Test suite(){
	    return new TestSuite(TestFiltroGaussian.class);
	}
	/**
	 * Metodo setUp que inicializa as variaveis de teste
	 */
	public void setUp() {		
		imagem1 = new ImageIcon("Imagens/lenaGaus3x3d3.jpg");
		imagem3 = new ImageIcon("Imagens/lenaGaus5x5d4.jpg");
		imagem5 = new ImageIcon("Imagens/lenaGaus7x7d6.jpg");
		imagem7 = new ImageIcon("Imagens/lenaGaus9x9d7.jpg");	
		
		imagem2 = new ImageIcon("Imagens/lenaGaus3x3d3FePDI.jpg");
		imagem4 = new ImageIcon("Imagens/lenaGaus5x5d4FePDI.jpg");
		imagem6 = new ImageIcon("Imagens/lenaGaus7x7d6FePDI.jpg");
		imagem8 = new ImageIcon("Imagens/lenaGaus9x9d7FePDI.jpg");
	}
	/**
	 * Metodo testGaus3x3d3 que verifica pixel a pixel se as imagem estao iguais
	 */	
	public void testGaus3x3d3() {
		
		pixelsMeu = new int[imagem1.getIconWidth()*imagem1.getIconHeight()];
		pg1 = new PixelGrabber(imagem1.getImage(),0,0,imagem1.getIconWidth(),imagem1.getIconHeight(),pixelsMeu,0,imagem1.getIconWidth());
		
		pixelsOutro = new int[imagem2.getIconWidth()*imagem2.getIconHeight()];
		pg2 = new PixelGrabber(imagem2.getImage(),0,0,imagem2.getIconWidth(),imagem2.getIconHeight(),pixelsOutro,0,imagem2.getIconWidth());
		try {
			pg1.grabPixels();
			pg2.grabPixels();
		} catch (InterruptedException e) {}
		
		for (int i = 0; i < imagem1.getIconWidth()*imagem1.getIconHeight(); i++) 
			assertEquals(pixelsMeu[i],pixelsOutro[i]);
	}
	/**
	 * Metodo testGaus5x5d4 que verifica pixel a pixel se as imagem estao iguais
	 *
	 */
	public void testGaus5x5d4() {
		
		pixelsMeu = new int[imagem3.getIconWidth()*imagem3.getIconHeight()];
		pg1 = new PixelGrabber(imagem3.getImage(),0,0,imagem3.getIconWidth(),imagem3.getIconHeight(),pixelsMeu,0,imagem3.getIconWidth());
		
		pixelsOutro = new int[imagem4.getIconWidth()*imagem4.getIconHeight()];
		pg2 = new PixelGrabber(imagem4.getImage(),0,0,imagem4.getIconWidth(),imagem4.getIconHeight(),pixelsOutro,0,imagem4.getIconWidth());
		try {
			pg1.grabPixels();
			pg2.grabPixels();
		} catch (InterruptedException e) {}
		
		for (int i = 0; i < imagem3.getIconWidth()*imagem3.getIconHeight(); i++) 
			assertEquals(pixelsMeu[i],pixelsOutro[i]);
	}
	
	/**
	 * Metodo tesGaus7x7d6 que verifica pixel a pixel se as imagem estao iguais
	 *
	 */
	public void tesGaus7x7d6() {
		
		pixelsMeu = new int[imagem5.getIconWidth()*imagem5.getIconHeight()];
		pg1 = new PixelGrabber(imagem5.getImage(),0,0,imagem5.getIconWidth(),imagem5.getIconHeight(),pixelsMeu,0,imagem5.getIconWidth());
		
		pixelsOutro = new int[imagem6.getIconWidth()*imagem6.getIconHeight()];
		pg2 = new PixelGrabber(imagem6.getImage(),0,0,imagem6.getIconWidth(),imagem6.getIconHeight(),pixelsOutro,0,imagem6.getIconWidth());
		try {
			pg1.grabPixels();
			pg2.grabPixels();
		} catch (InterruptedException e) {}
		
		for (int i = 0; i < imagem5.getIconWidth()*imagem5.getIconHeight(); i++) 
			assertEquals(pixelsMeu[i],pixelsOutro[i]);
	}
	
	/**
	 * Metodo testGaus9x9d7 que verifica pixel a pixel se as imagem estao iguais
	 *
	 */
	public void testGaus9x9d7() {
		
		pixelsMeu = new int[imagem7.getIconWidth()*imagem7.getIconHeight()];
		pg1 = new PixelGrabber(imagem7.getImage(),0,0,imagem7.getIconWidth(),imagem7.getIconHeight(),pixelsMeu,0,imagem7.getIconWidth());
		
		pixelsOutro = new int[imagem8.getIconWidth()*imagem8.getIconHeight()];
		pg2 = new PixelGrabber(imagem8.getImage(),0,0,imagem8.getIconWidth(),imagem8.getIconHeight(),pixelsOutro,0,imagem8.getIconWidth());
		try {
			pg1.grabPixels();
			pg2.grabPixels();
		} catch (InterruptedException e) {}
		
		for (int i = 0; i < imagem7.getIconWidth()*imagem7.getIconHeight(); i++) 
			assertEquals(pixelsMeu[i],pixelsOutro[i]);
	}
	/**
	 * Metodo main que executa a classe
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}	
}