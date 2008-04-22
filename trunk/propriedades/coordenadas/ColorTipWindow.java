package coordenadas;
/*
 * Color Tip Window
 * 
 * @version 1.0
 * 
 * Date: 03/11/05
 * 
 * Copyright FEDPI all rights reserved
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.BevelBorder;

/**
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 *
 * Classe que cria uma janela para mostrar as coordenadas x e y de um ponto e o RGB
 * do ponto
 * 
 */
public class ColorTipWindow extends JWindow {
	
	private static final long serialVersionUID = 1L;
	private JPanel colorPanel;
	JLabel coords, colors;
	
	/**
	 * Construtor da Classe ColorTipWindow
	 */
	public ColorTipWindow() {
		super();
		this.setSize(140,60);
		colorPanel = new JPanel();
		colorPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
		coords = new JLabel();
		colors = new JLabel();
		BorderLayout layout = new BorderLayout();
		this.getContentPane().setLayout(layout);
		this.getContentPane().add(colorPanel,BorderLayout.NORTH);
		this.getContentPane().add(coords,BorderLayout.CENTER);
		this.getContentPane().add(colors,BorderLayout.SOUTH);
	}

	/**
	 * Modifica a cor da janela
	 * @param color A nova cor da janela
	 */
	public void setColor(Color color){
		colorPanel.setBackground(color);
		colorPanel.repaint();
	}
	
	/**
	 * Modifica as coordenadas da janela
	 * @param p O ponto que comtem as novas coordenadas
	 */
	public void setCoords(Point p){
		coords.setText(" (X = "+p.x+",Y = "+p.y+")");
		coords.repaint();
		colors.setText(" (R = "+colorPanel.getBackground().getRed()+",G = "+colorPanel.getBackground().getGreen()+",B = "+colorPanel.getBackground().getBlue()+")");
		colors.repaint();
	}

}
