package simple.ui.menus;

import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;


import simple.ui.janelas.JanelaAjuda;
import simple.ui.janelas.JanelaSobre;
import simple.ui.janelas.SImPLe;

/**
 * Classe respons�vel pela cria��o do Menu Ajuda e dos seus respectivos sub-menus.
 * 
 */
public class MenuAjuda extends SimpleMenu {

	private static final long serialVersionUID = -1627739151434643493L;
	
	private JMenuItem topicosAjuda, sobre;
	private JanelaAjuda janelaAjuda;
	private JanelaSobre janelaSobre;
	
	/**
	 * Instancia��o do Menu Ajuda e dos seus respectivos sub-menus.
	 * 
	 * @param simple -- Uma inst�ncia �nica do SImPLe
	 */
	public MenuAjuda(SImPLe simple) {
		super(MENU_AJUDA, simple);
		configureMenu();
	}

	private void configureMenu() {

		topicosAjuda  = configureMenuItem("T�picos de Ajuda", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_A, "Resource/Icones/ajuda.gif");
		sobre = configureMenuItem("Sobre o SImPLe",NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_S, "Resource/Icones/simpleIcon.jpg");

		this.add(topicosAjuda);
		this.add(sobre);
	}

	/**
	 * Acesso aos t�picos de ajuda
	 */
	public void topicos() {
		janelaAjuda = new JanelaAjuda();
		janelaAjuda.exibeJanela();
	}
	
	/**
	 * Acesso ao "Sobre" do SImPLe
	 */
	public void sobre(){
		janelaSobre = new JanelaSobre();
		janelaSobre.exibeJanela();
	}



}
