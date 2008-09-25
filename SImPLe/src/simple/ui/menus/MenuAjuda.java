package simple.ui.menus;

import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;


import simple.ui.janelas.JanelaAjuda;
import simple.ui.janelas.JanelaSobre;
import simple.ui.janelas.SImPLe;

public class MenuAjuda extends SimpleMenu {

	private static final long serialVersionUID = -1627739151434643493L;
	
	private JMenuItem topicosAjuda, sobre;
	private JanelaAjuda janelaAjuda;
	private JanelaSobre janelaSobre;
	
	public MenuAjuda(SImPLe simple) {
		super(MENU_AJUDA, simple);
		configureMenu();
	}

	private void configureMenu() {

		topicosAjuda  = configureMenuItem("Tópicos de Ajuda", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_A, "Resource/Icones/ajuda.gif");
		sobre = configureMenuItem("Sobre o SImPLe",NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_S, "Resource/Icones/simpleIcon.jpg");

		this.add(topicosAjuda);
		this.add(sobre);
	}

	public void topicos() {
		janelaAjuda = new JanelaAjuda();
		janelaAjuda.exibeJanela();
	}
	
	public void sobre(){
		janelaSobre = new JanelaSobre();
		janelaSobre.exibeJanela();
	}



}
