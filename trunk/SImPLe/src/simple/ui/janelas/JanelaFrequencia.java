package simple.ui.janelas;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import simple.ajuda.Ajuda;

public class JanelaFrequencia extends JDialog implements KeyListener, ActionListener, ChangeListener {


	private static final long serialVersionUID = 1L;
	private JTextField jTextField2;
	private JButton ok,cancelar;
	private JLabel jTextField1;
	private JSlider slider;
	private double valorRaio;
	private boolean aplicaFiltro = false;

	/**
	 * Construtor da classe JanelaFrequencia
	 */
	public JanelaFrequencia(){ 
		initializeVariables();
		initializeComponent(); 
		this.setTitle("Filtragem no Domínio da Freqüência"); 
		this.setSize(258, 197);
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
		
		contentPane.add(getOk());
		contentPane.add(getCancelar());
		contentPane.add(getSlider());
		contentPane.add(getTexto());
		contentPane.add(getAreaTexto());

		
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
//		this.valorRaio = (double)(slider.getValue())/1000;
//		jTextField1.setText( String.valueOf(valorRaio));
		
	}
	
	public JButton getOk(){
		if (ok == null) {
			ok = new JButton();
			ok.setText("OK");
			ok.addActionListener(this);
			ok.setSize(new Dimension(99, 25));
			ok.setLocation(new Point(28, 124));
		}
		return ok;
	}
	
	public JButton getCancelar(){
		if (cancelar == null) {
			cancelar = new JButton();
			cancelar.setLocation(new Point(134, 124));
			cancelar.setText("Cancelar");
			cancelar.addActionListener(this);
			cancelar.setSize(new Dimension(99, 25));
		}
		return cancelar;
	}
	
	public JSlider getSlider(){
		if (slider == null) { 
			slider = new JSlider(JSlider.HORIZONTAL,0,1000,500);
			slider.setEnabled(true);
			slider.setName("slider");
			slider.setBounds(new Rectangle(20, 72, 214, 28));
		}
		return slider;
	}
	
	public JLabel getTexto(){
		if (jTextField1 == null) {
			jTextField1 = new JLabel();
			jTextField1.setBounds(new Rectangle(21, 30, 138, 20));
			jTextField1.setText("Freqüência de corte:");
		}
		return jTextField1;
	}
	
	public JTextField getAreaTexto(){
		if (jTextField2 == null){
				jTextField2 = new JTextField();
				jTextField2.setName("jTextField");
				jTextField2.setEnabled(true);
				jTextField2.setText("0.5");
				this.valorRaio = 0.5;
				jTextField2.setBounds(new Rectangle(162, 30, 75, 20));
		}
		return jTextField2;
	}

	/**
	 * Captura o evento realizado pelo usuário
	 */
	public void actionPerformed(ActionEvent arg0) {

		String evento = arg0.getActionCommand();
		if(evento.equals("OK")){
			
			if ((Double.parseDouble(jTextField2.getText()) > 1.0)||(Double.parseDouble(jTextField2.getText()) < 0.0)){
				JOptionPane
				.showMessageDialog(
						null,
						"O valor fornecido deve estar entre zero e um!",
						"ERRO NAS INFORMAÇÕES",
						JOptionPane.ERROR_MESSAGE);
			} else {
				this.aplicaFiltro  = true;
				this.dispose();
			}
		}
		else if(evento.equals("Cancelar")) {
			initializeVariables();
			this.dispose();
		}
		else if(evento.equals("?"))	{
			new Ajuda(Ajuda.AJUDA_FOURIER);
		}
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
		jTextField2.setText(String.valueOf(valorRaio));
		
	}

	public double getValorRaio() {
		return valorRaio;
	}

	public boolean isAplicaFiltro() {
		return aplicaFiltro;
	}
	



}
