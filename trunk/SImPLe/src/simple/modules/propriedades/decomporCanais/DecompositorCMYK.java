package simple.modules.propriedades.decomporCanais;
/*
 * DecompositorCMYK
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
 * Classe compõe e decompõe uma imagem para o modelo cromático CMYK(Cyan, Magenta, Yellow, Black)
 * 
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 *  
 */ 
 
public class DecompositorCMYK {
	
	private int altura;
	private int largura;
	private int[][] alpha;
	private static BufferedImage buffer;
	private final static int CYAN = 0;
	private final static int MAGENTA = 1;
	private final static int YELLOW = 2;
	private final static int BLACK = 3;
	private String nome;
   
	/**
	 * Construtor da Classe DecompositorCMYK
	 * @param image A imagem a ser modificada
	 * @param nome O nome da Imagem
	 */
	public DecompositorCMYK(BufferedImage image, String nome){
		this.altura = image.getHeight();
		this.largura = image.getWidth();
		this.nome = nome;
		buffer = image;
		alpha = new Modificador(image).getAlpha();
	}	
	/**
     * Decompoe a imagem em canais CMYK
     * @return um array com quatro canais CMYK
     */    
    public Image[] getCanaisCMYK(){
    	Image[] frames = new Image[4];
    	BufferedImage[] canais = new BufferedImage[4];
        canais[CYAN] = new BufferedImage(buffer.getWidth(),buffer.getHeight(),buffer.getType());
        canais[MAGENTA] = new BufferedImage(buffer.getWidth(),buffer.getHeight(),buffer.getType());
        canais[YELLOW] = new BufferedImage(buffer.getWidth(),buffer.getHeight(),buffer.getType());
        canais[BLACK] = new BufferedImage(buffer.getWidth(),buffer.getHeight(),buffer.getType());
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
                int K = Math.min(C, Math.min(M, Y));
                C = C - K;
                M = M - K;
                Y = Y - K;
            	canais[ 0 ].setRGB(x,y , ( A << 24 ) + ( C << 16 ) + ( C << 8 ) + C  );  //Canal C 
            	canais[ 1 ].setRGB(x,y , ( A << 24 ) + ( M << 16 ) + ( M << 8 ) + M  );  //Canal M
            	canais[ 2 ].setRGB(x,y , ( A << 24 ) + ( Y << 16 ) + ( Y << 8 ) + Y  );  //Canal Y
               	canais[ 3 ].setRGB(x,y , ( A << 24 ) + ( K << 16 ) + ( K << 8 ) + K  );  //Canal K
            }
        frames[0] = new ImageIcon(MyBufferedImage.makeImage(canais[0])).getImage();
        frames[1] = new ImageIcon(MyBufferedImage.makeImage(canais[1])).getImage();
        frames[2] = new ImageIcon(MyBufferedImage.makeImage(canais[2])).getImage();
        frames[3] = new ImageIcon(MyBufferedImage.makeImage(canais[3])).getImage();
        return frames;       
    }
    /**
     * Compoe uma imagem CMYK a partir de quatro canais C, M , Y e K
     * @param canais - Os canais que iram formar a nova imagem
     * @return A imagem CMYK
     */        
    public static Image setCanaisCMYK(BufferedImage[] canais) {
    	BufferedImage resultado = new BufferedImage(canais[0].getWidth(), canais[0].getHeight(),canais[0].getType());
    	for(int y = 0; y < canais[0].getHeight(); y++)
    		for(int x = 0; x < canais[0].getWidth(); x++) {                
            	int C = canais[0].getRGB(x,y) & 0xff0000;
                int M = canais[1].getRGB(x,y) & 0x00ff00;
                int Y = canais[2].getRGB(x,y) & 0x0000ff;
                int K = canais[3].getRGB(x,y);
                resultado.setRGB(x,y, 1 - (C + M + Y + K));      
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