package decomporCanais;
/*
 * DecompositorYCrCb
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
 * Classe compõe e decompõe uma imagem para o modelo cromático YCrCb
 * 
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 *  
 */ 
 
public class DecompositorYCrCb {	
      
	int altura;
	int largura;
	Image imagem;
	int[][] alpha;
	BufferedImage buffer;
	String nome;
	
	/**
	 * Construtor da Classe DecompositorYCrCb
	 * @param image A imagem a ser modificada
	 * @param nome O nome da Imagem
	 */
	public DecompositorYCrCb(BufferedImage image, String nome){
		this.altura = image.getHeight();
		this.largura = image.getWidth();
		this.nome = nome;
		buffer = image;
		imagem = MyBufferedImage.makeImage(image);
		alpha = new int[largura][altura];
		alpha = new Modificador(image).getAlpha();
	}
	
	/**
     * Decompoe a imagem em canais YCrCb
     * @return um array com tres canais YCrCb
     */            
    public Image[] getCanaisYCrCb(){
        	
        BufferedImage[] canais = new BufferedImage[3];
        Image[] frames = new Image[3];             
        canais[0] = new BufferedImage(largura,altura,buffer.getType());
        canais[1] = new BufferedImage(largura,altura,buffer.getType());
        canais[2] = new BufferedImage(largura,altura,buffer.getType());
        
        for(int x = 0; x < largura; x++)
            for(int y = 0; y < altura; y++) {
               	int R = buffer.getRGB(x,y) & 0xff0000;
                int G = buffer.getRGB(x,y) & 0x00ff00;
                int B = buffer.getRGB(x,y) & 0x0000ff;
                int A = alpha[y][x];
                
                R = R >> 16;
            	G = G >> 8;
            	B = B >> 0;
            	
    			double dY =  (int)( (0.2989)*R + (0.5866)*G + (0.1145)*B + ( 16.5));
    			double dCb = (int)(-(0.1688)*R - (0.3312)*G + (0.5000)*B + (128.5));
    			double dCr = (int)( (0.5000)*R - (0.4184)*G - (0.0816)*B + (128.5));
    			
    			int Y = (int)dY;
    			int Cb = (int)dCb;
    			int Cr = (int)dCr;
    			
                canais[0].setRGB(x,y , ( A << 24 ) + ( Y << 16 ) + ( Y << 8 ) + Y  );  //Canal Y 
                canais[1].setRGB(x,y , ( A << 24 ) + ( Cb << 16 ) + ( Cb << 8 ) + Cb);  //Canal Cb 
                canais[2].setRGB(x,y , ( A << 24 ) + ( Cr << 16 ) + ( Cr << 8 ) + Cr);  //Canal Cr 			  
            }
        frames[0] = new ImageIcon(MyBufferedImage.makeImage(canais[0])).getImage();
        frames[1] = new ImageIcon(MyBufferedImage.makeImage(canais[1])).getImage();
        frames[2] = new ImageIcon(MyBufferedImage.makeImage(canais[2])).getImage();
        return frames;
    }

    /**
     * Compoe uma imagem YCbCr a partir de tres canais Y,Cb e Cr
     * @param canais - Os canais que iram formar a nova imagem
     * @return A imagem YCbCr
     */        
    public static Image setCanaisYCrCb(BufferedImage canais[]) {
    	BufferedImage resultado = new BufferedImage(canais[0].getWidth(), canais[0].getHeight(),canais[0].getType());
    	for(int y = 0; y < canais[0].getHeight(); y++)
    		for(int x = 0; x < canais[0].getWidth(); x++) { 
    			
    			int R = canais[0].getRGB(x,y) & 0xff0000;
    			int G = canais[1].getRGB(x,y) & 0x00ff00;
    			int B = canais[2].getRGB(x,y) & 0x0000ff;
    			    			
    			R = R >> 16;
    			G = G >> 8;
    			B = B >> 0;
    			
    			double dY =  (int)( (0.2989)*R + (0.5866)*G + (0.1145)*B + ( 16.5));
    			double dCb = (int)(-(0.1688)*R - (0.3312)*G + (0.5000)*B + (128.5));
    			double dCr = (int)( (0.5000)*R - (0.4184)*G - (0.0816)*B + (128.5));
    			
    			int Y = (int)dY;
    			int Cb = (int)dCb;
    			int Cr = (int)dCr;
    		
    			resultado.setRGB(x, y, (Y<<16) + (Cb<<8) + Cr);
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