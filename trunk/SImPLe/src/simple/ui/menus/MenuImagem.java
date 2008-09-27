package simple.ui.menus;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

import simple.manipulacoes.util.ImageContrast;
import simple.manipulacoes.util.MyImage;
import simple.manipulacoes.util.MyJInternalFrame;
import simple.modules.operacoes.radiometricas.Binarize;
import simple.modules.propriedades.ilusaoOptica.Ilusao1;
import simple.modules.propriedades.ilusaoOptica.Ilusao10;
import simple.modules.propriedades.ilusaoOptica.Ilusao2;
import simple.modules.propriedades.ilusaoOptica.Ilusao3;
import simple.modules.propriedades.ilusaoOptica.Ilusao4;
import simple.modules.propriedades.ilusaoOptica.Ilusao5;
import simple.modules.propriedades.ilusaoOptica.Ilusao6;
import simple.modules.propriedades.ilusaoOptica.Ilusao7;
import simple.modules.propriedades.ilusaoOptica.Ilusao8;
import simple.modules.propriedades.ilusaoOptica.Ilusao9;
import simple.ui.janelas.JanelaCombinar;
import simple.ui.janelas.JanelaDecomposicao;
import simple.ui.janelas.JanelaHistogramaColorido;
import simple.ui.janelas.JanelaHistogramaPorCanal;
import simple.ui.janelas.JanelaPseudoColorizacao;
import simple.ui.janelas.JanelaRequantizar;
import simple.ui.janelas.SImPLe;
import simple.excecoes.PseudoColorException;

/**
 * Responsável pela instanciação do menu de imagem e acesso às suas respectivas
 * funcionalidades.
 * 
 */
public class MenuImagem extends SimpleMenu {

	private static final long serialVersionUID = 1L;

	private JMenu histograma, perfilLC,tresD,simples, modoApresentacao, combDecompor, ilusao;

	private JMenuItem colorido, porCanal,linha,coluna, linhaSimples, colunaSimples, brilhoContraste, peB, 
	escalaCinza, pseudoColorizacao, combinar, decompor, requantizacao, ilusao1, ilusao2, 
	ilusao3, ilusao4, ilusao5, ilusao6, ilusao7, ilusao8, ilusao9, ilusao10;

	private JRadioButtonMenuItem coordenadaPixel;

	/**
	 * Cria um menu do tipo Imagem
	 * @param simple a instância do simple que o menu deve ser adicionado
	 */
	public MenuImagem(SImPLe simple) {
		super(MENU_IMAGEM, simple);
		setMnemonic(KeyEvent.VK_I);
		configureMenu();

	}

	private void configureMenu(){

		histograma = new JMenu("Histograma");
		histograma.setIcon(new ImageIcon("Resource/Icones/transparente.gif"));
		histograma.setMnemonic(KeyEvent.VK_H);

		colorido = configureMenuItem("Colorido", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_O, "Resource/Icones/histogram.gif");
		porCanal = configureMenuItem("Por Canal", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_A, "Resource/Icones/histo.gif");

		histograma.add(colorido);
		histograma.add(porCanal);

		perfilLC = new JMenu("Perfil de Linha/Coluna");
		perfilLC.setIcon(new ImageIcon("Resource/Icones/transparente.gif"));
		perfilLC.setMnemonic(KeyEvent.VK_P);

		tresD = new JMenu("3D");
		tresD.setMnemonic(KeyEvent.VK_D);
		tresD.setEnabled(false);

		linha = configureMenuItem("Linha", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_L, "");
		linha.setActionCommand("Linha 3D");
		linha.setEnabled(false);

		coluna = configureMenuItem("Colna", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_C, "");
		coluna.setActionCommand("Coluna 3D");
		coluna.setEnabled(false);

		tresD.add(linha);
		tresD.add(coluna);

		simples = new JMenu("Simples");
		simples.setMnemonic(KeyEvent.VK_S);

		linhaSimples = configureMenuItem("Linha", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_L, "Resource/Icones/perfilLinha.gif");
		colunaSimples = configureMenuItem("Coluna", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_C, "Resource/Icones/perfilColuna.gif");

		simples.add(linhaSimples);
		simples.add(colunaSimples);

		perfilLC.add(tresD);
		perfilLC.add(simples);

		brilhoContraste = configureMenuItem("Brilho e Contraste", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_B, "Resource/Icones/transparente.gif"); 

		coordenadaPixel = new JRadioButtonMenuItem("Coordenada de Pixel");
		coordenadaPixel.setIcon(new ImageIcon("Resource/Icones/coordinate.gif"));
		coordenadaPixel.setMnemonic(KeyEvent.VK_C);
		coordenadaPixel.addChangeListener(super.getSimple());

		modoApresentacao = new JMenu("Modo de Apresentação");
		modoApresentacao.setIcon(new ImageIcon("Resource/Icones/transparente.gif"));
		modoApresentacao.setMnemonic(KeyEvent.VK_A);

		peB = configureMenuItem("Preto e Branco", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_P, "Resource/Icones/P&B.jpg");

		escalaCinza = configureMenuItem("Escala de Cinza", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_E, "Resource/Icones/grayscale.jpg");

		pseudoColorizacao = configureMenuItem("Pseudo Colorização", NO_VALUE, NO_VALUE , NO_VALUE, KeyEvent.VK_C, "Resource/Icones/pseudo.gif");

		modoApresentacao.add(peB);
		modoApresentacao.add(escalaCinza);
		modoApresentacao.add(pseudoColorizacao);

		combDecompor = new JMenu("Combinação/Decomposição de Canais");
		combDecompor.setMnemonic(KeyEvent.VK_D);
		combDecompor.setIcon(new ImageIcon("Resource/Icones/transparente.gif"));

		combinar = configureMenuItem("Combinação", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_C, "Resource/Icones/recompor.gif");

		decompor = configureMenuItem("Decomposição", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_D, "Resource/Icones/decompor.gif");

		combDecompor.add(combinar);
		combDecompor.add(decompor);

		requantizacao = configureMenuItem("Requantização", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_R, "Resource/Icones/transparente.gif");

		ilusao = new JMenu("Ilusões de Ótica");
		ilusao.setIcon(new ImageIcon("Resource/Icones/ilusao.gif"));
		ilusao.setMnemonic(KeyEvent.VK_I);

		ilusao1 = new JMenuItem("Ilusão 1");
		ilusao1.setMnemonic(KeyEvent.VK_1);
		ilusao1.addActionListener(super.getSimple());

		ilusao2 = new JMenuItem("Ilusão 2");
		ilusao2.setMnemonic(KeyEvent.VK_2);
		ilusao2.addActionListener(super.getSimple());

		ilusao3 = new JMenuItem("Ilusão 3");
		ilusao3.setMnemonic(KeyEvent.VK_3);
		ilusao3.addActionListener(super.getSimple());

		ilusao4 = new JMenuItem("Ilusão 4");
		ilusao4.setMnemonic(KeyEvent.VK_4);
		ilusao4.addActionListener(super.getSimple());

		ilusao5 = new JMenuItem("Ilusão 5");
		ilusao5.setMnemonic(KeyEvent.VK_5);
		ilusao5.addActionListener(super.getSimple());

		ilusao6 = new JMenuItem("Ilusão 6");
		ilusao6.setMnemonic(KeyEvent.VK_6);
		ilusao6.addActionListener(super.getSimple());

		ilusao7 = new JMenuItem("Ilusão 7");
		ilusao7.setMnemonic(KeyEvent.VK_7);
		ilusao7.addActionListener(super.getSimple());

		ilusao8 = new JMenuItem("Ilusão 8");
		ilusao8.setMnemonic(KeyEvent.VK_8);
		ilusao8.addActionListener(super.getSimple());

		ilusao9 = new JMenuItem("Ilusão 9");
		ilusao9.setMnemonic(KeyEvent.VK_9);
		ilusao9.addActionListener(super.getSimple());

		ilusao10 = new JMenuItem("Ilusão 10");
		ilusao10.setMnemonic(KeyEvent.VK_0);
		ilusao10.addActionListener(super.getSimple());

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

		add(histograma);
		add(perfilLC);
		add(brilhoContraste);
		add(coordenadaPixel);
		addSeparator();
		add(modoApresentacao);
		add(combDecompor);
		add(requantizacao);
		addSeparator();
		add(ilusao);
	}

	/**
	 * Habilita ou desabilita (de acordo com o valor do parâmetro) os botões de histograma,
	 * perfilLC, brilho e Contraste, coordenadaPixel, modo de apresentação, decomposição
	 * e requantização.
	 * 
	 * @param habilita se verdadeiro, habilita os referidos botões. Em caso contrário, desabilita-os.
	 */
	public void habilitaBotoes(boolean habilita){
		histograma.setEnabled(habilita);
		perfilLC.setEnabled(habilita);
		brilhoContraste.setEnabled(habilita);
		coordenadaPixel.setEnabled(habilita);
		modoApresentacao.setEnabled(habilita);
		combDecompor.setEnabled(habilita);
		requantizacao.setEnabled(habilita);
	}

	/**
	 * Método que realiza o histograma colorido
	 */
	public void colorido(){
		MyJInternalFrame f = (MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame();
		new JanelaHistogramaColorido(f.getImage(), getSimple().getFacade());
	}

	/**
	 * Realização do histograma por canais
	 */
	public void porCanal(){
		MyJInternalFrame f = (MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame();
		new JanelaHistogramaPorCanal(f.getImage(), getSimple().getFacade());
	}

	/**
	 * Permite que o brilho e contraste da imagem sejam alterados
	 */
	public void brilhoEContraste(){
		MyJInternalFrame f = (MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame();
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		try {
			new ImageContrast(f, super.getSimple());
		} catch (Exception e){

		}
		getSimple().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	/**
	 * Realiza a decomposição da imagem em canais.
	 */
	public void decomposicao(){
		JanelaDecomposicao jd = new JanelaDecomposicao();
		String modelo = jd.getModelo();
		if (jd.ok()) {
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			if (modelo.equals("RGB")) {
				Object[] array = getSimple().getFacade().decomporRGB((MyJInternalFrame) getSimple().getDesktopPane()
						.getSelectedFrame());
				MyJInternalFrame r = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - Red", new MyImage(
								(Image) array[0]));
				r.setFoiOperacao(true);
				r.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().addPropertyChangeListener(r.getScrollPane());
				getSimple().getDesktopPane().add(r);
				getSimple().getFacade().incrementaPosicao();
				MyJInternalFrame g = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - Green", new MyImage(
								(Image) array[1]));
				g.setFoiOperacao(true);
				g.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().addPropertyChangeListener(g.getScrollPane());
				getSimple().getDesktopPane().add(g);
				getSimple().getFacade().incrementaPosicao();
				MyJInternalFrame b = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - Blue", new MyImage(
								(Image) array[2]));
				b.setFoiOperacao(true);
				getSimple().addPropertyChangeListener(b.getScrollPane());
				b.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().getDesktopPane().add(b);
				getSimple().getFacade().incrementaPosicao();
				getSimple().getDesktopPane().setSelectedFrame(r);
				getSimple().getDesktopPane().setSelectedFrame(g);
				getSimple().getDesktopPane().setSelectedFrame(b);
				try {
					r.setSelected(true);
					g.setSelected(true);
					b.setSelected(true);
				} catch (PropertyVetoException e) {
				}

				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if (modelo.equals("CMY")) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				Object[] array = getSimple().getFacade().decomporCMY((MyJInternalFrame) getSimple().getDesktopPane()
						.getSelectedFrame());
				MyJInternalFrame c = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - Cyan", new MyImage(
								(Image) array[0]));
				c.setFoiOperacao(true);
				getSimple().addPropertyChangeListener(c.getScrollPane());
				c.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().getDesktopPane().add(c);
				getSimple().getFacade().incrementaPosicao();
				MyJInternalFrame m = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - Magenta",
						new MyImage((Image) array[1]));
				m.setFoiOperacao(true);
				getSimple().addPropertyChangeListener(m.getScrollPane());
				m.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().getDesktopPane().add(m);
				getSimple().getFacade().incrementaPosicao();
				MyJInternalFrame y = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - Yellow", new MyImage(
								(Image) array[2]));
				y.setFoiOperacao(true);
				getSimple().addPropertyChangeListener(y.getScrollPane());
				y.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().getDesktopPane().add(y);
				getSimple().getFacade().incrementaPosicao();
				getSimple().getDesktopPane().setSelectedFrame(c);
				getSimple().getDesktopPane().setSelectedFrame(m);
				getSimple().getDesktopPane().setSelectedFrame(y);
				try {
					c.setSelected(true);
					m.setSelected(true);
					y.setSelected(true);
				} catch (PropertyVetoException e) {
				}
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if (modelo.equals("CMYK")) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				Object[] array = getSimple().getFacade().decomporCMYK((MyJInternalFrame) getSimple().getDesktopPane()
						.getSelectedFrame());
				MyJInternalFrame c = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - C", new MyImage(
								(Image) array[0]));
				c.setFoiOperacao(true);
				getSimple().addPropertyChangeListener(c.getScrollPane());
				c.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().getDesktopPane().add(c);
				getSimple().getFacade().incrementaPosicao();
				MyJInternalFrame m = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - M", new MyImage(
								(Image) array[1]));
				m.setFoiOperacao(true);
				getSimple().addPropertyChangeListener(m.getScrollPane());
				m.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().getDesktopPane().add(m);
				getSimple().getFacade().incrementaPosicao();
				MyJInternalFrame y = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - Y", new MyImage(
								(Image) array[2]));
				y.setFoiOperacao(true);
				getSimple().addPropertyChangeListener(y.getScrollPane());
				y.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().getDesktopPane().add(y);
				getSimple().getFacade().incrementaPosicao();
				MyJInternalFrame k = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - K", new MyImage(
								(Image) array[3]));
				k.setFoiOperacao(true);
				getSimple().addPropertyChangeListener(k.getScrollPane());
				k.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().getDesktopPane().add(k);
				getSimple().getFacade().incrementaPosicao();
				getSimple().getDesktopPane().setSelectedFrame(c);
				getSimple().getDesktopPane().setSelectedFrame(m);
				getSimple().getDesktopPane().setSelectedFrame(y);
				getSimple().getDesktopPane().setSelectedFrame(k);
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
				Object[] array = getSimple().getFacade().decomporHSV((MyJInternalFrame) getSimple().getDesktopPane()
						.getSelectedFrame());
				MyJInternalFrame h = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - H", new MyImage(
								(Image) array[0]));
				h.setFoiOperacao(true);
				h.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().addPropertyChangeListener(h.getScrollPane());
				getSimple().getDesktopPane().add(h);
				getSimple().getFacade().incrementaPosicao();
				MyJInternalFrame s = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - S", new MyImage(
								(Image) array[1]));
				s.setFoiOperacao(true);
				getSimple().addPropertyChangeListener(s.getScrollPane());
				s.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().getDesktopPane().add(s);
				getSimple().getFacade().incrementaPosicao();
				MyJInternalFrame v = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - V", new MyImage(
								(Image) array[2]));
				v.setFoiOperacao(true);
				getSimple().addPropertyChangeListener(v.getScrollPane());
				v.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().getDesktopPane().add(v);
				getSimple().getFacade().incrementaPosicao();
				getSimple().getDesktopPane().setSelectedFrame(h);
				getSimple().getDesktopPane().setSelectedFrame(s);
				getSimple().getDesktopPane().setSelectedFrame(v);
				try {
					h.setSelected(true);
					s.setSelected(true);
					v.setSelected(true);
				} catch (PropertyVetoException e) {
				}

				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if (modelo.equals("YCrCb")) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				Object[] array = getSimple().getFacade().decomporYCrCb((MyJInternalFrame) getSimple().getDesktopPane()
						.getSelectedFrame());
				MyJInternalFrame y = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - Y", new MyImage(
								(Image) array[0]));
				y.setFoiOperacao(true);
				getSimple().addPropertyChangeListener(y.getScrollPane());
				y.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().getDesktopPane().add(y);
				getSimple().getFacade().incrementaPosicao();
				MyJInternalFrame cr = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - Cb", new MyImage(
								(Image) array[1]));
				cr.setFoiOperacao(true);
				getSimple().addPropertyChangeListener(cr.getScrollPane());
				cr.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().getDesktopPane().add(cr);
				getSimple().getFacade().incrementaPosicao();
				MyJInternalFrame cb = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - Cr", new MyImage(
								(Image) array[2]));
				cb.setFoiOperacao(true);
				getSimple().addPropertyChangeListener(cb.getScrollPane());
				cb.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().getDesktopPane().add(cb);
				getSimple().getFacade().incrementaPosicao();
				getSimple().getDesktopPane().setSelectedFrame(y);
				getSimple().getDesktopPane().setSelectedFrame(cr);
				getSimple().getDesktopPane().setSelectedFrame(cb);
				try {
					y.setSelected(true);
					cr.setSelected(true);
					cb.setSelected(true);
				} catch (PropertyVetoException e) {
				}
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if (modelo.equals("XYZ")) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				Object[] array = getSimple().getFacade().decomporXYZ((MyJInternalFrame) getSimple().getDesktopPane()
						.getSelectedFrame());
				MyJInternalFrame x = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - X", new MyImage(
								(Image) array[0]));
				x.setFoiOperacao(true);
				getSimple().addPropertyChangeListener(x.getScrollPane());
				x.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().getDesktopPane().add(x);
				getSimple().getFacade().incrementaPosicao();
				MyJInternalFrame y = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - Y", new MyImage(
								(Image) array[1]));
				y.setFoiOperacao(true);
				getSimple().addPropertyChangeListener(y.getScrollPane());
				y.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().getDesktopPane().add(y);
				getSimple().getFacade().incrementaPosicao();
				MyJInternalFrame z = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
						array[array.length - 1] + " - Z", new MyImage(
								(Image) array[2]));
				z.setFoiOperacao(true);
				getSimple().addPropertyChangeListener(z.getScrollPane());
				z.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
				getSimple().getDesktopPane().add(z);
				getSimple().getFacade().incrementaPosicao();
				getSimple().getDesktopPane().setSelectedFrame(x);
				getSimple().getDesktopPane().setSelectedFrame(y);
				getSimple().getDesktopPane().setSelectedFrame(z);
				try {
					x.setSelected(true);
					y.setSelected(true);
					z.setSelected(true);
				} catch (PropertyVetoException e) {
				}
			}
		}
		getSimple().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	/**
	 * Realiza o procedimento de combinação de uma imagem
	 */
	public void combinacao() { 
		MyJInternalFrame original = (MyJInternalFrame) getSimple().getDesktopPane()
		.getSelectedFrame();
		JanelaCombinar r = new JanelaCombinar(getSimple().getDesktopPane().getAllFrames(), original
				.getImage());
		if (r.ok()) {
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			MyJInternalFrame f = new MyJInternalFrame(getSimple(), getSimple().getFacade(), r
					.getNome(), new MyImage(r.getImageRecomposta()));
			f.setFoiOperacao(true);
			this.addPropertyChangeListener(f.getScrollPane());
			f.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
			getSimple().getDesktopPane().add(f);
			getSimple().getFacade().incrementaPosicao();
			try {
				f.setSelected(true);
			} catch (PropertyVetoException e) {
			}
		}
		getSimple().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

	}

	/**
	 * Faz com que uma imagem colorida seja transformada em uma imagem preta e branca.
	 */
	public void pretoBranco(){
		getSimple().setCursor(new Cursor(Cursor.WAIT_CURSOR));
		MyJInternalFrame f = (MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame();
		try {
			PlanarImage image = (PlanarImage)JAI.create("AWTImage",f.getImage());
			new Binarize(image, getSimple(), f.getName());
		} catch (Exception e){				
		}
		getSimple().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
	}

	/**
	 * Coloca a imagem selecionada em tons de cinza
	 */
	public void escalaCinza(){
		getSimple().setCursor(new Cursor(Cursor.WAIT_CURSOR));
		MyJInternalFrame f = (MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame();
		f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
		MyJInternalFrame n = new MyJInternalFrame(getSimple(), getSimple().getFacade(), f.getName()+ " em Escala de Cinza", new MyImage(getSimple().getFacade().escalaCinza(f.getImage())));
		n.setFoiOperacao(true);
		n.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
		getSimple().getDesktopPane().add(n, BorderLayout.CENTER);
		getSimple().getDesktopPane().setSelectedFrame(n);
		this.addPropertyChangeListener(n.getScrollPane());
		getSimple().getFacade().incrementaPosicao();
		try {
			n.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		getSimple().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	/**
	 * Realiza o procedimento de pseudocolorização da imagem selecionada.
	 */
	public void pseudoColorizacao(){

		JInternalFrame[] frames = getSimple().getDesktopPane().getAllFrames();
		if (frames.length > 0) {
			getSimple().setCursor(new Cursor(Cursor.WAIT_CURSOR));
			JanelaPseudoColorizacao jpc = new JanelaPseudoColorizacao(
					frames);
			if (jpc.getOk()) {
				Image pretoBranco = ((MyJInternalFrame) frames[jpc.getPretoBranco()]).getImage();
				Image coloridaBase = ((MyJInternalFrame) frames[jpc.getColorido()]).getImage();
				Image colorida;
				try {
					colorida = getSimple().getFacade().pseudocolorizacao(new ImageIcon(pretoBranco), new ImageIcon(coloridaBase));
					MyJInternalFrame pseudo = new MyJInternalFrame(getSimple(),getSimple().getFacade(), frames[jpc.getPretoBranco()].getName()
							+ " Pseudo Colorizada", new MyImage(colorida));
					pseudo.setFoiOperacao(true);
					this.addPropertyChangeListener(pseudo.getScrollPane());
					pseudo.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
					getSimple().getDesktopPane().add(pseudo);
					getSimple().getFacade().incrementaPosicao();
					try {
						pseudo.setSelected(true);
					} catch (PropertyVetoException e) {
					}
					getSimple().getDesktopPane().setSelectedFrame(pseudo);
				} catch (PseudoColorException e) {
					e.printStackTrace();
				}
			}
			getSimple().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else
			JOptionPane.showMessageDialog(null,
					"Selecione uma imagem para realizar a operação!!!",
					"ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Realiza o procedimento de requantização de uma imagem.
	 */
	public void requantizacao(){
		MyJInternalFrame f = (MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame();
		JanelaRequantizar r = new JanelaRequantizar(((MyJInternalFrame) f)
				.getImage());
		if (r.getIGS() && r.getOk()) {
			getSimple().setCursor(new Cursor(Cursor.WAIT_CURSOR));
			mostraTela(f, r);
		}
		if (r.getNumCores() != 0 && r.foiEscolhido() && r.getOk()) {
			getSimple().setCursor(new Cursor(Cursor.WAIT_CURSOR));
			mostraTela(f, r);
		}
		getSimple().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
		MyJInternalFrame ift = new MyJInternalFrame(getSimple(), getSimple().getFacade(), f.getName()+ " " + r.getNome(), new MyImage(r.getImagem()));
		ift.setFoiOperacao(true);
		getSimple().getDesktopPane().add(ift);
		getSimple().getDesktopPane().setSelectedFrame(ift);
		this.addPropertyChangeListener(ift.getScrollPane());
		try {
			ift.setSelected(true);
		} catch (PropertyVetoException e) {
		}
		ift.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
		getSimple().getFacade().incrementaPosicao();
	}

	/**
	 * Invoca a janela de ilusão de óptica do tipo 1.
	 */
	public void ilusao1(){
		JInternalFrame i = Ilusao1.create(getSimple(),getSimple().getFacade());
		getSimple().getDesktopPane().add(i);
		i.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
		addMouseListener((MouseListener) i);
		getSimple().getFacade().incrementaPosicao();
		getSimple().getDesktopPane().setSelectedFrame(i);
		ilusao1.setEnabled(false);
		try {
			i.setSelected(true);
		} catch (PropertyVetoException e) {}
	}

	/**
	 * Invoca a janela de ilusão de óptica do tipo 2.
	 */
	public void ilusao2(){
		JInternalFrame i = Ilusao2.create(getSimple(),getSimple().getFacade());
		getSimple().getDesktopPane().add(i);
		i.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
		addMouseListener((MouseListener) i);
		getSimple().getFacade().incrementaPosicao();
		getSimple().getDesktopPane().setSelectedFrame(i);
		ilusao2.setEnabled(false);
		try {
			i.setSelected(true);
		} catch (PropertyVetoException e) {}
	}

	/**
	 * Invoca a janela de ilusão de óptica do tipo 3.
	 */
	public void ilusao3(){
		JInternalFrame i = Ilusao3.create(getSimple(),getSimple().getFacade());
		getSimple().getDesktopPane().add(i);
		i.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
		addMouseListener((MouseListener) i);
		getSimple().getFacade().incrementaPosicao();
		getSimple().getDesktopPane().setSelectedFrame(i);
		ilusao3.setEnabled(false);
		try {
			i.setSelected(true);
		} catch (PropertyVetoException e) {}
	}

	/**
	 * Invoca a janela de ilusão de óptica do tipo 4.
	 */
	public void ilusao4(){
		JInternalFrame i = Ilusao4.create(getSimple(),getSimple().getFacade());
		getSimple().getDesktopPane().add(i);
		i.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
		addMouseListener((MouseListener) i);
		getSimple().getFacade().incrementaPosicao();
		getSimple().getDesktopPane().setSelectedFrame(i);
		ilusao4.setEnabled(false);
		try {
			i.setSelected(true);
		} catch (PropertyVetoException e) {}
	}

	/**
	 * Invoca a janela de ilusão de óptica do tipo 5.
	 */
	public void ilusao5(){
		JInternalFrame i = Ilusao5.create(getSimple(),getSimple().getFacade());
		getSimple().getDesktopPane().add(i);
		i.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
		addMouseListener((MouseListener) i);
		getSimple().getFacade().incrementaPosicao();
		getSimple().getDesktopPane().setSelectedFrame(i);
		ilusao5.setEnabled(false);
		try {
			i.setSelected(true);
		} catch (PropertyVetoException e) {}
	}

	/**
	 * Invoca a janela de ilusão de óptica do tipo 6.
	 */
	public void ilusao6(){
		JInternalFrame i = Ilusao6.create(getSimple(),getSimple().getFacade());
		getSimple().getDesktopPane().add(i);
		i.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
		addMouseListener((MouseListener) i);
		getSimple().getFacade().incrementaPosicao();
		getSimple().getDesktopPane().setSelectedFrame(i);
		ilusao6.setEnabled(false);
		try {
			i.setSelected(true);
		} catch (PropertyVetoException e) {}
	}

	/**
	 * Invoca a janela de ilusão de óptica do tipo 7.
	 */
	public void ilusao7(){
		JInternalFrame i = Ilusao7.create(getSimple(),getSimple().getFacade());
		getSimple().getDesktopPane().add(i);
		i.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
		addMouseListener((MouseListener) i);
		getSimple().getFacade().incrementaPosicao();
		getSimple().getDesktopPane().setSelectedFrame(i);
		ilusao7.setEnabled(false);
		try {
			i.setSelected(true);
		} catch (PropertyVetoException e) {}
	}

	/**
	 * Invoca a janela de ilusão de óptica do tipo 8.
	 */
	public void ilusao8(){
		JInternalFrame i = Ilusao8.create(getSimple(),getSimple().getFacade());
		getSimple().getDesktopPane().add(i);
		i.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
		addMouseListener((MouseListener) i);
		getSimple().getFacade().incrementaPosicao();
		getSimple().getDesktopPane().setSelectedFrame(i);
		ilusao8.setEnabled(false);
		try {
			i.setSelected(true);
		} catch (PropertyVetoException e) {}
	}

	/**
	 * Invoca a janela de ilusão de óptica do tipo 9.
	 */
	public void ilusao9(){
		JInternalFrame i = Ilusao9.create(getSimple(),getSimple().getFacade());
		getSimple().getDesktopPane().add(i);
		i.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
		addMouseListener((MouseListener) i);
		getSimple().getFacade().incrementaPosicao();
		getSimple().getDesktopPane().setSelectedFrame(i);
		ilusao9.setEnabled(false);
		try {
			i.setSelected(true);
		} catch (PropertyVetoException e) {}
	}

	/**
	 * Invoca a janela de ilusão de óptica do tipo 10.
	 */
	public void ilusao10(){
		JInternalFrame i = Ilusao10.create(getSimple(),getSimple().getFacade());
		getSimple().getDesktopPane().add(i);
		i.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
		addMouseListener((MouseListener) i);
		getSimple().getFacade().incrementaPosicao();
		getSimple().getDesktopPane().setSelectedFrame(i);
		ilusao10.setEnabled(false);
		try {
			i.setSelected(true);
		} catch (PropertyVetoException e) {}
	}

	/**
	 * Fecha a janela de uma ilusão que esteja aberta. 
	 * @param ilusao o número referente à ilusao que deve ser fechada. Valores de 1 a 10 devem
	 * ser fornecidos
	 */
	public void closeIlusao(int ilusao){

		switch (ilusao) {
		case 1:
			ilusao1.setEnabled(true);
			break;
		case 2:
			ilusao2.setEnabled(true);
			break;
		case 3:
			ilusao3.setEnabled(true);
			break;
		case 4:
			ilusao4.setEnabled(true);
			break;
		case 5:
			ilusao5.setEnabled(true);
			break;
		case 6:
			ilusao6.setEnabled(true);
			break;
		case 7:
			ilusao7.setEnabled(true);
			break;
		case 8:
			ilusao8.setEnabled(true);
			break;
		case 9:
			ilusao9.setEnabled(true);
			break;
		default:
			ilusao10.setEnabled(true);
		break;
		}
	}

	public JRadioButtonMenuItem getCoordenadaPixel() {
		return coordenadaPixel;
	}

	/**
	 * Indica a tonalidade e as coordenadas de um pixel em uma imagem.
	 */
	public void coordenadaPixel(){
		JRadioButtonMenuItem bt = (JRadioButtonMenuItem) coordenadaPixel;
		if (bt.isSelected()) {
			JInternalFrame[] frames = getSimple().getDesktopPane().getAllFrames();
			for (int k = 0; k < frames.length; k++) {
				((MyJInternalFrame) frames[k]).getScrollPane()
				.setExibirPixel(true);
			}
			boolean tmp = getSimple().isShowColors();
			getSimple().setShowColors(bt.isSelected());
			this.firePropertyChange("show-color", tmp, getSimple().isShowColors());
		} else {
			JInternalFrame[] frames = getSimple().getDesktopPane().getAllFrames();
			for (int k = 0; k < frames.length; k++) {
				if (getSimple().verificaInstancia((MyJInternalFrame) frames[k]))
					((MyJInternalFrame) frames[k]).getScrollPane().setExibirPixel(false);
			}
		}
	}


}
