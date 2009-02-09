package simple.ui.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import simple.ui.janelas.SImPLe;

/**
 * Superclasse de um menu do SImPLe
 *
 */
public class SimpleMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Nome dos menus
	public static String MENU_ARQUIVO = "Arquivo";
	public static String MENU_EDITAR = "Editar";
	public static String MENU_IMAGEM = "Imagem";
	public static String MENU_AJUDA = "Ajuda";
	public static String MENU_OPERACOES = "Opera��es";
	public static String MENU_CLASSIFICACAO = "Classifica��o";


	public static String OP_RADIOMETRICAS = "Radiom�tricas";
	public static String OP_PONTUAIS = "Pontuais";
	public static String OP_LOCAIS = "Locais";
	public static String OP_GEOMETRICAS = "Geom�tricas";


	private SImPLe simple;
	public static int NO_VALUE = -1;


	public SimpleMenu(String menuName, SImPLe simple){
		super(menuName);
		this.simple = simple;
	}

	/**
	 * Retorna uma inst�ncia �nica do SImPLe
	 * @return
	 */
	public SImPLe getSimple(){
		return simple;
	}

	/**
	 * Padroniza��o para a constru��o de objetos do tipo JMenuITem.
	 * 
	 * @param name
	 * @param key
	 * @param stroke
	 * @param action
	 * @param mnemonic
	 * @param pathToIcon
	 * @return
	 */
	public JMenuItem configureMenuItem(String name, int key, int stroke, int action, int mnemonic,  String 
			pathToIcon){
		JMenuItem temp = new JMenuItem(name,key);
		if ((stroke != NO_VALUE)&&(action != NO_VALUE)){
			temp.setAccelerator(KeyStroke.getKeyStroke(stroke,action));
		}
		if (mnemonic != NO_VALUE){
			temp.setMnemonic(mnemonic);
		}
		temp.setIcon(new ImageIcon(pathToIcon));
		temp.addActionListener(this.getSimple());
		return temp;
	}




}
