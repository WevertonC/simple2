package simple.manipulacoes.util;

/*
 * MyImage
 * 
 * @version 1.0
 * 
 * Date: 28/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.util.ArrayList;

import javax.media.jai.InterpolationBilinear;
import javax.media.jai.JAI;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import simple.excecoes.ZoomException;

/**
 * Classe que representa a nossa Imagem
 * @version 1.0 20/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */ 
@SuppressWarnings("unused")
public class MyImage extends ImageIcon {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<MyObject> listaDesfazer, listaRefazer;
	private RenderedImage originalImage;
	private RenderedImage scaledImage;
	private float zoomX, zoomY;
	private Image image;
	private boolean primeiraVez;
	private String dadosDaImagem;	
	private int contadorDesfazer;
	private int contadorRefazer;
		  	
	/**
	 * Construtor da classe com uma Image
	 * @param image A imagem desejada
	 * @throws ZoomException Nunca sera lancado nesse caso
	 */
	public MyImage(Image image){
		super(image);
		this.image = image;
		listaDesfazer = new ArrayList<MyObject>();
		listaRefazer = new ArrayList<MyObject>();
		originalImage = MyBufferedImage.makeBufferedImage(image);
		dadosDaImagem = getColorSpaceName(originalImage.getColorModel().getColorSpace()) + 
						" de " + originalImage.getColorModel().getPixelSize()+ " bits";
		primeiraVez = true;
		contadorDesfazer = 0;
		contadorRefazer = 0;
		try {
			setZoom(1.0f, 1.0f);
		} catch (ZoomException e) {}
	}
	/**
	 * Construtor da classe com um caminho da imagem
	 * @param caminho A imagem desejada
	 * @throws ZoomException Nunca sera lancado nesse caso
	 */
	public MyImage(String caminho){
		super(caminho);
		this.image = super.getImage();
		listaDesfazer = new ArrayList<MyObject>();
		listaRefazer = new ArrayList<MyObject>();
		originalImage = MyBufferedImage.makeBufferedImage(new ImageIcon(caminho).getImage());
		dadosDaImagem = getColorSpaceName(originalImage.getColorModel().getColorSpace()) + 
						" de " + originalImage.getColorModel().getPixelSize()+ " bits";
		primeiraVez = true;
		contadorDesfazer = 0;
		contadorRefazer = 0;
		try {
			setZoom(1.0f, 1.0f);
		} catch (ZoomException e) {}
	}
	
	public MyImage(){
		
	}
	
	/**
	 * Metodo setZoom que opera o Zoom da imagem
	 * @param valueX O fator de Zoom nas coordenadas x
	 * @param valueY O fator de Zoom nas coordenadas y
	 * @throws ZoomException Caso o valor esteja fora do limite (0 < valores obrigatoriamente < 1600)
	 */
	public void setZoom(float valueX, float valueY) throws ZoomException {
		if(zoomX+valueX < 0.1 || zoomY+valueY < 0.1){
			throw new ZoomException("Zoom com Valor Invalido (0 < valores obrigatoriamente <= 400)!!");
		}
		this.zoomX += valueX;
		this.zoomY += valueY;				
		update();
	}
	
	/**
	 * Metodo setZoom que opera o Zoom da imagem
	 * @param value O fator de Zoom nas coordenadas da imagem
	 * @throws ZoomException Caso o valor esteja fora do limite (0 < valores obrigatoriamente < 1600)
	 */
	public void setZoom(float value) throws ZoomException{
		this.zoomX = ((float)((double)zoomX/(100/value)));
		this.zoomY = ((float)((double)zoomY/(100/value)));
		update();
	}
	
	/**
	 * Metodo update que modifica a imagem com os zoom
	 * @throws ZoomException Caso o valor esteja fora do limite (0 < valores obrigatoriamente < 1600)
	 */
	private void update() throws ZoomException {
		try{
			if (originalImage != null) {
				ParameterBlock pb = new ParameterBlock();
				pb.addSource(originalImage);
				pb.add(zoomX);
				pb.add(zoomY);
				pb.add(0.0f);
				pb.add(0.0f);
				pb.add(new InterpolationBilinear());
				scaledImage = JAI.create("SCALE", pb);
				if(contadorDesfazer < 6) {
					listaDesfazer.add(new MyObject(scaledImage,new float[]{zoomX,zoomY}));
					contadorDesfazer++;
				}
				else {
					listaDesfazer.remove(0);
					listaDesfazer.add(new MyObject(scaledImage,new float[]{zoomX,zoomY}));
					contadorDesfazer = 6;
				}
				if(primeiraVez)listaRefazer.add(new MyObject(scaledImage,new float[]{zoomX,zoomY}));
				primeiraVez = false;
				//originalImage = scaledImage;
				//this.zoomX = 1.0f;
				//this.zoomY = 1.0f;
			}
		}catch(Exception e){
			desfazer();
			throw new ZoomException("Zoom com Valor Invalido (0 < valores obrigatoriamente <= 400)!!");
		}catch(java.lang.OutOfMemoryError error){
			JOptionPane.showMessageDialog(null,"Estouro de memoria, por favor reinicie o aplicativo.","ERRO INTERNO",JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Metodo setImage que muda a imagem corrente por uma RenderedImage
	 * @param image A nova imagem
	 * @throws ZoomException Nunca sera lancado nesse caso
	 */
	public void setImage(RenderedImage image){
		try{
			this.image = MyBufferedImage.makeImage(image);
			this.originalImage = image;
			scaledImage = originalImage;
			if(contadorDesfazer < 6) {
				listaDesfazer.add(new MyObject(scaledImage,new float[]{zoomX,zoomY}));
				contadorDesfazer++;
			}
			else {
				listaDesfazer.remove(0);
				listaDesfazer.add(new MyObject(scaledImage,new float[]{zoomX,zoomY}));
				contadorDesfazer = 6;
			}
		}catch (OutOfMemoryError e) {
			JOptionPane.showMessageDialog(null,"Estouro de memoria, por favor reinicie o aplicativo.","ERRO INTERNO",JOptionPane.ERROR_MESSAGE);
		}		
	}
	/**
	 * Metodo SetIMage que muda a imagem corrente por uma Image
	 * @param image A nova imagem
	 */
	public void setImage(Image image) {
		try{
			this.image = image;
			originalImage = MyBufferedImage.makeBufferedImage(image);
			scaledImage = originalImage;
			if(contadorDesfazer < 6) {
				listaDesfazer.add(new MyObject(scaledImage,new float[]{zoomX,zoomY}));
				contadorDesfazer++;
			}
			else {
				listaDesfazer.remove(0);
				listaDesfazer.add(new MyObject(scaledImage,new float[]{zoomX,zoomY}));
				contadorDesfazer = 6;
			}	
		} catch (OutOfMemoryError e) {
			JOptionPane.showMessageDialog(null,"Estouro de memoria, por favor reinicie o aplicativo.","ERRO INTERNO",JOptionPane.ERROR_MESSAGE);
		}	
	}
	/**
	 * Metodo desfazer que muda a imagem corrente com a modificacao anterior
	 * @return A imagem anterior
	 */	
	public RenderedImage desfazer() {
		if(listaDesfazer.size() > 1){
			if(contadorRefazer < 6) {
				listaRefazer.add(0,listaDesfazer.remove(listaDesfazer.size()-1));
				contadorRefazer++;
				contadorDesfazer--;
			}
			else {
				listaRefazer.remove(listaRefazer.remove(listaRefazer.size()-1));
				listaRefazer.add(0,listaDesfazer.remove(listaDesfazer.size()-1));
				contadorRefazer = 6;
				contadorDesfazer--;
			}			
		}
		else contadorDesfazer = 1;
		MyObject objeto = listaDesfazer.get(listaDesfazer.size()-1);
		scaledImage = objeto.getRenderedImage();
		originalImage = scaledImage;
		float[] array = objeto.getArray();
		zoomX = array[0];
		zoomY = array[1];		
		return scaledImage;
	}
	/**
	 * Metodo refazer que muda a imagem corrente com a nova modificação 
	 * @return A imagem modificacda
	 */	
	public RenderedImage refazer(){
		MyObject objeto = null;
		if(listaRefazer.size() > 1){
			objeto = listaRefazer.get(0);
			scaledImage = objeto.getRenderedImage();
			originalImage = scaledImage;
			float[] array = objeto.getArray();
			zoomX = array[0];
			zoomY = array[1];
		}
		if(listaRefazer.size() > 1) {			
			if(contadorDesfazer < 6) {
				listaDesfazer.add(listaRefazer.remove(0));
				contadorDesfazer++;
				contadorRefazer--;
			}
			else {
				listaDesfazer.remove(0);
				listaDesfazer.add(listaRefazer.remove(0));
				contadorDesfazer = 6;
				contadorRefazer--;
			}	
		}
		else contadorRefazer = 1;
		return scaledImage;
	}
	/**
	 * Metodo getRenderedImage que retorna a imagem com a ultima modificacao
	 * @return A imagem modificada
	 */
	public RenderedImage getRenderedImage(){
		return scaledImage;
	}
	/**
	 * Metodo getImagem que retorna a imagem corrente 
	 * @return A image corrente
	 */
	public Image getImage() {
		return MyBufferedImage.makeImage(scaledImage);
	}
	/**
	 * Metodo getAltura que retorna a altura da imagem corresnte
	 * @return O valor da altura da imagem corrente
	 */	
	public int getAltura(){
		return this.image.getHeight(null);
	}
	/**
	 * Metodo getLargura que retorna a largura da imagem corresnte
	 * @return O valor da largura da imagem corrente
	 */	
	public int getLargura(){
		return this.image.getWidth(null);
	}
	/**
	 * Metodo que retorna os dados da imagem, numero de Bits e o tipo do modelo de cores
	 * @return O numero de Bits e o tipo do modelo de cores da imagem
	 */
	public String getdadosDaImagem(){
		return dadosDaImagem;
	}	
	/**
	 * Metodo que determina o tipo do modelo de cores da imagem
	 * @param colorSpace O espaco de cores de imagem
	 * @return O nome correspondente do espaco de cores
	 */
	private static String getColorSpaceName(ColorSpace colorSpace) {
		switch (colorSpace.getType()) {
		case ColorSpace.CS_CIEXYZ:
			return "CIEXYZ";
		case ColorSpace.CS_GRAY:
			return "Linear Gray";
		case ColorSpace.CS_LINEAR_RGB:
			return "Linear RGB";
		case ColorSpace.CS_PYCC:
			return "Photo YCC";
		case ColorSpace.CS_sRGB:
			return "sRGB";
		case ColorSpace.TYPE_2CLR:
			return "2 Componentes Genéricas";
		case ColorSpace.TYPE_3CLR:
			return "3 Componentes Genéricas";
		case ColorSpace.TYPE_4CLR:
			return "4 Componentes Genéricas";
		case ColorSpace.TYPE_5CLR:
			return "5 Componentes Genéricas";
		case ColorSpace.TYPE_6CLR:
			return "6 Componentes Genéricas";
		case ColorSpace.TYPE_7CLR:
			return "7 Componentes Genéricas";
		case ColorSpace.TYPE_8CLR:
			return "8 Componentes Genéricas";
		case ColorSpace.TYPE_9CLR:
			return "9 Componentes Genéricas";
		case ColorSpace.TYPE_ACLR:
			return "10 Componentes Genéricas";
		case ColorSpace.TYPE_BCLR:
			return "11 Componentes Genéricas";
		case ColorSpace.TYPE_CCLR:
			return "12 Componentes Genéricas";
		case ColorSpace.TYPE_CMY:
			return "CMY";
		case ColorSpace.TYPE_CMYK:
			return "CMYK";
		case ColorSpace.TYPE_DCLR:
			return "13 Componentes Genéricas";
		case ColorSpace.TYPE_ECLR:
			return "14 Componentes Genéricas";
		case ColorSpace.TYPE_FCLR:
			return "15 Componentes Genéricas";
		case ColorSpace.TYPE_GRAY:
			return "GRAY";
		case ColorSpace.TYPE_HLS:
			return "HLS";
		case ColorSpace.TYPE_HSV:
			return "HSV";
		case ColorSpace.TYPE_Lab:
			return "Lab";
		case ColorSpace.TYPE_Luv:
			return "Luv";
		case ColorSpace.TYPE_RGB:
			return "RGB";
		case ColorSpace.TYPE_XYZ:
			return "XUZ";
		case ColorSpace.TYPE_YCbCr:
			return "YCbCr";
		case ColorSpace.TYPE_Yxy:
			return "Yxy";
		default:
			return "Espaço de cores não definido";
		}
	}
}