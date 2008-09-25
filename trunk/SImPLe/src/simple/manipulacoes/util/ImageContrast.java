package simple.manipulacoes.util;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import simple.ui.janelas.SImPLe;

import simple.manipulacoes.util.MyJInternalFrame;




	public class ImageContrast extends JFrame {
		private static final long serialVersionUID = 1L;
		private InterfaceBrilhoContraste interfaceBrilhoContraste;
	    private JButton brightenButton, darkenButton,
	            contIncButton, contDecButton, originalViewButton;
	    private SImPLe fePDI;
	    
	    @SuppressWarnings("deprecation")
		public ImageContrast(MyJInternalFrame f, SImPLe fePDI) {
	        super();
	        this.fePDI = fePDI;
	        
	        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	        
	        Container container = getContentPane();

	        interfaceBrilhoContraste = new InterfaceBrilhoContraste(f);
	        
	        this.setTitle("Ajuste do brilho/contraste da imagem");
	        
	        JPanel panel = new JPanel();
	        
	        GridLayout layout = new GridLayout(3, 2);
	        panel.setLayout(layout);
	        originalViewButton = new JButton("Imagem original");
	        originalViewButton.addActionListener(new ButtonListener());
	     
	        brightenButton = new JButton("Mais brilho");
	        brightenButton.addActionListener(new ButtonListener());
	        darkenButton = new JButton("Menos brilho");
	        darkenButton.addActionListener(new ButtonListener());

	        contIncButton = new JButton("Mais contraste");
	        contIncButton.addActionListener(new ButtonListener());
	        contDecButton = new JButton("Menos contraste");
	        contDecButton.addActionListener(new ButtonListener());

	        panel.add(brightenButton);
	        panel.add(darkenButton);
	        panel.add(contIncButton);
	        panel.add(contDecButton);
	        panel.add(originalViewButton);

	        container.add(BorderLayout.SOUTH, panel);

	        addWindowListener(new WindowEventHandler());
	        setSize(400, 140);
	        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	        setLocation(d.width/2 - 400/2,d.height/2 - 140/2);
	        show();
	    }
	    
	    public Image getImage() {
	    	return interfaceBrilhoContraste.getImage();
	    }

	    class WindowEventHandler extends WindowAdapter {
	        public void windowClosing(WindowEvent e) {
	        }
	    }

	    class ButtonListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            JButton temp = (JButton) e.getSource();

	            if (temp.equals(brightenButton)) {
	                interfaceBrilhoContraste.brighten = true;
	                interfaceBrilhoContraste.changeOffSet();
	                interfaceBrilhoContraste.rescale();
	                interfaceBrilhoContraste.repaint();
	                fePDI.verificaRefazer();
	            }
	            else if (temp.equals(darkenButton)) {
	                interfaceBrilhoContraste.brighten = false;
	                interfaceBrilhoContraste.changeOffSet();
	                interfaceBrilhoContraste.rescale();
	                interfaceBrilhoContraste.repaint();
	                fePDI.verificaRefazer();
	            }
	            else if (temp.equals(contIncButton)) {
	                interfaceBrilhoContraste.contrastInc = true;
	                interfaceBrilhoContraste.changeScaleFactor();
	                interfaceBrilhoContraste.rescale();
	                interfaceBrilhoContraste.repaint();
	                fePDI.verificaRefazer();
	            }
	            else if (temp.equals(contDecButton)) {
	                interfaceBrilhoContraste.contrastInc = false;
	                interfaceBrilhoContraste.changeScaleFactor();
	                interfaceBrilhoContraste.rescale();
	                interfaceBrilhoContraste.repaint();
	                fePDI.verificaRefazer();
	            }
	            else if (temp.equals(originalViewButton)) {
	                interfaceBrilhoContraste.originalView();
	                fePDI.verificaRefazer();
	            }
	        }
	    }
	}
