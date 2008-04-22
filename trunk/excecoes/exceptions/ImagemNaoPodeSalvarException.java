package exceptions;
/*
 * ImagemNaoPodeSalvarException
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */



/**
 * Classe de exce��o que deve ser utilizada quando tenta-se salvar 
 * um arquivo de imagem inv�lido, ou seja um arquivo que n�o seja do tipo Imagem 
 * (gif, bmp, jpg e png).
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class ImagemNaoPodeSalvarException extends Exception {

	private static final long serialVersionUID = 1L; //Numero de s�rie da classe
	private String mensagemDeErro;
	/**
	 * Cria uma exce��o que deve ser lan�ada quando tenta-se manipular (salvar)
	 * uma imagem de forma inadequada. Por exemplo: um arquivo que n�o seja do tipo
	 * (gif, bmp, jpg e png).
	 * @param msg Uma mensagem com o motivo da exce��o.
	 */
	public ImagemNaoPodeSalvarException(String msg){
		super(msg);
		mensagemDeErro = msg;	
	}
	/**
	 * Cria uma exce��o que deve ser lan�ada quando tenta-se manipular (salvar)
	 * uma imagem de forma inadequada. Por exemplo:um arquivo que n�o seja do tipo
	 * (gif, bmp, jpg e png). A mensagem resultando do erro � default.
	 */
	public ImagemNaoPodeSalvarException () {
		String mensagemDefault = "Imagem n�o pode ser salvar, pois o tipo selecionado n�o existe";
		mensagemDeErro = mensagemDefault;
		new ImagemNaoExisteException(mensagemDefault);
	}
	/**
	 * Recupera a mensagem de erro referente a exce��o em quest�o, salvamento de 
	 * imagem inadequadamente
	 * inexistente
	 * @return A mensagem de erro.
	 */
	public String getMessage (){
		return "ERRO: " + mensagemDeErro;
	}
}