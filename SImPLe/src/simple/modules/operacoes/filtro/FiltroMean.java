package simple.modules.operacoes.filtro;

/*
 * FiltroMean
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
import java.awt.image.BufferedImage;

/**
 * Classe responsavel por executar as operacoes do filtro mean.
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class FiltroMean extends FiltroAbstrato {
	
	//private int[][] mask;
	//private double maskCoeficient;
	
	/**
	 * Construtor de um filtro mean
	 * @param filterOperation operacao a ser realizada
	 * @param image imagem a ser realizada a operacao
	 */
	public FiltroMean(ImagemFiltroOperacao operation, BufferedImage image) {
		super(operation,image);
	}
	
	/**
	 * Metodo que retorna o valor da mascara
	 * @return 0
	 */
	protected int getMaskValue(int i, int j, int band) {
		return 1;
	}


	/**
	 * Metodo que normaliza o valor passado como paremetro
	 * @return valor normalizado
	 */
	protected int normalizePixelValue(double unitResult) {
		return (int)unitResult;
	}

	

}
