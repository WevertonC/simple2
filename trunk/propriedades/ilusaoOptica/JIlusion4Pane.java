/*
 * Painel de Ilusao de Optica
 * 
 * @version 1.0
 * 
 * Date: 09/11/05
 * 
 * Copyright FEDPI all rights reserved
 */

package ilusaoOptica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

/**
 * Classe que implementa uma Ilusao de Optica onde o usuario enxerga os circulos medios com 
 * tamanhos diferentes quando os circulos tem o mesmo tamanho.
 * @version 1.0 09/11/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class JIlusion4Pane extends JPanel implements IlusaoOptica{
	
	private static final long serialVersionUID = 1L;
	private static final String msg = "O Circulo do centro esquerdo é maior do que o centro direito?";
	private static final int altura = 300;
	private static final int largura = 500;
	public int circuloMenor = 15;
	public int circuloMaior = 45;
	public int circuloMedio = 30;
	public Color background = Color.BLACK;
	public Color corCiruclos = Color.WHITE;
	
	
	/**
	 * Metodo responsavel pela criacao da Ilusao de Optica, nesse metodo contem o 
	 * algoritmo responsavel pela criacao da Ilusao.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);		
		super.setPreferredSize(new java.awt.Dimension(largura, altura));
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(background);
		g2.fillRect(0,0,g2.getClipBounds().width,g2.getClipBounds().height);		
		g2.setColor(corCiruclos);
		Ellipse2D menor = new Ellipse2D.Double();
		Ellipse2D medio = new Ellipse2D.Double();
		Ellipse2D maior = new Ellipse2D.Double();
		
		// Lado Esquerdo da figura (Circulo Medio envolto por Circulos Menores)
		menor.setFrame(152,113,circuloMenor,circuloMenor);
		g2.fill(menor);
		menor.setFrame(82,113,circuloMenor,circuloMenor);
		g2.fill(menor);
		menor.setFrame(102,85,circuloMenor,circuloMenor);
		g2.fill(menor);
		menor.setFrame(132,85,circuloMenor,circuloMenor);
		g2.fill(menor);
		menor.setFrame(102,143,circuloMenor,circuloMenor);
		g2.fill(menor);
		menor.setFrame(132,143,circuloMenor,circuloMenor);
		g2.fill(menor);
		medio.setFrame(110,105,circuloMedio,circuloMedio);
		g2.fill(medio);
		
		// Lado Direito da figura (Circulo Medio envolto por Circulos Maiores)
		maior.setFrame(402,100,circuloMaior,circuloMaior);
		g2.fill(maior);
		maior.setFrame(304,100,circuloMaior,circuloMaior);
		g2.fill(maior);
		maior.setFrame(329,55,circuloMaior,circuloMaior);
		g2.fill(maior);
		maior.setFrame(379,55,circuloMaior,circuloMaior);
		g2.fill(maior);
		maior.setFrame(329,145,circuloMaior,circuloMaior);
		g2.fill(maior);
		maior.setFrame(379,145,circuloMaior,circuloMaior);
		g2.fill(maior);
		medio.setFrame(360,105,circuloMedio,circuloMedio);
		g2.fill(medio);
		
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
		return "Circulos";
	}
}
