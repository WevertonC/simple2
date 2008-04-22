/*
 * Subtracao
 * 
 * @version 1.0
 * 
 * Date: 28/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

package aritmeticas;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;

import util.MyBufferedImage;
import util.PixelUtils;
import exceptions.OperacaoAritmeticaException;

/**
 * Classe que faz Multiplicacao sobre imagens
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class Subtracao {

	/**
	 * Metodo subtracao que faz a subtracao entre duas imagens
	 * @param primeiraImage A primeira imagem a ser subtraida
	 * @param segundaImage A segunda para subtrair a primeira
	 * @return Image com a subtracao
	 * @throws OperacaoAritmeticaException Caso nao seja possivel capturar os pixels da imagem
	 */
	public static Image subtracao(ImageIcon primeiraImage, ImageIcon segundaImage) throws OperacaoAritmeticaException{
       Image image1 = primeiraImage.getImage();
       Image image2 = segundaImage.getImage();
       int image1Height = image1.getHeight(null);
       int image1Width = image1.getWidth(null);
       int image2Height = image2.getHeight(null);
       int image2Width = image2.getWidth(null);
       int imageResultHeight = image1Height > image2Height ? image1Height : image2Height;
       int imageResultWidth = image1Width > image2Width ? image1Width : image2Width;
       int[] resultImage = new int[imageResultHeight * imageResultWidth];
       int[] pixels = new int[image1Height * image1Width];
       PixelGrabber pg = new PixelGrabber(image1, 0, 0, image1Width, image1Height, pixels, 0, image1Width);
       try {
           pg.grabPixels();
       } catch (InterruptedException e) {
           throw new OperacaoAritmeticaException("Caso nao seja possivel capturar os pixels da imagem");
       }
       for (int i = 0; i < image1Height; i++) {
           for (int j = 0; j < image1Width; j++) {
               resultImage[PixelUtils.getIndex(i, j, imageResultWidth)] = PixelUtils.getPixel(pixels, i, j, image1Width);
           }
       }
       pixels = new int[image2Height * image2Width];
       pg = new PixelGrabber(image2, 0, 0, image2Width, image2Height, pixels, 0, image2Width);
       try {
           pg.grabPixels();
       } catch (InterruptedException e) {
           throw new OperacaoAritmeticaException("Caso nao seja possivel capturar os pixels da imagem");
       }
       for (int i = 0; i < image2Height; i++) {
           for (int j = 0; j < image2Width; j++) {
                 resultImage[PixelUtils.getIndex(i, j, imageResultWidth)] =  PixelUtils.subtractPixels(resultImage[PixelUtils.getIndex(i, j, imageResultWidth)],PixelUtils.getPixel(pixels, i, j, image2Width));
           }
       }
       BufferedImage bi = new BufferedImage(imageResultWidth, imageResultHeight, BufferedImage.TYPE_INT_RGB);
       bi.setRGB(0, 0, imageResultWidth, imageResultHeight, resultImage, 0, imageResultWidth);
       return MyBufferedImage.makeImage(bi);         
    }	
}