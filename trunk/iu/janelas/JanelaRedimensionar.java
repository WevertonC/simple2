package janelas;
/*
 * Classe Janela Redimensionar
 * 
 * version 1.0
 * 
 * Data 09/11/2005
 * 
 * CopyRight FePDI all rigths reserved 
 */

import java.awt.*; 
import java.awt.event.*; 

import javax.swing.*; 

import util.AjudaButton;

import ajuda.AjudaRedimensionar;

/**
 * Classe que cria uma janela para fornecer a opção de Redimensionar uma imagem
 * 
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class JanelaRedimensionar extends JDialog implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	private JTextField jTextField1 , jTextField2, jTextField3;
	private JRadioButton jRadioButton1, jRadioButton2, jRadioButton3, porcentagem, pixel; 
	private JButton ok, cancelar, ajuda;
	private String tipo; 
	private String modo;
	private int porcento, altura, largura;
	
	/**
	 * Construtor da classe Redimensionar
	 */
	public JanelaRedimensionar(){ 
		initializeComponent(); 
		initializeVariables();
		this.setTitle("Redimensionar"); 
		this.setSize(250, 380);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 250/2,d.height/2 - 380/2);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
		this.setModal(true); 
		jTextField1.addKeyListener(this);
		jTextField2.addKeyListener(this);
		jTextField3.addKeyListener(this);
		jRadioButton1.addKeyListener(this);
		jRadioButton2.addKeyListener(this);
		jRadioButton3.addKeyListener(this);
		porcentagem.addKeyListener(this);
		pixel.addKeyListener(this);
		ok.addKeyListener(this);
		cancelar.addKeyListener(this);
		ajuda.addKeyListener(this);
		this.setResizable(false);
		this.setVisible(true); 
	} 
	
	/**
	 * Inicializa a janela com as opções desejadas
	 */
	private void initializeComponent(){		
		JLabel jLabel1 = new JLabel("Informar");
		JLabel jLabel2 = new JLabel("Percentual: ");
		JLabel jLabel22 = new JLabel("(1% a 400%)");
		JLabel jLabel3 = new JLabel("Informar");
		JLabel jLabel44 = new JLabel("(1 à 1000)");
		JLabel jLabel4 = new JLabel("Altura: "); 
		JLabel jLabel5 = new JLabel("Largura: "); 
		JLabel jLabel55 = new JLabel("(1 à 1000)");
		JLabel jLabel6 = new JLabel("Informar");
		JLabel jLabel7 = new JLabel("Informar");
		
		jTextField1 = new JTextField();
		jTextField1.setEnabled(false);
		jTextField2 = new JTextField();
		jTextField2.setEnabled(false);
		jTextField3 = new JTextField();
		jTextField3.setEnabled(false);
		
		JSeparator jSeparator1 = new JSeparator();
		JSeparator jSeparator2 = new JSeparator();
		JSeparator jSeparator3 = new JSeparator();
		
		ok = new JButton("OK");
		ok.addActionListener(this);
		
		cancelar = new JButton("Cancelar"); 
		cancelar.addActionListener(this);
		
		ajuda = new AjudaButton();
		ajuda.setActionCommand("?");
		ajuda.addActionListener(this);
		
		ButtonGroup g = new ButtonGroup(); 		
		pixel = new JRadioButton("Pixel");
		pixel.addActionListener(this); 		
		porcentagem = new JRadioButton("Porcentagem");
		porcentagem.addActionListener(this);
		g.add(pixel);
		g.add(porcentagem);
		
		g = new ButtonGroup();
		jRadioButton1 = new JRadioButton("Bi-linear"); 
		jRadioButton1.addActionListener(this);
		jRadioButton1.setSize(new Dimension(30,100));
		jRadioButton2 = new JRadioButton("Bicúbico");
		jRadioButton2.addActionListener(this);
		jRadioButton2.setSize(new Dimension(30,100));
		jRadioButton3 = new JRadioButton("Vizinhança"); 
		jRadioButton3.addActionListener(this);
		jRadioButton3.setSize(new Dimension(30,100));
		jRadioButton1.setEnabled(false);
		jRadioButton2.setEnabled(false);
		jRadioButton3.setEnabled(false);
		g.add(jRadioButton1);
		g.add(jRadioButton2);
		g.add(jRadioButton3);
		
		JPanel contentPane = (JPanel)this.getContentPane(); 
		contentPane.setLayout(null); 
		
		addComponent(contentPane, jLabel1, 19,10,45,18);
		addComponent(contentPane, pixel, 41,30,64,24);
		addComponent(contentPane, porcentagem, 113,30,94,24);
		addComponent(contentPane, jSeparator3, 22,60,196,5);
		addComponent(contentPane, jLabel7, 19,65,45,18);
		addComponent(contentPane, jLabel2, 40,87,60,18);
		addComponent(contentPane, jLabel22, 40,100,70,18);
		addComponent(contentPane, jLabel3, 19,125,60,18); 
		addComponent(contentPane, jLabel4, 40,155,41,18);
		addComponent(contentPane, jLabel55, 40,168,50,18);
		addComponent(contentPane, jLabel5, 40,190,60,18);
		addComponent(contentPane, jLabel44, 40,203,50,18); 
		addComponent(contentPane, jLabel6, 19,240,60,18);
		addComponent(contentPane, jTextField1, 115,87,63,20); 
		addComponent(contentPane, jTextField2, 115,155,63,20); 
		addComponent(contentPane, jSeparator1, 22,117,196,5); 
		addComponent(contentPane, jRadioButton1, 15,270,64,24); 
		addComponent(contentPane, jRadioButton2, 77,270,64,24); 
		addComponent(contentPane, jRadioButton3, 141,270,78,24); 
		addComponent(contentPane, jTextField3, 115,190,63,20); 
		addComponent(contentPane, jSeparator2, 22,230,196,5); 
		addComponent(contentPane, ok, 40,305,57,25); 
		addComponent(contentPane, cancelar, 110,305,85,25);	
		addComponent(contentPane, ajuda, 220,0,23,23);
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
		tipo = "";
		modo = "";
		porcento = 0;
		altura = 0;
		largura = 0;
	}
	
	/**
	 * Captura o evento realizado pelo usuário
	 */
	public void actionPerformed(ActionEvent arg0) {
		String evento = arg0.getActionCommand();
		if(evento.equals("Pixel")){
			tipo = "Pixel";
			jTextField1.setEnabled(false);
			jTextField2.setEnabled(true);
			jTextField3.setEnabled(true);
			jRadioButton1.setEnabled(true);
			jRadioButton2.setEnabled(true);
			jRadioButton3.setEnabled(true);
		}
		else if(evento.equals("Porcentagem")){
			tipo = "Porcentagem";
			jTextField1.setEnabled(true);
			jTextField2.setEnabled(false);
			jTextField3.setEnabled(false);
			jRadioButton1.setEnabled(true);
			jRadioButton2.setEnabled(true);
			jRadioButton3.setEnabled(true);
		}
		else if(evento.equals("Bi-linear")) modo = "Bilinear";
		else if(evento.equals("Bicúbico")) modo = "Bicubic";
		else if(evento.equals("Vizinhança")) modo = "Nearest";
		else if(evento.equals("OK")){
			if(tipo.equals("")){
				JOptionPane.showMessageDialog(null,"Por favor, escolha o tipo de redimensionamento desejado" 
						,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
			}if (pixel.isSelected()){
				try{
					altura = Integer.parseInt(jTextField2.getText());
					largura = Integer.parseInt(jTextField3.getText());
					if(altura <= 0 || largura <= 0 || altura > 1000 || largura > 1000){
						JOptionPane.showMessageDialog(null,"Largura e/ou altura inválida!!! Informe valor dentro do intervalo (1 à 1000)" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
					jTextField1.setText("");
					jTextField2.setText("");
					jTextField3.setText("");
					} else if(modo.equals("") && (largura!=0 && altura!=0)){
						JOptionPane.showMessageDialog(null,"Preencha o modo de redimensionamento" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
					}else dispose();
				} catch (Exception e){
					JOptionPane.showMessageDialog(null,"Largura e/ou altura inválida!!! Informe valor dentro do intervalo (1 à 1000)" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
					jTextField1.setText("");
					jTextField2.setText("");
					jTextField3.setText("");
				}
			}if (porcentagem.isSelected()){
				try{
					porcento = Integer.parseInt(jTextField1.getText());
					if(porcento <= 0 || porcento > 400){
						JOptionPane.showMessageDialog(null,"Porcentagem inválida!!! Informe valor maior que zero" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
						jTextField1.setText("");
						jTextField2.setText("");
						jTextField3.setText("");
					}else if(modo.equals("") && porcento != 0){
						JOptionPane.showMessageDialog(null,"Preencha o modo de redimensionamento"
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
					}else dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Porcentagem inválido!!! Informe valor dentro do intervalo (1 à 400)" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
					jTextField1.setText("");
					jTextField2.setText("");
					jTextField3.setText("");
				}
			}
		}
		else if(evento.equals("Cancelar")) {
			initializeVariables();
			this.dispose();
		}
		else if(evento.equals("?"))	new AjudaRedimensionar();
	}
	
	/**
	 * Recupera a nova altura da imagem 
	 * @return A nova altura da imagem
	 */
	public int getAltura() {
		return altura;
	}
	
	/**
	 * Recupera a nova largura da imagem 
	 * @return A nova largura da imagem
	 */
	public int getLargura() {
		return largura;
	}
	
	/**
	 * Recupera o modo de redimensionamento(Bilinear, Bicubic ou Nearest)
	 * @return O modo de redimensionamento
	 */
	public String getModo() {
		return modo;
	}
	
	/**
	 * Recupera a porcentagem da redimensão
	 * @return A porcentagem utilizada
	 */
	public int getPorcento() {
		return porcento;
	}
	
	/**
	 * Recupera o tipo de redimensionamento(Porcentagem ou pixel)
	 * @returno O tipo de redimensionamento
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Metodo captura os eventos do teclado para ENETER e  ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			initializeVariables();
			this.dispose();
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (ajuda.isFocusOwner()) new AjudaRedimensionar();
			else if (ok.isFocusOwner() || !ok.isFocusOwner() && !cancelar.isFocusOwner()) {
				if(tipo.equals("")){
					JOptionPane.showMessageDialog(null,"Por favor, escolha o tipo de redimensionamento desejado" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
					jTextField1.setText("");
					jTextField2.setText("");
					jTextField3.setText("");
				}if (pixel.isSelected()){
					try{
						altura = Integer.parseInt(jTextField2.getText());
						largura = Integer.parseInt(jTextField3.getText());
					} catch (Exception ex){
						JOptionPane.showMessageDialog(null,"Largura e/ou altura inválida!!! Informe valor dentro do intervalo (1 à 1000)" 
								,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
						
					}if(altura <= 0 || largura <= 0 || altura > 1000 || altura > 1000){
						JOptionPane.showMessageDialog(null,"Largura e/ou altura inválida!!! Informe valor dentro do intervalo (1 à 1000)" 
								,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
						jTextField1.setText("");
						jTextField2.setText("");
						jTextField3.setText("");
					} else if(modo.equals("") && (largura!=0 && altura!=0)){
						JOptionPane.showMessageDialog(null,"Preencha o modo de redimensionamento" 
								,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
						jTextField1.setText("");
						jTextField2.setText("");
						jTextField3.setText("");
					}else dispose();
				}if (porcentagem.isSelected()){
					try{
						porcento = Integer.parseInt(jTextField1.getText());
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,"Porcentagem inválido!!! Informe valor dentro do intervalo (1 à 400)" 
								,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
						jTextField1.setText("");
						jTextField2.setText("");
						jTextField3.setText("");
					}if(porcento <= 0 || porcento > 400){
						JOptionPane.showMessageDialog(null,"Porcentagem inválido!!! Informe valor maior que zero" 
								,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
						jTextField1.setText("");
						jTextField2.setText("");
						jTextField3.setText("");
					}else if(modo.equals("") && porcento != 0){
						JOptionPane.showMessageDialog(null,"Preencha o modo de redimensionamento"
								,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
						jTextField1.setText("");
						jTextField2.setText("");
						jTextField3.setText("");
					}else dispose();
				}
			}
			else {
				initializeVariables();
				this.dispose();
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
	

} 