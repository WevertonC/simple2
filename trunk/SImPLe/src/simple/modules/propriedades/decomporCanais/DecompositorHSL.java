package simple.modules.propriedades.decomporCanais;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;

import simple.manipulacoes.util.Modificador;
import simple.manipulacoes.util.MyBufferedImage;

/**
 * @author Caio Santos
 * @author Renato Miceli
 */
public class DecompositorHSL {

	private static final int HUE = 0;
	private static final int SATURATION = 1;
	private static final int LIGHTNESS = 2;

	private final int[][] alpha;
	private final BufferedImage image;
	private final String imgName;

	/**
	 * Builds a new HSL Image Dealer by pointing the image to deal with and its
	 * name.
	 * 
	 * @param image
	 *            the image that will be dealt with
	 * @param nome
	 *            the image's name
	 */
	public DecompositorHSL(BufferedImage image, String nome) {
		this.imgName = nome;
		this.image = image;
		this.alpha = new Modificador(image).getAlpha();
	}

	/**
	 * Decomposes an image into HSL channels.
	 * 
	 * @return an array with three HSL channel images
	 */
	public Image[] getHSLChannels() {
		Image[] frames = new Image[3];
		BufferedImage[] channels = new BufferedImage[3];

		final int imgWidth = image.getWidth();
		final int imgHeight = image.getHeight();

		int imgType = image.getType();

		channels[HUE] = new BufferedImage(imgWidth, imgHeight, imgType);
		channels[SATURATION] = new BufferedImage(imgWidth, imgHeight, imgType);
		channels[LIGHTNESS] = new BufferedImage(imgWidth, imgHeight, imgType);

		for (int x = 0; x < imgWidth; x++)
			for (int y = 0; y < imgHeight; y++) {
				int red = image.getRGB(x, y) & 0x00ff0000;
				int green = image.getRGB(x, y) & 0x0000ff00;
				int blue = image.getRGB(x, y) & 0x000000ff;
				int alphaValue = alpha[y][x];

				red >>= 16;
				green >>= 8;
				blue >>= 0;

				double dRed = red / 255.;
				double dGreen = green / 255.;
				double dBlue = blue / 255.;

				double max = Math.max(dRed, Math.max(dGreen, dBlue));
				double min = Math.min(dRed, Math.min(dGreen, dBlue));
				double delta = max - min;

				double hue = 0;
				double saturation = 0;
				double lightness = (max + min) / 2;

				if (delta <= 0.0001) {
					saturation = 0.;
					hue = 0.;
				} else {
					if (dRed == max) {
						hue = (60 * (dGreen - dBlue) / delta + 360) % 360;
					} else if (dGreen == max) {
						hue = (60 * (dBlue - dRed) / delta) + 120;
					} else {
						hue = (60 * (dRed - dGreen) / delta) + 240;
					}

//					if (lightness <= 0.5) {
//						saturation = delta / 2 * lightness;
//					} else {
//						saturation = delta / (2 - 2 * lightness);
//					}
					saturation = max - min;
				}

				int iHue = (int) Math.round((hue / 360) * 0xff);
				int iSaturation = (int) Math.round(saturation * 0xff);
				int iLightness = (int) Math.round(lightness * 0xff);

				// Hue Channel
				channels[HUE].setRGB(x, y, (alphaValue << 24) + (iHue << 16) + (iHue << 8) + iHue);
				// Saturation Channel
				channels[SATURATION].setRGB(x, y, (alphaValue << 24) + (iSaturation << 16) + (iSaturation << 8) + iSaturation);
				// Lightness Channel
				channels[LIGHTNESS].setRGB(x, y, (alphaValue << 24) + (iLightness << 16) + (iLightness << 8) + iLightness);
			}
		frames[HUE] = new ImageIcon(MyBufferedImage.makeImage(channels[HUE])).getImage();
		frames[SATURATION] = new ImageIcon(MyBufferedImage.makeImage(channels[SATURATION])).getImage();
		frames[LIGHTNESS] = new ImageIcon(MyBufferedImage.makeImage(channels[LIGHTNESS])).getImage();
		return frames;
	}

	/**
	 * Composes an HSL image from its channels hue, saturation and lightness.
	 * 
	 * @param channels
	 *            The channels from which the image will be built
	 * @return the HSL composed image
	 */
	public static Image setCanaisHSL(BufferedImage channels[], int[][] alpha) {

		int height = channels[HUE].getHeight();
		int width = channels[HUE].getWidth();
		int area = height * width;

		int[] hueChannel = new int[area];
		int[] saturationChannel = new int[area];
		int[] lightnessChannel = new int[area];

		try {
			new PixelGrabber(channels[HUE].getScaledInstance(width, height, 0), 0, 0, width, height, hueChannel, 0, width).grabPixels();
			new PixelGrabber(channels[SATURATION].getScaledInstance(width, height, 0), 0, 0, width, height, saturationChannel, 0, width).grabPixels();
			new PixelGrabber(channels[LIGHTNESS].getScaledInstance(width, height, 0), 0, 0, width, height, lightnessChannel, 0, width).grabPixels();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		int[] newImage = new int[area];
		for (int i = 0; i < area; i++) {
			int hue = (hueChannel[i] & 0xff);
			int saturation = (saturationChannel[i] & 0xff);
			int lightness = (lightnessChannel[i] & 0xff);

			double dHue = hue / 255.;
			double dSaturation = saturation / 255.;
			double dLightness = lightness / 255.;
			
			double q;
			if (dLightness < 0.5) {
				q = dLightness * (1 + dSaturation);
			} else {
				q = dLightness + dSaturation - (dLightness * dSaturation);
			}
			double p = 2 * dLightness - q;

			double tRed = dHue + (1 / 3.);
			if (tRed < 0) {
				tRed += 1;
			}
			if (tRed > 1) {
				tRed -= 1;
			}

			double tGreen = dHue;
			if (tGreen < 0) {
				tGreen += 1;
			}
			if (tGreen > 1) {
				tGreen -= 1;
			}

			double tBlue = dHue - (1 / 3.);
			if (tBlue < 0) {
				tBlue += 1;
			}
			if (tBlue > 1) {
				tBlue -= 1;
			}

			double dRed;
			if (tRed < (1 / 6.)) {
				dRed = p + ((q - p) * 6 * tRed);
			} else if (tRed < 0.5) {
				dRed = q;
			} else if (tRed < (2 / 3.)) {
				dRed = p + ((q - p) * 6 * ((2 / 3.) - tRed));
			} else {
				dRed = p;
			}

			double dGreen;
			if (tGreen < (1 / 6.)) {
				dGreen = p + ((q - p) * 6 * tGreen);
			} else if (tGreen < 0.5) {
				dGreen = q;
			} else if (tGreen < (2 / 3.)) {
				dGreen = p + ((q - p) * 6 * ((2 / 3.) - tGreen));
			} else {
				dGreen = p;
			}

			double dBlue;
			if (tBlue < (1 / 6.)) {
				dBlue = p + ((q - p) * 6 * tBlue);
			} else if (tBlue < 0.5) {
				dBlue = q;
			} else if (tBlue < (2 / 3.)) {
				dBlue = p + ((q - p) * 6 * ((2 / 3.) - tBlue));
			} else {
				dBlue = p;
			}

			int red = (int) Math.round(dRed * 0xff);
			int green = (int) Math.round(dGreen * 0xff);
			int blue = (int) Math.round(dBlue * 0xff);

			newImage[i] = ((alpha[i % width][i / width] << 24) | (red << 16) | (green << 8) | (blue));
		}

		BufferedImage composedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		composedImage.setRGB(0, 0, width, height, newImage, 0, width);
		return new ImageIcon(MyBufferedImage.makeImage(composedImage)).getImage();
	}

	/**
	 * Recovers the image name.
	 * 
	 * @return the image name
	 */
	public String getNome() {
		return imgName;
	}

}