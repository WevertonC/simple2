/*
 * TestEscalaDeCinza
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

package simple.tests;

import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Classe que testa a classe EscalaCinza
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class TestEscalaDeCinza extends TestCase {
	
	protected ImageIcon imagem1, imagem2, imagem3, imagem4, imagem;
	protected int[] pixelsMeu, pixelsOutro, pixelsMeu2, pixelsOutro2;
	protected PixelGrabber pg1, pg2, pg3, pg4;
	
	/**
	 * Construtor do teste
	 */
	public TestEscalaDeCinza(String st) {
		super(st);
		
	}
	/**
	 * Metodo suite que cria o suite de testes
	 * @return O suite da classe
	 */
	public static Test suite(){
	    return new TestSuite(TestEscalaDeCinza.class);
	}
	/**
	 * Metodo setUp que inicializa as variaveis de teste
	 */
	public void setUp() {		
		imagem1 = new ImageIcon("Imagens/barcoMeu.jpg");
		imagem2 = new ImageIcon("Imagens/barcoOutro.jpg");
		imagem3 = new ImageIcon("Imagens/valeMeu.jpg");
		imagem4 = new ImageIcon("Imagens/valeOutro.jpg");		
	}
	/**
	 * Metodo testImage1Image2 que verifica pixel a pixel se as imagem estao iguais
	 */	
	public void testImagem1Imagem2() {
		
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
	 * Metodo testImage3Image4 que verifica pixel a pixel se as imagem estao iguais
	 *
	 */
	public void testImagem3Imagem4() {
		
		pixelsMeu2 = new int[imagem3.getIconWidth()*imagem3.getIconHeight()];
		pg3 = new PixelGrabber(imagem3.getImage(),0,0,imagem3.getIconWidth(),imagem3.getIconHeight(),pixelsMeu2,0,imagem3.getIconWidth());
		
		pixelsOutro2 = new int[imagem4.getIconWidth()*imagem4.getIconHeight()];
		pg4 = new PixelGrabber(imagem4.getImage(),0,0,imagem4.getIconWidth(),imagem4.getIconHeight(),pixelsOutro2,0,imagem4.getIconWidth());
		try {
			pg3.grabPixels();
			pg4.grabPixels();
		} catch (InterruptedException e) {}
	
		for (int i = 0; i < imagem3.getIconWidth()*imagem3.getIconHeight(); i++) 
			assertEquals(pixelsMeu2[i],pixelsOutro2[i]);
	}
	/**
	 * Metodo main que executa a classe
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}	
}
