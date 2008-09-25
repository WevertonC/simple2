/*
 * Pessoal
 * 
 * @version 1.0
 * 
 * Date: 28/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

package simple.modules.operacoes.aritmeticas;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import simple.manipulacoes.util.MyBufferedImage;


/**
 * Classe que faz Adicao sobre imagens
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class Pessoal {


	/**
	 * Metodo faz a operacao pessoal com os valores de GAIN e OFFSET que modificam os pixels da imagem
	 * @param imagenm Imagem desejada
	 * @param gain o valor do gain desejado
	 * @param offset o valor do offset desejado
	 */
	public static Image aritmeticaPessoal(Image imagem,Double gain, Double offset) {
		BufferedImage image = MyBufferedImage.makeBufferedImage(imagem);
		int countBands = image.getData().getNumBands();
		int imageType = countBands == 1 ? BufferedImage.TYPE_BYTE_GRAY : BufferedImage.TYPE_INT_RGB;
		BufferedImage novaImagem = new BufferedImage(image.getWidth(), image.getHeight(), imageType);
		Raster operand1Matrix = image.getData();
		WritableRaster matrixDestino = novaImagem.getRaster();
		for(int i = 0; i < matrixDestino.getWidth(); i++) {
			for(int j = 0; j < matrixDestino.getHeight(); j++) {
				int[] pixel1 = operand1Matrix.getPixel(i,j,new int[]{0,0,0});
				int[] pixel2 = null;
				int[] novoPixel = applyOperation(pixel1,pixel2,gain,offset);
				matrixDestino.setPixel(i,j,novoPixel);
			}
		}
		novaImagem.setData(matrixDestino);
		return MyBufferedImage.makeImage(novaImagem);
	}
	
	/**
	 * Método que modifica o valor de cada pixel de acordo com os valores do gain e do offset
	 * @param pixel pixel da imagem
	 * @param gain o valor do gain
	 * @param offset o valor do offset
	 * @return o valor do novo pixel
	 */
	private static int[] applyOperation(int[] pixel, int[] pixel2, Double gain,	Double offset) {
		int[] novoPixel = new int[pixel.length];
		for(int i = 0; i < pixel.length; i++){
			novoPixel[i] = ((int)(gain.doubleValue()*pixel[i] + offset.doubleValue()));
			if(novoPixel[i] > 255)
				novoPixel[i] = 255;
			else novoPixel[i] = novoPixel[i] & 255;
		}
		return novoPixel;
	}
}