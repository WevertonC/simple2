package segmentacao;

/*
 * BasicoThreshold
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
import histograma.Histograma;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.Map;

/**
 * 
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class BasicoThreshold implements ThresholdIF {

	/**
	 * Construtor de um BasicoThreshold
	 */
	public BasicoThreshold() {
		super();
	}

	/**
	 * Metodo que detecta o ponto inical da imagem
	 * @param image imagem a ser realizada o processamento
	 * @param mapa de parametros
	 * 
	 */
	@SuppressWarnings("unchecked")
	public double detectThreshold(BufferedImage image, Map params) {
		Histograma histo = new Histograma(image);
		String initialThresholdStr = (String) params.get("initialThreshold");
		int band = Integer.parseInt((String) params.get("band"));
		int limiarDeParada = Integer.parseInt((String) params.get("stopIterationLimiar"));
		int initialThreshold = 0;
		if(initialThresholdStr != null)
			initialThreshold = Integer.parseInt(initialThresholdStr);
		else
		initialThreshold = (int) histo.getMeanValue(0,255,band);
		Raster srcMatrix = image.getData();
		double novoLimiar = 0.0;
		double antigoLimiar = 255.0;
		while(Math.abs(novoLimiar - antigoLimiar) > limiarDeParada) {
			double somaMaior = 0.0;
			double somaMenor = 0.0;
			int totalMaior = 0;
			int totalMenor = 0;
			for(int i=0; i<srcMatrix.getWidth(); i++){
				for(int j=0; j<srcMatrix.getHeight(); j++){
					int[] pixel = srcMatrix.getPixel(i,j,new int[]{0,0,0});
					if(pixel[band] > initialThreshold) {
						somaMaior+= pixel[band];
						totalMaior++;
					}
					else {
						somaMenor+= pixel[band];
						totalMenor++;
					}
				}
			}
			if(totalMaior == 0)
				totalMaior =1;
			if(totalMenor == 0)
				totalMenor =1;
			double mediaMaior = somaMaior / totalMaior;
			double mediaMenor = somaMenor / totalMenor;
			
			antigoLimiar = novoLimiar;
			novoLimiar = (mediaMaior + mediaMenor) / 2;
		}
		return novoLimiar;
	}
}
