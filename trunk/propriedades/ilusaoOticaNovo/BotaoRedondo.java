/*
 * UNIVERSIDADE FEDERAL DE CAMPINA GRANDE
 * CENTRO DE ENGENHARIA EL�TRICA E INFORM�TICA
 * DEPARTAMENTO DE SISTEMAS E COMPUTA��O
 * 
 * DISCIPLINA: PROCESSAMENTO DIGITAL DE IMAGENS
 * PROFESSOR: EUST�QUIO RANGEL
 * ALUNOS: LUCIANA CAVALCANTE DE MENEZES
 *         RICARDO MADEIRA FERNANDES
 * 
 * PROJETO: ILUS�ES DE �TICA.
 */

package ilusaoOticaNovo;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * Classe respons�vel constru��o de um bot�o redondo.
 * Ser� utilizado nas ilus�es de �tica que precisam de c�rculos.
 * @author Luciana Cavalcante de Menezes.
 * @author Ricardo Madeira Fernandes.
 */
public class BotaoRedondo extends JButton {
	Shape shape;

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Cria uma nova inst�ncia da classe BotaoRedondo.
	 * @param label A label do bot�o.
	 */
	public BotaoRedondo(String label) {
		super(label);
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
	}

	/**
	 * Paint component.
	 */
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

		super.paintComponent(g);
	}
	
	/**
	 * Paint border.
	 */
	protected void paintBorder(Graphics g) {

	}
	
	/**
	 * Contains.
	 * @param x Posi��o x.
	 * @param y Posi��o y.
	 */
	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
		}
		return shape.contains(x, y);
	}

}
