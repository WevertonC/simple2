/*
 * TestTudo
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

/**
 * Classe que testa todos os Testes
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class TestTudo extends TestCase{
	
	/**
	 * Metodo suite que executa todas as classes de Teste
	 * @return
	 */
	public static Test suite(){
		TestSuite suite = new TestSuite("Test Tudo");
		suite.addTestSuite(TestAbrirImagem.class);
		suite.addTestSuite(TestAdicao.class);
		suite.addTestSuite(TestAnd.class);
		suite.addTestSuite(TestDesfazerRefazer.class);
		suite.addTestSuite(TestDivisao.class);
		suite.addTestSuite(TestEscalaDeCinza.class);
		suite.addTestSuite(TestHistograma.class);
		suite.addTestSuite(TestMultiplicacao.class);
		suite.addTestSuite(TestRedimensionar.class);
		suite.addTestSuite(TestNAnd.class);
		suite.addTestSuite(TestNOr.class);
		suite.addTestSuite(TestNot.class);
		suite.addTestSuite(TestOr.class);		
		suite.addTestSuite(TestSalvarImagem.class);
		suite.addTestSuite(TestSubtracao.class);
		suite.addTestSuite(TestXOr.class);
		suite.addTestSuite(TestRequantizar.class);
		suite.addTestSuite(TestCoordenadas.class);
		//suite.addTestSuite(TestZoom.class);
		return suite;
	}	
}