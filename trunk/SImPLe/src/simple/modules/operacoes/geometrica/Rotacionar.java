package simple.modules.operacoes.geometrica;

/*
 * Rotacionar
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;

import simple.manipulacoes.util.MyBufferedImage;
import simple.excecoes.RotacionarException;

/**
 * Classe que Rotaciona a imagem
 * @version 1.0 20/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class Rotacionar {

	/**
	 * Método rotacionar que Rotaciona a imagem com o grau desejado
	 * @param imagem A imagem a ser rotacionada
	 * @param graus O valor do grau de rotacao
	 * @return A nova imagem rotacionada
	 * @throws RotacionarException 
	 */
	public static BufferedImage rotacionar(RenderedImage imagem, int graus) throws RotacionarException{
		try{
			if((imagem.getWidth() != imagem.getHeight()) && graus == 90) return rotaciona90(imagem,graus);
			if((imagem.getWidth() != imagem.getHeight()) && graus == 270) return rotaciona270(imagem,graus);
			RenderedImage rotacionada = null;
			float angle = (float)(graus * (Math.PI/180.0F));
			ParameterBlock pb = new ParameterBlock();
			pb.addSource(imagem);					                   
			pb.add((float)(imagem.getWidth()/2));                     
			pb.add((float)(imagem.getHeight()/2));                     
			pb.add(angle);                      					 
			pb.add(new InterpolationNearest()); 					 
			rotacionada = JAI.create("Rotate", pb, null);
			BufferedImage bufferedImage = new BufferedImage(imagem.getWidth(),imagem.getHeight(),BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = bufferedImage.createGraphics();
			g2.drawRenderedImage(rotacionada,new AffineTransform());
			return bufferedImage;
		} catch (Exception e) {
			throw new RotacionarException("Erro na Rotacao...");
		}
	}

	/**
	 * Método rotaciona90 que Rotcaiona a imagem em 90 graus sem perdas caso a imagem nao tenha a mesma largura e altura
	 * @param imagem A imagem a ser rotacionada
	 * @param graus O valor do grau de rotacao
	 * @return A nova imagem rotacionada
	 */
	private static BufferedImage rotaciona90(RenderedImage imagem, double graus) {
		BufferedImage image = MyBufferedImage.makeBufferedImage(imagem);
		int[] pixels = new int[imagem.getWidth()*image.getHeight()];
		PixelGrabber pg1 = new PixelGrabber(image,0,0,image.getWidth(),image.getHeight(),pixels,0,image.getWidth());
		try { 
			pg1.grabPixels(); 
		} catch (InterruptedException e) {}
		int[] pixel90 = new int[pixels.length];
		int[][] mat = new int[imagem.getHeight()][imagem.getWidth()];
		int cont = 0;
		for (int i = 0; i < imagem.getHeight(); i++)
			for (int j = 0; j < imagem.getWidth(); j++) {
				mat[i][j] = pixels[cont];		      	
		        cont++;
			}
		int[][] mat90 = new int[imagem.getWidth()][imagem.getHeight()];
		for (int i = 0; i < imagem.getHeight(); i++)
			for (int j = 0; j < image.getWidth(); j++) 
				mat90[j][i] = mat[(imagem.getHeight()-1)-i][j];		      	
		cont = 0;
		for (int i = 0; i < imagem.getWidth(); i++)
			for (int j = 0; j< imagem.getHeight(); j++)
				pixel90[cont++] = mat90[i][j];
		BufferedImage b = new BufferedImage(imagem.getHeight(),imagem.getWidth(),BufferedImage.TYPE_INT_RGB);
		b.setRGB(0,0,imagem.getHeight(),imagem.getWidth(),pixel90,0,imagem.getHeight());
		return b;
	}
	
	/**
	 * Método rotaciona270 que Rotcaiona a imagem em 90 graus sem perdas caso a imagem nao tenha a mesma largura e altura
	 * @param imagem A imagem a ser rotacionada
	 * @param graus O valor do grau de rotacao
	 * @return A nova imagem rotacionada
	 */
	private static BufferedImage rotaciona270(RenderedImage imagem, double graus){
		BufferedImage image = MyBufferedImage.makeBufferedImage(imagem);
		int[] pixels = new int[imagem.getWidth()*image.getHeight()];
		PixelGrabber pg1 = new PixelGrabber(image,0,0,image.getWidth(),image.getHeight(),pixels,0,image.getWidth());
		try { 
			pg1.grabPixels(); 
		} catch (InterruptedException e) {}
		int[] pixel270 = new int[pixels.length];
		int[][] mat = new int[imagem.getHeight()][imagem.getWidth()];
		int cont = 0;
		for (int i = 0; i < imagem.getHeight(); i++)
			for (int j = 0; j < imagem.getWidth(); j++) {
				mat[i][j] = pixels[cont];		      	
		        cont++;
			}
		int[][] mat270 = new int[imagem.getWidth()][imagem.getHeight()];
		for (int i = 0; i < imagem.getHeight(); i++)
			for (int j = 0; j < image.getWidth(); j++) 
				mat270[j][i] = mat[i][(imagem.getWidth()-1)-j];		      	
		cont = 0;
		for (int i = 0; i < imagem.getWidth(); i++)
			for (int j = 0; j< imagem.getHeight(); j++)
				pixel270[cont++] = mat270[i][j];
		BufferedImage b = new BufferedImage(imagem.getHeight(),imagem.getWidth(),BufferedImage.TYPE_INT_RGB);
		b.setRGB(0,0,imagem.getHeight(),imagem.getWidth(),pixel270,0,imagem.getHeight());
		return b;
	}
}