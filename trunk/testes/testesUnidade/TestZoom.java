/*
 * TestZoom
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

package testesUnidade;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import util.MyImage;
import exceptions.ZoomException;
import facade.Facade;

/**
 * Classe que testa a classe Zoom
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class TestZoom extends TestCase{
	
	protected MyImage i, j;
	protected Facade facade;

	/**
	 * Construtor do teste
	 */
	public TestZoom(String msn){
		super(msn);
	}
	/**
	 * Metodo suite que cria o suite de testes
	 * @return O suite da classe
	 */
	public static Test suite(){
	    return new TestSuite(TestZoom.class);
	}
	/**
	 * Metodo setUp que inicializa as variaveis de teste
	 */
	public void setUp() throws ZoomException{
		i = new MyImage("Imagens/Camburiu.jpg");
		j = new MyImage("Imagens/Camburiu.jpg");		
		facade = Facade.getInstance();
	}
	/**
	 * Metodo testZoom que testa o Zoom comum
	 */
	public void testZoom(){
		try {
			facade.zoom(j,20);
		} catch (ZoomException e) {
			fail();
		}
		assertEquals("1", j.getAltura(), 120);
		assertEquals("2", j.getLargura(), 160);		
	}
	/**
	 * Metodo testZoomMenos que testa o ZoomMenos
	 */
	public void testZoomMenos(){
		try {
			facade.zoomMenos(i);
		} catch (ZoomException e) {
			fail();
		}
		assertEquals("5",i.getAltura(), 540);
		assertEquals("6",i.getLargura(), 720);
	}
	/**
	 * Metodo testZoomMais que testa o ZoomMais
	 */
	public void testZoomMais(){
		try {
			facade.zoomMais(i);
		} catch (ZoomException e) {
			fail();
		}
		assertEquals("3", i.getAltura(), 660);
		assertEquals("4", i.getLargura(), 880);		
	}	
	/**
	 * Metodo main que executa a classe
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}	
}