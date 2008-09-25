package simple.excecoes;

/*
 * ImageProcessingExceptio
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

/**
 * Classe de excecao
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class ImageProcessingException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor de uma ImageProcessingException
	 */
	public ImageProcessingException() {
		super();
	}

	/**
	 * Construtor de uma ImageProcessingException
	 * @param arg0
	 */
	public ImageProcessingException(String arg0) {
		super(arg0);
	}

	/**
	 * Construtor de uma ImageProcessingException
	 * @param arg0
	 */
	public ImageProcessingException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * Construtor de uma ImageProcessingException
	 * @param arg0
	 * @param arg1
	 */
	public ImageProcessingException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
