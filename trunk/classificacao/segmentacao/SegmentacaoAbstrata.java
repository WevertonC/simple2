package segmentacao;

/*
 * SegmentacaoAbstrata
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

/**
 * Classe base para as segmentacoes a serem utlilizadas
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public abstract class SegmentacaoAbstrata implements SegmentacaoIF {

	private SegmentaoOperacao operation;

	/**
	 * Construtor de uma segmentacao abastrata
	 */
	public SegmentacaoAbstrata(SegmentaoOperacao operation) {
		this.operation = operation;
	}

	/**
	 * Metodo que retorna a opecao a utilizada
	 * @return operacao
	 */
	public SegmentaoOperacao getOperation() {
		return operation;
	}
}
