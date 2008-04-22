package filtro;

/*
 * ImageFilterOperation
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
import java.util.Map;

import exceptions.ImageProcessingException;

/**
 * Classe responsavel por executar as operacoes dos filtros.
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class ImagemFiltroOperacao implements ImagemOperacaoIF {
	private final String MEAN = "mean";
	private final String GAUSSIAN = "gaussian";
	private final String SOBEL = "sobel";
	private final String LAPLACE = "laplace";
	private final String PREWITT = "prewitt";
	private final String ROBERTS = "roberts";
	private final String MEDIAN = "median";
	private final String MODA = "moda";
	
	@SuppressWarnings("unchecked")
	private Map parameterList;
	private BufferedImage image;
	
	/**
	 * Construtor de um ImageFilterOperation
	 * @param image a ser processada
	 * @param parameterList mapa de parametros que serao utilizados na imagem
	 */
	@SuppressWarnings("unchecked")
	public ImagemFiltroOperacao(BufferedImage image, Map parameterList) {
		this.image = image;
		this.parameterList = parameterList;
	}
	
	/**
	 * Metodo que executa a operacao de filtar a imagem de acordo com os parametros
	 * @return imagem processada pelo filtro na lista de parametros
	 */
	public BufferedImage executeOperation() throws ImageProcessingException {
		String filterId = (String)parameterList.get("nomeFiltro");
		int maskWidth = Integer.parseInt((String)parameterList.get("maskLargura"));
		int maskHeight = Integer.parseInt((String)parameterList.get("maskAltura"));
		
		FiltroIF filter = createFilter(filterId,image);
		FiltroMascara[] masks = new FiltroMascara[image.getData().getNumBands()];
		for(int i = 0; i < masks.length; i++) {
			masks[i] = filter.createMask(maskWidth,maskHeight,i);
		}
		int horizontalDistance = masks[0].getHorizontalDistance();
		int verticalDistance = masks[0].getVerticalDistance();
		
		int countBands = image.getData().getNumBands();
		int imageType = countBands == 1 ? BufferedImage.TYPE_BYTE_GRAY : BufferedImage.TYPE_INT_RGB;
		BufferedImage novaImagem = new BufferedImage(image.getWidth(), image.getHeight(), imageType);
		Raster srcMatrix = image.getData();
		WritableRaster matrixDestino = novaImagem.getRaster();
		for(int i=horizontalDistance; i< (matrixDestino.getWidth() - horizontalDistance); i++) {
			for(int j=verticalDistance; j< (matrixDestino.getHeight() - verticalDistance); j++) {
				@SuppressWarnings("unused") 
				//int[] centralPixel = srcMatrix.getPixel(i,j,new int[]{0,0,0});
				int[] responsePixel = filter.applyFilter(srcMatrix,masks,i,j);
				matrixDestino.setPixel(i,j,responsePixel);
			}
		}
		novaImagem.setData(matrixDestino);
		return novaImagem;
	}
	
	/**
	 * Metodo que cria um filtro de acordo com a string passada como parametro
	 * @param filterId nome do filtro a ser criado
	 * @param image imagem a ser processada
	 * @return filtro criado
	 * @throws ImageProcessingException
	 */
	protected FiltroIF createFilter(String filterId, BufferedImage image) throws ImageProcessingException{
		if(filterId == null)
			throw new ImageProcessingException("Invalid filter type: "+filterId);
		if(filterId.equalsIgnoreCase(MEAN))
			return new FiltroMean(this,image);
		if(filterId.equalsIgnoreCase(GAUSSIAN))
			return new FiltroGaussian(this,image);
		if(filterId.equalsIgnoreCase(SOBEL))
			return new FiltroSobel(this,image);
		if(filterId.equalsIgnoreCase(LAPLACE))
			return new FiltroLaplacian(this,image);
		if(filterId.equalsIgnoreCase(PREWITT))
			return new FiltroPrewitt(this,image);
		if(filterId.equalsIgnoreCase(ROBERTS))
			return new FiltroRoberts(this,image);
		if(filterId.equalsIgnoreCase(MEDIAN))
			return new FiltroMedian(this,image);
		if(filterId.equalsIgnoreCase(MODA))
			return new FiltroModa(this,image);
		throw new ImageProcessingException("Invalid filter type: "+filterId);
	}
	
	/**
	 * Metodo que retorna o parametro de acordo a chave passada
	 * @param paramName chave do mapa
	 * @return parametro de acordo com a chave
	 */
	public Object getParameter(String paramName) {
		return parameterList.get(paramName);
	}

}
