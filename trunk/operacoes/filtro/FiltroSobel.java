package filtro;

/*
 * FiltroSobel
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
 * Classe responsavel por executar as operacoes do filtro sobel.
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class FiltroSobel extends FiltroAbstrato {
	@SuppressWarnings("unused")
	private int pixelsBrancos, pixelsCinzas;
	
	/**
	 * Construtor de um filtro sobel
	 * @param filterOperation operacao a ser realizada
	 * @param image imagem a ser realizada a operacao
	 */
	public FiltroSobel(ImagemFiltroOperacao filterOperation, BufferedImage image) {
		super(filterOperation, image);
	}
	
	/**
	 * Metodo que retorna o valor da mascara
	 * @return 0
	 */
	protected int getMaskValue(int i, int j, int band) {
		return 0;
	}
	
	/**
	 * Metodo que determina a mascara para o filtro
	 * @param width largura do filtro
	 * @param height altura do filtro
	 * @band 
	 * @return mascara criada
	 */
	public FiltroMascara createMask(int width, int height, int band) {
		String direction = (String) getFilterOperation().getParameter("direcao");
		if(direction == null)
			return createXYSobelMask();
		if(direction.equalsIgnoreCase("y"))
			return createVerticalSobelMask();
		if(direction.equalsIgnoreCase("x"))
			return createHorizontalSobelMask();
		return null;
	}
	
	/**
	 * Metodo que cria a mascara para o filtro sobel
	 * @return mascara criada
	 */
	private FiltroMascara createXYSobelMask() {
		int[][] mask = new int[3][3];
		mask[0][0] = -2;
		mask[0][1] = -2;
		mask[0][2] = 0;
		mask[1][0] = -2;
		mask[1][1] = 0;
		mask[1][2] = 2;
		mask[2][0] = 0;
		mask[2][1] = 2;
		mask[2][2] = 2;
		FiltroMascara fmask = new FiltroMascara(mask);
		fmask.setMaskCoeficient(1.0);
		return fmask;
	}
	
	/**
	 * Metodo que cria a mascara horizontal para o filtro sobel
	 * @return mascara criada
	 */
	private FiltroMascara createHorizontalSobelMask() {
		int[][] mask = new int[3][3];
		mask[0][0] = 1;
		mask[0][1] = 2;
		mask[0][2] = 1;
		mask[1][0] = 0;
		mask[1][1] = 0;
		mask[1][2] = 0;
		mask[2][0] = -1;
		mask[2][1] = -2;
		mask[2][2] = -1;
		FiltroMascara fmask = new FiltroMascara(mask);
		fmask.setMaskCoeficient(1.0);
		return fmask;
	}
	
	/**
	 * Metodo que cria a mascara vertical para o filtro sobel
	 * @return mascara criada
	 */
	private FiltroMascara createVerticalSobelMask() {
		int[][] mask = new int[3][3];
		mask[0][0] = -1;
		mask[0][1] = 0;
		mask[0][2] = 1;
		mask[1][0] = -2;
		mask[1][1] = 0;
		mask[1][2] = 2;
		mask[2][0] = -1;
		mask[2][1] = 0;
		mask[2][2] = 1;
		FiltroMascara fmask = new FiltroMascara(mask);
		fmask.setMaskCoeficient(1.0);
		return fmask;
	}
	
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
		for(int i=-horizontalDistance; i<= horizontalDistance; i++) {
			for(int j=-verticalDistance; j<=verticalDistance; j++) {
				int pixelValue = subImage.getPixel((centralX+i),(centralY+j),new int[]{0,0,0})[0];
				double convolutedValue = masks[0].getMaskValue(i,j) * pixelValue;
				unitResult+=  convolutedValue;
			}
		}
		for(int k=0; k<response.length;k++)
			response[k] = normalizePixelValue(unitResult);
		return response;
		
	}

	/**
	 * Metodo que normaliza o valor passado como parametro
	 * @return valor normalizado
	 */
	protected int normalizePixelValue(double unitResult) {
		int limiar;
		try {
			limiar = Integer.parseInt((String) this.getFilterOperation().getParameter("limiar"));
		} catch (NumberFormatException e) {
			limiar = 30;
		}
		double absValue = Math.abs(unitResult);
		if(absValue > limiar & unitResult < 0.0)
			return 0;
		if(absValue > limiar & unitResult > 0.0)
			return 255;
		return 170;
	}
}
