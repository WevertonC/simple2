package util;

/*
 * AjudaButton
 *   
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Bot�o de Ajuda Personalizado
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class AjudaButton extends JButton implements MouseListener{
	
	private static final long serialVersionUID = 1L; //Numero de s�rie da classe

	/**
	 * Construtor de Bot�o de Ajuda Personalizado
	 */
	public AjudaButton(){
		setBorder(BorderFactory.createEmptyBorder());
		setIcon(new ImageIcon("Resource/Icones/help.JPG"));
		setSelectedIcon(new ImageIcon("Resource/Icones/help.png"));
		setSize(23,23);
		addMouseListener(this);
	}
	
	/**
	 * M�todo que muda o icone de exibicao do botao apos ter sido clicado
	 */
	public void mouseClicked(MouseEvent arg0) {
		setIcon(new ImageIcon("Resource/Icones/help.JPG"));
	}

	/**
	 * M�todo que muda o icone de exibicao do botao apos ter sido pressionado
	 */
	public void mousePressed(MouseEvent arg0) {
		setIcon(new ImageIcon("Resource/Icones/help.png"));
	}

	/**
	 * M�todo que muda o icone de exibicao do botao quando o cursor do mouse 
	 * esta sobre o botao
	 */
	public void mouseEntered(MouseEvent arg0) {
		setIcon(new ImageIcon("Resource/Icones/helpacesa.png"));
	}

	/**
	 * M�todo que muda o icone de exibicao do botao apos o cursor do mouse 
	 * ter saido de sobre o botao
	 */
	public void mouseExited(MouseEvent arg0) {
		setIcon(new ImageIcon("Resource/Icones/help.JPG"));
	}
	
	/**
	 * M�todo que muda o icone de exibicao do botao quando o mouse � solto
	 */
	public void mouseReleased(MouseEvent arg0) {
		setIcon(new ImageIcon("Resource/Icones/help.JPG"));
	}
}