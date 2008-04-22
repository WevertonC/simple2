package util;
/*
 * Classe MyScrollPane
 * 
 * version 1.0
 * 
 * Data 09/11/2005
 * 
 * CopyRight FePDI all rigths reserved 
 */ 

import janelas.SImPLe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.RenderedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;





import com.sun.media.jai.widget.DisplayJAI;

import coordenadas.AlgoritmosImagem;
import coordenadas.ColorTipWindow;
import coordenadas.HistogramJWindow;
/**
 * Classe que mostra uma MyScrollPane
 * 
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class MyScrollPane extends JScrollPane implements MouseMotionListener, MouseListener,  PropertyChangeListener {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage imagem;
	private ColorTipWindow colorWindow;
	private RenderedImage originalImage;
	private boolean showColor = false;
	
	public static int x1, x2, y1, y2, px, py;
	private int[][] matrizOriginal;
	private BufferedImage copiada;
	private boolean selecionou;
	private boolean janelaSelecionada = false;
	private boolean clickEsquerda = false;	
	public MyPopUp popUp = null;
	private boolean exibirPixel = false;
	private boolean estaExbindoPerfis = false;
			
	/**
	 * Construtor da classe
	 * @param image imagem que sera exibida no scrollPane
	 */
	public MyScrollPane(RenderedImage image, MyPopUpListener listener) {
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setImage(image);
		colorWindow = new ColorTipWindow();
		imagem = MyBufferedImage.makeBufferedImage(image);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		selecionou = true;
		this.addMouseListener(listener);
		this.popUp = listener.getPopUp();
	}
	
	public RenderedImage getImage() {
		return originalImage;
	}
	
	/**
	 * Metodo que modifica a imagem
	 * @param image
	 */
	public void setImage(RenderedImage image) {
		originalImage = image;
		imagem = MyBufferedImage.makeBufferedImage(image);
		setViewportView(new DisplayJAI(image));
	}
	
	/**
	 * Recupera a imagem
	 * @return
	 */
	public RenderedImage getRenderedImage(){
		return originalImage;
	}
	
	/**
	 * Recupra um buffer da image
	 * @return
	 */
	public BufferedImage getBufferedImage(){
		return imagem;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isEstaExbindoPerfis() {
		return estaExbindoPerfis;
	}

	/**
	 * 
	 * @param estaExbindoPerfis
	 */
	public void setEstaExbindoPerfis(boolean estaExbindoPerfis) {
		this.estaExbindoPerfis = estaExbindoPerfis;
	}
	
	/**
	 * Captura os eventos atraves do movimento do mouse 
	 */
	public void mouseMoved(MouseEvent event) {
		if(isExibirPixel()){
			colorWindow.setCoords(new Point(event.getX(), event.getY()));
			if (event.getX() > imagem.getWidth() || event.getY() > imagem.getHeight()) {
				
				return;
			}
			int pixel = 0;
			try{
				pixel = imagem.getRGB(event.getX(), event.getY());
			} catch (Exception e) {
				try{
					pixel = imagem.getRGB(event.getX()-1, event.getY()-1);
				} catch (Exception a) {
					pixel = imagem.getRGB(event.getX()-2, event.getY()-2);
				}
			}
			int[] components = new int[3];
			AlgoritmosImagem.getRGBComponents(components, pixel);
			Color c = new Color(components[0], components[1], components[2]);
			colorWindow.setColor(c);
			if (!colorWindow.isVisible() && showColor)
				colorWindow.setVisible(true);
			colorWindow.setLocation(this.getLocationOnScreen().x + event.getX()
					+ 15, (this.getLocationOnScreen().y + event.getY() + 10));
		}

		if(event.getButton() == MouseEvent.BUTTON3){
			if(estaExbindoPerfis){
				popUp.setVisible(false);
			}
		}
	}
	
	/**
	 * Metodo que recurepa as coordenadas da imagem enquanto o botao do mouse esta sendo apertado
	 */
	public void mouseDragged(MouseEvent e) {
		if(clickEsquerda){
			x2 = e.getX();
			y2 = e.getY();
			repaint();
			selecionou = false;
		}
		if(e.getButton() == MouseEvent.BUTTON3){
			if(estaExbindoPerfis){
				popUp.setVisible(false);
			}
		}
		
	}
	
	/**
	 * Metodo que desabilita os botoes reponsavel por editar a imagem
	 */
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			MouseListener[] listener = getMouseListeners();
			HistogramJWindow j = null;
			for(int i =0; i< listener.length; i++){
				if(listener[i] instanceof HistogramJWindow) j = (HistogramJWindow) listener[i];
			}
			if(j != null){
				removeMouseListener(j);
				estaExbindoPerfis = false;
			}
		}
		
		if(e.getButton() == MouseEvent.BUTTON3){
			if(estaExbindoPerfis){
				popUp.setVisible(false);
			}
		}
		
		getRecortar().setEnabled(false);
		getRecortarButton().setEnabled(false);
		getCopiar().setEnabled(false);
		getCopiarButton().setEnabled(false);
		getColar().setEnabled(false);
		getColarButton().setEnabled(false);
		if(popUp != null){
			popUp.recortar.setEnabled(false);
			popUp.copiar.setEnabled(false);
			popUp.colar.setEnabled(false);
		}		
	}
		
	/**
	 * Metodo que recupera a coordenada da imagem quando o botao do mouse eh apertado
	 */
	public void mousePressed(MouseEvent e) {		
		if(e.getButton() == MouseEvent.BUTTON1){
			x1 = e.getX();
			y1 = e.getY();
			getRecortar().setEnabled(true);
			getRecortarButton().setEnabled(true);
			getCopiar().setEnabled(true);
			getCopiarButton().setEnabled(true);
			getColar().setEnabled(false);
			getColarButton().setEnabled(false);
			selecionou = true;
			repaint();
			clickEsquerda = true;
			if(popUp != null){
				popUp.recortar.setEnabled(true);
				popUp.copiar.setEnabled(true);
			}
		}		
		else clickEsquerda = false;
		if(e.getButton() == MouseEvent.BUTTON3){
			if(estaExbindoPerfis){
				popUp.setVisible(false);
			}
		}
	}
	
	/**
	 * Metodo que recupera a coordenada do imagem quando o botao do mouse eh solto
	 */
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			x2 = e.getX();
			y2 = e.getY();
			Point p = this.getLocation();
			px = p.x;
			py = p.y;
			selecionou = false;			
		}
		if(e.getButton() == MouseEvent.BUTTON3){
			if(estaExbindoPerfis){
				popUp.setVisible(false);
			}
		}
	}
	
	/**
	 * Metodo que captura os eventos atraves do movimento do mouse 
	 */
	public void mouseEntered(MouseEvent e) {
		if(janelaSelecionada){
			MouseListener[] listener = getMouseListeners();
			HistogramJWindow j = null;
			for(int i = 0; i < listener.length; i++){
				if(listener[i] instanceof HistogramJWindow) j = (HistogramJWindow) listener[i];
			}
			if(j != null){
				j.setVisible(true);
				estaExbindoPerfis = true;
			}
		}
//		if(!info.hide){
//			x1 = e.getX()+ this.getX();
//			y1 = e.getY()+120 + this.getY();
//			info.setLocation(x1,y1);
//			info.setVisible(true);
//		}
//		//info.setVisible(false);
	}
	
	/**
	 * Metodo que captura os eventos atraves do movimento do mouse 
	 */
	public void mouseExited(MouseEvent e) {
		if (colorWindow.isVisible())
			colorWindow.setVisible(false);
		MouseListener[] listener = getMouseListeners();
		if (janelaSelecionada){
			HistogramJWindow j = null;
			for(int i =0; i < listener.length; i++){
				if(listener[i] instanceof HistogramJWindow) j = (HistogramJWindow) listener[i];
			}
			if(j != null) j.setVisible(false);
		}		
//		info.setVisible(false);	
	//	info.hide = false;
	}
	
	/**
	 * Captura o evento do PropertyChangeListener
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("show-color"))
			showColor = ((Boolean) evt.getNewValue()).booleanValue();
	}
	
	/**
	 * Metodo responsavel por pintar o retangulo que representa a area a ser copiada ou recortada
	 */
	public void paint(Graphics g) {
		super.paint(g);
		verificaCoordenadas();
		g.setColor(Color.white);
		//Graphics2D g2 = (Graphics2D)g;
		g.setXORMode(Color.black);
		//g2.setComposite(makeComposite(4*0.1F));
		if (!selecionou) g.drawRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x1-x2),Math.abs(y1-y2)); 
	}
	
	/**
	 * Metodo que verifica os limites da imagem ou seja as coordenadas limites da imagem, atribuindo um novo
	 * valor as coordenadas estejam fora dos limites.
	 */
	private void verificaCoordenadas() { 
		if (x1 <= 0) x1 = 1;
		if (y1 <= 0) y1 = 1;
		if (x2 <= 0) x2 = 1;
		if (y2 <= 0) y2 = 1;
		if (x1 >= imagem.getWidth(null)) x1 = imagem.getWidth(null);
		if (y1 >= imagem.getHeight(null)) y1 = imagem.getHeight(null);
		if (x2 >= imagem.getWidth(null)) x2 = imagem.getWidth(null);
		if (y2 >= imagem.getHeight(null)) y2 = imagem.getHeight(null);
	}
	
	/**
	 * Metodo que converte a matriz de pixel em um vetor de pixel
	 * @param mat matriz a ser convertida
	 * @param largura largura da matriz
	 * @param altura altuta da matriz
	 * @return vetor de com pixels da matriz, com tamanho largura*altura
	 */
	private int[] converteMatrizVetor(int[][] mat, int largura, int altura) {
		int[] vetorPixels = new int[largura*altura];
		int cont = 0;
		for (int i = 0; i < altura; i++) 
			for (int j = 0; j < largura; j++) 
				vetorPixels[cont++] = matrizOriginal[i][j];
		return vetorPixels;
	}
	
	/**
	 * Metodo que carrega a matriz de pixel da imagem a ser editada
	 * @return matriz de pixel da imagem
	 */
	private int[][] carregaMatrizPixels() {
		ImageIcon original = new ImageIcon(imagem);
		int [] pixelsOri = new int[original.getIconWidth()*original.getIconHeight()];
		PixelGrabber pg1 = new PixelGrabber(original.getImage(),0,0,original.getIconWidth(),original.getIconHeight(),pixelsOri,0,original.getIconWidth());
		try {
			pg1.grabPixels();
		} catch (InterruptedException e) {}
		matrizOriginal = new int[original.getIconHeight()][original.getIconWidth()];
		int cont = 0;
		for (int i = 0; i < original.getIconHeight(); i++) 
			for (int j = 0; j < original.getIconWidth(); j++) 
				matrizOriginal[i][j] = pixelsOri[cont++];
		return matrizOriginal;
	}
	
	/**
	 * Metodo que transfere a imagem copiada ou recortada
	 * @param copiada
	 */
	public void transferirImagemCopiada(BufferedImage copiada) {
		this.copiada = copiada;
	}
	
	/**
	 * Metodo que transfere a imagem original
	 * @param original
	 */
	public void transferirImagemOriginal(RenderedImage original) {
		this.setImage(original);
	}
	
	/**
	 * Metodo responsavel por setar a nova imagem apos ser copiada ou recortada
	 * @param copiada imagem copiada ou recortada
	 * @param posTelaX coordenada x da imagem na tela
	 * @param posTelaY coordenada y da imagem na tela
	 * @param supx1 coordenada superior esquerda x da imagem
	 * @param supy1 coordenada superior esquerda y da imagem
	 * @param largura largura da imagem copiada ou recortada
	 * @param altura altura da imagem  copiada ou recortada
	 */
	public void setNovaImagem(ImageIcon copiada ,int posTelaX, int posTelaY, int supx1, int supy1, int largura, int altura) {
		this.copiada = MyBufferedImage.makeBufferedImage(copiada.getImage());
		this.setImage((RenderedImage)colarImagem(posTelaX, posTelaY, supx1, supy1, largura, altura));
	}
	
	/**
	 * Metodo responsavel por colar a imagem copiada/recortada na imagem original
	  *@param posTelaX coordenada x da imagem na tela
	 * @param posTelaY coordenada y da imagem na tela
	 * @param supx1 coordenada superior esquerda x da imagem
	 * @param supy1 coordenada superior esquerda y da imagem
	 * @param largura largura da imagem copiada ou recortada
	 * @param altura altura da imagem  copiada ou recortada
	 * @return
	 */
	public Image colarImagem(int posTelaX, int posTelaY, int supx1, int supy1, int largura, int altura) {
		carregaMatrizPixels();
		ImageIcon original = new ImageIcon(imagem);
		ImageIcon colada = new ImageIcon(copiada);
		int[] pixelsCop = new int[colada.getIconWidth()*colada.getIconHeight()];
		PixelGrabber pg = new PixelGrabber(colada.getImage(),0,0,colada.getIconWidth(),colada.getIconHeight(),pixelsCop,0,colada.getIconWidth());
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {}
		//matriz com a imagem copiada/recortada
		int[][] matCopiada = new int[colada.getIconHeight()][colada.getIconWidth()];
		//varre imagem copiada/recortada
		int cont = 0;
		for (int i = 0; i < colada.getIconHeight(); i++) {
			for (int j = 0; j < colada.getIconWidth(); j++) {
				matCopiada[i][j] = pixelsCop[cont++];
			}
		}
		//coloca a imagem copiada na imagem original, na sua devida posicao
		//5 e 25 sao constantes da borda do intenalFrame
		int posx = 0;
		String os = System.getProperty("os.name").toUpperCase();
		int constY, constX;
		if (os.equals("WINDOWS XP")) {
			constY = 31;
			constX = 6;
		}
		else {
			constY = 28;
			constX = 5;
		}
		for (int i = supy1-posTelaY-constY; i < supy1-posTelaY+altura-constY; i++) {
			int posy = 0;
			for (int j = supx1-posTelaX-constX; j < supx1-posTelaX+largura-constX; j++) 
				matrizOriginal[i][j] = matCopiada[posx][posy++];
			posx++;
		}
		//trasnforma a matriz da nova imagem(com a imagem copiada) em um vetor
		int[] pixelsOri = converteMatrizVetor(matrizOriginal,imagem.getWidth(),imagem.getHeight());
		//converte o vetor de pixel em uma imagem
		JLabel label = new JLabel();
		Image novaImagem = label.createImage(new MemoryImageSource(original.getIconWidth(),original.getIconHeight(),
													pixelsOri,0,original.getIconWidth()));
		return MyBufferedImage.makeBufferedImage(novaImagem);
	}
	
	public Image getImagem() {
		return imagem;
	}
	
	/**
	 * Metodo que modifica a variavel booleana para true
	 */
	public void selecionadoTrue() {
		this.selecionou = true;
	}
	
	/**
	 * Metodo que recupera o JMenuItem recortar
	 * @return JMenuItem recortar
	 */
	private static JMenuItem getRecortar() {
		return SImPLe.recortar;
	}
	
	/**
	 * Metodo que recupera o JButton recortar
	 * @return JButton recortar
	 */
	private static JButton getRecortarButton() {
		return SImPLe.buttonRecortar;
	}
	
	/**
	 * Metodo que recupera o JMenuItem copiar
	 * @return JMenuItem copiar
	 */
	private static JMenuItem getCopiar() {
		return SImPLe.copiar;
	}
	
	/**
	 * Metodo que recupera o JButton copiar
	 * @return JButton copiar
	 */
	private static JButton getCopiarButton() {
		return SImPLe.buttonCopiar;
	}
	
	/**
	 * Metodo que recupera o JMenuItem colar
	 * @return JMenuItem colar
	 */
	private static JMenuItem getColar() {
		return SImPLe.colar;
	}
	
	/**
	 * Metodo que recupera o JButton colar
	 * @return JButton colar
	 */
	private static JButton getColarButton() {
		return SImPLe.buttonColar;
	}
	
	/**
	 * Metodo que modifica a janela selecionada
	 * @param janelaSelecionada True se ela foi selecionada e false caso contrario
	 */
	public void setJanelaSelecionada(boolean janelaSelecionada) {
		this.janelaSelecionada = janelaSelecionada;
	}

	public boolean isExibirPixel() {
		return exibirPixel;
	}

	public void setExibirPixel(boolean exibirPixel) {
		this.exibirPixel = exibirPixel;
	}
}