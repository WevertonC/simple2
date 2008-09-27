package simple.ui.menus;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import simple.manipulacoes.imagem.DeteccaoBordas;
import simple.manipulacoes.util.DisplayDEM;
import simple.manipulacoes.util.MyBufferedImage;
import simple.manipulacoes.util.MyImage;
import simple.manipulacoes.util.MyJInternalFrame;
import simple.manipulacoes.util.RegionGrowingSegmentation;
import simple.modules.classificacao.paralelepipedo.GerenciadorClassesParalelepipedo;
import simple.modules.classificacao.paralelepipedo.Legenda;
import simple.modules.classificacao.paralelepipedo.Opcao1;
import simple.modules.classificacao.paralelepipedo.Opcao2;
import simple.modules.classificacao.paralelepipedo.Opcao3;
import simple.excecoes.ImageProcessingException;

import simple.ui.janelas.JanelaSegmentacaoAdaptativa;
import simple.ui.janelas.JanelaSegmentacaoGlobal;
import simple.ui.janelas.SImPLe;

/**
 * Responsável pela criação do Menu Classificação, dos seus respectivos submenus
 * e acesso às funcionalides providas por eles.
 *
 */
public class MenuClassificacao extends SimpleMenu {

	private static final long serialVersionUID = -5222981100700996029L;

	private JMenu segmentacao, classificadores;
	private JMenuItem global, adaptativa, regionGrowing, paralelepipedo;

	public MenuClassificacao(SImPLe simple) {
		super(MENU_CLASSIFICACAO, simple);
		setMnemonic(KeyEvent.VK_L);
		configuraMenu();
	}

	private void configuraMenu(){

		segmentacao = new JMenu("Segmentação");
		segmentacao.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		segmentacao.setMnemonic(KeyEvent.VK_S);

		global = new JMenuItem("Global");
		global.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		global.setMnemonic(KeyEvent.VK_G);
		global.addActionListener(getSimple());

		adaptativa = new JMenuItem("Adaptativa Básica");
		adaptativa.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		adaptativa.setMnemonic(KeyEvent.VK_A);
		adaptativa.addActionListener(getSimple());

		regionGrowing = new JMenuItem("Crescimento de Região");
		regionGrowing.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		regionGrowing.setMnemonic(KeyEvent.VK_R);
		regionGrowing.addActionListener(getSimple());

		segmentacao.add(global);
		segmentacao.add(adaptativa);
		segmentacao.add(regionGrowing);

		classificadores = new JMenu("Classificadores");
		classificadores.setIcon(new ImageIcon("Resource/Icones/transparente.gif"));
		classificadores.setMnemonic(KeyEvent.VK_C);

		paralelepipedo = new JMenuItem("Paralelepipedo");
		paralelepipedo.setMnemonic(KeyEvent.VK_P);
		paralelepipedo.addActionListener(getSimple());

		classificadores.add(paralelepipedo);

		add(segmentacao);
		add(classificadores);
	}

	/**
	 * Realização da segmentação global
	 */
	public void global(){

		JanelaSegmentacaoGlobal j = new JanelaSegmentacaoGlobal();
		int limiar = j.getLimiar();

		MyImage myImage;
		try {
			myImage = new MyImage(getSimple().getFacade().segmentacaoGlobal(getSimple().getImagefromFrame(), limiar));
			BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
			getSimple().buildFrame(img, "  Segmentação Global");	
		} catch (ImageProcessingException e) {
		}

	}

	/**
	 * Realização da segmentação adaptativa
	 */
	public void adaptativa(){
		
		MyJInternalFrame f = (MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame();		
		JanelaSegmentacaoAdaptativa j = new JanelaSegmentacaoAdaptativa();
		
		int dimensao = j.getDimensao();
		
		if (dimensao >= 0) {
			try {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				
				MyJInternalFrame segmentado = new MyJInternalFrame(getSimple(),
						getSimple().getFacade(), "Segmentação Adaptativa", new MyImage(
								getSimple().getFacade().segmentacaoAdaptativa(getSimple().getImagefromFrame(),
										dimensao)));
				
				segmentado.setFoiOperacao(true);
				getSimple().addPropertyChangeListener(segmentado.getScrollPane());
				getSimple().getDesktopPane().add(segmentado, BorderLayout.CENTER);
				getSimple().getDesktopPane().setSelectedFrame(segmentado);
				segmentado.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade()
						.getPosicao());
				segmentado.setSelected(true);
				getSimple().getFacade().incrementaPosicao();
				getSimple().addPropertyChangeListener(segmentado.getScrollPane());
				getSimple().changeFire();
			} catch (ImageProcessingException e) {
			} catch (PropertyVetoException e) {
			}
		}
		getSimple().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	public void habilitaBotoes(boolean habilita) {
		setEnabled(habilita);
	}
	
	/**
	 * Realização da classificação do tipo paralelepípedo
	 */
	public void paralelepipedo(){
		
		MyJInternalFrame f = (MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame();
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
	
	private void criaImagemClassificada(Opcao2 opcao2, String nome){
		try {	
			MyJInternalFrame freichen20061 = new MyJInternalFrame(getSimple(), getSimple().getFacade(),"Classificação Paralelepípedo - " + nome,new MyImage(opcao2.getResultado().getBufferedImage()));
			freichen20061.setFoiOperacao(true);
			getSimple().addPropertyChangeListener(freichen20061.getScrollPane());
			getSimple().getDesktopPane().add(freichen20061,BorderLayout.CENTER);
			getSimple().getDesktopPane().setSelectedFrame(freichen20061);
			getSimple().addPropertyChangeListener(freichen20061.getScrollPane());
			freichen20061.setSelected(true);	
			freichen20061.setLocation(getSimple().getFacade().getPosicao(),getSimple().getFacade().getPosicao());
			getSimple().getFacade().incrementaPosicao();
			getSimple().addPropertyChangeListener(freichen20061.getScrollPane());	
			getSimple().changeFire();
			Legenda legenda = new Legenda(GerenciadorClassesParalelepipedo.getInstance().getClasses());
			GerenciadorClassesParalelepipedo.getInstance().zeraClasses();
			legenda.setLocation(getSimple().getFacade().getPosicao() + 5,getSimple().getFacade().getPosicao()+ 5);
			legenda.setVisible(true);
		} catch (Exception e){
			
		}			
	}
	
	private void criaImagemClassificada(Opcao3 opcao2, String nome){
		try {	
			MyJInternalFrame freichen20061 = new MyJInternalFrame(getSimple(), getSimple().getFacade(),"Classificação Paralelepípedo - " + nome,new MyImage(opcao2.getResultado().getBufferedImage()));
			freichen20061.setFoiOperacao(true);
			getSimple().addPropertyChangeListener(freichen20061.getScrollPane());
			getSimple().getDesktopPane().add(freichen20061,BorderLayout.CENTER);
			getSimple().getDesktopPane().setSelectedFrame(freichen20061);
			getSimple().addPropertyChangeListener(freichen20061.getScrollPane());
			freichen20061.setSelected(true);	
			freichen20061.setLocation(getSimple().getFacade().getPosicao(),getSimple().getFacade().getPosicao());
			getSimple().getFacade().incrementaPosicao();
			getSimple().addPropertyChangeListener(freichen20061.getScrollPane());	
			getSimple().changeFire();
			Legenda legenda = new Legenda(GerenciadorClassesParalelepipedo.getInstance().getClasses());
			GerenciadorClassesParalelepipedo.getInstance().zeraClasses();
			legenda.setLocation(getSimple().getFacade().getPosicao() + 5,getSimple().getFacade().getPosicao()+ 5);
			legenda.setVisible(true);
		} catch (Exception e){
			
		}			
	}
	
	/**
	 * Efetua a operação de crescimento de regiões da imagem.
	 */
	public void crescimentoRegiao(){
		getSimple().setCursor(new Cursor(Cursor.WAIT_CURSOR));
		MyJInternalFrame f = (MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame();
		final String nome = f.getName();
		try {
			f.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
			PlanarImage image = (PlanarImage)JAI.create("AWTImage",f.getImage());
			RegionGrowingSegmentation task = new RegionGrowingSegmentation(image,false,true);
			task.run();
			DisplayDEM display = new DisplayDEM(task.getOutput());
			image = display.getSurrogateImage();
			MyJInternalFrame segmentacaoRegionGrowing = new MyJInternalFrame(getSimple(), getSimple().getFacade(),nome + " - Segmentaçao - crescimento de região",new MyImage(image.getAsBufferedImage()));
			segmentacaoRegionGrowing.setFoiOperacao(true);
			getSimple().addPropertyChangeListener(segmentacaoRegionGrowing.getScrollPane());
			getSimple().getDesktopPane().add(segmentacaoRegionGrowing,BorderLayout.CENTER);
			getSimple().getDesktopPane().setSelectedFrame(segmentacaoRegionGrowing);
			getSimple().addPropertyChangeListener(segmentacaoRegionGrowing.getScrollPane());
			segmentacaoRegionGrowing.setSelected(true);	
			segmentacaoRegionGrowing.setLocation(getSimple().getFacade().getPosicao(),getSimple().getFacade().getPosicao());
			getSimple().getFacade().incrementaPosicao();
			this.addPropertyChangeListener(segmentacaoRegionGrowing.getScrollPane());	
			this.firePropertyChange("show-color", false, getSimple().isShowColors());
			f.getScrollPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		} catch (Exception e){				
		}
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));			
	}




}
