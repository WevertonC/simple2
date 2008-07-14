package simple.fourier.exceptions;

/**
 * Representa uma exceção lançada quando há um problema durante a operação da Transformada de Fourier. 
 * Notifica erros de naturezas relativas à este domínio, deste arquivos de entrada não encontrados até
 * a impossibilidade de execução  devido a problemas nas dimensões da imagem.
 * 
 * @see core.FourierImage
 * @author Elloa B. Guedes - elloa@dsc.ufcg.edu.br
 * @author Odilon F. Lima Jr. - odilon@dsc.ufcg.edu.br
 */
public class FourierException extends Exception {

	public static String ARQUIVO_N_EXISTE = "Arquivo não encontrado. Forneça uma imagem válida como entrada.";
	public static String ERRO_SALVAR_SAIDA = "O arquivo já existe ou não pode ser salvo. Verifique imagem de saída passada como parâmetro.";
	public static String DOMINIO_FREQUENCIA = "A imagem deve estar no domínio da freqüência para que esta operação seja realizada.";
	public static String DIMENSAO_IMAGEM = "A imagem deve ter mesma altura e largura, ambas potências de 2";

	/**
	 * Construtor default. A mensagem de erro é passada como parâmetro. Esta classe contém
	 * mensagem parametrizadas cujo uso é preferível.
	 * 
	 * @param message descrição da mensagem de erro.
	 */
	public FourierException(String message) {
		super(message);
	}
}
