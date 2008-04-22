/*
 * Interface Ilusao de Optica
 * 
 * @version 1.0
 * 
 * Date: 09/11/05
 * 
 * Copyright FEDPI all rights reserved
 */

package ilusaoOptica;

import java.awt.Graphics;

/**
 * Interface para ilusoes de Opticas, trata os diferentes tipos como um unico tipo.
 * @version 1.0 09/11/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public interface IlusaoOptica {
	
	/**
	 * Metodo que cria a Ilusao de Optica (algoritmo)
	 * @param g
	 */
	public void paintComponent(Graphics g);
	
	/**
	 * Metodo que retorna a mensagem daS Ilusao.
	 * @return msg
	 */
	public String getMsg();
	
	/**
	 * Metodo que retorna a largura do Painel onde a Ilusao de Optica esta inserida.
	 * @return largura
	 */
	public int getLargura();
	
	/**
	 * Metodo que retorna a altura do Painel onde a Ilusao de Optica esta inserida.
	 * @return altura
	 */
	public int getAltura();
	
	/**
	 * Metodo que retorna o nome do Painel onde a Ilusao de Optica esta inserida.
	 * @return altura
	 */
	public String getNome();
	

}
