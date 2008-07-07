package simple.core.fourier;

import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Main {

	public static void main(String args[]) {

		File file = new File(args[0]);
		Main fftmag = new Main(file);
	}

	public Main(File file) {

		// Define objeto BufferedImage para encapsular a imagem
		BufferedImage src = null, dest = null;

		// Armazena arquivo imagem numa BufferedImage
		try {
			src = ImageIO.read(file);
		} catch (Exception e) {
			System.out.println("Imagem '" + file + "' nao existe.");
			System.exit(0);
		}

		try {
			dest = computeSpectrum(src);
		} catch (FourierException ffte) {
			ffte.getMessage();
		}

		try {
			File outputFile = new File(".\\images\\fourier.bmp");
			ImageIO.write(dest, "BMP", outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Imagem salva com sucesso...");

	}

	public BufferedImage computeSpectrum(BufferedImage src) throws FourierException {

		FourierImage fft = new FourierImage(src);
		fft.transform();

		return fft.getSpectrum();

	}

}
