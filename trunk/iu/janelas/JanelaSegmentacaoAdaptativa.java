package janelas;
/*
 * Classe Janela Segmentação
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

import ajuda.AjudaSegmentacaoAdaptativa;

import util.AjudaButton;

/**
 * Classe que cria uma janela para fornecer a opção segmentar uma imagem de forma adaptativa
 * 
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

@SuppressWarnings("serial")
public class JanelaSegmentacaoAdaptativa extends JDialog implements ActionListener, KeyListener {
	
	private JLabel informar, label, label2;
	private JTextField text;
	private JButton ok, cancelar, ajuda;
	private int dimensao;
	private boolean foiOk = false;
	
	/**
	 * Construtor da classe
	 */
	@SuppressWarnings("static-access")
	public JanelaSegmentacaoAdaptativa() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		dimensao = 0;
		setSize(200,180);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 200/2,d.height/2 - 180/2);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setTitle("Segmentação Adaptativa");
		setLayout(null);
		setModal(true);
		setResizable(false);
		
		informar = new JLabel("Informar");
		informar.setBounds(10,18,100,20);
		
		label = new JLabel("Limiar para Dimensão:");
		label.setBounds(10,50,170,20);
		label2 = new JLabel("(Valor maior que 0)");
		label2.setBounds(15,70,170,20);
		
		text = new JTextField();
		text.setBounds(150,50,35,20);
		
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
		dimensao = -1;
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
		String evento = evt.getActionCommand();
		if (evento.equals("OK")) {
			foiOk = true;
			try {
				dimensao = Integer.parseInt(text.getText());
				if(dimensao < 1){
					JOptionPane.showMessageDialog(null,"Selecione o valor do limiar (maior que 0)" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
					text.setText("");
				}else dispose();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"Selecione o valor do limiar (maior que 0)" 
						,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
				foiOk = false;
				text.setText("");                        
				dimensao = -1;
			}			
		}
		else if (evento.equals("Cancelar")) {
			this.dispose();
			dimensao = -1;
		}
		else if(evento.equals("?")) new AjudaSegmentacaoAdaptativa();
	}
	
	/**
	 * Recupera a dimensão da segmentação
	 * @return A dimensão da segmentação
	 */
	public int getDimensao(){
		return dimensao;
	}
	
	/**
	 * Determina se o usuario clicou no botao OK
	 */
	public boolean foiOk(){
		return foiOk;
	}
	
	/**
	 * Metodo captura os eventos do teclado para ENETER e  ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.dispose();
			dimensao = -1;
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (ajuda.isFocusOwner()) new AjudaSegmentacaoAdaptativa();
			else if (ok.isFocusOwner() || !ok.isFocusOwner() && !cancelar.isFocusOwner() ) {
				foiOk = true;
				try {
					dimensao = Integer.parseInt(text.getText());
					if(dimensao < 1){
						JOptionPane.showMessageDialog(null,"Selecione o valor do limiar (maior que 0)" 
								,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
						text.setText("");
					}else dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,"Selecione o valor do limiar (maior que 0)" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
					text.setText("");
					foiOk = false;
					dimensao = -1;
				}		
			} 
			else {
				this.dispose();
				dimensao = -1;
			}
		}	
	}
	
	public void keyReleased(KeyEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
}
