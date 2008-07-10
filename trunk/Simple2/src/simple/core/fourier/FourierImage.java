package simple.core.fourier;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Representa a imagem de Fourier (imagem no dominio da frequencia).
 * 
 * @author elloa
 * @author odilon
 *
 */
public class FourierImage {

	private static final String NO_DATA = "a imagem deve ser transformada para o dominio da fourier antes de executar essa operacao";

	private static final double DOIS_PI = 2.0 * Math.PI;

	/** dados da imagem. armazena o resultado da FFT */
	private Complexo[] data;

	/** logaritmo base-2 do comprimento */
	private int log2w;

	/** logaritmo base-2 da altura */
	private int log2h;

	/** comprimento da imagem no dominio de Fourier */
	private int width;

	/** altura da image no dominio de Fourier */
	private int height;

	/** indica se os dados estao no dominio espectral ou espacial */
	private boolean spectral = false;

	/** imagem em escala de cinza */
	private BufferedImage grayImage;

	/**
	 * Constroi FourierImage para uma imagem dada.
	 * @param image 
	 */
	public FourierImage(BufferedImage image) {

		if (image.getType() != BufferedImage.TYPE_BYTE_GRAY) {
			
			 ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			 ColorConvertOp op = new ColorConvertOp(cs, null); grayImage =
			 op.filter(image, null);
			 
			//throw new FourierException("a imagem deve ser em tom de cinza - 8-bit");

		} else {
			grayImage = image;
		}

		log2w = potenciaDeDois(grayImage.getWidth());
		log2h = potenciaDeDois(grayImage.getHeight());
		width = 1 << log2w;
		height = 1 << log2h;

		data = new Complexo[width * height];
		for (int i = 0; i < data.length; ++i)
			data[i] = new Complexo();

		Raster raster = grayImage.getRaster();

		for (int y = 0; y < grayImage.getHeight(); ++y)
			for (int x = 0; x < grayImage.getWidth(); ++x)
				data[y * width + x].real = raster.getSample(x, y, 0);

	}

	/**
	 * Retorna o comprimento da imagem no dominio de Fourier.
	 * @return comprimento da imagem.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Retorna a altura da imagem no dominio de Fourier.
	 * @return altura da imagem
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Retorna se os dados estao representados no dominio espectral ou espacial
	 * @return true se os dados estao no dominio espectral, caso contrario falso.
	 */
	public boolean isSpectral() {
		return spectral;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = new String(getClass().getName() + ": " + width + "x"
				+ height
				+ (spectral ? ", dominio da frequencia " : ", dominio espacial"));
		return s;
	}

	/**
	 * Transforma os dados da imagem atraves da FFT direta ou inversa.
	 * A transformada inversa é computada se a transformaçao anterior 
	 * foi feita, caso contrario a tranformação direta é computada.
	 */
	public void transform() {

		int x, y, i;
		Complexo[] linha = new Complexo[width];
		for (x = 0; x < width; ++x)
			linha[x] = new Complexo();
		Complexo[] coluna = new Complexo[height];
		for (y = 0; y < height; ++y)
			coluna[y] = new Complexo();

		int direction;
		if (spectral)
			direction = -1; // transformada inversa
		else
			direction = 1; // transformada

		// Executa a FFT em cada linha
		for (y = 0; y < height; ++y) {
			for (i = y * width, x = 0; x < width; ++x, ++i) {
				linha[x].real = data[i].real;
				linha[x].im = data[i].im;
			}
			reorder(linha, width);
			fft(linha, width, log2w, direction);
			for (i = y * width, x = 0; x < width; ++x, ++i) {
				data[i].real = linha[x].real;
				data[i].im = linha[x].im;
			}
		}

		// Executa a FFT em cada coluna
		for (x = 0; x < width; ++x) {
			for (i = x, y = 0; y < height; ++y, i += width) {
				coluna[y].real = data[i].real;
				coluna[y].im = data[i].im;
			}
			reorder(coluna, height);
			fft(coluna, height, log2h, direction);
			for (i = x, y = 0; y < height; ++y, i += width) {
				data[i].real = coluna[y].real;
				data[i].im = coluna[y].im;
			}
		}

		if (spectral)
			spectral = false;
		else
			spectral = true;

	}

	/**
	 * Converte os dados armazenados em uma image.
	 * @param image imagem destino, ou null.
	 * @return FFT dos dados armazenados como uma imagem. 
	 * @throws FourierException se os dados estao em forma espectral.
	 * 	uma imagem pode ser criada apenas dos dados no dominio espacial.
	 */
	public BufferedImage toImage(BufferedImage image) throws FourierException {
		return toImage(image, 0);
	}

	/**
	 * Converte os dados armazenados em uma imagem.
	 * @param image imagem destino, ou null.
	 * @param bias valor constante adicionada aos dados.
	 * @throws FourierException se os dados estao em forma espectral.
	 * 	uma imagem pode ser criada apenas dos dados no dominio espacial.
	 */
	public BufferedImage toImage(BufferedImage image, int bias)
			throws FourierException {

		if (spectral)
			throw new FourierException("cannot transfer spectral data to an image");

		if (image == null)
			image = new BufferedImage(width, height,
					BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = image.getRaster();

		int w = Math.min(image.getWidth(), width);
		int h = Math.min(image.getHeight(), height);

		// Se a imagem destino é maior, seta cada pixel como zero
		if (w < image.getWidth() || h < image.getHeight())
			for (int y = 0; y < image.getHeight(); ++y)
				for (int x = 0; x < image.getWidth(); ++x)
					raster.setSample(x, y, 0, 0);

		// Copia o componente real dos dados para a imagem destino
		int i = 0, value;
		for (int y = 0; y < height; ++y)
			for (int x = 0; x < width; ++x, ++i) {
				value = Math.max(0, Math.min(255, bias
						+ Math.round(data[i].real)));
				raster.setSample(x, y, 0, value);
			}

		return image;

	}

	/**
	 * Retorna a amplitude do espectro de uma imagem como uma outra imagem.
	 * Os dados são deslocados. 
	 * @return espectro deslocado como uma imagem.
	 * @throws FourierException se os dados espectrais nao estiverem disponivel.
	 */
	public BufferedImage getSpectrum() throws FourierException {

		if (!spectral)
			throw new FourierException(NO_DATA);

		// Calcula a magnitude e encontra o máximo
		float[] magData = new float[width * height];
		float maximum = calculateMagnitudes(magData);
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = image.getRaster();

		double scale = 255.0 / Math.log(maximum + 1.0);
		int x2 = width / 2, y2 = height / 2;
		int sx, sy, value;
		for (int y = 0; y < height; ++y) {
			sy = shift(y, y2);
			for (int x = 0; x < width; ++x) {
				sx = shift(x, x2);
				value = (int) Math.round(scale
						* Math.log(magData[sy * width + sx] + 1.0));
				raster.setSample(x, y, 0, value);
			}
		}

		return image;

	}

	/**
	 * Retorna a amplitude do espectro de uma imagem como uma outra imagem.
	 * Os dados nao sao deslocados.
	 * @return espectro nao deslocado como uma imagem.
	 * @throws FourierException se os dados espectrais nao estiverem disponiveis
	 */
	public BufferedImage getUnshiftedSpectrum() throws FourierException {

		if (!spectral)
			throw new FourierException(NO_DATA);

		// Calcula a magnitude e encontra o máximo
		float[] magData = new float[width * height];
		float maximum = calculateMagnitudes(magData);
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = image.getRaster();

		double scale = 255.0 / Math.log(maximum + 1.0);
		int i = 0, value;
		for (int y = 0; y < height; ++y)
			for (int x = 0; x < width; ++x, ++i) {
				value = (int) Math.round(scale * Math.log(magData[i] + 1.0));
				raster.setSample(x, y, 0, value);
			}

		return image;

	}
	
	/**
	 * Computa a magnitude para um ponto qualquer do espectro.
	 * @param u frequencia espacial horizontal
	 * @param v frequencia espacial vertical
	 * @return magnitude do ponto especificado ou zero se o ponto nao existe.
	 * @throws FourierException se os dados espectrais nao estiverem disponiveis 
	 */
	public float getMagnitude(int u, int v) throws FourierException {
		if (!spectral)
			throw new FourierException(NO_DATA);
		if (u >= 0 && u < width && v >= 0 && v < height)
			return data[v * width + u].getMagnitude();
		else
			return 0.0f;
	}
	
	/**
	 * Computa a fase para um ponto qualquer do espectro.
	 * @param u frequencia espacial horizontal 
	 * @param v frequencia espacial vertical
	 * @return a fase do ponto especificado ou zero se o ponto nao existe.
	 * @throws FourierException se os dados espectrais nao estiverem disponiveis
	 */
	public float getFase(int u, int v) throws FourierException {
		if (!spectral)
			throw new FourierException(NO_DATA);
		if (u >= 0 && u < width && v >= 0 && v < height)
			return data[v * width + u].getFase();
		else
			return 0.0f;
	}
	
	/**
	 * Modifica a magnitude de um ponto qualquer do espectro.
	 * @param u frequencia espacial horizontal
	 * @param v frequencia espacial vertical
	 * @param mag nova magnitude para o ponto especificado
	 * @throws FourierException se os dados espectrais nao estiverem disponiveis
	 */
	public void setMagnitude(int u, int v, float mag) throws FourierException {
		if (!spectral)
			throw new FourierException(NO_DATA);
		if (u >= 0 && u < width && v >= 0 && v < height) {
			int i = v * width + u;
			data[i].setPolar(mag, data[i].getFase());
		}
	}
	
	/**
	 * Modifica a fase de um ponto qualquer do espectro.
	 * @param u frequencia espacial horizontal
	 * @param v frequencia espacial vertical
	 * @param fase nova fase para o ponto especificado
	 * @throws FourierException se os dados espectrais nao estiverem disponiveis
	 */
	public void setFase(int u, int v, float fase) throws FourierException {
		if (!spectral)
			throw new FourierException(NO_DATA);
		if (u >= 0 && u < width && v >= 0 && v < height) {
			int i = v * width + u;
			data[i].setPolar(data[i].getMagnitude(), fase);
		}
	}
	
	/**
	 * Calcula a pontencia de dois correspondente ao valor igual ou maior 
	 * do inteiro especificado.
	 * @param n valor inteiro 
	 * @return potencia de dois
	 */
	private static int potenciaDeDois(int n) {
		int i = 0, m = 1;
		while (m < n) {
			m <<= 1;
			++i;
		}
		return i;
	}

	/**
	 * Reordena um array de dados para prepara para a FFT.
	 * O elemento no index i é trocado com o elemento no 
	 * indice dado pelo valor do bit-reverso de i.
	 * @param data array of valores complexos.
	 * @param n numero de valores
	 */
	private static void reorder(Complexo[] data, int n) {
		int j = 0, m;
		for (int i = 0; i < n; ++i) {
			if (i > j)
				data[i].swapWith(data[j]);
			m = n >> 1;
			while ((j >= m) && (m >= 2)) {
				j -= m;
				m >>= 1;
			}
			j += m;
		}
	}
	
	/**
	 * Executa a FFT em uma dimensao nos dados especificados.
	 * @param data dados de entrada (ja ordenados por bit-reverso)
	 * @param size numero de elementos do array
	 * @param log2n logaritmo base-2 do numero de elementos
	 * @param dir direcao da transformada (1 = direta, -1 inversa)
	 */
	private static void fft(Complexo[] data, int size, int log2n, int dir) {

		double angulo, wtmp, wpr, wpi, wr, wi, tmpr, tmpi;
		int n = 1, n2;
		for (int k = 0; k < log2n; ++k) {

			n2 = n;
			n <<= 1;
			angulo = (-DOIS_PI / n) * dir;
			wtmp = Math.sin(0.5 * angulo);
			wpr = -2.0 * wtmp * wtmp;
			wpi = Math.sin(angulo);
			wr = 1.0;
			wi = 0.0;

			for (int m = 0; m < n2; ++m) {
				for (int i = m; i < size; i += n) {
					int j = i + n2;
					tmpr = wr * data[j].real - wi * data[j].im;
					tmpi = wi * data[j].real + wr * data[j].im;
					data[j].real = (float) (data[i].real - tmpr);
					data[i].real += (float) tmpr;
					data[j].im = (float) (data[i].im - tmpi);
					data[i].im += (float) tmpi;
				}
				wtmp = wr;
				wr = wtmp * wpr - wi * wpi + wr;
				wi = wi * wpr + wtmp * wpi + wi;
			}

		}

		if (dir == -1)
			for (int i = 0; i < size; ++i) {
				data[i].real /= size;
				data[i].im /= size;
			}

	}

	/**
	 * Desloca a coordenada relativa ao ponto central
	 * @param d coordenada
	 * @param d2 ponto central
	 * @return coordenada deslocada
	 */
	private static final int shift(int d, int d2) {
		return (d >= d2 ? d - d2 : d + d2);
	}

	/**
	 * Extrai a magnitude dos dados espectrais. 
	 * Armazenando-os em um array dado
	 * @param mag array que armazena os dados espectrais
	 * @return magnitude maxima
	 */
	private float calculateMagnitudes(float[] mag) {
		float maximum = 0.0f;
		for (int i = 0; i < data.length; ++i) {
			mag[i] = data[i].getMagnitude();
			if (mag[i] > maximum)
				maximum = mag[i];
		}
		return maximum;
	}
}
