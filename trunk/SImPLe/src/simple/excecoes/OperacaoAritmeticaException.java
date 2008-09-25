package simple.excecoes;
/*
 * OperacaoAritmeticaException
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */



/**
 * Classe de Excecao que deve ser lan�ada pelas classes do pacote fePDI.operacoesAritmeticas, 
 * caso as classe Adicao, Divisao, Multiplicacao e Subtracao caso essas n�o consigam capturar
 * os pixels da imagem.
 * 
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class OperacaoAritmeticaException extends Exception {

	private static final long serialVersionUID = 1L; //Numero de s�rie da classe
	private static String mensagemDeErro;
	/**
	 * Cria uma exce��o que deve ser lan�ada pelas classes do pacote fePDI.operacoesAritmeticas, 
	 * caso as classe Adicao, Divisao, Multiplicacao e Subtracao caso essas n�o consigam capturar
	 * os pixels da imagem.
	 * @param msg Uma mensagem com o motivo da exce��o.
	 */
	public OperacaoAritmeticaException(String msg) {
		super(msg);
		mensagemDeErro = msg;
	}
    /**
	 * Cria uma exce��o que deve ser lan�ada pelas classes do pacote fePDI.operacoesAritmeticas, 
     * caso as classe Adicao, Divisao, Multiplicacao e Subtracao caso essas n�o consigam capturar
     * os pixels da imagem. A mensagem resultando do erro � default.
	 */
	public OperacaoAritmeticaException() {
		String mensagemDefault = "Nao foi possivel capturar os pixels da imagem!";
		mensagemDeErro = mensagemDefault;
		new ImagemNaoExisteException(mensagemDefault);
	}
	/**
	 * Recupera a mensagem de erro referente a exce��o em quest�o, n�o foi possivel
	 * capturar os pixels da imagem.
	 * @return A mensagem de erro.
	 */
	public String getMessage (){
		return "ERRO: " + mensagemDeErro;
	}
}
