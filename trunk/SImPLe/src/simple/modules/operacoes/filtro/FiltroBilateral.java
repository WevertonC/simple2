package simple.modules.operacoes.filtro;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

/**
 * @author Renato Miceli
 */
public class FiltroBilateral {

	private final BufferedImage image;
	private final int n;
	private final double sigmaD;
	private final double sigmaR;

	public FiltroBilateral(BufferedImage image, int n, double sigmaD, double sigmaR) {
		this.image = image;
		this.n = n;
		this.sigmaD = sigmaD;
		this.sigmaR = sigmaR;
	}

	public BufferedImage getBufferedImage() {

		int width = image.getWidth();
		int height = image.getHeight();
		int area = width * height;

		int[] pixelsOriginal = new int[area];
		PixelGrabber grabber = new PixelGrabber(image.getScaledInstance(width, height, 0), 0, 0, width, height, pixelsOriginal, 0, width);
		try {
			grabber.grabPixels();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		int[] newImage = new int[area];
		for (int maskCenter = 0; maskCenter < area; maskCenter++) {

			if (maskCenter < n * width || maskCenter >= area - n * width || maskCenter < (maskCenter / width) * width + n
					|| maskCenter >= ((maskCenter / width) + 1) * width - n) {
				newImage[maskCenter] = pixelsOriginal[maskCenter];
				continue;
			}

			int alpha = (pixelsOriginal[maskCenter] >> 24);
			int red = ((pixelsOriginal[maskCenter] & 0xff0000) >> 16);
			int green = ((pixelsOriginal[maskCenter] & 0x00ff00) >> 8);
			int blue = (pixelsOriginal[maskCenter] & 0xff);

			int row = maskCenter / width;
			int column = maskCenter - row * width;

			double rRed = 0;
			double rGreen = 0;
			double rBlue = 0;
			double newRed = 0;
			double newGreen = 0;
			double newBlue = 0;
			for (int centerPixelInRow = maskCenter - n * width; centerPixelInRow <= maskCenter + n * width; centerPixelInRow += width) {
				for (int neighborPos = centerPixelInRow - n; neighborPos <= centerPixelInRow + n; neighborPos++) {

					int anotherRed = ((pixelsOriginal[neighborPos] & 0xff0000) >> 16);
					int anotherGreen = ((pixelsOriginal[neighborPos] & 0x00ff00) >> 8);
					int anotherBlue = (pixelsOriginal[neighborPos] & 0xff);

					int anotherRow = centerPixelInRow / width;
					int anotherColumn = neighborPos - anotherRow * width;

					double aValue = Math.exp(-(Math.pow(anotherRow - row, 2) + Math.pow(anotherColumn - column, 2)) / (2 * Math.pow(sigmaD, 2)));

					double redValue = aValue * Math.exp(-Math.pow(anotherRed - red, 2) / (2 * Math.pow(sigmaR, 2)));
					double greenValue = aValue * Math.exp(-Math.pow(anotherGreen - green, 2) / (2 * Math.pow(sigmaR, 2)));
					double blueValue = aValue * Math.exp(-Math.pow(anotherBlue - blue, 2) / (2 * Math.pow(sigmaR, 2)));

					rRed += redValue;
					rGreen += greenValue;
					rBlue += blueValue;

					newRed += anotherRed * redValue;
					newGreen += anotherGreen * greenValue;
					newBlue += anotherBlue * blueValue;
				}
			}
			newRed /= rRed;
			newGreen /= rGreen;
			newBlue /= rBlue;

			newImage[maskCenter] = (alpha << 24 | (((int) Math.round(newRed)) << 16) | (((int) Math.round(newGreen)) << 8) | ((int) Math
					.round(newBlue)));
		}

		BufferedImage returnImage = new BufferedImage(width, height, image.getType());

		returnImage.setRGB(0, 0, width, height, newImage, 0, width);
		return returnImage;
	}

}
