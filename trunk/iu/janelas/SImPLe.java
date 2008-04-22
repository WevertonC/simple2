package janelas;

/*
 * FePDI
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
import ilusaoOticaNovo.Ilusao1;
import ilusaoOticaNovo.Ilusao10;
import ilusaoOticaNovo.Ilusao2;
import ilusaoOticaNovo.Ilusao3;
import ilusaoOticaNovo.Ilusao4;
import ilusaoOticaNovo.Ilusao5;
import ilusaoOticaNovo.Ilusao6;
import ilusaoOticaNovo.Ilusao7;
import ilusaoOticaNovo.Ilusao8;
import ilusaoOticaNovo.Ilusao9;
import imagem.DeteccaoBordas;
import imagem.EqualizarImagem;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.ArrayList;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import manipularArquivo.AbrirImagem;
import manipularArquivo.BMPFilter;
import manipularArquivo.GIFFilter;
import manipularArquivo.ImageFileView;
import manipularArquivo.JPGFilter;
import manipularArquivo.PNGFilter;
import manipularArquivo.SalvarImagem;
import paralelepipedo.GerenciadorClassesParalelepipedo;
import paralelepipedo.Legenda;
import paralelepipedo.Opcao1;
import paralelepipedo.Opcao2;
import paralelepipedo.Opcao3;
import radiometricas.Binarize;
import ruido.GeradorDeRuido;
import ruido.RuidoGaussiano;
import util.DisplayDEM;
import util.Emboss;
import util.ImageContrast;
import util.MyBufferedImage;
import util.MyImage;
import util.MyJInternalFrame;
import util.MyScrollPane;
import util.RegionGrowingSegmentation;
import ajuda.JanelaAjuda;
import coordenadas.AlgoritmosImagem;
import coordenadas.HistogramJWindow;
import exceptions.FormatoInvalidoException;
import exceptions.ImageProcessingException;
import exceptions.ImagemNaoExisteException;
import exceptions.ImagemNaoPodeSalvarException;
import exceptions.ImagemNaoSelecionadaException;
import exceptions.NomeInvalidoException;
import exceptions.OperacaoAritmeticaException;
import exceptions.OperacaoLogicaException;
import exceptions.PseudoColorException;
import exceptions.RedimensionarException;
import exceptions.RotacionarException;
import exceptions.ZoomException;
import facade.Facade;

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

	private static final long serialVersionUID = 1L;

	private static SImPLe unicaInstancia = null;

	private JDesktopPane dp;

	private Facade facade;

	private SImPLe fepdi;

	private JRadioButtonMenuItem coordenadaPixel;

	private boolean showColors = false;

	private JMenu menuArquivo, menuEditar, menuImagem,
			menuOperacoes, menuClassificacao, menuAjuda, histograma;

	public JMenuItem abrirNovo, abrir, fechar, salvar, salvarComo, exportar,
			refazer, desfazer, brilhoContraste;

	public JButton buttonAbrir, buttonNovo, buttonSalvarComo, buttonCamera,
			buttonSccaner, buttonDesfazer, buttonRefazer, buttonZMais,
			buttonZMenos, buttonRotacionar, buttonRedimensionar, buttonHCanal,
			buttonHColorido, buttonExportar, buttonPLinha, buttonPColuna,
			buttonOpLogica, buttonOpAritP, buttonOpAritB, buttonEscCinza,
			buttonDecompor, buttonRecompor, buttonPseudo;

	public JButton buttonIlusao1, buttonIlusao2, buttonIlusao3, buttonIlusao4;

	private JMenu combDecompor, modoApresentacao;

	private JMenu perfilLC;

	private JMenuItem requantizacao;

	public static JMenuItem recortar, copiar, colar, ilusao1, ilusao2, ilusao3,
			ilusao4, ilusao5, ilusao6, ilusao7, ilusao8, ilusao9, ilusao10;

	public static JButton buttonRecortar, buttonCopiar, buttonColar;

	/**
	 * Construtor da classe com Desing Pattern Singleton.
	 */
	public SImPLe() {
		fepdi = this;
		setIconImage(new ImageIcon("Resource/Icones/fepdi.JPG").getImage());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - 800 / 2,	screenSize.height / 2 - 600 / 2);
		setSize(800, 600);
		setTitle("SImPLe - System for Imagem Processing Learning");
		setLayout(new BorderLayout());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		dp = new JDesktopPane();
		dp.setBorder(BorderFactory.createLoweredBevelBorder());

		JMenuBar menuBar = new JMenuBar();

		// ***********************************************************************//
		// ****************             Menu Arquivo              ****************//
		// ***********************************************************************//

		menuArquivo = new JMenu("Arquivo");
		menuArquivo.setMnemonic(KeyEvent.VK_A);

		abrirNovo = new JMenuItem("Novo", KeyEvent.ALT_MASK);
		abrirNovo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		abrirNovo.setMnemonic(KeyEvent.VK_N);
		abrirNovo.setIcon(new ImageIcon("Resource/Icones/new.gif"));
		abrirNovo.addActionListener(this);

		abrir = new JMenuItem("Abrir", KeyEvent.ALT_MASK);
		abrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		abrir.setMnemonic(KeyEvent.VK_A);
		abrir.setIcon(new ImageIcon("Resource/Icones/open.gif"));
		abrir.addActionListener(this);

		fechar = new JMenuItem("Fechar", KeyEvent.ALT_MASK);
		fechar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
		fechar.setMnemonic(KeyEvent.VK_F);
		fechar.setIcon(new ImageIcon("Resource/Icones/cancel.gif"));
		fechar.addActionListener(this);

		salvar = new JMenuItem("Salvar", KeyEvent.ALT_MASK);
		salvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		salvar.setMnemonic(KeyEvent.VK_S);
		salvar.setIcon(new ImageIcon("Resource/Icones/save16.gif"));
		salvar.addActionListener(this);
		salvar.setEnabled(false);

		salvarComo = new JMenuItem("Salvar Como", KeyEvent.ALT_MASK);
		salvarComo.setMnemonic(KeyEvent.VK_C);
		salvarComo.setIcon(new ImageIcon("Resource/Icones/save.gif"));
		salvarComo.addActionListener(this);

		exportar = new JMenuItem("Exportar", KeyEvent.VK_P);
		exportar.setActionCommand("Comprimir");
		exportar.setMnemonic(KeyEvent.VK_C);
		exportar.setIcon(new ImageIcon("Resource/Icones/exportar.gif"));
		exportar.addActionListener(this);

		JMenu capturar = new JMenu("Capturar Imagem");
		capturar.setMnemonic(KeyEvent.VK_P);
		capturar.setIcon(new ImageIcon("Resource/Icones/transparente.gif"));
		JMenuItem camera = new JMenuItem("Câmera", KeyEvent.ALT_MASK);
		camera.setIcon(new ImageIcon("Resource/Icones/webcam.gif"));
		camera.setMnemonic(KeyEvent.VK_C);
		camera.addActionListener(this);
		capturar.setEnabled(false);
		camera.setEnabled(false);

		JMenuItem scanner = new JMenuItem("Scanner", KeyEvent.ALT_MASK);
		scanner.setIcon(new ImageIcon("Resource/Icones/scanner.gif"));
		scanner.setMnemonic(KeyEvent.VK_S);
		scanner.addActionListener(this);
		scanner.setEnabled(false);

		capturar.add(camera);
		capturar.add(scanner);

		JMenuItem sair = new JMenuItem("Sair", KeyEvent.VK_R);
		sair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,ActionEvent.ALT_MASK));
		sair.setIcon(new ImageIcon("Resource/Icones/sair.gif"));
		sair.addActionListener(this);

		menuArquivo.add(abrirNovo);
		menuArquivo.add(abrir);
		menuArquivo.add(fechar);
		menuArquivo.addSeparator();
		menuArquivo.add(salvar);
		menuArquivo.add(salvarComo);
		menuArquivo.add(exportar);
		menuArquivo.addSeparator();
		menuArquivo.add(capturar);
		menuArquivo.addSeparator();
		menuArquivo.add(sair);

		// ***********************************************************************//
		// ****************            Menu Editar                ****************//
		// ***********************************************************************//

		menuEditar = new JMenu("Editar");
		menuEditar.setMnemonic(KeyEvent.VK_E);

		desfazer = new JMenuItem("Desfazer");
		desfazer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
		desfazer.setMnemonic(KeyEvent.VK_D);
		desfazer.setIcon(new ImageIcon("Resource/Icones/undo.gif"));
		desfazer.addActionListener(this);
		desfazer.setEnabled(false);

		refazer = new JMenuItem("Refazer");
		refazer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,ActionEvent.CTRL_MASK));
		refazer.setMnemonic(KeyEvent.VK_F);
		refazer.setIcon(new ImageIcon("Resource/Icones/redo.gif"));
		refazer.addActionListener(this);
		refazer.setEnabled(false);

		recortar = new JMenuItem("Recortar");
		recortar.setEnabled(false);
		recortar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		recortar.setMnemonic(KeyEvent.VK_T);
		recortar.setIcon(new ImageIcon("Resource/Icones/cut.gif"));
		recortar.addActionListener(this);

		copiar = new JMenuItem("Copiar");
		copiar.setEnabled(false);
		copiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		copiar.setMnemonic(KeyEvent.VK_O);
		copiar.setIcon(new ImageIcon("Resource/Icones/copy.gif"));
		copiar.addActionListener(this);

		colar = new JMenuItem("Colar");
		colar.setEnabled(false);
		colar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
		colar.setMnemonic(KeyEvent.VK_C);
		colar.setIcon(new ImageIcon("Resource/Icones/paste.gif"));
		colar.addActionListener(this);

		menuEditar.add(desfazer);
		menuEditar.add(refazer);
		menuEditar.addSeparator();
		menuEditar.add(recortar);
		menuEditar.add(copiar);
		menuEditar.add(colar);

		// ***********************************************************************//
		// ****************         Menu Imagem                   ****************//
		// ***********************************************************************//

		menuImagem = new JMenu("Imagem");
		menuImagem.setMnemonic(KeyEvent.VK_I);
		
		histograma = new JMenu("Histograma");
		histograma.setIcon(new ImageIcon("Resource/Icones/transparente.gif"));
		histograma.setMnemonic(KeyEvent.VK_H);
		
		JMenuItem colorido = new JMenuItem("Colorido");
		colorido.setIcon(new ImageIcon("Resource/Icones/histogram.gif"));
		colorido.setMnemonic(KeyEvent.VK_O);
		colorido.addActionListener(this);

		JMenuItem porCanal = new JMenuItem("Por Canal");
		porCanal.setIcon(new ImageIcon("Resource/Icones/histo.gif"));
		porCanal.setMnemonic(KeyEvent.VK_A);
		porCanal.addActionListener(this);

		histograma.add(colorido);
		histograma.add(porCanal);
		
		perfilLC = new JMenu("Perfil de Linha/Coluna");
		perfilLC.setIcon(new ImageIcon("Resource/Icones/transparente.gif"));
		perfilLC.setMnemonic(KeyEvent.VK_P);

		JMenu tresD = new JMenu("3D");
		tresD.setMnemonic(KeyEvent.VK_D);
		tresD.setEnabled(false);

		JMenuItem linha = new JMenuItem("Linha");
		linha.setMnemonic(KeyEvent.VK_L);
		linha.setActionCommand("Linha 3D");
		linha.addActionListener(this);
		linha.setEnabled(false);
		
		JMenuItem coluna = new JMenuItem("Coluna");
		coluna.setMnemonic(KeyEvent.VK_C);
		coluna.setActionCommand("Coluna 3D");
		coluna.addActionListener(this);
		coluna.setEnabled(false);

		tresD.add(linha);
		tresD.add(coluna);

		JMenu simples = new JMenu("Simples");
		simples.setMnemonic(KeyEvent.VK_S);

		linha = new JMenuItem("Linha");
		linha.setMnemonic(KeyEvent.VK_L);
		linha.setIcon(new ImageIcon("Resource/Icones/perfilLinha.gif"));
		linha.addActionListener(this);

		coluna = new JMenuItem("Coluna");
		coluna.setIcon(new ImageIcon("Resource/Icones/perfilColuna.gif"));
		coluna.setMnemonic(KeyEvent.VK_C);
		coluna.addActionListener(this);

		simples.add(linha);
		simples.add(coluna);

		perfilLC.add(tresD);
		perfilLC.add(simples);

		brilhoContraste = new JMenuItem("Brilho e Contraste");
		brilhoContraste.setMnemonic(KeyEvent.VK_B);
		brilhoContraste.setIcon(new ImageIcon("Resource/Icones/transparente.gif"));
		brilhoContraste.addActionListener(this);
		
		coordenadaPixel = new JRadioButtonMenuItem("Coordenada de Pixel");
		coordenadaPixel.setIcon(new ImageIcon("Resource/Icones/coordinate.gif"));
		coordenadaPixel.setMnemonic(KeyEvent.VK_C);
		coordenadaPixel.addChangeListener(this);

		modoApresentacao = new JMenu("Modo de Apresentação");
		modoApresentacao.setIcon(new ImageIcon("Resource/Icones/transparente.gif"));
		modoApresentacao.setMnemonic(KeyEvent.VK_A);
		
		JMenuItem PeB = new JMenuItem("Preto e Branco");
		PeB.setIcon(new ImageIcon("Resource/Icones/P&B.jpg"));
		PeB.setMnemonic(KeyEvent.VK_P);
		PeB.addActionListener(this);

		JMenuItem escalaCinza = new JMenuItem("Escala de Cinza");
		escalaCinza.setIcon(new ImageIcon("Resource/Icones/grayscale.jpg"));
		escalaCinza.setMnemonic(KeyEvent.VK_E);
		escalaCinza.addActionListener(this);

		JMenuItem pseudoColorizacao = new JMenuItem("Pseudo Colorização");
		pseudoColorizacao.setIcon(new ImageIcon("Resource/Icones/pseudo.gif"));
		pseudoColorizacao.setMnemonic(KeyEvent.VK_C);
		pseudoColorizacao.addActionListener(this);
		
		modoApresentacao.add(PeB);
		modoApresentacao.add(escalaCinza);
		modoApresentacao.add(pseudoColorizacao);
		
		combDecompor = new JMenu("Combinação/Decomposição de Canais");
		combDecompor.setMnemonic(KeyEvent.VK_D);
		combDecompor.setIcon(new ImageIcon("Resource/Icones/transparente.gif"));
		
		JMenuItem combinar = new JMenuItem("Combinação");
		combinar.setIcon(new ImageIcon("Resource/Icones/recompor.gif"));
		combinar.setMnemonic(KeyEvent.VK_C);
		combinar.addActionListener(this);
		
		JMenuItem decompor = new JMenuItem("Decomposição");
		decompor.setIcon(new ImageIcon("Resource/Icones/decompor.gif"));
		decompor.setMnemonic(KeyEvent.VK_D);
		decompor.addActionListener(this);

		combDecompor.add(combinar);
		combDecompor.add(decompor);

		requantizacao = new JMenuItem("Requantização");
		requantizacao.setIcon(new ImageIcon("Resource/Icones/transparente.gif"));
		requantizacao.setMnemonic(KeyEvent.VK_R);
		requantizacao.addActionListener(this);
		
		JMenu ilusao = new JMenu("Ilusões de Ótica");
		ilusao.setIcon(new ImageIcon("Resource/Icones/ilusao.gif"));
		ilusao.setMnemonic(KeyEvent.VK_I);

		ilusao1 = new JMenuItem("Ilusão 1");
		ilusao1.setMnemonic(KeyEvent.VK_1);
		ilusao1.addActionListener(this);

		ilusao2 = new JMenuItem("Ilusão 2");
		ilusao2.setMnemonic(KeyEvent.VK_2);
		ilusao2.addActionListener(this);

		ilusao3 = new JMenuItem("Ilusão 3");
		ilusao3.setMnemonic(KeyEvent.VK_3);
		ilusao3.addActionListener(this);

		ilusao4 = new JMenuItem("Ilusão 4");
		ilusao4.setMnemonic(KeyEvent.VK_4);
		ilusao4.addActionListener(this);
		
		ilusao5 = new JMenuItem("Ilusão 5");
		ilusao5.setMnemonic(KeyEvent.VK_5);
		ilusao5.addActionListener(this);
		
		ilusao6 = new JMenuItem("Ilusão 6");
		ilusao6.setMnemonic(KeyEvent.VK_6);
		ilusao6.addActionListener(this);
		
		ilusao7 = new JMenuItem("Ilusão 7");
		ilusao7.setMnemonic(KeyEvent.VK_7);
		ilusao7.addActionListener(this);
		
		ilusao8 = new JMenuItem("Ilusão 8");
		ilusao8.setMnemonic(KeyEvent.VK_8);
		ilusao8.addActionListener(this);
		
		ilusao9 = new JMenuItem("Ilusão 9");
		ilusao9.setMnemonic(KeyEvent.VK_9);
		ilusao9.addActionListener(this);
				
		ilusao10 = new JMenuItem("Ilusão 10");
		ilusao10.setMnemonic(KeyEvent.VK_0);
		ilusao10.addActionListener(this);
		
		ilusao.add(ilusao1);
		ilusao.add(ilusao2);
		ilusao.add(ilusao3);
		ilusao.add(ilusao4);
		ilusao.add(ilusao5);
		ilusao.add(ilusao6);
		ilusao.add(ilusao7);
		ilusao.add(ilusao8);
		ilusao.add(ilusao9);
		ilusao.add(ilusao10);
				
		menuImagem.add(histograma);
		menuImagem.add(perfilLC);
		menuImagem.add(brilhoContraste);
		menuImagem.add(coordenadaPixel);
		menuImagem.addSeparator();
		menuImagem.add(modoApresentacao);
		menuImagem.add(combDecompor);
		menuImagem.add(requantizacao);
		menuImagem.addSeparator();
		menuImagem.add(ilusao);
			
		// ***********************************************************************//
		// ****************            Menu Operações             ****************//
		// ***********************************************************************//

		menuOperacoes = new JMenu("Operações");
		menuOperacoes.setMnemonic(KeyEvent.VK_E);

		
		// ***************************//
		// ****  Op Radiometricas ****//
		// ***************************//

		JMenu radiometricas = new JMenu("Radiométricas");
		radiometricas.setMnemonic(KeyEvent.VK_R);
		radiometricas.setIcon(new ImageIcon("Resource/Icones/radiometrica.jpg"));
				
		// ***************************//
		// ****   Op Pontuais    *****//
		// ***************************//
		
		JMenu pontuais = new JMenu("Pontuais");
		pontuais.setMnemonic(KeyEvent.VK_P);
		
		JMenuItem opLogicas = new JMenuItem("Lógicas");
		opLogicas.setIcon(new ImageIcon("Resource/Icones/oplogica.GIF"));
		opLogicas.setMnemonic(KeyEvent.VK_L);
		opLogicas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
		opLogicas.addActionListener(this);
			
		JMenuItem opAritmetica = new JMenuItem("Aritméticas");
		opAritmetica.setIcon(new ImageIcon("Resource/Icones/oparitmetica.GIF"));
		opAritmetica.setMnemonic(KeyEvent.VK_B);
		opAritmetica.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
		opAritmetica.addActionListener(this);

		JMenuItem equalizar = new JMenuItem("Equalizar");
		equalizar.setIcon(new ImageIcon("Resource/Icones/transparente.GIF"));
		equalizar.setMnemonic(KeyEvent.VK_E);
		equalizar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
		equalizar.addActionListener(this);
		
		JMenuItem ganhoOffset = new JMenuItem("Ganho/Offset");
		ganhoOffset.setIcon(new ImageIcon("Resource/Icones/oparitmeticapessoal.GIF"));
		ganhoOffset.setMnemonic(KeyEvent.VK_P);
		ganhoOffset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		ganhoOffset.addActionListener(this);

		JMenu histogramica = new JMenu("Histogrâmicas");
		histogramica.setIcon(new ImageIcon("Resource/Icones/transparente.GIF"));
		histogramica.setMnemonic(KeyEvent.VK_P);
		
		JMenuItem expansaoCompressao = new JMenuItem("Expansão/Compressão");
		expansaoCompressao.setMnemonic(KeyEvent.VK_E);
		expansaoCompressao.setEnabled(false);
		
		JMenuItem equalizacao = new JMenuItem("Equalização");
		equalizacao.setMnemonic(KeyEvent.VK_Q);
		equalizacao.setEnabled(false);
		
		histogramica.add(expansaoCompressao);
		histogramica.add(equalizacao);
		
		JMenu morfologica = new JMenu("Morfológicas");
		morfologica.setIcon(new ImageIcon("Resource/Icones/transparente.GIF"));
		morfologica.setMnemonic(KeyEvent.VK_M);
		
		JMenu dilatacao = new JMenu("Dilatação");
		dilatacao.setMnemonic(KeyEvent.VK_E);
		
		JMenuItem dilatacao4 = new JMenuItem("Dilatação 4");
		dilatacao4.setIcon(new ImageIcon("Resource/Icones/dilatacao.jpg"));
		dilatacao4.addActionListener(this);
		
		JMenuItem dilatacao8 = new JMenuItem("Dilatação 8");
		dilatacao8.setIcon(new ImageIcon("Resource/Icones/dilatacao.jpg"));
		dilatacao8.addActionListener(this);
		
		dilatacao.add(dilatacao4);
		dilatacao.add(dilatacao8);
						
		JMenu erosao = new JMenu("Erosão");
		erosao.setMnemonic(KeyEvent.VK_Q);
		
		JMenuItem erosao4 = new JMenuItem("Erosão 4");
		erosao4.setIcon(new ImageIcon("Resource/Icones/erosao.GIF"));
		erosao4.addActionListener(this);
				
		JMenuItem erosao8 = new JMenuItem("Erosão 8");
		erosao8.setIcon(new ImageIcon("Resource/Icones/erosao.GIF"));
		erosao8.addActionListener(this);
		
		erosao.add(erosao4);
		erosao.add(erosao8);
		
		morfologica.add(dilatacao);
		morfologica.add(erosao);
		
		pontuais.add(opLogicas);
		pontuais.add(ganhoOffset);
		pontuais.add(opAritmetica);
		pontuais.add(equalizar);
		pontuais.add(histogramica);
		pontuais.add(morfologica);

		// ***************************//
		// ******   Op Locais    *****//
		// ***************************//
		
		JMenu locais = new JMenu("Locais");
		locais.setMnemonic(KeyEvent.VK_L);
		
		JMenu filtros = new JMenu("Filtros");
		filtros.setMnemonic(KeyEvent.VK_F);
		filtros.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		
		JMenu ruidos = new JMenu("Geradores de Ruído");
		ruidos.setMnemonic(KeyEvent.VK_R);
		ruidos.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		
		// ******************************//
		// ****** Geradores de Ruído ****//
		// ******************************//
		
		JMenuItem ruidoSalt_and_pepper = new JMenuItem("Salt-and-Pepper");
		ruidoSalt_and_pepper.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		ruidoSalt_and_pepper.addActionListener(this);
		
		/*JMenuItem knuth = new JMenuItem("Knuth");
		knuth.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		knuth.addActionListener(this);*/
		
		JMenuItem ruidoGaussiano = new JMenuItem("Gaussiano");
		ruidoGaussiano.setText("Ruído Gaussiano");
		ruidoGaussiano.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		ruidoGaussiano.addActionListener(this);
		
		//Frequência
		
		JMenu frequencia = new JMenu("Frequência");
		frequencia.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		
		// Espaciais
		
		JMenu espaciais = new JMenu("Espaciais");
		espaciais.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		
		// ***************************//
		// ****   Passa-baixas   *****//
		// ***************************//
		
		JMenu passaBaixa = new JMenu("Passa-Baixas");
		passaBaixa.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		
		JMenuItem media = new JMenuItem("Média");
		media.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		media.setMnemonic(KeyEvent.VK_M);
		media.addActionListener(this);
		
		JMenuItem mediana = new JMenuItem("Mediana");
		mediana.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		mediana.setMnemonic(KeyEvent.VK_D);
		mediana.addActionListener(this);
		
		JMenuItem moda = new JMenuItem("Moda");
		moda.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		moda.setMnemonic(KeyEvent.VK_D);
		moda.addActionListener(this);
				
		passaBaixa.add(media);
		passaBaixa.add(mediana);
		passaBaixa.add(moda);
		
		// ***************************//
		// ****   Passa-altas    *****//
		// ***************************//
		
		JMenu passaAlta = new JMenu("Passa-Altas");
		passaAlta.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		
		JMenuItem sobel = new JMenuItem("Sobel");
		sobel.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		sobel.setMnemonic(KeyEvent.VK_O);
		sobel.addActionListener(this);
		
		JMenuItem prewit = new JMenuItem("Prewit");
		prewit.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		prewit.setMnemonic(KeyEvent.VK_P);
		prewit.addActionListener(this);
		
		JMenuItem roberts = new JMenuItem("Roberts");
		roberts.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		roberts.setMnemonic(KeyEvent.VK_R);
		roberts.addActionListener(this);
		
		JMenuItem laplace = new JMenuItem("Laplace");
		laplace.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		laplace.setMnemonic(KeyEvent.VK_L);
		laplace.addActionListener(this);
		
		JMenuItem gaussiano = new JMenuItem("Gaussiano");
		gaussiano.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		gaussiano.setMnemonic(KeyEvent.VK_G);
		gaussiano.addActionListener(this);
		
		JMenuItem emboss = new JMenuItem("Emboss");
		emboss.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		emboss.setMnemonic(KeyEvent.VK_E);
		emboss.addActionListener(this);
		
		JMenuItem freichen = new JMenuItem("Frei & Chen");
		freichen.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		freichen.setMnemonic(KeyEvent.VK_F);
		freichen.addActionListener(this);
		
		passaAlta.add(sobel);
		passaAlta.add(prewit);
		passaAlta.add(roberts);
		passaAlta.add(laplace);
		passaAlta.add(gaussiano);
		passaAlta.add(emboss);
		passaAlta.add(freichen);
		
		espaciais.add(passaBaixa);
		espaciais.add(passaAlta);
		
		// ***************************//
		// ****  Morfológicas    *****//
		// ***************************//
		
		JMenu filtroMorfologico = new JMenu("Morfológicos");
		filtroMorfologico.setIcon(new ImageIcon("Resource/Icones/transparente.GIF"));
		filtroMorfologico.setMnemonic(KeyEvent.VK_M);
		
		JMenu abertura = new JMenu("Abertura");
				
		JMenuItem abertura4 = new JMenuItem("Abertura 4");
		abertura4.addActionListener(this);
		
		JMenuItem abertura8 = new JMenuItem("Abertuta 8");
		abertura8.addActionListener(this);
		
		abertura.add(abertura4);
		abertura.add(abertura8);
				
		JMenu fechamento = new JMenu("Fechamento");
		
		JMenuItem fechamento4 = new JMenuItem("Fechamento 4");
		fechamento4.addActionListener(this);
		
		JMenuItem fechamento8 = new JMenuItem("Fechamento 8");
		fechamento8.addActionListener(this);
		
		fechamento.add(fechamento4);
		fechamento.add(fechamento8);
		
		JMenuItem rank = new JMenuItem("Rank");
		rank.addActionListener(this);
		rank.setEnabled(false);
		JMenuItem morMediana = new JMenuItem("Mediana");
		morMediana.addActionListener(this);
		morMediana.setEnabled(false);
		
		filtroMorfologico.add(abertura);
		filtroMorfologico.add(fechamento);
		filtroMorfologico.add(rank);
		filtroMorfologico.add(morMediana);
		
		filtros.add(espaciais);
		filtros.add(frequencia);
		filtros.add(filtroMorfologico);
		
		ruidos.add(ruidoSalt_and_pepper);
		//ruidos.add(knuth);
		ruidos.add(ruidoGaussiano);
		
		locais.add(filtros);
		locais.add(ruidos);
				
		radiometricas.add(pontuais);
		radiometricas.add(locais);

		// ***************************//
		// ****  Op Geometricas ******//
		// ***************************//
		
		JMenu geometricas = new JMenu("Geométricas");
		geometricas.setIcon(new ImageIcon("Resource/Icones/geometrica.gif"));
		geometricas.setMnemonic(KeyEvent.VK_R);
		
		JMenuItem rotacionar = new JMenuItem("Rotacionar", KeyEvent.VK_R);
		rotacionar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
		rotacionar.setIcon(new ImageIcon("Resource/Icones/rotacionar.gif"));
		rotacionar.addActionListener(this);

		JMenuItem redimensionar = new JMenuItem("Redimensionar", KeyEvent.VK_E);
		redimensionar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
		redimensionar.setIcon(new ImageIcon("Resource/Icones/resize.gif"));
		redimensionar.addActionListener(this);

		JMenuItem ampliar = new JMenuItem("Ampliar");
		ampliar.setMnemonic(KeyEvent.VK_Z);
		ampliar.addActionListener(this);
		ampliar.setIcon(new ImageIcon("Resource/Icones/zoom.gif"));
		
		geometricas.add(rotacionar);
		geometricas.add(redimensionar);
		geometricas.add(ampliar);
				
		menuOperacoes.add(radiometricas);
		menuOperacoes.add(geometricas);
		
		// ***********************************************************************//
		// ****************            Menu Classificaçao         ****************//
		// ***********************************************************************//

		menuClassificacao = new JMenu("Classificação");
		menuClassificacao.setMnemonic(KeyEvent.VK_L);

		JMenu segmentacao = new JMenu("Segmentação");
		segmentacao.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		segmentacao.setMnemonic(KeyEvent.VK_S);

		JMenuItem global = new JMenuItem("Global");
		global.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		global.setMnemonic(KeyEvent.VK_G);
		global.addActionListener(this);

		JMenuItem adaptativa = new JMenuItem("Adaptativa Básica");
		adaptativa.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		adaptativa.setMnemonic(KeyEvent.VK_A);
		adaptativa.addActionListener(this);
		
		JMenuItem regionGrowing = new JMenuItem("Crescimento de Região");
		regionGrowing.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		regionGrowing.setMnemonic(KeyEvent.VK_R);
		regionGrowing.addActionListener(this);
		
		segmentacao.add(global);
		segmentacao.add(adaptativa);
		segmentacao.add(regionGrowing);
		
		JMenu classificadores = new JMenu("Classificadores");
		classificadores.setIcon(new ImageIcon("Resource/Icones/transparente.gif"));
		classificadores.setMnemonic(KeyEvent.VK_C);
				
		JMenuItem paralelepipedo = new JMenuItem("Paralelepipedo");
		//paralelepipedo.setIcon(new ImageIcon("Resource/Icones/transparente.gif"));
		paralelepipedo.setMnemonic(KeyEvent.VK_P);
		paralelepipedo.addActionListener(this);
		
		classificadores.add(paralelepipedo);
		
		menuClassificacao.add(segmentacao);
		menuClassificacao.add(classificadores);
		
		
		// ***********************************************************************//
		// ****************            Menu Ajuda                 ****************//
		// ***********************************************************************//

		menuAjuda = new JMenu("Ajuda");

		JMenuItem ajuda = new JMenuItem("Tópicos de Ajuda");
		ajuda.setMnemonic(KeyEvent.VK_A);
		ajuda.addActionListener(this);
		ajuda.setIcon(new ImageIcon("Resource/Icones/ajuda.gif"));
		menuAjuda.add(ajuda);

		JMenuItem sobre = new JMenuItem("Sobre FePDI");
		sobre.setIcon(new ImageIcon("Resource/Icones/fepdi2.jpg"));
		sobre.setMnemonic(KeyEvent.VK_S);
		sobre.addActionListener(this);
		menuAjuda.add(sobre);

		menuBar.add(menuArquivo);
		menuBar.add(menuEditar);
		menuBar.add(menuImagem);
		menuBar.add(menuOperacoes);
		menuBar.add(menuClassificacao);
		menuBar.add(menuAjuda);

		menuBar.setBorder(BorderFactory.createEmptyBorder());

		setJMenuBar(menuBar);

		// ***********************************************************************//
		// ****************           Menu de Acesso Direto       ****************//
		// ***********************************************************************//

		JToolBar jToolBar = new JToolBar();
		jToolBar.setFloatable(true);
		jToolBar.setRollover(true);
		jToolBar.setBounds(0, 0, screenSize.width, 26);
		jToolBar.setBorder(BorderFactory.createRaisedBevelBorder());

		buttonNovo = new JButton();
		buttonNovo.setIcon(new ImageIcon("Resource/Icones/new.gif"));
		buttonNovo.addActionListener(this);
		buttonNovo.setActionCommand(abrirNovo.getActionCommand());
		buttonNovo.setToolTipText("Novo");
		jToolBar.add(buttonNovo);

		buttonAbrir = new JButton();
		buttonAbrir.setIcon(new ImageIcon("Resource/Icones/open.gif"));
		buttonAbrir.addActionListener(this);
		buttonAbrir.setActionCommand(abrir.getActionCommand());
		buttonAbrir.setToolTipText("Abrir");
		jToolBar.add(buttonAbrir);

		buttonSalvarComo = new JButton();
		buttonSalvarComo.setIcon(new ImageIcon("Resource/Icones/save.gif"));
		buttonSalvarComo.addActionListener(this);
		buttonSalvarComo.setActionCommand(salvarComo.getActionCommand());
		buttonSalvarComo.setToolTipText("Salvar Como");
		jToolBar.add(buttonSalvarComo);

		jToolBar.addSeparator(new Dimension(3, 3));

//		=========================================================================
//		Tirei do Menu para colocar as novas Ilusoes de Otica
		
//		buttonIlusao1 = new JButton();
//		buttonIlusao1.setIcon(new ImageIcon("Resource/Icones/pontosPretos.gif"));
//		buttonIlusao1.addActionListener(this);
//		buttonIlusao1.setActionCommand(ilusao1.getActionCommand());
//		buttonIlusao1.setToolTipText("Ilusão 1");
//		jToolBar.add(buttonIlusao1);
//
//		buttonIlusao2 = new JButton();
//		buttonIlusao2.setIcon(new ImageIcon("Resource/Icones/retas.gif"));
//		buttonIlusao2.addActionListener(this);
//		buttonIlusao2.setActionCommand(ilusao2.getActionCommand());
//		buttonIlusao2.setToolTipText("Ilusão 2");
//		jToolBar.add(buttonIlusao2);
//
//		buttonIlusao3 = new JButton();
//		buttonIlusao3.setIcon(new ImageIcon("Resource/Icones/quadrados.gif"));
//		buttonIlusao3.addActionListener(this);
//		buttonIlusao3.setActionCommand(ilusao3.getActionCommand());
//		buttonIlusao3.setToolTipText("Ilusão 3");
//		jToolBar.add(buttonIlusao3);
//
//		buttonIlusao4 = new JButton();
//		buttonIlusao4.setIcon(new ImageIcon("Resource/Icones/circulos.gif"));
//		buttonIlusao4.addActionListener(this);
//		buttonIlusao4.setActionCommand(ilusao4.getActionCommand());
//		buttonIlusao4.setToolTipText("Ilusão 4");
//		jToolBar.add(buttonIlusao4);
//
//		jToolBar.addSeparator();
//		======================================================================

		buttonCamera = new JButton();
		buttonCamera.setIcon(new ImageIcon("Resource/Icones/webcam.gif"));
		buttonCamera.addActionListener(this);
		buttonCamera.setActionCommand(camera.getActionCommand());
		buttonCamera.setToolTipText("Câmera");
		jToolBar.add(buttonCamera);
		buttonCamera.setEnabled(false);

		buttonSccaner = new JButton();
		buttonSccaner.setIcon(new ImageIcon("Resource/Icones/scanner.gif"));
		buttonSccaner.addActionListener(this);
		buttonSccaner.setActionCommand(scanner.getActionCommand());
		buttonSccaner.setToolTipText("Scanner");
		jToolBar.add(buttonSccaner);
		buttonSccaner.setEnabled(false);

		jToolBar.addSeparator();

		buttonRecortar = new JButton();
		buttonRecortar.setEnabled(false);
		buttonRecortar.setIcon(new ImageIcon("Resource/Icones/cut.gif"));
		buttonRecortar.addActionListener(this);
		buttonRecortar.setActionCommand(recortar.getActionCommand());
		buttonRecortar.setToolTipText("Recortar");
		jToolBar.add(buttonRecortar);

		buttonCopiar = new JButton();
		buttonCopiar.setEnabled(false);
		buttonCopiar.setIcon(new ImageIcon("Resource/Icones/copy.gif"));
		buttonCopiar.addActionListener(this);
		buttonCopiar.setActionCommand(copiar.getActionCommand());
		buttonCopiar.setToolTipText("Copiar");
		jToolBar.add(buttonCopiar);

		buttonColar = new JButton();
		buttonColar.setEnabled(false);
		buttonColar.setIcon(new ImageIcon("Resource/Icones/paste.gif"));
		buttonColar.addActionListener(this);
		buttonColar.setActionCommand(colar.getActionCommand());
		buttonColar.setToolTipText("Colar");
		jToolBar.add(buttonColar);

		jToolBar.addSeparator();

		buttonDesfazer = new JButton();
		buttonDesfazer.setIcon(new ImageIcon("Resource/Icones/undo.gif"));
		buttonDesfazer.addActionListener(this);
		buttonDesfazer.setActionCommand(desfazer.getActionCommand());
		buttonDesfazer.setToolTipText("Desfazer");
		jToolBar.add(buttonDesfazer);
		buttonDesfazer.setSelected(false);

		buttonRefazer = new JButton();
		buttonRefazer.setIcon(new ImageIcon("Resource/Icones/redo.gif"));
		buttonRefazer.addActionListener(this);
		buttonRefazer.setActionCommand(refazer.getActionCommand());
		buttonRefazer.setToolTipText("Refazer");
		jToolBar.add(buttonRefazer);
		buttonRefazer.setEnabled(false);

		jToolBar.addSeparator();

		buttonZMenos = new JButton();
		buttonZMenos.setIcon(new ImageIcon("Resource/Icones/zoomOut.gif"));
		buttonZMenos.addActionListener(this);
		buttonZMenos.setActionCommand("Zoom Menos");
		buttonZMenos.setToolTipText("Zoom Menos");
		jToolBar.add(buttonZMenos);

		buttonZMais = new JButton();
		buttonZMais.setIcon(new ImageIcon("Resource/Icones/zoomIn.gif"));
		buttonZMais.addActionListener(this);
		buttonZMais.setActionCommand("Zoom Mais");
		buttonZMais.setToolTipText("Zoom Mais");
		jToolBar.add(buttonZMais);

		jToolBar.addSeparator();

		buttonRotacionar = new JButton();
		buttonRotacionar.setIcon(new ImageIcon("Resource/Icones/rotacionar.gif"));
		buttonRotacionar.addActionListener(this);
		buttonRotacionar.setActionCommand(rotacionar.getActionCommand());
		buttonRotacionar.setToolTipText("Rotacionar");
		jToolBar.add(buttonRotacionar);

		buttonRedimensionar = new JButton();
		buttonRedimensionar.setIcon(new ImageIcon("Resource/Icones/resize.gif"));
		buttonRedimensionar.addActionListener(this);
		buttonRedimensionar.setActionCommand(redimensionar.getActionCommand());
		buttonRedimensionar.setToolTipText("Redimensionar");
		jToolBar.add(buttonRedimensionar);

		jToolBar.addSeparator();

		buttonHColorido = new JButton();
		buttonHColorido.setIcon(new ImageIcon("Resource/Icones/histogram.gif"));
		buttonHColorido.addActionListener(this);
		buttonHColorido.setActionCommand(colorido.getActionCommand());
		buttonHColorido.setToolTipText("Histograma Colorido");
		jToolBar.add(buttonHColorido);

		buttonHCanal = new JButton();
		buttonHCanal.setIcon(new ImageIcon("Resource/Icones/histo.gif"));
		buttonHCanal.addActionListener(this);
		buttonHCanal.setActionCommand(porCanal.getActionCommand());
		buttonHCanal.setToolTipText("Histograma Por Canal");
		jToolBar.add(buttonHCanal);

		jToolBar.addSeparator();

		buttonPLinha = new JButton();
		buttonPLinha.setIcon(new ImageIcon("Resource/Icones/perfilLinha.gif"));
		buttonPLinha.addActionListener(this);
		buttonPLinha.setActionCommand(linha.getActionCommand());
		buttonPLinha.setToolTipText("Perfil de Linha Simples");
		jToolBar.add(buttonPLinha);

		buttonPColuna = new JButton();
		buttonPColuna.setIcon(new ImageIcon("Resource/Icones/perfilColuna.gif"));
		buttonPColuna.addActionListener(this);
		buttonPColuna.setActionCommand(coluna.getActionCommand());
		buttonPColuna.setToolTipText("Perfil de Coluna Simples");
		jToolBar.add(buttonPColuna);

		jToolBar.addSeparator();

		buttonExportar = new JButton();
		buttonExportar.setIcon(new ImageIcon("Resource/Icones/exportar.GIF"));
		buttonExportar.addActionListener(this);
		buttonExportar.setActionCommand(exportar.getActionCommand());
		buttonExportar.setToolTipText("Exportar");
		jToolBar.add(buttonExportar);

		jToolBar.addSeparator();

		buttonOpLogica = new JButton();
		buttonOpLogica.setIcon(new ImageIcon("Resource/Icones/oplogica.gif"));
		buttonOpLogica.addActionListener(this);
		buttonOpLogica.setActionCommand(opLogicas.getActionCommand());
		buttonOpLogica.setToolTipText("Operação Lógica");
		jToolBar.add(buttonOpLogica);

		buttonOpAritB = new JButton();
		buttonOpAritB.setIcon(new ImageIcon("Resource/Icones/oparitmetica.gif"));
		buttonOpAritB.addActionListener(this);
		buttonOpAritB.setActionCommand(opAritmetica.getActionCommand());
		buttonOpAritB.setToolTipText("Operação Aritmética Básica");
		jToolBar.add(buttonOpAritB);

		buttonOpAritP = new JButton();
		buttonOpAritP.setIcon(new ImageIcon("Resource/Icones/oparitmeticapessoal.gif"));
		buttonOpAritP.addActionListener(this);
		buttonOpAritP.setActionCommand(ganhoOffset.getActionCommand());
		buttonOpAritP.setToolTipText("Operação Aritmética Pessoal");
		jToolBar.add(buttonOpAritP);

		jToolBar.addSeparator();

		buttonEscCinza = new JButton();
		buttonEscCinza.setIcon(new ImageIcon("Resource/Icones/grayscale.jpg"));
		buttonEscCinza.addActionListener(this);
		buttonEscCinza.setActionCommand(escalaCinza.getActionCommand());
		buttonEscCinza.setToolTipText("Escala de Cinza");
		jToolBar.add(buttonEscCinza);

		buttonDecompor = new JButton();
		buttonDecompor.setIcon(new ImageIcon("Resource/Icones/decompor.gif"));
		buttonDecompor.addActionListener(this);
		buttonDecompor.setActionCommand(decompor.getActionCommand());
		buttonDecompor.setToolTipText("Decompor Canais");
		jToolBar.add(buttonDecompor);

		buttonRecompor = new JButton();
		buttonRecompor.setIcon(new ImageIcon("Resource/Icones/recompor.gif"));
		buttonRecompor.addActionListener(this);
		buttonRecompor.setActionCommand(combinar.getActionCommand());
		buttonRecompor.setToolTipText("Combinar Canais");
		jToolBar.add(buttonRecompor);

		buttonPseudo = new JButton();
		buttonPseudo.setIcon(new ImageIcon("Resource/Icones/pseudo.GIF"));
		buttonPseudo.addActionListener(this);
		buttonPseudo.setActionCommand(pseudoColorizacao.getActionCommand());
		buttonPseudo.setToolTipText("Pseudo Colorizacao");
		jToolBar.add(buttonPseudo);

		getContentPane().add(jToolBar, BorderLayout.NORTH);
		getContentPane().add(dp, BorderLayout.CENTER);
		dp.setBackground(new Color(128, 128, 128));
		facade = Facade.getInstance();

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
					fepdi.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
					facade.setPosicao(0);
				}
			}
		});

		this.firePropertyChange("show-color", false, showColors);
		this.addMouseWheelListener(this);
	}

	/**
	 * Método que inicializa os botoes do programa
	 */
	public void habilitaBotoes(boolean habilita) {
		menuEditar.setEnabled(habilita);
		menuOperacoes.setEnabled(habilita);
		menuClassificacao.setEnabled(habilita);
		histograma.setEnabled(habilita);
		perfilLC.setEnabled(habilita);
		brilhoContraste.setEnabled(habilita);
		coordenadaPixel.setEnabled(habilita);
		modoApresentacao.setEnabled(habilita);
		combDecompor.setEnabled(habilita);
		requantizacao.setEnabled(habilita);
		fechar.setEnabled(habilita);
		salvarComo.setEnabled(habilita);
		buttonSalvarComo.setEnabled(habilita);
		exportar.setEnabled(habilita);
		buttonExportar.setEnabled(habilita);
		buttonDesfazer.setEnabled(habilita);
		buttonRefazer.setEnabled(habilita);
		buttonZMais.setEnabled(habilita);
		buttonZMenos.setEnabled(habilita);
		buttonRotacionar.setEnabled(habilita);
		buttonRedimensionar.setEnabled(habilita);
		buttonHCanal.setEnabled(habilita);
		buttonHColorido.setEnabled(habilita);
		buttonPLinha.setEnabled(habilita);
		buttonPColuna.setEnabled(habilita);
		buttonOpLogica.setEnabled(habilita);
		buttonOpAritP.setEnabled(habilita);
		buttonOpAritB.setEnabled(habilita);
		buttonEscCinza.setEnabled(habilita);
		buttonDecompor.setEnabled(habilita);
		buttonRecompor.setEnabled(habilita);
		buttonPseudo.setEnabled(habilita);
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
	 * Metodo que exibe na tela a imagem requantizada
	 * 
	 * @param f
	 *            O MyJInternalFrame aberto
	 * @param r
	 *            A JanelaRequantizar que esta sendo usada
	 */
	private void mostraTela(MyJInternalFrame f, JanelaRequantizar r) {
		MyJInternalFrame ift = new MyJInternalFrame(this, facade, f.getName()+ " " + r.getNome(), new MyImage(r.getImagem()));
		ift.setFoiOperacao(true);
		dp.add(ift);
		dp.setSelectedFrame(ift);
		this.addPropertyChangeListener(ift.getScrollPane());
		try {
			ift.setSelected(true);
		} catch (PropertyVetoException e) {
		}
		ift.setLocation(facade.getPosicao(), facade.getPosicao());
		facade.incrementaPosicao();
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
			JInternalFrame[] frames = dp.getAllFrames();
			if (frames != null) {
				for (int i = 0; i < frames.length; i++) {
					if (verificaInstancia(frames[i]))
						((MyJInternalFrame) frames[i]).fecharAoSair();
				}
			}
			JanelaSair js = new JanelaSair("Deseja realmente sair?");
			String t = js.getAcaoSelecionada();
			if (t.equals("Sim"))
				System.exit(0);
			else if (t.equals("Nao"))
				fepdi.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		} 
		else if (evento.equals("Novo")) {
			JanelaNovo j = new JanelaNovo(this);
			int altura = j.getAltura();
			int largura = j.getLargura();
			if (altura > 0 && largura > 0 && j.foiOk()) {
				Image image = null;
				try {
					image = facade.emPixelsBICUBIC(new ImageIcon("Resource/Logos/branco.jpg"), largura, altura);
					MyJInternalFrame i = new MyJInternalFrame(this, facade,	"Novo", new MyImage(image));
					dp.add(i, BorderLayout.CENTER);
					dp.setSelectedFrame(i);
					i.setSelected(true);
					i.setLocation(facade.getPosicao(), facade.getPosicao());
					this.addPropertyChangeListener(i.getScrollPane());
					this.firePropertyChange("show-color", false, showColors);
					habilitaBotoes(true);
					facade.incrementaPosicao();
				} catch (RedimensionarException e) {
				} catch (PropertyVetoException e) {
				}
			}
			j.dispose();
		} 
		else if (evento.equals("Abrir")) {
			Object[] abrir = null;
			try {
				abrir = facade.abrir();
			} catch (ImagemNaoExisteException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
			} catch (NomeInvalidoException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
			}
			MyJInternalFrame i = null;
			Image img = null;
			try {
				img = (Image) abrir[0];
				if ((img.getWidth(null) > 1024) || (img.getHeight(null) > 1024)) {
					JOptionPane
							.showMessageDialog(null,"Imagem Impossível de ser aberta!!! Tamanho Máximo 1024 x 1024",
									"ERRO NAS INFORMAÇÕES",	JOptionPane.ERROR_MESSAGE);
				} else {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					i = new MyJInternalFrame(this, facade, (String) abrir[1],
							new MyImage(img));
					i.setCaminhoImagem(AbrirImagem.caminho);
				}
			} catch (Exception e) {
			}
			if (i != null) {
				boolean existe = false;
				for (int j = 0; j < dp.getAllFrames().length; j++) {
					if (verificaInstancia(dp.getAllFrames()[j])) {
						if (((MyJInternalFrame) dp.getAllFrames()[j])
								.getCaminhoImagem()
								.equals(i.getCaminhoImagem())) {
							existe = true;
							try {
								dp.getAllFrames()[j].setSelected(true);
							} catch (PropertyVetoException e) {
							}
						}
					}
				}
				if (!existe && i.getAbriu()) {
					dp.add(i, BorderLayout.CENTER);
					dp.setSelectedFrame(i);
					try {
						i.setSelected(true);
					} catch (PropertyVetoException e) {
					}
					this.addPropertyChangeListener(i.getScrollPane());
					this.firePropertyChange("show-color", false, showColors);
					i.setLocation(facade.getPosicao(), facade.getPosicao());
					if (i.getAbriu()) {
						habilitaBotoes(true);
						i.setVisible(true);
						facade.incrementaPosicao();
					}
				}
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} 
		else if (evento.equals("Salvar")) {
			if (verificaInstancia(dp.getSelectedFrame())) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				MyJInternalFrame i = (MyJInternalFrame) dp.getSelectedFrame();
				if (i != null) {
					try {
						if (i.getFoiModificado() || facade.modificouImagem()) {
							i.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
							i.setSalvou(true);
							facade.salvar(i);
							i.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(this, e.getMessage(),
								"ERRO!", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else
				JOptionPane.showMessageDialog(null,"Selecione uma imagem para realizar a operação!!!",
						"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} 
		else if (evento.equals("Salvar Como")) {
			MyJInternalFrame i = (MyJInternalFrame) dp.getSelectedFrame();
			if (i != null) {
				try {
					i.setSalvou(true);
					SalvarImagem.caminho = i.getName();
					SalvarImagem.caminhoImagem = AbrirImagem.caminho;
					int valor = facade.salvarComo(i.getImage());
					if (valor != 0) {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						i.getScrollPane().setCursor(
								new Cursor(Cursor.WAIT_CURSOR));
						MyJInternalFrame j = new MyJInternalFrame(this, facade,
								SalvarImagem.nome, new MyImage(
										SalvarImagem.caminhoImagem));
						j.setCaminhoImagem(SalvarImagem.caminhoImagem);
						dp.add(j);
						dp.setSelectedFrame(j);
						j.setLocation(facade.getPosicao(), facade.getPosicao());
						facade.incrementaPosicao();
						j.setSelected(true);
						this.addPropertyChangeListener(j.getScrollPane());
						this
								.firePropertyChange("show-color", false,
										showColors);
						i.getScrollPane().setCursor(
								new Cursor(Cursor.CROSSHAIR_CURSOR));
					}
				} catch (ImagemNaoSelecionadaException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(),
							"ERRO!", JOptionPane.ERROR_MESSAGE);
				} catch (ImagemNaoPodeSalvarException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(),
							"ERRO!", JOptionPane.ERROR_MESSAGE);
				} catch (ImagemNaoExisteException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(),
							"ERRO!", JOptionPane.ERROR_MESSAGE);
				} catch (NomeInvalidoException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(),
							"ERRO!", JOptionPane.ERROR_MESSAGE);
				} catch (PropertyVetoException e) {
				}
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		}
		else if (evento.equals("Comprimir")) {
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			JFileChooser fc = new JFileChooser(new File("."));
			fc.addChoosableFileFilter(new GIFFilter());
			fc.addChoosableFileFilter(new PNGFilter());
			fc.addChoosableFileFilter(new BMPFilter());
			fc.addChoosableFileFilter(new JPGFilter());
			fc.setFileView(new ImageFileView());
			int returnVal = fc.showDialog(new JFrame(), "Exportar");
			if (returnVal == JFileChooser.CANCEL_OPTION)
				return;
			else {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				String formato = fc.getFileFilter().getDescription();
				String caminho = fc.getSelectedFile().getAbsolutePath();
				File arquivo = new File(caminho);
				String nomeArquivo = (arquivo.toString()).replace(fc
						.getCurrentDirectory().toString(), "");
				nomeArquivo = nomeArquivo.replaceFirst("\\\\", "");
				if (nomeArquivo.contains("/") || nomeArquivo.contains(":")
						|| nomeArquivo.contains("\\")
						|| nomeArquivo.contains("<")
						|| nomeArquivo.contains(">")
						|| nomeArquivo.contains("|"))
					JOptionPane.showMessageDialog(this,
							"Arquivo com nome invalido!", "ERRO!",
							JOptionPane.ERROR_MESSAGE);
				else {
					try {
						try {
							facade.comprimirImagem(formato, caminho,MyBufferedImage.makeBufferedImage(f.getImage()));
						} catch (FormatoInvalidoException e) {
						}
					} catch (InterruptedException e) {
						JOptionPane.showMessageDialog(this, e.getMessage(),
								"ERRO!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else if (evento.equals("Fechar")) {
			MyJInternalFrame i = (MyJInternalFrame) dp.getSelectedFrame();
			if (i != null) {
				i.fechar();
			}
		}

		// ***********************************************************************//
		// ****************            Menu Editar                ****************//
		// ***********************************************************************//
		
		else if (evento.equals("Desfazer")) {
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			if (f != null) {
				RenderedImage i = facade.desfazer((MyImage) f.getMyImage());
				f.setImage(i);
			}
		} 
		else if (evento.equals("Refazer")) {
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			if (f != null) {
				RenderedImage i = facade.refazer((MyImage) f.getMyImage());
				f.setImage(i);
			}
		}
		else if (evento.equals("Recortar")) {
			boolean recortou = facade.recortar(this, getFrameAberto()
					.getImage());
			if (recortou) {
				colar.setEnabled(true);
				buttonColar.setEnabled(true);
				((MyJInternalFrame) dp.getSelectedFrame()).setImage(facade.getImagemRecortada());
			} else {
				colar.setEnabled(false);
				buttonColar.setEnabled(false);
			}
			recortar.setEnabled(false);
			buttonRecortar.setEnabled(false);
			copiar.setEnabled(false);
			buttonCopiar.setEnabled(false);
		}
		else if (evento.equals("Copiar")) {
			boolean copiou = facade.copiar(this, getFrameAberto().getImage());
			if (copiou) {
				colar.setEnabled(true);
				buttonColar.setEnabled(true);
			} else {
				colar.setEnabled(false);
				buttonColar.setEnabled(false);
			}
			recortar.setEnabled(false);
			buttonRecortar.setEnabled(false);
			copiar.setEnabled(false);
			buttonCopiar.setEnabled(false);

		}
		else if (evento.equals("Colar")) {
			if (verificaInstancia(dp.getSelectedFrame())) {
				ImageIcon colada = new ImageIcon(facade.colar());
				new MyJWindow(this, colada, colada.getIconWidth(), colada
						.getIconHeight(), this.getLocation().x
						+ getFrameAberto().getX() + 10, this.getLocation().y
						+ getFrameAberto().getY() + 105);
			}
		}

		// ***********************************************************************//
		// ****************            Menu Imagem                ****************//
		// ***********************************************************************//
		
		else if (evento.equals("Colorido")) {
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			new JanelaHistogramaColorido(f.getImage(), facade);
			
		}
		else if (evento.equals("Por Canal")) {
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			new JanelaHistogramaPorCanal(f.getImage(), facade);
		}
		else if (evento.equals("Linha")){
			onTheFlyProfile(AlgoritmosImagem.LINHA);
		}
		else if (evento.equals("Coluna")){
			onTheFlyProfile(!AlgoritmosImagem.LINHA);
		}
		else if(evento.equals("Brilho e Contraste")) {
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			try {
				new ImageContrast(f, this);
			} catch (Exception e){
				
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));			
		}
		else if (evento.equals("Decomposição")) {
			JanelaDecomposicao jd = new JanelaDecomposicao();
			String modelo = jd.getModelo();
			if (jd.ok()) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				if (modelo.equals("RGB")) {
					Object[] array = facade.decomporRGB((MyJInternalFrame) dp
							.getSelectedFrame());
					MyJInternalFrame r = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - Red", new MyImage(
									(Image) array[0]));
					r.setFoiOperacao(true);
					r.setLocation(facade.getPosicao(), facade.getPosicao());
					this.addPropertyChangeListener(r.getScrollPane());
					dp.add(r);
					facade.incrementaPosicao();
					MyJInternalFrame g = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - Green", new MyImage(
									(Image) array[1]));
					g.setFoiOperacao(true);
					g.setLocation(facade.getPosicao(), facade.getPosicao());
					this.addPropertyChangeListener(g.getScrollPane());
					dp.add(g);
					facade.incrementaPosicao();
					MyJInternalFrame b = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - Blue", new MyImage(
									(Image) array[2]));
					b.setFoiOperacao(true);
					this.addPropertyChangeListener(b.getScrollPane());
					b.setLocation(facade.getPosicao(), facade.getPosicao());
					dp.add(b);
					facade.incrementaPosicao();
					dp.setSelectedFrame(r);
					dp.setSelectedFrame(g);
					dp.setSelectedFrame(b);
					try {
						r.setSelected(true);
						g.setSelected(true);
						b.setSelected(true);
					} catch (PropertyVetoException e) {
					}

					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} else if (modelo.equals("CMY")) {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Object[] array = facade.decomporCMY((MyJInternalFrame) dp
							.getSelectedFrame());
					MyJInternalFrame c = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - Cyan", new MyImage(
									(Image) array[0]));
					c.setFoiOperacao(true);
					this.addPropertyChangeListener(c.getScrollPane());
					c.setLocation(facade.getPosicao(), facade.getPosicao());
					dp.add(c);
					facade.incrementaPosicao();
					MyJInternalFrame m = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - Magenta",
							new MyImage((Image) array[1]));
					m.setFoiOperacao(true);
					this.addPropertyChangeListener(m.getScrollPane());
					m.setLocation(facade.getPosicao(), facade.getPosicao());
					dp.add(m);
					facade.incrementaPosicao();
					MyJInternalFrame y = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - Yellow", new MyImage(
									(Image) array[2]));
					y.setFoiOperacao(true);
					this.addPropertyChangeListener(y.getScrollPane());
					y.setLocation(facade.getPosicao(), facade.getPosicao());
					dp.add(y);
					facade.incrementaPosicao();
					dp.setSelectedFrame(c);
					dp.setSelectedFrame(m);
					dp.setSelectedFrame(y);
					try {
						c.setSelected(true);
						m.setSelected(true);
						y.setSelected(true);
					} catch (PropertyVetoException e) {
					}
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} else if (modelo.equals("CMYK")) {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Object[] array = facade.decomporCMYK((MyJInternalFrame) dp
							.getSelectedFrame());
					MyJInternalFrame c = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - C", new MyImage(
									(Image) array[0]));
					c.setFoiOperacao(true);
					this.addPropertyChangeListener(c.getScrollPane());
					c.setLocation(facade.getPosicao(), facade.getPosicao());
					dp.add(c);
					facade.incrementaPosicao();
					MyJInternalFrame m = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - M", new MyImage(
									(Image) array[1]));
					m.setFoiOperacao(true);
					this.addPropertyChangeListener(m.getScrollPane());
					m.setLocation(facade.getPosicao(), facade.getPosicao());
					dp.add(m);
					facade.incrementaPosicao();
					MyJInternalFrame y = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - Y", new MyImage(
									(Image) array[2]));
					y.setFoiOperacao(true);
					this.addPropertyChangeListener(y.getScrollPane());
					y.setLocation(facade.getPosicao(), facade.getPosicao());
					dp.add(y);
					facade.incrementaPosicao();
					MyJInternalFrame k = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - K", new MyImage(
									(Image) array[3]));
					k.setFoiOperacao(true);
					this.addPropertyChangeListener(k.getScrollPane());
					k.setLocation(facade.getPosicao(), facade.getPosicao());
					dp.add(k);
					facade.incrementaPosicao();
					dp.setSelectedFrame(c);
					dp.setSelectedFrame(m);
					dp.setSelectedFrame(y);
					dp.setSelectedFrame(k);
					try {
						c.setSelected(true);
						m.setSelected(true);
						y.setSelected(true);
						k.setSelected(true);
					} catch (PropertyVetoException e) {
					}

					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} else if (modelo.equals("HSV")) {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Object[] array = facade.decomporHSV((MyJInternalFrame) dp
							.getSelectedFrame());
					MyJInternalFrame h = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - H", new MyImage(
									(Image) array[0]));
					h.setFoiOperacao(true);
					h.setLocation(facade.getPosicao(), facade.getPosicao());
					this.addPropertyChangeListener(h.getScrollPane());
					dp.add(h);
					facade.incrementaPosicao();
					MyJInternalFrame s = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - S", new MyImage(
									(Image) array[1]));
					s.setFoiOperacao(true);
					this.addPropertyChangeListener(s.getScrollPane());
					s.setLocation(facade.getPosicao(), facade.getPosicao());
					dp.add(s);
					facade.incrementaPosicao();
					MyJInternalFrame v = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - V", new MyImage(
									(Image) array[2]));
					v.setFoiOperacao(true);
					this.addPropertyChangeListener(v.getScrollPane());
					v.setLocation(facade.getPosicao(), facade.getPosicao());
					dp.add(v);
					facade.incrementaPosicao();
					dp.setSelectedFrame(h);
					dp.setSelectedFrame(s);
					dp.setSelectedFrame(v);
					try {
						h.setSelected(true);
						s.setSelected(true);
						v.setSelected(true);
					} catch (PropertyVetoException e) {
					}

					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} else if (modelo.equals("YCrCb")) {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Object[] array = facade.decomporYCrCb((MyJInternalFrame) dp
							.getSelectedFrame());
					MyJInternalFrame y = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - Y", new MyImage(
									(Image) array[0]));
					y.setFoiOperacao(true);
					this.addPropertyChangeListener(y.getScrollPane());
					y.setLocation(facade.getPosicao(), facade.getPosicao());
					dp.add(y);
					facade.incrementaPosicao();
					MyJInternalFrame cr = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - Cb", new MyImage(
									(Image) array[1]));
					cr.setFoiOperacao(true);
					this.addPropertyChangeListener(cr.getScrollPane());
					cr.setLocation(facade.getPosicao(), facade.getPosicao());
					dp.add(cr);
					facade.incrementaPosicao();
					MyJInternalFrame cb = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - Cr", new MyImage(
									(Image) array[2]));
					cb.setFoiOperacao(true);
					this.addPropertyChangeListener(cb.getScrollPane());
					cb.setLocation(facade.getPosicao(), facade.getPosicao());
					dp.add(cb);
					facade.incrementaPosicao();
					dp.setSelectedFrame(y);
					dp.setSelectedFrame(cr);
					dp.setSelectedFrame(cb);
					try {
						y.setSelected(true);
						cr.setSelected(true);
						cb.setSelected(true);
					} catch (PropertyVetoException e) {
					}
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} else if (modelo.equals("XYZ")) {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Object[] array = facade.decomporXYZ((MyJInternalFrame) dp
							.getSelectedFrame());
					MyJInternalFrame x = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - X", new MyImage(
									(Image) array[0]));
					x.setFoiOperacao(true);
					this.addPropertyChangeListener(x.getScrollPane());
					x.setLocation(facade.getPosicao(), facade.getPosicao());
					dp.add(x);
					facade.incrementaPosicao();
					MyJInternalFrame y = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - Y", new MyImage(
									(Image) array[1]));
					y.setFoiOperacao(true);
					this.addPropertyChangeListener(y.getScrollPane());
					y.setLocation(facade.getPosicao(), facade.getPosicao());
					dp.add(y);
					facade.incrementaPosicao();
					MyJInternalFrame z = new MyJInternalFrame(this, facade,
							array[array.length - 1] + " - Z", new MyImage(
									(Image) array[2]));
					z.setFoiOperacao(true);
					this.addPropertyChangeListener(z.getScrollPane());
					z.setLocation(facade.getPosicao(), facade.getPosicao());
					dp.add(z);
					facade.incrementaPosicao();
					dp.setSelectedFrame(x);
					dp.setSelectedFrame(y);
					dp.setSelectedFrame(z);
					try {
						x.setSelected(true);
						y.setSelected(true);
						z.setSelected(true);
					} catch (PropertyVetoException e) {
					}
				}
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else if (evento.equalsIgnoreCase("Combinação")) {
			MyJInternalFrame original = (MyJInternalFrame) dp
					.getSelectedFrame();
			JanelaCombinar r = new JanelaCombinar(dp.getAllFrames(), original
					.getImage());
			if (r.ok()) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				MyJInternalFrame f = new MyJInternalFrame(this, facade, r
						.getNome(), new MyImage(r.getImageRecomposta()));
				f.setFoiOperacao(true);
				this.addPropertyChangeListener(f.getScrollPane());
				f.setLocation(facade.getPosicao(), facade.getPosicao());
				dp.add(f);
				facade.incrementaPosicao();
				try {
					f.setSelected(true);
				} catch (PropertyVetoException e) {
				}
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else if (evento.equals("Preto e Branco")){
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			try {
				PlanarImage image = (PlanarImage)JAI.create("AWTImage",f.getImage());
				new Binarize(image, this, f.getName());
			} catch (Exception e){				
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
		}
		else if (evento.equals("Escala de Cinza")) {
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame n = new MyJInternalFrame(this, facade, f.getName()+ " em Escala de Cinza", new MyImage(facade.escalaCinza(f.getImage())));
			n.setFoiOperacao(true);
			n.setLocation(facade.getPosicao(), facade.getPosicao());
			dp.add(n, BorderLayout.CENTER);
			dp.setSelectedFrame(n);
			this.addPropertyChangeListener(n.getScrollPane());
			facade.incrementaPosicao();
			try {
				n.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} 
		else if (evento.equals("Pseudo Colorização")) {
			JInternalFrame[] frames = dp.getAllFrames();
			if (frames.length > 0) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				JanelaPseudoColorizacao jpc = new JanelaPseudoColorizacao(
						frames);
				if (jpc.getOk()) {
					Image pretoBranco = ((MyJInternalFrame) frames[jpc.getPretoBranco()]).getImage();
					Image coloridaBase = ((MyJInternalFrame) frames[jpc.getColorido()]).getImage();
					Image colorida;
					try {
						colorida = facade.pseudocolorizacao(new ImageIcon(pretoBranco), new ImageIcon(coloridaBase));
						MyJInternalFrame pseudo = new MyJInternalFrame(this,facade, frames[jpc.getPretoBranco()].getName()
										+ " Pseudo Colorizada", new MyImage(colorida));
						pseudo.setFoiOperacao(true);
						this.addPropertyChangeListener(pseudo.getScrollPane());
						pseudo.setLocation(facade.getPosicao(), facade.getPosicao());
						dp.add(pseudo);
						facade.incrementaPosicao();
						try {
							pseudo.setSelected(true);
						} catch (PropertyVetoException e) {
						}
						dp.setSelectedFrame(pseudo);
					} catch (PseudoColorException e) {
						e.printStackTrace();
					}
				}
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else
				JOptionPane.showMessageDialog(null,
						"Selecione uma imagem para realizar a operação!!!",
						"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
		}			
		else if (evento.equals("Requantização")) {
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			JanelaRequantizar r = new JanelaRequantizar(((MyJInternalFrame) f)
					.getImage());
			if (r.getIGS() && r.getOk()) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				mostraTela(f, r);
			}
			if (r.getNumCores() != 0 && r.foiEscolhido() && r.getOk()) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				mostraTela(f, r);
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else if (evento.equals("Ilusão 1")) {
			JInternalFrame i = Ilusao1.create(this,facade);
			dp.add(i);
			i.setLocation(facade.getPosicao(), facade.getPosicao());
			addMouseListener((MouseListener) i);
			facade.incrementaPosicao();
			dp.setSelectedFrame(i);
			ilusao1.setEnabled(false);
			try {
				i.setSelected(true);
			} catch (PropertyVetoException e) {}
		} else if (evento.equals("Ilusão 2")){
			JInternalFrame i = Ilusao2.create(this,facade);
			dp.add(i);
			i.setLocation(facade.getPosicao(), facade.getPosicao());
			addMouseListener((MouseListener) i);
			facade.incrementaPosicao();
			dp.setSelectedFrame(i);
			ilusao2.setEnabled(false);
			try {
				i.setSelected(true);
			} catch (PropertyVetoException e) {}
		} else if (evento.equals("Ilusão 3")){
			JInternalFrame i = Ilusao3.create(this,facade);
			dp.add(i);
			i.setLocation(facade.getPosicao(), facade.getPosicao());
			addMouseListener((MouseListener) i);
			facade.incrementaPosicao();
			dp.setSelectedFrame(i);
			ilusao3.setEnabled(false);
			try {
				i.setSelected(true);
			} catch (PropertyVetoException e) {}
		} else if (evento.equals("Ilusão 4")){
			JInternalFrame i = Ilusao4.create(this,facade);
			dp.add(i);
			i.setLocation(facade.getPosicao(), facade.getPosicao());
			addMouseListener((MouseListener) i);
			facade.incrementaPosicao();
			dp.setSelectedFrame(i);
			ilusao4.setEnabled(false);
			try {
				i.setSelected(true);
			} catch (PropertyVetoException e) {}
		} else if (evento.equals("Ilusão 5")){
			JInternalFrame i = Ilusao5.create(this,facade);
			dp.add(i);
			i.setLocation(facade.getPosicao(), facade.getPosicao());
			addMouseListener((MouseListener) i);
			facade.incrementaPosicao();
			dp.setSelectedFrame(i);
			ilusao5.setEnabled(false);
			try {
				i.setSelected(true);
			} catch (PropertyVetoException e) {}
		} else if (evento.equals("Ilusão 6")){
			JInternalFrame i = Ilusao6.create(this,facade);
			dp.add(i);
			i.setLocation(facade.getPosicao(), facade.getPosicao());
			addMouseListener((MouseListener) i);
			facade.incrementaPosicao();
			dp.setSelectedFrame(i);
			ilusao6.setEnabled(false);
			try {
				i.setSelected(true);
			} catch (PropertyVetoException e) {}
		} else if (evento.equals("Ilusão 7")){
			JInternalFrame i = Ilusao7.create(this,facade);
			dp.add(i);
			i.setLocation(facade.getPosicao(), facade.getPosicao());
			addMouseListener((MouseListener) i);
			facade.incrementaPosicao();
			dp.setSelectedFrame(i);
			ilusao7.setEnabled(false);
			try {
				i.setSelected(true);
			} catch (PropertyVetoException e) {}
		} else if (evento.equals("Ilusão 8")){
			JInternalFrame i = Ilusao8.create(this,facade);
			dp.add(i);
			i.setLocation(facade.getPosicao(), facade.getPosicao());
			addMouseListener((MouseListener) i);
			facade.incrementaPosicao();
			dp.setSelectedFrame(i);
			ilusao8.setEnabled(false);
			try {
				i.setSelected(true);
			} catch (PropertyVetoException e) {}
		} else if (evento.equals("Ilusão 9")){
			JInternalFrame i = Ilusao9.create(this,facade);
			dp.add(i);
			i.setLocation(facade.getPosicao(), facade.getPosicao());
			addMouseListener((MouseListener) i);
			facade.incrementaPosicao();
			dp.setSelectedFrame(i);
			ilusao9.setEnabled(false);
			try {
				i.setSelected(true);
			} catch (PropertyVetoException e) {}
		} else if (evento.equals("Ilusão 10")){
			JInternalFrame i = Ilusao10.create(this,facade);
			dp.add(i);
			i.setLocation(facade.getPosicao(), facade.getPosicao());
			addMouseListener((MouseListener) i);
			facade.incrementaPosicao();
			dp.setSelectedFrame(i);
			ilusao10.setEnabled(false);
			try {
				i.setSelected(true);
			} catch (PropertyVetoException e) {}
		}
		
		// **********************************************************************//
		// ****************             Menu Operações              *************//
		// **********************************************************************//

		
		// ***************************//
		// ****   Op Pontuais    *****//
		// ***************************//
		else if (evento.equals("Lógicas")) {
			JanelaOperacoesLogicas j = new JanelaOperacoesLogicas(dp.getAllFrames());
			if (j.getSelecionados() != null) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				ArrayList<ImageIcon> desejada = new ArrayList<ImageIcon>();
				ArrayList<String> selecionada = j.getSelecionados();
				String nomes = adicionaNomes(desejada, selecionada);
				String operacao = j.getOperacao();
				if (operacao.equals("NOT")) {
					try {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						Image i = facade.not(desejada.get(0));
						MyJInternalFrame c = new MyJInternalFrame(this, facade,
								nomes + " - NOT", new MyImage(i));
						c.setFoiOperacao(true);
						c.setLocation(facade.getPosicao(), facade.getPosicao());
						dp.add(c);
						c.setSelected(true);
						this.addPropertyChangeListener(c.getScrollPane());
						this
								.firePropertyChange("show-color", false,
										showColors);
						facade.incrementaPosicao();
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					} catch (OperacaoLogicaException e) {
					} catch (PropertyVetoException e) {
					}
				} else if (operacao.equals("AND")) {
					try {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						Image i = facade.and(desejada);
						MyJInternalFrame c = new MyJInternalFrame(this, facade,
								nomes + " - AND", new MyImage(i));
						c.setFoiOperacao(true);
						c.setLocation(facade.getPosicao(), facade.getPosicao());
						dp.add(c);
						c.setSelected(true);
						this.addPropertyChangeListener(c.getScrollPane());
						facade.incrementaPosicao();
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					} catch (OperacaoLogicaException e) {
					} catch (PropertyVetoException e) {
					}
				} else if (operacao.equals("XOR")) {
					try {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						Image i = facade.xor(desejada);
						MyJInternalFrame c = new MyJInternalFrame(this, facade,
								nomes + " - XOR", new MyImage(i));
						c.setFoiOperacao(true);
						c.setLocation(facade.getPosicao(), facade.getPosicao());
						dp.add(c);
						c.setSelected(true);
						this.addPropertyChangeListener(c.getScrollPane());
						facade.incrementaPosicao();
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					} catch (OperacaoLogicaException e) {
					} catch (PropertyVetoException e) {
					}
				} else if (operacao.equals("NAND")) {
					try {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						Image i = facade.nand(desejada);
						MyJInternalFrame c = new MyJInternalFrame(this, facade,
								nomes + " - NAND", new MyImage(i));
						c.setFoiOperacao(true);
						c.setLocation(facade.getPosicao(), facade.getPosicao());
						dp.add(c);
						c.setSelected(true);
						this.addPropertyChangeListener(c.getScrollPane());
						facade.incrementaPosicao();
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					} catch (OperacaoLogicaException e) {
					} catch (PropertyVetoException e) {
					}
				} else if (operacao.equals("NOR")) {
					try {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						Image i = facade.nor(desejada);
						MyJInternalFrame c = new MyJInternalFrame(this, facade,
								nomes + " - NOR", new MyImage(i));
						c.setFoiOperacao(true);
						c.setLocation(facade.getPosicao(), facade.getPosicao());
						dp.add(c);
						c.setSelected(true);
						this.addPropertyChangeListener(c.getScrollPane());
						facade.incrementaPosicao();
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					} catch (OperacaoLogicaException e) {
					} catch (PropertyVetoException e) {
					}
				} else if (operacao.equals("OR")) {
					try {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						Image i = facade.or(desejada);
						MyJInternalFrame c = new MyJInternalFrame(this, facade,
								nomes + " - OR", new MyImage(i));
						c.setFoiOperacao(true);
						c.setLocation(facade.getPosicao(), facade.getPosicao());
						dp.add(c);
						c.setSelected(true);
						this.addPropertyChangeListener(c.getScrollPane());
						facade.incrementaPosicao();

					} catch (OperacaoLogicaException e) {
					} catch (PropertyVetoException e) {
					}
				}
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else if (evento.equals("Equalizar")){
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			try {
				PlanarImage image = (PlanarImage)JAI.create("AWTImage",f.getImage());
				BufferedImage img = EqualizarImagem.getImagemEqualizado(image.getAsBufferedImage());
				MyJInternalFrame imagemEqualizada = new MyJInternalFrame(this, facade,f.getName() + " Equalizada",new MyImage(img));
				imagemEqualizada.setFoiOperacao(true);
				this.addPropertyChangeListener(imagemEqualizada.getScrollPane());
				dp.add(imagemEqualizada,BorderLayout.CENTER);
				dp.setSelectedFrame(imagemEqualizada);
				this.addPropertyChangeListener(imagemEqualizada.getScrollPane());
				imagemEqualizada.setSelected(true);	
				imagemEqualizada.setLocation(facade.getPosicao(),facade.getPosicao());
				facade.incrementaPosicao();
				this.addPropertyChangeListener(imagemEqualizada.getScrollPane());	
				this.firePropertyChange("show-color", false, showColors);
				f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			} catch (Exception e){				
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));			
		}
		else if (evento.equals("Ganho/Offset")) {
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			JanelaAritmeticaGanhoOffset j = new JanelaAritmeticaGanhoOffset();
			if (j.getGain() != Double.MAX_VALUE
					&& j.getOffset() != Double.MAX_VALUE && j.foiOk()) {
				Image i = facade.aritmeticaPessoal(f.getImage(), j.getGain(), j
						.getOffset());
				MyJInternalFrame c = new MyJInternalFrame(this, facade, f
						.getName()
						+ " - Aritmética Pessoal", new MyImage(i));
				c.setFoiOperacao(true);
				dp.add(c, BorderLayout.CENTER);
				dp.setSelectedFrame(c);
				this.addPropertyChangeListener(c.getScrollPane());
				try {
					c.setSelected(true);
				} catch (PropertyVetoException e) {
				}
				c.setLocation(facade.getPosicao(), facade.getPosicao());
				facade.incrementaPosicao();
			}

		} 
		else if (evento.equals("Aritméticas")) {
			JanelaAritmetica j = new JanelaAritmetica(dp
					.getAllFrames());
			if (j.getSelecionados() != null) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				ArrayList<ImageIcon> desejada = new ArrayList<ImageIcon>();
				ArrayList<String> selecionada = j.getSelecionados();
				String nomes = adicionaNomes(desejada, selecionada);
				if (j.getOperacao().equals("Adicao")) {
					try {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						Image i = facade.adicao(desejada);
						MyJInternalFrame c = new MyJInternalFrame(this, facade,
								nomes + " - Adicao", new MyImage(i));
						c.setFoiOperacao(true);
						c.setLocation(facade.getPosicao(), facade.getPosicao());
						dp.add(c);
						c.setSelected(true);
						this.addPropertyChangeListener(c.getScrollPane());
						facade.incrementaPosicao();
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					} catch (OperacaoAritmeticaException e) {
					} catch (PropertyVetoException e) {
					}
				} else if (j.getOperacao().equals("Subtracao")) {
					try {

						Image i = facade.subtracao(desejada.get(0), desejada
								.get(1));
						MyJInternalFrame c = new MyJInternalFrame(this, facade,
								nomes + " - Subtracao", new MyImage(i));
						c.setFoiOperacao(true);
						c.setLocation(facade.getPosicao(), facade.getPosicao());
						dp.add(c);
						c.setSelected(true);
						this.addPropertyChangeListener(c.getScrollPane());
						facade.incrementaPosicao();

					} catch (OperacaoAritmeticaException e) {
					} catch (PropertyVetoException e) {
					}
				} else if (j.getOperacao().equals("Multiplicacao")) {
					try {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						Image i = facade.multiplica(desejada);
						MyJInternalFrame c = new MyJInternalFrame(this, facade,
								nomes + " - Multiplicacao", new MyImage(i));
						c.setFoiOperacao(true);
						c.setLocation(facade.getPosicao(), facade.getPosicao());
						dp.add(c);
						c.setSelected(true);
						this.addPropertyChangeListener(c.getScrollPane());
						facade.incrementaPosicao();

					} catch (OperacaoAritmeticaException e) {
					} catch (PropertyVetoException e) {
					}

				} else if (j.getOperacao().equals("Divisao")) {
					try {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						Image i = facade.divide(desejada.get(0), desejada
								.get(1));
						MyJInternalFrame c = new MyJInternalFrame(this, facade,
								nomes + " - Divisao", new MyImage(i));
						c.setFoiOperacao(true);
						c.setLocation(facade.getPosicao(), facade.getPosicao());
						dp.add(c);
						c.setSelected(true);
						this.addPropertyChangeListener(c.getScrollPane());
						facade.incrementaPosicao();

					} catch (OperacaoAritmeticaException e) {
					} catch (PropertyVetoException e) {
					}
				}
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} 
				
		// ***************************//
		// ******   Filtros    *******//
		// ***************************//
		
		else if (evento.equals("Média")) {
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			JanelaFiltro j = new JanelaFiltro("Média");
			if (j.getMaskara() > 0) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					MyJInternalFrame segmentado = new MyJInternalFrame(this,
							facade, "Média", new MyImage(facade
									.mean(f.getImage(), j.getMaskara(), j
											.getMaskara())));
					segmentado.setFoiOperacao(true);
					dp.add(segmentado, BorderLayout.CENTER);
					dp.setSelectedFrame(segmentado);
					this.addPropertyChangeListener(segmentado.getScrollPane());
					segmentado.setSelected(true);
					segmentado.setLocation(facade.getPosicao(), facade
							.getPosicao());
					facade.incrementaPosicao();
					this.addPropertyChangeListener(segmentado.getScrollPane());
					this.firePropertyChange("show-color", false, showColors);

				} catch (PropertyVetoException e) {
				} catch (ImageProcessingException e) {
				}
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} 
		else if (evento.equals("Mediana")) {
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			try {
				f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
				MyJInternalFrame segmentado = new MyJInternalFrame(this,facade, "Mediana", new MyImage(facade.median(f.getImage())));
				segmentado.setFoiOperacao(true);
				dp.add(segmentado, BorderLayout.CENTER);
				dp.setSelectedFrame(segmentado);
				segmentado.setSelected(true);
				this.addPropertyChangeListener(segmentado.getScrollPane());
				segmentado.setLocation(facade.getPosicao(), facade.getPosicao());
				facade.incrementaPosicao();
				this.addPropertyChangeListener(segmentado.getScrollPane());
				this.firePropertyChange("show-color", false, showColors);
				f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			} catch (PropertyVetoException e) {
			} catch (ImageProcessingException e) {
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else if(evento.equals("Moda")) {
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			try {
				f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
				MyJInternalFrame segmentado = new MyJInternalFrame(this,facade, "Moda", new MyImage(facade.moda(f.getImage())));
				segmentado.setFoiOperacao(true);
				dp.add(segmentado, BorderLayout.CENTER);
				dp.setSelectedFrame(segmentado);
				segmentado.setSelected(true);
				this.addPropertyChangeListener(segmentado.getScrollPane());
				segmentado.setLocation(facade.getPosicao(), facade.getPosicao());
				facade.incrementaPosicao();
				this.addPropertyChangeListener(segmentado.getScrollPane());
				this.firePropertyChange("show-color", false, showColors);
				f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			} catch (PropertyVetoException e) {
			} catch (ImageProcessingException e) {
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
		//********     Sobel     *********//
		else if (evento.equals("Sobel")){
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			final String name = f.getName();
			try {
				f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
				DeteccaoBordas img = new DeteccaoBordas(f.getImage(),"sobel");
				MyJInternalFrame sobel = new MyJInternalFrame(this, facade,name + " - Sobel",new MyImage(img.getBufferedImage()));
				sobel.setFoiOperacao(true);
				this.addPropertyChangeListener(sobel.getScrollPane());
				dp.add(sobel,BorderLayout.CENTER);
				dp.setSelectedFrame(sobel);
				this.addPropertyChangeListener(sobel.getScrollPane());
				sobel.setSelected(true);	
				sobel.setLocation(facade.getPosicao(),facade.getPosicao());
				facade.incrementaPosicao();
				this.addPropertyChangeListener(sobel.getScrollPane());	
				this.firePropertyChange("show-color", false, showColors);
				f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			} catch (Exception e){
				
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));			
		}
		else if (evento.equals("Prewit")) {
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			final String name = f.getName();
			try {
				f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
				DeteccaoBordas img = new DeteccaoBordas(f.getImage(),"prewitt");
				MyJInternalFrame prewitt = new MyJInternalFrame(this, facade,name + " - Prewitt",new MyImage(img.getBufferedImage()));
				prewitt.setFoiOperacao(true);
				this.addPropertyChangeListener(prewitt.getScrollPane());
				dp.add(prewitt,BorderLayout.CENTER);
				dp.setSelectedFrame(prewitt);
				this.addPropertyChangeListener(prewitt.getScrollPane());
				prewitt.setSelected(true);	
				prewitt.setLocation(facade.getPosicao(),facade.getPosicao());
				facade.incrementaPosicao();
				this.addPropertyChangeListener(prewitt.getScrollPane());	
				this.firePropertyChange("show-color", false, showColors);
				f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			} catch (Exception e){
				
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));			
		}
		else if (evento.equals("Roberts")) {
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			final String name = f.getName();
			try {
				f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
				DeteccaoBordas img = new DeteccaoBordas(f.getImage(),"roberts");
				MyJInternalFrame roberts20061 = new MyJInternalFrame(this, facade,name + " - Roberts",new MyImage(img.getBufferedImage()));
				roberts20061.setFoiOperacao(true);
				this.addPropertyChangeListener(roberts20061.getScrollPane());
				dp.add(roberts20061,BorderLayout.CENTER);
				dp.setSelectedFrame(roberts20061);
				this.addPropertyChangeListener(roberts20061.getScrollPane());
				roberts20061.setSelected(true);	
				roberts20061.setLocation(facade.getPosicao(),facade.getPosicao());
				facade.incrementaPosicao();
				this.addPropertyChangeListener(roberts20061.getScrollPane());	
				this.firePropertyChange("show-color", false, showColors);
				f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			} catch (Exception e){
				
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));			
		}
		else if (evento.equals("Laplace")) {
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();	
			final String name = f.getName();
			try {
				f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));			
				DeteccaoBordas img = new DeteccaoBordas(f.getImage(),"laplace");
				MyJInternalFrame laplace = new MyJInternalFrame(this, facade,name + " - Laplace",new MyImage(img.getBufferedImage()));
				laplace.setFoiOperacao(true);
				this.addPropertyChangeListener(laplace.getScrollPane());
				dp.add(laplace,BorderLayout.CENTER);
				dp.setSelectedFrame(laplace);				
				laplace.setSelected(true);	
				laplace.setLocation(facade.getPosicao(),facade.getPosicao());
				facade.incrementaPosicao();
				this.addPropertyChangeListener(laplace.getScrollPane());	
				this.firePropertyChange("show-color", false, showColors);
				f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			} catch (Exception e){
				
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));			
		}
		else if (evento.equals("Gaussiano")) {
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			JanelaFiltro j = new JanelaFiltro("Gaussiano");
			if (j.getMaskara() > 0) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					MyJInternalFrame segmentado = new MyJInternalFrame(this,
							facade, "Gaussiano", new MyImage(facade.gaussian(f
									.getImage(), j.getMaskara(),
									j.getMaskara(), 1)));
					segmentado.setFoiOperacao(true);
					dp.add(segmentado, BorderLayout.CENTER);
					dp.setSelectedFrame(segmentado);
					this.addPropertyChangeListener(segmentado.getScrollPane());
					segmentado.setSelected(true);
					segmentado.setLocation(facade.getPosicao(), facade
							.getPosicao());
					facade.incrementaPosicao();
					this.addPropertyChangeListener(segmentado.getScrollPane());
					this.firePropertyChange("show-color", false, showColors);

				} catch (PropertyVetoException e) {
				} catch (ImageProcessingException e) {
				}
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else if(evento.equals("Emboss")) {
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			final String name = f.getName();
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			try {
				Emboss img = new Emboss(f.getImage());
				MyJInternalFrame segmentado = new MyJInternalFrame(this, facade,name + " - Emboss",new MyImage(img.getBufferedImage()));
				segmentado.setFoiOperacao(true);
				dp.add(segmentado,BorderLayout.CENTER);
				dp.setSelectedFrame(segmentado);
				this.addPropertyChangeListener(segmentado.getScrollPane());
				segmentado.setSelected(true);	
				segmentado.setLocation(facade.getPosicao(),facade.getPosicao());
				facade.incrementaPosicao();
				this.addPropertyChangeListener(segmentado.getScrollPane());	
				this.firePropertyChange("show-color", false, showColors);
			} catch (Exception e){
				
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else if (evento.equals("Frei & Chen")){
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			final String name = f.getName();
			try {
				f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
				DeteccaoBordas img = new DeteccaoBordas(f.getImage(),"freichen");
				MyJInternalFrame freichen = new MyJInternalFrame(this, facade,name + " - Frei & Chen",new MyImage(img.getBufferedImage()));
				freichen.setFoiOperacao(true);
				this.addPropertyChangeListener(freichen.getScrollPane());
				dp.add(freichen,BorderLayout.CENTER);
				dp.setSelectedFrame(freichen);
				this.addPropertyChangeListener(freichen.getScrollPane());
				freichen.setSelected(true);	
				freichen.setLocation(facade.getPosicao(),facade.getPosicao());
				facade.incrementaPosicao();
				this.addPropertyChangeListener(freichen.getScrollPane());	
				this.firePropertyChange("show-color", false, showColors);
				f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			} catch (Exception e){
				
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));			
		}
		
		// **********************************//
		// ****   Geradores de Ruído    *****//
		// **********************************//
		
		else if (evento.equals("Salt-and-Pepper")){
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			final String name = f.getName();
			try {
				f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
				GeradorDeRuido img = new GeradorDeRuido(f.getImage());
				MyJInternalFrame salt_and_pepper = new MyJInternalFrame(this, facade,name + 
						" - Salt-and-pepper",new MyImage(img.salt_and_pepper()));
				salt_and_pepper.setFoiOperacao(true);
				this.addPropertyChangeListener(salt_and_pepper.getScrollPane());
				dp.add(salt_and_pepper,BorderLayout.CENTER);
				dp.setSelectedFrame(salt_and_pepper);
				this.addPropertyChangeListener(salt_and_pepper.getScrollPane());
				salt_and_pepper.setSelected(true);
				salt_and_pepper.setLocation(facade.getPosicao(),facade.getPosicao());
				facade.incrementaPosicao();
				this.addPropertyChangeListener(salt_and_pepper.getScrollPane());	
				this.firePropertyChange("show-color", false, showColors);
				f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			} catch (Exception e){
				e.printStackTrace();
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
		}
		else if (evento.equals("Ruído Gaussiano")){
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			final String name = f.getName();
			try {
				f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
				JanelaRuidoGaussiano j = new JanelaRuidoGaussiano();
				double desvpad = j.getDesvioPadrao();
				if (desvpad != 0) {
					RuidoGaussiano img = new RuidoGaussiano(f.getImage());
					MyJInternalFrame salt_and_pepper = new MyJInternalFrame(this, facade,name + 
							" - Ruído Gaussiano",new MyImage(img.Gaussian(desvpad)));
					salt_and_pepper.setFoiOperacao(true);
					this.addPropertyChangeListener(salt_and_pepper.getScrollPane());
					dp.add(salt_and_pepper,BorderLayout.CENTER);
					dp.setSelectedFrame(salt_and_pepper);
					this.addPropertyChangeListener(salt_and_pepper.getScrollPane());
					salt_and_pepper.setSelected(true);
					salt_and_pepper.setLocation(facade.getPosicao(),facade.getPosicao());
					facade.incrementaPosicao();
					this.addPropertyChangeListener(salt_and_pepper.getScrollPane());	
					this.firePropertyChange("show-color", false, showColors);
					f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
				}
			} catch (Exception e){
				e.printStackTrace();
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
		// ***************************//
		// ****   Geométricas    *****//
		// ***************************//
		else if (evento.equals("Rotacionar")) {
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			JanelaRotacionar j = new JanelaRotacionar();
			int graus = j.getAngulo();
			if (graus != 0) {
				try {
					f.setImage(MyBufferedImage.makeImage(facade.rotacionar(f
							.getMyImage().getRenderedImage(), graus)));
				} catch (RotacionarException e) {
					e.printStackTrace();

				}
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else if (evento.equals("Redimensionar")) {
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			JanelaRedimensionar m = new JanelaRedimensionar();
			if (m.getTipo().equals("Pixel")) {
				if (m.getModo().equals("Bicubic")) {
					try {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						Image i = facade.emPixelsBICUBIC(f.getMyImage(), m
								.getLargura(), m.getAltura());
						f.setImage(i);
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					} catch (RedimensionarException e) {
					}
				} else if (m.getModo().equals("Bilinear")) {
					try {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						Image i = facade.emPixelsBILINEAR(f.getMyImage(), m
								.getLargura(), m.getAltura());
						f.setImage(i);
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					} catch (RedimensionarException e) {
					}
				} else if (m.getModo().equals("Nearest")) {
					try {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						Image i = facade.emPixelsNEAREST_NEIGHBOR(f
								.getMyImage(), m.getLargura(), m.getAltura());
						f.setImage(i);
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					} catch (RedimensionarException e) {
					}
				}
			} else if (m.getTipo().equals("Porcentagem")) {
				if (m.getModo().equals("Bicubic")) {
					try {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						Image i = facade.emPorcentagemBICUBIC(f.getMyImage(), m
								.getPorcento(), m.getPorcento());
						f.setImage(i);
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

					} catch (RedimensionarException e) {
					}
				} else if (m.getModo().equals("Bilinear")) {
					try {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						Image i = facade.emPorcentagemBILINEAR(f.getMyImage(),
								m.getPorcento(), m.getPorcento());
						f.setImage(i);
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					} catch (RedimensionarException e) {
					}
				} else if (m.getModo().equals("Nearest")) {
					try {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						Image i = facade
								.emPorcentagemNEAREST_NEIGHBOR(f.getMyImage(),
										m.getPorcento(), m.getPorcento());
						f.setImage(i);
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					} catch (RedimensionarException e) {
					}
				}
			}
		} 
		else if (evento.equals("Ampliar")) {
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			try {
				f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
				JanelaAmpliar z = new JanelaAmpliar();
				if (z.foiOk()) {
					RenderedImage i = facade.zoom(f.getMyImage(), z.getZoom());
					f.setImage(i);
				}
				f.getScrollPane()
						.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} catch (Exception e) {
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} 
		else if (evento.equals("Zoom Mais")) {
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			try {
				RenderedImage i = facade.zoomMais(f.getMyImage());
				f.setImage(i);
			} catch (ZoomException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO!",
						JOptionPane.ERROR_MESSAGE);
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else if (evento.equals("Zoom Menos")) {
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			try {
				RenderedImage i = facade.zoomMenos(f.getMyImage());
				f.setImage(i);
			} catch (ZoomException e) {
				JOptionPane.showMessageDialog(this, "Tamanho minimo atingido",
						"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}


		// **********************************************************************//
		// ****************           Menu Classificação            *************//
		// **********************************************************************//
		else if (evento.equals("Global")) {
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			JanelaSegmentacaoGlobal j = new JanelaSegmentacaoGlobal();
			int limiar = j.getLimiar();
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			if (limiar > 0) {
				try {
					MyJInternalFrame segmentado = new MyJInternalFrame(this,
							facade, "Segmentação Global", new MyImage(facade
									.segmentacaoGlobal(f.getImage(), limiar)));
					segmentado.setFoiOperacao(true);
					this.addPropertyChangeListener(segmentado.getScrollPane());
					dp.add(segmentado, BorderLayout.CENTER);
					dp.setSelectedFrame(segmentado);
					segmentado.setLocation(facade.getPosicao(), facade
							.getPosicao());
					segmentado.setSelected(true);
					facade.incrementaPosicao();
					this.addPropertyChangeListener(segmentado.getScrollPane());
					this.firePropertyChange("show-color", false, showColors);

				} catch (ImageProcessingException e) {
				} catch (PropertyVetoException e) {
				}
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else if (evento.equals("Adaptativa Básica")) {
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			JanelaSegmentacaoAdaptativa j = new JanelaSegmentacaoAdaptativa();
			int dimensao = j.getDimensao();
			if (dimensao >= 0) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					MyJInternalFrame segmentado = new MyJInternalFrame(this,
							facade, "Segmentação Adaptativa", new MyImage(
									facade.segmentacaoAdaptativa(f.getImage(),
											dimensao)));
					segmentado.setFoiOperacao(true);
					this.addPropertyChangeListener(segmentado.getScrollPane());
					dp.add(segmentado, BorderLayout.CENTER);
					dp.setSelectedFrame(segmentado);
					segmentado.setLocation(facade.getPosicao(), facade
							.getPosicao());
					segmentado.setSelected(true);
					facade.incrementaPosicao();
					this.addPropertyChangeListener(segmentado.getScrollPane());
					this.firePropertyChange("show-color", false, showColors);

				} catch (ImageProcessingException e) {
				} catch (PropertyVetoException e) {
				}
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else if (evento.equals("Crescimento de Região")){
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			final String nome = f.getName();
			try {
				f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
				PlanarImage image = (PlanarImage)JAI.create("AWTImage",f.getImage());
				RegionGrowingSegmentation task = new RegionGrowingSegmentation(image,false,true);
				task.run();
				DisplayDEM display = new DisplayDEM(task.getOutput());
				image = display.getSurrogateImage();
				MyJInternalFrame segmentacaoRegionGrowing = new MyJInternalFrame(this, facade,nome + " - Segmentaçao - crescimento de região",new MyImage(image.getAsBufferedImage()));
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
				f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			} catch (Exception e){				
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));			
		}
		else if (evento.equals("Paralelepipedo")){
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			final String nome = f.getName();
			PlanarImage img0 = (PlanarImage)JAI.create("AWTImage",f.getImage());
			GerenciadorClassesParalelepipedo.getInstance().setImagem(img0.getAsBufferedImage());
			final Opcao2 opcao2 = new Opcao2();
			opcao2.addWindowFocusListener(new WindowFocusListener(){
				public void windowGainedFocus(WindowEvent arg0) {
					if (!opcao2.notClosed()) {
						criaImagemClassificada(opcao2, nome);
					}
				}
				public void windowLostFocus(WindowEvent arg0) {
					if (!opcao2.notClosed()) {
						criaImagemClassificada(opcao2, nome);
					}
				}
			});	
			final Opcao3 opcao3 = new Opcao3();
			opcao3.addWindowFocusListener(new WindowFocusListener(){
				public void windowGainedFocus(WindowEvent arg0) {
					if (!opcao3.notClosed()) {
						criaImagemClassificada(opcao3, nome);
					}
				}
				public void windowLostFocus(WindowEvent arg0) {
					if (!opcao3.notClosed()) {
						criaImagemClassificada(opcao3, nome);
					}
				}
			});				
			final Opcao1 opcao = new Opcao1(opcao2, opcao3);
			opcao.setVisible(true);
		}
		
		// **********************************************************************//
		// ****************         Menu Morfologia                 *************//
		// **********************************************************************//
		else if (evento.equals("Erosão 4")){
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame n = new MyJInternalFrame(this, facade, f.getName()+ " Erosão 4", new MyImage(facade.erosao4(f.getImage())));
			n.setFoiOperacao(true);
			n.setLocation(facade.getPosicao(), facade.getPosicao());
			dp.add(n, BorderLayout.CENTER);
			dp.setSelectedFrame(n);
			this.addPropertyChangeListener(n.getScrollPane());
			facade.incrementaPosicao();
			try {
				n.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
		else if (evento.equals("Erosão 8")){
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame n = new MyJInternalFrame(this, facade, f.getName()+ " Erosão 8", new MyImage(facade.erosao8(f.getImage())));
			n.setFoiOperacao(true);
			n.setLocation(facade.getPosicao(), facade.getPosicao());
			dp.add(n, BorderLayout.CENTER);
			dp.setSelectedFrame(n);
			this.addPropertyChangeListener(n.getScrollPane());
			facade.incrementaPosicao();
			try {
				n.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else if (evento.equals("Dilatação 4")){
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame n = new MyJInternalFrame(this, facade, f.getName()+ " Dilatação 4", new MyImage(facade.dilatacao4(f.getImage())));
			n.setFoiOperacao(true);
			n.setLocation(facade.getPosicao(), facade.getPosicao());
			dp.add(n, BorderLayout.CENTER);
			dp.setSelectedFrame(n);
			this.addPropertyChangeListener(n.getScrollPane());
			facade.incrementaPosicao();
			try {
				n.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else if (evento.equals("Dilatação 8")){
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame n = new MyJInternalFrame(this, facade, f.getName()+ " Dilatação 8", new MyImage(facade.dilatacao8(f.getImage())));
			n.setFoiOperacao(true);
			n.setLocation(facade.getPosicao(), facade.getPosicao());
			dp.add(n, BorderLayout.CENTER);
			dp.setSelectedFrame(n);
			this.addPropertyChangeListener(n.getScrollPane());
			facade.incrementaPosicao();
			try {
				n.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else if (evento.equals("Abertura 4")){
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame n = new MyJInternalFrame(this, facade, f.getName()+ " Abertura 4", new MyImage(facade.abertura4(f.getImage())));
			n.setFoiOperacao(true);
			n.setLocation(facade.getPosicao(), facade.getPosicao());
			dp.add(n, BorderLayout.CENTER);
			dp.setSelectedFrame(n);
			this.addPropertyChangeListener(n.getScrollPane());
			facade.incrementaPosicao();
			try {
				n.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else if (evento.equals("Abertura 8")){
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame n = new MyJInternalFrame(this, facade, f.getName()+ " Abertura 8", new MyImage(facade.abertura8(f.getImage())));
			n.setFoiOperacao(true);
			n.setLocation(facade.getPosicao(), facade.getPosicao());
			dp.add(n, BorderLayout.CENTER);
			dp.setSelectedFrame(n);
			this.addPropertyChangeListener(n.getScrollPane());
			facade.incrementaPosicao();
			try {
				n.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else if (evento.equals("Fechamento 4")){
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame n = new MyJInternalFrame(this, facade, f.getName()+ " Fechamento 4", new MyImage(facade.fechamento4(f.getImage())));
			n.setFoiOperacao(true);
			n.setLocation(facade.getPosicao(), facade.getPosicao());
			dp.add(n, BorderLayout.CENTER);
			dp.setSelectedFrame(n);
			this.addPropertyChangeListener(n.getScrollPane());
			facade.incrementaPosicao();
			try {
				n.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else if (evento.equals("Fechamento 8")){
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = (MyJInternalFrame) dp.getSelectedFrame();
			f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame n = new MyJInternalFrame(this, facade, f.getName()+ " Fechamento 8", new MyImage(facade.fechamento8(f.getImage())));
			n.setFoiOperacao(true);
			n.setLocation(facade.getPosicao(), facade.getPosicao());
			dp.add(n, BorderLayout.CENTER);
			dp.setSelectedFrame(n);
			this.addPropertyChangeListener(n.getScrollPane());
			facade.incrementaPosicao();
			try {
				n.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
		
		// ***********************************************************************//
		// ****************              Menu Ajuda                  *************//
		// ***********************************************************************//
		else if (evento.equals("Sobre FePDI"))
			new JanelaSobre();

		else if (evento.equals("Tópicos de Ajuda"))
			new JanelaAjuda();

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
	private String adicionaNomes(ArrayList<ImageIcon> desejada,
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
		if (evt.getSource() == coordenadaPixel) {

			JRadioButtonMenuItem bt = (JRadioButtonMenuItem) evt.getSource();
			if (bt.isSelected()) {
				JInternalFrame[] frames = dp.getAllFrames();
				for (int k = 0; k < frames.length; k++) {
					((MyJInternalFrame) frames[k]).getScrollPane()
							.setExibirPixel(true);
				}
				boolean tmp = showColors;
				showColors = bt.isSelected();
				this.firePropertyChange("show-color", tmp, showColors);
			} else {
				JInternalFrame[] frames = dp.getAllFrames();
				for (int k = 0; k < frames.length; k++) {
					if (verificaInstancia((MyJInternalFrame) frames[k]))
						((MyJInternalFrame) frames[k]).getScrollPane().setExibirPixel(false);
				}
			}
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
	protected void criaImagemClassificada(Opcao3 opcao2, String nome){
		try {	
			MyJInternalFrame freichen20061 = new MyJInternalFrame(this, facade,"Classificação Paralelepípedo - " + nome,new MyImage(opcao2.getResultado().getBufferedImage()));
			freichen20061.setFoiOperacao(true);
			this.addPropertyChangeListener(freichen20061.getScrollPane());
			dp.add(freichen20061,BorderLayout.CENTER);
			dp.setSelectedFrame(freichen20061);
			this.addPropertyChangeListener(freichen20061.getScrollPane());
			freichen20061.setSelected(true);	
			freichen20061.setLocation(facade.getPosicao(),facade.getPosicao());
			facade.incrementaPosicao();
			this.addPropertyChangeListener(freichen20061.getScrollPane());	
			this.firePropertyChange("show-color", false, showColors);
			Legenda legenda = new Legenda(GerenciadorClassesParalelepipedo.getInstance().getClasses());
			GerenciadorClassesParalelepipedo.getInstance().zeraClasses();
			legenda.setLocation(facade.getPosicao() + 5,facade.getPosicao()+ 5);
			legenda.setVisible(true);
		} catch (Exception e){
			
		}			
	}
	
	protected void criaImagemClassificada(Opcao2 opcao2, String nome){
		try {	
			MyJInternalFrame freichen20061 = new MyJInternalFrame(this, facade,"Classificação Paralelepípedo - " + nome,new MyImage(opcao2.getResultado().getBufferedImage()));
			freichen20061.setFoiOperacao(true);
			this.addPropertyChangeListener(freichen20061.getScrollPane());
			dp.add(freichen20061,BorderLayout.CENTER);
			dp.setSelectedFrame(freichen20061);
			this.addPropertyChangeListener(freichen20061.getScrollPane());
			freichen20061.setSelected(true);	
			freichen20061.setLocation(facade.getPosicao(),facade.getPosicao());
			facade.incrementaPosicao();
			this.addPropertyChangeListener(freichen20061.getScrollPane());	
			this.firePropertyChange("show-color", false, showColors);
			Legenda legenda = new Legenda(GerenciadorClassesParalelepipedo.getInstance().getClasses());
			GerenciadorClassesParalelepipedo.getInstance().zeraClasses();
			legenda.setLocation(facade.getPosicao() + 5,facade.getPosicao()+ 5);
			legenda.setVisible(true);
		} catch (Exception e){
			
		}			
	}

}