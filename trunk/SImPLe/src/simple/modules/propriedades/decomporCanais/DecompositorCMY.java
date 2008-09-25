package simple.modules.propriedades.decomporCanais;
/*
 * DecompositorCMY
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

import simple.manipulacoes.util.Modificador;
import simple.manipulacoes.util.MyBufferedImage;


/**
 * Classe que compõe e decompõe uma imagem para o modelo cromático CMY(Cyan, Magenta, Yellow)
 * 
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 *  
 */ 
public class DecompositorCMY {
	
	private int altura;
	private int largura;
	private int[][] alpha;
	private BufferedImage buffer;
	private String nome;	
	
	/**
	 * Construtor da Classe DecompositorCMY
	 * @param image A imagem a ser modificada
	 * @param nome O nome da Imagem
	 */
	public DecompositorCMY(BufferedImage image, String nome){
		this.altura = image.getHeight();
		this.largura = image.getWidth();
		this.nome = nome;
		buffer = image;
	    alpha = new Modificador(image).getAlpha();
	}
	
    /**
     * Decompõe a imagem em canais CMY
     * @return um array com três canais CMY
     */
    public Image[] getCanaisCMY(){
        BufferedImage[] canais = new BufferedImage[3];
        Image[] frames = new Image[3];
        canais[0] = new BufferedImage(buffer.getWidth(),buffer.getHeight(),buffer.getType());
        canais[1] = new BufferedImage(buffer.getWidth(),buffer.getHeight(),buffer.getType());
        canais[2] = new BufferedImage(buffer.getWidth(),buffer.getHeight(),buffer.getType());
        
        for(int x = 0; x < largura; x++) 
        	 for(int y = 0; y < altura; y++){
               	int R = buffer.getRGB(x,y) & 0xff0000;
                int G = buffer.getRGB(x,y) & 0x00ff00;
                int B = buffer.getRGB(x,y) & 0x0000ff;
                int A = alpha[y][x];
                R = R >> 16;
            	G = G >> 8;
            	B = B >> 0;
                
               	int C = 255 - R;
                int M = 255 - G;
                int Y = 255 - B;
                
            	canais[0].setRGB(x,y,(A << 24) + (C << 16) + (C << 8) + C);  // Canal C 
            	canais[1].setRGB(x,y,(A << 24) + (M << 16) + (M << 8) + M);  // Canal M 
            	canais[2].setRGB(x,y,(A << 24) + (Y << 16) + (Y << 8) + Y);  // Canal Y 
            }
        frames[0] = new ImageIcon(MyBufferedImage.makeImage(canais[0])).getImage();
        frames[1] = new ImageIcon(MyBufferedImage.makeImage(canais[1])).getImage();
        frames[2] = new ImageIcon(MyBufferedImage.makeImage(canais[2])).getImage();
        return frames;
        
    }      
    /**
     * Compõe uma imagem CMY a partir de tres canais C,M e Y
     * @param canais - Os canais que iram formar a nova imagem
     * @return A imagem CMY
     */        
    public static Image setCanaisCMY(BufferedImage[] canais, int[][] alpha) {
    	BufferedImage resultado = new BufferedImage(canais[0].getWidth(), canais[0].getHeight(),BufferedImage.TYPE_INT_ARGB);
    	for(int x = 0; x < canais[0].getWidth(); x++)
    		for(int y = 0; y < canais[0].getHeight(); y++){
    			int R = canais[0].getRGB(x,y) & 0xff0000;
    			int G = canais[1].getRGB(x,y) & 0x00ff00;
    			int B = canais[2].getRGB(x,y) & 0x0000ff;
    			int A = alpha[y][x];    			
            	int C = 255 - R;
    			int M = 255 - G;
    			int Y = 255 - B;    			    			
    			resultado.setRGB(x,y, A + C + M + Y  );    			
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