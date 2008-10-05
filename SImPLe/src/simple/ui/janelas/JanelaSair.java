package simple.ui.janelas;
/*
 * Classe Janela Sair
 * 
 * version 1.0
 * 
 * Data 09/11/2005
 * 
 * CopyRight FePDI all rigths reserved 
 */

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.UIManager;

import simple.manipulacoes.util.AjudaButton;

import simple.ajuda.Ajuda;

import simple.ui.som.Sound;

/**
 * Classe que cria uma janela para fornecer a opção sair do programa
 * 
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class JanelaSair extends JDialog implements ActionListener, WindowListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	private JButton salvar, naoSalvar, ajuda;
	private JLabel label, icone;
	private String acaoSelecionada;
	
	/**
	 * Construtor da classe 
	 * @param mensagem A mensagem de texto a ser exibida
	 */
	public JanelaSair(String mensagem){
		super();
		Sound.play("alerta.wav");	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}	
		acaoSelecionada = "";
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width;
		
		setLocation(screenWidth/2 - 200/2,screenHeight/2 - 123/2);
		setSize(200,123);
		setTitle("Atenção!");
		setLayout(null);
		setModal(true);
		setResizable(false);
		label = new JLabel(mensagem);
		label.setBounds(55,18,280,15);
		icone = new JLabel(new ImageIcon("Resource/Icones/aviso.gif"));
		icone.setBounds(10,10,32,32);
		salvar = new JButton("Sim");
		salvar.setBounds(45,50,55,25);
		salvar.addActionListener(this);
		naoSalvar = new JButton("Não");
		naoSalvar.setBounds(109,50,55,25);
		naoSalvar.addActionListener(this);
		ajuda = new AjudaButton();
		ajuda.setActionCommand("?");
		ajuda.setLocation(170,0);
		ajuda.addActionListener(this);
		getContentPane().add(label);
		getContentPane().add(icone);
		getContentPane().add(salvar);
		getContentPane().add(naoSalvar);
		getContentPane().add(ajuda);
		addWindowListener(this);
		salvar.addKeyListener(this);
		naoSalvar.addKeyListener(this);
		ajuda.addKeyListener(this);
		setVisible(true);			
	}
	
	/**
	 * Construtor da Janela Sair
	 * @param frame O frame da vez
	 * @param mensagem A mensagem que será exibida
	 */
	public JanelaSair(String titulo, String mensagem){
		Sound.play("alerta.wav");	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}	
		acaoSelecionada = "";
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width;
		setLocation(screenWidth/2 - 245/2,screenHeight/2 - 123/2);
		setSize(245,123);
		setTitle(titulo);
		setLayout(null);
		setModal(true);
		setResizable(false);
		label = new JLabel(mensagem);
		label.setBounds(55,18,280,15);
		icone = new JLabel(new ImageIcon("Resource/Icones/aviso.gif"));
		icone.setBounds(10,10,32,32);
		salvar = new JButton("Sim");
		salvar.setBounds(65,50,55,25);
		salvar.addActionListener(this);
		naoSalvar = new JButton("Não");
		naoSalvar.setBounds(129,50,55,25);
		naoSalvar.addActionListener(this);
		getContentPane().add(label);
		getContentPane().add(icone);
		getContentPane().add(salvar);
		getContentPane().add(naoSalvar);
		addWindowListener(this);
		salvar.addKeyListener(this);
		naoSalvar.addKeyListener(this);
		setVisible(true);
	}
	
	/**
	 * Captura o evento realizado pelo usuário
	 */
	public void actionPerformed(ActionEvent evt) {
		acaoSelecionada = "";
		String evento = evt.getActionCommand();
		if(evento.equals("Sim")){						
			acaoSelecionada = "Sim";	
			this.dispose();
		}
		else if(evento.equals("Não")){
			acaoSelecionada = "Nao";
			this.dispose();
		}	
		else if(evento.equals("?")) {
			new Ajuda(Ajuda.AJUDA_SAIR);
		}
	}
	
	/**
	 * Recupera a ação selecionada
	 * @return A ação selecionada
	 */
	public String getAcaoSelecionada(){
		return acaoSelecionada;
	}
	
	/**
	 * Método que atribue não a ação Selecionada
	 */
	public void windowClosing(WindowEvent arg0) {
		acaoSelecionada = "Nao";		
	}
	
	public void windowOpened(WindowEvent arg0) {}
	
	public void windowClosed(WindowEvent arg0) {}
	
	public void windowIconified(WindowEvent arg0) {}
	
	public void windowDeiconified(WindowEvent arg0) {}
	
	public void windowActivated(WindowEvent arg0) {}
	
	public void windowDeactivated(WindowEvent arg0) {}
	
	/**
	 * Metodo captura os eventos do teclado para ENETER e  ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			acaoSelecionada = "Nao";
			this.dispose();
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (ajuda.isFocusOwner()) {
				new Ajuda(Ajuda.AJUDA_SAIR);
			}
			else if (salvar.isFocusOwner() || !salvar.isFocusOwner() && !naoSalvar.isFocusOwner()) {
				acaoSelecionada = "Sim";
				dispose();
			}
			else {
				acaoSelecionada = "Nao";
				dispose();
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
}
