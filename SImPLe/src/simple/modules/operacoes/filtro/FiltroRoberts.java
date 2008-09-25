package simple.modules.operacoes.filtro;

/*
 * FiltroPrewitt
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
import java.awt.image.BufferedImage;

/**
 * Classe responsavel por executar as operacoes do filtro roberts.
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class FiltroRoberts extends FiltroAbstrato {
	
	/**
	 * Construtor de um filtro roberts
	 * @param filterOperation operacao a ser realizada
	 * @param image imagem a ser realizada a operacao
	 */
	public FiltroRoberts(ImagemFiltroOperacao filterOperation,
			BufferedImage image) {
		super(filterOperation, image);
	}
	
	/**
	 * Metodo que cria a mascara para o filtro roberts
	 * @param width largura do filtro
	 * @param height altura do filtro
	 * @band 
	 * @return mascara criada
	 */
	public FiltroMascara createMask(int width, int height, int band) {
		int[][] mask = new int[3][3];
	
		mask[0][0] = 0;
		mask[0][1] = 1;
		mask[1][0] = -1;
		mask[1][1] = 0;
		mask[1][2] = 1;
		mask[2][0] = 1;
		mask[2][1] = 1;
		mask[2][2] = 1;
				
		FiltroMascara fmask = new FiltroMascara(mask);
		fmask.setMaskCoeficient(1.0);
		return fmask;
	}
	
	/**
	 * Metodo que retorna o valor da mascara
	 * @return 0
	 */
	protected int getMaskValue(int i, int j, int band) {
		return 0;
	}

	/**
	 * Metodo que normaliza o valor passado como paremetro
	 * @return valor normalizado
	 */
	protected int normalizePixelValue(double unitResult) {
		return (int) unitResult;
	}

}
