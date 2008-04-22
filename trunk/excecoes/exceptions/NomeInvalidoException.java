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
 * Classe de Excecao que � lan�ada quando o nome escolhido para salvar uma imagem
 * � um nome inv�lido, ou seja, nome contendo os caracteres do tipo ("<",">",":",";","/").
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class NomeInvalidoException extends Exception {

	private static final long serialVersionUID = 1L; //Numero de s�rie da classe
	private static String mensagemDeErro;
	/**
	 * Cria uma exce��o que deve ser lan�ada quando tenta-se salvar uma imagem
	 * com um nome com caracteres inv�lidos ("<",">",":",";","/").
	 * @param msg Uma mensagem com o motivo da exce��o.
	 */
	public NomeInvalidoException(String msg) {
		super(msg);
		mensagemDeErro = msg;
	}
    /**
	 * Cria uma exce��o que deve ser lan�ada quando tenta-se salvar uma imagem
	 * com um nome com caracteres inv�lidos ("<",">",":",";","/"). A mensagem resultando do erro � default.
	 */
	public NomeInvalidoException() {
		String mensagemDefault = "Zoom com Valor Invalido (valores obrigatoriamente > 0)!";
		mensagemDeErro = mensagemDefault;
		new ImagemNaoExisteException(mensagemDefault);
	}
	/**
	 * Recupera a mensagem de erro referente a exce��o em quest�o, o nome da imagem
	 * � inv�lido.
	 * @return A mensagem de erro.
	 */
	public String getMessage (){
		return "ERRO: " + mensagemDeErro;
	}
}