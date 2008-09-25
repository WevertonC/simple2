package simple.ui.menus;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.RenderedImage;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import simple.manipulacoes.util.MyBufferedImage;
import simple.manipulacoes.util.MyJInternalFrame;
import simple.excecoes.RedimensionarException;
import simple.excecoes.RotacionarException;
import simple.excecoes.ZoomException;

import simple.ui.janelas.JanelaAmpliar;
import simple.ui.janelas.JanelaRedimensionar;
import simple.ui.janelas.JanelaRotacionar;
import simple.ui.janelas.SImPLe;

public class MenuGeometricas extends SimpleMenu {

	private static final long serialVersionUID = 3893704783286438149L;
	
	private JMenuItem rotacionar, redimensionar, ampliar;
	
	public MenuGeometricas( SImPLe simple) {
		super(OP_GEOMETRICAS, simple);
		setIcon(new ImageIcon("Resource/Icones/geometrica.gif"));
		setMnemonic(KeyEvent.VK_R);
		configureMenu();
	}
	
	private void configureMenu(){
		
		rotacionar = configureMenuItem("Rotacionar", NO_VALUE, KeyEvent.VK_R,ActionEvent.CTRL_MASK, NO_VALUE, "Resource/Icones/rotacionar.gif");
		redimensionar = configureMenuItem("Redimensionar",  KeyEvent.VK_E, KeyEvent.VK_E,ActionEvent.CTRL_MASK, NO_VALUE, "Resource/Icones/resize.gif");
		ampliar = configureMenuItem("Ampliar",  NO_VALUE, NO_VALUE,NO_VALUE , KeyEvent.VK_Z, "Resource/Icones/zoom.gif");;

		add(rotacionar);
		add(redimensionar);
		add(ampliar);
	}
	
	public void redimensionar(){
		MyJInternalFrame f = (MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame();
		JanelaRedimensionar m = new JanelaRedimensionar();
		if (m.getTipo().equals("Pixel")) {
			if (m.getModo().equals("Bicubic")) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Image i = getSimple().getFacade().emPixelsBICUBIC(f.getMyImage(), m
							.getLargura(), m.getAltura());
					f.setImage(i);
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} catch (RedimensionarException e) {
				}
			} else if (m.getModo().equals("Bilinear")) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Image i = getSimple().getFacade().emPixelsBILINEAR(f.getMyImage(), m
							.getLargura(), m.getAltura());
					f.setImage(i);
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} catch (RedimensionarException e) {
				}
			} else if (m.getModo().equals("Nearest")) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Image i = getSimple().getFacade().emPixelsNEAREST_NEIGHBOR(f
							.getMyImage(), m.getLargura(), m.getAltura());
					f.setImage(i);
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} catch (RedimensionarException e) {
				}
			}
		} else if (m.getTipo().equals("Porcentagem")) {
			if (m.getModo().equals("Bicubic")) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Image i = getSimple().getFacade().emPorcentagemBICUBIC(f.getMyImage(), m
							.getPorcento(), m.getPorcento());
					f.setImage(i);
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

				} catch (RedimensionarException e) {
				}
			} else if (m.getModo().equals("Bilinear")) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Image i = getSimple().getFacade().emPorcentagemBILINEAR(f.getMyImage(),
							m.getPorcento(), m.getPorcento());
					f.setImage(i);
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} catch (RedimensionarException e) {
				}
			} else if (m.getModo().equals("Nearest")) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Image i = getSimple().getFacade()
							.emPorcentagemNEAREST_NEIGHBOR(f.getMyImage(),
									m.getPorcento(), m.getPorcento());
					f.setImage(i);
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} catch (RedimensionarException e) {
				}
			}
		}
	}
	
	public void rotacionar(){
		getSimple().setCursor(new Cursor(Cursor.WAIT_CURSOR));
		MyJInternalFrame f = (MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame();
		JanelaRotacionar j = new JanelaRotacionar();
		int graus = j.getAngulo();
		if (graus != 0) {
			try {
				f.setImage(MyBufferedImage.makeImage(getSimple().getFacade().rotacionar(f
						.getMyImage().getRenderedImage(), graus)));
			} catch (RotacionarException e) {
				e.printStackTrace();

			}
		}
		getSimple().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void ampliar(){
		MyJInternalFrame f = (MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame();
		try {
			f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
			JanelaAmpliar z = new JanelaAmpliar();
			if (z.foiOk()) {
				RenderedImage i = getSimple().getFacade().zoom(f.getMyImage(), z.getZoom());
				f.setImage(i);
			}
			f.getScrollPane()
					.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} catch (Exception e) {
		}
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void zoomMais(){
		getSimple().setCursor(new Cursor(Cursor.WAIT_CURSOR));
		MyJInternalFrame f = (MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame();
		try {
			RenderedImage i = getSimple().getFacade().zoomMais(f.getMyImage());
			f.setImage(i);
		} catch (ZoomException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO!",
					JOptionPane.ERROR_MESSAGE);
		}
		getSimple().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void zoomMenos(){
		getSimple().setCursor(new Cursor(Cursor.WAIT_CURSOR));
		MyJInternalFrame f = (MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame();
		try {
			RenderedImage i = getSimple().getFacade().zoomMenos(f.getMyImage());
			f.setImage(i);
		} catch (ZoomException e) {
			JOptionPane.showMessageDialog(this, "Tamanho minimo atingido",
					"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
		}
		getSimple().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	
}

