/*
 * TestHistograma
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

package testesUnidade;

import histograma.Histograma;

import javax.swing.ImageIcon;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Classe que testa a classe Histograma
 * @version 1.0 20/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class TestHistograma extends TestCase {

	protected Histograma h;
	
	/**
	 * Construtor do teste
	 */
	public TestHistograma(String msn){
		super(msn);
	}
	/**
	 * Metodo suite que cria o suite de testes
	 * @return O suite da classe
	 */
	public static Test suite(){
	    return new TestSuite(TestHistograma.class);
	}
	/**
	 * Metodo setUp que inicializa as variaveis de teste
	 */
	public void setUp(){
		ImageIcon i = new ImageIcon("Imagens/baboon.jpg");
		h = new Histograma(i.getImage());	
	}
	/**
	 * Metodo testMedia que testa a media da imagem por canal(RGB e GrayScale)
	 */
	public void testMedia(){
		assertEquals(137.76+"",h.getMedia(h.getHistogram(0))+"");
		assertEquals(128.52+"",h.getMedia(h.getHistogram(1))+"");
		assertEquals(113.69+"",h.getMedia(h.getHistogram(2))+"");
		assertEquals(126.33+"",h.getMedia(h.getGrayHistograma())+"");
	}
	
	/**
	 * Metodo testVariancia que testa a variancia da imagem por canal(RGB e GrayScale)
	 */
	public void testVariancia(){
		assertEquals(2895.14+"",h.getVariancia(h.getHistogram(0))+"");
		assertEquals(1905.71+"",h.getVariancia(h.getHistogram(1))+"");
		assertEquals(3395.08+"",h.getVariancia(h.getHistogram(2))+"");
		assertEquals(1667.09+"",h.getVariancia(h.getGrayHistograma())+"");
	}
		
	/**
	 * Metodo testDesvioPadrao que testa o desvio padrao por canal(RGB e GrayScale)
	 */
	public void testDesvioPadrao(){
		assertEquals(53.81+"",h.getDesvioPadrao(h.getHistogram(0))+"");
		assertEquals(43.65+"",h.getDesvioPadrao(h.getHistogram(1))+"");
		assertEquals(58.27+"",h.getDesvioPadrao(h.getHistogram(2))+"");
		assertEquals(40.83+"",h.getDesvioPadrao(h.getGrayHistograma())+"");
	}
	/**
	 * Metodo main que executa a classe
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}	
}