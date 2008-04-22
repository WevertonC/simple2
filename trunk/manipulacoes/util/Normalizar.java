package util;

public class Normalizar {
	
	public static int[][] normalizaMatriz(int[][] matriz, int w, int h, int minY, int maxY) {
		//int[][] normalizada = new int[h][w];
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (matriz[i][j] > max)
					max = matriz[i][j];
				if (matriz[i][j] < min)
					min = matriz[i][j];
			}
		}
		//normaliza os valores da matriz valores para a escala minY a maxY
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				matriz[i][j] = ((maxY - minY) * ((matriz[i][j] - min)) / (max - min)) + minY;
			}
		}
		//System.out.println(min + " " + max);
		return matriz;
	}

}
