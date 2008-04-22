package filtro;

/*
 * FiltroIF
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
import java.awt.image.Raster;

/**
 * Interface filtro.
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public interface FiltroIF {
	
	/**
	 * Recupera a máscara utilizada por um tipo de filtro
	 * @param width largura da mascara
	 * @param height altura da mascara
	 * @return a mascara
	 */
	public FiltroMascara createMask(int width, int height, int band);
	
	/**
	 * Aplica o filtro na vizinhança do pixel
	 * @param subImage vizinhanca do pixel
	 * @param mask mascara a ser aplicada
	 * @param centralX x do pixel central
	 * @param centralY y do pixel central
	 * @return pixel resultante da convolução
	 */
	public int[] applyFilter(Raster subImage, FiltroMascara[] mask, int centralX, int centralY);	

}
