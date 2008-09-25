package simple.ui.janelas;
/*
 * Classe Janela Rotacionar
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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import simple.manipulacoes.util.AjudaButton;


import simple.ajuda.AjudaRotacionar;
/**
 * Classe que cria uma janela para fornecer a opção de Rotacionar uma imagem
 * 
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class JanelaRuidoGaussiano extends JDialog implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	private JLabel informar, label;
	private JTextField text;
	private JButton ok, cancelar, ajuda;
	private double dp;
	private boolean cancelou = false;
	
	/**
	 * Construtor da JanelaRotacionar
	 */
	@SuppressWarnings("static-access")
	public JanelaRuidoGaussiano() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 200/2,d.height/2 - 180/2);
		setSize(200,180);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setTitle("Ruído Gaussiano");
		setLayout(null);
		setModal(true);
		setResizable(false);
		dp = 0;
		informar = new JLabel("Informar");
		informar.setBounds(10,18,100,20);
		
		label = new JLabel("Desvio-Padrão: ");
		label.setBounds(40,50,160,20);
		
		JLabel l2 = new JLabel("(1 à 300)");
		l2.setBounds(55,70,120,20);
		
		text = new JTextField();
		text.setBounds(120,60,35,20);
		
		ok = new JButton("OK");
		ok.setBounds(20,105,57,25);
		ok.addActionListener(this);
		
		cancelar = new JButton("Cancelar");
		cancelar.setBounds(90,105,85,25);
		cancelar.addActionListener(this);
		
		ajuda = new AjudaButton();
		ajuda.setActionCommand("?");
		ajuda.setLocation(170,0);
		ajuda.addActionListener(this);
		
		getContentPane().add(informar);
		getContentPane().add(label);
		getContentPane().add(l2);
		getContentPane().add(text);
		getContentPane().add(ok);
		getContentPane().add(cancelar);
		getContentPane().add(ajuda);
		text.addKeyListener(this);
		ok.addKeyListener(this);
		cancelar.addKeyListener(this);
		ajuda.addKeyListener(this);
		setVisible(true);
		
	}
	
	/**
	 * Captura o evento realizado pelo usuário
	 */
	public void actionPerformed(ActionEvent evt) {
		dp = 0;
		boolean ajuda = false;
		String evento = evt.getActionCommand();
		if (evento.equals("OK")) {
			try {
				dp = Double.parseDouble(text.getText());
			} catch (Exception e) {
				dp = 0;
			}			
		}
		else if (evento.equals("Cancelar")) {
			cancelou = true;
			dp = 0;
			this.dispose();
		}
		else if(evento.equals("?")) {
			new AjudaRotacionar();
			ajuda = true;
		}
		if (!ajuda) {
			if((dp < 1 || dp > 300) && !cancelou){
				JOptionPane.showMessageDialog(null,"Valor Inválido!!! Informe um valor dentro do limite (1 à 300)" 
						,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
				text.setText("");
				dp = 0;
			}
			else this.dispose();
		}
	}
	
	/**
	 * Recupera o ângulo de rotação
	 * @return O ângulo de rotação
	 */
	public double getDesvioPadrao(){
		return dp;
	}
	
	/**
	 * Metodo captura os eventos do teclado para ENETER e  ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		dp = 0;
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			cancelou = true;
			this.dispose();
			dp = 0;
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (ajuda.isFocusOwner()) new AjudaRotacionar();
			else if (ok.isFocusOwner() || !ok.isFocusOwner() && !cancelar.isFocusOwner()) {
				try {
					dp = Integer.parseInt(text.getText());
					if((dp < 1 || dp > 300)){
						JOptionPane.showMessageDialog(null,"Valor Inválido!!! Informe um valor dentro do limite (1 à 360)" 
								,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
					text.setText("");
					} else this.dispose();
				} catch (Exception ex) {
					dp = 0;
				}
			}else {
				cancelou = true;
				dp = 0;
				this.dispose();
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
	public static void main(String[] args) {
		new JanelaRuidoGaussiano();
	}
}
