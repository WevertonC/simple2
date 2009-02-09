package simple.modules.fourier.filtering;

import java.awt.Color;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;

import com.sun.corba.se.spi.ior.MakeImmutable;

import simple.manipulacoes.util.MyBufferedImage;
import simple.manipulacoes.util.PixelUtils;
import simple.modules.fourier.controller.Controller;
import simple.modules.fourier.exceptions.FourierException;
import simple.modules.operacoes.radiometricas.EscalaCinza;

public class FiltragemHomomorfica {


	public FiltragemHomomorfica(){
	}

	public BufferedImage reflectanciaFreq(BufferedImage input, double corte) throws FourierException{
		Image i = exp(new ImageIcon(MyBufferedImage.makeImage(input)));
		return Controller.passaAltaFreq(MyBufferedImage.makeBufferedImage(i),corte);
	}

	public BufferedImage reflectanciaEsp(BufferedImage input, double corte) throws FourierException{

		Image i = log(new ImageIcon(MyBufferedImage.makeImage(input)));
		BufferedImage out = Controller.passaAltaEsp(MyBufferedImage.makeBufferedImage(i),corte);
		i = exp(new ImageIcon(out));
		return MyBufferedImage.makeBufferedImage(i);
	}

	public BufferedImage iluminacaoFreq(BufferedImage input, double corte) throws FourierException{
		Image i = exp(new ImageIcon(MyBufferedImage.makeImage(input)));
		return Controller.passaBaixaFreq(MyBufferedImage.makeBufferedImage(i),corte);
	}

	public BufferedImage iluminacaoEsp(BufferedImage input, double corte) throws FourierException{

		Image i = log(new ImageIcon(MyBufferedImage.makeImage(input)));
		BufferedImage out = Controller.passaBaixaEsp(MyBufferedImage.makeBufferedImage(i),corte);
		i = exp(new ImageIcon(MyBufferedImage.makeImage(out)));
		return escalaCinza(MyBufferedImage.makeBufferedImage(i));
	}


	private static Image exp(ImageIcon imagem) throws FourierException{

		int altura = imagem.getIconHeight();
		int largura = imagem.getIconWidth();
		Image img = imagem.getImage();

		int[] pixels = new int[altura*largura];

		PixelGrabber pg =  new PixelGrabber(img, 0, 0, largura, altura, pixels, 0, largura);

		try { 
			pg.grabPixels(); 
		}
		catch (InterruptedException e)  {
			throw new FourierException(FourierException.PIXELS_N_CAPTURADOS);
		}

		for (int i =0; i < pixels.length; i++){

			Color c = new Color(pixels[i]);
			int red = PixelUtils.exp(c.getRed());
			Color c2 = new Color(red,red,red);
			pixels[i] = c2.getRGB();
		}

		BufferedImage bi = new BufferedImage(largura, altura, BufferedImage.TYPE_BYTE_GRAY);			
		bi.setRGB(0, 0, largura, altura, pixels, 0, largura);

		return bi;


	}

	private static Image log(ImageIcon imagem) throws FourierException {
		int altura = imagem.getIconHeight();
		int largura = imagem.getIconWidth();
		Image img = imagem.getImage();

		int[] pixels = new int[altura*largura];

		PixelGrabber pg =  new PixelGrabber(img, 0, 0, largura, altura, pixels, 0, largura);

		try { 
			pg.grabPixels(); 
		}
		catch (InterruptedException e)  {
			throw new FourierException(FourierException.PIXELS_N_CAPTURADOS);
		}

		for (int i =0; i < pixels.length; i++){

			Color c = new Color(pixels[i]);
			int red = PixelUtils.log(c.getRed() + 1);
			if (red < 0) red = 0;
			Color c2 = new Color(red,red,red);
			pixels[i] = c2.getRGB();

		}
		BufferedImage bi = new BufferedImage(largura, altura, BufferedImage.TYPE_BYTE_GRAY);			
		bi.setRGB(0, 0, largura, altura, pixels, 0, largura);

		return bi;

	}
	private BufferedImage escalaCinza(BufferedImage image){

		BufferedImage grayImage;

		if (image.getType() != BufferedImage.TYPE_BYTE_GRAY) {
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			ColorConvertOp op = new ColorConvertOp(cs, null); grayImage =
				op.filter(image, null);
		} else {
			grayImage = image;
		}

		return grayImage;
	}

}
