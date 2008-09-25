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

import simple.excecoes.ImageProcessingException;

/**
 * Interface para Segmentação
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public interface ImageOperationIF {
	public BufferedImage executeOperation() throws ImageProcessingException;

}
