package janelas;
/*
 * Classe Janela Segmentação Global
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

import ajuda.AjudaSegmentacaoGlobal;

import util.AjudaButton;
/**
 * Classe que cria uma janela para fornecer a opção segmentar uma imagem de forma global
 * 
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class JanelaSegmentacaoGlobal extends JDialog implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	private JLabel informar, label, label2;
	private JTextField text;
	private JButton ok, cancelar, ajuda;
	private int limiar;
	
	/**
	 * Construtor da calsse
	 */
	@SuppressWarnings("static-access")
	public JanelaSegmentacaoGlobal() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		setSize(200,180);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 200/2,d.height/2 - 180/2);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setTitle("Segmentação Global");
		setLayout(null);
		setModal(true);
		setResizable(false);
		informar = new JLabel("Informar");
		informar.setBounds(10,18,100,20);
		
		label = new JLabel("Limiar global:");
		label.setBounds(30,50,120,20);
		label2 = new JLabel("(Valor maior que 0)");
		label2.setBounds(15,70,170,20);
		
		text = new JTextField();
		text.setBounds(120,50,35,20);
		
		ok = new JButton("OK");
		ok.setBounds(20,110,57,25);
		ok.addActionListener(this);
		
		cancelar = new JButton("Cancelar");
		cancelar.setBounds(90,110,85,25);
		cancelar.addActionListener(this);
		
		ajuda = new AjudaButton();
		ajuda.setActionCommand("?");
		ajuda.setLocation(170,0);
		ajuda.addActionListener(this);
		
		getContentPane().add(informar);
		getContentPane().add(label);
		getContentPane().add(label2);
		getContentPane().add(text);
		getContentPane().add(ok);
		getContentPane().add(cancelar);
		getContentPane().add(ajuda);
		text.setText("");
		text.addKeyListener(this);
		ok.addKeyListener(this);
		cancelar.addKeyListener(this);
		ajuda.addKeyListener(this);
		limiar = 0;
		setVisible(true);
	}
	
	/**
	 * Captura o evento realizado pelo usuário
	 */
	public void actionPerformed(ActionEvent evt) {
		String evento = evt.getActionCommand();
		if (evento.equals("OK")) {
			try {
				limiar = Integer.parseInt(text.getText());
				if (limiar < 1){
					JOptionPane.showMessageDialog(null,"Selecione o valor do limiar (maior que 0)" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
					text.setText("");
				} else dispose();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"Selecione o valor do limiar (maior que 0)" 
						,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
				limiar = -1;
			}			
		}
		else if (evento.equals("Cancelar")) {
			limiar = -1;
			dispose();
		}
		else if(evento.equals("?")) new AjudaSegmentacaoGlobal();
	}
	
	/**
	 * Recupera o limiar da segmentação
	 * @return O limiar da segmentação
	 */
	public int getLimiar(){
		return limiar;
	}
	
	/**
	 * Metodo captura os eventos do teclado para ENETER e  ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			limiar = -1;
			dispose();
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (ajuda.isFocusOwner()) new AjudaSegmentacaoGlobal();
			else if (ok.isFocusOwner() || !ok.isFocusOwner() && !cancelar.isFocusOwner()) {
				try {
					limiar = Integer.parseInt(text.getText());
					if (limiar < 1){
						JOptionPane.showMessageDialog(null,"Selecione o valor do limiar (maior que 0)" 
								,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
						text.setText("");
					} else dispose();
				} catch (Exception ex) {
					limiar = -1;
				}
			}
			else {
				limiar = -1;
				dispose();
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
	public static void main(String[] args) {
		new JanelaSegmentacaoGlobal();
	}
}	
