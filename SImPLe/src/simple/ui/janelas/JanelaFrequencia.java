package simple.ui.janelas;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ajuda.AjudaRedimensionar;

public class JanelaFrequencia extends JDialog implements KeyListener, ActionListener, ChangeListener {


	private static final long serialVersionUID = 1L;
	private JTextField jTextField1;
	private JButton ok,cancelar;
	private JSlider slider;
	private JLabel texto;
	private double valorRaio;
	private boolean aplicaFiltro = false;

	/**
	 * Construtor da classe Redimensionar
	 */
	public JanelaFrequencia(){ 
		initializeVariables();
		initializeComponent(); 
		this.setTitle("Filtragem no Domínio da Freqüência"); 
		this.setSize(250,200);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 250/2,d.height/2 - 380/2);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
		this.setModal(true); 
		jTextField1.addKeyListener(this);
		ok.addKeyListener(this);
		cancelar.addKeyListener(this);
		slider.addChangeListener(this);
		this.setResizable(false);
		this.setVisible(true); 
	} 

	/**
	 * Inicializa a janela com as opções desejadas
	 */
	private void initializeComponent(){		
			
		JPanel contentPane = (JPanel)this.getContentPane(); 
		contentPane.setLayout(null); 

		addComponent(contentPane, this.texto, 19,19,150,19);
		addComponent(contentPane, jTextField1, 150,18,63,20); 
		addComponent(contentPane, slider, 25,50,180,30); 
		addComponent(contentPane, ok, 60,110,50,20); 
		addComponent(contentPane, cancelar, 110,110,85,20);	
	} 

	/** Add Component Without a Layout Manager (Absolute Positioning) */ 
	private void addComponent(Container container,Component c,int x,int y,int width,int height)	{ 
		c.setBounds(x,y,width,height); 
		container.add(c); 
	} 

	/**
	 * Inicializa as variáveis utilizadas pela janela
	 */
	private void initializeVariables(){
		texto = new JLabel();
		texto.setText("Freqüência de corte:");
		
		ok = new JButton("OK");
		ok.addActionListener(this);

		cancelar = new JButton("Cancelar"); 
		cancelar.addActionListener(this);
		
		jTextField1 = new JTextField();
		jTextField1.setEnabled(false);
		
		slider = new JSlider(JSlider.HORIZONTAL,0,1000,500);
		slider.setEnabled(true);
		this.valorRaio = (double)(slider.getValue())/1000;
		jTextField1.setText( String.valueOf(valorRaio));
		

		
	}

	/**
	 * Captura o evento realizado pelo usuário
	 */
	public void actionPerformed(ActionEvent arg0) {
		String evento = arg0.getActionCommand();
		if(evento.equals("OK")){
			this.aplicaFiltro  = true;
			this.dispose();
		}
		else if(evento.equals("Cancelar")) {
			initializeVariables();
			this.dispose();
		}
		else if(evento.equals("?"))	new AjudaRedimensionar();
	}


	/**
	 * Metodo captura os eventos do teclado para ENETER e  ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			initializeVariables();
			this.dispose();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//			}else dispose();
		}		
		else {
			initializeVariables();
			this.dispose();
		}
	}


	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		this.valorRaio = (double)(slider.getValue())/1000;
		jTextField1.setText( String.valueOf(valorRaio));
		
	}

	public double getValorRaio() {
		return valorRaio;
	}

	public boolean isAplicaFiltro() {
		return aplicaFiltro;
	}
	



}
