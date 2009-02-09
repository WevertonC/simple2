package simple.modules.propriedades.requantizar;

/*
 * Classe Requantizador
 * 
 * version 1.0
 * 
 * Data 09/11/2005
 * 
 * CopyRight FePDI all rigths reserved 
 */
import java.awt.Image;
import java.awt.image.MemoryImageSource;

import javax.swing.JLabel;

import simple.manipulacoes.util.Modificador;

/**
 * Classe que cria um requantizador, que e responavel por requantizar uma imagem
 * fornecida
 * 
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class Requantizador {

	private int largura, altura;
	private int[][] red, green, blue, alpha;
	private Modificador mod;

	/**
	 * Construtor da classe requantizador que requantiza uma imagem
	 * 
	 * @param i
	 *            A imagem a ser requantizada
	 */
	public Requantizador(Image i) {
		mod = new Modificador(i);
		alpha = mod.getAlpha();
		red = mod.getRed();
		green = mod.getGreen();
		blue = mod.getBlue();
		altura = mod.getAltura();
		largura = mod.getLargura();
	}

	/**
	 * Metodo que requantiza uma imagem atraves da operacao AND
	 * 
	 * @param valor
	 *            O numero de tons da quantizacao
	 * @return A imagem requantizada
	 */
	public Image maskAND(int valor) {
		for (int x = 0; x < altura; x++) {
			for (int y = 0; y < largura; y++) {
				alpha[x][y] = alpha[x][y] & valor;
				red[x][y] = red[x][y] & valor;
				green[x][y] = green[x][y] & valor;
				blue[x][y] = blue[x][y] & valor;
			}
		}
		JLabel label = new JLabel();
		int[] pixels = mod.getPixels(red, green, blue, alpha);
		Image i = label.createImage(new MemoryImageSource(largura, altura, pixels, 0, largura));
		return i;
	}

	/**
	 * Metodo que requantiza uma imagem atraves da operacao OR
	 * 
	 * @param valor
	 *            O numero de tons da quantizacao
	 * @return A imagem requantizada
	 */
	public Image maskOR(int valor) {
		for (int x = 0; x < altura; x++) {
			for (int y = 0; y < largura; y++) {
				alpha[x][y] = alpha[x][y] | valor;
				red[x][y] = red[x][y] | valor;
				green[x][y] = green[x][y] | valor;
				blue[x][y] = blue[x][y] | valor;
			}
		}
		JLabel label = new JLabel();
		int[] pixels = mod.getPixels(red, green, blue, alpha);
		Image i = label.createImage(new MemoryImageSource(largura, altura, pixels, 0, largura));
		return i;
	}

	/**
	 * Metodo que requantiza uma imagem atraves do algoritmo IGS
	 * 
	 * @return A imagem requantizada
	 */
	public Image maskIGS() {
		int[][] red = mod.getRed();
		int[][] green = mod.getGreen();
		int[][] blue = mod.getBlue();

		final int f0Mask = 0xf0;
		final int fMask = 0xf;

		int sum = 0;
		for (int x = 0; x < mod.getAltura(); x++) {
			for (int y = 0; y < mod.getLargura(); y++) {

				int avg = (red[x][y] + blue[x][y] + green[x][y]) / 3;
				if ((avg & f0Mask) != fMask) {
					sum = (sum & fMask);
				}
				sum += avg;
				int gray = sum & f0Mask;

				red[x][y] = gray;
				green[x][y] = gray;
				blue[x][y] = gray;
			}
		}
		JLabel label = new JLabel();
		int[] pixels = mod.getPixels(red, green, blue, alpha);
		Image i = label.createImage(new MemoryImageSource(largura, altura, pixels, 0, largura));
		return i;
	}

}
