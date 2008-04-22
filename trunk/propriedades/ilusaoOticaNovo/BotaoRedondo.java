/*
 * UNIVERSIDADE FEDERAL DE CAMPINA GRANDE
 * CENTRO DE ENGENHARIA ELÉTRICA E INFORMÁTICA
 * DEPARTAMENTO DE SISTEMAS E COMPUTAÇÃO
 * 
 * DISCIPLINA: PROCESSAMENTO DIGITAL DE IMAGENS
 * PROFESSOR: EUSTÁQUIO RANGEL
 * ALUNOS: LUCIANA CAVALCANTE DE MENEZES
 *         RICARDO MADEIRA FERNANDES
 * 
 * PROJETO: ILUSÕES DE ÓTICA.
 */

package ilusaoOticaNovo;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * Classe responsável construção de um botão redondo.
 * Será utilizado nas ilusões de ótica que precisam de círculos.
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
	 * Cria uma nova instância da classe BotaoRedondo.
	 * @param label A label do botão.
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
	 * @param x Posição x.
	 * @param y Posição y.
	 */
	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
		}
		return shape.contains(x, y);
	}

}
