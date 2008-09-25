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
 */

package simple.manipulacoes.util;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * Classe que permite gerar em uma imagem o efeito emboss.
 * @author Luciana Cavalcante de Menezes.
 * @author Ricardo Madeira Fernandes.
 */
public class Emboss extends ImageIcon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage bufferedImage;

	public Emboss(Image image) {
		super(image);
		bufferedImage = new BufferedImage(getIconWidth(), getIconHeight(),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D bufferedGraphics = bufferedImage.createGraphics();
		bufferedGraphics.drawImage(getImage(), null, null);
		bufferedImage = emboss(bufferedImage);
	}

	private BufferedImage emboss(BufferedImage src) {
		int width = src.getWidth();
		int height = src.getHeight();

		BufferedImage dst = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				int current = src.getRGB(j, i);

				int upperLeft = 0;
				if (i > 0 && j > 0)
					upperLeft = src.getRGB(j - 1, i - 1);

				int rDiff = ((current >> 16) & 255) - ((upperLeft >> 16) & 255);
				int gDiff = ((current >> 8) & 255) - ((upperLeft >> 8) & 255);
				int bDiff = (current & 255) - (upperLeft & 255);

				int diff = rDiff;
				if (Math.abs(gDiff) > Math.abs(diff))
					diff = gDiff;
				if (Math.abs(bDiff) > Math.abs(diff))
					diff = bDiff;
				int grayLevel = Math.max(Math.min(128 + diff, 255), 0);
				dst.setRGB(j, i, (grayLevel << 16) + (grayLevel << 8)
						+ grayLevel);
			}

		return dst;
	}

	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
		if (getImageObserver() == null)
			g.drawImage(bufferedImage, x, y, c);
		else
			g.drawImage(bufferedImage, x, y, getImageObserver());
	}

	public void setImage(Image image) {
		super.setImage(image);
		bufferedImage = new BufferedImage(getIconWidth(), getIconHeight(),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D bg = bufferedImage.createGraphics();
		bg.drawImage(getImage(), null, null);
		bufferedImage = emboss(bufferedImage);
	}
	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	
}
