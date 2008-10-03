package simple.ui.janelas;

/*
 * HistogramaCinza
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import simple.ajuda.Ajuda;
import simple.facade.Facade;
import simple.manipulacoes.util.AjudaButton;

/**
 * Classe que cria a janela do Histograma
 * @version 1.0 20/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class JanelaHistogramaColorido extends JDialog implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = 1L; //Numero de série da classe
	
	public static final int GRAYBANDA = 3;

	private JPanel histogramPane, yAxisPanel;
	private JScrollPane histogramScrollPane;
	private JLabel histogramLabel, yAxisMaxValue;
	private JLabel mediaLabel, varianciaLabel, desvioPadraoLabel, l;
	private Facade facade;
	private JButton ajuda;
	
	/**
	 * Construtor que Cria a janela que mostra os histogramas da imagem
	 * @param imagem A imagem da vez
	 */
	public JanelaHistogramaColorido(Image imagem, Facade facade) {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 579/2,d.height/2 - 495/2);
		setLayout(null);
		setSize(579,495);
		setTitle("Histograma Colorido");
		setModal(true);
		setLayout(null);
		setResizable(false);
		facade.criarHistograma(imagem);
		this.facade = facade;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		createGUI();
		setVisible(true);
		repaint();
	}
		
	/**
	 * Metodo createAndShowGUI que Cria os painéis e os gráficos do histograma
	 */
	private void createGUI() {				
		histogramPane = createHistogramPane();
		histogramPane.setBounds(10,70,554,322);
				
		ajuda = new AjudaButton();
		ajuda.setActionCommand("?");
		ajuda.addActionListener(this);
		ajuda.addKeyListener(this);
		ajuda.setLocation(549,1);
		
		l = new JLabel();
		l.setBounds(545,392,60,15);
				
		mediaLabel = new JLabel("Média: ");
		mediaLabel.setBounds(10,405,250,15);
		
		varianciaLabel = new JLabel("Variância: ");
		varianciaLabel.setBounds(10,422,250,15);
		
		desvioPadraoLabel = new JLabel("Desvio Padrão: ");
		desvioPadraoLabel.setBounds(10,437,250,15);
				
		getContentPane().add(ajuda);
		getContentPane().add(l);
		getContentPane().add(histogramPane);
		getContentPane().add(mediaLabel);
		getContentPane().add(varianciaLabel);
		getContentPane().add(desvioPadraoLabel);
	}
	
	public void paint(Graphics g){
		gerarHistograma(facade.getGrayHistograma(),GRAYBANDA);
		Image imagem = new ImageIcon("Resource/Icones/help.JPG").getImage();
		ajuda.getGraphics().drawImage(imagem,0,0,this);
		Graphics s = histogramScrollPane.getGraphics();
		s.setColor(new Color(113,153,176));
		s.drawRect(0,0,histogramPane.getWidth()-2,histogramPane.getHeight()-15);
	}
	
	/**
	 * Metodo createHistogramPane que Cria a janela com os gráficos da imagem
	 * @return O painel com os gráficos
	 */
	private JPanel createHistogramPane() {
		histogramPane = new JPanel();
		histogramPane.setLayout(new BorderLayout());
		histogramPane.add(createHistogramYAxis(0),BorderLayout.NORTH);
		histogramLabel = new JLabel();
		histogramLabel.setPreferredSize(new Dimension(550,300));
		histogramLabel.setBackground(Color.WHITE);
		histogramLabel.setOpaque(true);
		histogramScrollPane = new JScrollPane(histogramLabel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
											  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		histogramPane.add(histogramScrollPane);
		return histogramPane;
	}
	
	/**
	 * Metodo drawHistogramAxis que Pinta o eixo X do grafico 
	 * @param histo PO histograma da imagem
	 */
	private void drawHistogramAxis(JLabel histo) {
		Graphics2D canvas = (Graphics2D)histo.getGraphics();
		canvas.setPaint(Color.black);
		canvas.drawLine(1,histo.getHeight()-1,1,(int) (histo.getHeight()*0.1));
		canvas.drawLine(1,histo.getHeight()-1,(int) (histo.getWidth()*0.95),histo.getHeight()-1);
	}
	
	/**
	 * Metodo createHistogramYAxis que Cria o eixo Y do gráfico
	 * @return O eixo Y do gráfico
	 */
	public Component createHistogramYAxis(int maxValue) {
		yAxisPanel = new JPanel();
		yAxisPanel.setLayout(new BorderLayout());
		yAxisMaxValue = new JLabel(Integer.toString(maxValue));
		yAxisPanel.add(yAxisMaxValue,BorderLayout.PAGE_START);
		return yAxisPanel;
	}
	
	/**
	 * Metodo actionPerformed que Captura o evento
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("?")) {
			new Ajuda(Ajuda.AJUDA_HISTOGRAMA_COLORIDO);
		}
	}
	
	/**
	 * Metodo gerarHistograma que Modifica o histograma desejado
	 * @param histo O novo histograma
	 */
	private void gerarHistograma(int[] histo, int corBanda) {
		final int X_OFFSET = 2;
		int maxYValue = facade.getMaiorValor(histo);
		int maxYPx = (int) (this.histogramLabel.getHeight()*0.9);
		int xDraw = 0;
		
		Graphics2D canvas = (Graphics2D) this.histogramLabel.getGraphics();
		Color c = createColor(corBanda);
		canvas.setBackground(Color.white);
		canvas.clearRect(0,0,this.histogramLabel.getWidth(),this.histogramLabel.getHeight());
		drawHistogramAxis(this.histogramLabel);
		canvas.setColor(c);
		
		for(int i=0; i < histo.length; i++) {
			xDraw += X_OFFSET;
			int yValue = calcHistogramYPixelValue(histo[i],maxYPx,maxYValue);
			canvas.drawLine(xDraw,this.histogramLabel.getHeight()-1,xDraw,yValue);
		}
		yAxisMaxValue.setText(Integer.toString(maxYValue));
		l.setText("255");
		mediaLabel.setText("Média: "+ facade.getMedia(histo));
		varianciaLabel.setText("Variância: "+ facade.getVariancia(histo));
		desvioPadraoLabel.setText("Desvio Padrão: "+ facade.getDesvioPadrao(histo));		
	}
	
	/**
	 * Metodo createColor que Transforma a acao do click no botao em cores dos histogramas 
	 * @param bandClicked O número da cor desejada
	 * @return A cor da banda
	 */
	private Color createColor(int bandClicked) {
		if(bandClicked == 3) return Color.DARK_GRAY;
		return Color.black;
	}
	
	/**
	 * Metodo calcHistogramYPixelValue que Calcula o histograma de um pixel Y
	 * @param valor O valor do pixel
	 * @param maxYInPix O maior pixel do intervalo
	 * @param maxYvalue O maior valor do intervalo
	 * @return O maior valor do histograma
	 */
	private int calcHistogramYPixelValue(int valor, int maxYInPix, int maxYvalue) {
		if(maxYvalue == 0)
			return histogramLabel.getHeight();
		return this.histogramLabel.getHeight()-(valor*maxYInPix/maxYvalue);
	}
	
	/**
	 * Metodo captura os eventos do teclado para ENETER e  ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.dispose();
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			gerarHistograma(facade.getGrayHistograma(),GRAYBANDA);
		}
	}
	
	//Metodo apenas para implementação da interface
	public void keyReleased(KeyEvent e){}	
	public void keyTyped(KeyEvent e){}	
}
