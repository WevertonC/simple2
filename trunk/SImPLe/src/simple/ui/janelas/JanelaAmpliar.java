package simple.ui.janelas;

/*
 * JanelaZoom
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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import simple.manipulacoes.util.AjudaButton;


import ajuda.AjudaZoom;
/** 
 * Summary description for JanelaZoom 
 * 
 */ 
public class JanelaAmpliar extends JDialog implements ActionListener, ChangeListener, KeyListener{
	
	private static final long serialVersionUID = 1L;
	private JLabel jLabel1; 
	private JLabel jLabel2; 
	private JTextField jTextField1; 
	private JSlider jSlider1; 
	private JSeparator jSeparator1; 
	private JButton ok, cancelar, ajuda; 
	private JPanel contentPane; 
	private int zoom;
	private boolean foiOk = false;
		
	public JanelaAmpliar(){ 
		initializeComponent(); 
		zoom = 0;
		this.setTitle("Ampliar"); 
		this.setSize(new Dimension(255, 260)); 
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 255/2,d.height/2 - 260/2);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
		this.setModal(true); 
		this.setResizable(false);
		jSlider1.addKeyListener(this);
		jTextField1.addKeyListener(this);
		ok.addKeyListener(this);
		cancelar.addKeyListener(this);
		ajuda.addKeyListener(this);
		this.setVisible(true); 
	} 
	
	/** 
	 * This method is called from within the constructor to initialize the form. 
	 * WARNING: Do NOT modify this code. The content of this method is always regenerated 
	 * by the Windows Form Designer. Otherwise, retrieving design might not work properly. 
	 * Tip: If you must revise this method, please backup this GUI file for JFrameBuilder 
	 * to retrieve your design properly in future, before revising this method. 
	 */ 
	private void initializeComponent(){ 
		jLabel1 = new JLabel("Informar"); 
		jLabel2 = new JLabel("Valor:"); 
		jTextField1 = new JTextField(); 
		jTextField1.addActionListener(this);
		jSlider1 = new JSlider(JSlider.HORIZONTAL,0,400,0); 
		jSlider1.addChangeListener(this);
		jSeparator1 = new JSeparator(); 
		ok = new JButton(); 
		cancelar = new JButton(); 
		contentPane = (JPanel)this.getContentPane();
		
		jSlider1.setSnapToTicks(true);
		jSlider1.setMajorTickSpacing(100);
		jSlider1.setMinorTickSpacing(1);
		jSlider1.setPaintLabels(true);
		jSlider1.setPaintTrack(true);
		jSlider1.setPaintTicks(true);
		
		ok.setText("OK"); 
		ok.addActionListener(this);
		
		cancelar.setText("Cancelar"); 
		cancelar.addActionListener(this);
		contentPane.setLayout(null); 
		
		ajuda = new AjudaButton();
		ajuda.setActionCommand("?");
		ajuda.addActionListener(this);
					
		addComponent(contentPane, jLabel1, 26,105,60,18); 
		addComponent(contentPane, jLabel2, 52,141,33,18); 
		addComponent(contentPane, jTextField1, 114,141,56,20); 
		addComponent(contentPane, jSlider1, 10,28,230,40); 
		addComponent(contentPane, jSeparator1, 25,80,192,5); 
		addComponent(contentPane, ok, 41,185,57,25); 
		addComponent(contentPane, cancelar, 113,185,85,25);		 
		addComponent(contentPane, ajuda, 225,0,23,23);
	} 
	
	/** Add Component Without a Layout Manager (Absolute Positioning) */ 
	private void addComponent(Container container,Component c,int x,int y,int width,int height)	{ 
		c.setBounds(x,y,width,height); 
		container.add(c); 
	} 
	
	/**
	 * Captura os eventos realizados pelo usuario
	 */
	public void actionPerformed(ActionEvent arg0) {
		int valor = 0;
		if(arg0.getActionCommand().equals("OK")){
			try{
				foiOk = true;
				valor = Integer.parseInt(jTextField1.getText());
				jSlider1.setValue(valor);
				zoom = jSlider1.getValue();
				if(valor < 0 || valor > 400) {
					foiOk = false;
					JOptionPane.showMessageDialog(null,"Valor Inválido!! O valor desejado deve está entre 0 e 400" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
					jTextField1.setText("");
				}else dispose();
				
			} catch(Exception e){
				foiOk = false;
				JOptionPane.showMessageDialog(null,"Valor Inválido!! O valor desejado deve está entre 0 e 400" 
						,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
				jTextField1.setText("");
			}
		}
		if(arg0.getActionCommand().equals("Cancelar")){
			zoom = 0;
			dispose();
		} else if(arg0.getActionCommand().equals("?")) new AjudaZoom();
	} 
	
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		jTextField1.setText(source.getValue()+"");
		
	}	
	
	public boolean foiOk(){
		return foiOk;
	}
	
	public int getZoom() {
		return zoom;
	}
	
	/**
	 * Metodo captura os eventos do teclado para ENETER e  ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			zoom = 0;
			dispose();
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (ajuda.isFocusOwner()) new AjudaZoom();
			else if (ok.isFocusOwner() || !ok.isFocusOwner() && !cancelar.isFocusOwner()) {
				zoom = jSlider1.getValue();
				dispose();
			}
			else {
				zoom = 0;
				dispose();
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
}
