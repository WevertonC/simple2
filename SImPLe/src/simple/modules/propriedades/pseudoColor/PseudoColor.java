package simple.modules.propriedades.pseudoColor;
/*
 * PseudoColor
 * 
 * @version 1.0
 * 
 * Date: 28/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ImageIcon;

import simple.manipulacoes.util.Modificador;
import simple.manipulacoes.util.MyBufferedImage;
import simple.modules.operacoes.radiometricas.EscalaCinza;


import simple.excecoes.PseudoColorException;

/**
 * Classe que faz PseudoColorizacao de uma imagem em nivelCinza a partir de uma colorida
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class PseudoColor {    
    
	/**
	 * Metodo pseudo que faz a pseudo colorizacao 
	 * @param pretaBranco A imagem a ser colorizada
	 * @param colorida A imagem que servira de base para a colorizacao
	 * @return Imagem pseudo-colorizada
	 * @throws PseudoColorException Caso nao seja possivel pegar os pixels da imagem
	 */
	@SuppressWarnings("unchecked")
	public static Image pseudo(ImageIcon pretaBranco, ImageIcon colorida) throws PseudoColorException{
    	Image brancoPreta = pretaBranco.getImage();
    	Object[] array = toGrayScale(colorida);
        //a nivel de cinza da colorida
    	BufferedImage nivelCinzaColorida = (BufferedImage) array[0];
        //pixels da colorida
    	int[] coloridaPixels = (int[]) array[1];
        //pega os pixels da nivel de cinza da colorida
        int[] nivelCinzaColoridaPixels = new int[nivelCinzaColorida.getWidth() * nivelCinzaColorida.getHeight()];
        PixelGrabber pg = new PixelGrabber(nivelCinzaColorida, 0, 0, nivelCinzaColorida.getWidth(),  nivelCinzaColorida.getHeight(), nivelCinzaColoridaPixels, 0, nivelCinzaColorida.getWidth());
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            throw new PseudoColorException("Impossible to grab original Image's pixels!");
        }
        //coloca cada pixel com seu correspondente em nivel de cinza no mapa colorBW
        Map<Integer,Integer> colorNivelCinza = new TreeMap<Integer,Integer>();
        //coloca cada pixel(cor) com sua quantidade de ocorrencias(frequencia)
        Map<Integer,Integer> colorFrequencies = new TreeMap<Integer,Integer>();
        for (int k = 0; k < coloridaPixels.length; k++) {
        	Integer color = new Integer(coloridaPixels[k]);
            if (colorFrequencies.containsKey(color)) {
                int freq = ((Integer)colorFrequencies.get(color)).intValue();
                freq++;
                colorFrequencies.put(color, new Integer(freq));
            } else {
                colorFrequencies.put(color, new Integer(1));
                colorNivelCinza.put(color, new Integer(nivelCinzaColoridaPixels[k]));
            }
        }
        Map<Integer,Integer> nivelcinzaColor = new TreeMap<Integer,Integer>();
        Iterator colorIt = colorFrequencies.entrySet().iterator();
        //limpa cores com tons de cinza repetidos baseado na frequência
        //cria mapa tom de cinza -> cores
        while (colorIt.hasNext()) {
        	Map.Entry entry = (Map.Entry) colorIt.next();
            Integer color = (Integer) entry.getKey();
            Integer freq = (Integer) entry.getValue();
            Integer nivelcinza = (Integer) colorNivelCinza.get(color);
            Integer colorIn = (Integer) nivelcinzaColor.get(nivelcinza); 
            if (colorIn != null) {
            	Integer frequencia = (Integer) colorFrequencies.get(colorIn);
               	if (freq.compareTo(frequencia) > 0) {
                    nivelcinzaColor.put(nivelcinza, color);
                }             
            }
            else nivelcinzaColor.put(nivelcinza, color);
        }
        // substitui tom de cinza da pretaBranca pela cor da original
        int[] brancoPretaPixels = new int[brancoPreta.getWidth(null)* brancoPreta.getHeight(null)];
        pg = new PixelGrabber(brancoPreta, 0, 0, brancoPreta.getWidth(null), brancoPreta.getHeight(null), brancoPretaPixels, 0,brancoPreta.getWidth(null));
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            throw new PseudoColorException("Impossible to grab original Image's pixels!");
        }
        BufferedImage finalImage = new BufferedImage(brancoPreta.getWidth(null), brancoPreta.getHeight(null), BufferedImage.TYPE_INT_RGB);
        for (int k = 0; k < brancoPretaPixels.length; k++) {
            Integer color = ((Integer) nivelcinzaColor.get(new Integer(brancoPretaPixels[k]))); 
            if (color != null) {
                int pseudoColor = color.intValue();
            	finalImage.setRGB(k % brancoPreta.getWidth(null), k / brancoPreta.getWidth(null), pseudoColor);
            }//mod         
            else finalImage.setRGB(k % brancoPreta.getWidth(null), k / brancoPreta.getWidth(null), brancoPretaPixels[k]);        	
        }
               
        return MyBufferedImage.makeImage(new Modificador(MyBufferedImage.makeImage(finalImage)).mediaVizinhos());        
    }

    /**
     * Pega os pixels da colorida e a buffered da imagem em nivel de cinza da colorida
     * @param image A imagem colorida base
     * @return Um objeto com o par: os pixels da imagem colorida e a imagem em nivelCinza da colorida
     * @throws PseudoColorException  Caso nao seja possivel pegar os pixels da imagem
     */
    private static Object[] toGrayScale(ImageIcon imageColorida) throws PseudoColorException{
    	int[] pixels = new int[imageColorida.getIconHeight()* imageColorida.getIconWidth()];
        PixelGrabber pg = new PixelGrabber(imageColorida.getImage(), 0, 0, imageColorida.getIconWidth(), imageColorida.getIconHeight(), pixels, 0, imageColorida.getIconWidth());
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            throw new PseudoColorException("Impossible to grab original Image's pixels!");
        }
        Object[] result = {MyBufferedImage.makeBufferedImage(EscalaCinza.nivelCinza(imageColorida)), pixels};
        return result;        
    }   
}