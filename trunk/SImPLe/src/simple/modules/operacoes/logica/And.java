/*
 * AndX
 * 
 * @version 1.0
 * 
 * Date: 28/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

package simple.modules.operacoes.logica;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;

import simple.manipulacoes.util.MyBufferedImage;
import simple.manipulacoes.util.PegarPixels;
import simple.manipulacoes.util.PixelUtils;

import simple.excecoes.OperacaoLogicaException;

/**
 * Classe que faz a operacao logica And sobre imagens
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class And {
	
	private static int imageResultHeight, imageResultWidth;
	
	/**
	 * Metodo maiorLarguraEAltura que determina a imagem com maior altura e largura.
	 * @param imagensIcon
	 */
	private static void maiorLarguraEAltura(List<ImageIcon> imagensIcon) {
		for (int i = 0; i < imagensIcon.size(); i++) {
			int altura = imagensIcon.get(i).getIconHeight();
			int largura = imagensIcon.get(i).getIconWidth();
			imageResultHeight = altura > imageResultHeight ? altura : imageResultHeight;
			imageResultWidth = largura > imageResultWidth ? largura : imageResultWidth;
		}
	}
	
	/**
	 * Metodo and que faz a operacao add com varias imagens gerando uma outra com o resultado
	 * @param imagensIcon lista com as imagens
	 * @return Imagem com a multiplicacao
	 * @throws OperacaoLogicaException Caso nao seja possivel capturar os pixels da imagem
	 */
	public static Image and(List<ImageIcon> imagensIcon) throws OperacaoLogicaException{
		imageResultHeight = 0;
		imageResultWidth = 0;
		int altura, largura;
		int[] pixels, resultImage;
		maiorLarguraEAltura(imagensIcon);
		resultImage = new int[imageResultHeight * imageResultWidth];
		Image image = imagensIcon.get(0).getImage();
		largura = image.getWidth(null);
		altura = image.getHeight(null); 
		pixels = new int[largura*altura];
		try{
			pixels = PegarPixels.vetorPixels(image,largura,altura);
		} catch (Exception e) {
			throw new OperacaoLogicaException(e.getMessage());
		}
		for (int i = 0; i < imagensIcon.get(0).getIconHeight(); i++) 
			for (int j = 0; j < imagensIcon.get(0).getIconWidth(); j++) 
				resultImage[PixelUtils.getIndex(i, j, imageResultWidth)] = PixelUtils.getPixel(pixels, i, j, largura);
		int k = 1;
		while (k < imagensIcon.size()) {
			image = imagensIcon.get(k).getImage();
			altura = imagensIcon.get(k).getIconHeight();
			largura = imagensIcon.get(k).getIconWidth();
			try{
				pixels = new int[altura*largura];
				pixels = PegarPixels.vetorPixels(image,largura,altura);			
				pixels = new int[largura*altura];
				pixels = PegarPixels.vetorPixels(image,largura,altura);
			} catch (Exception e) {
				throw new OperacaoLogicaException(e.getMessage());
			}
			
			for (int i = 0; i < altura; i++) 
				for (int j = 0; j < largura; j++) 
					resultImage[PixelUtils.getIndex(i, j, imageResultWidth)] =  PixelUtils.and(
							resultImage[PixelUtils.getIndex(i, j, imageResultWidth)],PixelUtils.getPixel(pixels, i, j, largura));
			k++;
		}
		BufferedImage bi = new BufferedImage(imageResultWidth, imageResultHeight, BufferedImage.TYPE_INT_RGB);
		bi.setRGB(0, 0, imageResultWidth, imageResultHeight, resultImage, 0, imageResultWidth);
		return MyBufferedImage.makeImage(bi);
	}
}