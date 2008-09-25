/*
 * ImagePreview
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
package simple.manipulacoes.manipularArquivo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

/**
 * Classe que cria um Preview da imagem na Janela Abrir.
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class ImagePreview extends JComponent implements PropertyChangeListener {
    
	private static final long serialVersionUID = 1L;
	ImageIcon thumbnail = null;
	public ImageIcon imagem = null;
    File file = null;
                             
    /**
     * Cria um ImagePreview na janela Abrir.
     * @param fc A janela Abrir
     */
    public ImagePreview(JFileChooser fc) {
        setPreferredSize(new Dimension(150, 150));
        fc.addPropertyChangeListener(this);
    }

    /**
     * Método que carrega a imagem.
     */
    public void loadImage() {
        if (file == null)
            return;
         ImageIcon tmpIcon = new ImageIcon(file.getPath());
        if (tmpIcon.getIconWidth() > 150 || tmpIcon.getIconHeight() > 150) {
            thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT));
            imagem = new ImageIcon(tmpIcon.getImage().getScaledInstance(tmpIcon.getIconWidth(),tmpIcon.getIconHeight() ,Image.SCALE_DEFAULT));
        } else {
            thumbnail = tmpIcon;
            imagem = tmpIcon;
        }
    }

    /**
     * Método que muda a propriedade da JFileChooser
     * @see javax.swing.JFileChooser
     */
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();
        if (prop.equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)) {
            file = (File) e.getNewValue();
            if (isShowing()) {
                loadImage();
                repaint();
            }
        }
    }

    /**
     * Cria um gráfico com a imagem redimensionada da imagem
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    public void paintComponent(Graphics g) {
        if (thumbnail == null) {
            loadImage();
        }
        if (thumbnail != null) {
            int x = getWidth()/2 - thumbnail.getIconWidth()/2;
            int y = getHeight()/2 - thumbnail.getIconHeight()/2;

            if (y < 0) {
                y = 0;
            }

            if (x < 5) {
                x = 5;
            }
            thumbnail.paintIcon(this, g, x, y);
        }
    }
}
