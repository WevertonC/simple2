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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import simple.ajuda.AjudaRedimensionar;

public class JanelaFrequenciaFaixa extends JDialog implements KeyListener, ActionListener, ChangeListener {


	private static final long serialVersionUID = 1L;
	private JTextField jTextField1,jTextField2;
	private JButton ok,cancelar;
	private JSlider sliderExterno,sliderInterno;
	private JLabel textoExterno,textoInterno;
	private double raioExterno,raioInterno;
	private boolean aplicaFiltro = false;

	/**
	 * Construtor da classe Redimensionar
	 */
	public JanelaFrequenciaFaixa(){ 
		initializeVariables();
		initializeComponent(); 
		this.setTitle("Filtragem no Domínio da Freqüência"); 
		this.setSize(280,230);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 250/2,d.height/2 - 380/2);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
		this.setModal(true); 
		jTextField1.addKeyListener(this);
		jTextField2.addKeyListener(this);
		ok.addKeyListener(this);
		cancelar.addKeyListener(this);
		sliderExterno.addChangeListener(this);
		sliderInterno.addChangeListener(this);
		this.setResizable(false);
		this.setVisible(true); 
	} 

	/**
	 * Inicializa a janela com as opções desejadas
	 */
	private void initializeComponent(){		
			
		JPanel contentPane = (JPanel)this.getContentPane(); 
		contentPane.setLayout(null); 

		addComponent(contentPane, this.textoExterno, 19,19,200,19);
		addComponent(contentPane, jTextField1, 180,18,63,20); 
		addComponent(contentPane, sliderExterno, 25,50,180,30); 
		
		addComponent(contentPane, this.textoInterno, 19,100,200,19);
		addComponent(contentPane, jTextField2, 180,100,63,20); 
		addComponent(contentPane, sliderInterno, 25,120,180,30);
		
		addComponent(contentPane, ok, 60,150,50,20); 
		addComponent(contentPane, cancelar, 110,150,85,20);	
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
		textoExterno = new JLabel();
		textoExterno.setText("Freqüência de corte 1:");
		
		textoInterno = new JLabel();
		textoInterno.setText("Freqüência de corte 2:");
		
		
		ok = new JButton("OK");
		ok.addActionListener(this);

		cancelar = new JButton("Cancelar"); 
		cancelar.addActionListener(this);
		
		jTextField1 = new JTextField();
		jTextField1.setEnabled(false);

		jTextField2 = new JTextField();
		jTextField2.setEnabled(false);
				
		sliderExterno = new JSlider(JSlider.HORIZONTAL,0,1000,500);
		sliderExterno.setEnabled(true);
		sliderInterno = new JSlider(JSlider.HORIZONTAL,0,1000,500);

		this.raioExterno = (double)(sliderExterno.getValue())/1000;
		jTextField1.setText( String.valueOf(raioExterno));
		
		this.raioInterno = (double)(sliderExterno.getValue())/1000;
		jTextField2.setText( String.valueOf(raioInterno));
	}

	/**
	 * Captura o evento realizado pelo usuário
	 */
	public void actionPerformed(ActionEvent arg0) {
		String evento = arg0.getActionCommand();
		if(evento.equals("OK")){
			
			if (raioExterno < raioInterno) {
				JOptionPane
				.showMessageDialog(
						null,
						"O raio externo deve ser maior que o raio interno!",
						"ERRO NAS INFORMAÇÕES",
						JOptionPane.ERROR_MESSAGE);
			}
			else { 
				this.aplicaFiltro  = true;
				this.dispose();
			}
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
		this.raioExterno = (double)(sliderExterno.getValue())/1000;
		jTextField1.setText( String.valueOf(raioExterno));
		
		this.raioInterno = (double)(sliderInterno.getValue())/1000;
		jTextField2.setText( String.valueOf(raioInterno));
		
		
	}

	public double getRaioExterno() {
		return raioExterno;
	}

	public boolean isAplicaFiltro() {
		return aplicaFiltro;
	}

	public double getRaioInterno() {
		return raioInterno;
	}
	
	
	



}

