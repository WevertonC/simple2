package simple.ui.menus;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import simple.excecoes.FormatoInvalidoException;
import simple.excecoes.ImagemNaoExisteException;
import simple.excecoes.ImagemNaoPodeSalvarException;
import simple.excecoes.ImagemNaoSelecionadaException;
import simple.excecoes.NomeInvalidoException;
import simple.excecoes.RedimensionarException;
import simple.manipulacoes.manipularArquivo.AbrirImagem;
import simple.manipulacoes.manipularArquivo.BMPFilter;
import simple.manipulacoes.manipularArquivo.GIFFilter;
import simple.manipulacoes.manipularArquivo.ImageFileView;
import simple.manipulacoes.manipularArquivo.JPGFilter;
import simple.manipulacoes.manipularArquivo.PNGFilter;
import simple.manipulacoes.manipularArquivo.SalvarImagem;
import simple.manipulacoes.util.MyBufferedImage;
import simple.manipulacoes.util.MyImage;
import simple.manipulacoes.util.MyJInternalFrame;
import simple.ui.janelas.JanelaErro;
import simple.ui.janelas.JanelaNovo;
import simple.ui.janelas.JanelaSair;
import simple.ui.janelas.SImPLe;

/**
 * Classe que modela um menu da barra de menus e as respectivas
 * ações a serem tomadas por cada um dos itens do menu.
 * 
 * @version 2.0
 * @author Elloá B. Guedes - elloaguedes@gmail.com
 * @author Odilon F. de Lima Jr. - odilonflj@gmail.com
 *
 */
public class MenuArquivo extends SimpleMenu {

	private static final long serialVersionUID = 1L;

	

	private JMenuItem abrirNovo, abrir, fechar, salvar, salvarComo, exportar, scanner, sair, camera;
	private JMenu capturar;
	private JanelaErro je;

	/**
	 * Instancia um novo Menu Arquivo e também cria os submenus.
	 * 
	 * @param simple - uma referência para a instância única do SImPLe, para 
	 * execução das respectivas modificações na interface.
	 */
	public MenuArquivo(SImPLe simple){
		super(SimpleMenu.MENU_ARQUIVO,simple);
		super.setMnemonic(KeyEvent.VK_A);
		configureMenu();
	}

	private void configureMenu(){

		abrirNovo = configureMenuItem("Novo", KeyEvent.ALT_MASK, KeyEvent.VK_O, ActionEvent.CTRL_MASK, KeyEvent.VK_N,
		"Resource/Icones/new.gif");

		abrir = configureMenuItem("Abrir", KeyEvent.ALT_MASK,KeyEvent.VK_A,ActionEvent.CTRL_MASK,KeyEvent.VK_A,"Resource/Icones/open.gif");

		fechar = configureMenuItem("Fechar", KeyEvent.ALT_MASK, KeyEvent.VK_F, ActionEvent.CTRL_MASK, KeyEvent.VK_F, "Resource/Icones/cancel.gif");
		fechar.setEnabled(false);

		salvar = configureMenuItem("Salvar", KeyEvent.ALT_MASK, KeyEvent.VK_S,ActionEvent.CTRL_MASK, KeyEvent.VK_S, "Resource/Icones/save16.gif");
		salvar.setEnabled(false);

		salvarComo = configureMenuItem("Salvar Como",KeyEvent.ALT_MASK, NO_VALUE,NO_VALUE, KeyEvent.VK_C,"Resource/Icones/save.gif");
		salvar.setEnabled(false);

		exportar = configureMenuItem("Exportar", KeyEvent.VK_P, NO_VALUE, NO_VALUE, KeyEvent.VK_C, "Resource/Icones/exportar.gif");
		exportar.setActionCommand("Comprimir");

		camera = configureMenuItem("Câmera", KeyEvent.ALT_MASK, NO_VALUE, NO_VALUE, KeyEvent.VK_C, "Resource/Icones/webcam.gif");
		camera.setEnabled(false);		

		scanner = configureMenuItem("Scanner", KeyEvent.ALT_MASK, NO_VALUE, NO_VALUE, KeyEvent.VK_S, "Resource/Icones/scanner.gif");
		scanner.setEnabled(false);


		capturar = new JMenu("Capturar Imagem");
		capturar.setMnemonic(KeyEvent.VK_P);
		capturar.setIcon(new ImageIcon("Resource/Icones/transparente.gif"));
		capturar.setEnabled(false);
		capturar.add(camera);
		capturar.add(scanner);

		sair = configureMenuItem("Sair", KeyEvent.VK_R, KeyEvent.VK_F4, ActionEvent.ALT_MASK, 0, "Resource/Icones/sair.gif");

		add(abrirNovo);
		add(abrir);
		add(fechar);
		addSeparator();
		add(salvar);
		add(salvarComo);
		add(exportar);
		addSeparator();
		add(capturar);
		addSeparator();
		add(sair);
	}

	/**
	 * Permite que uma nova janela seja aberta na interface e nesta sejam fornecidas as 
	 * dimensões para a criação de um novo arquivo.
	 */
	public void novo(){

		JanelaNovo j = new JanelaNovo(super.getSimple());
		int altura = j.getAltura();
		int largura = j.getLargura();
		if (altura > 0 && largura > 0 && j.foiOk()) {
			Image image = null;
			try {
				image = super.getSimple().getFacade().emPixelsBICUBIC(new ImageIcon("Resource/Logos/branco.jpg"), largura, altura);
				MyJInternalFrame i = new MyJInternalFrame(super.getSimple(), super.getSimple().getFacade(),	"Novo", new MyImage(image));
				super.getSimple().getDesktopPane().add(i, BorderLayout.CENTER);
				super.getSimple().getDesktopPane().setSelectedFrame(i);
				i.setSelected(true);
				i.setLocation(super.getSimple().getFacade().getPosicao(), super.getSimple().getFacade().getPosicao());
				this.addPropertyChangeListener(i.getScrollPane());
				this.firePropertyChange("show-color", false, true);
				super.getSimple().habilitaBotoes(true);
				super.getSimple().getFacade().incrementaPosicao();
			} catch (RedimensionarException e) {
			} catch (PropertyVetoException e) {
			}
		}

		j.dispose();
	}

	/**
	 * Permite que um arquivo existente em disco seja aberto.
	 */
	public void abrir(){

		Object[] abrir = null;
		try {
			abrir = super.getSimple().getFacade().abrir();
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
				i = new MyJInternalFrame(super.getSimple(), super.getSimple().getFacade(), (String) abrir[1], new MyImage(img));
				i.setCaminhoImagem(AbrirImagem.caminho);
			}
		} catch (Exception e) {
		}
		if (i != null) {
			boolean existe = false;
			for (int j = 0; j < super.getSimple().getDesktopPane().getAllFrames().length; j++) {
				if (super.getSimple().verificaInstancia(super.getSimple().getDesktopPane().getAllFrames()[j])) {
					if (((MyJInternalFrame) super.getSimple().getDesktopPane().getAllFrames()[j])
							.getCaminhoImagem()
							.equals(i.getCaminhoImagem())) {
						existe = true;
						try {
							super.getSimple().getDesktopPane().getAllFrames()[j].setSelected(true);
						} catch (PropertyVetoException e) {
						}
					}
				}
			}
			if (!existe && i.getAbriu()) {
				super.getSimple().getDesktopPane().add(i, BorderLayout.CENTER);
				super.getSimple().getDesktopPane().setSelectedFrame(i);
				try {
					i.setSelected(true);
				} catch (PropertyVetoException e) {
				}
				this.addPropertyChangeListener(i.getScrollPane());
				this.firePropertyChange("show-color", false, true);
				i.setLocation(super.getSimple().getFacade().getPosicao(), super.getSimple().getFacade().getPosicao());
				if (i.getAbriu()) {
					super.getSimple().habilitaBotoes(true);
					i.setVisible(true);
					super.getSimple().getFacade().incrementaPosicao();
				}
			}
		}
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	/**
	 * Permite que o conteúdo em uma das janelas seja salvo.
	 */
	public void salvar(){
		try {
			if (super.getSimple().verificaInstancia(super.getSimple().getDesktopPane().getSelectedFrame())) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				MyJInternalFrame i = (MyJInternalFrame) super.getSimple().getDesktopPane().getSelectedFrame();
				if (i != null) {
					try {
						if (i.getFoiModificado() || super.getSimple().getFacade().modificouImagem()) {
							i.getScrollPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
							i.setSalvou(true);
							super.getSimple().getFacade().salvar(i);
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
		} catch (HeadlessException e) {
			je = new JanelaErro(e.getMessage());
		}catch (ClassCastException e0){
			je = new JanelaErro(JanelaErro.JANELA_INVALIDA);
		} finally {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}

	/**
	 * Permite salvar o conteúdo de uma janela como um novo arquivo.
	 */
	public void salvarComo(){
		try {
		MyJInternalFrame i = (MyJInternalFrame) super.getSimple().getDesktopPane().getSelectedFrame();
		if (i != null) {

				i.setSalvou(true);
				SalvarImagem.caminho = i.getName();
				SalvarImagem.caminhoImagem = AbrirImagem.caminho;
				int valor = super.getSimple().getFacade().salvarComo(i.getImage());
				if (valor != 0) {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					i.getScrollPane().setCursor(
							new Cursor(Cursor.WAIT_CURSOR));
					MyJInternalFrame j = new MyJInternalFrame(super.getSimple(), super.getSimple().getFacade(),
							SalvarImagem.nome, new MyImage(
									SalvarImagem.caminhoImagem));
					j.setCaminhoImagem(SalvarImagem.caminhoImagem);
					super.getSimple().getDesktopPane().add(j);
					super.getSimple().getDesktopPane().setSelectedFrame(j);
					j.setLocation(super.getSimple().getFacade().getPosicao(), super.getSimple().getFacade().getPosicao());
					super.getSimple().getFacade().incrementaPosicao();
					j.setSelected(true);
					this.addPropertyChangeListener(j.getScrollPane());
					this
					.firePropertyChange("show-color", false,
							true);
					i.getScrollPane().setCursor(
							new Cursor(Cursor.CROSSHAIR_CURSOR));
				}
			} } catch (ImagemNaoSelecionadaException e) {
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
			}catch (ClassCastException e0){
				je = new JanelaErro(JanelaErro.JANELA_INVALIDA);
			} finally {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
	}

	/**
	 * Efetua a saída do Simple. Todos os arquivos que não tiverem sido salvos, serão consultados.
	 */
	public void sair(){
		JInternalFrame[] frames = super.getSimple().getDesktopPane().getAllFrames();
		if (frames != null) {
			for (int i = 0; i < frames.length; i++) {
				try{
					if (super.getSimple().verificaInstancia(frames[i]))
						((MyJInternalFrame) frames[i]).fecharAoSair();
				} catch (ClassCastException e0) {
					frames[i].dispose();
				}
			}
		}
		JanelaSair js = new JanelaSair("Deseja realmente sair?");
		String t = js.getAcaoSelecionada();
		if (t.equals("Sim"))
			System.exit(0);
		else if (t.equals("Nao"))
			super.getSimple().setDefaultCloseOperation(super.getSimple().DO_NOTHING_ON_CLOSE);
	}

	/**
	 * Permite que um arquivo aberto seja comprimido antes de ser salvo.
	 */
	public void comprimir(){
		try {
			MyJInternalFrame f = (MyJInternalFrame) super.getSimple().getDesktopPane().getSelectedFrame();
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
							super.getSimple().getFacade().comprimirImagem(formato, caminho,MyBufferedImage.makeBufferedImage(f.getImage()));
						} catch (FormatoInvalidoException e) {
						}
					} catch (InterruptedException e) {
						JOptionPane.showMessageDialog(this, e.getMessage(),
								"ERRO!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} catch (HeadlessException e) {
			je = new JanelaErro(e.getMessage());
		} catch (ClassCastException e0){
			je = new JanelaErro(JanelaErro.JANELA_INVALIDA);
		} finally {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}

	/**
	 * Fecha uma janela aberta.
	 */
	public void fechar(){
		
		try {
			MyJInternalFrame i = (MyJInternalFrame) super.getSimple().getDesktopPane().getSelectedFrame();
			if (i != null) {
				i.fechar();
			}
		} catch (ClassCastException e) {
			JInternalFrame i0 = super.getSimple().getDesktopPane().getSelectedFrame();
			i0.dispose();
		}
	}

	/**
	 * Habilita/Desabilita botões
	 * @param b true para habilitar, falso para desabilitar.
	 */
	public void habilitaBotoes(boolean b){
		fechar.setEnabled(b);
		salvarComo.setEnabled(b);
		exportar.setEnabled(b);
	}

	public JMenuItem getSalvar() {
		return salvar;
	}


	


}
