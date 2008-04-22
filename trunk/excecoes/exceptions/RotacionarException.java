package exceptions;
/*
 * RotacionarExpection
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */



/**
 * Classe de Excecao que deve ser lançada que é lançada quando o ângulo é 
 * fora do limite (valores obrigatoriamente >0).
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class RotacionarException extends Exception {

	private static final long serialVersionUID = 1L;//Numero de série da classe
	private static String mensagemDeErro;
	/**
	 * Cria uma exceção que deve ser lançada que é lançada quando o ângulo é 
     * fora do limite (valores obrigatoriamente >0).
	 * @param msg Uma mensagem com o motivo da exceção.
	 */
	public RotacionarException(String msg) {
		super(msg);
		mensagemDeErro = msg;
	}
    /**
	 * Cria uma exceção que deve ser lançada que é lançada quando o ângulo é 
     * fora do limite (valores obrigatoriamente >0).
	 */
	public RotacionarException() {
		String mensagemDefault = "O valor informado para o angulo esta fora do intervalo permintido (Intervalo >0)!";
		mensagemDeErro = mensagemDefault;
		new ImagemNaoExisteException(mensagemDefault);
	}
	/**
	 * Recupera a mensagem de erro referente a exceção em questão, valor fora da faixa
	 * permitida.
	 * @return A mensagem de erro.
	 */
	public String getMessage (){
		return "ERRO: " + mensagemDeErro;
	}
}