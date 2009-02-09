package simple.ui.menus;

import java.awt.Dimension;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import simple.ui.janelas.SImPLe;

/**
 * Classe responsável pela criação e configuração de um menu de acesso direto para o SImPLe.
 * 
 * @author elloa
 *
 */
public class SimpleToolBar extends JToolBar {

	private Dimension screenSize;
	private JButton novo, abrir, salvarComo, recortar, copiar,colar,desfazer, refazer, zoomMais, zoomMenos,
	rotacionar, redimensionar;
	private SImPLe s;
	private JButton buttonHColorido;
	private JButton buttonHCanal;
	private JButton buttonPLinha;
	private JButton buttonPColuna;
	private JButton buttonExportar;
	private JButton buttonOpLogica;
	private JButton buttonOpAritB;
	private JButton buttonOpAritP;
	private JButton buttonEscCinza;
	private AbstractButton buttonDecompor;
	private JButton buttonRecompor;
	private JButton buttonPseudo;

	/**
	 * Construtor da classe SimpleToolBar
	 * 
	 * @param screenSize - as dimensões da tela que o menu será inserido
	 * @param s - uma instância do SImPle
	 */
	public SimpleToolBar(Dimension screenSize, SImPLe s){
		super.setFloatable(true);
		super.setRollover(true);
		super.setBounds(0, 0, screenSize.width, 26);
		super.setBorder(BorderFactory.createRaisedBevelBorder());
		this.screenSize = screenSize;
		this.s = s;
		configuraBotoes();
	}


	private void configuraBotoes(){

		// Novo
		novo = new JButton();
		novo.setIcon(new ImageIcon("Resource/Icones/new.gif"));
		novo.addActionListener(s);
		novo.setActionCommand("Novo");
		novo.setToolTipText("Novo");
		novo.setEnabled(true);
		this.add(novo);

		// Abrir
		abrir = new JButton();
		abrir.setIcon(new ImageIcon("Resource/Icones/open.gif"));
		abrir.addActionListener(s);
		abrir.setActionCommand("Abrir");
		abrir.setToolTipText("Abrir");
		this.add(abrir);

		// Salvar como
		salvarComo = new JButton();
		salvarComo.setIcon(new ImageIcon("Resource/Icones/save.gif"));
		salvarComo.addActionListener(s);
		salvarComo.setActionCommand("Salvar Como");
		salvarComo.setToolTipText("Salvar Como");
		this.add(salvarComo);

		this.addSeparator();

		// Recortar
		recortar = new JButton();
		recortar.setEnabled(false);
		recortar.setIcon(new ImageIcon("Resource/Icones/cut.gif"));
		recortar.addActionListener(s);
		recortar.setActionCommand("Recortar");
		recortar.setToolTipText("Recortar");
		this.add(recortar);

		// Copiar
		copiar = new JButton();
		copiar.setEnabled(false);
		copiar.setIcon(new ImageIcon("Resource/Icones/copy.gif"));
		copiar.addActionListener(s);
		copiar.setActionCommand("Copiar");
		copiar.setToolTipText("Copiar");
		this.add(copiar);

		// Colar
		colar = new JButton();
		colar.setEnabled(false);
		colar.setIcon(new ImageIcon("Resource/Icones/paste.gif"));
		colar.addActionListener(s);
		colar.setActionCommand("Colar");
		colar.setToolTipText("Colar");
		this.add(colar);

		this.addSeparator();

		// Desfazer
		desfazer = new JButton();
		desfazer.setIcon(new ImageIcon("Resource/Icones/undo.gif"));
		desfazer.addActionListener(s);
		desfazer.setActionCommand("Desfazer");
		desfazer.setToolTipText("Desfazer");
		this.add(desfazer);
		desfazer.setSelected(false);

		// Refazer
		refazer = new JButton();
		refazer.setIcon(new ImageIcon("Resource/Icones/redo.gif"));
		refazer.addActionListener(s);
		refazer.setActionCommand("Refazer");
		refazer.setToolTipText("Refazer");
		this.add(refazer);
		refazer.setEnabled(false);

		this.addSeparator();

		zoomMenos = new JButton();
		zoomMenos.setIcon(new ImageIcon("Resource/Icones/zoomOut.gif"));
		zoomMenos.addActionListener(s);
		zoomMenos.setActionCommand("Zoom Menos");
		zoomMenos.setToolTipText("Zoom Menos");
		this.add(zoomMenos);

		zoomMais = new JButton();
		zoomMais.setIcon(new ImageIcon("Resource/Icones/zoomIn.gif"));
		zoomMais.addActionListener(s);
		zoomMais.setActionCommand("Zoom Mais");
		zoomMais.setToolTipText("Zoom Mais");
		this.add(zoomMais);

		this.addSeparator();

		// Rotacionar
		rotacionar = new JButton();
		rotacionar.setIcon(new ImageIcon("Resource/Icones/rotacionar.gif"));
		rotacionar.addActionListener(s);
		rotacionar.setActionCommand("Rotacionar");
		rotacionar.setToolTipText("Rotacionar");
		this.add(rotacionar);

		//Redimensionar
		redimensionar = new JButton();
		redimensionar.setIcon(new ImageIcon("Resource/Icones/resize.gif"));
		redimensionar.addActionListener(s);
		redimensionar.setActionCommand("Redimensionar");
		redimensionar.setToolTipText("Redimensionar");
		this.add(redimensionar);

		this.addSeparator();

		buttonHColorido = new JButton();
		buttonHColorido.setIcon(new ImageIcon("Resource/Icones/histogram.gif"));
		buttonHColorido.addActionListener(s);
		buttonHColorido.setActionCommand("Colorido");
		buttonHColorido.setToolTipText("Histograma Colorido");
		this.add(buttonHColorido);

		buttonHCanal = new JButton();
		buttonHCanal.setIcon(new ImageIcon("Resource/Icones/histo.gif"));
		buttonHCanal.addActionListener(s);
		buttonHCanal.setActionCommand("Por Canal");
		buttonHCanal.setToolTipText("Histograma Por Canal");
		this.add(buttonHCanal);

		this.addSeparator();

		buttonPLinha = new JButton();
		buttonPLinha.setIcon(new ImageIcon("Resource/Icones/perfilLinha.gif"));
		buttonPLinha.addActionListener(s);
		buttonPLinha.setActionCommand("Linha");
		buttonPLinha.setToolTipText("Perfil de Linha Simples");
		this.add(buttonPLinha);

		buttonPColuna = new JButton();
		buttonPColuna.setIcon(new ImageIcon("Resource/Icones/perfilColuna.gif"));
		buttonPColuna.addActionListener(s);
		buttonPColuna.setActionCommand("Coluna");
		buttonPColuna.setToolTipText("Perfil de Coluna Simples");
		this.add(buttonPColuna);

		this.addSeparator();

		buttonExportar = new JButton();
		buttonExportar.setIcon(new ImageIcon("Resource/Icones/exportar.GIF"));
		buttonExportar.addActionListener(s);
		buttonExportar.setActionCommand("Exportar");
		buttonExportar.setToolTipText("Exportar");
		this.add(buttonExportar);

		this.addSeparator();

		buttonOpLogica = new JButton();
		buttonOpLogica.setIcon(new ImageIcon("Resource/Icones/oplogica.gif"));
		buttonOpLogica.addActionListener(s);
		buttonOpLogica.setActionCommand("Lógicas");
		buttonOpLogica.setToolTipText("Operação Lógica");
		this.add(buttonOpLogica);

		buttonOpAritB = new JButton();
		buttonOpAritB.setIcon(new ImageIcon("Resource/Icones/oparitmetica.gif"));
		buttonOpAritB.addActionListener(s);
		buttonOpAritB.setActionCommand("Aritméticas");
		buttonOpAritB.setToolTipText("Operação Aritmética Básica");
		this.add(buttonOpAritB);

		buttonOpAritP = new JButton();
		buttonOpAritP.setIcon(new ImageIcon("Resource/Icones/oparitmeticapessoal.gif"));
		buttonOpAritP.addActionListener(s);
		buttonOpAritP.setActionCommand("Ganho/Offset");
		buttonOpAritP.setToolTipText("Operação Aritmética Pessoal");
		this.add(buttonOpAritP);

		this.addSeparator();

		buttonEscCinza = new JButton();
		buttonEscCinza.setIcon(new ImageIcon("Resource/Icones/grayscale.jpg"));
		buttonEscCinza.addActionListener(s);
		buttonEscCinza.setActionCommand("Escala de Cinza");
		buttonEscCinza.setToolTipText("Escala de Cinza");
		this.add(buttonEscCinza);

		buttonDecompor = new JButton();
		buttonDecompor.setIcon(new ImageIcon("Resource/Icones/decompor.gif"));
		buttonDecompor.addActionListener(s);
		buttonDecompor.setActionCommand("Decomposição");
		buttonDecompor.setToolTipText("Decompor Canais");
		this.add(buttonDecompor);

		buttonRecompor = new JButton();
		buttonRecompor.setIcon(new ImageIcon("Resource/Icones/recompor.gif"));
		buttonRecompor.addActionListener(s);
		buttonRecompor.setActionCommand("Combinação");
		buttonRecompor.setToolTipText("Combinar Canais");
		this.add(buttonRecompor);

		buttonPseudo = new JButton();
		buttonPseudo.setIcon(new ImageIcon("Resource/Icones/pseudo.GIF"));
		buttonPseudo.addActionListener(s);
		buttonPseudo.setActionCommand("Pseudo Colorização");
		buttonPseudo.setToolTipText("Pseudo Colorizacao");
		this.add(buttonPseudo);
	}

	public void habilitaBotoes(boolean habilita){

		abrir.setEnabled(habilita);
		salvarComo.setEnabled(habilita);
		recortar.setEnabled(habilita);
		copiar.setEnabled(habilita);
		colar.setEnabled(habilita);
		desfazer.setEnabled(habilita);
		refazer.setEnabled(habilita);
		zoomMais.setEnabled(habilita);
		zoomMenos.setEnabled(habilita);
		rotacionar.setEnabled(habilita);
		redimensionar.setEnabled(habilita);;
		buttonHColorido.setEnabled(habilita);
		buttonHCanal.setEnabled(habilita);
		buttonPLinha.setEnabled(habilita);
		buttonPColuna.setEnabled(habilita);
		buttonExportar.setEnabled(habilita);
		buttonOpLogica.setEnabled(habilita);
		buttonOpAritB.setEnabled(habilita);
		buttonOpAritP.setEnabled(habilita);
		buttonEscCinza.setEnabled(habilita);
		buttonDecompor.setEnabled(habilita);
		buttonRecompor.setEnabled(habilita);
		buttonPseudo.setEnabled(habilita);
	}


	public JButton getRecortar() {
		return recortar;
	}


	public JButton getCopiar() {
		return copiar;
	}


	public JButton getColar() {
		return colar;
	}


	public JButton getDesfazer() {
		return desfazer;
	}


	public JButton getRefazer() {
		return refazer;
	}
	
	
	
	
	
	
}
