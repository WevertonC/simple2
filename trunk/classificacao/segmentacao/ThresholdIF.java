package segmentacao;

/*
 * ThresholdIF
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Interface ThresholdIF
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public interface ThresholdIF {
	
	/**
	 * Metodo que detecta o ponto inical da imagem
	 * @param image imagem a ser realizada o processamento
	 * @param mapa de parametros
	 * 
	 */
	@SuppressWarnings("unchecked")
	public double detectThreshold(BufferedImage image, Map params);
}
