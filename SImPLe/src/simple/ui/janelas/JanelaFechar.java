package simple.ui.janelas;
/*
 * Classe Janela Fechar
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

import simple.ajuda.AjudaFechar;
import simple.manipulacoes.util.AjudaButton;
import simple.ui.som.Sound;
/**
 * Classe que cria uma janela para fornecer a opção de fechar os frames abertos
 * 
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class JanelaFechar extends JDialog implements ActionListener, WindowListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	private JButton salvar, naoSalvar, cancelar, ajuda;
	private JLabel label, icone;
	private String acaoSelecionada;
	
	/**
	 * Construtor da classe
	 */
	public JanelaFechar(String nome){
		Sound.play("alerta.wav");	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}	
		acaoSelecionada = "";
		
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width;
		
		setLocation(screenWidth/2 - 345/2,screenHeight/2 - 123/2);	
		
		setSize(345,123);
		setTitle("Atenção! - " + nome);
		setLayout(null);
		setModal(true);
		setResizable(false);
		label = new JLabel("Deseja salvar as modificações antes de fechar?");
		label.setBounds(55,18,280,15);
		icone = new JLabel(new ImageIcon("Resource/Icones/aviso.gif"));
		icone.setBounds(10,10,32,32);
		salvar = new JButton("Sim");
		salvar.setBounds(65,50,57,25);
		salvar.addActionListener(this);
		naoSalvar = new JButton("Não");
		naoSalvar.setBounds(129,50,57,25);
		naoSalvar.addActionListener(this);
		cancelar = new JButton("Cancelar");
		cancelar.setBounds(192,50,85,25);
		cancelar.addActionListener(this);
		ajuda = new AjudaButton();
		ajuda.setActionCommand("?");
		ajuda.setLocation(315,0);
		ajuda.addActionListener(this);
		getContentPane().add(label);
		getContentPane().add(icone);
		getContentPane().add(salvar);
		getContentPane().add(naoSalvar);
		getContentPane().add(cancelar);
		getContentPane().add(ajuda);
		salvar.addKeyListener(this);
		naoSalvar.addKeyListener(this);
		cancelar.addKeyListener(this);
		ajuda.addKeyListener(this);
		addWindowListener(this);
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
			this.setVisible(false);
		}
		else if(evento.equals("Não")){
			acaoSelecionada = "Não";
			this.setVisible(false);
		}
		else if(evento.equals("Cancelar")){
			acaoSelecionada = "Cancelar";
			this.setVisible(false);
		}
		else if(evento.equals("?")) new AjudaFechar();
	}
	
	/**
	 * Recupera a ação selecionada
	 * @return A ação selecionada
	 */
	public String getAcaoSelecionada(){
		return acaoSelecionada;
	}
	
	/**
	 * Fecha a janela selecionada
	 */
	public void windowClosing(WindowEvent arg0) {
		acaoSelecionada = "Cancelar";		
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
		acaoSelecionada = "";
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			acaoSelecionada = "Cancelar";
			this.setVisible(false);
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (ajuda.isFocusOwner()) new AjudaFechar();
			else if (salvar.isFocusOwner()) {
				acaoSelecionada = "Sim";	
				this.setVisible(false);
			}
			else if (naoSalvar.isFocusOwner()){
				acaoSelecionada = "Não";
				this.setVisible(false);
			}
			else if (cancelar.isFocusOwner()) {
				acaoSelecionada = "Cancelar";
				this.setVisible(false);
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
}
