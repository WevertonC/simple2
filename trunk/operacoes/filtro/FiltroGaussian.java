package filtro;

/*
 * FiltroGaussian
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

/**
 * Classe responsavel por executar as operacoes do filtro gaussin.
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class FiltroGaussian extends FiltroAbstrato {
	@SuppressWarnings("unused")
	private double mean[], desvioPadrao[];
	@SuppressWarnings("unused")
	private boolean calculouMediaEVariancia = false;
	private double desvio; 
	@SuppressWarnings("unused")
	private BufferedImage image;
	
	/**
	 * Construtor de um filtro gaussian
	 * @param filterOperation operacao a ser realizada
	 * @param image imagem a ser realizada a operacao
	 */
	public FiltroGaussian(ImagemFiltroOperacao filterOperation, BufferedImage image) {
		super(filterOperation, image);
		this.image = image;
		desvio = Double.parseDouble((String)filterOperation.getParameter("desvio"));
	}
	
	/**
	 * Metodo que retorna valor da mascara
	 */
	protected int getMaskValue(int i, int j, int band) {
		return normalizeGaussianValue(gaussian(i,j,desvio));
	}

	/**
	 * Metodo que normaliza o valor retornado da funcao gaussiana (entre 1..2)
	 * para a faixa de 0..255;
	 * @param value valor a ser normalizado
	 * @return valor normalizado
	 */
	private int normalizeGaussianValue(double value) {
		return (int) ((255.0 * (value)) / (2.0 * desvio));
	}
	
	/**
	 * Metodo que calcula o desvio padrao
	 * @param image2 imagem a ser calculada o desvio padrao
	 * @return desvio padrao da imagem
	 */
	@SuppressWarnings("unused")
	private double[] calculaDesvioPadrao(BufferedImage image2) {
		Raster matrix = image2.getData();
		double count = 0.0;
		double[] sum = new double[image2.getData().getNumBands()];
		for(int i = 0; i < matrix.getWidth(); i++) {
			for(int j = 0; j < matrix.getHeight(); j++){
				int[] pixel = matrix.getPixel(i,j,new int[]{0,0,0});
				count++;
				for(int k = 0; k < pixel.length; k++)
					sum[k] += Math.pow((pixel[k] - mean[k]),2);
			}
		}
		sum[0] = Math.sqrt((sum[0] / count));
		sum[1] = Math.sqrt((sum[1] / count));
		sum[2] = Math.sqrt((sum[2] / count));
		return sum;
	}
	
	/**
	 * Metodo que calcula a media da imagem
	 * @param image imagem a ser calcula a media
	 * @return media da imagem
	 */
	@SuppressWarnings("unused")
	private double[] calculaMedia(BufferedImage image) {
		Raster matrix = image.getData();
		double count = 0.0;
		double[] sum = new double[image.getData().getNumBands()];
		
		for(int i=0; i<matrix.getWidth(); i++) {
			for(int j=0; j<matrix.getHeight(); j++){
				int[] pixel = matrix.getPixel(i,j,new int[]{0,0,0});
				count++;
				for(int k=0; k< pixel.length; k++)
					sum[k] += pixel[k];
			}
		}
		sum[0] = sum[0] / count;
		sum[1] = sum[1] / count;
		sum[2] = sum[2] / count;
		return sum;
	}
	
	/**
	 * Metodo que aplicao o filtro gaussian
	 * @param i
	 * @param j
	 * @param desvioPadrao
	 * @return
	 */
	public double gaussian(int i, int j, double desvioPadrao) {
		double fator1 = 2.0 * Math.pow(desvioPadrao,2);
		double fator2 = (Math.pow(i,2) + Math.pow(j,2)) / fator1;
		double expoente = -fator2;
		double exponencial  = Math.exp(expoente);
		double denominador = desvioPadrao * (Math.sqrt(2.0 * Math.PI));
		double fator3=0;
		if(denominador != 0)
			fator3 = 1 / denominador;
		return fator3 * exponencial;
	}

	/**
	 * Método que normaliza o valor passado como parâmetro
	 * @return valor normalizado
	 */
	protected int normalizePixelValue(double unitResult) {
		if(unitResult < 0) return 0;
		if(unitResult > 255) return 0;
		return (int) unitResult;
	}
}
