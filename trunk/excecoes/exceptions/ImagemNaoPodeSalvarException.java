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
 * Classe de exceção que deve ser utilizada quando tenta-se salvar 
 * um arquivo de imagem inválido, ou seja um arquivo que não seja do tipo Imagem 
 * (gif, bmp, jpg e png).
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class ImagemNaoPodeSalvarException extends Exception {

	private static final long serialVersionUID = 1L; //Numero de série da classe
	private String mensagemDeErro;
	/**
	 * Cria uma exceção que deve ser lançada quando tenta-se manipular (salvar)
	 * uma imagem de forma inadequada. Por exemplo: um arquivo que não seja do tipo
	 * (gif, bmp, jpg e png).
	 * @param msg Uma mensagem com o motivo da exceção.
	 */
	public ImagemNaoPodeSalvarException(String msg){
		super(msg);
		mensagemDeErro = msg;	
	}
	/**
	 * Cria uma exceção que deve ser lançada quando tenta-se manipular (salvar)
	 * uma imagem de forma inadequada. Por exemplo:um arquivo que não seja do tipo
	 * (gif, bmp, jpg e png). A mensagem resultando do erro é default.
	 */
	public ImagemNaoPodeSalvarException () {
		String mensagemDefault = "Imagem não pode ser salvar, pois o tipo selecionado não existe";
		mensagemDeErro = mensagemDefault;
		new ImagemNaoExisteException(mensagemDefault);
	}
	/**
	 * Recupera a mensagem de erro referente a exceção em questão, salvamento de 
	 * imagem inadequadamente
	 * inexistente
	 * @return A mensagem de erro.
	 */
	public String getMessage (){
		return "ERRO: " + mensagemDeErro;
	}
}