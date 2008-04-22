package filtro;

/*
 * FiltroAbstrato
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

/**
 * Classe abstrata que implementa as funcoes comuns a todos os filtros.
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public abstract class FiltroAbstrato implements FiltroIF {
	
	private ImagemFiltroOperacao filterOperation;
	@SuppressWarnings("unused")
	private BufferedImage image;
	
	/**
	 * Construtor de um filtro abstrato.
	 * @param filterOperation operacao a ser realizada na imagem
	 * @param image imagem a ser processada
	 */
	public FiltroAbstrato(ImagemFiltroOperacao filterOperation, BufferedImage image) {
		this.filterOperation = filterOperation;
		this.image = image;
	}
	
	/**
	 * Metodo que cria uma mascara com os parametro passados
	 * @param width largura da mascara
	 * @param height altura da mascara
	 * @return mascara criada
	 */
	public FiltroMascara createMask(int width, int height, int band) {
		int[][] mask = new int[width][height];
		int horizontalDistance = width /2;
		int verticalDistance = height /2;
		for(int i=-horizontalDistance; i<= horizontalDistance; i++) {
			for(int j=-verticalDistance; j<= verticalDistance; j++)
				mask[i+horizontalDistance][j+verticalDistance] = getMaskValue(i,j,band);
		}
		return new FiltroMascara(mask);
	}
	
	/**
	 * Metodo que retorna o valor da mascara de acordo com cada filtro
	 * @param i
	 * @param j
	 * @param band
	 * @return valor da mascara
	 */
	protected abstract int getMaskValue(int i, int j, int band);
	
	
	/**
	 * Metodo que aplica o filtro a determinada imagem
	 * @param subImage
	 * @param masks mascara a ser apliacda
	 * @centralX posicao central x
	 * @centralY posicao central Y
	 * @return 
	 */ 
	public int[] applyFilter(Raster subImage, FiltroMascara[] masks, int centralX, int centralY) {
		int[] response = new int[masks.length];
		double unitResult = 0.0;
		int horizontalDistance = masks[0].getHorizontalDistance();
		int verticalDistance = masks[0].getVerticalDistance();
		for(int k = 0; k < response.length; k++) {
			for(int i =-horizontalDistance; i <= horizontalDistance; i++) {
				for(int j=-verticalDistance; j<=verticalDistance; j++) {
					unitResult+= subImage.getPixel((centralX+i),(centralY+j),new int[]{0,0,0})[k] * ((double)(masks[k].getMaskValue(i,j)) * masks[k].getMaskCoeficient());
				}
			}
			response[k] =  normalizePixelValue(unitResult);
			unitResult = 0.0;
		}
		return response;
	}
	

	/**
	 * Metodo que retorna o valor normalizado
	 * @param unitResult a ser normalizado
	 * @return valor normalizado
	 */
	protected abstract int normalizePixelValue(double unitResult);
	
	/**
	 * Metodo que retorna a operacao realizada na imagem
	 * @return operacao realizada na imagem
	 */
	protected ImagemFiltroOperacao getFilterOperation() {
		return filterOperation;
	}
}
