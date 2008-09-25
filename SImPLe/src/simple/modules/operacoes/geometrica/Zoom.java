package simple.modules.operacoes.geometrica;
/*
 * Rotacionar
 * 
 * @version 1.0
 * 
 * Date: 10/10/05
 * 
 * Copyright FEDPI all rights reserved
 */
import simple.excecoes.RedimensionarException;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Classe que fornece a opção de zoom na imagem
 * @version 1.0 20/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class Zoom {

	private ImageIcon imagem;
	private int fatorZoom;
	
	/**
	 * Cosntrutor da classe zoom
	 * @param imagem A imagem que ira sofrer o zoom
	 */
	public Zoom(ImageIcon imagem){
		this.imagem = imagem;
		this.fatorZoom = 100;
	}
	
	/**
	 * Produz um maior zoom na imagem
	 * @param porcentagem A porcentagem de zoom desejada
	 * @return A nova imagem com a modificacao realizada
	 * @throws RedimensionarException 
	 */
	public Image maisZoom(int porcentagem) throws RedimensionarException{
		fatorZoom += porcentagem;
		return Redimensionar.emPorcentagemBICUBIC(imagem,fatorZoom,fatorZoom);
	}
	
	/**
	 * Da um zoom de 10% na imagem
	 * @return A imagem modificada
	 * @throws RedimensionarException 
	 */
	public Image maisZoom() throws RedimensionarException{
		return maisZoom(10);
	}
	
	/**
	 * Dimuniu o zoom da imagem
	 * @param porcentagem A porcetagem desejada para o zoom
	 * @return A imagem modificada
	 * @throws RedimensionarException 
	 */
	public Image menosZoom(int porcentagem) throws RedimensionarException{
		fatorZoom -= porcentagem;
		return Redimensionar.emPorcentagemBICUBIC(imagem,fatorZoom,fatorZoom);
	}
	
	/**
	 * Diminui em 10% o zoom da imagem
	 * @return A imagem modificada
	 * @throws RedimensionarException 
	 */
	public Image menosZoom() throws RedimensionarException{
		return menosZoom(10);
	}
}