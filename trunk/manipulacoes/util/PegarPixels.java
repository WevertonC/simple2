package util;
/*
 * Ilusao de Optica (Pontos Pretos)
 * 
 * @version 1.0
 * 
 * Date: 03/11/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.Image;
import java.awt.image.PixelGrabber;

import exceptions.OperacaoAritmeticaException;

/**
 * Classe que pega os pixels de uma imagem qualquer
 * @version 1.0 03/11/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class PegarPixels {
	
	/**
	 * Recupera o vetor de pixels de um imagem
	 * @param image A imagem a ser utilizada
	 * @param largura A largura da imagem
	 * @param altura A altura da imagem
	 * @return Um vetor com os pixels
	 * @throws OperacaoAritmeticaException
	 */
	public static int[] vetorPixels(Image image, int largura, int altura) throws OperacaoAritmeticaException {
		
		int[] pixels = new int[largura*altura];
		
		PixelGrabber pg = new PixelGrabber(image, 0, 0, largura, altura, pixels, 0, largura);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            throw new OperacaoAritmeticaException("Nao foi possivel capturar os pixels da imagem!!");
        }
        return pixels;
	}
}
