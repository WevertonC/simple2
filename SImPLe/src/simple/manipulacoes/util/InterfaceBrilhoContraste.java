package simple.manipulacoes.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import javax.swing.JPanel;

import simple.manipulacoes.util.MyJInternalFrame;


public class InterfaceBrilhoContraste extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Image displayImage;
	private BufferedImage biSrc, biDest, bi; 
	private Graphics2D big;
	private RescaleOp rescale;
	private float scaleFactor = 1.0f;
	private float offset = 10;
	boolean brighten, contrastInc;
	MyJInternalFrame frame;

    InterfaceBrilhoContraste(MyJInternalFrame f) {
    	frame = f;
    	displayImage = Toolkit.getDefaultToolkit().getImage(f.getCaminhoImagem());
        setBackground(Color.black);
        setSize(displayImage.getWidth(this),
                displayImage.getWidth(this));
        createBufferedImages();
        
    }


    public void createBufferedImages() {
        biSrc = new BufferedImage(displayImage.getWidth(this),
                                  displayImage.getHeight(this),
                                  BufferedImage.TYPE_INT_RGB);

        big = biSrc.createGraphics();
        big.drawImage(displayImage, 0, 0, this);

        biDest = new BufferedImage(displayImage.getWidth(this),
                                   displayImage.getHeight(this),
                                   BufferedImage.TYPE_INT_RGB);
        bi = biSrc;
    }

    public void changeOffSet() {
        if (brighten) {
            if (offset < 255)
               offset = offset+5.0f;
        }
        else {
            if (offset > 0)
               offset = offset-5.0f;
        }
    }

    public void changeScaleFactor() {
        if (contrastInc) {
            if (scaleFactor < 2)
                scaleFactor = scaleFactor+0.1f;
        }
        else {
            if (scaleFactor > 0)
                scaleFactor = scaleFactor-0.1f;
        }
    }

    public void rescale() {
        rescale = new RescaleOp(scaleFactor, offset, null);
        rescale.filter(biSrc, biDest);
        bi = biDest;
        frame.setImage(getImage());
    }

    public void update(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        paintComponent(g);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(bi, 0, 0, this);
    }

	public Image getImage() {
		return bi;	
	}
	
	public Image getOriginalImage() {
		return biSrc;	
	}	


	public void originalView() {
		frame.setImage(getOriginalImage());
		frame.setFoiModificado(false);
		bi = biSrc;
		offset = 10;
		scaleFactor = 1.0f;
	}
}

