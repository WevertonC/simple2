package segmentacao;

/*
 * Thresholding
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
 * Interface SegmentacaoIF
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class Thresholding {
	private final String STRATEGY_BASIC = "basic";

	/**
	 * Construtor de um Thresholding
	 */
	public Thresholding() {
	}
	
	@SuppressWarnings("unchecked")
	public double detectThreshold(BufferedImage image, Map params, String strategy) {
		ThresholdIF strategyAlgorithm = createDetectStrategy(strategy);
		if(strategyAlgorithm != null)
			return strategyAlgorithm.detectThreshold(image,params);
		return 0.0;
	}

	/**
	 * Metodo que determina a estrategia a ser seguida na segmentacao
	 * @param strategy a ser seguida
	 * @return estrategia seguida
	 */
	private ThresholdIF createDetectStrategy(String strategy) {
		if(strategy.equalsIgnoreCase(STRATEGY_BASIC))
			return new BasicoThreshold();
		return null;
	}

}
