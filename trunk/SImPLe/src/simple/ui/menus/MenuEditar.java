package simple.ui.menus;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.RenderedImage;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import simple.manipulacoes.util.MyImage;
import simple.manipulacoes.util.MyJInternalFrame;

import simple.ui.janelas.MyJWindow;
import simple.ui.janelas.SImPLe;

public class MenuEditar extends SimpleMenu {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem desfazer, refazer, recortar, copiar, colar;

	public MenuEditar(SImPLe simple){
		super(SimpleMenu.MENU_EDITAR,simple);
		super.setMnemonic(KeyEvent.VK_E);
		configureMenu();
	}
	
	private void configureMenu(){
		
		desfazer = configureMenuItem("Desfazer",NO_VALUE, KeyEvent.VK_Z,ActionEvent.CTRL_MASK, KeyEvent.VK_D, "Resource/Icones/undo.gif");
		desfazer.setEnabled(false);

		refazer = configureMenuItem("Refazer",NO_VALUE, KeyEvent.VK_Y,ActionEvent.CTRL_MASK, KeyEvent.VK_F, "Resource/Icones/redo.gif");
		refazer.setEnabled(false);

		recortar = configureMenuItem("Recortar",NO_VALUE, KeyEvent.VK_X,ActionEvent.CTRL_MASK, KeyEvent.VK_T, "Resource/Icones/cut.gif");
		recortar.setEnabled(false);
		
		copiar = configureMenuItem("Copiar",NO_VALUE, KeyEvent.VK_C,ActionEvent.CTRL_MASK, KeyEvent.VK_O, "Resource/Icones/copy.gif");
		recortar.setEnabled(false);
		
		colar = configureMenuItem("Colar",NO_VALUE, KeyEvent.VK_V,ActionEvent.CTRL_MASK, KeyEvent.VK_C, "Resource/Icones/paste.gif");
		colar.setEnabled(false);

		add(desfazer);
		add(refazer);
		addSeparator();
		add(recortar);
		add(copiar);
		add(colar);
	}
	
	public void desfazer(){
		MyJInternalFrame f = (MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame();
		if (f != null) {
			RenderedImage i = getSimple().getFacade().desfazer((MyImage) f.getMyImage());
			f.setImage(i);
		}
	}
	
	public void refazer(){
		MyJInternalFrame f = (MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame();
		if (f != null) {
			RenderedImage i = getSimple().getFacade().refazer((MyImage) f.getMyImage());
			f.setImage(i);
		}
	}
	
	public void recortar(){
		boolean recortou = getSimple().getFacade().recortar(getSimple(), getSimple().getFrameAberto()
				.getImage());
		if (recortou) {
			colar.setEnabled(true);
			getSimple().buttonColar.setEnabled(true);
			((MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame()).setImage(getSimple().getFacade().getImagemRecortada());
		} else {
			colar.setEnabled(false);
			getSimple().buttonColar.setEnabled(false);
		}
		recortar.setEnabled(false);
		getSimple().buttonRecortar.setEnabled(false);
		copiar.setEnabled(false);
		getSimple().buttonCopiar.setEnabled(false);
	}
	
	public void copiar(){
		boolean copiou = getSimple().getFacade().copiar(getSimple(), getSimple().getFrameAberto().getImage());
		if (copiou) {
			colar.setEnabled(true);
			getSimple().buttonColar.setEnabled(true);
		} else {
			colar.setEnabled(false);
			getSimple().buttonColar.setEnabled(false);
		}
		recortar.setEnabled(false);
		getSimple().buttonRecortar.setEnabled(false);
		copiar.setEnabled(false);
		getSimple().buttonCopiar.setEnabled(false);
	}
	
	public void colar(){
		if (getSimple().verificaInstancia(getSimple().getDesktopPane().getSelectedFrame())) {
			ImageIcon colada = new ImageIcon(getSimple().getFacade().colar());
			new MyJWindow(getSimple(), colada, colada.getIconWidth(), colada
					.getIconHeight(), getSimple().getLocation().x
					+ getSimple().getFrameAberto().getX() + 10, getSimple().getLocation().y
					+ getSimple().getFrameAberto().getY() + 105);
		}
	}
	
	
	

}
