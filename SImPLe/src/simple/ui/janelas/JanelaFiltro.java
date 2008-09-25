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
import java.awt.Component;
import java.awt.Container;
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import simple.manipulacoes.util.AjudaButton;

import simple.ajuda.AjudaFiltro;


/**
 * Classe que cria uma janela para fornecer a opção de filtrar imegens selecionadas
 * 
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class JanelaFiltro extends JDialog implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	private JLabel informar;
	private JButton ok, cancelar, ajuda;
	private JRadioButton x3, x5, x7, x9;
	private int maskara;
	private JPanel contentPane; 
	
	/**
	 * Construtor da classe
	 * @param nome O nome a ser colocado na janela com o filtro
	 */
	@SuppressWarnings("static-access")
	public JanelaFiltro(String nome) {
		initializeComponent();
		setSize(300,180);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 300/2,d.height/2 - 180/2);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setTitle(nome);
		setLayout(null);
		setModal(true);
		setResizable(false);
		ok.addKeyListener(this);
		cancelar.addKeyListener(this);
		x3.addKeyListener(this);
		x5.addKeyListener(this);
		x7.addKeyListener(this);
		x9.addKeyListener(this);
		ajuda.addKeyListener(this);
		this.setVisible(true);
	}
	
	/**
	 * Método que inicializa a janela e posiciona na tela 
	 */
	private void initializeComponent(){		
		contentPane = (JPanel)this.getContentPane();
		
		maskara = 0;
		
		informar = new JLabel("Selecionar Máscara");
		informar.setBounds(8,18,100,20);
		
		x3 = new JRadioButton("3x3");
		x3.setBounds(20,50,40,40);
		x3.addActionListener(this);
		
		x5 = new JRadioButton("5x5");
		x5.setBounds(90,50,40,40);
		x5.addActionListener(this);
		
		x7 = new JRadioButton("7x7");
		x7.setBounds(160,50,40,40);
		x7.addActionListener(this);
		
		x9 = new JRadioButton("9x9");
		x9.setBounds(230,50,40,40);
		x9.addActionListener(this);
		
		ButtonGroup g = new ButtonGroup();
		g.add(x3);
		g.add(x5);
		g.add(x7);
		g.add(x9);
		
		ok = new JButton("OK");
		ok.addActionListener(this);
		
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		
		ajuda = new AjudaButton();
		ajuda.setActionCommand("?");
		ajuda.addActionListener(this);
		
		addComponent(contentPane, informar, 8,18,100,20); 
		addComponent(contentPane, x3, 20,50,60,40); 
		addComponent(contentPane, x5, 90,50,60,40); 
		addComponent(contentPane, x7,160,50,60,40); 
		addComponent(contentPane, x9, 230,50,60,40); 
		addComponent(contentPane, ok,60,100,57,25);
		addComponent(contentPane, cancelar,130,100,85,25);
		addComponent(contentPane, ajuda,270,0,23,23);
	}
	
	/** Add Component Without a Layout Manager (Absolute Positioning) */ 
	private void addComponent(Container container,Component c,int x,int y,int width,int height){ 
		c.setBounds(x,y,width,height); 
		container.add(c); 
	}
	
	/**
	 * Método que captura os eventos realizados pelo usuário
	 */
	public void actionPerformed(ActionEvent evt) {
		String evento = evt.getActionCommand();
		if (evento.equals("3x3")) maskara = 3;
		else if (evento.equals("5x5")) maskara = 5;
		else if (evento.equals("7x7")) maskara = 7;
		else if (evento.equals("9x9")) maskara = 9;
		else if (evento.equals("Cancelar")){
			maskara = 0;
			dispose();
		}
		else if(evento.equals("OK")) {
			if(maskara == 0){
				JOptionPane.showMessageDialog(null,"Selecione a máscara desejada!!!" 
						,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
			} else dispose();
		}
		else if(evento.equals("?")) new AjudaFiltro();	
	}
	
	/**
	 * Recupera a máscara escolhida pelo usuário
	 * @return A máscara escolhida pelo usuário
	 */
	public int getMaskara(){
		return maskara;
	}
	
	/**
	 * Metodo captura os eventos do teclado para ENETER e  ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			maskara = 0;
			dispose();
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			//if (ajuda.isFocusOwner()) {/*Falta ajuda*/} else
			if (ok.isFocusOwner() || !ok.isFocusOwner() && !cancelar.isFocusOwner()) {
				if(maskara == 0){
					JOptionPane.showMessageDialog(null,"Selecione a máscara desejada!!!" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
				} else dispose();
			}
			else {
				maskara = 0;
				dispose();
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
}
