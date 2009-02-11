package simple.modules.operacoes.filtro;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

/**
 * @author Renato Miceli
 */
public class FiltroBilateralAdaptativo {

	public static enum KernelSize {
		kernel7x7, kernel9x9;
	}

	private final static int CLASS_NO_LOG = 121;
	private final static int WIND_SIZE = 7;
	private final static double SIGMA_D = 1.0;

	private static final int[] OFFSETS = new int[] { -45, -27, -25, -26, -25, -25, -23, -24, -23, -23, -22, -21, -21, -21, -20, -20, -19, -19, -17,
			-17, -16, -16, -16, -15, -14, -14, -13, -12, -12, -11, -10, -12, -11, -10, -12, -13, -12, -12, -11, -12, -11, -10, -9, -10, -10, -8, -8,
			-7, -5, -5, -4, -4, -3, -4, -2, -2, -1, -1, -1, 0, 2, 0, 1, 1, 1, 1, 2, 2, 3, 4, 5, 6, 5, 6, 7, 9, 7, 9, 8, 9, 10, 10, 9, 10, 11, 10, 11,
			11, 12, 11, 11, 10, 11, 11, 11, 13, 13, 14, 15, 14, 15, 17, 16, 17, 17, 18, 18, 19, 19, 19, 19, 21, 21, 21, 22, 21, 22, 23, 23, 24, 37 };

	private static final int[] SIGMA_R = new int[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7,
			7, 7, 9, 11, 11, 11, 11, 13, 13, 13, 13, 15, 15, 15, 15, 15, 13, 13, 13, 15, 13, 15, 13, 13, 11, 13, 11, 11, 9, 11, 13, 11, 13, 11, 13,
			13, 13, 15, 15, 17, 15, 15, 15, 17, 13, 15, 13, 13, 13, 13, 11, 11, 11, 9, 9, 9, 9, 7, 7, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
			5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };

	private static int[][] applyLoG7x7(int[][] blurNoisy, int width, int height, int t) {

		/* default sigma_r=1.0 for 7x7 LoG */
		final double[][] LoG7x7 = new double[][] { { -0.0015, -0.0088, -0.0275, -0.0394, -0.0275, -0.0088, -0.0015 },
				{ -0.0088, -0.0555, -0.1237, -0.1359, -0.1237, -0.0555, -0.0088 }, { -0.0275, -0.1237, -0.0005, 0.3027, -0.0005, -0.1237, -0.0275 },
				{ -0.0394, -0.1359, 0.3027, 0.9995, 0.3027, -0.1359, -0.0394 }, { -0.0275, -0.1237, -0.0005, 0.3027, -0.0005, -0.1237, -0.0275 },
				{ -0.0088, -0.0555, -0.1237, -0.1359, -0.1237, -0.0555, -0.0088 }, { -0.0015, -0.0088, -0.0275, -0.0394, -0.0275, -0.0088, -0.0015 } };

		final int w = 3;
		final int[][] LoGImage = new int[width][height];
		/* Filtering the image with LoG operator */
		for (int i = w; i < width - w; i++) {
			for (int j = w; j < height - w; j++) {

				double temp = 0;
				for (int k = i - w; k <= i + w; k++) {
					for (int l = j - w; l <= j + w; l++) {
						temp += LoG7x7[k - i + w][l - j + w] * (double) (blurNoisy[k][l]);
					}
				}

				if (temp < 0) {
					LoGImage[i][j] = Math.max((int) (temp - 0.5), -t);
				} else {
					LoGImage[i][j] = Math.min((int) Math.round(temp), t);
				}
			}
		}

		return LoGImage;
	}

	private static int[][] applyLoG9x9(int[][] blurNoisy, int width, int height, int t) {

		/* default sigma_r=1.5 */
		/* This is used in Sangho's LoG filter for his OUM 12/28/06 */
		final double[][] LoG9x9 = new double[][] { { -0.0091, -0.0217, -0.0446, -0.0677, -0.0771, -0.0677, -0.0446, -0.0217, -0.0091 },
				{ -0.0217, -0.0591, -0.1092, -0.1366, -0.1395, -0.1366, -0.1092, -0.0591, -0.0217 },
				{ -0.0446, -0.1092, -0.1356, -0.0407, 0.0416, -0.0407, -0.1356, -0.1092, -0.0446 },
				{ -0.0677, -0.1366, -0.0407, 0.3521, 0.6187, 0.3521, -0.0407, -0.1366, -0.0677 },
				{ -0.0771, -0.1395, 0.0416, 0.6187, 0.9959, 0.6187, 0.0416, -0.1395, -0.0771 },
				{ -0.0677, -0.1366, -0.0407, 0.3521, 0.6187, 0.3521, -0.0407, -0.1366, -0.0677 },
				{ -0.0446, -0.1092, -0.1356, -0.0407, 0.0416, -0.0407, -0.1356, -0.1092, -0.0446 },
				{ -0.0217, -0.0591, -0.1092, -0.1366, -0.1395, -0.1366, -0.1092, -0.0591, -0.0217 },
				{ -0.0091, -0.0217, -0.0446, -0.0677, -0.0771, -0.0677, -0.0446, -0.0217, -0.0091 } };

		final int w = 4;
		final int[][] LoGImage = new int[width][height];
		/* Filtering the image with LoG operator */
		for (int i = w; i < width - w; i++) {
			for (int j = w; j < height - w; j++) {

				double temp = 0;
				for (int k = i - w; k <= i + w; k++) {
					for (int l = j - w; l <= j + w; l++) {
						temp += LoG9x9[k - i + w][l - j + w] * (double) (blurNoisy[k][l]);
					}
				}

				if (temp < 0) {
					LoGImage[i][j] = Math.max((int) (temp - 0.5), -t);
				} else {
					LoGImage[i][j] = Math.min((int) Math.round(temp), t);
				}
			}
		}

		return LoGImage;
	}

	private static double[][] getGaussR(int[][] data, double sigma, int windSize, int offset) {

		final int center = windSize / 2;
		double[][] filter = new double[windSize][windSize];

		final int centerValue = data[center][center];

		/* initialization of filter kernel */
		for (int i = 0; i < windSize; i++) {
			for (int j = 0; j < windSize; j++) {
				filter[i][j] = Math.exp(-(Math.pow((double) (data[i][j] - centerValue - offset), 2) / (sigma * sigma)));
			}
		}

		return filter;
	}

	private static double[][] getGaussD(double sigma, int windSize) {

		final int center = windSize / 2;
		double[][] filter = new double[windSize][windSize];

		/* initialization of filter kernel */
		for (int i = -center; i <= center; i++) {
			for (int j = -center; j <= center; j++) {
				filter[i + center][j + center] = Math.exp(-((double) (i * i + j * j) / (2.0 * sigma * sigma)));
			}
		}

		return filter;
	}

	private final BufferedImage image;
	private KernelSize kernelSize;

	public FiltroBilateralAdaptativo(BufferedImage image, KernelSize kernelSize) {
		this.image = image;
		this.kernelSize = kernelSize;
	}

	public BufferedImage getBufferedImage() {

		final int width = image.getWidth();
		final int height = image.getHeight();
		final int area = width * height;
		final int[] pixelsOriginal = new int[area];

		PixelGrabber grabber = new PixelGrabber(image.getScaledInstance(width, height, 0), 0, 0, width, height, pixelsOriginal, 0, width);
		try {
			grabber.grabPixels();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		int[][] alpha = new int[width][height];
		int[][] red = new int[width][height];
		int[][] redOutput = new int[width][height];
		int[][] green = new int[width][height];
		int[][] greenOutput = new int[width][height];
		int[][] blue = new int[width][height];
		int[][] blueOutput = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int index = i + j * width;
				alpha[i][j] = ((pixelsOriginal[index] & 0xff000000) >> 24);
				red[i][j] = ((pixelsOriginal[index] & 0xff0000) >> 16);
				redOutput[i][j] = red[i][j];
				green[i][j] = ((pixelsOriginal[index] & 0xff00) >> 8);
				greenOutput[i][j] = green[i][j];
				blue[i][j] = ((pixelsOriginal[index] & 0xff));
				blueOutput[i][j] = blue[i][j];
			}
		}

		final int halfWindSize = WIND_SIZE / 2;
		final int LoGUB = CLASS_NO_LOG / 2;

		int[][][] inputs = new int[][][] { red, green, blue };
		int[][][] outputs = new int[][][] { redOutput, greenOutput, blueOutput };

		for (int index = 0; index < 3; index++) {

			int[][] input = inputs[index];
			int[][] output = outputs[index];

			int[][] LoGImage;
			if (kernelSize == KernelSize.kernel7x7) {
				LoGImage = applyLoG7x7(input, width, height, LoGUB);
			} else {
				LoGImage = applyLoG9x9(input, width, height, LoGUB);
			}

			int[][] window = new int[WIND_SIZE][WIND_SIZE];
			for (int idx = halfWindSize; idx < width - halfWindSize; idx++) {
				for (int jdx = halfWindSize; jdx < height - halfWindSize; jdx++) {
					/* get 7x7 window of data for training */
					for (int v = -halfWindSize; v <= halfWindSize; v++) {
						for (int w = -halfWindSize; w <= halfWindSize; w++) {
							window[v + halfWindSize][w + halfWindSize] = input[idx + v][jdx + w];
						}
					}
					final int clsLoG = LoGImage[idx][jdx] + LoGUB;

					double sigmaR = SIGMA_R[clsLoG];
					int offset = OFFSETS[clsLoG];

					double[][] gaussR = getGaussR(window, sigmaR, WIND_SIZE, offset);
					double[][] gaussD = getGaussD(SIGMA_D, WIND_SIZE);

					double sum = 0;
					double bilateralOut = 0;
					for (int i = 0; i < WIND_SIZE; i++) {
						for (int j = 0; j < WIND_SIZE; j++) {
							double bilateralF = gaussR[i][j] * gaussD[i][j];
							sum += bilateralF;
							bilateralOut += (double) window[i][j] * bilateralF;
						}
					}

					/* normalization */
					if (sum == 0) {
						bilateralOut = 0;
					} else {
						bilateralOut /= sum;
					}

					if (bilateralOut < 0) {
						output[idx][jdx] = 0;
					} else if (bilateralOut > 255) {
						output[idx][jdx] = 255;
					} else {
						output[idx][jdx] = (int) Math.round(bilateralOut);
					}
				}
			}
		}

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pixelsOriginal[i + j * width] = (alpha[i][j] << 24) | (redOutput[i][j] << 16) | (greenOutput[i][j] << 8) | blueOutput[i][j];
			}
		}

		BufferedImage returnImage = new BufferedImage(width, height, image.getType());

		returnImage.setRGB(0, 0, width, height, pixelsOriginal, 0, width);
		return returnImage;
	}

}
