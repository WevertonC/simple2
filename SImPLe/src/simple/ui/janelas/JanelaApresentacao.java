package simple.ui.janelas;
/*
 * Classe JanelaApresentacao
 * 
 * version 1.0
 * 
 * Data 09/11/2005
 * 
 * CopyRight FePDI all rigths reserved 
 */ 
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import simple.manipulacoes.util.MyBufferedImage;

 
/**
* Classe que mostra uma janela
* 
* @author Andre Cavalcante Hora
* @author Eduardo Santiago Moura
* @author Paulo de Tarso Firmino Junior
* @author Vinicius de Araujo Porto
* @author Yuska Paola Aguiar
*/

public class JanelaApresentacao extends Window {
    
	private static final long serialVersionUID = 1L;
	private BufferedImage splash = null;
	private BufferedImage inputImage = null;
 
	
	
	/**
	 * Construtor da Classe
	 * @param image A bufferedImage a seer exibida
	 */
    public JanelaApresentacao(BufferedImage image) {
    	super(new Frame());
    	inputImage = image;
    	createShadowPicture(inputImage);
        setVisible(true);
    }
 
    /**
     * Metodo que pinta a imagem no frame 
     */
    public void paint(Graphics g) {
        if (splash != null) {
            g.drawImage(splash, 0, 0, null);
        }
    }
    
    /**
     * Metodo que mostra os frames 
     * @param image A imagem a ser exibida
     */
    private void createShadowPicture(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int extra = 14;
 
        setSize(new Dimension(width + extra, height + extra));
        setLocationRelativeTo(null);
        Rectangle windowRect = getBounds();
 
        splash = new BufferedImage(width + extra, height + extra, BufferedImage.BITMASK);
        Graphics2D g2 = (Graphics2D) splash.getGraphics();
        g2.setBackground(Color.magenta);
        
        try {
            Robot robot = new Robot(getGraphicsConfiguration().getDevice());
            BufferedImage capture = robot.createScreenCapture(new Rectangle(windowRect.x, windowRect.y, windowRect.width + extra, windowRect.height + extra));
            g2.drawImage(capture, null, 0, 0);
        } catch (AWTException e) { }
 
        BufferedImage shadow = new BufferedImage(width + extra, height + extra, BufferedImage.TRANSLUCENT); 
        Graphics g = shadow.getGraphics();
        //Color c = new Color(0.0f, 0.0f, 0.0f, 0.3f);
        g.fillRoundRect(6, 6, width, height, 12, 12);
 
        g2.drawImage(shadow, getBlurOp(10), 0, 0);
        g2.drawImage(image, 0, 0, this);
        
        JLabel j = new JLabel();
        j.setIcon(new ImageIcon(MyBufferedImage.makeImage(image)));
        j.show();
    }
 
    /**
     * Metodo que cria a sombra da imagem 
     * @param size O tamanho da sombra
     * @return A sobra da imagem
     */
    private ConvolveOp getBlurOp(int size) {
        float[] data = new float[size * size];
        float value = 1 / (float) (size * size);
        for (int i = 0; i < data.length; i++) {
            data[i] = value;
        }
        return new ConvolveOp(new Kernel(size, size, data));
    }
    
}
