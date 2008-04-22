package exceptions;
/*
 * CompressorImagemException
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
 * @version 1.0 07/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class FormatoInvalidoException  extends Exception{

	private static final long serialVersionUID = 1L;
	private String mensagemDeErro;

	/**
	 * Cria uma exce��o que deve ser lan�ada quando tenta-se manipular (abrir)
	 * uma imagem que n�o existe
	 * @param msg Uma mensagem com o motivo da exce��o.
	 */
	public FormatoInvalidoException(String msg){
		super(msg);
		mensagemDeErro = msg;	
	}	
	/**
	 * Cria uma exce��o que deve ser lan�ada quando tenta-se comprimir (exportar)
	 * uma imagem com formato invalido. A mensagem resultando do erro � default.
	 * 
	 */
	public FormatoInvalidoException() {
		String mensagemDefault = "Erro ao comprimir a imagem! Verifique se o formato desejado � valido.";
		new ImagemNaoExisteException(mensagemDefault);
	}	
	/**
	 * Recupera a mensagem de erro referente a exce��o em quest�o, comprimir
	 * imagem com formato invalido.
	 * @return A mensagem de erro.
	 */
	public String getMessage (){
		return "ERRO: " + mensagemDeErro;
	}
}
