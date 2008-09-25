package simple.manipulacoes.util;
/*
 * Classe Modificador
 * 
 * version 1.0
 * 
 * Data 09/11/2005
 * 
 * CopyRight FePDI all rigths reserved 
 */
import java.awt.Image;
import java.awt.Label;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

/**
 * Classe que meche com os canais R, G e B da imagem
 * @version 1.0 20/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */ 

public class Modificador {
	
	private int[][] red, green, blue, alpha;
	private int[] pixels;
	private int largura;
	private int altura;	
	
	/**
	 * Construtor da classe Modificador
	 * @param imagem A imagem a ser modificada
	 */
	public Modificador(Image imagem){
		this.largura =  imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		this.pixels = new int[largura*altura];
		PixelGrabber pg = new PixelGrabber(imagem,0,0,largura,altura,pixels,0,largura);
		try{
			pg.grabPixels();
		}catch(Exception e){			
		}		
		red   = new int[altura][largura];
		green = new int[altura][largura];
		blue  = new int[altura][largura]; 
		alpha = new int[altura][largura];		
		int k = 0;
		for (int i = 0; i < altura; i++) 
			for (int j = 0; j < largura; j++) {
				red	 [i][j] = ((pixels[k] & 0xff0000) >> 16);
				green[i][j] = ((pixels[k] & 0x00ff00) >> 8);
				blue [i][j] = (pixels[k] & 0xff);
				alpha[i][j] = ((pixels[k] & 0xff000000) >> 24);
				k++;
			}
	}	
	
	/**
	 * Recupera se uma imagem está sobre o nível de cinza
	 * @param r A componente RED
	 * @param g A componente GREEN
	 * @param b A componente BLUE
	 * @return
	 */
	public boolean ehCinza(int r, int g, int b){
		return (r == g) && (g == b);
	}
	
	/**
	 * Recupera a media dos vizinhos
	 * @return Uma imagem formada por essa média
	 */
	public BufferedImage mediaVizinhos(){
		int mediaR = 0;
		int mediaG = 0;
		int mediaB = 0;
		int cont = 0;
		for(int linha = 0; linha < altura; linha++){
			for(int coluna = 0; coluna < largura; coluna++){
				if(ehCinza(red[linha][coluna],green[linha][coluna],blue[linha][coluna])){
					if(linha -2 > 0){
						mediaR += red[linha-2][coluna];
						mediaG += green[linha-2][coluna];
						mediaB += blue[linha-2][coluna];
						cont++;
					}
					if(coluna +2 < largura-1){
						mediaR += red[linha][coluna+2];
						mediaG += green[linha][coluna+2];
						mediaB += blue[linha][coluna+2];
						cont++;
					}
					if(linha +2 < altura -1){
						mediaR += red[linha+2][coluna];
						mediaG += green[linha+2][coluna];
						mediaB += blue[linha+2][coluna];
						cont++;
					}
					if(coluna -2 > 0){
						mediaR += red[linha][coluna-2];
						mediaG += green[linha][coluna-2];
						mediaB += blue[linha][coluna-2];
						cont++;
					}
					red[linha][coluna] = mediaR/cont;
					green[linha][coluna] = mediaG/cont;
					blue[linha][coluna] = mediaB/cont;
				}
				cont = 0;
				mediaR = 0;
				mediaG = 0;
				mediaB = 0;				
			}
		}		
		int[] pixels = getPixels(red,green,blue,alpha);
		BufferedImage finalImage = new BufferedImage(largura,altura, BufferedImage.TYPE_INT_RGB);
		for (int k = 0; k < pixels.length; k++) finalImage.setRGB(k % largura, k / largura, pixels[k]);
		return finalImage;
	}
	
	/**
	 * Recupera uma matriz do canal RED
	 * @return A matriz do canal RED
	 */
	public int[][] getRed() {
		return red;
	}

	/**
	 * Recupera uma matriz do canal GREEN
	 * @return A matriz do canal GREEN
	 */
	public int[][] getGreen() {
		return green;
	}

	/**
	 * Recupera uma matriz do canal BLUE
	 * @return A matriz do canal BLUE
	 */
	public int[][] getBlue(){
		return blue;
	}

	/**
	 * Recupera uma matriz do canal alpha
	 * @return A matriz do canal alpha
	 */
	public int[][] getAlpha() {
		return alpha;
	}
	
	/**
	 * Recupera a altura da imagem utilizada
	 * @return A altura
	 */
	public int getAltura(){
		return altura;
	}

	/**
	 * Recupera a largura da imagem utilizada
	 * @return A largura
	 */
	public int getLargura(){
		return largura;
	}
	
	/**
	 * Metodo que junta os canais (matrizes)
	 * @param red O canal red
	 * @param green O canal green
	 * @param blue O canal blue
	 * @param alpha O canal alpha
	 * @return Um vetor com os canais juntos
	 */
	public int[] getPixels(int[][] red, int[][] green, int[][] blue, int[][] alpha) {
		int k = 0;
		int[] pixelsVet = new int[largura*altura];
		for (int i = 0; i < altura; i++)
			for (int j = 0; j < largura; j++)
				pixelsVet[k++] = (((red[i][j]) << 16) | ((green[i][j]) << 8) | (blue[i][j]) | ((alpha[i][j]) << 24));
		return pixelsVet;
	}
	
	/**
	 * Metodo que junta os canais (vetores)
	 * @param red O canal red
	 * @param green O canal green
	 * @param blue O canal blue
	 * @param alpha O canal alpha
	 * @return Um vetor com os canais juntos
	 */
	public int[] getPixels(int[] red, int[] green, int[] blue, int [] alpha) {
		int[] pixelsVet = new int[(largura*altura)/4];
		for (int i = 0; i < (largura*altura)/4; i++)
			pixelsVet[i] = (((red[i]) << 16) | ((green[i]) << 8) | (blue[i]) | ((alpha[i]) << 24));
		return pixelsVet; 
	}
	
	public Image recopoeImagem(int[] pixels, int w, int h) {
		return new Label().createImage(new MemoryImageSource(w,h,pixels,0,w));
	}
}
