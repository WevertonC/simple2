/*
 * Main
 * 
 * version 1.0
 * 
 * Data 09/11/2005
 * 
 * CopyRight FePDI all rigths reserved 
 */

package main;
 

import janelas.JanelaApresentacao;
import janelas.SImPLe;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import exceptions.ZoomException;

import util.MyBufferedImage;
import util.MyImage;

/** 
* Classe que excuta o programa principal
* 
* @author Andre Cavalcente Hora
* @author Eduardo Santiago Moura
* @author Paulo de Tarso Firmino Junior
* @author Vinicius de Araujo Porto
* @author Yuska Paola Aguiar
*/
public class Main {

	public static void main(String[] args){
		try{
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				UIManager.put("FileChooser.lookInLabelText","Localizar:");
				UIManager.put("FileChooser.lookInLabelText","Localizar:");
				UIManager.put("FileChooser.saveInLabelText","Salvar em:");
				UIManager.put("FileChooser.fileNameLabelText","Nome Arquivo:");
				UIManager.put("FileChooser.filesOfTypeLabelText","Tipos Arquivo:");
				UIManager.put("FileChooser.upFolderToolTipText","Um nivel acima");
				UIManager.put("FileChooser.upFolderAccessibleName","Acima");
				UIManager.put("FileChooser.newFolderToolTipText","Nova pasta");
				UIManager.put("FileChooser.newFolderAccessibleName","Nova pasta");
				UIManager.put("FileChooser.newFolderActionLabelText","Nova pasta");
				UIManager.put("FileChooser.listViewButtonToolTipText","Lista");
				UIManager.put("FileChooser.listViewButtonAccessibleName","Lista");
				UIManager.put("FileChooser.listViewActionLabelText","Lista");
				UIManager.put("FileChooser.detailsViewButtonToolTipText","Detalhes");
				UIManager.put("FileChooser.detailsViewButtonAccessibleName","Detalhes");
				UIManager.put("FileChooser.detailsViewActionLabelText","Detalhes");
				UIManager.put("FileChooser.refreshActionLabelText","Atualizar");
				UIManager.put("FileChooser.cancelButtonText", "Cancelar");
				UIManager.put ("FileChooser.directoryOpenButtonText", "Abrir");
			} catch (Exception e) {}
//			Image i = new MyImage("Resource/Logos/logo-simple.png").getImage();
//			i.flush();
//			MyImage i2 = new MyImage(i);
//			try {
//				i2.setZoom(-0.05f, -0.05f);
//			} catch (ZoomException e1) {
//				e1.printStackTrace();
//			}
//			BufferedImage image = MyBufferedImage.makeBufferedImage(i2.getImage());
//			JanelaApresentacao s = new JanelaApresentacao(image);
//			try {
//				Thread.sleep(5000);
//			} catch (Exception e) {}
			SImPLe.getInstance();  
			//s.dispose();
		} catch (OutOfMemoryError e) {
			JOptionPane.showMessageDialog(null,"Estouro de memoria, por favor reinicie o aplicativo.","ERRO INTERNO",JOptionPane.ERROR_MESSAGE);
		}
	}
}