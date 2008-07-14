package simple.fourier.exceptions;

/**
 * Representa uma exce��o lan�ada quando h� um problema durante a opera��o da Transformada de Fourier. 
 * Notifica erros de naturezas relativas � este dom�nio, deste arquivos de entrada n�o encontrados at�
 * a impossibilidade de execu��o  devido a problemas nas dimens�es da imagem.
 * 
 * @see core.FourierImage
 * @author Elloa B. Guedes - elloa@dsc.ufcg.edu.br
 * @author Odilon F. Lima Jr. - odilon@dsc.ufcg.edu.br
 */
public class FourierException extends Exception {

	public static String ARQUIVO_N_EXISTE = "Arquivo n�o encontrado. Forne�a uma imagem v�lida como entrada.";
	public static String ERRO_SALVAR_SAIDA = "O arquivo j� existe ou n�o pode ser salvo. Verifique imagem de sa�da passada como par�metro.";
	public static String DOMINIO_FREQUENCIA = "A imagem deve estar no dom�nio da freq��ncia para que esta opera��o seja realizada.";
	public static String DIMENSAO_IMAGEM = "A imagem deve ter mesma altura e largura, ambas pot�ncias de 2";

	/**
	 * Construtor default. A mensagem de erro � passada como par�metro. Esta classe cont�m
	 * mensagem parametrizadas cujo uso � prefer�vel.
	 * 
	 * @param message descri��o da mensagem de erro.
	 */
	public FourierException(String message) {
		super(message);
	}
}
