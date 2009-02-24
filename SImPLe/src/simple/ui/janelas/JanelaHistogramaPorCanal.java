package simple.ui.janelas;

/*
 * HistogramaIU
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
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import simple.ajuda.Ajuda;
import simple.facade.Facade;
import simple.manipulacoes.util.AjudaButton;

/**
 * Classe que cria a janela do Histograma
 * 
 * @version 1.0 20/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 * 
 * @author Renato Miceli Costa Ribeiro
 */
public class JanelaHistogramaPorCanal extends JDialog implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L; // Numero de série da
	// classe

	public static final int REDBANDA = 0;
	public static final int GREENBANDA = 1;
	public static final int BLUEBANDA = 2;

	private JPanel histogramPane;
	private JScrollPane histogramScrollPane;
	private JLabel histogramLabel;
	private JLabel mediaLabel, varianciaLabel, desvioPadraoLabel;
	private JPanel yAxisPanel;
	private JPanel xAxisPanel;
	private JLabel yAxisMaxValue;
	private JRadioButton redRadioButton, greenRadioButton, blueRadioButton;
	private JButton buttonAjuda;
	private Facade facade;

	/**
	 * Construtor que Cria a janela que mostra os histogramas da imagem
	 * 
	 * @param imagem
	 *            A imagem da vez
	 */
	public JanelaHistogramaPorCanal(Image imagem, Facade facade) {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width / 2 - 579 / 2, d.height / 2 - 495 / 2);
		setSize(579, 495);
		setTitle("Histograma Por Canal");
		setModal(true);
		setLayout(null);
		setResizable(false);
		facade.criarHistograma(imagem);
		this.facade = facade;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		createAndShowGUI();
	}

	/**
	 * Metodo createAndShowGUI que Cria os painéis e os gráficos do histograma
	 */
	private void createAndShowGUI() {
		JLabel l = new JLabel("Selecionar Canal");
		l.setBounds(15, 15, 120, 15);

		histogramPane = createHistogramPane();
		histogramPane.setBounds(10, 70, 554, 335);

		redRadioButton = new JRadioButton("RED");
		redRadioButton.setForeground(Color.RED);
		redRadioButton.addActionListener(this);
		redRadioButton.setName("redButton");
		redRadioButton.setBounds(35, 40, 65, 25);

		greenRadioButton = new JRadioButton("GREEN");
		greenRadioButton.setForeground(Color.GREEN);
		greenRadioButton.addActionListener(this);
		greenRadioButton.setName("greenButton");
		greenRadioButton.setBounds(100, 40, 65, 25);

		blueRadioButton = new JRadioButton("BLUE");
		blueRadioButton.setForeground(Color.BLUE);
		blueRadioButton.addActionListener(this);
		blueRadioButton.setName("blueButton");
		blueRadioButton.setBounds(165, 40, 65, 25);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(redRadioButton);
		buttonGroup.add(greenRadioButton);
		buttonGroup.add(blueRadioButton);

		buttonAjuda = new AjudaButton();
		buttonAjuda.setActionCommand("?");
		buttonAjuda.addActionListener(this);
		buttonAjuda.setLocation(549, 1);

		redRadioButton.addKeyListener(this);
		greenRadioButton.addKeyListener(this);
		blueRadioButton.addKeyListener(this);
		buttonAjuda.addKeyListener(this);

		mediaLabel = new JLabel("Média: ");
		mediaLabel.setBounds(10, 405, 250, 15);

		varianciaLabel = new JLabel("Variância: ");
		varianciaLabel.setBounds(10, 422, 250, 15);

		desvioPadraoLabel = new JLabel("Desvio Padrão: ");
		desvioPadraoLabel.setBounds(10, 437, 250, 15);

		getContentPane().add(l);
		getContentPane().add(buttonAjuda);
		getContentPane().add(redRadioButton);
		getContentPane().add(greenRadioButton);
		getContentPane().add(blueRadioButton);
		getContentPane().add(histogramPane);
		getContentPane().add(mediaLabel);
		getContentPane().add(varianciaLabel);
		getContentPane().add(desvioPadraoLabel);

		setVisible(true);
	}

	/**
	 * Metodo createHistogramPane que Cria a janela com os gráficos da imagem
	 * 
	 * @return O painel com os gráficos
	 */
	private JPanel createHistogramPane() {
		histogramPane = new JPanel();
		histogramPane.setLayout(new BorderLayout());
		histogramPane.add(createHistogramYAxis(0), BorderLayout.NORTH);
		histogramPane.add(createHistogramXAxis(), BorderLayout.SOUTH);
		histogramLabel = new JLabel();
		histogramLabel.setPreferredSize(new Dimension(550, 300));
		histogramLabel.setBackground(Color.WHITE);
		histogramLabel.setOpaque(true);
		histogramScrollPane = new JScrollPane(histogramLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		histogramPane.add(histogramScrollPane);
		return histogramPane;
	}

	/**
	 * Metodo drawHistogramAxis que Pinta o eixo X do grafico
	 * 
	 * @param histo
	 *            PO histograma da imagem
	 */
	private void drawHistogramAxis(JLabel histo) {
		Graphics2D canvas = (Graphics2D) histo.getGraphics();
		canvas.setPaint(Color.black);
		canvas.drawLine(1, histo.getHeight() - 1, 1, (int) (histo.getHeight() * 0.1));
		canvas.drawLine(1, histo.getHeight() - 1, (int) (histo.getWidth() * 0.95), histo.getHeight() - 1);
	}

	/**
	 * Metodo createHistogramXAxis que Cria o eixo X do gráfico
	 * 
	 * @return O exixo X do gráfico
	 */
	private Component createHistogramXAxis() {
		xAxisPanel = new JPanel();
		xAxisPanel.setLayout(new BorderLayout());
		xAxisPanel.add(new JLabel("255"), BorderLayout.EAST);
		return xAxisPanel;
	}

	/**
	 * Metodo createHistogramYAxis que Cria o eixo Y do gráfico
	 * 
	 * @return O eixo Y do gráfico
	 */
	public Component createHistogramYAxis(int maxValue) {
		yAxisPanel = new JPanel();
		yAxisPanel.setLayout(new BorderLayout());
		yAxisMaxValue = new JLabel(Integer.toString(maxValue));
		yAxisPanel.add(yAxisMaxValue, BorderLayout.PAGE_START);
		return yAxisPanel;
	}

	/**
	 * Metodo actionPerformed que Captura o evento
	 */
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		if (actionCmd.equalsIgnoreCase("RED"))
			gerarHistograma(facade.getRedHistograma(), REDBANDA);
		else if (actionCmd.equalsIgnoreCase("GREEN"))
			gerarHistograma(facade.getGreenHistograma(), GREENBANDA);
		else if (actionCmd.equalsIgnoreCase("BLUE"))
			gerarHistograma(facade.getBlueHistograma(), BLUEBANDA);
		else if (actionCmd.equalsIgnoreCase("?")) {
			new Ajuda(Ajuda.AJUDA_HISTOGRAMA_CANAL);
		}
	}

	/**
	 * Metodo gerarHistograma que Modifica o histograma desejado
	 * 
	 * @param histo
	 *            O novo histograma
	 */
	private void gerarHistograma(int[] histo, int corBanda) {
		final int X_OFFSET = 2;
		int maxYValue = facade.getMaiorValor(histo);
		int maxYPx = (int) (this.histogramLabel.getHeight() * 0.9);

		Graphics2D canvas = (Graphics2D) this.histogramLabel.getGraphics();
		Color c = createColor(corBanda);
		canvas.setBackground(Color.white);
		canvas.clearRect(0, 0, this.histogramLabel.getWidth(), this.histogramLabel.getHeight());
		drawHistogramAxis(this.histogramLabel);

		int xDraw = 0;
		int yValue = calcHistogramYPixelValue(histo[0], maxYPx, maxYValue);
		for (int i = 1; i < histo.length; i++) {
			canvas.setColor(new Color((int) ((c.getRed() / 255.) * i), (int) ((c.getGreen() / 255.) * i), (int) ((c.getBlue() / 255.) * i)));
			canvas.drawLine(xDraw, this.histogramLabel.getHeight() - 1, xDraw, yValue);
			int newXDraw = xDraw + X_OFFSET;
			int newYValue = calcHistogramYPixelValue(histo[i], maxYPx, maxYValue);
			canvas.drawLine(xDraw, yValue, newXDraw, newYValue);
			xDraw = newXDraw;
			yValue = newYValue;
		}
		canvas.drawLine(xDraw, this.histogramLabel.getHeight() - 1, xDraw, yValue);
		this.yAxisMaxValue.setText(Integer.toString(maxYValue));
		mediaLabel.setText("Média: " + facade.getMedia(histo));
		varianciaLabel.setText("Variância: " + facade.getVariancia(histo));
		desvioPadraoLabel.setText("Desvio Padrão: " + facade.getDesvioPadrao(histo));
	}

	/**
	 * Metodo createColor que Transforma a acao do click no botao em cores dos
	 * histogramas
	 * 
	 * @param bandClicked
	 *            O número da cor desejada
	 * @return A cor da banda
	 */
	private Color createColor(int bandClicked) {
		if (bandClicked == 0)
			return Color.red;
		if (bandClicked == 1)
			return Color.green;
		if (bandClicked == 2)
			return Color.blue;
		return Color.black;
	}

	/**
	 * Metodo calcHistogramYPixelValue que Calcula o histograma de um pixel Y
	 * 
	 * @param valor
	 *            O valor do pixel
	 * @param maxYInPix
	 *            O maior pixel do intervalo
	 * @param maxYvalue
	 *            O maior valor do intervalo
	 * @return O maior valor do histograma
	 */
	private int calcHistogramYPixelValue(int valor, int maxYInPix, int maxYvalue) {
		if (maxYvalue == 0)
			return histogramLabel.getHeight();
		return this.histogramLabel.getHeight() - (valor * maxYInPix / maxYvalue);
	}

	/**
	 * Metodo captura os eventos do teclado para ENETER e ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.dispose();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (redRadioButton.isFocusOwner())
				gerarHistograma(facade.getRedHistograma(), REDBANDA);
			else if (greenRadioButton.isFocusOwner())
				gerarHistograma(facade.getGreenHistograma(), GREENBANDA);
			else if (blueRadioButton.isFocusOwner())
				gerarHistograma(facade.getBlueHistograma(), BLUEBANDA);
		}
	}

	// Metodo apenas para implementação da interface
	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}
