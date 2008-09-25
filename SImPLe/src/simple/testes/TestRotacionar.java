/*
 * TestRotacionar
 * 
 * @version 1.0
 * 
 * Date: 28/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

package simple.testes;

import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Classe que testa a classe Rotacionar
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class TestRotacionar extends TestCase{
	
	protected ImageIcon i, j;
	
	/**
	 * Construtor do teste
	 */
	public TestRotacionar(String msg){
		super(msg);
	}
	/**
	 * Metodo suite que cria o suite de testes
	 * @return O suite da classe
	 */
	public static Test suite(){
	    return new TestSuite(TestRotacionar.class);
	}
	/**
	 * Metodo setUp que inicializa as variaveis de teste
	 */
	public void setUp(){
		i = new ImageIcon("Imagens/rotOriginal345.jpg");
		j = new ImageIcon("Imagens/rotacionada345.jpg");
	}
	/**
	 * Metodo testAdicao que verifica pixel a pixels se as imagens estao iguais
	 */
	public void testRotacao45(){
		int[] pixelsMeu = new int[j.getIconWidth()*j.getIconHeight()];
		PixelGrabber pg1 = new PixelGrabber(j.getImage(),0,0,j.getIconWidth(),j.getIconHeight(),pixelsMeu,0,j.getIconWidth());
		int[] pixelsOutro = new int[i.getIconWidth()*i.getIconHeight()];
		PixelGrabber pg2 = new PixelGrabber(i.getImage(),0,0,i.getIconWidth(),i.getIconHeight(),pixelsOutro,0,i.getIconWidth());
		try {
			pg1.grabPixels();
			pg2.grabPixels();
		} catch (InterruptedException e) {}
		for (int k = 0; k < j.getIconWidth()*j.getIconHeight(); k++) 
			assertEquals(""+(k+1),pixelsMeu[k],(int)pixelsOutro[k]);
	}
}
