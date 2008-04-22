package janelas;
/*
 * janela Aritmetica Basica
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import ajuda.AjudaOperacoesLogicas;

import util.AjudaButton;
import util.MyDragList;
/**
 * Janela responsável pela Operação de Aritmética Básica
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class JanelaOperacoesLogicas extends JDialog implements ActionListener, KeyListener{ 
	
	private static final long serialVersionUID = 1L;
	// Variables declaration 
	private JLabel jLabel1, jLabel2, jLabel3 ; 
	private JRadioButton jRadioButton1, jRadioButton2, jRadioButton3, jRadioButton4, 
	jRadioButton5, jRadioButton6; 
	private JButton jButton1, jButton2, ajuda; 
	private JPanel contentPane; 
	private ArrayList<String> selecionados;
	private MyDragList m;
	private String operacao;
	
	/**
	 * Construtor da Janela
	 * @param frames Os frames que estão abertos na tela
	 */
	public JanelaOperacoesLogicas(Object[] frames){ 
		initializeComponent(frames); 
		this.setTitle("Operações Lógicas"); 
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 440/2,d.height/2 - 280/2); 
		this.setSize(440, 280); 
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jRadioButton1.addKeyListener(this);
		jRadioButton2.addKeyListener(this);
		jRadioButton3.addKeyListener(this);
		jRadioButton4.addKeyListener(this);
		jRadioButton5.addKeyListener(this);
		jRadioButton6.addKeyListener(this);
		jButton1.addKeyListener(this);
		jButton2.addKeyListener(this);
		ajuda.addKeyListener(this);
		this.setModal(true); 
		this.setResizable(false); 
		this.setVisible(true); 
	} 
	
	/**
	 * Método que inicializa janela
	 * @param frames Uma lista com os frames que estão abertos na tela
	 */
	private void initializeComponent(Object[] frames){ 
		operacao = "";
		jLabel1 = new JLabel("Selecionar Operador Básico"); 
		jLabel2 = new JLabel("Arquivos Abertos");
		jLabel3 = new JLabel("Arquivos Selecionados");
		
		jRadioButton1 = new JRadioButton("NOT"); 
		jRadioButton1.addActionListener(this);
		jRadioButton2 = new JRadioButton("AND");
		jRadioButton2.addActionListener(this);
		jRadioButton3 = new JRadioButton("XOR");
		jRadioButton3.addActionListener(this);
		jRadioButton4 = new JRadioButton("NAND");
		jRadioButton4.addActionListener(this);
		jRadioButton5 = new JRadioButton("NOR");
		jRadioButton5.addActionListener(this);
		jRadioButton6 = new JRadioButton("OR");
		jRadioButton6.addActionListener(this);
		
		jButton1 = new JButton("OK"); 
		jButton1.addActionListener(this);
		
		jButton2 = new JButton("Cancelar");
		jButton2.addActionListener(this);
		
		ajuda = new AjudaButton();
		ajuda.addActionListener(this);
		ajuda.setActionCommand("?");
		contentPane = (JPanel)this.getContentPane(); 
		
		ButtonGroup g = new ButtonGroup();
		g.add(jRadioButton1);
		g.add(jRadioButton2);
		g.add(jRadioButton3);
		g.add(jRadioButton4);
		g.add(jRadioButton5);
		g.add(jRadioButton6);
		
		contentPane.setLayout(null); 
		addComponent(contentPane, jLabel1, 19,15,140,18); 
		addComponent(contentPane, jRadioButton1, 30,40,70,24); 
		addComponent(contentPane, jRadioButton2, 100,40,70,24); 
		addComponent(contentPane, jRadioButton3, 170,40,70,24); 
		addComponent(contentPane, jRadioButton4, 240,40,70,24); 
		addComponent(contentPane, jRadioButton5, 310,40,70,24); 
		addComponent(contentPane, jRadioButton6, 380,40,70,24); 
		addComponent(contentPane,jLabel2,19,75,120,18);
		addComponent(contentPane,jLabel3,237,75,120,18);
		addComponent(contentPane, jButton1, 160,210,57,25); 
		addComponent(contentPane, jButton2, 230,210,85,25); 
		addComponent(contentPane, ajuda, 410,0,23,23);
		m = new MyDragList(frames);
		addComponent(contentPane,m,10,100,m.getWidth(),m.getHeight());		
	} 
	
	/** Add Component Without a Layout Manager (Absolute Positioning) */ 
	private void addComponent(Container container,Component c,int x,int y,int width,int height){ 
		c.setBounds(x,y,width,height); 
		container.add(c); 
	}  
	
	/**
	 * Método que captura os eventos realizados pelo usuário
	 */
	public void actionPerformed(ActionEvent arg0) {
		String evento = arg0.getActionCommand();
		if(evento.equals("OK")){
			selecionados = m.getListaSelecionada();
			if((getSelecionados().size() <= 1 && !operacao.equals("NOT")) || operacao.equals("")){
				JOptionPane.showMessageDialog(null,"Selecione a operação desejada e/ou selecione os arquivos (mínimo dois arquivos)" 
						,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
			}else if(operacao.equals("NOT") & (getSelecionados().size() > 1 || getSelecionados().size() == 0)){
				JOptionPane.showMessageDialog(null,"O operação NOT permite apenas 1(uma) imagem selecionada" 
						,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
			}else dispose();
		}
		else if(evento.equals("NOT")) operacao = "NOT";
		else if(evento.equals("AND")) operacao = "AND";
		else if(evento.equals("XOR")) operacao = "XOR";
		else if(evento.equals("NAND")) operacao = "NAND";
		else if(evento.equals("NOR")) operacao = "NOR";
		else if(evento.equals("OR")) operacao = "OR";
		else if(evento.equals("Cancelar")){
			selecionados = null;
			dispose();
		}
		else if(evento.equals("?")) new AjudaOperacoesLogicas();
	}
	
	/**
	 * Recupera a operacao selecionada pelo usuário
	 * @return A operação selecionada
	 */
	public String getOperacao() {
		return operacao;
	}
	
	/**
	 * Recupra os nomes dos frames que estão expostos
	 * @return Um Array com os nomes
	 */
	public ArrayList<String> getSelecionados() {
		return selecionados;
	}
	
	/**
	 * Metodo captura os eventos do teclado para ENETER e  ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			selecionados = null;
			dispose();
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			//if (ajuda.isFocusOwner()) else
			if (jButton1.isFocusOwner() || !jButton1.isFocusOwner() && !jButton2.isFocusOwner()) {
				selecionados = m.getListaSelecionada();
				if((getSelecionados().size() <= 1 && !operacao.equals("NOT")) || operacao.equals("")){
					JOptionPane.showMessageDialog(null,"Selecione a operação desejada e/ou selecione os arquivos (mínimo dois arquivos)" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
				}else if(operacao.equals("NOT") & (getSelecionados().size() > 1 || getSelecionados().size() == 0)){
					JOptionPane.showMessageDialog(null,"O operação NOT permite apenas 1(uma) imagem selecionada" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
				}else dispose();
			}
			else {
				selecionados = null;
				dispose();
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
}