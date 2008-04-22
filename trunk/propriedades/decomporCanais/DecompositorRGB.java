package decomporCanais;
/*
 * DecompositorRGB
 * 
 * @version 1.0
 * 
 * Date: 03/11/05
 * 
 * Copyright FEDPI all rights reserved
 */
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import util.Modificador;
import util.MyBufferedImage;

/**
 * Classe compõe e decompõe uma imagem para o modelo cromático RGB(Red, Green, Blue)
 * 
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 *  
 */ 
public class DecompositorRGB {
	
	int altura;
	int largura;
	Image imagem;
	int[][] alpha;
	BufferedImage buffer;
	String nome;
   
	 /**
	 * Construtor da Classe DecompositorRGB
	 * @param image A imagem a ser modificada
	 * @param nome O nome da Imagem
	 */
	public DecompositorRGB(BufferedImage image, String nome){
		this.nome = nome;
		this.altura = image.getHeight();
		this.largura = image.getWidth();
		buffer = image;
		imagem = MyBufferedImage.makeImage(image);
		alpha = new int[largura][altura];
		alpha = new Modificador(image).getAlpha();
	}
	
    /**
     * Decompõe a imagem em canais RGB
     * 
     * @return Um array de tres canais RGB em tons de cinza da imagem original
     */
    public Image[] getCanaisRGB(){
        BufferedImage[] canais = new BufferedImage[3];
        Image[] frames = new Image[3];
        largura = buffer.getWidth();
        altura = buffer.getHeight();
                
        canais[0] = new BufferedImage(buffer.getWidth(),buffer.getHeight(),buffer.getType());
        canais[1] = new BufferedImage(buffer.getWidth(),buffer.getHeight(),buffer.getType());
        canais[2] = new BufferedImage(buffer.getWidth(),buffer.getHeight(),buffer.getType());
        
        for(int x = 0; x < largura; x++) 
        	 for(int y = 0; y < altura; y++){
               	int R = buffer.getRGB(x,y) & 0xff0000;
                int G = buffer.getRGB(x,y) & 0x00ff00;
                int B = buffer.getRGB(x,y) & 0x0000ff;
            	
            	canais[0].setRGB(x,y , (R + ( R >> 8 ) + ( R >> 16 )) ); //Red Calturaannel
            	canais[1].setRGB(x,y , (( G << 8 ) + G + ( G >> 8 )) );  //Green Calturaannel
            	canais[2].setRGB(x,y , (( B << 16 ) + ( B << 8 ) + B) ); //Blue Calturaannel
            }
        frames[0] = new ImageIcon(MyBufferedImage.makeImage(canais[0])).getImage();
        frames[1] = new ImageIcon(MyBufferedImage.makeImage(canais[1])).getImage();
        frames[2] = new ImageIcon(MyBufferedImage.makeImage(canais[2])).getImage();
        return frames;        
   }

    /**
     * Compõe uma imagem RGB a partir de tres canais R, G, e B
     * @param canais - Os canais que iram formar a nova imagem
     * @return A imagem RGB
     */    
   
    public static Image setCanaisRGB(BufferedImage[] canais) {
    	BufferedImage resultado = new BufferedImage( canais[0].getWidth(), canais[0].getHeight(),canais[0].getType());
    	
    	for(int x = 0; x < canais[0].getWidth(); x++) 
    		for(int y = 0; y < canais[0].getHeight(); y++){
    		    int R = canais[0].getRGB(x,y) & 0xff0000;
    			int G = canais[1].getRGB(x,y) & 0x00ff00;
    			int B = canais[2].getRGB(x,y) & 0x0000ff;
    			resultado.setRGB(x,y,  R + G + B);
    		}
    	return new ImageIcon(MyBufferedImage.makeImage(resultado)).getImage();
    }
    
    /**
     * Recupera o nome da imagem
     * @return O nome da imagem
     */
    public String getNome(){
    	return nome;
    }
}