/*
 * Painel Ilusao de Optica
 * 
 * @version 1.0
 * 
 * Date: 03/11/05
 * 
 * Copyright FEDPI all rights reserved
 */

package ilusaoOptica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JLabel;

/**
 * Classe que implementa uma Ilusao de Optica onde o usuario enxerga um dos retangulos
 * cinza maior do que o outro, quando na realidade os dois retangulos sao do mesmo
 * tamanho
 * brancos espalhados pelas
 * @version 1.0 09/11/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class JIlusion3Pane extends JLabel implements IlusaoOptica {
	
	private static final String msg = "Qual dos retangulos cinza é o maior?";
	private static final long serialVersionUID = 1L;
	private static final int altura = 250;
	private static final int largura = 300;
	public Image imagem;
	
	public Color quadradoMenor = Color.lightGray;
	public Color corQuadrado = new Color(0,0,140);
	public Color corQuadrado2 = Color.YELLOW;
	
	
	public JIlusion3Pane(){
		repaint();
	}
	/**
	 * Metodo responsavel pela criacao da Ilusao de Optica, nesse metodo contem o 
	 * algoritmo responsavel pela criacao da Ilusao.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		super.setPreferredSize(new java.awt.Dimension(largura, altura));
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(corQuadrado);
		g2.fillRect(0,0,g2.getClipBounds().width-(g2.getClipBounds().width/2),g2.getClipBounds().height);
		g2.setColor(corQuadrado2);
		g2.fillRect(g2.getClipBounds().width-(g2.getClipBounds().width/2),0,g2.getClipBounds().width-(g2.getClipBounds().width/2),g2.getClipBounds().height);
		g2.setColor(quadradoMenor);
		g2.fillRect(20,20,105,150);
		g2.fillRect(165,20,105,150);
	}
	
	/**
	 * Metodo onde voce recupera o texto necessario para o entendimento da ilusao de optica
	 * @return Mensagem da Ilusao
	 */
	public String getMsg(){
		return msg;
	}

	/**
	 * Metodo que retorna a largura do Painel onde a Ilusao de Optica esta inserida.
	 * @return largura
	 */
	public int getLargura() {
		return largura;
	}

	/**
	 * Metodo que retorna a altura do Painel onde a Ilusao de Optica esta inserida.
	 * @return altura
	 */
	public int getAltura() {
		return altura;
	}
	
	/**
	 * Metodo que retorna o nome da ilusao
	 * @return O nome da ilusao
	 */
	public String getNome(){
		return "Quadrados";
	}
}