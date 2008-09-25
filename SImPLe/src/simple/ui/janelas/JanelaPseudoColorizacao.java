package simple.ui.janelas;
/*
 * Classe Janela Pseudo Colorizacao
 * 
 * version 1.0
 * 
 * Data 09/11/2005
 * 
 * CopyRight FePDI all rigths reserved 
 */
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import simple.manipulacoes.util.AjudaButton;
import simple.manipulacoes.util.MyJInternalFrame;


import simple.ajuda.AjudaPseudoColorizacao;

/**
 * Classe que cria uma janela para fornecer a opcao colorir uma imagem preto e pranco a partir de
 * uma imagem colorida ja existente
 * 
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class JanelaPseudoColorizacao extends JDialog implements ActionListener, KeyListener{
	
	private JButton buttonOk, buttonCancel, buttonAjuda;
	private JComboBox base, colorir;
	private Label texto;
	private Object[] lista;
	private boolean ok = false;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Contrutor da janela para requantizar uma imagem
	 * @param i A imagem a ser requantizada
	 */
	public JanelaPseudoColorizacao(Object[] lista){
		this.setLayout(null);
		this.lista = lista;
		this.setSize(400,330);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 400/2,d.height/2 - 330/2);
		this.setTitle("Pseudo Colorização");
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		createAndShowGUI();
		base.addKeyListener(this);
		colorir.addKeyListener(this);
		buttonOk.addKeyListener(this);
		buttonCancel.addKeyListener(this);
		buttonAjuda.addKeyListener(this);
		this.setVisible(true);
	}
	
	/**
	 * Metodo que cria a janela com seus botoes e os mostra na tela
	 */
	private void createAndShowGUI() {
		
		base = new JComboBox();
		base.setBounds(100,80,200,20);
		getContentPane().add(base);
		
		colorir = new JComboBox();
		colorir.setBounds(100,190,200,20);
		getContentPane().add(colorir);
		
		texto = new Label("Arquivo Base para Colorização");
		texto.setBounds(10,30,250,10);
		getContentPane().add(texto);
		
		texto = new Label("Arquivo a Colorir");
		texto.setBounds(10,140,83,10);
		getContentPane().add(texto);
		
		buttonOk = new JButton("OK");
		buttonOk.setBounds(110,260,57,25);
		buttonOk.addActionListener(this);
		
		buttonCancel = new JButton("Cancelar");
		buttonCancel.setBounds(180,260,85,25);
		buttonCancel.addActionListener(this);
		
		buttonAjuda = new AjudaButton();
		buttonAjuda.setActionCommand("?");
		buttonAjuda.setLocation(368,0);
		buttonAjuda.addActionListener(this);
		
		getContentPane().add(buttonOk);
		getContentPane().add(buttonCancel);
		getContentPane().add(buttonAjuda);
		
		// Adiciona o nome das janelas no comboBox
		for (int i = 0; i < lista.length; i++) {
			if(lista[i] instanceof MyJInternalFrame)
				addItemsToChannels(((MyJInternalFrame) lista[i]).getName());
		}	
	}
	
	/**
	 * Captura os eventos realizados pelo usuario 
	 */
	public void actionPerformed(ActionEvent e) {
		String evt = e.getActionCommand();
		if(evt.equals("OK")){
			ok = true;
			this.dispose();
		}else if(evt.equals("Cancelar")){
			ok = false;
			this.dispose();
		}else if(evt.equals("?"))
			new AjudaPseudoColorizacao();
	}
	
	/**
	 * Metodo que adiciona os nomes das imagens que estao abertas no comboBox
	 * @param item O nome a ser adicioadono comboBox
	 */
	public void addItemsToChannels(String item) {
		base.addItem(item);
		colorir.addItem(item);
	}
	
	/**
	 * Recupera a imagem escolhida que servira como base na coloracao 
	 * @return Um inteiro que representa a posicao do frame selecionado na lista
	 */
	public int getColorido(){
		return base.getSelectedIndex();
	}
	
	/**
	 * Recupera a imagem escolhida que sera colorida 
	 * @return Um inteiro que representa a posicao do frame selecionado na lista
	 */
	public int getPretoBranco(){
		return colorir.getSelectedIndex();
	}
	
	/**
	 * Metodo que recupera se a operacao na janela foi confirmada ou nao
	 * @return true se for confirmada e false caso contrario
	 */
	public boolean getOk(){
		return ok;
	}
	
	/**
	 * Metodo captura os eventos do teclado para ENETER e  ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			ok = false;
			this.dispose();
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (buttonAjuda.isFocusOwner()) new AjudaPseudoColorizacao();
			else if (buttonOk.isFocusOwner() || !buttonOk.isFocusOwner() && !buttonCancel.isFocusOwner()) {
				ok = true;
				this.dispose();
			}
			else {
				ok = false;
				this.dispose();
			}
			
		}	
	}
	
	public void keyReleased(KeyEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
}
