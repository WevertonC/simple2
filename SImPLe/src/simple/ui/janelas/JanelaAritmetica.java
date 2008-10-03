package simple.ui.janelas;
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

import simple.ajuda.Ajuda;
import simple.manipulacoes.util.AjudaButton;
import simple.manipulacoes.util.MyDragList;


/**
 * Janela respons�vel pela Opera��o de Aritm�tica B�sica
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class JanelaAritmetica extends JDialog implements ActionListener, KeyListener { 
	
	private static final long serialVersionUID = 1L;
	
	private JLabel jLabel1, jLabel2, jLabel3 ; 
	private JRadioButton jRadioButton1; 
	private JRadioButton jRadioButton2; 
	private JRadioButton jRadioButton3; 
	private JRadioButton jRadioButton4; 
	private JButton jButton1, jButton2, ajuda; 
	private JPanel contentPane; 
	private ArrayList<String> selecionados;
	private MyDragList m;
	private String operacao;
	
	/**
	 * Construtor da Janela
	 * @param frames Os frames que est�o abertos na tela
	 */
	public JanelaAritmetica(Object[] frames){ 
		initializeComponent(frames); 
		this.setTitle("Opera��es Aritm�ticas"); 
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 440/2,d.height/2 - 280/2);
		this.setSize(440, 280); 
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
		this.setModal(true); 
		this.setResizable(false);
		this.addKeyListener(this);
		jButton1.addKeyListener(this);
		jButton2.addKeyListener(this);
		jRadioButton1.addKeyListener(this);
		jRadioButton2.addKeyListener(this);
		jRadioButton3.addKeyListener(this);
		jRadioButton4.addKeyListener(this);
		ajuda.addKeyListener(this);
		this.setVisible(true); 
	} 
	
	/**
	 * M�todo que inicializa janela
	 * @param frames Uma lista com os frames que est�o abertos na tela
	 */
	private void initializeComponent(Object[] frames){ 
		operacao = "";
		jLabel1 = new JLabel("Selecionar Operador B�sico"); 
		jLabel2 = new JLabel("Arquivos Abertos");
		jLabel3 = new JLabel("Arquivos Selecionados");
		jRadioButton1 = new JRadioButton("Adi��o"); 
		jRadioButton1.addActionListener(this);
		jRadioButton2 = new JRadioButton("Subtra��o");
		jRadioButton2.addActionListener(this);
		jRadioButton3 = new JRadioButton("Multiplica��o");
		jRadioButton3.addActionListener(this);
		jRadioButton4 = new JRadioButton("Divis�o");
		jRadioButton4.addActionListener(this);
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
		
		contentPane.setLayout(null); 
		addComponent(contentPane, jLabel1, 19,15,140,18); 
		addComponent(contentPane, jRadioButton1, 30,40,61,24); 
		addComponent(contentPane, jRadioButton2, 110,40,75,22); 
		addComponent(contentPane, jRadioButton3, 210,40,92,24); 
		addComponent(contentPane, jRadioButton4, 320,40,63,24); 
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
	 * M�todo que captura os eventos realizados pelo usu�rio
	 */
	public void actionPerformed(ActionEvent e) {
		String evento = e.getActionCommand();
		if (evento.equals("Adi��o")) operacao = "Adicao";
		else if (evento.equals("Subtra��o")) operacao = "Subtracao";
		else if (evento.equals("Multiplica��o")) operacao = "Multiplicacao";
		else if (evento.equals("Divis�o")) operacao = "Divisao";
		else if(evento.equals("OK")){
			selecionados = m.getListaSelecionada();
			int frames = getSelecionados().size();
			if(frames <= 1 || operacao.equals("")) {
				JOptionPane.showMessageDialog(null,"Selecione a opera��o desejada e/ou selecione os arquivos (m�nimo dois arquivos)" 
						,"ERRO NAS INFORMA��ES", JOptionPane.ERROR_MESSAGE);
			}else if(((operacao.equals("Subtracao") || operacao.equals("Divisao")) & frames != 2)){
				JOptionPane.showMessageDialog(null,"As opera��es Subtra��o e Divis�o permitem apenas 2(duas) imagens" 
						,"ERRO NAS INFORMA��ES", JOptionPane.ERROR_MESSAGE);
			} else dispose();
		}
		else if(evento.equals("Cancelar")){
			selecionados = null;
			dispose();
		}
		else if(evento.equals("?")) {
			new Ajuda(Ajuda.AJUDA_OP_ARIT_BASICA);
		}
	}
	
	/**
	 * Recupera a operacao selecionada pelo usu�rio
	 * @return A opera��o selecionada
	 */
	public String getOperacao() {
		return operacao;
	}
	
	/**
	 * Recupra os nomes dos frames que est�o expostos
	 * @return Um Array com os nomes
	 */
	public ArrayList<String> getSelecionados() {
		return selecionados;
	}	
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			selecionados = null;
			dispose();
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (ajuda.isFocusOwner()){
				new Ajuda(Ajuda.AJUDA_OP_ARIT_BASICA);
			}
			else if (jButton1.isFocusOwner() || !jButton1.isFocusOwner() && !jButton2.isFocusOwner()) {
				selecionados = m.getListaSelecionada();
				int frames = getSelecionados().size();
				if(frames <= 1 || operacao.equals("")) {
					JOptionPane.showMessageDialog(null,"Selecione a opera��o desejada e/ou selecione os arquivos (m�nimo dois arquivos)" 
							,"ERRO NAS INFORMA��ES", JOptionPane.ERROR_MESSAGE);
				}else if(((operacao.equals("Subtracao") || operacao.equals("Divisao")) & frames != 2)){
					JOptionPane.showMessageDialog(null,"As opera��es Subtra��o e Divis�o permitem apenas 2(duas) imagens" 
							,"ERRO NAS INFORMA��ES", JOptionPane.ERROR_MESSAGE);
				} else dispose();
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
