package simple.ui.menus;

import java.awt.event.KeyEvent;

import simple.ui.janelas.SImPLe;

public class MenuOperacoes extends SimpleMenu{
	
	/**
	 * 
	 */
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


	public void operacoesLogicas(){
		radiometricas.operacoesLogicas();
	}

	public void habilitaBotoes(boolean habilita) {
		setEnabled(habilita);
	}
	
	public void equalizar(){
		radiometricas.equalizar();
	}
	
	public void ganhoOffset(){
		radiometricas.ganhoOffset();
	}
	
	public void operacoesAritmeticas(){
		radiometricas.operacoesAritmeticas();
	}
	
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

}
