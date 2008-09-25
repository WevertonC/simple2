package simple.modules.operacoes.filtro;

/*
 * FiltraImagem
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import simple.excecoes.ImageProcessingException;

/**
 * Classe responsavel por aplicar os filtros na imagem
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class FiltraImagem {
	
	private static final String MEAN = "mean";
	private static final String GAUSSIAN = "gaussian";
	private static final String SOBEL = "sobel";
	private static final String LAPLACE = "laplace";
	private static final String PREWITT = "prewitt";
	private static final String ROBERTS = "roberts";
	private static final String MEDIAN = "median";
	private static final String MODA = "moda";
	
	@SuppressWarnings("unchecked")
	private static  Map mapa;
	
	/**
	 * Metodo que aplica o filtro gaussian na imagem passada como parametro
	 * @param image imagem a ser processada pelo filtro
	 * @param maskLargura largura da mascara
	 * @param maskAltura alturada mascara
	 * @param desvio desvio
	 * @return imagem com o filtro gaussian aplicada
	 * @throws ImageProcessingException
	 */
	@SuppressWarnings("unchecked")
	public static BufferedImage gaussian(BufferedImage image,
			int maskLargura, int maskAltura, double desvio) throws ImageProcessingException {
		mapa = new HashMap();
		mapa.put("nomeFiltro",GAUSSIAN);
		mapa.put("maskLargura",Integer.toString(maskLargura));
		mapa.put("maskAltura",Integer.toString(maskAltura));
		mapa.put("desvio",Double.toString(desvio));
		return new ImagemFiltroOperacao(image,mapa).executeOperation();
	}
	
	/**
	 * Metodo que aplica o filtro mean na imagem passada como parametro
	 * @param image imagem a ser processada pelo filtro
	 * @param maskLargura largura da mascara
	 * @param maskAltura alturada mascara
	 * @return imagem com o filtro mean aplicada
	 * @throws ImageProcessingException
	 */
	@SuppressWarnings("unchecked")
	public static BufferedImage mean(BufferedImage image, 
			int maskLargura, int maskAltura) throws ImageProcessingException {
		mapa = new HashMap();
		mapa.put("nomeFiltro",MEAN);
		mapa.put("maskLargura",Integer.toString(maskLargura));
		mapa.put("maskAltura",Integer.toString(maskAltura));
		return new ImagemFiltroOperacao(image,mapa).executeOperation();
	}
	
	/**
	 * Metodo que aplica o filtro sobel na imagem passada como parametro
	 * @param image imagem a ser processada pelo filtro
	 * @param direcao direcao de aplicao do filtro, horizontal(x) ou vertical(y)
	 * @return imagem com o filtro sobel aplicada
	 * @throws ImageProcessingException
	 */
	@SuppressWarnings("unchecked")
	public static BufferedImage sobel(BufferedImage image, String direcao) throws ImageProcessingException {
		mapa = new HashMap();
		mapa.put("nomeFiltro",SOBEL);
		mapa.put("maskLargura",Integer.toString(3));
		mapa.put("maskAltura",Integer.toString(3));
		mapa.put("direcao",direcao);
		return new ImagemFiltroOperacao(image,mapa).executeOperation();	
	}
	
	/**
	 * Metodo que aplica o filtro laplace na imagem passada como parametro
	 * @param image imagem a ser processada pelo filtro
	 * @return imagem com o filtro laplace aplicada
	 * @throws ImageProcessingException
	 */
	@SuppressWarnings("unchecked")
	public static BufferedImage laplace(BufferedImage image) throws ImageProcessingException {
		mapa = new HashMap();
		mapa.put("nomeFiltro",LAPLACE);
		mapa.put("maskLargura",Integer.toString(3));
		mapa.put("maskAltura",Integer.toString(3));
		return new ImagemFiltroOperacao(image,mapa).executeOperation();
	}
	
	/**
	 * Metodo que aplica o filtro prewitt na imagem passada como parametro
	 * @param image imagem a ser processada pelo filtro
	 * @return imagem com o filtro prewitt aplicada
	 * @throws ImageProcessingException
	 */
	@SuppressWarnings("unchecked")
	public static BufferedImage prewitt(BufferedImage image) throws ImageProcessingException {
		mapa = new HashMap();
		mapa.put("nomeFiltro",PREWITT);
		mapa.put("maskLargura",Integer.toString(3));
		mapa.put("maskAltura",Integer.toString(3));
		return new ImagemFiltroOperacao(image,mapa).executeOperation();
	}
	/**
	 * Metodo que aplica o filtro prewitt na imagem passada como parametro
	 * @param image imagem a ser processada pelo filtro
	 * @return imagem com o filtro prewitt aplicada
	 * @throws ImageProcessingException
	 */
	@SuppressWarnings("unchecked")
	public static BufferedImage roberts(BufferedImage image) throws ImageProcessingException {
		mapa = new HashMap();
		mapa.put("nomeFiltro",ROBERTS);
		mapa.put("maskLargura",Integer.toString(3));
		mapa.put("maskAltura",Integer.toString(3));
		return new ImagemFiltroOperacao(image,mapa).executeOperation();
	}
	
	/**
	 * Metodo que aplica o filtro median na imagem passada como parametro
	 * @param image imagem a ser processada pelo filtro
	 * @return imagem com o filtro median aplicada
	 * @throws ImageProcessingException
	 */
	@SuppressWarnings("unchecked")
	public static BufferedImage median(BufferedImage image) throws ImageProcessingException {
		mapa = new HashMap();
		mapa.put("nomeFiltro",MEDIAN);
		mapa.put("maskLargura",Integer.toString(3));
		mapa.put("maskAltura",Integer.toString(3));
		return new ImagemFiltroOperacao(image,mapa).executeOperation();	
	}	
	
	/**
	 * Metodo que aplica o filtro median na imagem passada como parametro
	 * @param image imagem a ser processada pelo filtro
	 * @return imagem com o filtro median aplicada
	 * @throws ImageProcessingException
	 */
	@SuppressWarnings("unchecked")
	public static BufferedImage moda(BufferedImage image) throws ImageProcessingException {
		mapa = new HashMap();
		mapa.put("nomeFiltro",MODA);
		mapa.put("maskLargura",Integer.toString(3));
		mapa.put("maskAltura",Integer.toString(3));
		return new ImagemFiltroOperacao(image,mapa).executeOperation();	
	}	
}
