package simple.manipulacoes.util;

/**
 * Classe abstrata que representa uma imagem que esta sendo executada por
 * sua propria thread.
 * @author Ricardo Madeira Fernandes
 */
public abstract class ImageProcessing {

	/**
	 * Metodo para invocar o processamento da imagem
	 */
	public abstract void run();

	/**
	 * Retorna o numero de passos para a segmentacao.
	 */
	public abstract long getSize();

	/**
	 * Retorna a posicao da imagem que esta sendo processada.
	 */
	public abstract long getPosition();

	/**
	 * Verifica se a imagem ja foi processada.
	 */
	public abstract boolean isFinished();

}
