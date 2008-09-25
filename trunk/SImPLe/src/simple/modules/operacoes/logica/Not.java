/*
 * Not
 * 
 * @version 1.0
 * 
 * Date: 30/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

package simple.modules.operacoes.logica;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;

import simple.manipulacoes.util.MyBufferedImage;
import simple.manipulacoes.util.PixelUtils;

import simple.excecoes.OperacaoAritmeticaException;
import simple.excecoes.OperacaoLogicaException;

/**
 * Classe que faz a operacao logica Not sobre imagens
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class Not {
	
	/**
	 * Metodo not que faz a operacao not duas imagens gerando uma outra com o resultado
	 * @param image A operar
	 * @return Imagem com a operacao
	 * @throws OperacaoAritmeticaException Caso nao seja possivel capturar os pixels da imagem
	 */
	public static Image not(ImageIcon imagem) throws OperacaoLogicaException {
		Image image1 = imagem.getImage();
		int image1Height = image1.getHeight(null);
		int image1Width = image1.getWidth(null);
		int imageResultHeight = image1Height;
		int imageResultWidth = image1Width;
		int[] resultImage = new int[imageResultHeight * imageResultWidth];
		int[] pixels = new int[image1Height * image1Width];
		PixelGrabber pg = new PixelGrabber(image1, 0, 0, image1Width, image1Height, pixels, 0, image1Width);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			throw new OperacaoLogicaException("Nao foi possivel capturar os pixels da imagem!!");
		}
		for (int i = 0; i < image1Height; i++) {
			for (int j = 0; j < image1Width; j++) {
				resultImage[PixelUtils.getIndex(i, j, imageResultWidth)] = PixelUtils.notPixel(PixelUtils.getPixel(pixels, i, j, image1Width));
			}
		}       
		BufferedImage bi = new BufferedImage(imageResultWidth, imageResultHeight, BufferedImage.TYPE_INT_RGB);
		bi.setRGB(0, 0, imageResultWidth, imageResultHeight, resultImage, 0, imageResultWidth);
		return MyBufferedImage.makeImage(bi);        
	}
}