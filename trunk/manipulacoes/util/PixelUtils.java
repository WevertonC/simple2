package util;
/*
 * PixelUtils
 * 
 * @version 1.0
 * 
 * Date: 28/09/05
 * 
 * Copyright FEDPI all rights reserved
 */



import java.awt.Color;

/**
 * Classe que faz operacoes sobre os pixels das imagens
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */ 

public class PixelUtils {

    /**
     * Metodo getPixel que retorna o pixel correspondente as coordenadas x e y
     * da imagem, de acordo com o vetor de pixels da mesma
     * @param pixels O array de pixels
     * @param x A coordenada x da imagem
     * @param y A coordenada y da imagem.     
     * @param width A largura da imagem
     * @return O pixels desejado
     */
	public static int getPixel(int[] pixels, int x, int y, int width) {
	   return pixels[(x * width) + y];
	}
   	/**
	 * Metodo getIndex que retorn a posicao do pixel na imagem
	 * @param x A coordenada x da imagem
	 * @param y A coordenada y da imagem
	 * @param width A largura da imagem
	 * @return A posicao desejada
	 */
	public static int getIndex(int x, int y, int width) {
		   return (x * width) + y;
		}  

    /**
     * Metodo getColor que retorna a cor de um pixel
     * @param pixel O pixels desejado
     * @return A cor representante do pixel
     */
    public static Color getColor(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red   = (pixel >> 16) & 0xff;
        int green = (pixel >>  8) & 0xff;
        int blue  = (pixel      ) & 0xff;
        return new Color(red, green, blue, alpha);
    }
    
    /**
     * Metodo addPixels que adiciona dois pixels
     * @param a O primeiro pixel
     * @param b O segundo pixel
     * @return O pixels resultante da soma
     */
    public static int addPixels(int a, int b) {
		Color aC = PixelUtils.getColor(a);
		Color bC = PixelUtils.getColor(b);
		int red = aC.getRed() + bC.getRed() > 255 ? 255 : aC.getRed() + bC.getRed();
		int green = aC.getGreen() + bC.getGreen() > 255 ? 255 : aC.getGreen() + bC.getGreen();
		int blue = aC.getBlue() + bC.getBlue() > 255 ? 255 : aC.getBlue() + bC.getBlue();
		int alpha = aC.getAlpha() + bC.getAlpha() > 255 ? 255 : aC.getAlpha() + bC.getAlpha();
		return new Color(red, green, blue, alpha).getRGB();
	}
    /**
     * Metodo subtractPixels que subtrai dois pixels
     * @param a O primeiro pixel
     * @param b O segundo pixel
     * @return O pixel resultante
     */
    public static int subtractPixels(int a, int b) {
        Color aC = PixelUtils.getColor(a);
        Color bC = PixelUtils.getColor(b);
        int red = aC.getRed() - bC.getRed() < 0 ? 0 : aC.getRed() - bC.getRed();
        int green = aC.getGreen() - bC.getGreen() < 0 ? 0 : aC.getGreen() - bC.getGreen();
        int blue = aC.getBlue() - bC.getBlue() < 0 ? 0 : aC.getBlue() - bC.getBlue();
        int alpha = aC.getAlpha() - bC.getAlpha() < 0 ? 0 : aC.getAlpha() - bC.getAlpha();
        return new Color(red, green, blue, alpha).getRGB();
    }
    /**
     * Metodo dividePixels que divide dois pixels
     * @param a O primeiro pixel
     * @param b O segundo pixel
     * @return O pixel resultante
     */
    public static int dividePixels(int a, int b) {
        Color aC = PixelUtils.getColor(a);
        Color bC = PixelUtils.getColor(b);
        int red = bC.getRed() == 0 ? aC.getRed() :  (aC.getRed() / bC.getRed());
        int green = bC.getGreen() == 0 ? aC.getGreen() : (aC.getGreen() / bC.getGreen());
        int blue = bC.getBlue() == 0 ? aC.getBlue() : (aC.getBlue() / bC.getBlue());
        int alpha = bC.getAlpha() == 0 ? aC.getAlpha() : (aC.getAlpha() / bC.getAlpha());
        return new Color(red, green, blue, alpha).getRGB();
    }
    /**
     * Metodo multiplyPixels que multiplica dois pixels
     * @param a O primeiro pixel
     * @param b O segundo pixel
     * @return O pixel resultante
     */   
    public static int multiplyPixels(int a, int b) {
        Color aC = PixelUtils.getColor(a);
        Color bC = PixelUtils.getColor(b);
        int red = aC.getRed() * bC.getRed() > 255 ? 255 : aC.getRed() * bC.getRed();
        int green = aC.getGreen() * bC.getGreen() > 255 ? 255 : aC.getGreen() * bC.getGreen();
        int blue = aC.getBlue() * bC.getBlue() > 255 ? 255 : aC.getBlue() * bC.getBlue();
        int alpha = aC.getAlpha() * bC.getAlpha() > 255 ? 255 : aC.getAlpha() * bC.getAlpha();
        return new Color(red, green, blue, alpha).getRGB();
    } 
 
    /**
     * Metodo xor que auxilia o metodo xorPixels
     * @param x O primeiro pixel
     * @param y O segundo pixel
     * @return O pixel resultante
     */
    public static int xor(int x, int y) {
        return  x ^ y;
    }
    /**
     * Metodo notPixels que opera o not de um pixel
     * @param a O pixel desejado
     * @return O pixel resultante
     */
    public static int notPixel(int x) {
        return ~(x);
    }
  
    /**
     * Metodo or que auxilia o metodo orPixels
     * @param x O primeiro pixel
     * @param y O segundo pixel
     * @return O pixel resultante
     */
    public static int or(int x, int y) {
        return x | y;
    }
    /**
     * Metodo and que auxilia o metodo andPixels
     * @param x O primeiro pixel
     * @param y O segundo pixel
     * @return O pixel resultante
     */
    public static int and(int x, int y) {
        return x & y;
    }
    /**
     * Metodo nand que auxilia o metodo nandPixels
     * @param x O primeiro pixel
     * @param y O segundo pixel
     * @return O pixel resultante
     */
    public static int nand(int x, int y) {
        return ~(x & y);
    }
    

    /**
     * Metodo nand que auxilia o metodo norPixels
     * @param x O primeiro pixel
     * @param y O segundo pixel
     * @return O pixel resultante
     */
    public static int nor(int x, int y) {
        return ~(x | y);
    }
}