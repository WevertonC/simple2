package simple.modules.classificacao.segmentacao;

/*
 * SegmentaoOperacao
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
import java.awt.image.BufferedImage;
import java.util.Map;

import simple.excecoes.ImageProcessingException;

/**
 * Classe que representa as operacoes realizadas na segmentacao de imagens
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class SegmentaoOperacao implements ImageOperationIF {
	private final String SEGMENTATION_STRATEGY_BASIC_THRESHOLDING = "limiarizacaoBasica";
	private final String SEGMENTATION_STRATEGY_BASIC_ADAPTATIVE_THRESHOLDING = "limiarizacaoAdaptativa";
	@SuppressWarnings("unchecked")
	private Map parameterList;
	private BufferedImage image;

	/**
	 * Construtor de um SegmentaoOperacao
	 * @param image imagem a ser processda
	 * @param parameterList mapa de parametros
	 */
	@SuppressWarnings("unchecked")
	public SegmentaoOperacao(BufferedImage image, Map parameterList) {
		this.image = image;
		this.parameterList = parameterList;
	}

	/**
	 * Metodo que executa as operacoes de segmentar imagens
	 * @return a imagem processada pelo determinado filtro
	 * @throws ImageProcessingException
	 */
	public BufferedImage executeOperation()
			throws ImageProcessingException {
		String segmentationStrategy = (String) parameterList.get("strategy");
		SegmentacaoIF strategy = createSegmentationStrategy(segmentationStrategy,image);
		return strategy.segmentImage(image);
	}

	/**
	 * Metodo que cria a segmentacao da imagem passada como parametro de acordo com a string
	 * @param segmentationStrategy nome da segmentacao a ser processada
	 * @param image imagem a ser processada
	 * @return segmentacao da imagem
	 */
	protected SegmentacaoIF createSegmentationStrategy(String segmentationStrategy, BufferedImage image)
		throws ImageProcessingException {
		if(segmentationStrategy == null)
			throw new ImageProcessingException("Invalid segmentation strategy: "+segmentationStrategy);
		if(segmentationStrategy.equalsIgnoreCase(SEGMENTATION_STRATEGY_BASIC_THRESHOLDING))
			return new BasicoThresholding(this);
		if(segmentationStrategy.equalsIgnoreCase(SEGMENTATION_STRATEGY_BASIC_ADAPTATIVE_THRESHOLDING))
			return new SegmentacaoBasicaAdaptativa(this);
		throw new ImageProcessingException("Invalid segmentation strategy: "+segmentationStrategy);
	}
	
	/**
	 * Metodo que retorna o parametro de acordo a chave passada
	 * @param key chave do mapa
	 * @return parametro de acordo com a chave
	 */
	public Object getParameter(String key) {
		return parameterList.get(key);
	}

}
