package simple.modules.operacoes.filtro;
import java.awt.Image;

import javax.swing.ImageIcon;

import simple.manipulacoes.util.Modificador;
import simple.manipulacoes.util.Normalizar;


public class Roberts {
	
	public static Image roberts(ImageIcon image) {
		Modificador mod = new Modificador(image.getImage());
		int w = mod.getLargura();
		int h = mod.getAltura();
		int[][] red = Normalizar.normalizaMatriz(aplicaRoberts(mod.getRed(),w,h),w,h,0,255);
		int[][] green = Normalizar.normalizaMatriz(aplicaRoberts(mod.getGreen(),w,h),w,h,0,255);
		int[][] blue = Normalizar.normalizaMatriz(aplicaRoberts(mod.getBlue(),w,h),w,h,0,255);
		int[][] alpha = mod.getAlpha();
		int[] pixels = mod.getPixels(red,green,blue,alpha);
		return mod.recopoeImagem(pixels,w,h);
	}
	
	private static int[][] aplicaRoberts(int[][] matriz, int w, int h) {
		int[][] mat1 = new int[h][w];
		int[][] mat2 = new int[h][w];
		for (int i = 0+1; i < h-1; i++) {
			for (int j = 0+1; j < w-1; j++) {
				mat1[i][j] = matriz[i][j] - matriz[i+1][j+1];
				mat2[i][j] = matriz[i][j+1] - matriz[i+1][j];
				matriz[i][j] = mat1[i][j] + mat2[i][j];
			}
		}
		return matriz;
	}
}
