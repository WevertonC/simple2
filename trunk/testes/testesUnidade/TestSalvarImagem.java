/*
 * TestSalvarImagem
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
package testesUnidade;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import manipularArquivo.SalvarImagem;
import exceptions.ImagemNaoExisteException;
import exceptions.NomeInvalidoException;

/**
 * Classe destinada a testar salvar uma imagem, forçando o lançamento das exceçoes: ImagemNaoExisteException e
 * NomeInvalidoException.
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class TestSalvarImagem extends TestCase{
	
	private Image image;
	private File file1, file2, file3, file4;
	
	/**
	 * Construtor de uma TestSalvarImagem
	 * @param msg
	 */
	public TestSalvarImagem(String msg) {
		super(msg);
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
	
	public static Test suite() {
		return new TestSuite(TestSalvarImagem.class);
	}
	
	/**
	 * Metodo setUp responsavel pela inicializacao das variaveis
	 */
	public void setUp(){
		
		image = Toolkit.getDefaultToolkit().getImage("andre.jpg");
		file1 = new File("naoExiste");
		file2 = new File("//erro//");
		file3 = new File("");
		file4 = new File("//erro//erro//erro");
	}
	
	
	/**
	 * Metodo testSalvarDiretorioInvalido1 que testa salvar uma imagem em um diretorio inexistente.
	 */
	public void testSalvarDiretorioInvalido1() {
		
		try {
			SalvarImagem.salvarArquivo(file1,"jpg",image);
			fail();
		} 
		catch (NomeInvalidoException e) {
			e.getMessage();
		}
		catch (ImagemNaoExisteException e) {
			e.getMessage();
		}
	}
	
	/**
	 * Metodo testSalvarDiretorioInvalido2 que testa salvar uma imagem em um diretorio inexistente.
	 */
	public void testSalvarDiretorioInvalido2() {
		
		try {
			SalvarImagem.salvarArquivo(file2,"gif",image);
			fail();
		} 
		catch (NomeInvalidoException e) {
			e.getMessage();
		}
		catch (ImagemNaoExisteException e) {
			e.getMessage();
		}
	}
	
	/**
	 * Metodo testSalvarDiretorioInvalido3 que testa salvar uma imagem em um diretorio inexistente.
	 */
	public void testSalvarDiretorioInvalido3() {
		
		try {
			SalvarImagem.salvarArquivo(file3,"jpg",image);
			fail();
		} 
		catch (NomeInvalidoException e) {
			e.getMessage();
		}
		catch (ImagemNaoExisteException e) {
			e.getMessage();
		}
	}
	
	/**
	 * Metodo testSalvarDiretorioInvalido4 que testa salvar uma imagem em um diretorio inexistente.
	 */
	public void testSalvarDiretorioInvalido5() {
		
		try {
			SalvarImagem.salvarArquivo(file4,"jpg",image);
			fail();
		} 
		catch (NomeInvalidoException e) {
			e.getMessage();
		}
		catch (ImagemNaoExisteException e) {
			e.getMessage();
		}
	}
}

