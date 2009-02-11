package simple.ui.janelas;

/*
 * FePDI
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.ArrayList;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import simple.manipulacoes.util.MyImage;
import simple.manipulacoes.util.MyJInternalFrame;
import simple.manipulacoes.util.MyScrollPane;
import simple.modules.propriedades.coordenadas.AlgoritmosImagem;
import simple.modules.propriedades.coordenadas.HistogramJWindow;
import simple.ui.menus.MenuAjuda;
import simple.ui.menus.MenuArquivo;
import simple.ui.menus.MenuClassificacao;
import simple.ui.menus.MenuEditar;
import simple.ui.menus.MenuImagem;
import simple.ui.menus.MenuOperacoes;
import simple.ui.menus.SimpleToolBar;
import simple.excecoes.ZoomException;
import simple.facade.Facade;

/**
 * Frame principal que controla todas as atividades realizadas pelo usuário
 * 
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class SImPLe extends JFrame implements ActionListener, ChangeListener,
		MouseWheelListener {


	private static final long serialVersionUID = -3860290027518266755L;

	private static SImPLe unicaInstancia = null;

	private JDesktopPane dp;

	private Facade facade;

	private SImPLe simple;

	private boolean showColors = false;

	public JMenuItem abrirNovo, abrir, fechar, salvar, salvarComo, exportar,
			refazer, desfazer, brilhoContraste;

	public static JMenuItem recortar, copiar, colar;

	public static JButton buttonRecortar, buttonCopiar, buttonColar, buttonDesfazer, buttonRefazer;
	
	private MenuArquivo meuMenuArquivo;
	private MenuEditar meuMenuEditar;
	private MenuImagem meuMenuImagem;
	private MenuAjuda meuMenuAjuda;
	private MenuOperacoes meuMenuOperacoes;
	private MenuClassificacao meuMenuClassificacao;
	
	// Dimensão da janela
	private Dimension screenSize;
	
	private JMenuBar menuBar;
	private SimpleToolBar simpleBar;

	/**
	 * Construtor da classe com Desing Pattern Singleton.
	 */
	private SImPLe() {
		
		/* Configura barra de menus */
		initializeFrame();
		menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createEmptyBorder());
		setJMenuBar(menuBar);

		// ***********************************************************************//
		// ****************           Menu de Acesso Direto       ****************//
		// ***********************************************************************//

		simpleBar = new SimpleToolBar(screenSize,this);
		

		getContentPane().add(simpleBar, BorderLayout.NORTH);
		getContentPane().add(dp, BorderLayout.CENTER);
		
		dp.setBackground(new Color(128, 128, 128));
		facade = Facade.getInstance();
		
		// ***********************************************************************//
		// ****************          Configura Copiar, Colar...   ****************//
		// ***********************************************************************//
		
	}
	
	private void configurarBotoesEditar() {
		this.recortar = meuMenuEditar.getRecortar();
		this.copiar = meuMenuEditar.getCopiar();
		this.colar = meuMenuEditar.getColar();
		this.buttonColar = simpleBar.getColar();
		this.buttonCopiar = simpleBar.getCopiar();
		this.buttonRecortar = simpleBar.getRecortar();
		this.salvar = meuMenuArquivo.getSalvar();
		this.desfazer = meuMenuEditar.getDesfazer();
		this.refazer = meuMenuEditar.getRefazer();
		this.buttonDesfazer = simpleBar.getDesfazer();
		this.buttonRefazer = simpleBar.getRefazer();
		
		this.buttonCopiar.setEnabled(false);
		this.buttonColar.setEnabled(false);
		this.buttonRecortar.setEnabled(false);
		
	}

	public void start(){
		
		configureMenuBar();
		configurarBotoesEditar();
		habilitaBotoes(false);
		this.setVisible(true);

		// Exibição da janela do programa

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				JInternalFrame[] frames = dp.getAllFrames();
				if (frames != null) {
					for (int i = 0; i < frames.length; i++) {
						if (verificaInstancia(frames[i]))
							((MyJInternalFrame) frames[i]).fecharAoSair();
					}
				}
				JanelaSair j = new JanelaSair("Deseja realmente sair?");
				String t = j.getAcaoSelecionada();
				if (t.equals("Sim"))
					System.exit(0);
				else if (t.equals("Nao")) {
					simple.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
					facade.setPosicao(0);
				}
			}
		});

		this.firePropertyChange("show-color", false, showColors);
		this.addMouseWheelListener(this);
		
	}

	private void initializeFrame() {
		simple = this;
		setIconImage(new ImageIcon("Resource/Icones/simpleIcon.jpg").getImage());
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - 800 / 2,	screenSize.height / 2 - 600 / 2);
		setSize(800, 600);
		setTitle("SImPLe - System for Imagem Processing Learning");
		setLayout(new BorderLayout());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		dp = new JDesktopPane();
		dp.setBorder(BorderFactory.createLoweredBevelBorder());
		
	}

	/**
	 * Método que inicializa os botoes do programa
	 */
	public void habilitaBotoes(boolean habilita) {
		
		meuMenuArquivo.habilitaBotoes(habilita);
		meuMenuImagem.habilitaBotoes(habilita);
		meuMenuOperacoes.habilitaBotoes(habilita);
		meuMenuClassificacao.habilitaBotoes(habilita);
		meuMenuEditar.setEnabled(habilita);
		simpleBar.habilitaBotoes(habilita);
		
	}

	/**
	 * Método responsável por criar uma única instância da Fachada.
	 * 
	 * @return A instancia da classe
	 */
	public static SImPLe getInstance() {
		if (unicaInstancia == null) {
			unicaInstancia = new SImPLe();
		}
		return unicaInstancia;
	}

	

	/**
	 * Método que verifica se existem frames abertos no Desktop Panel
	 * 
	 */
	public void verificaFrames() {
		if (dp.getAllFrames().length == 0) {
			habilitaBotoes(false);
			habilitaBotoes(false);
			recortar.setEnabled(false);
			buttonRecortar.setEnabled(false);
			copiar.setEnabled(false);
			buttonCopiar.setEnabled(false);
			colar.setEnabled(false);
			buttonColar.setEnabled(false);
			salvar.setEnabled(false);
		}
	}

	public void verificaUltimo() {
		if (dp.getAllFrames().length == 1)
			habilitaBotoes(false);
	}

	public boolean verificaInstancia(JInternalFrame frame) {
		if (frame instanceof MyJInternalFrame)
			return true;
		return false;
	}

	public JDesktopPane getDesktopPane() {
		return dp;
	}

	public void salvar() {
		if (verificaInstancia(dp.getSelectedFrame())) {
			MyJInternalFrame i = (MyJInternalFrame) dp.getSelectedFrame();
			if (i != null) {
				try {
					if (i.getFoiModificado() || facade.modificouImagem()) {
						i.setSalvou(true);
						facade.salvar(i);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, e.getMessage(),
							"ERRO!", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else
			JOptionPane.showMessageDialog(null,
					"Selecione uma imagem para realizar a operação!!!",
					"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
	}

	public void recortar() {

		if (verificaInstancia(dp.getSelectedFrame())) {
			boolean recortou = facade.recortar(this, getFrameAberto()
					.getImage());
			if (recortou) {
				colar.setEnabled(true);
				buttonColar.setEnabled(true);
				((MyJInternalFrame) dp.getSelectedFrame()).getScrollPane().popUp.colar.setEnabled(true);
				((MyJInternalFrame) dp.getSelectedFrame()).getScrollPane().popUp.salvar.setEnabled(true);
				((MyJInternalFrame) dp.getSelectedFrame()).setImage(facade.getImagemRecortada());
			} else {
				colar.setEnabled(false);
				buttonColar.setEnabled(false);
				((MyJInternalFrame) dp.getSelectedFrame()).getScrollPane().popUp.colar.setEnabled(false);
				((MyJInternalFrame) dp.getSelectedFrame()).getScrollPane().popUp.salvar.setEnabled(false);
			}
			recortar.setEnabled(false);
			buttonRecortar.setEnabled(false);
			copiar.setEnabled(false);
			buttonCopiar.setEnabled(false);
			((MyJInternalFrame) dp.getSelectedFrame()).getScrollPane().popUp.recortar.setEnabled(false);
			((MyJInternalFrame) dp.getSelectedFrame()).getScrollPane().popUp.copiar.setEnabled(false);
		}
		verificaRefazer();
	}

	public void copiar() {

		if (verificaInstancia(dp.getSelectedFrame())) {
			boolean copiou = facade.copiar(this, getFrameAberto().getImage());
			if (copiou) {
				colar.setEnabled(true);
				buttonColar.setEnabled(true);
				((MyJInternalFrame) dp.getSelectedFrame()).getScrollPane().popUp.colar.setEnabled(true);
			} else {
				colar.setEnabled(false);
				buttonColar.setEnabled(false);
				((MyJInternalFrame) dp.getSelectedFrame()).getScrollPane().popUp.colar.setEnabled(false);
			}
			recortar.setEnabled(false);
			buttonRecortar.setEnabled(false);
			copiar.setEnabled(false);
			buttonCopiar.setEnabled(false);
			((MyJInternalFrame) dp.getSelectedFrame()).getScrollPane().popUp.recortar.setEnabled(false);
			((MyJInternalFrame) dp.getSelectedFrame()).getScrollPane().popUp.copiar.setEnabled(false);
			((MyJInternalFrame) dp.getSelectedFrame()).getScrollPane().popUp.salvar.setEnabled(false);
		}
		verificaRefazer();

	}

	public void colar() {
		if (verificaInstancia(dp.getSelectedFrame())) {
			ImageIcon colada = new ImageIcon(facade.colar());
			new MyJWindow(this, colada, colada.getIconWidth(), colada.getIconHeight(), this.getLocation().x
					+ getFrameAberto().getX() + 10, this.getLocation().y + getFrameAberto().getY() + 105);
		}
	}

	public void fechar() {
		if (verificaInstancia(dp.getSelectedFrame())) {
			MyJInternalFrame i = (MyJInternalFrame) dp.getSelectedFrame();
			if (i != null) {
				i.fechar();
			}
		} else
			JOptionPane.showMessageDialog(null,
					"Selecione uma imagem para realizar a operação!!!",
					"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Captura os eventos realizados pelo usuário
	 */
	public void actionPerformed(ActionEvent evt) {

		String evento = evt.getActionCommand();
		verificaFrames();
		// ***********************************************************************//
		// ****************        Menu Arquivo                   ****************//
		// ***********************************************************************//
		if (evento.equals("Sair")) {
			meuMenuArquivo.sair();
		} 
		else if (evento.equals("Novo")) {
			meuMenuArquivo.novo();
		} 
		else if (evento.equals("Abrir")) {
			meuMenuArquivo.abrir();
		} 
		else if (evento.equals("Salvar")) {
			meuMenuArquivo.salvar();
		} 
		else if (evento.equals("Salvar Como")) {
			meuMenuArquivo.salvarComo();
		}
		else if (evento.equals("Comprimir")) {
			meuMenuArquivo.comprimir();
		}
		else if (evento.equals("Fechar")) {
			meuMenuArquivo.fechar();
		}

		// ***********************************************************************//
		// ****************            Menu Editar                ****************//
		// ***********************************************************************//
		
		else if (evento.equals("Desfazer")) {
			meuMenuEditar.desfazer();
		} 
		else if (evento.equals("Refazer")) {
			meuMenuEditar.refazer();
		}
		else if (evento.equals("Recortar")) {
			meuMenuEditar.recortar();
		}
		else if (evento.equals("Copiar")) {
			meuMenuEditar.copiar();
		}
		else if (evento.equals("Colar")) {
			meuMenuEditar.colar();
		}

		// ***********************************************************************//
		// ****************            Menu Imagem                ****************//
		// ***********************************************************************//
		
		else if (evento.equals("Colorido")) {
			meuMenuImagem.colorido();
		}
		else if (evento.equals("Por Canal")) {
			meuMenuImagem.porCanal();
		}
		else if (evento.equals("Linha")){
			onTheFlyProfile(AlgoritmosImagem.LINHA);
		}
		else if (evento.equals("Coluna")){
			onTheFlyProfile(!AlgoritmosImagem.LINHA);
		}
		else if(evento.equals("Brilho e Contraste")) {
			meuMenuImagem.brilhoEContraste();			
		}
		else if (evento.equals("Decomposição")) {
			meuMenuImagem.decomposicao();
		} else if (evento.equalsIgnoreCase("Combinação")) {
			meuMenuImagem.combinacao();
		}
		else if (evento.equals("Preto e Branco")){
			meuMenuImagem.pretoBranco();
		}
		else if (evento.equals("Escala de Cinza")) {
			meuMenuImagem.escalaCinza();
		} 
		else if (evento.equals("Pseudo Colorização")) {
			meuMenuImagem.pseudoColorizacao();
		}			
		else if (evento.equals("Requantização")) {
			meuMenuImagem.requantizacao();
		}
		else if (evento.equals("Ilusão 1")) {
			meuMenuImagem.ilusao1();
		} else if (evento.equals("Ilusão 2")){
			meuMenuImagem.ilusao2();
		} else if (evento.equals("Ilusão 3")){
			meuMenuImagem.ilusao3();
		} else if (evento.equals("Ilusão 4")){
			meuMenuImagem.ilusao4();
		} else if (evento.equals("Ilusão 5")){
			meuMenuImagem.ilusao5();
		} else if (evento.equals("Ilusão 6")){
			meuMenuImagem.ilusao6();
		} else if (evento.equals("Ilusão 7")){
			meuMenuImagem.ilusao7();
		} else if (evento.equals("Ilusão 8")){
			meuMenuImagem.ilusao8();
		} else if (evento.equals("Ilusão 9")){
			meuMenuImagem.ilusao9();
		} else if (evento.equals("Ilusão 10")){
			meuMenuImagem.ilusao10();
		}
		
		// **********************************************************************//
		// ****************             Menu Operações              *************//
		// **********************************************************************//

		
		// ***************************//
		// ****   Op Pontuais    *****//
		// ***************************//
		else if (evento.equals("Lógicas")) {
			meuMenuOperacoes.operacoesLogicas();
		}
		else if (evento.equals("Equalizar")){
			meuMenuOperacoes.equalizar();
		}
		else if (evento.equals("Ganho/Offset")) {
			meuMenuOperacoes.ganhoOffset();
		} 
		else if (evento.equals("Aritméticas")) {
			meuMenuOperacoes.operacoesAritmeticas();
		} 
		
		
		/* *****************************
		 *  Filtragem no dominio da freqüência
		 *  ****************************/		
		else if (evento.equals("Passa-Alta")){
			meuMenuOperacoes.passaAlta();
		} else if (evento.equals("Passa-Baixa")) {
			meuMenuOperacoes.passaBaixa();
		} else if (evento.equals("Passa-Faixa")){
			meuMenuOperacoes.passaFaixa();
		} else if (evento.equals("Rejeita-Faixa")){
			meuMenuOperacoes.rejeitaFaixa();
		}	else if (evento.equals("Reflectância")){
			meuMenuOperacoes.reflectancia();
		} else if (evento.equals("Iluminação")){
			meuMenuOperacoes.iluminacao();
		} else if (evento.equals("Gaussiano Passa-Alta")){
			meuMenuOperacoes.gaussianoPassaAlta();
		} else if (evento.equals("Gaussiano Passa-Baixa")){
			meuMenuOperacoes.gaussianoPassaBaixa();
		} else if (evento.equals("Butterworth Passa-Alta")){
			meuMenuOperacoes.butterworthPassaAlta();
		} else if (evento.equals("Butterworth Passa-Baixa")){
			meuMenuOperacoes.butterworthPassaBaixa();
		}
				
		// ***************************//
		// ******   Filtros    *******//
		// ***************************//
		
		else if (evento.equals("Média")) {
			meuMenuOperacoes.media();
		} 
		else if (evento.equals("Mediana")) {
			meuMenuOperacoes.mediana();
		}
		else if(evento.equals("Moda")) {
			meuMenuOperacoes.moda();
		}
		else if (evento.equals("Sobel")){
			meuMenuOperacoes.sobel();
		}
		else if (evento.equals("Prewit")) {
			meuMenuOperacoes.prewitt();	
		}
		else if (evento.equals("Roberts")) {
			meuMenuOperacoes.roberts();			
		}
		else if (evento.equals("Laplace")) {
			meuMenuOperacoes.laplace();		
		}
		else if (evento.equals("Gaussiano")) {
			meuMenuOperacoes.gaussiano();	
		}
		else if(evento.equals("Emboss")) {
			meuMenuOperacoes.emboss();
		}
		else if (evento.equals("Frei & Chen")){
			meuMenuOperacoes.freiChen();
		}
		else if (evento.equals("Filtro Bilateral")) {
			meuMenuOperacoes.filtroBilateral();
		}
		else if (evento.equals("Filtro Bilateral Adaptativo")) {
			meuMenuOperacoes.filtroBilateralAdaptativo();
		}

		
		// **********************************//
		// ****   Geradores de Ruído    *****//
		// **********************************//
		
		else if (evento.equals("Salt-and-Pepper")){
			meuMenuOperacoes.saltPepper();
		}
		else if (evento.equals("Ruído Gaussiano")){
			meuMenuOperacoes.ruidoGaussiano();
		}
		
		// ***************************//
		// ****   Geométricas    *****//
		// ***************************//
		else if (evento.equals("Rotacionar")) {
			meuMenuOperacoes.rotacionar();
		} else if (evento.equals("Redimensionar")) {
			meuMenuOperacoes.redimensionar();
		} 
		else if (evento.equals("Ampliar")) {
			meuMenuOperacoes.ampliar();
		} 
		else if (evento.equals("Zoom Mais")) {
			meuMenuOperacoes.zoomMais();

		} else if (evento.equals("Zoom Menos")) {
			meuMenuOperacoes.zoomMenos();

		}


		// **********************************************************************//
		// ****************           Menu Classificação            *************//
		// **********************************************************************//
		else if (evento.equals("Global")) {
			meuMenuClassificacao.global();
		} else if (evento.equals("Adaptativa Básica")) {
			meuMenuClassificacao.adaptativa();
		}
		else if (evento.equals("Crescimento de Região")){
			meuMenuClassificacao.crescimentoRegiao();
		}
		else if (evento.equals("Paralelepipedo")){
			meuMenuClassificacao.paralelepipedo();
		}
		
		// **********************************************************************//
		// ****************         Menu Morfologia                 *************//
		// **********************************************************************//
		else if (evento.equals("Erosão 4")){
			meuMenuOperacoes.erosao4();
		}
		else if (evento.equals("Erosão 8")){
			meuMenuOperacoes.erosao8();
		}
		else if (evento.equals("Dilatação 4")){
			meuMenuOperacoes.dilatacao4();
		}
		else if (evento.equals("Dilatação 8")){
			meuMenuOperacoes.dilatacao8();
		}
		else if (evento.equals("Abertura 4")){
			meuMenuOperacoes.abertura4();
		}
		else if (evento.equals("Abertura 8")){
			meuMenuOperacoes.abertura8();
		}
		else if (evento.equals("Fechamento 4")){
			meuMenuOperacoes.fechamento4();
		}
		else if (evento.equals("Fechamento 8")){
			meuMenuOperacoes.fechamento8();
		}
		
		
		// ***********************************************************************//
		// ****************              Menu Ajuda                  *************//
		// ***********************************************************************//
		else if (evento.equals("Sobre o SImPLe")){
			meuMenuAjuda.sobre();
		} else if (evento.equals("Tópicos de Ajuda")){
			meuMenuAjuda.topicos();
		}
			

		verificaRefazer();

	}

	/**
	 * Método que adiciona os nomes dos frames em uma lista
	 * 
	 * @param desejada
	 *            A lista de Imagens
	 * @param selecionada
	 *            O nome da imagem selecionada
	 * @return Uma string com o nome das imagens
	 */
	public String adicionaNomes(ArrayList<ImageIcon> desejada,
			ArrayList<String> selecionada) {
		JInternalFrame[] frames = dp.getAllFrames();
		String nomes = "";
		for (int i = 0; i < selecionada.size(); i++) {
			String nome = selecionada.get(i);
			for (int k = 0; k < frames.length; k++) {
				if (nome.equals(frames[k].getName())) {
					desejada.add(((MyJInternalFrame) frames[k]).getMyImage());
					nomes += frames[k].getName() + " ";
				}
			}
		}
		return nomes.trim();
	}

	/**
	 * Captura o evento da ChangedListener
	 */
	public void stateChanged(ChangeEvent evt) {
		if (evt.getSource() == meuMenuImagem.getCoordenadaPixel()) {
			meuMenuImagem.coordenadaPixel();
		}
	}

	/**
	 * Método responsável pela realização do perfil de linha e de coluna
	 * 
	 * @param linha
	 *            Uma booleana que se for true é perfil de linha e caso
	 *            contrário é de coluna
	 */
	private void onTheFlyProfile(boolean linha) {
		MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
		if (f != null) {
			HistogramJWindow hw = HistogramJWindow.getInstance(this, f, linha);
			f.getScrollPane().addMouseListener(hw);
			f.getScrollPane().addMouseMotionListener(hw);
			((MyScrollPane) f.getScrollPane()).setEstaExbindoPerfis(true);
			hw.setVisible(true);
		}
	}

	public MyJInternalFrame getFrameAberto() {
		return ((MyJInternalFrame) dp.getSelectedFrame());
	}

	public MyScrollPane scrollPane() {
		return ((MyJInternalFrame) dp.getSelectedFrame()).getScrollPane();
	}

	public void setSalvar(boolean valor) {
		salvar.setEnabled(valor);
	}

	public void setDesfazer(boolean valor) {
		desfazer.setEnabled(valor);
	}

	public void setRefazer(boolean valor) {
		refazer.setEnabled(valor);
	}

	public void verificaRefazer() {
		if (verificaInstancia(dp.getSelectedFrame())) {
			if (dp.getSelectedFrame() != null
					&& ((MyJInternalFrame) dp.getSelectedFrame())
							.getFoiModificado()) {
				refazer.setEnabled(true);
				desfazer.setEnabled(true);
				buttonDesfazer.setEnabled(true);
				buttonRefazer.setEnabled(true);
				salvar.setEnabled(true);
			} else {
				refazer.setEnabled(false);
				desfazer.setEnabled(false);
				buttonDesfazer.setEnabled(false);
				buttonRefazer.setEnabled(false);
				salvar.setEnabled(false);
			}
		}
	}

	/**
	 * Método que captura o evento de acordo com o mouse
	 */
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		try {
			if (dp.getSelectedFrame() != null
					&& verificaInstancia(dp.getSelectedFrame())) {
				if (arg0.getWheelRotation() < 0) {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					if (verificaInstancia(dp.getSelectedFrame())) {
						MyJInternalFrame f = (MyJInternalFrame) dp
								.getSelectedFrame();
						if (f != null) {
							try {
								RenderedImage i = facade.zoomMais(f
										.getMyImage());
								f.setImage(i);
							} catch (ZoomException e) {
								JOptionPane.showMessageDialog(this, e
										.getMessage(), "ERRO!",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					} else
						JOptionPane
								.showMessageDialog(
										null,
										"Selecione uma imagem para realizar a operação!!!",
										"ERRO NAS INFORMAÇÕES",
										JOptionPane.ERROR_MESSAGE);
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} else if (arg0.getWheelRotation() > 0) {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					if (verificaInstancia(dp.getSelectedFrame())) {
						MyJInternalFrame f = (MyJInternalFrame) dp
								.getSelectedFrame();
						if (f != null) {
							try {
								RenderedImage i = facade.zoomMenos(f
										.getMyImage());
								f.setImage(i);
							} catch (ZoomException e) {
								JOptionPane.showMessageDialog(this,
										"Tamanho minimo atingido",
										"ERRO NAS INFORMAÇÕES",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					} else
						JOptionPane
								.showMessageDialog(
										null,
										"Selecione uma imagem para realizar a operação!!!",
										"ERRO NAS INFORMAÇÕES",
										JOptionPane.ERROR_MESSAGE);
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}
		} catch (Exception e) {
		}
	}
	public void drawImage(Image image, String title) {
		try {
			MyJInternalFrame segmentacaoRegionGrowing = new MyJInternalFrame(this, facade,title,new MyImage(image));
			segmentacaoRegionGrowing.setFoiOperacao(true);
			this.addPropertyChangeListener(segmentacaoRegionGrowing.getScrollPane());
			dp.add(segmentacaoRegionGrowing,BorderLayout.CENTER);
			dp.setSelectedFrame(segmentacaoRegionGrowing);
			this.addPropertyChangeListener(segmentacaoRegionGrowing.getScrollPane());
			segmentacaoRegionGrowing.setSelected(true);	
			segmentacaoRegionGrowing.setLocation(facade.getPosicao(),facade.getPosicao());
			facade.incrementaPosicao();
			this.addPropertyChangeListener(segmentacaoRegionGrowing.getScrollPane());	
			this.firePropertyChange("show-color", false, showColors);
		}catch (Exception e){}
	}	
	
	
	
	
	
	public Facade getFacade(){
		return Facade.getInstance();
	}
	
	public void configureMenuBar(){
		meuMenuArquivo = new MenuArquivo(SImPLe.getInstance());
		meuMenuEditar = new MenuEditar(SImPLe.getInstance());
		meuMenuImagem = new MenuImagem(SImPLe.getInstance());
		meuMenuAjuda = new MenuAjuda(SImPLe.getInstance());
		meuMenuOperacoes= new MenuOperacoes(SImPLe.getInstance());
		meuMenuClassificacao = new MenuClassificacao(SImPLe.getInstance());
		
		menuBar.add(meuMenuArquivo);
		menuBar.add(meuMenuEditar);
		menuBar.add(meuMenuImagem);
		menuBar.add(meuMenuOperacoes);
		menuBar.add(meuMenuClassificacao);
		menuBar.add(meuMenuAjuda);
		setJMenuBar(menuBar);
	}
	
	public void closeIlusao(int ilusao){
		meuMenuImagem.closeIlusao(ilusao);
	}

	public boolean isShowColors() {
		return showColors;
	}

	public void setShowColors(boolean showColors) {
		this.showColors = showColors;
	}
	
	public void buildFrame(BufferedImage img, String opName){
		
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
		try {
			MyJInternalFrame imagem = new MyJInternalFrame(this, facade,f.getName() + opName,new MyImage(img));
			imagem.setFoiOperacao(true);
			this.addPropertyChangeListener(imagem.getScrollPane());
			dp.add(imagem,BorderLayout.CENTER);
			dp.setSelectedFrame(imagem);
			this.addPropertyChangeListener(imagem.getScrollPane());
			imagem.setSelected(true);	
			imagem.setLocation(facade.getPosicao(),facade.getPosicao());
			facade.incrementaPosicao();
			this.addPropertyChangeListener(imagem.getScrollPane());	
			this.firePropertyChange("show-color", false, showColors);
			f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		} catch (Exception e){				
		}
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	public BufferedImage getImagefromFrame(){
		MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
		return ((PlanarImage)JAI.create("AWTImage",f.getImage())).getAsBufferedImage();
	}
	
	public void changeFire(){
		firePropertyChange("show-color", false, showColors);
	}


}
