package segmentacao;

/*
 * BasicoThresholding
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.HashMap;
import java.util.Map;

import exceptions.ImageProcessingException;

/**
 * Classe que implementa a segmentacao basico thresholding.
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class BasicoThresholding extends SegmentacaoAbstrata {

	/**
	 * Construtor de um BasicoThresholding
	 * @param operation operacao a ser realizada na imagem
	 */
	public BasicoThresholding(SegmentaoOperacao operation) {
		super(operation);
	}

	/**
	 * Metodo que segmenta a imagem passada como parametro
	 * @param image imagem a ser segmentada
	 * @return imagem segmentada
	 */
	public BufferedImage segmentImage(BufferedImage image)
			throws ImageProcessingException {
		String limiarStr = (String) getOperation().getParameter("limiar");
		int limiar;
		if(limiarStr != null)
			try {
				limiar = Integer.parseInt(limiarStr);
			}catch (NumberFormatException e) {
				limiar = calculaLimiar(image);
			}
		else
			limiar = calculaLimiar(image);
		Raster srcMatrix = image.getData();
		int numBands = srcMatrix.getNumBands();
		int imageType = numBands == 1 ? BufferedImage.TYPE_BYTE_GRAY : BufferedImage.TYPE_INT_RGB;  
		BufferedImage novaImagem = new BufferedImage(image.getWidth(), image.getHeight(), imageType);
		WritableRaster destMatrix = novaImagem.getRaster();
		for(int i=0; i<destMatrix.getWidth(); i++) {
			for(int j=0; j<destMatrix.getHeight(); j++) {
				int[] pixel = srcMatrix.getPixel(i,j,new int[]{0,0,0});
				int[] novoPixel = new int[numBands];
				for(int k=0; k<novoPixel.length; k++) {
					if(pixel[k] > limiar)
						novoPixel[k] = 255;
					else
						novoPixel[k] = 0;
				}
				destMatrix.setPixel(i,j,novoPixel);
			}
		}
		novaImagem.setData(destMatrix);
		return novaImagem;
	}

	/**
	 * Metodo que calcula o limiar da imagem
	 * @param image imagem a ser calculada o limiar
	 * @return limiar
	 */
	@SuppressWarnings("unchecked")
	private int calculaLimiar(BufferedImage image) {
		Thresholding td = new Thresholding();
		Map params = new HashMap();
		params.put("band","0");
		params.put("stopIterationLimiar","5");
		return (int) td.detectThreshold(image,params,"basic");
	}

}
