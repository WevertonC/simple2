/*
 * HistogramaJWindow
 * 
 * @version 1.0
 * 
 * Date: 03/11/05
 * 
 * Copyright FEDPI all rights reserved
 */

package coordenadas;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JInternalFrame;
import javax.swing.JWindow;

import util.MyScrollPane;

/**
 * Classe que gera e exibe os histogramas por linha e por coluna
 * @version 1.0 03/11/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class HistogramJWindow extends JWindow implements MouseListener, MouseMotionListener, KeyListener{
		
	private static final long serialVersionUID = 1L;
	public final int LINE = 0;
	public final int COLLUMN = 1;
	private static HistogramJWindow w;
	private static boolean type = AlgoritmosImagem.LINHA;
	private BufferedImage image;
	private static JInternalFrame frame;
	private int valor = 0;
	private boolean foiFechado = false;
		
	/**
	 * Construtor da classe  
	 * @param owner O frame a ser exibido
	 */
	private HistogramJWindow(Frame owner){
		super(owner);
		setBackground(java.awt.Color.WHITE);
		setSize(500,150);
		addMouseListener(this);
		addKeyListener(this);
		setVisible(false);
	}

	/**
	 * Método que recupera se o HistogramaJWindow e instancia de Frame
	 * @param owner O Frame
	 * @param f O JInternalFrame
	 * @param t O type
	 * @return O HistogramaJWindow
	 */
	public static HistogramJWindow getInstance(Frame owner, JInternalFrame f, boolean t){
		type = t;
		frame = f;
		if (w == null) return (w = new HistogramJWindow(owner));
		else return w;
	}
	
	/**
	 * Método que pinta o gráfico com as informacoes da coluna e linha
	 * @param g O grafico a ser pintado
	 */
	public void paint(Graphics g){
		if (image != null){
			int[][] perfil = null;
			Graphics2D g2 = (Graphics2D)g;
			if (type == AlgoritmosImagem.LINHA)
				try{
					perfil = AlgoritmosImagem.perfilLinha(image,valor,0,image.getWidth());
					setSize(perfil[0].length,300);
					g2.clearRect(0,0,getWidth(),getHeight());
					for (int i = 0; i < perfil[0].length-1; i++) {
						g2.setColor(Color.RED);   
						g2.drawLine(i,getHeight()-5-perfil[0][i],i+1,getHeight()-5-perfil[0][i+1]);
						g2.setColor(Color.GREEN);   
						g2.drawLine(i,getHeight()-5-perfil[1][i],i+1,getHeight()-5-perfil[1][i+1]);
						g2.setColor(Color.BLUE);   
						g2.drawLine(i,getHeight()-5-perfil[2][i],i+1,getHeight()-5-perfil[2][i+1]);
					}	
				} catch (Exception e) {}
			else{
				try{
					perfil = AlgoritmosImagem.perfilColuna(image,valor,0,image.getHeight());
					setSize(perfil[0].length,300);
					g2.clearRect(0,0,getWidth(),getHeight());
					for (int i = 0; i < perfil[0].length-1; i++) {
						g2.setColor(Color.RED);   
						g2.drawLine(i,getHeight()-5-perfil[0][i],i+1,getHeight()-5-perfil[0][i+1]);
						g2.setColor(Color.GREEN);   
						g2.drawLine(i,getHeight()-5-perfil[1][i],i+1,getHeight()-5-perfil[1][i+1]);
						g2.setColor(Color.BLUE);   
						g2.drawLine(i,getHeight()-5-perfil[2][i],i+1,getHeight()-5-perfil[2][i+1]);
					}	
				} catch (Exception e) {}
			}
		}
	}
	
	/**
	 * Metodo que move a janela dos perfis sobre a tela
	 */
	public void mouseMoved(MouseEvent ev) {
		MyScrollPane pn = (MyScrollPane)ev.getSource();
		image = pn.getBufferedImage();
		setLocation(frame.getX()+ev.getX()+30, frame.getY()+ ev.getY()+30);
		if (type == AlgoritmosImagem.LINHA) valor = ev.getY();
		else valor = ev.getX();
		repaint();
	}

	/**
	 * Metodo que finaliza a janela quando deseja-se encerrar sua exibicao
	 */
	public void mouseClicked(MouseEvent ev){
		foiFechado = true;
		dispose();
	}
	
	public void setFoiFechado(boolean fechado){
		foiFechado = fechado;
	}
	
	public boolean foiFechado(){
		return foiFechado;
	}
	
	//Metodos exijidos pelas Interfaces implementadas
	public void mouseDragged(MouseEvent arg0) {}	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {
		setVisible(false);
	}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

	public void keyTyped(KeyEvent arg0) {
		
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_ENTER)
			this.dispose();		
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
