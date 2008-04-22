package morfologia;

public class Morfologicos {
	
	public int[][] erosao4NVezes(int[][] matriz, int w, int h, int n) {
		for (int i = 0; i < n; i++) {
			matriz = erosao4(matriz,w,h);
		}
		return matriz;
		
	}
	
	public int[][] erosao8NVezes(int[][] matriz, int w, int h, int n) {
		for (int i = 0; i < n; i++) {
			matriz = erosao8(matriz,w,h);
		}
		return matriz;
		
	}
	
	//fechamento de buracos na imagem
	public int[][] erosao4(int[][] matriz, int w, int h) {
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
	public int[][] erosao8(int[][] matriz, int w, int h) {
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
	
	public int[][] dilatacao4NVezes(int[][] matriz, int w, int h, int n) {
		for (int i = 0; i < n; i++) {
			matriz = dilatacao4(matriz,w,h);
		}
		return matriz;
		
	}
	
	public int[][] dilatacao8NVezes(int[][] matriz, int w, int h, int n) {
		for (int i = 0; i < n; i++) {
			matriz = dilatacao8(matriz,w,h);
		}
		return matriz;
		
	}
	
	//aumento de buracos na imagem
	public int[][] dilatacao4(int[][] matriz, int w, int h) {
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
	public int[][] dilatacao8(int[][] matriz, int w, int h) {
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
	
	//dilatação - erosão
	public int[][] fechamento(int[][] matriz, int w, int h) {
		return erosao4(dilatacao4(matriz,w,h),w,h);
		
	}
	
	//erosão - dilatação
	public int[][] abertura(int[][] matriz, int w, int h) {
		return dilatacao4(erosao4(matriz,w,h),w,h);
		
	}

}
