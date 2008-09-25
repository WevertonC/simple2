package simple.ui.janelas;
/*
 * Classe Janela Requantizar
 * 
 * version 1.0
 * 
 * Data 09/11/2005
 * 
 * CopyRight FePDI all rigths reserved 
 */
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;


import simple.manipulacoes.util.AjudaButton;
import simple.modules.propriedades.requantizar.Requantizador;

import ajuda.AjudaRequantizacao;

/**
 * Classe que cria uma janela para fornecer a opcao de requantizar uma imagem
 * 
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class JanelaRequantizar extends JDialog implements ActionListener, KeyListener{
	
	private JRadioButton radioButtonAnd, radioButtonOr, radioButtonIGS, c2, c4, c8, c16, c32, c64, c128; 
	private JButton buttonOk, buttonCancel, buttonAjuda;
	private ButtonGroup botoesOperacoes, botoesValores;
	private Image i;
	private int valorMaskAND, valorMaskOR;
	private boolean and = false;
	private boolean or = false;
	private boolean igs = false;
	private Image imagemModificada;
	private int numCores;
	private boolean ok = false;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Contrutor da janela para requantizar uma imagem
	 * @param i A imagem a ser requantizada
	 */
	public JanelaRequantizar(Image i){
		this.setLayout(null);
		this.setSize(350,280);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 350/2,d.height/2 - 280/2);
		this.setTitle("Requantização");
		this.setModal(true);
		this.i = i;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		createAndShowGUI();
		buttonOk.addKeyListener(this);
		buttonCancel.addKeyListener(this);
		radioButtonAnd.addKeyListener(this);
		radioButtonOr.addKeyListener(this);
		radioButtonIGS.addKeyListener(this);
		buttonAjuda.addKeyListener(this);
		c2.addKeyListener(this);
		c4.addKeyListener(this);
		c8.addKeyListener(this);
		c16.addKeyListener(this);
		c32.addKeyListener(this);
		c64.addKeyListener(this);
		c128.addKeyListener(this);
		this.setVisible(true);
	}
	
	/**
	 * Metodo que cria a janela com seus botoes e os mostra na tela
	 */
	private void createAndShowGUI() {
		botoesOperacoes = new ButtonGroup();
		
		//Cria os botoes de operacoes
		radioButtonAnd = new JRadioButton("AND");
		radioButtonAnd.addActionListener(this);
		
		radioButtonOr = new JRadioButton("OR");
		radioButtonOr.addActionListener(this);
		
		radioButtonIGS = new JRadioButton("IGS");
		radioButtonIGS.addActionListener(this);
		
		JLabel texto = new JLabel("Selecionar Operação");
		texto.setBounds(30,20,200,20);
		botoesOperacoes.add(radioButtonAnd);
		radioButtonAnd.setBounds(50,40,50,50);
		botoesOperacoes.add(radioButtonOr);
		radioButtonOr.setBounds(155,40,50,50);
		botoesOperacoes.add(radioButtonIGS);
		radioButtonIGS.setBounds(250,40,50,50);
		
		botoesValores = new ButtonGroup();
		
		// Cria os botoes de Numero de Tons
		JLabel texto2 = new JLabel("Selecionar Número de Tons");
		texto2.setBounds(30,100,200,20);
		
		c2 = new JRadioButton("2");
		c2.addActionListener(this);
		c2.setBounds(30,130,40,40);
		
		c4 = new JRadioButton("4");
		c4.addActionListener(this);
		c4.setBounds(70,130,40,40);
		
		c8 = new JRadioButton("8");
		c8.addActionListener(this);
		c8.setBounds(110,130,40,40);
		
		c16 = new JRadioButton("16");
		c16.addActionListener(this);
		c16.setBounds(150,130,40,40);
		
		c32 = new JRadioButton("32");
		c32.addActionListener(this);
		c32.setBounds(190,130,40,40);
		
		c64 = new JRadioButton("64");
		c64.addActionListener(this);
		c64.setBounds(230,130,40,40);
		
		c128 = new JRadioButton("128");
		c128.addActionListener(this);
		c128.setBounds(270,130,50,40);
		
		//Adiciona os botoes em um grupo
		botoesValores.add(c2);
		botoesValores.add(c4);
		botoesValores.add(c8);
		botoesValores.add(c16);
		botoesValores.add(c32);
		botoesValores.add(c64);
		botoesValores.add(c128);
		
		buttonOk = new JButton("OK");
		buttonOk.setBounds(90,200,57,25);
		buttonOk.addActionListener(this);
		
		buttonCancel = new JButton("Cancelar");
		buttonCancel.setBounds(160,200,85,25);
		buttonCancel.addActionListener(this);
		
		buttonAjuda = new AjudaButton();
		buttonAjuda.setActionCommand("?");
		buttonAjuda.setLocation(318,0);
		buttonAjuda.addActionListener(this);
		
		getContentPane().add(texto);
		getContentPane().add(radioButtonAnd);
		getContentPane().add(radioButtonOr);
		getContentPane().add(radioButtonIGS);
		getContentPane().add(texto2);
		getContentPane().add(c2);
		getContentPane().add(c4);
		getContentPane().add(c8);
		getContentPane().add(c16);
		getContentPane().add(c32);
		getContentPane().add(c64);
		getContentPane().add(c128);
		getContentPane().add(buttonOk);
		getContentPane().add(buttonCancel);
		getContentPane().add(buttonAjuda);
	}
	
	/**
	 * Metodo que recupera a imagem apos a requantizacao
	 * @return A imagem requantizada
	 */
	public Image getImagem(){
		return imagemModificada;
	}
	
	/**
	 * Captura os eventos realizados pelo usuario
	 */
	public void actionPerformed(ActionEvent e) {
		String evt = e.getActionCommand();
		if(evt.equals("AND")){
			and = true;
			or = false;
			igs = false;
			botoes(true);
		}else if(evt.equals("OR")){
			and = false;
			or = true;
			igs = false;
			botoes(true);
		}else if(evt.equals("IGS")){
			and = false;
			or = false;
			igs = true;
			botoes(false);
		}else if(evt.equals("2")){
			valorMaskAND = 128;
			valorMaskOR = 127;
			numCores = 2;
		}else if(evt.equals("4")){
			valorMaskAND = 192;
			valorMaskOR = 63;
			numCores = 4;
		}else if(evt.equals("8")){
			valorMaskAND = 224;
			valorMaskOR =31;
			numCores = 8;
		}else if(evt.equals("16")){
			valorMaskAND = 240;
			valorMaskOR = 15;
			numCores = 16;
		}else if(evt.equals("32")){
			valorMaskAND = 248;
			valorMaskOR = 7;
			numCores = 32;
		}else if(evt.equals("64")){
			valorMaskAND = 252;
			valorMaskOR = 3;
			numCores = 64;
		}else if(evt.equals("128")){
			valorMaskAND = 254;
			valorMaskOR = 1;
			numCores = 128;
		}else if(evt.equals("OK")){
			ok = true;
			Requantizador r = new Requantizador(i);
			if(and && numCores!=0){ 
				imagemModificada = r.maskAND(valorMaskAND);
				this.dispose();
			}
			else if(or && numCores!=0){
				imagemModificada = r.maskOR(valorMaskOR);
				this.dispose();
			}
			else if(igs){
				imagemModificada = r.maskIGS();
				this.dispose();
			}
			else if(numCores == 0 || !foiEscolhido())
				JOptionPane.showMessageDialog(null,"Selecione a operação desejada e o número de tons" 
						,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
			else this.dispose();
		}else if(evt.equals("Cancelar")){
			ok = false;
			this.dispose();
		}else if(evt.equals("?")) new AjudaRequantizacao();
	}
	
	/**
	 * Recupera a operacao realizada e o numero de tons da operacao
	 * @return Uma String com essas informacoes
	 */
	public String getNome(){
		if(and){
			return " - Requantizacao AND " + "com " + numCores + " tons";
		}else if (or)
			return " - Requantizacao OR " + "com " + numCores + " tons";
		return " - Requantizacao IGS";
	}
	
	/**
	 * Recupera o numero de cores da requantizacao
	 * @return O numero de cores da requantizacao
	 */
	public int getNumCores(){
		return numCores;
	}
	
	/**
	 * Metodo que recupera se foi escolhida alguma das tres operacoes
	 * @return true se alguma operacao foi escolhida e false caso contrario
	 */
	public boolean foiEscolhido(){
		return and || or || igs;
	}
	
	/**
	 * Metodo que recupera se a operacao realizada foi IGS
	 * @return true se for IGS e false caso contrario
	 */
	public boolean getIGS(){
		return igs;
	}
	
	/**
	 * Recupera se a operacao foi confirmada
	 * @return true se for confirmada e false caso contrario
	 */
	public boolean getOk(){
		return ok;
	}
	
	/**
	 * Metodo que habilita ou desabilita os botoes de tons de cores
	 * @param habilita true se quer habilitar e false caso contrario
	 */
	private void botoes(boolean habilita){
		c2.setEnabled(habilita);
		c4.setEnabled(habilita);
		c8.setEnabled(habilita);
		c16.setEnabled(habilita);
		c32.setEnabled(habilita);
		c64.setEnabled(habilita);
		c128.setEnabled(habilita);
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
			if (buttonAjuda.isFocusOwner()) new AjudaRequantizacao(); 
			else if (buttonOk.isFocusOwner() || !buttonOk.isFocusOwner() && !buttonCancel.isFocusOwner()) {
				ok = true;
				Requantizador r = new Requantizador(i);
				if(and && numCores!=0){ 
					imagemModificada = r.maskAND(valorMaskAND);
					this.dispose();
				}
				else if(or && numCores!=0){
					imagemModificada = r.maskOR(valorMaskOR);
					this.dispose();
				}
				else if(igs){
					imagemModificada = r.maskIGS();
					this.dispose();
				}
				else if(numCores == 0 || !foiEscolhido())
					JOptionPane.showMessageDialog(null,"Selecione a operação desejada e o número de tons" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
				else this.dispose();
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
