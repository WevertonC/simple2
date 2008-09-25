package simple.ui.janelas;
/*
 * Classe Janela Decompor Canais
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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import simple.manipulacoes.util.AjudaButton;


import simple.ajuda.AjudaDecomporCanais;

/**
 * Classe que cria uma janela para fornecer a opcao Decompor uma imagem
 * 
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class JanelaDecomposicao extends JDialog implements ActionListener, KeyListener{
	
	private JRadioButton rgb, cmy, cmyk, hsv, ycrcb, xyz; 
	private JButton buttonOk, buttonCancel, buttonAjuda;
	private ButtonGroup botoesModelos;
	private String modelo;
	private boolean ok;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Contrutor da janela para requantizar uma imagem
	 * @param i A imagem a ser requantizada
	 */
	public JanelaDecomposicao(){
		this.setLayout(null);
		this.setSize(400,200);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 400/2,d.height/2 - 200/2);
		this.setTitle("Decomposição de Canais");
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		createAndShowGUI();
		buttonOk.addKeyListener(this);
		buttonCancel.addKeyListener(this);
		buttonAjuda.addKeyListener(this);
		rgb.addKeyListener(this);
		cmy.addKeyListener(this);
		cmyk.addKeyListener(this);
		hsv.addKeyListener(this);
		ycrcb.addKeyListener(this);
		xyz.addKeyListener(this);
		this.setVisible(true);
	}
	
	/**
	 * Metodo que cria a janela com seus botoes e os mostra na tela
	 */
	private void createAndShowGUI() {
		
		botoesModelos = new ButtonGroup();
		
		//Cria os botoes de operacoes
		rgb = new JRadioButton("RGB");
		rgb.addActionListener(this);
		
		cmy = new JRadioButton("CMY");
		cmy.addActionListener(this);
		
		cmyk = new JRadioButton("CMYK");
		cmyk.addActionListener(this);
		
		hsv = new JRadioButton("HSV");
		hsv.addActionListener(this);
		
		ycrcb = new JRadioButton("YCrCb");
		ycrcb.addActionListener(this);
		
		xyz = new JRadioButton("XYZ");
		xyz.addActionListener(this);
		
		JLabel texto = new JLabel("Selecionar Modelo Cromático");
		texto.setBounds(20,10,200,20);
		botoesModelos.add(rgb);
		rgb.setBounds(30,40,50,50);
		botoesModelos.add(cmy);
		cmy.setBounds(90,40,50,50);
		botoesModelos.add(cmyk);
		cmyk.setBounds(150,40,60,50);
		botoesModelos.add(hsv);
		hsv.setBounds(215,40,50,50);
		botoesModelos.add(ycrcb);
		ycrcb.setBounds(270,40,60,50);
		botoesModelos.add(xyz);
		xyz.setBounds(335,40,50,50);
		
		buttonOk = new JButton("OK");
		buttonOk.setBounds(110,120,57,25);
		buttonOk.addActionListener(this);
		
		buttonCancel = new JButton("Cancelar");
		buttonCancel.setBounds(180,120,85,25);
		buttonCancel.addActionListener(this);
		
		buttonAjuda = new AjudaButton();
		buttonAjuda.setActionCommand("?");
		buttonAjuda.setLocation(368,0);
		buttonAjuda.addActionListener(this);
		
		getContentPane().add(rgb);
		getContentPane().add(cmy);
		getContentPane().add(cmyk);
		getContentPane().add(hsv);
		getContentPane().add(xyz);
		getContentPane().add(ycrcb);
		getContentPane().add(texto);
		getContentPane().add(buttonAjuda);
		getContentPane().add(buttonOk);
		getContentPane().add(buttonCancel);
		modelo = "";
	}
	
	/**
	 * Captura os eventos realizados pelo usuário
	 */
	public void actionPerformed(ActionEvent e) {
		String evt = e.getActionCommand();
		if(evt.equals("RGB")){
			modelo = evt;
		}else if(evt.equalsIgnoreCase("CMY")){
			modelo = evt;
		}else if(evt.equalsIgnoreCase("CMYK")){
			modelo = evt;
		}else if(evt.equalsIgnoreCase("HSV")){
			modelo = evt;
		}else if(evt.equalsIgnoreCase("YCrCb")){
			modelo = evt;
		}else if(evt.equalsIgnoreCase("XYZ")){
			modelo = evt;
		}else if(evt.equalsIgnoreCase("ok")){
			ok = true;
			if(modelo.equals("")){
				JOptionPane.showMessageDialog(null,"Selecione a operação desejada" 
						,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
			}else dispose();
		}else if(evt.equalsIgnoreCase("cancelar")){
			ok = false;
			modelo = "";
			this.dispose();
		}else if (evt.equalsIgnoreCase("?"))
			new AjudaDecomporCanais();
	}
	
	/**
	 * Recupera o modelo crolmatico selecionado pelo usuario
	 * @return O modelo selecionado
	 */
	public String getModelo(){
		return modelo;
	}
	
	/**
	 * Recupera se a operacao foi confirmada
	 * @return true se for confirmada e false caso contrario
	 */
	public boolean ok(){
		return ok;
	}
	
	/**
	 * Metodo captura os eventos do teclado para ENETER e  ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			ok = false;
			modelo = "";
			this.dispose();
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (buttonAjuda.isFocusOwner()) new AjudaDecomporCanais();
			else if (buttonOk.isFocusOwner() || !buttonOk.isFocusOwner() && !buttonCancel.isFocusOwner()) {
				ok = true;
				if(modelo.equals("")){
					JOptionPane.showMessageDialog(null,"Selecione a operação desejada" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
				}else dispose();
			}
			else {
				ok = false;
				modelo = "";
				this.dispose();
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
}
