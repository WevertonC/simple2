package simple.testes;
/*
 * TestFiltroMedian
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
 * Classe que testa a classe FiltroMedian
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class TestFiltroMedian extends TestCase {
	
	protected ImageIcon imagem1, imagem2;
	protected int[] pixelsMeu, pixelsOutro;
	protected PixelGrabber pg1, pg2;
	
	/**
	 * Construtor do teste
	 */
	public TestFiltroMedian(String st) {
		super(st);
		
	}
	/**
	 * Metodo suite que cria o suite de testes
	 * @return O suite da classe
	 */
	public static Test suite(){
	    return new TestSuite(TestFiltroMedian.class);
	}
	/**
	 * Metodo setUp que inicializa as variaveis de teste
	 */
	public void setUp() {		
		imagem1 = new ImageIcon("Imagens/lenaMedian.jpg");
		imagem2 = new ImageIcon("Imagens/lenaMedianFePDI.jpg");	
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
	 * Metodo main que executa a classe
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}	
}
