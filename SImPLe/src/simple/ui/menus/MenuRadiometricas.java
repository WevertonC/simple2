package simple.ui.menus;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import simple.ui.janelas.SImPLe;

public class MenuRadiometricas extends SimpleMenu{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MenuOperacoesPontuais pontuais;
	private MenuOperacoesLocais locais;

	public MenuRadiometricas(SImPLe simple) {
		super(OP_RADIOMETRICAS, simple);
		setMnemonic(KeyEvent.VK_R);
		setIcon(new ImageIcon("Resource/Icones/radiometrica.jpg"));
		pontuais = new MenuOperacoesPontuais(simple);
		locais = new MenuOperacoesLocais(simple);
		configureMenu();
	}

	private void configureMenu(){
		add(pontuais);
		add(locais);
	}

	/**
	 * Chamada da janela para exibição das operações lógicas
	 */
	public void operacoesLogicas(){
		pontuais.operacoesLogicas();
	}

	public void habilitaBotoes(boolean habilita) {
		setEnabled(habilita);
	}

	/**
	 * Realiza a equalização de uma imagem
	 */
	public void equalizar(){
		pontuais.equalizar();
	}

	/**
	 * Invoca uma janela que exibe o ganho/offset de uma imagem
	 */
	public void ganhoOffset(){
		pontuais.ganhoOffset();
	}

	/**
	 * Exibição da janela de operações aritméticas
	 */
	public void operacoesAritmeticas(){
		pontuais.operacoesAritmeticas();
	}

	/**
	 *  Realiza a dilatação 4 de uma imagem
	 */
	public void dilatacao4(){
		pontuais.dilatacao4();
	}

	public void dilatacao8(){
		pontuais.dilatacao8();
	}

	public void erosao4(){
		pontuais.erosao4();
	}
	public void erosao8(){
		pontuais.erosao8();
	}

	public void media(){
		locais.media();
	}

	public void mediana(){
		locais.mediana();
	}

	public void moda(){
		locais.moda();
	}

	public void sobel(){
		locais.sobel();
	}

	public void prewitt() {
		locais.prewitt();

	}

	public void roberts() {
		locais.roberts();
	}

	public void laplace() {
		locais.laplace();
	}

	public void gaussiano() {
		locais.gaussiano();
	}

	public void freiChen(){
		locais.freiChen();
	}

	public void passaAlta(){
		locais.passaAlta();
	}

	public void passaBaixa(){
		locais.passaBaixa();
	}

	public void passaFaixa(){
		locais.passaFaixa();
	}

	public void fechamento4(){
		locais.fechamento4();
	}

	public void fechamento8(){
		locais.fechamento8();
	}

	public void abertura4(){
		locais.abertura4();
	}

	public void abertura8(){
		locais.abertura8();
	}

	public void saltPepper(){
		locais.saltPepper();
	}

	public void ruidoGaussiano(){
		locais.ruidoGaussiano();
	}

	public void emboss() {
		locais.emboss();

	}

}
