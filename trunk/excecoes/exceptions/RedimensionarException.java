package exceptions;
/*
 * RedimensionarException
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */



/**
 * Classe de Excecao que é lançada quando a altura ou largura estão 
 * fora do limite (valores obrigatoriamente >0).
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 * Classe: RedimensionarException para a classe Redimensionar.
 */
public class RedimensionarException extends Exception {

	private static final long serialVersionUID = 1L; //Numero de série da classe
	private static String mensagemDeErro;
	/**
	 * Cria uma exceção que deve ser lançada que é lançada quando a altura ou largura estão 
     * fora do limite (valores obrigatoriamente >0).
	 * @param msg Uma mensagem com o motivo da exceção.
	 */
	public RedimensionarException(String msg) {
		super(msg);
		mensagemDeErro = msg;
	}	
    /**
	 * Cria uma exceção que deve ser lançada que é lançada quando a altura ou largura estão 
     * fora do limite (valores obrigatoriamente >0).
	 */
	public RedimensionarException() {
		String mensagemDefault = "O valor informado para altura e largura estao fora do intervalo permintido (Intervalo >0)!";
		mensagemDeErro = mensagemDefault;
		new ImagemNaoExisteException(mensagemDefault);
	}	
	/**
	 * Recupera a mensagem de erro referente a exceção em questão, valores fora da faixa
	 * de valores permitido.
	 * @return A mensagem de erro.
	 */
	public String getMessage (){
		return "ERRO: " + mensagemDeErro;
	}
}