package ilusaoOptica;

/*
 * Painel de Ilusao de Optica
 * 
 * @version 1.0
 * 
 * Date: 09/11/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


/**
 * Classe que implementa uma Ilusao de Optica onde o usuario enxerga linhas inclinadas quando 
 * as linhas sao realmente horizontais paralelas.
 * @version 1.0 09/11/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class JIlusion2Pane extends JPanel implements IlusaoOptica{
	
	private static final long serialVersionUID = 1L;
	private static final String msg = "São linhas horizontais paralelas? Ou elas se incliam?";
	private static final int altura = 194;
	private static final int largura = 519;
	public int ladoQuadrado = 26;
	public Color background = Color.WHITE;
	public Color corQuadrado = Color.BLACK;
	public Color corLinha = Color.RED;
	
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

		int deslocamento = 0;
		int fator = 1;
		
		for (int y = 0; y < altura; y+=ladoQuadrado) {		
			g2.setColor(corQuadrado);
			for (int x = 0; x < largura; x+=2*ladoQuadrado) {
				g2.fillRect(x+deslocamento,y,ladoQuadrado,ladoQuadrado);				
			}
			g2.setColor(corLinha);
			g2.drawLine(0,y,g2.getClipBounds().width,y);
			if ( (y % 3 == 0) && (y!=0))
				fator = fator*(-1);
			deslocamento+=fator*ladoQuadrado/3;
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
		return "Linhas Paralelas";
	}
}
