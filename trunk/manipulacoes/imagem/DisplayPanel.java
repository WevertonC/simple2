package imagem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class DisplayPanel extends JLabel {
	  Image displayImage;

	  BufferedImage biSrc, biDest;

	  BufferedImage bi;

	  Graphics2D big;

	  AffineTransform transform;

	  double scalex = 1.0;

	  double scaley = 1.0;

	  double shearx = 1.0;

	  double sheary = 1.0;

	  DisplayPanel() {
	    setBackground(Color.black);
	    
	  }

	  public void loadImage(Image imagem) {	
		  
	    displayImage = imagem;
	    MediaTracker mt = new MediaTracker(this);
	    mt.addImage(displayImage, 1);
	    try {
	      mt.waitForAll();
	    } catch (Exception e) {
	      System.out.println("Exception while loading.");
	    }

	    if (displayImage.getWidth(this) == -1) {
	      System.out.println(" Arquivo não encontrado");
	      System.exit(0);
	    }
	    setSize(displayImage.getWidth(this), displayImage.getWidth(this)); // panel
	    createBufferedImages();
	    transform = new AffineTransform();	    
	  }

	  public void createBufferedImages() {
	    biSrc = new BufferedImage(displayImage.getWidth(this), displayImage
	        .getHeight(this), BufferedImage.TYPE_INT_RGB);

	    big = biSrc.createGraphics();
	    big.drawImage(displayImage, 0, 0, this);

	    bi = biSrc;

	    biDest = new BufferedImage(displayImage.getWidth(this), displayImage
	        .getHeight(this), BufferedImage.TYPE_INT_RGB);
	  }

	  public void applyValue(boolean scale, boolean shear) {
	    if (scale) {
	      transform.setToScale(scalex, scaley);
	      scale = false;
	    } else if (shear) {
	      transform.setToShear(shearx, sheary);
	      shear = false;
	    }
	  }

	  public void applyFilter() {
		  try {
		    AffineTransformOp op = new AffineTransformOp(transform, null);
		    Graphics2D biDestG2D = biDest.createGraphics();
		    biDestG2D
		        .clearRect(0, 0, biDest.getWidth(this), biDest.getHeight(this));
		    op.filter(biSrc, biDest);
		    bi = biDest;
		  }catch(Exception e) {}
	  }

	  public void reset() {
	    big.setColor(Color.black);
	    big.clearRect(0, 0, bi.getWidth(this), bi.getHeight(this));
	    big.drawImage(displayImage, 0, 0, this);
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
	}


