package janelas;

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

import util.AjudaButton;

import ajuda.AjudaOperacoesAritmeticasPessoal;

public class JanelaAritmeticaGanhoOffset extends JDialog implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	private JLabel informar, labelOffset, labelGain;
	private JTextField textGain, textOffset;
	private JButton ok, cancelar, ajuda;
	private Double gain, offset;
	private boolean foiOk = false;
	
	@SuppressWarnings("static-access")
	public JanelaAritmeticaGanhoOffset() {
		setSize(250,220);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 200/2,d.height/2 - 220/2);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Ganho/Offset");
		setLayout(null);
		setModal(true);
		setResizable(false);
		
		gain = Double.MAX_VALUE;
		offset = Double.MAX_VALUE;
		
		informar = new JLabel("Informar");
		informar.setBounds(10,18,100,20);
		
		labelGain = new JLabel("Ganho:");
		labelGain.setBounds(50,50,80,20);
		
		JLabel faixa = new JLabel("(1 à 255)");
		faixa.setBounds(45,65,80,20);
		
		textGain = new JTextField();
		textGain.setBounds(120,60,45,20);
		
		labelOffset = new JLabel("Offset:");
		labelOffset.setBounds(50,90,80,20);
		
		JLabel faixa2 = new JLabel("(0 à 255)");
		faixa2.setBounds(45,105,80,20);
		
		textOffset = new JTextField();
		textOffset.setBounds(120,100,45,20);
		
		ok = new JButton("OK");
		ok.setBounds(50,150,57,25);
		ok.addActionListener(this);
		
		cancelar = new JButton("Cancelar");
		cancelar.setBounds(120,150,85,25);
		cancelar.addActionListener(this);
		
		ajuda = new AjudaButton();
		ajuda.setActionCommand("?");
		ajuda.setLocation(220,0);
		ajuda.addActionListener(this);
		
		getContentPane().add(informar);
		getContentPane().add(labelGain);
		getContentPane().add(faixa);
		getContentPane().add(textGain);
		getContentPane().add(labelOffset);
		getContentPane().add(faixa2);
		getContentPane().add(textOffset);
		getContentPane().add(ok);
		getContentPane().add(cancelar);
		getContentPane().add(ajuda);
		
		textGain.addKeyListener(this);
		textOffset.addKeyListener(this);
		ok.addKeyListener(this);
		cancelar.addKeyListener(this);
		ajuda.addKeyListener(this);
		
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent evt) {
		String evento = evt.getActionCommand();
		if (evento.equals("OK")) {
			try{
				foiOk = true;
				gain = Double.parseDouble(textGain.getText());
				offset = Double.parseDouble(textOffset.getText());
				if(gain < 1 || gain > 255 || offset < 0 || offset > 255){
					foiOk = false;
					JOptionPane.showMessageDialog(null,"Valores inválidos!!! Informe valores dentro do intervalo" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
					textGain.setText("");
					textOffset.setText("");
				}else dispose();
			} catch (Exception e) {
				foiOk = false;
				JOptionPane.showMessageDialog(null,"Valores inválidos!!! Digite apenas números inteiros" 
						,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
				textGain.setText("");
				textOffset.setText("");
			}			
		}
		else if(evento.equals("Cancelar")){
			foiOk = false;
			dispose();
		}
		else if(evento.equals("?")) 
			new AjudaOperacoesAritmeticasPessoal();
	}
	
	public double getGain(){
		return gain;
	}
	
	public double getOffset(){
		return offset;
	}	
	
	public boolean foiOk(){
		return foiOk;
	}
	/**
	 * Metodo captura os eventos do teclado para ENETER e  ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			dispose();
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (ajuda.isFocusOwner()) new AjudaOperacoesAritmeticasPessoal();
			else if (ok.isFocusOwner() || !ok.isFocusOwner() && !cancelar.isFocusOwner()) {
				try{
					foiOk = true;
					gain = Double.parseDouble(textGain.getText());
					offset = Double.parseDouble(textOffset.getText());
					if(gain < 1 || gain > 255 || offset < 0 || offset > 255){
						foiOk = false;
						JOptionPane.showMessageDialog(null,"Valores inválidos!!! Informe valores dentro do intervalo" 
								,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
						textGain.setText("");
						textOffset.setText("");
					}else dispose();
				} catch (Exception ex) {
					foiOk = false;
					JOptionPane.showMessageDialog(null,"Valores inválidos!!! Digite apenas números inteiros" 
							,"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
					textGain.setText("");
					textOffset.setText("");
				}
			} else {
				foiOk = false;
				dispose();
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
}

