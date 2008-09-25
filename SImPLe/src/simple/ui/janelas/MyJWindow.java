package simple.ui.janelas;
/*
 * Classe MyJWindow
 * 
 * version 1.0
 * 
 * Data 22/11/2005
 * 
 * CopyRight FePDI all rigths reserved 
 * 
 */ 
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;






/**
* Classe que mostra um JWindow na tela
* @author Andre Cavalcante Hora
* @author Eduardo Santiago Moura
* @author Paulo de Tarso Firmino Junior
* @author Vinicius de Araujo Porto
* @author Yuska Paola Aguiar
*/
public class MyJWindow extends JWindow implements MouseListener, MouseMotionListener {
	
	private static final long serialVersionUID = 1L;
	private SImPLe fepdi;
	private JLabel label;
	private int posx1, posy1, x1, y1, largura, altura;
	private ImageIcon image;
	
	/**
	 * Construtor de um MyJWindow
	 * @param fepdi fepdi
	 * @param image imagem que pode ser arrastada pelo usuario
	 * @param largura largura da imagem arrastada
	 * @param altura altura da imagem arrastada
	 * @param posx1 coordenada superior esquerda x da posicao inicial da imagem arrastada 
	 * @param posy1 coordenada superior esquerda y da posicao inicial da imagem arrastada
	 */
	public MyJWindow(SImPLe fepdi, ImageIcon image, int largura, int altura, int posx1, int posy1) {
		super(fepdi);
		this.fepdi = fepdi;
		this.posx1 = posx1;
		this.posy1 = posy1;
		this.largura = largura;
		this.altura = altura;
		this.image = image;
		label = new JLabel(image);
		this.getContentPane().add(label);
		this.setSize(largura,altura);
		this.setLocation(posx1,posy1);
		this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setVisible(true);
	}
	
	/**
	 * Metodo sem uso(devido a implementacao da inteface)
	 */
	public void mouseClicked(MouseEvent e) {
	}
	
	/**
	 * Metodo sem uso(devido a implementacao da inteface)
	 */
	public void mouseExited(MouseEvent e) {
	}
	
	/**
	 * Metodo sem uso(devido a implementacao da inteface)
	 */
	public void mouseEntered(MouseEvent e) {
	}
	
	/**
	 * Metodo sem uso(devido a implementacao da inteface)
	 */
	public void mouseMoved(MouseEvent e) {	
	}
	
	/**
	 * Metodo responsavel por mover a JWindow selecionada pelo usuario
	 */
	public void mouseDragged(MouseEvent e) {
		x1 = e.getX()+this.getX();
		y1 = e.getY()+this.getY();
		this.setLocation(x1,y1);
	}
	
	/**
	 * Metodo responsavel por colar a imagem arrasta na imagem original
	 */
	public void mouseReleased(MouseEvent e) {
		if(fepdi.getFrameAberto() != null){
			int posTelaX = (fepdi.getFrameAberto()).getLocalizacaoNaTelaX();
			int posTelaY = (fepdi.getFrameAberto()).getLocalizacaoNaTelaY();
			try {
				(fepdi.scrollPane()).setNovaImagem(image,posTelaX,posTelaY,this.getX(),this.getY(),largura,altura);
				(fepdi.getFrameAberto()).setImage(fepdi.scrollPane().getImagem());
				fepdi.setSalvar(true);
				fepdi.getFrameAberto().setFoiOperacao(true);
				fepdi.getFrameAberto().setFoiModificado(true);
				this.dispose();
			}
			catch (Exception ex) {
				this.setLocation(fepdi.getLocation().x+fepdi.getFrameAberto().getX()+10,
						fepdi.getLocation().y+fepdi.getFrameAberto().getY()+105);
			}
		}
	}
	
	/**
	 * Metodo sem uso(devido a implementacao da inteface)
	 */
	public void mousePressed(MouseEvent e) {
		//this.setLocation(this.getX(),this.getY());
	}

	public int getPosx1() {
		return posx1;
	}

	public int getPosy1() {
		return posy1;
	}
}
