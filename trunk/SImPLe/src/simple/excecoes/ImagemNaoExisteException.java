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
 * Classe de Exce��o para manipula��o de imagem inexistente. Deve ser lan�ada quando
 * tenta-se abrir um arquivo de Imagem que n�o existe.
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class ImagemNaoExisteException extends Exception{
		
	private static final long serialVersionUID = 1L; //Numero de s�rie da classe
	private String mensagemDeErro;
	/**
	 * Cria uma exce��o que deve ser lan�ada quando tenta-se manipular (abrir)
	 * uma imagem que n�o existe
	 * @param msg Uma mensagem com o motivo da exce��o.
	 */
	public ImagemNaoExisteException(String msg){
		super(msg);
		mensagemDeErro = msg;	
	}	
	/**
	 * Cria uma exce��o que deve ser lan�ada quando tenta-se manipular (abrir)
	 * uma imagem que n�o existe. A mensagem resultando do erro � default.
	 * 
	 */
	public ImagemNaoExisteException () {
		String mensagemDefault = "Imagem n�o pode ser manipulada (aberta), pois a mesma n�o existe";
		new ImagemNaoExisteException(mensagemDefault);
	}	
	/**
	 * Recupera a mensagem de erro referente a exce��o em quest�o, abrir imagem
	 * inexistente.
	 * @return A mensagem de erro.
	 */
	public String getMessage (){
		return "ERRO: " + mensagemDeErro;
	}
}
