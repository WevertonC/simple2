package ruido;

/*
 * FiltroSobel
 * 
 * @version 1.0
 * 
 * Date: 10/11/07
 * 
 * Copyright FEDPI all rights reserved
 */
import java.awt.Image;

import javax.swing.ImageIcon;

import util.Modificador;

/**
 * Classe responsavel por executar as operacoes de geracao de ruido.
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class GeradorDeRuido {
	
	private ImageIcon image;
	
	public GeradorDeRuido(Image image){
		this.image = new ImageIcon(image);
	}
	
	public Image salt_and_pepper() {
		Modificador mod = new Modificador(image.getImage());
		int h = mod.getAltura();
		int w = mod.getLargura();
		int number, pos, x, y;
        number = (int)(h * w * 0.05);
        for(int i = 0; i < number; i++) {
                pos = (int)(w*h* Math.random());
                x = pos / w;
                y = pos % w;
                if (((double)Math.random()) >= 0.5){
                	mod.getBlue()[x][y] = 0;
                	mod.getRed()[x][y] = 0;
                	mod.getGreen()[x][y] = 0;
                }else{
                	mod.getBlue()[x][y] = 255;
                	mod.getRed()[x][y] = 255;
                	mod.getGreen()[x][y] = 255;
                }
        }
		int[] pixels = mod.getPixels(mod.getRed(),mod.getGreen(),mod.getBlue(),mod.getAlpha());
		return mod.recopoeImagem(pixels,w,h);
	}
	
	/*public Image gaussiano() {
		Modificador mod = new Modificador(image.getImage());
		int h = mod.getAltura();
		int w = mod.getLargura();
		int number, pos, x, y;
        number = (int)(h * w * 0.05);
        int dp = 20;
        for(int i = 0; i < number; i++) {
                pos = (int)(w*h* Math.random());
                x = pos / w;
                y = pos % w;
                int fator = (int)(Math.random()*2*dp) + 1;
                int desvioBlue = mod.getBlue()[x][y] - dp + fator;
                int desvioRed = mod.getRed()[x][y] - dp + fator;
                int desvioGreen = mod.getGreen()[x][y] - dp + fator;
                mod.getBlue()[x][y] = desvioBlue < 0 ? 0 : desvioBlue > 255 ? 255 : desvioBlue;
                mod.getRed()[x][y] = desvioRed < 0 ? 0 : desvioRed > 255 ? 255 : desvioRed;
                mod.getGreen()[x][y] = desvioGreen < 0 ? 0 : desvioGreen > 255 ? 255 : desvioGreen;
        }
		int[] pixels = mod.getPixels(mod.getRed(),mod.getGreen(),mod.getBlue(),mod.getAlpha());
		return mod.recopoeImagem(pixels,w,h);
	}*/
	
	public Image paulodt() {
		Modificador mod = new Modificador(image.getImage());
		int h = mod.getAltura();
		int w = mod.getLargura();
		int number, pos, x, y;
        number = (int)(h * w * 0.05);
        for(int i = 0; i < number; i++) {
                pos = (int)(w*h* Math.random());
                x = pos / w;
                y = pos % w;
                mod.getRed()[x][y] = (x-1) >= 0 ? ((9821 * mod.getRed()[x-1][y]) + 6925) % 65535 : 
                	((9821 * mod.getRed()[x][y]) + 6925) % 65535;
                mod.getGreen()[x][y] = (x-1) >= 0 ? ((9821 * mod.getGreen()[x-1][y]) + 6925) % 65535 : 
                	((9821 * mod.getGreen()[x][y]) + 6925) % 65535;
                mod.getBlue()[x][y] = (x-1) >= 0 ? ((9821 * mod.getBlue()[x-1][y]) + 6925) % 65535 : 
                	((9821 * mod.getBlue()[x][y]) + 6925) % 65535;
                
        }
		int[] pixels = mod.getPixels(mod.getRed(),mod.getGreen(),mod.getBlue(),mod.getAlpha());
		return mod.recopoeImagem(pixels,w,h);
	}
}

