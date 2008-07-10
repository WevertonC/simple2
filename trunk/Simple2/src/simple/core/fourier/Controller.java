package simple.core.fourier;

import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Controller {

	private File file;
	
	public Controller() {
		//
	}
	
	public void fft(String path, String fileName){
		setFile(new File(path));
		
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
			File outputFile = new File(fileName);
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
