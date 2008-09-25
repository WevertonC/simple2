package simple.testes;
/*
 * TestAnd
 * 
 * @version 1.0
 * 
 * Date: 03/10/05
 * 
 * Copyright FEDPI all rights reserved
 */
import simple.modules.propriedades.coordenadas.AlgoritmosImagem;
import junit.framework.TestCase;
/**
 * Classe que testa a classe Algoritmos de imagem
 * @version 1.0 03/10/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class TestCoordenadas extends TestCase{
	
	private int valor, valor2, valor3, valor4, valor5;
	private int[] array, array2, array3, array4, array5;
	
	
	public void setUp(){
		valor = 16777215;
		valor2 = 0;
		valor3 = 16711680;
		valor4 = 65280;
		valor5 = 255;
		array = new int[3];//Branco
		array[0] = 255;
		array[1] = 255;
		array[2] = 255;
		array2 = new int[3];//Preto
		array2[0] = 0;
		array2[1] = 0;
		array2[2] = 0;
		array3 = new int[3];//Vermelho
		array3[0] = 255;
		array3[1] = 0;
		array3[2] = 0;
		array4 = new int[3];//Verde
		array4[0] = 0;
		array4[1] = 255;
		array4[2] = 0;
		array5 = new int[3];//Azul
		array5[0] = 0;
		array5[1] = 0;
		array5[2] = 255;
		
	}
	
	public void testGetRGBPixels(){
		assertEquals("1", AlgoritmosImagem.getRGBPixel(array), valor);
		assertEquals("2", AlgoritmosImagem.getRGBPixel(array2), valor2);
		assertEquals("3", AlgoritmosImagem.getRGBPixel(array3), valor3);
		assertEquals("4", AlgoritmosImagem.getRGBPixel(array4), valor4);
		assertEquals("5", AlgoritmosImagem.getRGBPixel(array5), valor5);
	}
	
	public void testGetRGBComponentes(){
		assertEquals("1", AlgoritmosImagem.getRGBComponents(array, valor), "255 255 255");
		assertEquals("2", AlgoritmosImagem.getRGBComponents(array2, valor2), "0 0 0");
		assertEquals("3", AlgoritmosImagem.getRGBComponents(array3, valor3), "255 0 0");
		assertEquals("4", AlgoritmosImagem.getRGBComponents(array4, valor4), "0 255 0");
		assertEquals("5", AlgoritmosImagem.getRGBComponents(array5, valor5), "0 0 255");
	}

	
	
}
