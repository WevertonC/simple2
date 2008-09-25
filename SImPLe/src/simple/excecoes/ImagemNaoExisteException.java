package simple.excecoes;
/*
 * ImagemNaoExisteException
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

/**
 * Classe de Exceção para manipulação de imagem inexistente. Deve ser lançada quando
 * tenta-se abrir um arquivo de Imagem que não existe.
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class ImagemNaoExisteException extends Exception{
		
	private static final long serialVersionUID = 1L; //Numero de série da classe
	private String mensagemDeErro;
	/**
	 * Cria uma exceção que deve ser lançada quando tenta-se manipular (abrir)
	 * uma imagem que não existe
	 * @param msg Uma mensagem com o motivo da exceção.
	 */
	public ImagemNaoExisteException(String msg){
		super(msg);
		mensagemDeErro = msg;	
	}	
	/**
	 * Cria uma exceção que deve ser lançada quando tenta-se manipular (abrir)
	 * uma imagem que não existe. A mensagem resultando do erro é default.
	 * 
	 */
	public ImagemNaoExisteException () {
		String mensagemDefault = "Imagem não pode ser manipulada (aberta), pois a mesma não existe";
		new ImagemNaoExisteException(mensagemDefault);
	}	
	/**
	 * Recupera a mensagem de erro referente a exceção em questão, abrir imagem
	 * inexistente.
	 * @return A mensagem de erro.
	 */
	public String getMessage (){
		return "ERRO: " + mensagemDeErro;
	}
}
