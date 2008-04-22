/*
 * NomeInvalidoException
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

package exceptions;

/**
 * Classe de Excecao que é lançada quando o nome escolhido para salvar uma imagem
 * é um nome inválido, ou seja, nome contendo os caracteres do tipo ("<",">",":",";","/").
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class NomeInvalidoException extends Exception {

	private static final long serialVersionUID = 1L; //Numero de série da classe
	private static String mensagemDeErro;
	/**
	 * Cria uma exceção que deve ser lançada quando tenta-se salvar uma imagem
	 * com um nome com caracteres inválidos ("<",">",":",";","/").
	 * @param msg Uma mensagem com o motivo da exceção.
	 */
	public NomeInvalidoException(String msg) {
		super(msg);
		mensagemDeErro = msg;
	}
    /**
	 * Cria uma exceção que deve ser lançada quando tenta-se salvar uma imagem
	 * com um nome com caracteres inválidos ("<",">",":",";","/"). A mensagem resultando do erro é default.
	 */
	public NomeInvalidoException() {
		String mensagemDefault = "Zoom com Valor Invalido (valores obrigatoriamente > 0)!";
		mensagemDeErro = mensagemDefault;
		new ImagemNaoExisteException(mensagemDefault);
	}
	/**
	 * Recupera a mensagem de erro referente a exceção em questão, o nome da imagem
	 * é inválido.
	 * @return A mensagem de erro.
	 */
	public String getMessage (){
		return "ERRO: " + mensagemDeErro;
	}
}