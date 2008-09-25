package simple.modules.classificacao.segmentacao;
/*
 * SegmentacaoAbstrata
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Classe base para as segmentacoes a serem utlilizadas
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class AdaptativeSubImage {
	@SuppressWarnings("unused")
	private BufferedImage image,subImage;
	private int x, y, width, height, ordem;
	

	/**
	 * @param image
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public AdaptativeSubImage(BufferedImage image, int x, int y, int width, int height) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Metodo que retorna a sub-imagem
	 * @return sub-imagem
	 */
	public BufferedImage getSubImage() {
		int imageType = image.getData().getNumBands() == 1 ? BufferedImage.TYPE_BYTE_GRAY : BufferedImage.TYPE_INT_RGB;
		BufferedImage subimage = new BufferedImage(width,height,imageType);
		WritableRaster destMatrix = subimage.getRaster();
		Raster srcMatrix = image.getData();
		for(int i=0; i<width;i++) {
			for(int j=0; j<height; j++) {
				int[] pixel = srcMatrix.getPixel((x+i),(y+j), new int[]{0,0,0});
				destMatrix.setPixel(i,j,pixel);
			}
		}
		subimage.setData(destMatrix);
		return subimage;
	}
	/**
	 * Método que recupera a ordem
	 * @return A ordem
	 */
	public int getOrdem() {
		return ordem;
	}
	/**
	 * Modifica a ordem
	 * @param ordem A nova ordem
	 */
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
	/**
	 * Método que recupera a altura
	 * @return A altura
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Método que modifica a altura
	 * @param height A nova altura
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Método que recupera a largura
	 * @return A largura
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Método que modifica a largura
	 * @param width A nova largura
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Método que recupera a coordenada X
	 * @return A coordenada X
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Método que modifica a coordenada X
	 * @param x A nova coordenada X
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Método que recupera a coordenada Y
	 * @return A coordenada Y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Método que modifica a coordenada Y
	 * @param y A nova coordenada Y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Método que recupera a Imagem
	 * @return A imgem
	 */
	public BufferedImage getImage() {
		return this.image;
	}
}
