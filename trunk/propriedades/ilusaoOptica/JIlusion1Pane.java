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
import java.awt.geom.Ellipse2D;
import javax.swing.JLabel;

/**
 * Classe que implementa uma Ilusao de Optica onde o usuario enxerga pontos pretos ao longos dos pontos 
 * brancos espalhados pelas
 * @version 1.0 09/11/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class JIlusion1Pane extends JLabel implements IlusaoOptica {
	
	private static final String msg = "Conte os pontos pretos!";
	private static final long serialVersionUID = 1L;
	public int LadoQuadrado = 70;
	public int larguraLinha = 10;
	private static final int altura = 500;
	private static final int largura = 500;
	public Image imagem;
	
	public Color background = Color.lightGray;
	public Color corCirculo = Color.WHITE;
	public Color corQuadrado = Color.BLACK;
	
	
	public JIlusion1Pane(){
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
		Ellipse2D circulo = new Ellipse2D.Double();
		
		g2.setColor(background);
		g2.fillRect(0,0,g2.getClipBounds().width,g2.getClipBounds().height);		
    	g2.setColor(corQuadrado);
			
		// Desenha as linhas no Painel
    	for(int x =0; x < largura; x+=LadoQuadrado+larguraLinha){
		    g2.fillRect(x,0,LadoQuadrado,LadoQuadrado);
		    for(int y=0; y <altura;y+=LadoQuadrado+larguraLinha){
		    	g2.fillRect(x,y,LadoQuadrado,LadoQuadrado);
		    }
		}
		
    	// Desenha os circulos Brancos no Painel
		for(int x =LadoQuadrado-5; x < largura; x+=LadoQuadrado+larguraLinha){
		    circulo.setFrame(x,LadoQuadrado-5,20,20);
		    g2.setColor(corCirculo);
			g2.fill(circulo);
		    for(int y=LadoQuadrado-5; y <altura;y+=LadoQuadrado+larguraLinha){
			    circulo.setFrame(x,y,20,20);
			    g2.setColor(corCirculo);
				g2.fill(circulo);
		    }
		}
		
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
		return "Pontos Pretos";
	}
}