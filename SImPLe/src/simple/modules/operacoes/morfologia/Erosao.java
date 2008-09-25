package simple.modules.operacoes.morfologia;

import java.awt.Image;
import javax.swing.ImageIcon;

import simple.manipulacoes.util.Modificador;

public class Erosao {
	
	public static Image erosao4(ImageIcon image) {
		Modificador mod = new Modificador(image.getImage());
		int h = mod.getAltura();
		int w = mod.getLargura();
		int[][] red = aplicaErosao4(mod.getRed(),w,h);
		int[][] green = aplicaErosao4(mod.getGreen(),w,h);
		int[][] blue = aplicaErosao4(mod.getBlue(),w,h);
		int[][] alpha = mod.getAlpha();
		int[] pixels = mod.getPixels(red,green,blue,alpha);
		return mod.recopoeImagem(pixels,w,h);
	}
	
	public static Image erosao8(ImageIcon image) {
		Modificador mod = new Modificador(image.getImage());
		int h = mod.getAltura();
		int w = mod.getLargura();
		int[][] red = aplicaErosao8(mod.getRed(),w,h);
		int[][] green = aplicaErosao8(mod.getGreen(),w,h);
		int[][] blue = aplicaErosao8(mod.getBlue(),w,h);
		int[][] alpha = mod.getAlpha();
		int[] pixels = mod.getPixels(red,green,blue,alpha);
		return mod.recopoeImagem(pixels,w,h);
	}
	
	//fechamentos buracos na imagem
	private static int[][] aplicaErosao4(int[][] matriz, int w, int h) {
		int[][] novaMatriz = new int[h][w];
		for (int i = 0+1; i < h-1; i++) {
			for (int j = 0+1; j < w-1; j++) {
				int min = Math.min(matriz[i-1][j],matriz[i][j-1]);
				min = Math.min(min,matriz[i][j]);
				min = Math.min(min,matriz[i][j+1]);
				min = Math.min(min,matriz[i+1][j]);
				novaMatriz[i][j] = min;
			}
		}
		return novaMatriz;
	}
	
	//fechamentos buracos na imagem
	private static int[][] aplicaErosao8(int[][] matriz, int w, int h) {
		int[][] novaMatriz = new int[h][w];
		for (int i = 0+1; i < h-1; i++) {
			for (int j = 0+1; j < w-1; j++) {
				int min = Math.min(matriz[i-1][j-1],matriz[i-1][j]);
				min = Math.min(min,matriz[i-1][j+1]);
				min = Math.min(min,matriz[i][j-1]);
				min = Math.min(min,matriz[i][j]);
				min = Math.min(min,matriz[i][j+1]);
				min = Math.min(min,matriz[i+1][j-1]);
				min = Math.min(min,matriz[i+1][j]);
				min = Math.min(min,matriz[i+1][j+1]);
				novaMatriz[i][j] = min;
			}
		}
		return novaMatriz;
	}
}
