package simple.modules.operacoes.morfologia;

import java.awt.Image;
import javax.swing.ImageIcon;

import simple.manipulacoes.util.Modificador;

public class Dilatacao {
	
	public static Image dilatacao4(ImageIcon image) {
		Modificador mod = new Modificador(image.getImage());
		int h = mod.getAltura();
		int w = mod.getLargura();
		int[][] red = aplicaDilatacao4(mod.getRed(),w,h);
		int[][] green = aplicaDilatacao4(mod.getGreen(),w,h);
		int[][] blue = aplicaDilatacao4(mod.getBlue(),w,h);
		int[][] alpha = mod.getAlpha();
		int[] pixels = mod.getPixels(red,green,blue,alpha);
		return mod.recopoeImagem(pixels,w,h);
	}
	
	public static Image dilatacao8(ImageIcon image) {
		Modificador mod = new Modificador(image.getImage());
		int h = mod.getAltura();
		int w = mod.getLargura();
		int[][] red = aplicaDilatacao8(mod.getRed(),w,h);
		int[][] green = aplicaDilatacao8(mod.getGreen(),w,h);
		int[][] blue = aplicaDilatacao8(mod.getBlue(),w,h);
		int[][] alpha = mod.getAlpha();
		int[] pixels = mod.getPixels(red,green,blue,alpha);
		return mod.recopoeImagem(pixels,w,h);
	}
	
	//aumento de buracos na imagem
	private static int[][] aplicaDilatacao4(int[][] matriz, int w, int h) {
		int[][] novaMatriz = new int[h][w];
		for (int i = 0+1; i < h-1; i++) {
			for (int j = 0+1; j < w-1; j++) {
				int max = Math.max(matriz[i-1][j],matriz[i][j-1]);
				max = Math.max(max,matriz[i][j]);
				max = Math.max(max,matriz[i][j+1]);
				max = Math.max(max,matriz[i+1][j]);
				novaMatriz[i][j] = max;
			}
		}
		return novaMatriz;
	}
	
	//aumento de buracos na imagem
	private static int[][] aplicaDilatacao8(int[][] matriz, int w, int h) {
		int[][] novaMatriz = new int[h][w];
		for (int i = 0+1; i < h-1; i++) {
			for (int j = 0+1; j < w-1; j++) {
				int max = Math.max(matriz[i-1][j-1],matriz[i-1][j]);
				max = Math.max(max,matriz[i-1][j+1]);
				max = Math.max(max,matriz[i][j-1]);
				max = Math.max(max,matriz[i][j]);
				max = Math.max(max,matriz[i][j+1]);
				max = Math.max(max,matriz[i+1][j-1]);
				max = Math.max(max,matriz[i+1][j]);
				max = Math.max(max,matriz[i+1][j+1]);
				novaMatriz[i][j] = max;
			}
		}
		return novaMatriz;
	}
}
