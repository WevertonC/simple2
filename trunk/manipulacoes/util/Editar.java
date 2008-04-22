package util;
/*
 * Editar
 * 
 * @version 1.0
 * 
 * Date: 22/11/05
 * 
 * Copyright FEDPI all rights reserved
 */

import janelas.SImPLe;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.RenderedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;





/**
* Classe responsavel por tratar as funcoes de editar a imagem, recortar, copiar e colar
* 
* @author Andre Cavalcante Hora
* @author Eduardo Santiago Moura
* @author Paulo de Tarso Firmino Junior
* @author Vinicius de Araujo Porto
* @author Yuska Paola Aguiar
*/
public class Editar {
	
	private int x1, y1, x2, y2;
	private BufferedImage imagem, copiada;
	private int[][] matrizOriginal;
	private SImPLe fepdi;
	
	/**
	 * Construtor da classe
	 * @param fepdi
	 * @param imagem imagem a ser editada
	 * @param x1 coordenada x do primeiro click do usuario
	 * @param y1 coordenada y do primeiro click do usuario
	 * @param x2 coordenada x do ultimo click do usuario
	 * @param y2 coordenada y do ultimo click do usuario
	 */
	public Editar(SImPLe fepdi, Image imagem, int x1, int y1, int x2, int y2, int px, int py) {
		this.fepdi = fepdi;
		this.imagem = MyBufferedImage.makeBufferedImage(imagem);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		carregaMatrizPixels();
		
	}
	
	/**
	 * Metodo que verifica os limites da imagem ou seja as coordenadas limites da imagem, atribuindo um novo
	 * valor as coordenadas estejam fora dos limites.
	 */
	private void verificaCoordenadas() { 
		if (x1 <= 0) x1 = 1;
		if (y1 <= 0) y1 = 1;
		if (x2 <= 0) x2 = 1;
		if (y2 <= 0) y2 = 1;
		if (x1 >= imagem.getWidth(null)) x1 = imagem.getWidth(null);
		if (y1 >= imagem.getHeight(null)) y1 = imagem.getHeight(null);
		if (x2 >= imagem.getWidth(null)) x2 = imagem.getWidth(null);
		if (y2 >= imagem.getHeight(null)) y2 = imagem.getHeight(null);
	}
	
	/**
	 * Metodo que converte a matriz de pixel em um vetor de pixel
	 * @param mat matriz a ser convertida
	 * @param largura largura da matriz
	 * @param altura altuta da matriz
	 * @return vetor de com pixels da matriz, com tamanho largura*altura
	 */
	private int[] converteMatrizVetor(int[][] mat, int largura, int altura) {
		int[] vetorPixels = new int[largura*altura];
		int cont = 0;
		for (int i = 0; i < altura; i++) 
			for (int j = 0; j < largura; j++) 
				vetorPixels[cont++] = matrizOriginal[i][j];
		return vetorPixels;
	}
	
	/**
	 * Metodo que carrega a matriz de pixel da imagem a ser editada
	 * @return matriz de pixel da imagem
	 */
	private int[][] carregaMatrizPixels() {
		ImageIcon original = new ImageIcon(imagem);
		int [] pixelsOri = new int[original.getIconWidth()*original.getIconHeight()];
		PixelGrabber pg1 = new PixelGrabber(original.getImage(),0,0,original.getIconWidth(),original.getIconHeight(),pixelsOri,0,original.getIconWidth());
		try {
			pg1.grabPixels();
		} catch (InterruptedException e) {}
		matrizOriginal = new int[original.getIconHeight()][original.getIconWidth()];
		int cont = 0;
		for (int i = 0; i < original.getIconHeight(); i++) 
			for (int j = 0; j < original.getIconWidth(); j++) 
				matrizOriginal[i][j] = pixelsOri[cont++];
		return matrizOriginal;
	}
	
	/**
	 * Metodo que recorta a area selecionada da imagem, pitando de branco tal area
	 * @return nova imagem com a area recortada
	 */
	public Image recortaImagem() {
		for (int i = Math.min(y1,y2); i < Math.min(y1,y2)+copiada.getHeight(); i++) 
			for (int j = Math.min(x1,x2); j < Math.min(x1,x2)+copiada.getWidth(); j++)
				matrizOriginal[i][j] = -1; //-1 corresponde a branco
		int[] pixelsOri = converteMatrizVetor(matrizOriginal,imagem.getWidth(),imagem.getHeight());
		
		JLabel label = new JLabel();
		Image novaImagem = label.createImage(new MemoryImageSource(imagem.getWidth(),imagem.getHeight(),
													pixelsOri,0,imagem.getWidth()));
		return MyBufferedImage.makeBufferedImage(novaImagem);
	}
	
	/**
	 * Metodo responsavel por selecionar a area da imagem a ser recortada
	 * @return true caso a area selecionada seja valida, false caso contrario
	 */
	public boolean recortar() {
		getScrollPane().selecionadoTrue();
		verificaCoordenadas();
		if (!ladosZero()) {
			copiada = imagem.getSubimage(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x1-x2),Math.abs(y1-y2));
			getScrollPane().transferirImagemCopiada(copiada);
			getScrollPane().transferirImagemOriginal((RenderedImage)recortaImagem());
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo reposavel por selecionar a area da imagem a ser copiada
	 * @return true caso a area selecionada seja valida, false caso contrario
	 */
	public boolean copiar() {
		getScrollPane().selecionadoTrue();
		verificaCoordenadas();
		(getScrollPane()).repaint();
		if (!ladosZero()) {
			copiada = imagem.getSubimage(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x1-x2),Math.abs(y1-y2));
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo que retorna a imagem copiada ou recortada
	 * @return
	 */
	public Image colar() {
		return copiada;
	}
	
	/**
	 * Metodo que verifica se a area selecionada pelo usuario e invalida ou seja se a area e nula
	 */
	private boolean ladosZero() {
		if ((Math.abs(x1-x2) == 0) || (Math.abs(y1-y2) == 0)) return true;
		return false;
	}
	
	/**
	 * Metodo que retorna o scrollPane 
	 * @return scrollPane
	 */
	private MyScrollPane getScrollPane() {
		return fepdi.scrollPane();
	}
}
