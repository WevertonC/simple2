package ajuda;
/*
 * Janela de Ajuda
 * 
 * @version 1.0
 * 
 * Date: 23/11/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JTextPane;

/**
 * Janela que contem um texto explicativo referente a ajuda.
 * @version 1.0 23/11/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class Ajuda extends JDialog implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	private JTextPane texto;
	
	/**
	 * Construtor da Ajuda
	 *
	 */
	public Ajuda(String nome, String titulo){
		texto = new JTextPane();
		texto.setText(leArquivo(new File("Resource/Help/"+ nome)));
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width;
		
		setLocation(screenWidth/2 - 400/2,screenHeight/2 - 400/2);
		setSize(400,400);
		texto.setBackground(new Color(255,255,225));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Ajuda - " + titulo);
		setModal(true);
		getContentPane().add(texto);
		
		setResizable(true);
		texto.setEditable(false);
		texto.addKeyListener(this);
		setVisible(true);	
	}
	
	/**
	 * Método responsável por ler do arquivo as mensagens de ajuda
	 * @param texto arquivo que contem o texto
	 * @return o texto lido do arquivo
	 */
	public String leArquivo(File texto){
		String textoAjuda = "";
		try {
			Scanner sc = new Scanner(texto);
			while(sc.hasNextLine()){
				textoAjuda += sc.nextLine() + "\n";
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return textoAjuda;
	}
	
	/**
	 * Metodo captura os eventos do teclado para ENETER e ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_ENTER)
			this.dispose();
	}
	
	public void keyReleased(KeyEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
}
