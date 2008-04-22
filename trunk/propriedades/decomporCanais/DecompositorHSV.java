package decomporCanais;
/*
 * DecompositorHSV
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
 * Classe compõe e decompõe uma imagem para o modelo cromático HSV(Hue, Saturation, Value)
 * 
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 *  
 */ 
 
public class DecompositorHSV {
	
    private final int HUE = 0;
    private final int SATURATION = 1;
    private final int VALUE = 2;
    private int altura;
    private int largura;
    private int[][] alpha;
    private BufferedImage buffer;
    private String nome;
	
    /**
	 * Construtor da Classe DecompositorHSV
	 * @param image A imagem a ser modificada
	 * @param nome O nome da Imagem
	 */
	public DecompositorHSV(BufferedImage image, String nome){
		this.altura = image.getHeight();
		this.largura = image.getWidth();
		this.nome = nome;
		buffer = image;
		alpha = new Modificador(image).getAlpha();
	}
	
    /**
     * Decompõe a imagem em canais HSV
     * @return um array com três canais HSV
     */            
    public Image[] getHSVChannels(){
    	Image[] frames = new Image[3];	
        BufferedImage[] canais = new BufferedImage[3];
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
            	
                double max = Math.max(R,Math.max(G,B));
                double min = Math.min(R,Math.min(G,B));                
                double delta = max - min;
                
                double H = 0;                
                double S = 0;
                double V = 0;
                                
                V = max;
                
                H = H / 255;
                S = S / 255;
                V = V / 255;
             	
                //Algoritmo retirado do codigo fonte do GIMP
                if (delta > 0.0001){
                	S = delta / max;
                	if (R == max){
                		H = (G - B)/delta;
                		if (H < 0.0)H += 6.0;
                	}
                	else if (G == max){
                		H = 2.0 + (B - R) / delta;
                	}
                	else if (B == max){
                		H = 4.0 + (R - G) / delta;
                	}
                	H /= 6.0;
                } else {
                	S = 0.0;
                	H = 0.0;
                }

                H = H * 255;                
                S = S * 255;
                V = V * 255;
                
                int iH = (int)H; 
                int iS = (int)S;
                int iV = (int)V;
                canais[HUE].setRGB(x,y , (A << 24) + ( iH << 16 ) + ( iH << 8 ) + iH  );  //Canal H 
                canais[SATURATION].setRGB(x,y , (A << 24) + ( iS << 16 ) + ( iS << 8 ) + iS  );  //Canal S 
                canais[VALUE].setRGB(x,y , (A << 24) + ( iV << 16 ) + ( iV << 8 ) + iV  );  //Canal V 			  
            }
        frames[0] = new ImageIcon(MyBufferedImage.makeImage(canais[0])).getImage();
        frames[1] = new ImageIcon(MyBufferedImage.makeImage(canais[1])).getImage();
        frames[2] = new ImageIcon(MyBufferedImage.makeImage(canais[2])).getImage();
        return frames;       
    }

    /**
     * Compõe uma imagem HSV a partir de tres canais H,S e V
     * @param canais - Os canais que iram formar a nova imagem
     * @return A imagem HSV
     */        
    public static Image setCanaisHSV(BufferedImage canais[], int[][] alpha) {    
    	BufferedImage resultado = new BufferedImage(canais[0].getWidth(), canais[0].getHeight(),BufferedImage.TYPE_INT_ARGB);
    	int[] pixels = new int[canais[0].getWidth()*canais[0].getHeight()];
    	int cont = 0;
    	for(int y = 0; y < canais[0].getHeight(); y++)
    		for(int x = 0; x < canais[0].getWidth(); x++) { 
				int H = ((canais[0].getRGB(x,y) & 0xff0000) >> 16); 
                int S = ((canais[1].getRGB(x,y) & 0x00ff00) >> 8);
                int V = (canais[2].getRGB(x,y) & 0xff);
                int A = ((alpha[y][x] & 0xff000000) >> 24);
                pixels[cont] = (((H) << 16) | ((S) << 8) | (V) | (A  <<  24));
			
                //resultado.setRGB(x, y, (H<<8) + (S<<16) + (V<<16));
                resultado.setRGB(x, y, (A<<24) + (H<<16) + (S<<8) + (V));
                cont++;
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