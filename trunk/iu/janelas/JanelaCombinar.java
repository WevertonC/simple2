/*
 * Janela Recompor
 * 
 * @version 1.0
 * 
 * Date: 03/11/05
 * 
 * Copyright FEDPI all rights reserved
 */

package janelas;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import util.AjudaButton;
import util.Modificador;
import util.MyBufferedImage;
import util.MyJInternalFrame;
import ajuda.AjudaCombinarCanais;
import decomporCanais.DecompositorCMY;
import decomporCanais.DecompositorCMYK;
import decomporCanais.DecompositorHSV;
import decomporCanais.DecompositorRGB;
import decomporCanais.DecompositorXYZ;
import decomporCanais.DecompositorYCrCb;

/**
 * Classe que gera uma janela responsável pela cominucação entre o usuário e o programa para
 * realização da recomposição da imagem 
 * @version 1.0 03/11/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar  
 */ 
public class JanelaCombinar extends JDialog implements ActionListener, KeyListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JRadioButton botaoRGB, botaoCMY, botaoCMYK, botaoHSV, botaoYCrCb, botaoXYZ;
	private JLabel jLabel , jLabel2 , jLabelChannel1, jLabelChannel2, jLabelChannel3, jLabelChannel4;
	private JComboBox jComboBoxChannel1, jComboBoxChannel2, jComboBoxChannel3, jComboBoxChannel4;
	private JButton botaoOK, botaoCancelar, botaoAjuda;
	private Image imageRecomposta;	
	private String nome;
	private Object[] lista;
	@SuppressWarnings("unchecked")
	private ArrayList listaFrames;
	private int[][] alpha;
	private boolean ok = false;
	private String modelo;
	
	/**
	 * Construtor da classe RecompositorUI
	 * @param facade A facade a ser utilizada
	 * @param lista A lista de frames que estão abertos
	 * @param original A imagem original que será recomposta
	 */
	@SuppressWarnings("unchecked")
	public JanelaCombinar(Object[] lista, Image original){
		setSize(450, 350);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 450/2,d.height/2 - 350/2);
		this.lista = lista;
		setModal(true);
		setTitle("Combinar Canais");
		setLayout(null);
		listaFrames = new ArrayList();
		alpha = new Modificador(original).getAlpha();
		nome = "Composição - ";
		jLabel = new JLabel();
		jLabel.setText("Selecionar Modelo Cromático");
		jLabel.setBounds(10, 15, 170, 20);
		jLabel2 = new JLabel();
		jLabel2.setText("Lista de arquivos abertos");
		jLabel2.setBounds(220, 15, 170, 20);
		
		jLabelChannel1 = new JLabel();
		jLabelChannel2 = new JLabel();
		jLabelChannel3 = new JLabel();
		jLabelChannel4 = new JLabel();
		
		panel = new JPanel();
		panel.setLayout(null);
		
		// Modificando Labels
		
		jLabelChannel1.setSize(108, 20);
		jLabelChannel1.setLocation(200, 50);
		
		jLabelChannel2.setSize(108, 20);
		jLabelChannel2.setLocation(200, 110);
		
		jLabelChannel3.setSize(108, 20);
		jLabelChannel3.setLocation(200, 170);
		
		jLabelChannel4.setSize(108, 20);
		jLabelChannel4.setLocation(200, 230);
		
		//Botões
		botaoOK = new JButton("OK");
		botaoOK.setBounds(140, 275, 57, 25);
		botaoOK.addActionListener(this);
		
		botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setLocation(210, 275);
		botaoCancelar.setSize(85, 25);
		botaoCancelar.addActionListener(this);			
		
		botaoAjuda = new AjudaButton();
		botaoAjuda.setActionCommand("?");
		botaoAjuda.setLocation(418,0);
		botaoAjuda.addActionListener(this);
		
		botaoRGB = new JRadioButton("RGB");
		botaoRGB.setBounds(12, 50, 70, 21);
		botaoRGB.addActionListener(this);
		
		botaoCMY = new JRadioButton("CMY");
		botaoCMY.setSize(70, 21);
		botaoCMY.setLocation(12,90);
		botaoCMY.addActionListener(this);
		
		botaoCMYK = new JRadioButton("CMYK");
		botaoCMYK.setSize(70, 21);
		botaoCMYK.setLocation(12, 130);
		botaoCMYK.addActionListener(this);
		
		botaoHSV = new JRadioButton("HSV");
		botaoHSV.setSize(70, 21);
		botaoHSV.setLocation(12, 170);
		botaoHSV.addActionListener(this);
		
		botaoYCrCb = new JRadioButton("YCrCb");
		botaoYCrCb.setSize(70, 21);
		botaoYCrCb.setLocation(12, 210);
		botaoYCrCb.addActionListener(this);
		
		botaoXYZ = new JRadioButton("XYZ");
		botaoXYZ.setSize(70, 21);
		botaoXYZ.setLocation(12, 250);
		botaoXYZ.addActionListener(this);
		
		ButtonGroup botoes = new ButtonGroup();
		botoes.add(botaoRGB);
		botoes.add(botaoCMY);
		botoes.add(botaoCMYK);
		botoes.add(botaoHSV);
		botoes.add(botaoYCrCb);
		botoes.add(botaoXYZ);
		
		jComboBoxChannel1 = new JComboBox();
		jComboBoxChannel1.setBounds(230, 50, 200, 20);
		
		jComboBoxChannel2 = new JComboBox();
		jComboBoxChannel2.setSize(200, 20);
		jComboBoxChannel2.setLocation(230, 110);
		
		jComboBoxChannel3 = new JComboBox();
		jComboBoxChannel3.setSize(200, 20);
		jComboBoxChannel3.setLocation(230, 170);
		
		jComboBoxChannel4 = new JComboBox();
		jComboBoxChannel4.setSize(200, 20);
		jComboBoxChannel4.setLocation(230, 230);
		
		// Adicionando ao painel
		getContentPane().add(botaoRGB);
		getContentPane().add(jLabel);
		getContentPane().add(botaoCMY);
		getContentPane().add(botaoCMYK);
		getContentPane().add(botaoHSV);
		getContentPane().add(botaoXYZ);
		getContentPane().add(botaoYCrCb);
		getContentPane().add(jLabelChannel1);
		getContentPane().add(jComboBoxChannel1);
		getContentPane().add(jComboBoxChannel2);
		getContentPane().add(jComboBoxChannel3);
		getContentPane().add(jComboBoxChannel4);
		getContentPane().add(jLabel);
		getContentPane().add(jLabel2);
		
		getContentPane().add(botaoOK);
		getContentPane().add(botaoCancelar);
		getContentPane().add(botaoAjuda);
		getContentPane().add(jLabelChannel2);
		getContentPane().add(jLabelChannel3);
		getContentPane().add(jLabelChannel4);
		
		botaoRGB.addKeyListener(this);
		botaoCMY.addKeyListener(this);
		botaoCMYK.addKeyListener(this);
		botaoHSV.addKeyListener(this);
		botaoYCrCb.addKeyListener(this);
		botaoXYZ.addKeyListener(this);
		jComboBoxChannel1.addKeyListener(this);
		jComboBoxChannel2.addKeyListener(this);
		jComboBoxChannel3.addKeyListener(this);
		jComboBoxChannel4.addKeyListener(this);
		botaoOK.addKeyListener(this);
		botaoCancelar.addKeyListener(this);
		botaoAjuda.addKeyListener(this);
		
		jLabelChannel1.setText("R");
		jLabelChannel2.setText("G");
		jLabelChannel3.setText("B");
		jLabelChannel4.setText("-");
		
		jComboBoxChannel4.setEnabled(false);
		modelo = "";
		for (int i = 0; i < lista.length; i++) {
			if(lista[i] instanceof MyJInternalFrame){
				listaFrames.add(lista[i]);
				addItemsToChannels(((MyJInternalFrame) lista[i]).getName());
			}
		}		
		setVisible(true);
	}
	
	/**
	 * Adiciona os frames que estão abertos nos comboBox
	 * @param item O item a ser adicionado
	 */
	public void addItemsToChannels(String item) {
		jComboBoxChannel1.addItem(item);
		jComboBoxChannel2.addItem(item);
		jComboBoxChannel3.addItem(item);
		jComboBoxChannel4.addItem(item);		
	}
	
	/**
	 * Metodo que captura os eventos realizados pelo usuário
	 */
	public void actionPerformed(ActionEvent evt) {
		String evento = evt.getActionCommand();
		if(evento.equals("Cancelar")) this.dispose();
		else if(evento.equalsIgnoreCase("RGB")){
			jLabelChannel1.setText("R");
			jLabelChannel2.setText("G");
			jLabelChannel3.setText("B");
			jLabelChannel4.setText("-");
			modelo = "RGB";
			jComboBoxChannel4.setEnabled(false);
		} else if(evento.equalsIgnoreCase("CMY")){
			jLabelChannel1.setText("C");
			jLabelChannel2.setText("M");
			jLabelChannel3.setText("Y");
			jLabelChannel4.setText("-");
			modelo = "CMY";
			jComboBoxChannel4.setEnabled(false);
		} else if(evento.equalsIgnoreCase("CMYK")){
			jLabelChannel1.setText("C");
			jLabelChannel2.setText("M");
			jLabelChannel3.setText("Y");
			jLabelChannel4.setText("K");
			modelo = "CMYK";
			jComboBoxChannel4.setEnabled(true);	
		} else if(evento.equalsIgnoreCase("XYZ")){
			jLabelChannel1.setText("X");
			jLabelChannel2.setText("Y");
			jLabelChannel3.setText("Z");
			jLabelChannel4.setText("-");
			modelo = "XYZ";
			jComboBoxChannel4.setEnabled(false);
		} else if(evento.equalsIgnoreCase("HSV")){
			jLabelChannel1.setText("H");
			jLabelChannel2.setText("S");
			jLabelChannel3.setText("V");
			jLabelChannel4.setText("-");					
			modelo = "HSV";
			jComboBoxChannel4.setEnabled(false);
		} else if(evento.equalsIgnoreCase("YCrCb")){
			jLabelChannel1.setText("Y");
			jLabelChannel2.setText("Cr");
			jLabelChannel3.setText("Cb");
			jLabelChannel4.setText("-");
			modelo = "YCrCb";
			jComboBoxChannel4.setEnabled(false);
		} else if(evento.equals("OK")){
			ok = true;
			if(!modelo.equals("")){
				Image i1 = ((MyJInternalFrame) listaFrames.get(jComboBoxChannel1.getSelectedIndex())).getImage(); 
				Image i2 = ((MyJInternalFrame) listaFrames.get(jComboBoxChannel2.getSelectedIndex())).getImage();
				Image i3 = ((MyJInternalFrame) listaFrames.get(jComboBoxChannel3.getSelectedIndex())).getImage();
				if(((i1.getHeight(null) == i2.getHeight(null)) && (i2.getHeight(null) == i3.getHeight(null))) && 
						((i1.getWidth(null) == i2.getWidth(null)) && (i2.getWidth(null) == i3.getWidth(null)))){
					int altura = i1.getHeight(null);
					int largura = i1.getWidth(null);
					if(modelo.equals("RGB")){
						BufferedImage[] array = new BufferedImage[3];
						array[0] = MyBufferedImage.makeBufferedImage(i1);
						array[1] = MyBufferedImage.makeBufferedImage(i2);
						array[2] = MyBufferedImage.makeBufferedImage(i3);
						imageRecomposta = DecompositorRGB.setCanaisRGB(array);
						nome += "RGB";
						dispose();
					} else if (modelo.equals("CMY")){
						BufferedImage[] array = new BufferedImage[3];
						array[0] = MyBufferedImage.makeBufferedImage(i1);
						array[1] = MyBufferedImage.makeBufferedImage(i2);
						array[2] = MyBufferedImage.makeBufferedImage(i3);
						imageRecomposta = DecompositorCMY.setCanaisCMY(array, alpha);
						nome += "CMY";
						dispose();
					} else if (modelo.equals("CMYK")){
						Image i4 = ((MyJInternalFrame) listaFrames.get(jComboBoxChannel4.getSelectedIndex())).getImage();
						if(altura == i4.getHeight(null) && largura == i4.getWidth(null)){
							BufferedImage[] array = new BufferedImage[4];
							array[0] = MyBufferedImage.makeBufferedImage(i1);
							array[1] = MyBufferedImage.makeBufferedImage(i2);
							array[2] = MyBufferedImage.makeBufferedImage(i3);
							array[3] = MyBufferedImage.makeBufferedImage(i4);
							imageRecomposta = DecompositorCMYK.setCanaisCMYK(array);
							nome += "CMYK";
							dispose();
						}else JOptionPane.showMessageDialog(null,"Imagens de tamanhos diferentes, Impossível realizar combinação!!!" 
								,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
					} else if (modelo.equals("HSV")){
						BufferedImage[] array = new BufferedImage[3];
						array[0] = MyBufferedImage.makeBufferedImage(i1);
						array[1] = MyBufferedImage.makeBufferedImage(i2);
						array[2] = MyBufferedImage.makeBufferedImage(i3);
						imageRecomposta = DecompositorHSV.setCanaisHSV(array, alpha);
						nome += "HSV";
						dispose();
					} else if (modelo.equals("YCrCb")){
						BufferedImage[] array = new BufferedImage[3];
						array[0] = MyBufferedImage.makeBufferedImage(i1);
						array[1] = MyBufferedImage.makeBufferedImage(i2);
						array[2] = MyBufferedImage.makeBufferedImage(i3);
						imageRecomposta = DecompositorYCrCb.setCanaisYCrCb(array);
						nome += "YCrCb";
						dispose();
					} else if (modelo.equals("XYZ")){
						BufferedImage[] array = new BufferedImage[3];
						array[0] = MyBufferedImage.makeBufferedImage(i1);
						array[1] = MyBufferedImage.makeBufferedImage(i2);
						array[2] = MyBufferedImage.makeBufferedImage(i3);
						imageRecomposta = DecompositorXYZ.setCanaisXYZ(array, alpha);
						nome += "XYZ";
						dispose();
					}
				} else {
					ok = false;
					JOptionPane.showMessageDialog(null,"Imagens de tamanhos diferentes, Impossível realizar combinação!!!" ,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE); 
				}
						
			} else {
				ok = false;
				JOptionPane.showMessageDialog(null,"Selecione a operação desejada" 
						,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
			}}else if(evento.equals("Cancelar")){
			ok = false;
			return;
		}else if(evento.equals("?")) new AjudaCombinarCanais();
	}
	
	/**
	 * Metodo que recupera se a operacao foi ou nao confirmada
	 * @return True se for confirmada e else caso contrario
	 */
	public boolean ok(){
		return ok;
	}
	
	/**
	 * Recupra o frame recomposto com a imagem
	 * @return O Frame recomposto
	 */
	public Image getImageRecomposta(){
		return imageRecomposta;
	}
	
	/**
	 * Recupera o nome da composicao
	 * @return O nome da composicao
	 */
	public String getNome(){
		return nome;
	}
	
	/**
	 * Metodo captura os eventos do teclado para ENETER e  ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.dispose();
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (botaoAjuda.isFocusOwner())  new AjudaCombinarCanais(); else
			if (botaoOK.isFocusOwner() || !botaoOK.isFocusOwner() && !botaoCancelar.isFocusOwner()) {
				ok = true;
				if(!modelo.equals("")){
					if(modelo.equals("RGB")){
						BufferedImage[] array = new BufferedImage[3];
						array[0] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel1.getSelectedIndex()]).getImage());
						array[1] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel2.getSelectedIndex()]).getImage());
						array[2] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel3.getSelectedIndex()]).getImage());
						imageRecomposta = DecompositorRGB.setCanaisRGB(array);
						nome = "RGB";
						dispose();
					} else if (modelo.equals("CMY")){
						BufferedImage[] array = new BufferedImage[3];
						array[0] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel1.getSelectedIndex()]).getImage());
						array[1] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel2.getSelectedIndex()]).getImage());
						array[2] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel3.getSelectedIndex()]).getImage());
						imageRecomposta = DecompositorCMY.setCanaisCMY(array, alpha);
						nome = "CMY";
						dispose();
					} else if (modelo.equals("CMYK")){
						BufferedImage[] array = new BufferedImage[4];
						array[0] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel1.getSelectedIndex()]).getImage());
						array[1] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel2.getSelectedIndex()]).getImage());
						array[2] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel3.getSelectedIndex()]).getImage());
						array[3] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel4.getSelectedIndex()]).getImage());
						imageRecomposta = DecompositorCMYK.setCanaisCMYK(array);
						nome = "CMYK";
						dispose();
					} else if (modelo.equals("HSV")){
						BufferedImage[] array = new BufferedImage[3];
						array[0] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel1.getSelectedIndex()]).getImage());
						array[1] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel2.getSelectedIndex()]).getImage());
						array[2] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel3.getSelectedIndex()]).getImage());
						imageRecomposta = DecompositorHSV.setCanaisHSV(array, alpha);
						nome = "HSV";
						dispose();
					} else if (modelo.equals("YCrCb")){
						BufferedImage[] array = new BufferedImage[3];
						array[0] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel1.getSelectedIndex()]).getImage());
						array[1] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel2.getSelectedIndex()]).getImage());
						array[2] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel3.getSelectedIndex()]).getImage());
						imageRecomposta = DecompositorYCrCb.setCanaisYCrCb(array);
						nome = "Composicao YCrCb";
						dispose();
					} else if (modelo.equals("XYZ")){
						BufferedImage[] array = new BufferedImage[3];
						array[0] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel1.getSelectedIndex()]).getImage());
						array[1] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel2.getSelectedIndex()]).getImage());
						array[2] = MyBufferedImage.makeBufferedImage(((MyJInternalFrame) lista[jComboBoxChannel3.getSelectedIndex()]).getImage());
						imageRecomposta = DecompositorXYZ.setCanaisXYZ(array, alpha);
						nome = "XYZ";
						dispose();
					}
				} else {
					ok = false;
					JOptionPane.showMessageDialog(null,"Selecione a operacao desejada" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
				}
			} else this.dispose();
		}
	}
	
	public void keyReleased(KeyEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
}