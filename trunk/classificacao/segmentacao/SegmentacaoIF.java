package segmentacao;

/*
 * SegmentacaoIF
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
import java.awt.image.BufferedImage;

import exceptions.ImageProcessingException;

/**
 * Interface SegmentacaoIF
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public interface SegmentacaoIF {
	
	/**
	 * Metodo que segmenta a imagem passada como parametro
	 * @param image imagem a ser segmentada
	 * @return imagem segmentada
	 */
	public BufferedImage segmentImage(BufferedImage image) throws ImageProcessingException;
}
