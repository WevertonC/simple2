package simple.ui.janelas;
/*
 * Classe Janela Filtro
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

import simple.ajuda.Ajuda;
import simple.manipulacoes.util.AjudaButton;



/**
 * Classe que cria uma janela nova, ou seja, sem nenhuma imagem
 * 
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class JanelaNovo extends JDialog implements ActionListener, KeyListener{
	
	private static final long serialVersionUID = 1L;
	private JButton ok, cancelar, ajuda;
	private JLabel informar, labelAltura, labelLargura;
	private JTextField alt, lar;
	private int altura, largura;
	private boolean foiOk = false;
	
	/**
	 * Construtor da janelaNovo
	 * @param frame Uma FePDI
	 */
	public JanelaNovo(SImPLe frame){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}	
		altura = 0;
		largura = 0;
		setSize(200,200);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 200/2,d.height/2 - 200/2);
		setTitle("Novo Arquivo");
		setLayout(null);
		setModal(true);
		setResizable(false);
		informar = new JLabel("Informar");
		informar.setBounds(10,18,280,20);
		labelAltura = new JLabel("Altura (pixels):");
		labelAltura.setBounds(30,50,85,20);
		
		JLabel aux = new JLabel("(1 à 1000)");
		aux.setBounds(40, 65, 90, 20);
		
		alt = new JTextField();
		alt.setBounds(120,60,40,20);
		
		labelLargura = new JLabel("Largura (pixels):");
		labelLargura.setBounds(30,80,85,20);
		
		JLabel aux2 = new JLabel("(1 à 1000)");
		aux2.setBounds(40, 95,90,20);
		
		lar = new JTextField();
		lar.setBounds(120,90,40,20);
		
		ok = new JButton("OK");
		ok.setBounds(20,130,57,25);
		ok.addActionListener(this);
		
		cancelar = new JButton("Cancelar");
		cancelar.setBounds(90,130,85,25);
		cancelar.addActionListener(this);
		
		ajuda = new AjudaButton();
		ajuda.setActionCommand("?");
		ajuda.setLocation(170,0);
		ajuda.addActionListener(this);
		
		getContentPane().add(informar);
		getContentPane().add(labelAltura);
		getContentPane().add(aux);
		getContentPane().add(alt);
		getContentPane().add(labelLargura);
		getContentPane().add(aux2);
		getContentPane().add(lar);
		getContentPane().add(ok);
		getContentPane().add(cancelar);
		getContentPane().add(ajuda);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		alt.addKeyListener(this);
		lar.addKeyListener(this);
		ok.addKeyListener(this);
		cancelar.addKeyListener(this);
		ajuda.addKeyListener(this);
		setVisible(true);			
	}
	
	/**
	 * Recupera a altura da janela
	 * @return A altura da janela
	 */
	public int getAltura() {
		return altura;
	}
	
	/**
	 * Recupera a largura da janela
	 * @return A largura da janela
	 */
	public int getLargura() {
		return largura;
	}
	
	/**
	 * Captura o evento realizado pelo usuário
	 */
	public void actionPerformed(ActionEvent evt) {
		foiOk = false;
		String evento = evt.getActionCommand();
		if(evento.equals("OK")){
			foiOk = true;
			try{
				altura = Integer.parseInt(alt.getText());
				largura = Integer.parseInt(lar.getText());
				if((largura <= 0 || largura > 1000) || (altura <= 0 || altura > 1000)){
					JOptionPane.showMessageDialog(null,"Valor inválido!!! Informe valor dentro da faixa permitida (1 à 1000)" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
					foiOk = false;
				}else dispose();
			} catch (Exception e){
				foiOk = false;
				JOptionPane.showMessageDialog(null,"Valor inválido!!! Informe valor dentro da faixa permitida (1 à 1000)" 
						,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
			}
		}else if(evento.equals("Cancelar")) dispose();
		else if(evento.equals("?")){ 
			new Ajuda(Ajuda.AJUDA_ABRIR_NOVO);
		}
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
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (ajuda.isFocusOwner()) {
				new Ajuda(Ajuda.AJUDA_ABRIR_NOVO);
			}
			else if (ok.isFocusOwner() || !ok.isFocusOwner() && !cancelar.isFocusOwner()) {
				foiOk = true;
				try{
					altura = Integer.parseInt(alt.getText());
					largura = Integer.parseInt(lar.getText());
					if((largura <= 0 || largura > 1000) || (altura <= 0 || altura > 1000)){
						JOptionPane.showMessageDialog(null,"Valor inválido!!! Informe valor dentro da faixa permitida (1 à 1000)" 
								,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
						foiOk = false;
					}else dispose();
				} catch (Exception ex){
					JOptionPane.showMessageDialog(null,"Valor inválido!!! Informe valor dentro da faixa permitida (1 à 1000)" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
				}
			}
			else this.dispose();
		}
	}
	
	public void keyReleased(KeyEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
}
