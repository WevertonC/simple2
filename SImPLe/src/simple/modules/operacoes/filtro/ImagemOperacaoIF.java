package simple.modules.operacoes.filtro;

/*
 * ImagemOperacaoIF
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
import java.awt.image.BufferedImage;

import simple.excecoes.ImageProcessingException;

/**
 * Interface ImagemOperacaoIF.
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public interface ImagemOperacaoIF {
	
	/**
	 * Metodo que executa as operacoes de cada filtro
	 * @return a imagem processada pelo determinado filtro
	 * @throws ImageProcessingException
	 */
	public BufferedImage executeOperation() throws ImageProcessingException;
}
