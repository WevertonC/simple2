package simple.modules.operacoes.geometrica;
/*
 * Redimensionar
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import javax.swing.ImageIcon;

import simple.manipulacoes.util.MyBufferedImage;

import simple.excecoes.RedimensionarException;

/**
 * Classe que calcula o Redimensionamento da imagem
 * @version 1.0 20/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class Redimensionar {
	
	private static final int PORCENTAGEM = 100;

	/**
	 * Metodo emPorcentagem que Faz a Transformação da imagem por porcentagem da largura e altura
	 * @param imagem A image desejada
	 * @param largura A largura da imagem
	 * @param altura A altura da imagem
	 * @param tipoTransformacao O tipo desejado na transformação
	 * @return Imagem redimensionada
	 */
	private static Image emPorcentagem(ImageIcon imagem, double largura, double altura, int tipoTransformacao){
		AffineTransform at = AffineTransform.getScaleInstance((double)largura/PORCENTAGEM,(double)altura/PORCENTAGEM);
		BufferedImageOp op = new AffineTransformOp(at,tipoTransformacao);
		BufferedImage source = MyBufferedImage.makeBufferedImage(imagem.getImage(), BufferedImage.TYPE_INT_RGB);
        return op.filter(source, null);
	}
	
	/**
	 * Método emPorcentagemBICUBIC que Transforma em porcentagem tipo BICUBIC
	 * @param imagem A imagem desejada 
	 * @param largura A largura da imagem
	 * @param altura A altura da imagem
	 * @return Imagem redimensionada
	 * @throws RedimensionarException Quando a altura ou largura estao foram do limite (valores obrigatoriamente >0)
	 */
	public static Image emPorcentagemBICUBIC(ImageIcon imagem, double largura, double altura) throws RedimensionarException{
		try{
			return emPorcentagem(imagem,largura,altura,AffineTransformOp.TYPE_BICUBIC);
		} catch(Exception e){
			throw new RedimensionarException("O valor informado (Porcentagem: "+largura+") esta fora do intervalo permintido( Intervalo >0 )!!!");
		}		
	}
	
	/**
	 * Metodo emPorcentagemBILINEAR que Transforma em porcentagem tipo BILINEAR
	 * @param imagem A imagem desejada 
	 * @param largura A largura da imagem
	 * @param altura A altura da imagem
	 * @return Imagem redimensionada
	 * @throws RedimensionarException Quando a altura ou largura estao foram do limite (valores obrigatoriamente >0)
	 */
	public static Image emPorcentagemBILINEAR(ImageIcon imagem, double largura, double altura) throws RedimensionarException{
		try{
			return emPorcentagem(imagem,largura,altura,AffineTransformOp.TYPE_BILINEAR);
		}catch(Exception e){
			throw new RedimensionarException("O valor informado (Porcentagem: "+largura+") esta fora do intervalo permintido( Intervalo >0 )!!!");
		}
	}

	/**
	 * Metodo emPorcentagemNEAREST_NEIGHBOR que Transforma em porcentagem de tipo Nearest_neighbor
	 * @param imagem A imagem desejada 
	 * @param largura A largura da imagem
	 * @param altura A altura da imagem
	 * @return Imagem redimensionada
	 * @throws RedimensionarException Quando a altura ou largura estao foram do limite (valores obrigatoriamente >0)
	 */
	public static Image emPorcentagemNEAREST_NEIGHBOR(ImageIcon imagem, double largura, double altura) throws RedimensionarException{
		try{
			return emPorcentagem(imagem,largura,altura,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		}catch(Exception e){
			throw new RedimensionarException("O valor informado (Porcentagem: "+largura+") esta fora do intervalo permintido( Intervalo >0 )!!!");
		}
	}
		
	/**
	 * Método emPixels que redimensiona a imagem em pixels do tamanho desejado
	 * @param imagem A imagem desejada
	 * @param novaLargura A nova largura da imagem
	 * @param novaAltura A nova altura da imagem
	 * @param tipoTransformacao O tipo desejado para a transformacao
	 * @return A nova imagem
	 * @throws RedimensionarException Quando a altura ou largura estao foram do limite (valores obrigatoriamente >0)
	 */
	private static Image emPixels(ImageIcon imagem, int novaLargura, int novaAltura, int tipoTransformacao) throws RedimensionarException{
		try{
			return emPorcentagem(imagem,((double)novaLargura/imagem.getIconWidth())*100,
					((double)novaAltura/imagem.getIconHeight())*100,tipoTransformacao);
		}catch(Exception e){
			throw new RedimensionarException("O valor informado (A: "+novaAltura+" / L: "+novaLargura+ ") esta fora do intervalo permintido( Intervalo >0 )!!!");
		}
	}
	
	/**
	 * Método emPixelsBICUBIC que Transforma a imagem em pixels tipo BICUBIC
	 * @param imagem A imagem desejada
	 * @param novaLargura A nova largura da imagem
	 * @param novaAltura A nova altura da imagem
	 * @return A imagem modificada
	 * @throws RedimensionarException Quando a altura ou largura estao foram do limite (valores obrigatoriamente >0)
	 */
	public static Image emPixelsBICUBIC(ImageIcon imagem, int novaLargura, int novaAltura) throws RedimensionarException{
		try{
			return emPixels(imagem,novaLargura,novaAltura,AffineTransformOp.TYPE_BICUBIC);
		} catch (Exception e){
			throw new RedimensionarException("O valor informado (A: "+novaAltura+" / L: "+novaLargura+ ") esta fora do intervalo permintido( Intervalo >0 )!!!");
		}
	}

	/**
	 * Método emPixelsBILINEAR que Transforma a imagem em pixels tipo BILINEAR
	 * @param imagem A imagem desejada
	 * @param novaLargura A nova largura da imagem
	 * @param novaAltura A nova altura da imagem
	 * @return A imagem modificada
	 * @throws RedimensionarException Quando a altura ou largura estao foram do limite (valores obrigatoriamente >0)
	 */
	public static Image emPixelsBILINEAR(ImageIcon imagem, int novaLargura, int novaAltura) throws RedimensionarException{
		try{
			return emPixels(imagem,novaLargura,novaAltura,AffineTransformOp.TYPE_BILINEAR);
		}catch(Exception e){
			throw new RedimensionarException("O valor informado (A: "+novaAltura+" / L: "+novaLargura+ ") esta fora do intervalo permintido( Intervalo >0 )!!!");
		}
	}
		
	/**
	 * Método emPixelsNEAREST_NEIGHBOR que Transforma a imagem em pixels tipo NEAREST_NEIGHBOR
	 * @param imagem A imagem desejada
	 * @param novaLargura A nova largura da imagem
	 * @param novaAltura A nova altura da imagem
	 * @return A imagem modificada
	 * @throws RedimensionarException Quando a altura ou largura estao foram do limite (valores obrigatoriamente >0)
	 */
	public static Image emPixelsNEAREST_NEIGHBOR(ImageIcon imagem, int novaLargura, int novaAltura) throws RedimensionarException{
		try{
			return emPixels(imagem,novaLargura,novaAltura,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		} catch(Exception e){
			throw new RedimensionarException("O valor informado (L: "+novaLargura+" / A: "+novaAltura+ ") esta fora do intervalo permintido( Intervalo >0 )!!!");		
		}
	}	
}