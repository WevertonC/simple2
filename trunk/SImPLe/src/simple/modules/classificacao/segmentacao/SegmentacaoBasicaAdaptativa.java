package simple.modules.classificacao.segmentacao;

/*
 * SegmentacaoBasicaAdaptativa
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Iterator;
import java.util.LinkedList;

import simple.modules.propriedades.histograma.Histograma;
import simple.excecoes.ImageProcessingException;

/**
 * SegmentacaoBasicaAdaptativa
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class SegmentacaoBasicaAdaptativa extends SegmentacaoAbstrata {
	@SuppressWarnings("unused")
	private BufferedImage originalImage;
	
	/**
	 * Construtor de uma SegmentacaoBasicaAdaptativa
	 * @param operation
	 */
	public SegmentacaoBasicaAdaptativa(SegmentaoOperacao operation) {
		super(operation);
	}
	/**
	 * Metodo que segmenta a imagem passada como parametro
	 * @param image imagem a ser segmentada
	 * @return imagem segmentada
	 */
	@SuppressWarnings("unchecked")
	public BufferedImage segmentImage(BufferedImage image)
	throws ImageProcessingException {
		String windowDimensionStr = (String)getOperation().getParameter("windowDimension");
		int windowDimension = -1;
		if(windowDimensionStr != null) {
			windowDimension = Integer.parseInt(windowDimensionStr);
		}
		int imageWidth = image.getData().getWidth();
		int imageHeight = image.getData().getHeight();
		LinkedList subImages = null;
		if(windowDimension != -1) {
			subImages = divideImageByWindow(image, windowDimension, imageWidth, imageHeight);
			LinkedList segmentedSubImages = segmentSubImages(subImages);
			return mergeSubImages(segmentedSubImages,image,windowDimension);
		}
		else {
			subImages = autoDivideImage(image, imageWidth, imageHeight);
			LinkedList segmentedSubImages = segmentSubImages(subImages);
			return mergeAdaptativeSubImages(segmentedSubImages,image);
		}
	}
	
	/**
	 * Efetua um merge das subimagens que foram segmentadas por autoDivisao
	 * @param segmentedSubImages subimagens segmentadas
	 * @param image imagem original
	 * @return imagem segmentada
	 */
	@SuppressWarnings("unchecked")
	private BufferedImage mergeAdaptativeSubImages(LinkedList segmentedSubImages, BufferedImage originalImage) {
		int numBands = originalImage.getData().getNumBands();
		int imageType = numBands == 1 ? BufferedImage.TYPE_BYTE_GRAY : BufferedImage.TYPE_INT_RGB;
		BufferedImage resultado = new BufferedImage(originalImage.getWidth(),originalImage.getHeight(),imageType);
		WritableRaster destMatrix = resultado.getRaster();
		@SuppressWarnings("unused") int imageWidth = destMatrix.getWidth();
		@SuppressWarnings("unused") int imageHeight = destMatrix.getHeight();
		while(!segmentedSubImages.isEmpty()) {
			AdaptativeSubImage adaptSubImage = (AdaptativeSubImage) segmentedSubImages.remove();
			Raster srcMatrix = adaptSubImage.getSubImage().getData();
			int xPos = adaptSubImage.getX();
			int yPos = adaptSubImage.getY();
			for(int i = 0; i < srcMatrix.getWidth(); i++) {
				for(int j = 0; j < srcMatrix.getHeight(); j++) {
					int[] pixel = srcMatrix.getPixel(i,j,new int[]{0,0,0});
					destMatrix.setPixel((xPos + i),(yPos + j),pixel);
				}
			}
		}
		resultado.setData(destMatrix);
		return resultado;
	}
	
	/**
	 * Divide a imagem original em tantas subimagens quanto achar necessaria.
	 * @param image Imagem original
	 * @param imageWidth largura da imagem
	 * @param imageHeight altura da imagem
	 * @return
	 */
	@SuppressWarnings({"unchecked","unchecked"})
	private LinkedList autoDivideImage(BufferedImage image, int imageWidth, int imageHeight) {
		LinkedList subImagesReady = new LinkedList();
		LinkedList subImagesNotReady = new LinkedList();
		AdaptativeSubImage firstSubImage = new AdaptativeSubImage(image,0,0,imageWidth,imageHeight);
		subImagesNotReady.addLast(firstSubImage);
		while(!subImagesNotReady.isEmpty()) {
			AdaptativeSubImage subImage = (AdaptativeSubImage) subImagesNotReady.removeFirst();
			if(precisaDividir(subImage)) {
				LinkedList divisionResult = divideImage(subImage);
				addListElements(divisionResult,subImagesNotReady);
			}
			else {
				subImagesReady.addLast(subImage);
			}
		}
		return subImagesReady;
	}
	
	/**
	 * Adiciona os elementos de uma lista em outra lista
	 * @param elementsToInsert
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	private void addListElements(LinkedList elementsToInsert, LinkedList list) {
		Iterator i = elementsToInsert.iterator();
		while(i.hasNext())
			list.addLast(i.next());
	}
	
	/**
	 * Divide uma imagem em quatro partes iguais
	 * @param subImage imagem a dividir
	 * @return colecao com 4 subimagens iguais
	 */
	@SuppressWarnings("unchecked")
	private LinkedList divideImage(AdaptativeSubImage subImage) {
		BufferedImage image = subImage.getSubImage();
		int windowDimension = image.getData().getWidth()/2;
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();
		LinkedList subImages = divideImageByWindow(subImage.getImage(),windowDimension,imageWidth,imageHeight);
		LinkedList result = new LinkedList();
		Iterator i = subImages.iterator();
		int xPos = subImage.getX();
		int yPos = subImage.getY();
		while(i.hasNext()) {
			@SuppressWarnings("unused") BufferedImage nextSubImage = ((AdaptativeSubImage) i.next()).getSubImage();
			AdaptativeSubImage adaptSub = new AdaptativeSubImage(subImage.getImage(),xPos,yPos,windowDimension,windowDimension);
			result.addLast(adaptSub);
			yPos+= windowDimension;
			if(yPos >= subImage.getImage().getHeight()) {
				xPos+=windowDimension;
				yPos = subImage.getY();
			}
		}
		return result;
	}
	
	/**
	 * Se uma imagem possuir varianca de pixel maior que 75.0
	 * será dividida
	 * @param subImage
	 * @return 
	 */
	private boolean precisaDividir(AdaptativeSubImage subImage) {
		Histograma histo = new Histograma(subImage.getSubImage());
		return histo.getDesvioPadrao(0,255,0) > 40;
	}
	
	/**Divide a imagem original em n subimagens de dimensoes quadradas
	 * @param image imagem
	 * @param windowDimension dimensao das suboimagens
	 * @param imageWidth largra da imagem
	 * @param imageHeight altura da imagem
	 */
	@SuppressWarnings({"unchecked","unchecked"})
	private LinkedList divideImageByWindow(BufferedImage image, int windowDimension, int imageWidth, int imageHeight) {
		LinkedList subImages = new LinkedList();
		for(int xPos = 0; xPos < imageWidth; xPos+= windowDimension) {
			for(int yPos = 0; yPos < imageHeight; yPos+= windowDimension) {
				if((imageWidth - xPos) >= windowDimension) { //ainda cabe uma janela inteira na direcao X
					if((imageHeight - yPos) >= windowDimension) { //ainda cabe uma janela inteira na direcao Y
						AdaptativeSubImage subImage = new AdaptativeSubImage(image,xPos,yPos,windowDimension,windowDimension);
						subImages.addLast(subImage);
					}
					else {//Janela cabe na direcao X mas nao em Y
						int residualWindow = (imageHeight - yPos);
						AdaptativeSubImage subImage = new AdaptativeSubImage(image,xPos,yPos,windowDimension,residualWindow);
						subImages.addLast(subImage);
					}
				}
				else {// A janela nao cabe na direcao X
					int residualWindow = (imageWidth - xPos);
					if((imageHeight - yPos) >= windowDimension) {
						AdaptativeSubImage subImage = new AdaptativeSubImage(image,xPos,yPos,residualWindow,windowDimension);
						subImages.addLast(subImage);
					}
					else {//Janela nao cabe na direcao X nem em Y
						int residualWindowY = (imageHeight - yPos);
						AdaptativeSubImage subImage = new AdaptativeSubImage(image,xPos,yPos,residualWindow,residualWindowY);
						subImages.addLast(subImage);
					}
				}
			}
		}
		return subImages;
	}
	
	/**
	 * Efetua o merge das subImagens segmentadas
	 * @param segmentedSubImages lista das subimagens segmentadas
	 * @return Imagem Completa segmentada
	 */
	@SuppressWarnings("unchecked")
	private BufferedImage mergeSubImages(LinkedList segmentedSubImages, BufferedImage originalImage, int windowDimension) {
		int numBands = originalImage.getData().getNumBands();
		int imageType = numBands == 1 ? BufferedImage.TYPE_BYTE_GRAY : BufferedImage.TYPE_INT_RGB;
		BufferedImage resultado = new BufferedImage(originalImage.getWidth(),originalImage.getHeight(),imageType);
		WritableRaster destMatrix = resultado.getRaster();
		int imageWidth = destMatrix.getWidth();
		int imageHeight = destMatrix.getHeight();
		for(int xPos = 0; xPos < imageWidth; xPos+= windowDimension) {
			for(int yPos = 0; yPos < imageHeight; yPos+= windowDimension) {
				BufferedImage subImage = (BufferedImage)((AdaptativeSubImage)segmentedSubImages.remove()).getSubImage();
				Raster srcMatrix = subImage.getData();
				for(int k=0; k<srcMatrix.getWidth(); k++) {
					for(int j=0; j<srcMatrix.getHeight(); j++) {
						int[] pixel = srcMatrix.getPixel(k,j,new int[]{0,0,0});
						destMatrix.setPixel((xPos + k),(yPos + j),pixel);
					}
				}
			}
		}
		resultado.setData(destMatrix);
		return resultado;
	}
	
	/**
	 * @param subImages
	 * @return
	 * @throws ImageProcessingException
	 */
	@SuppressWarnings("unchecked")
	private LinkedList segmentSubImages(LinkedList subImages) throws ImageProcessingException {
		Iterator i = subImages.iterator();
		BasicoThresholding basicSegmentation = new BasicoThresholding(getOperation());
		LinkedList resultSet = new LinkedList();
		while(i.hasNext()) {
			AdaptativeSubImage subImage = (AdaptativeSubImage)i.next();
			@SuppressWarnings("unused") BufferedImage result = basicSegmentation.segmentImage(subImage.getSubImage());
			resultSet.addLast(new AdaptativeSubImage(subImage.getImage(),subImage.getX(),subImage.getY(),subImage.getWidth(),subImage.getHeight()));
		}
		return resultSet;
	}
}
