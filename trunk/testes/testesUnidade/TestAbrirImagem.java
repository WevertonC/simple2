/*
 * TestAbrirImagem
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
package testesUnidade;

import java.io.File;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import manipularArquivo.AbrirImagem;
import exceptions.ImagemNaoExisteException;
import exceptions.NomeInvalidoException;

/**
 * Classe destinada a testar abrir uma imagem, forçando o lançamento das exceçoes: ImagemNaoExisteException e
 * NomeInvalidoException.
 * 
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class TestAbrirImagem extends TestCase {

	private File file1, file2, file3;
	
	/**
	 * Construtor de uma TestAbrirImagem
	 * @param msg
	 */
	public TestAbrirImagem(String msg) {
		super(msg);
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
	
	public static Test suite() {
		return new TestSuite(TestAbrirImagem.class);
	}
	
	/**
	 * Metodo setUp responsavel pela inicializacao das variaveis
	 */
	public void setUp() {
		
		file1 = new File("imagemNaoExistente");
		file2 = new File("");
		file3 = new File("andre.jpg");
		
	}
	
	/**
	 * Metodo testAbrirImagemInvalida que testa abrir uma imagem inexistente.
	 */
	public void testAbrirImagemInvalida() {
		try {
			AbrirImagem.abrirArquivo(file1);
			fail();
		}
		catch (ImagemNaoExisteException e) {
			e.getMessage();
		}
		catch (NomeInvalidoException e) {
			e.getMessage();
		}
	}
	
	/**
	 * Metodo testAbrirNomeInvalido que testa abrir uma imagem com um nome invalido.
	 */
	public void testAbrirImagemNomeInvalido() {
		try {
			AbrirImagem.abrirArquivo(file2);
			fail();
		}
		catch (ImagemNaoExisteException e) {
			e.getMessage();
		}
		catch (NomeInvalidoException e) {
			e.getMessage();
		}
	}
	
	/**
	 * Metodo testAbrirNomeInvalido que testa abrir uma imagem valida.
	 */
	public void testAbrirImagemValida() {
		try {
			AbrirImagem.abrirArquivo(file3);
		}
		catch (ImagemNaoExisteException e) {
			e.getMessage();
			fail();
		}
		catch (NomeInvalidoException e) {
			fail();
		}
	}

}

