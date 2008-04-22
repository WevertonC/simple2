/*
 * Classe do InternalFrame Ilusao de Optica
 * 
 * @version 1.0
 * 
 * Date: 09/11/05
 * 
 * Copyright FEDPI all rights reserved
 */

package ilusaoOptica;

import facade.Facade;

import janelas.SImPLe;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;





/**
 * Classe que cria um Frame para ilusoes Opticas, com o espaco para a 
 * imagem (Ilusao) e o texto explicativo sobre a mesma.
 * @version 1.0 09/11/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class IlusaoOpticaInternalFrame extends JInternalFrame implements InternalFrameListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel jPanel1;
	private JLabel jLabel1;
	private IlusaoOptica i;
	private SImPLe fepdi;
	private Facade facade;
	private String nome;
	
	/**
	 * Consrtutor de um Frame para Ilusoes de Optica.
	 * @param title O titulo do frame
	 * @param texto A mensagem explicatica sobre a Ilusao.
	 * @param i a Ilusao de Optica
	 * @throws ZoomException 
	 */
	public IlusaoOpticaInternalFrame(SImPLe fepdi, Facade facade, String title, IlusaoOptica i){
		super(title,false);
		addMouseListener(this);
		this.setFrameIcon(new ImageIcon("Resource/Icones/fepdi.JPG"));
		this.fepdi = fepdi;
		this.facade = facade;
		this.setClosable(true);
		this.setMaximizable(false);
		this.setResizable(false);
		this.i = i;
		nome = i.getNome();
		fepdi.habilitaBotoes(false);
		initGUI();
	}
	
	/**
	 * Metodo responsavel pela criacao do frame da Ilusao de Optica.
	 *
	 */
	private void initGUI() {
		try {			
			this.setPreferredSize(new java.awt.Dimension(i.getLargura(),i.getAltura()));
			this.setBounds(0, 0,i.getLargura(), i.getAltura());
			BorderLayout thisLayout = new BorderLayout();
			this.getContentPane().setLayout(thisLayout);
			setVisible(true);
			this.getContentPane().add((Component) i, BorderLayout.CENTER);
			jPanel1 = new JPanel();
			this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
			jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));			
			jLabel1 = new JLabel();
			jPanel1.add(jLabel1);
			jLabel1.setText(i.getMsg());
			jLabel1.setBounds(7, 10, 322, 26);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		addInternalFrameListener(this);
	}

	/**
	 * Metodo responsavel para verificar se o frame e o ultimo
	 */
	@SuppressWarnings("static-access")
	public void internalFrameClosing(InternalFrameEvent arg0) {
		fepdi.verificaUltimo();
		facade.decrementaPosicao();
		String nome = this.getName();
		if(nome.equals("Pontos Pretos")){
			fepdi.buttonIlusao1.setEnabled(true);
			fepdi.ilusao1.setEnabled(true);
		}else if(nome.equals("Linhas Paralelas")){
			fepdi.buttonIlusao2.setEnabled(true);
			fepdi.ilusao2.setEnabled(true);
		}else if(nome.equals("Quadrados")){
			fepdi.buttonIlusao3.setEnabled(true);
			fepdi.ilusao3.setEnabled(true);
		}else if(nome.equals("Circulos")){
			fepdi.buttonIlusao4.setEnabled(true);
			fepdi.ilusao4.setEnabled(true);
		}
	}
	
	public String getName(){
		return nome;
	}

	//Metodos Necessarios para a implementacao da interface
	public void internalFrameClosed(InternalFrameEvent arg0) {}

	public void internalFrameIconified(InternalFrameEvent arg0) {}

	public void internalFrameDeiconified(InternalFrameEvent arg0) {}

	public void internalFrameActivated(InternalFrameEvent arg0) {
		fepdi.habilitaBotoes(false);
	}

	public void internalFrameDeactivated(InternalFrameEvent arg0) {}

	public void internalFrameOpened(InternalFrameEvent arg0) {}

	public void mouseClicked(MouseEvent arg0) {}

	public void mousePressed(MouseEvent arg0) {
		fepdi.habilitaBotoes(false);
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}
}