package filtro;

/*
 * FiltroMascara
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

/**
 * Classe que representa uma mascara de um filtro.
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class FiltroMascara {
	private int[][] mask;
	private double coeficient = -1;
	
	/**
	 * Construtor de uma mascara de um filtro
	 * @param mask mascara criada
	 */
	public FiltroMascara(int[][] mask) {
		this.mask = mask;
	}
	
	/**
	 * Metodo que retorna a mascara criada
	 * @return mascara criada
	 */
	public int[][] getMask() {
		return mask;
	}
	
	/**
	 * Metodo que seta a mascara criada
	 * @param mask nava mascara
	 */
	public void setMask(int[][] mask) {
		this.mask = mask;
	}
	
	/**
	 * Metodo que retorna o valor da mascara
	 * @param i
	 * @param j
	 * @return valor da mascara
	 */
	public int getMaskValue(int i, int j) {
		if(mask != null)
			return mask[(i+getHorizontalDistance())][(j+getVerticalDistance())];
		return 0;
	}
	
	/**
	 * Metodo que retorna o tamanho horizontal da mascara
	 * @return tamanho horizontal
	 */
	public int getHorizontalDistance() {
		if(mask != null)
			return mask.length / 2;
		return 0;
	}
	
	/**
	 * Metodo que retorna o tamanho vertical da mascara
	 * @return tamanho vertical
	 */
	public int getVerticalDistance() {
		if(mask != null)
			return mask[0].length / 2;
		return 0;
	}
	
	/**
	 * Metodo que retorna o coeficiente da mascara
	 * @return coeficiente da mascara
	 */
	public double getMaskCoeficient() {
		if(mask == null)
			return 0.0;
		if(coeficient != -1)
			return coeficient;
		double maskCoeficient = 0.0;
		for(int i=0; i<mask.length; i++) {
			for (int j=0; j<mask[0].length; j++) {
				maskCoeficient+= mask[i][j];
			}
		}
		coeficient = 1.0/maskCoeficient;
		return 1.0/maskCoeficient;
		
	}
	
	/**
	 * Metodo que seta o coeficiente da mascara
	 * @param coeficient novo coeficiente
	 */
	public void setMaskCoeficient(double coeficient) {
		this.coeficient = coeficient;
	}
	
	/**
	 * Metodo toString
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		for(int i=0;i< mask.length; i++) {
			for (int j=0; j< mask[0].length; j++)
				buffer.append(mask[i][j]+" ");
			buffer.append("]\n[");
		}
		buffer.append("coficient: "+getMaskCoeficient());
		return buffer.toString();
	}

}
