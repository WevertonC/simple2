package simple.ui.menus;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import simple.manipulacoes.imagem.EqualizarImagem;
import simple.manipulacoes.util.MyBufferedImage;
import simple.manipulacoes.util.MyImage;
import simple.manipulacoes.util.MyJInternalFrame;
import simple.ui.janelas.JanelaAritmetica;
import simple.ui.janelas.JanelaAritmeticaGanhoOffset;
import simple.ui.janelas.JanelaOperacoesLogicas;
import simple.ui.janelas.SImPLe;
import simple.excecoes.OperacaoAritmeticaException;
import simple.excecoes.OperacaoLogicaException;

/**
 * 
 * Constrói o menu que realiza todas as operações pontuais em uma imagem. Apresenta
 * métodos que contém todas as funcionalidades providas por estes menus.
 *
 */
public class MenuOperacoesPontuais extends SimpleMenu{
	
	private static final long serialVersionUID = -1181861870195340849L;
	
	private JMenuItem opLogicas,opAritmetica, equalizar,ganhoOffset, dilatacao4, dilatacao8, erosao4, erosao8, equalizacao,expansaoCompressao ;
	
	private JMenu histogramica, morfologica, dilatacao, erosao;

	public MenuOperacoesPontuais(SImPLe s){
		super(OP_PONTUAIS,s);
		this.setMnemonic(KeyEvent.VK_P);
		configureMenu();
	}
	
	private void configureMenu(){
		
		opLogicas = configureMenuItem("Lógicas", NO_VALUE, KeyEvent.VK_L, ActionEvent.CTRL_MASK, KeyEvent.VK_L, "Resource/Icones/oplogica.GIF"); 
		
		opAritmetica = configureMenuItem("Aritméticas", NO_VALUE, KeyEvent.VK_B,ActionEvent.CTRL_MASK, KeyEvent.VK_B, "Resource/Icones/oparitmetica.GIF");
			
		equalizar = configureMenuItem("Equalizar",  NO_VALUE, KeyEvent.VK_E, ActionEvent.CTRL_MASK, KeyEvent.VK_E, "Resource/Icones/transparente.GIF");

		ganhoOffset = configureMenuItem("Ganho/Offset",  NO_VALUE, KeyEvent.VK_P,ActionEvent.CTRL_MASK, KeyEvent.VK_P, "Resource/Icones/oparitmeticapessoal.GIF");
		

		histogramica = new JMenu("Histogrâmicas");
		histogramica.setIcon(new ImageIcon("Resource/Icones/transparente.GIF"));
		histogramica.setMnemonic(KeyEvent.VK_P);
		
		expansaoCompressao = new JMenuItem("Expansão/Compressão");
		expansaoCompressao.setMnemonic(KeyEvent.VK_E);
		expansaoCompressao.setEnabled(false);
		
		equalizacao = new JMenuItem("Equalização");
		equalizacao.setMnemonic(KeyEvent.VK_Q);
		equalizacao.setEnabled(false);
		
		histogramica.add(expansaoCompressao);
		histogramica.add(equalizacao);
		
		morfologica = new JMenu("Morfológicas");
		morfologica.setIcon(new ImageIcon("Resource/Icones/transparente.GIF"));
		morfologica.setMnemonic(KeyEvent.VK_M);
		
		dilatacao = new JMenu("Dilatação");
		dilatacao.setMnemonic(KeyEvent.VK_E);
		
		dilatacao4 = configureMenuItem("Dilatação 4", NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE, "Resource/Icones/dilatacao.jpg");
		dilatacao8 = configureMenuItem("Dilatação 8", NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE, "Resource/Icones/dilatacao.jpg");

		dilatacao.add(dilatacao4);
		dilatacao.add(dilatacao8);
						
		erosao = new JMenu("Erosão");
		erosao.setMnemonic(KeyEvent.VK_Q);
		
		erosao4 = configureMenuItem("Erosão 4", NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE, "Resource/Icones/erosao.GIF"); 
		erosao8 = configureMenuItem("Erosão 8", NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE, "Resource/Icones/erosao.GIF");
			 
		erosao.add(erosao4);
		erosao.add(erosao8);
		
		morfologica.add(dilatacao);
		morfologica.add(erosao);
		
		this.add(opLogicas);
		this.add(ganhoOffset);
		this.add(opAritmetica);
		this.add(equalizar);
		this.add(histogramica);
		this.add(morfologica);
	}
	
	public void operacoesLogicas(){
		
		JanelaOperacoesLogicas j = new JanelaOperacoesLogicas(getSimple().getDesktopPane().getAllFrames());
		if (j.getSelecionados() != null) {
			getSimple().setCursor(new Cursor(Cursor.WAIT_CURSOR));
			ArrayList<ImageIcon> desejada = new ArrayList<ImageIcon>();
			ArrayList<String> selecionada = j.getSelecionados();
			String nomes = getSimple().adicionaNomes(desejada, selecionada);
			String operacao = j.getOperacao();
			if (operacao.equals("NOT")) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Image i = getSimple().getFacade().not(desejada.get(0));
					MyJInternalFrame c = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
							nomes + " - NOT", new MyImage(i));
					c.setFoiOperacao(true);
					c.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
					getSimple().getDesktopPane().add(c);
					c.setSelected(true);
					this.addPropertyChangeListener(c.getScrollPane());
					this
							.firePropertyChange("show-color", false,
									getSimple().isShowColors());
					getSimple().getFacade().incrementaPosicao();
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} catch (OperacaoLogicaException e) {
				} catch (PropertyVetoException e) {
				}
			} else if (operacao.equals("AND")) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Image i = getSimple().getFacade().and(desejada);
					MyJInternalFrame c = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
							nomes + " - AND", new MyImage(i));
					c.setFoiOperacao(true);
					c.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
					getSimple().getDesktopPane().add(c);
					c.setSelected(true);
					this.addPropertyChangeListener(c.getScrollPane());
					getSimple().getFacade().incrementaPosicao();
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} catch (OperacaoLogicaException e) {
				} catch (PropertyVetoException e) {
				}
			} else if (operacao.equals("XOR")) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Image i = getSimple().getFacade().xor(desejada);
					MyJInternalFrame c = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
							nomes + " - XOR", new MyImage(i));
					c.setFoiOperacao(true);
					c.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
					getSimple().getDesktopPane().add(c);
					c.setSelected(true);
					this.addPropertyChangeListener(c.getScrollPane());
					getSimple().getFacade().incrementaPosicao();
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} catch (OperacaoLogicaException e) {
				} catch (PropertyVetoException e) {
				}
			} else if (operacao.equals("NAND")) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Image i = getSimple().getFacade().nand(desejada);
					MyJInternalFrame c = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
							nomes + " - NAND", new MyImage(i));
					c.setFoiOperacao(true);
					c.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
					getSimple().getDesktopPane().add(c);
					c.setSelected(true);
					this.addPropertyChangeListener(c.getScrollPane());
					getSimple().getFacade().incrementaPosicao();
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} catch (OperacaoLogicaException e) {
				} catch (PropertyVetoException e) {
				}
			} else if (operacao.equals("NOR")) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Image i = getSimple().getFacade().nor(desejada);
					MyJInternalFrame c = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
							nomes + " - NOR", new MyImage(i));
					c.setFoiOperacao(true);
					c.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
					getSimple().getDesktopPane().add(c);
					c.setSelected(true);
					this.addPropertyChangeListener(c.getScrollPane());
					getSimple().getFacade().incrementaPosicao();
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} catch (OperacaoLogicaException e) {
				} catch (PropertyVetoException e) {
				}
			} else if (operacao.equals("OR")) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Image i = getSimple().getFacade().or(desejada);
					MyJInternalFrame c = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
							nomes + " - OR", new MyImage(i));
					c.setFoiOperacao(true);
					c.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
					getSimple().getDesktopPane().add(c);
					c.setSelected(true);
					this.addPropertyChangeListener(c.getScrollPane());
					getSimple().getFacade().incrementaPosicao();

				} catch (OperacaoLogicaException e) {
				} catch (PropertyVetoException e) {
				}
			}
		}
		getSimple().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void equalizar(){
		BufferedImage img = EqualizarImagem.getImagemEqualizado(getSimple().getImagefromFrame());
		getSimple().buildFrame(img, "Equalizada");	
	}
	
	public void ganhoOffset(){
		MyJInternalFrame f = (MyJInternalFrame) getSimple().getDesktopPane().getSelectedFrame();
		JanelaAritmeticaGanhoOffset j = new JanelaAritmeticaGanhoOffset();
		if (j.getGain() != Double.MAX_VALUE
				&& j.getOffset() != Double.MAX_VALUE && j.foiOk()) {
			Image i = getSimple().getFacade().aritmeticaPessoal(f.getImage(), j.getGain(), j
					.getOffset());
			MyJInternalFrame c = new MyJInternalFrame(getSimple(), getSimple().getFacade(), f
					.getName()
					+ " - Aritmética Pessoal", new MyImage(i));
			c.setFoiOperacao(true);
			getSimple().getDesktopPane().add(c, BorderLayout.CENTER);
			getSimple().getDesktopPane().setSelectedFrame(c);
			getSimple().addPropertyChangeListener(c.getScrollPane());
			try {
				c.setSelected(true);
			} catch (PropertyVetoException e) {
			}
			c.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
			getSimple().getFacade().incrementaPosicao();
		}

	}
	
	public void operacoesAritmeticas(){
		JanelaAritmetica j = new JanelaAritmetica(getSimple().getDesktopPane()
				.getAllFrames());
		if (j.getSelecionados() != null) {
			getSimple().setCursor(new Cursor(Cursor.WAIT_CURSOR));
			ArrayList<ImageIcon> desejada = new ArrayList<ImageIcon>();
			ArrayList<String> selecionada = j.getSelecionados();
			String nomes = getSimple().adicionaNomes(desejada, selecionada);
			if (j.getOperacao().equals("Adicao")) {
				try {
					getSimple().setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Image i = getSimple().getFacade().adicao(desejada);
					MyJInternalFrame c = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
							nomes + " - Adicao", new MyImage(i));
					c.setFoiOperacao(true);
					c.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
					getSimple().getDesktopPane().add(c);
					c.setSelected(true);
					this.addPropertyChangeListener(c.getScrollPane());
					getSimple().getFacade().incrementaPosicao();
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} catch (OperacaoAritmeticaException e) {
				} catch (PropertyVetoException e) {
				}
			} else if (j.getOperacao().equals("Subtracao")) {
				try {

					Image i = getSimple().getFacade().subtracao(desejada.get(0), desejada
							.get(1));
					MyJInternalFrame c = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
							nomes + " - Subtracao", new MyImage(i));
					c.setFoiOperacao(true);
					c.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
					getSimple().getDesktopPane().add(c);
					c.setSelected(true);
					this.addPropertyChangeListener(c.getScrollPane());
					getSimple().getFacade().incrementaPosicao();

				} catch (OperacaoAritmeticaException e) {
				} catch (PropertyVetoException e) {
				}
			} else if (j.getOperacao().equals("Multiplicacao")) {
				try {
					getSimple().setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Image i = getSimple().getFacade().multiplica(desejada);
					MyJInternalFrame c = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
							nomes + " - Multiplicacao", new MyImage(i));
					c.setFoiOperacao(true);
					c.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
					getSimple().getDesktopPane().add(c);
					c.setSelected(true);
					this.addPropertyChangeListener(c.getScrollPane());
					getSimple().getFacade().incrementaPosicao();

				} catch (OperacaoAritmeticaException e) {
				} catch (PropertyVetoException e) {
				}

			} else if (j.getOperacao().equals("Divisao")) {
				try {
					getSimple().setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Image i = getSimple().getFacade().divide(desejada.get(0), desejada
							.get(1));
					MyJInternalFrame c = new MyJInternalFrame(getSimple(), getSimple().getFacade(),
							nomes + " - Divisao", new MyImage(i));
					c.setFoiOperacao(true);
					c.setLocation(getSimple().getFacade().getPosicao(), getSimple().getFacade().getPosicao());
					getSimple().getDesktopPane().add(c);
					c.setSelected(true);
					this.addPropertyChangeListener(c.getScrollPane());
					getSimple().getFacade().incrementaPosicao();

				} catch (OperacaoAritmeticaException e) {
				} catch (PropertyVetoException e) {
				}
			}
		}
		getSimple().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void dilatacao4(){
		MyImage myImage = new MyImage(getSimple().getFacade().dilatacao4(getSimple().getImagefromFrame()));
		BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
		getSimple().buildFrame(img, "Dilatação 4");	
		
	}
	
	public void dilatacao8(){
		MyImage myImage = new MyImage(getSimple().getFacade().dilatacao8(getSimple().getImagefromFrame()));
		BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
		getSimple().buildFrame(img, "Dilatação 8");	
		
	}
	
	public void erosao4(){
		MyImage myImage = new MyImage(getSimple().getFacade().erosao4(getSimple().getImagefromFrame()));
		BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
		getSimple().buildFrame(img, "Erosã0 4");	
	}
	
	
	public void erosao8(){
		MyImage myImage = new MyImage(getSimple().getFacade().erosao8(getSimple().getImagefromFrame()));
		BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
		getSimple().buildFrame(img, "Erosã0 8");
	}
	
	
	

}
