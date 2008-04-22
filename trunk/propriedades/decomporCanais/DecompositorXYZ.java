package decomporCanais;
/*
 * DecompositorXYZ
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
 * Classe compõe e decompõe uma imagem para o modelo cromático XYZ
 * 
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 *  
 */ 

public class DecompositorXYZ {
	
	int altura;
	int largura;
	Image imagem;
	int[][] alpha;
	BufferedImage buffer;
	String nome;
	
	/**
	 * Construtor da Classe DecompositorXYZ
	 * @param image A imagem a ser modificada
	 * @param nome O nome da Imagem
	 */
	public DecompositorXYZ(BufferedImage image, String nome){
		this.altura = image.getHeight();
		this.largura = image.getWidth();
		this.nome = nome;
		buffer = image;
		imagem = MyBufferedImage.makeImage(image);
		alpha = new int[largura][altura];
		alpha = new Modificador(image).getAlpha();
	}
	
	
    /**
     * Decompoe a imagem em canais XYZ
     * @return um array com quatro canais XYZ
     */    
    public Image[] getCanaisXYZ(){
    	Image[] frames = new Image[3];
    	BufferedImage[] canais = new BufferedImage[4];
        canais[0] = new BufferedImage(buffer.getWidth(),buffer.getHeight(),buffer.getType());
        canais[1] = new BufferedImage(buffer.getWidth(),buffer.getHeight(),buffer.getType());
        canais[2] = new BufferedImage(buffer.getWidth(),buffer.getHeight(),buffer.getType());
       
                
        for(int y = 0; y < altura; y++)
            for(int x = 0; x < largura; x++) {
               	int R = buffer.getRGB(x,y) & 0xff0000;
                int G = buffer.getRGB(x,y) & 0x00ff00;
                int B = buffer.getRGB(x,y) & 0x0000ff;
                int A = alpha[y][x];
            	R = R >> 16;
            	G = G >> 8;
            	B = B >> 0;                
            	int X = (int)(0.431*R + 0.342*G + 0.178*B);
				int Y = (int)(0.222*R + 0.707*G + 0.071*B);
				int Z = (int)(0.020*R + 0.130*G + 0.8*B);
            	canais[ 0 ].setRGB(x,y , ( A << 24 ) + ( X << 16 ) + ( X << 8 ) +  X );  //Canal X
            	canais[ 1 ].setRGB(x,y , ( A << 24 ) + ( Y << 16 ) + ( Y << 8 ) +  Y  ); //Canal Y 
            	canais[ 2 ].setRGB(x,y , ( A << 24 ) + ( Z << 16 ) + ( Z << 8 ) +  Z );  //Canal Z 
            }
        frames[0] = new ImageIcon(MyBufferedImage.makeImage(canais[0])).getImage();
        frames[1] = new ImageIcon(MyBufferedImage.makeImage(canais[1])).getImage();
        frames[2] = new ImageIcon(MyBufferedImage.makeImage(canais[2])).getImage();
        return frames;
    }

    /**
     * Compoe uma imagem CMYK a partir de quatro canais C, M , Y e K
     * @param canais - Os canais que iram formar a nova imagem
     * @return A imagem CMYK
     */        
    public static Image setCanaisXYZ(BufferedImage canais[], int[][] alpha) {
    	BufferedImage resultado = new BufferedImage(canais[0].getWidth(), canais[0].getHeight(),canais[0].getType());
    	for(int y = 0; y < canais[0].getHeight(); y++)
    		for(int x = 0; x < canais[0].getWidth(); x++) { 
    		   			
    			int X = canais[0].getRGB(x,y) & 0xff0000;
    			int Y = canais[1].getRGB(x,y) & 0x00ff00;
    			int Z = canais[2].getRGB(x,y) & 0x0000ff;
    			int A = alpha[y][x];
    			resultado.setRGB(x,y, A + X + Y + Z);
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