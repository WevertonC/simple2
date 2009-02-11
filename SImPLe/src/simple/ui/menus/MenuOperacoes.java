package simple.ui.menus;

import java.awt.event.KeyEvent;

import simple.ui.janelas.SImPLe;

/**
 * Responsável pela instanciação do menu operações e da invocação das respectivas
 * funcionalidades associadas aos itens do menu.
 * 
 */
public class MenuOperacoes extends SimpleMenu{
	
	private static final long serialVersionUID = 1L;
	private MenuRadiometricas radiometricas;
	private MenuGeometricas geometricas;

	public MenuOperacoes(SImPLe simple) {
		super(MENU_OPERACOES, simple);
		setMnemonic(KeyEvent.VK_E);
		radiometricas = new MenuRadiometricas(simple);
		geometricas = new MenuGeometricas(simple);
		add(radiometricas);
		add(geometricas);
	}
	
	
	public MenuGeometricas getGeometricas() {
		return geometricas;
	}

	public MenuRadiometricas getRadiometricas() {
		return radiometricas;
	}

	/**
	 * Chamada da janela para exibição das operações lógicas
	 */
	public void operacoesLogicas(){
		radiometricas.operacoesLogicas();
	}

	public void habilitaBotoes(boolean habilita) {
		setEnabled(habilita);
	}
	
	/**
	 * Realiza a equalização de uma imagem
	 */
	public void equalizar(){
		radiometricas.equalizar();
	}
	
	/**
	 * Invoca uma janela que exibe o ganho/offset de uma imagem
	 */
	public void ganhoOffset(){
		radiometricas.ganhoOffset();
	}
	
	/**
	 * Exibição da janela de operações aritméticas
	 */
	public void operacoesAritmeticas(){
		radiometricas.operacoesAritmeticas();
	}
	
	/**
	 * Realiza a dilatação 4 de uma imagem
	 */
	public void dilatacao4(){
		radiometricas.dilatacao4();
	}
	
	public void dilatacao8(){
		radiometricas.dilatacao8();
	}
	
	public void erosao4(){
		radiometricas.erosao4();
	}
	
	public void erosao8(){
		radiometricas.erosao8();
	}
	
	public void media(){
		radiometricas.media();
	}
	
	public void mediana(){
		radiometricas.mediana();
	}
	
	public void moda(){
		radiometricas.moda();
	}
	
	public void sobel(){
		radiometricas.sobel();
	}

	public void prewitt() {
		radiometricas.prewitt();
		
	}

	public void roberts() {
		radiometricas.roberts();
		
	}

	public void laplace() {
		radiometricas.laplace();
		
	}

	public void gaussiano() {
		radiometricas.gaussiano();
	}
	
	public void freiChen() {
		radiometricas.freiChen();
	}
	
	public void passaAlta(){
		radiometricas.passaAlta();
	}
	
	public void passaBaixa(){
		radiometricas.passaBaixa();
	}
	
	public void passaFaixa(){
		radiometricas.passaFaixa();
	}
	
	public void rejeitaFaixa(){
		radiometricas.rejeitaFaixa();
	}
	
	public void reflectancia(){
		radiometricas.reflectancia();
	}
	
	public void iluminacao(){
		radiometricas.iluminacao();
	}
	
	public void gaussianoPassaAlta(){
		radiometricas.gaussianoPassaAlta();
	}
	
	public void gaussianoPassaBaixa(){
		radiometricas.gaussianoPassaBaixa();
	}
	
	public void butterworthPassaAlta(){
		radiometricas.butterworthPassaAlta();
	}
	
	public void butterworthPassaBaixa(){
		radiometricas.butterworthPassaBaixa();
	}
	
	public void fechamento4(){
		radiometricas.fechamento4();
	}
	
	public void fechamento8(){
		radiometricas.fechamento8();
	}
	
	public void abertura4(){
		radiometricas.abertura4();
	}
	
	public void abertura8(){
		radiometricas.abertura8();
	}
	
	public void saltPepper(){
		radiometricas.saltPepper();
	}
	
	public void ruidoGaussiano(){
		radiometricas.ruidoGaussiano();
	}
	
	public void zoomMais(){
		geometricas.zoomMais();
	}
	
	public void zoomMenos(){
		geometricas.zoomMenos();
	}
	
	public void ampliar(){
		geometricas.ampliar();
	}
	
	public void rotacionar(){
		geometricas.rotacionar();
	}
	
	public void redimensionar(){
		geometricas.redimensionar();
	}
	
	public void emboss(){
		radiometricas.emboss();
	}

	public void filtroBilateral() {
		radiometricas.filtroBilateral();
	}
	
	public void filtroBilateralAdaptativo() {
		radiometricas.filtroBilateralAdaptativo();
	}

}
