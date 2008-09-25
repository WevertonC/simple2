package simple.excecoes;
/*
 * ImagemNaoSelecionadaException
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */



/**
 * Classe de exceção que deve ser utilizada quando tenta-se manipular uma imagem que 
 * não foi selecionada pela interface das janelas de Abrir e Salvar.
 * @see fePDI.manipularArquivo.JanelasArquivo;
 * @see fePDI.manipularArquivo.AbrirImagem;
 * @see fePDI.manipularArquivo.SalvarImagem;
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class ImagemNaoSelecionadaException extends Exception{

	private static final long serialVersionUID = 1L; //Numero de série da classe
	private static String mensagemDeErro;
	/**
	 * Cria uma exceção quando tenta-se manipular uma imagem que 
     * não foi selecionada pela interface das janelas de Abrir e Salvar.
	 * @param msg Uma mensagem com o motivo da exceção.
	 */
	public ImagemNaoSelecionadaException(String msg) {
		super(msg);
		mensagemDeErro = msg;
	}
    /**
	 * Cria uma exceção que deve ser lançada quando tenta-se utilizar um valor
	 * de zoom menor do que zero. A mensagem resultando do erro é default.
	 * 
	 */
	public ImagemNaoSelecionadaException() {
		String mensagemDefault = "A imagem da janela nao foi selecionda!";
		mensagemDeErro = mensagemDefault;
		new ImagemNaoExisteException(mensagemDefault);
	}
	/**
	 * Recupera a mensagem de erro referente a exceção em questão, uma imagem não
	 * selecionada pela interface.
	 * @return A mensagem de erro.
	 */
	public String getMessage (){
		return "ERRO: " + mensagemDeErro;
	}
}
