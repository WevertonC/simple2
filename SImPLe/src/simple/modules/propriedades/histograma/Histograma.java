package simple.modules.propriedades.histograma;
/*
 * Histograma
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.math.BigDecimal;

import javax.swing.ImageIcon;

import simple.manipulacoes.util.MyBufferedImage;
import simple.modules.operacoes.radiometricas.EscalaCinza;



/**
 * Classe calcula o Histograma da imagem
 * @version 1.0 20/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class Histograma {
	
	public static final int REDHISTOGRAMA = 0;
	public static final int GREENHISTOGRAMA = 1;
	public static final int BLUEHISTOGRAMA = 2;
	private static final int MAOIRCAPACIDADE = 256;
	private BufferedImage image;
	private Image imagemOriginal;
	private int[] histograma, grayScale;
	
	/**
	 * Construtor que Cria o Histograma da imagem
	 * @param imagem A imagem desejada
	 */
	public Histograma (Image imagem){
		imagemOriginal = imagem;
		image = MyBufferedImage.makeBufferedImage(imagem);
		grayScale = grayHistogram();
	}

	/**
	 * Construtor que Cria o Histograma do Nível Cinza da imagem
	 * @param imagem A imagem desejada
	 */	
	private Histograma(BufferedImage image) {
		this.image = image;
	}
	/**
	 * Metodo getHistogram que Calcula o histograma para uma determinada banda da imagem
	 * @param numBand número da banda a calcular o histograma(0 - red, 1 - green e 2 - blue)
	 * @return Histograma
	 */
	public int[] getHistogram(int numBand) {
		this.histograma = new int[MAOIRCAPACIDADE];
		if(image != null && (numBand >=0 && numBand < image.getRaster().getNumBands())) {
			Raster data = image.getData();
			for(int i=0; i<image.getWidth(); i++)
				for(int j=0; j<image.getHeight(); j++)
					histograma[data.getSample(i,j,numBand)]++;
			return histograma;
		}
		return histograma;
	}
	/**
	 * Método getGrayHistograma que Recupera o histograma do nivel de cinza
	 */
	public int[] getGrayHistograma(){
		return grayScale;
	}
	/**
	 * Metodo grayHistogram que Recupera o histograma da três cores juntas, em escala de cinza
	 * @return O histograma das três cores em conjunto.
	 */
	private int[] grayHistogram(){
		Histograma grayHistogram = new Histograma(MyBufferedImage.makeBufferedImage(
							 EscalaCinza.nivelCinza(new ImageIcon(imagemOriginal))));
		return grayHistogram.getHistogram(Histograma.REDHISTOGRAMA);
	}	
	/**
	 * Metodo getDesvioPadrao que Recupera o desvio-padrão da imagem
	 * @return O desvio-padrão
	 */
	public double getDesvioPadrao(int[] histo) {
		return formatNumber(Math.sqrt(getVariancia(histo))).doubleValue();
	}
	/**
	 * Método getDesvioPadrao que Recupera o desvio-padrão da imagem
	 * @return O desvio-padrão
	 */
	public double getDesvioPadrao(int beginValue, int finalValue, int band) {
		return Math.sqrt(getVariance(beginValue,finalValue,band));
	}
	/**
	 * Método getVariancia que Recupera a variância da imagem
	 * @return A variancia da imagem
	 */
	private double getVariance(int beginValue, int finalValue, int band) {
		double mean = getMeanValue(beginValue,finalValue,band);
		double variance = 0.0;
		double n = 0.0;
		if(histograma == null)
			this.histograma = getHistogram(band);
		for(int i=beginValue; i<=finalValue; i++) {
			variance+= histograma[i] * (Math.pow((i-mean),2));
			n += histograma[i];
		}
		return variance / n;
	}
	/**
	 * Método getMedia que Recupera a média da imagem
	 * @return A média da imagem
	 */
	public double getMedia(int[] histo) {
		return formatNumber(calcMedia(histo)).doubleValue();
	}
	/**
	 * Método calcMedia que Calcula média do Histograma
	 * @param histo O histograma da imagem
	 * @return A media da imagem
	 */
	private double calcMedia(int[] histo) {
		double totalValue = 0;
		double count = 0;
		for(int i = 0; i < histo.length; i++) {
			totalValue += histo[i]*i;
			count += histo[i];
		}
		return totalValue/count;
	}
	/**
	 * Metodo getVariancia que Recupera a variancia da imagem
	 * @return A variancia da imagem
	 */
	public double getVariancia(int[] histo) {
		return formatNumber(calcVariance(histo, getMedia(histo))).doubleValue();
	}
	/**
	 * Metodo calcVariance que calcula variancia do histograma
	 * @param histo O histograma da imagem
	 * @return A variancia da imagem
	 */
	private double calcVariance(int[] histo, double media) {
		double total = 0;
		double count =0;
		for(int i=0; i<histo.length; i++) {
			total+= histo[i] * Math.pow((i - media),2);
			count+= histo[i];
		}
		return total/count;
	}	
	/**
	 * Metodo getMaxYValue que Recupera o maior valor de Y
	 * @param histo O histograma da imagem
	 * @return O maior valor
	 */
	public int getMaxYValue(int[] histo) {
		int max = 0;
		for(int i=0; i<histo.length; i++) {
			if(histo[i] > max)
				max = histo[i];
		}
		return max;
	}	
	/**
	 * Metodo formatNumber que formata a média da imagem em BigDecimal
	 * @param media A media da imagem
	 * @return A media em bigDecimal
	 */
	private BigDecimal formatNumber(double media) {
		BigDecimal bd;
		try {
			bd = new BigDecimal(media);
		} catch (NumberFormatException e) {
			return null;
		}
		return bd.setScale(2,BigDecimal.ROUND_HALF_DOWN);
	}
	/**
	 * Recupera a media de valor de cinza um intervalo de valores
	 * do histograma
	 * @param beginValue valor inicial do intervalo
	 * @param finalValue valor final do intervalo
	 * @return media de valor de cinza um intervalo de valores
	 */
	public double getMeanValue(int beginValue, int finalValue, int band) {
		double somatorio = 0.0;
		double pesos = 0.0;
		if(histograma == null)
			this.histograma = getHistogram(band);
		for(int i = beginValue; i <= finalValue; i++) {
			somatorio+= i*histograma[i];
			pesos += histograma[i];
		}
		return somatorio / pesos;
	}
}