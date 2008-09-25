package simple.manipulacoes.util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyPopUpListener extends MouseAdapter {
		
		private MyPopUp popup;
		
		public MyPopUpListener(MyPopUp popup){
			this.popup = popup;
		}
		
		public void mousePressed(MouseEvent e) {
			maybeShowPopup(e);
		}
		
		public void mouseReleased(MouseEvent e) {
			maybeShowPopup(e);
		}
		
		private void maybeShowPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				popup.show(e.getComponent(),
						e.getX(), e.getY());
			}
		}
		
		public MyPopUp getPopUp(){
			return popup;
		}
}
