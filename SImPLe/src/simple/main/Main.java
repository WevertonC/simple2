/*
 * Main
 * 
 * version 1.0
 * 
 * Data 09/11/2005
 * 
 * Copyright SImPLe - All Rigths Reserved 
 *
 */

package simple.main;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import simple.manipulacoes.util.MyBufferedImage;
import simple.manipulacoes.util.MyImage;
import simple.ui.janelas.JanelaApresentacao;
import simple.ui.janelas.SImPLe;
import simple.excecoes.ZoomException;


/** 
* Classe responsável pela execução do Simple. 
* Apresenta o método 'main' para execução.
* 
* @author Andre Cavalcente Hora
* @author Eduardo Santiago Moura
* @author Paulo de Tarso Firmino Junior
* @author Vinicius de Araujo Porto
* @author Yuska Paola Aguiar
* 
* 
* @author Elloá B. Guedes - elloaguedes@gmail.com
* @author Odilon F. de Lima Jr. - odilonflj@gmail.com
*/
public class Main {
	
	/*
	 * Imagem a ser exibida na tela de splash 
	 */
	private static String splashScreen = "Resource/Logos/splash.png";

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
			
			showSplashScreen();
			SImPLe s = SImPLe.getInstance();
			s.start();
			
		} catch (OutOfMemoryError e) {
			JOptionPane.showMessageDialog(null,"Estouro de memoria, por favor reinicie o aplicativo.","ERRO INTERNO",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	/**
	 * Responsável pela exibição da tela "splash". A imagem a ser
	 * exibida deve ser configurada na constante splashScreen.
	 */
	private static void showSplashScreen(){
		
		Image i = new MyImage(splashScreen).getImage();
		i.flush();
		MyImage i2 = new MyImage(i);
		try {
			i2.setZoom(-0.05f, -0.05f);
		} catch (ZoomException e1) {
			e1.printStackTrace();
		}
		
		BufferedImage image = MyBufferedImage.makeBufferedImage(i2.getImage());
		JanelaApresentacao s = new JanelaApresentacao(image);
		
		try {
			Thread.sleep(3000);
			s.dispose();
		} catch (Exception e) {
		}
		
	}
}