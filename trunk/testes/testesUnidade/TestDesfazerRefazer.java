/*
 * TestDesfazerRefazer
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

package testesUnidade;

import java.awt.Image;

import javax.swing.ImageIcon;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import util.MyBufferedImage;
import util.MyImage;
import exceptions.ZoomException;

/**
 * Classe que testa a classe MyImage
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class TestDesfazerRefazer extends TestCase{
	
	protected MyImage imagem;
	protected ImageIcon imagemOriginal;
	
	/**
	 * Construtor do teste
	 */
	public TestDesfazerRefazer(String msg){
		super(msg);
	}
	/**
	 * Metodo suite que cria o suite de testes
	 * @return O suite da classe
	 */
	public static Test suite(){
	    return new TestSuite(TestDesfazerRefazer.class);
	}
	/**
	 * Metodo setUp que inicializa as variaveis de teste
	 * @throws ZoomException 
	 */
	public void setUp() throws ZoomException{
		imagem = new MyImage("Imagens/baboon.jpg");
		imagemOriginal = new ImageIcon("Imagens/baboon.jpg");
	}
	/**
	 * Metodo testDesfazer que testa o desfazer da classe MyImage
	 */
	public void testDesfazer(){
		try {
			imagem.setZoom(0.1f,0.1f);
		} catch (ZoomException e) {
			System.err.println(e.getMessage());
		}
		Image desfeita = MyBufferedImage.makeImage(imagem.desfazer());
		assertEquals(imagemOriginal.getIconWidth(),desfeita.getWidth(null));
		assertEquals(imagemOriginal.getIconHeight(),desfeita.getHeight(null));
	}
	/**
	 * Metodo testRefazer que testa o desfazer da classe MyImage
	 */
	public void testRefazer(){
		Image refeita = MyBufferedImage.makeImage(imagem.refazer());
		assertEquals(imagemOriginal.getIconWidth(),refeita.getWidth(null));
		assertEquals(imagemOriginal.getIconHeight(),refeita.getHeight(null));
	}
	/**
	 * Metodo main que executa a classe
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}