package simple.modules.classificacao.segmentacao;

/*
 * SegmentaImagem
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import simple.manipulacoes.util.MyBufferedImage;

import simple.excecoes.ImageProcessingException;

/**
 * Classe responsavel por aplicar a segmentacao nas imagens.
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class SegmentarImagem {
	
	private static final String SEGMENTATION_STRATEGY_BASIC_THRESHOLDING = "limiarizacaoBasica";
	private static final String SEGMENTATION_STRATEGY_BASIC_ADAPTATIVE_THRESHOLDING = "limiarizacaoAdaptativa";
	
	@SuppressWarnings("unchecked")
	private static  Map mapa;
	
	/**
	 * Metodo que aplica a segmentacao basica na imagem passada como parametro
	 * @param image imagem a ser aplicada a segmentacao
	 * @param limiar limiar
	 * @return a imagem com a segmentacao basica
	 * @throws ImageProcessingException
	 */
	@SuppressWarnings("unchecked")
	public static Image segmentacaoGlobal(BufferedImage image, String limiar) throws ImageProcessingException {
		mapa = new HashMap();
		mapa.put("strategy",SEGMENTATION_STRATEGY_BASIC_THRESHOLDING);
		mapa.put("limiar",limiar);
		return MyBufferedImage.makeImage(new SegmentaoOperacao(image,mapa).executeOperation());
	}
	
	/**
	 * Metodo que aplica a segmentacao basica adaptativa na imagem passada como parametro 
	 * @param image imagem a ser aplicada a segmentacao
	 * @param dimensao dimensao da janela
	 * @return a imagem com a segmentacao basica adaptativa
	 * @throws ImageProcessingException
	 */
	@SuppressWarnings("unchecked")
	public static Image segmentacaoAdaptativa(BufferedImage image, String dimensao) throws ImageProcessingException {
		mapa = new HashMap();
		mapa.put("strategy",SEGMENTATION_STRATEGY_BASIC_ADAPTATIVE_THRESHOLDING);
		mapa.put("windowDimension",dimensao);
		return MyBufferedImage.makeImage(new SegmentaoOperacao(image,mapa).executeOperation());
	}	
}