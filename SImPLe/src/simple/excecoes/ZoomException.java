package simple.excecoes;
/*
 * ZoomException
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */



/**
 * Classe de Excecao utilizada pela classe MyImage, quando se escolhe um valor não
 * vãlido para dar um zoom na imagem, ou seja, quando o valor de zoom é maior do que
 * zero.
 *  
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class ZoomException extends Exception {

	private static final long serialVersionUID = 1L; //Numero de série da classe
	private static String mensagemDeErro;
	/**
	 * Cria uma exceção que deve ser lançada quando tenta-se utilizar um valor
	 * de zoom menor do que zero ou maior que 1600.
	 * @param msg Uma mensagem com o motivo da exceção.
	 */
	public ZoomException(String msg) {
		super(msg);
		mensagemDeErro = msg;
	}
    /**
	 * Cria uma exceção que deve ser lançada quando tenta-se utilizar um valor
	 * de zoom menor do que zero ou maior que 10000. A mensagem resultando do erro é default.
	 */
	public ZoomException() {
		String mensagemDefault = "Impossível diminuir/aumentar mais a imagem!";
		mensagemDeErro = mensagemDefault;
		new ImagemNaoExisteException(mensagemDefault);
	}
	/**
	 * Recupera a mensagem de erro referente a exceção em questão, valor de zoom
	 * menor do que 0.
	 * @return A mensagem de erro.
	 */
	public String getMessage (){
		return "ERRO: " + mensagemDeErro;
	}
}
