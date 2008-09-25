package simple.modules.operacoes.filtro;

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
import java.util.HashMap;

/**
 * Classe responsavel por executar as operacoes do filtro median.
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class FiltroModa extends FiltroAbstrato {

	/**
	 * Construtor de um filtro mean
	 * @param filterOperation operacao a ser realizada
	 * @param image imagem a ser realizada a operacao
	 */
	public FiltroModa(ImagemFiltroOperacao filterOperation,BufferedImage image) {
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
					response[k] =  moda(subImage,masks[0],centralX,centralY,k);
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
	private int moda(Raster subImage, FiltroMascara mask, int centralX, int centralY, int k) {
		int[] valores = new int[mask.getMask().length * mask.getMask()[0].length];
		int horizontalDistance = mask.getHorizontalDistance();
		int verticalDistance = mask.getVerticalDistance();
		int valoresIndex = 0;
		for(int i=-horizontalDistance; i<= horizontalDistance; i++) {
			for(int j=-verticalDistance; j<=verticalDistance; j++) {
				valores[valoresIndex++] =  subImage.getPixel((centralX-i),(centralY-j),new int[]{0,0,0})[k];
			}
		}
		//Arrays.sort(valores);
		return maiorOcorrencia(valores);
	}

	private static int maiorOcorrencia(int valores[]){
		HashMap<Integer, Integer> ocorrencia = new HashMap<Integer, Integer>();
		for (int i = 0; i < valores.length; i++) {
			if(ocorrencia.containsKey(valores[i])){
				int valor = ocorrencia.get(valores[i])+1;
				ocorrencia.put(valores[i], valor);
			} 
			else {
				ocorrencia.put(valores[i], 1);
			}
		}
		int maior = ocorrencia.get(valores[0]);
		int chave = 0;
		for (int i = 1; i < valores.length; i++) {
			if(ocorrencia.get(valores[i]) > maior){
				chave = i;
				maior = ocorrencia.get(valores[i]);
			}
		}
		return valores[chave];
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
