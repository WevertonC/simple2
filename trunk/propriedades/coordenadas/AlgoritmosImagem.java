package coordenadas;
/*
 * Algoritmos de Imagem
 * 
 * @version 1.0
 * 
 * Date: 03/11/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.image.BufferedImage;


/**
 * Classe que contém alguns algoritmos sobre a imagem
 * 
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 *  
 */
public class AlgoritmosImagem {
	
	public static final boolean LINHA = true;
	
	/**
	 * Calacula perfil de uma linha (ou uma parte da linha) de uma imagem
	 * @param image A imagem a ser utilizada
	 * @param linha A linha na qual sera calculado perfil
	 * @param colunaInicial A coluna inicial do perfil a ser calculado
	 * @param tamanho O tamanho das colunas
	 * @return Uma matriz com os valores de X e Y
	 */
	public static int[][] perfilLinha(BufferedImage image, int linha, int colunaInicial, int tamanho){
		int[][] perfil = new int[3][tamanho];
		int pixel;
		for (int x = 0; x < tamanho; x++) {
			pixel = image.getRGB(x,linha);
			perfil[0][x] = (pixel >> 16) & 0xff;;
			perfil[1][x] = (pixel >> 8) & 0xff;
			perfil[2][x] = (pixel) & 0xff;
		}
		return perfil;
	}
	
	/**
	 * Calacula perfil de uma coluna (ou uma parte da coluna) de uma imagem
	 * @param image A imagem a ser utilizada
	 * @param coluna A coluna na qual sera calculado perfil
	 * @param linhaInicial A linha inicial do perfil a ser calculado
	 * @param tamanho O tamanho das linhas
	 * @return Uma matriz com os valores de X e Y
	 */
	public static int[][] perfilColuna(BufferedImage image, int coluna, int linhaInicial, int tamanho){
		int[][] perfil = new int[3][tamanho];
		int pixel;
		for (int y = 0; y < tamanho; y++) {
			pixel = image.getRGB(coluna,y);
			perfil[0][y] = (pixel >> 16) & 0xff;;
			perfil[1][y] = (pixel >> 8) & 0xff;
			perfil[2][y] = (pixel) & 0xff;
		}
		return perfil;
	}
	
	/**
	 * Recupera um inteiro a partir do array de RGB
	 * @param component Um vetor com o R, G, B
	 * @return O pixel que representa o RGB
	 */
	public static int getRGBPixel(int[] component) {
		return (component[0] << 16) | (component[1] << 8) | (component[2]);
	}
	
	/**
	 * Recupera um vetor com os RGB
	 * @param component O vetor que sera preenchido
	 * @param pixel O pixel da imagem
	 * @return Uma String com os valores de R G B
	 */
	public static String getRGBComponents(int[] component, int pixel){
		component[0] = (pixel >> 16) & 0xff;
		component[1] = (pixel >>  8) & 0xff;
		component[2] = (pixel      ) & 0xff;
		String nome = "";
		for (int i = 0; i < component.length; i++) {
			nome += component[i] + " ";
		}
		return nome.trim();
	}

}