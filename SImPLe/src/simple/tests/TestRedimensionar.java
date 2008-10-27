/*
 * TestRedimensionar
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

package simple.tests;

import simple.excecoes.RedimensionarException;

import javax.swing.ImageIcon;

import simple.modules.operacoes.geometrica.Redimensionar;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Classe que testa a classe Redimensionar
 * @version 1.0 20/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class TestRedimensionar extends TestCase {
	
	protected ImageIcon h, j, k, l, m, n, o, p;

	/**
	 * Construtor do teste
	 */
	public TestRedimensionar(String msn){
		super(msn);
	}
	/**
	 * Metodo suite que cria o suite de testes
	 * @return O suite da classe
	 */
	public static Test suite(){
	    return new TestSuite(TestRedimensionar.class);
	}
	/**
	 * Metodo setUp que inicializa as variaveis de teste
	 */
	public void setUp(){
		h = new ImageIcon("Imagens/baboon.jpg");
		k = new ImageIcon("Imagens/baboonaumentada.jpg");
		j = new ImageIcon("Imagens/mar.jpg");
		l = new ImageIcon("Imagens/marmaior.jpg");
		m = new ImageIcon("Imagens/Tubaroes.jpg");
		n = new ImageIcon("Imagens/Tubaroesmaior.jpg");
		o = new ImageIcon("Imagens/Vale.jpg");
		p = new ImageIcon("Imagens/Valemenor.jpg");
	}
	/**
	 * Metodo testRedimensionarPixels que testa o redimensionar Pixels
	 */
	public void testRedimensionarPixels(){
		try {
			Redimensionar.emPixelsBICUBIC(h,768,768);
			Redimensionar.emPixelsBICUBIC(j, 900, 578);
		} catch (RedimensionarException e) {
			System.out.println(e.getMessage());
		}
		assertEquals("1",h.getIconHeight(),k.getIconHeight());
		assertEquals("2",h.getIconWidth(),k.getIconWidth());
		assertEquals("3", j.getIconHeight(), l.getIconHeight());
		assertEquals("4", j.getIconWidth(), l.getIconWidth());
		
	}
	/**
	 * Metodo testRedimensionarPorcentagem que testa o redimensionar Porcentagem
	 */
	public void testRedimensionarPorcentagem(){
		try {
			Redimensionar.emPorcentagemBICUBIC(k,70,70);
			Redimensionar.emPixelsBICUBIC(h,538,538);
			Redimensionar.emPorcentagemBICUBIC(m, 200, 200);
			Redimensionar.emPorcentagemBICUBIC(o, 50, 50);
		} catch (RedimensionarException e) {
			System.out.println(e.getMessage());
		}
		assertEquals("1",h.getIconHeight(),k.getIconHeight());
		assertEquals("2",h.getIconWidth(),k.getIconWidth());
		assertEquals("3",m.getIconHeight(),n.getIconHeight());
		assertEquals("4",m.getIconWidth(),n.getIconWidth());
		assertEquals("5",o.getIconWidth(),p.getIconWidth());
		assertEquals("6",o.getIconWidth(),p.getIconWidth());
	}
	/**
	 * Metodo main que executa a classe
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}
