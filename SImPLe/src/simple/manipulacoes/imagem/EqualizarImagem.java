package simple.manipulacoes.imagem;


import java.awt.image.BufferedImage;

/**
 * An example which shows how to utilze an instance of HistEqualizer
 * to perform histogram equalization on an IntensityImage.
 */
public class EqualizarImagem {
	
	
	public static BufferedImage getImagemEqualizado(BufferedImage image){
		
		IntensityImage imagem = new IntensityImage(new RGBImage(image));
		HistEqualizer equalizer = new HistEqualizer();
		IntensityImage imagemEqualizada = (IntensityImage)equalizer.filter(imagem);
		return imagemEqualizada.makeBufferedImage();
		
	}
}
