package filtro;

/*
 * FiltroMedian
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.Arrays;

/**
 * Classe responsavel por executar as operacoes do filtro median.
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class FiltroMedian extends FiltroAbstrato{

	/**
	 * Construtor de um filtro mean
	 * @param filterOperation operacao a ser realizada
	 * @param image imagem a ser realizada a operacao
	 */
	public FiltroMedian(ImagemFiltroOperacao filterOperation,
			BufferedImage image) {
		super(filterOperation, image);
	}
	
	/**
	 * Metodo que aplica o filtro a determinada imagem
	 * @param subImage
	 * @param masks mascara a ser apliacda
	 * @centralX posicao central x
	 * @centralY posicao central Y
	 * @return 
	 */ 
	public int[] applyFilter(Raster subImage, FiltroMascara[] masks, int centralX,
			int centralY) {
		int[] response = new int[masks.length];
		int horizontalDistance = masks[0].getHorizontalDistance();
		int verticalDistance = masks[0].getVerticalDistance();
		for(int k=0; k<response.length; k++) {
			for(int i=-horizontalDistance; i<= horizontalDistance; i++) {
				for(int j=-verticalDistance; j<=verticalDistance; j++) {
					response[k] =  mediana(subImage,masks[0],centralX,centralY,k);
				}
			}
		}
		return response;
	}
	
	/**
	 * Calcula a mediana da matriz da imagem sobre a mascara
	 * @param subImage imagem
	 * @param mask mascara
	 * @param centralX X do pixel central
	 * @param centralY Y do pixel central
	 * @return mediana da matriz da imagem sobre a mascara
	 */
	private int mediana(Raster subImage, FiltroMascara mask, int centralX, int centralY, int k) {
		int[] valores = new int[mask.getMask().length * mask.getMask()[0].length];
		int horizontalDistance = mask.getHorizontalDistance();
		int verticalDistance = mask.getVerticalDistance();
		int valoresIndex = 0;
		for(int i=-horizontalDistance; i<= horizontalDistance; i++) {
			for(int j=-verticalDistance; j<=verticalDistance; j++) {
				valores[valoresIndex++] =  subImage.getPixel((centralX-i),(centralY-j),new int[]{0,0,0})[k];
			}
		}
		Arrays.sort(valores);
		return valores[valores.length/2];
	}

	/**
	 * Metodo que retorna o valor da mascara
	 * @return 1
	 */
	protected int getMaskValue(int i, int j, int band) {
		return 1;
	}

	/**
	 * Metodo que normaliza o valor passado como paremetro
	 * @return valor normalizado
	 */
	protected int normalizePixelValue(double unitResult) {
		return (int) unitResult;
	}
}
