package simple.modules.fourier.core;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import simple.modules.fourier.exceptions.FourierException;



/**
 * Representa a imagem de Fourier, ou seja, uma imagem no domínio da freqüência.
 * 
 * @author Elloa B. Guedes - elloa@dsc.ufcg.edu.br
 * @author Odilon F. Lima Junior - odilonflj@dsc.ufcg.edu.br
 *
 */
public class FourierImagem {

	private static final double DOIS_PI = 2.0 * Math.PI;

	/** dados da imagem. armazena o resultado da FFT */
	private Complexo[] data;

	/** logaritmo base-2 do comprimento */
	private int log2L;

	/** logaritmo base-2 da altura */
	private int log2A;

	/** comprimento da imagem no dominio de Fourier */
	private int largura;

	/** altura da image no dominio de Fourier */
	private int altura;

	/** indica se os dados estao no dominio espectral ou espacial */
	private boolean espectral = false;

	/** imagem em escala de cinza. É preferível que a aplicação da Transformada de Fourier aconteça
	 * em  imagens nesta escala de tons.
	 */
	private BufferedImage grayImage;

	/**
	 * Construtor vazio default.
	 */
	public FourierImagem(){

	}

	/**
	 * Constroi FourierImage para uma imagem de entrada no domínio do espaço.
	 * @param image a imagem de entrada, que está no domínio do espaço.
	 * @throws FourierException  caso a imagem não tenha altura e largura iguais.
	 */
	public FourierImagem(BufferedImage image) throws FourierException {


		if (image.getHeight() != image.getWidth()) {
			throw new FourierException(FourierException.DIMENSAO_IMAGEM);
		}

		// A imagem é convertida para escala de cinza.
		if (image.getType() != BufferedImage.TYPE_BYTE_GRAY) {
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			ColorConvertOp op = new ColorConvertOp(cs, null); grayImage =
				op.filter(image, null);
		} else {
			grayImage = image;
		}

		log2L = potenciaDeDois(grayImage.getWidth());
		log2A = potenciaDeDois(grayImage.getHeight());
		largura = 1 << log2L;
		altura = 1 << log2A;

		/*
		 * Instancia um número complexo para cada pixel da imagem
		 */
		data = new Complexo[largura * altura];
		for (int i = 0; i < data.length; ++i)
			data[i] = new Complexo();

		Raster raster = grayImage.getRaster();

		/*
		 * Ao valor de cada pixel é associada a parte real de um número complexo atribuído anteriormente.
		 */
		for (int y = 0; y < grayImage.getHeight(); ++y)
			for (int x = 0; x < grayImage.getWidth(); ++x)
				data[y * largura + x].real = raster.getSample(x, y, 0);

	}

	/**
	 * Retorna o comprimento da imagem no dominio de Fourier.
	 * @return comprimento da imagem.
	 */
	public int getLargura() {
		return largura;
	}

	/**
	 * Retorna a altura da imagem no dominio de Fourier.
	 * @return altura da imagem
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * Retorna se os dados estao representados no dominio espectral ou espacial
	 * @return true se os dados estao no dominio espectral, caso contrario falso.
	 */
	public boolean isEspectral() {
		return espectral;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = new String(getClass().getName() + ": " + largura + "x"
				+ altura
				+ (espectral ? ", dominio da frequencia " : ", dominio espacial"));
		return s;
	}

	/**
	 * Transforma os dados da imagem atraves da FFT direta ou inversa.
	 * A transformada inversa é computada se a transformaçao anterior 
	 * foi feita, caso contrario a tranformação direta é computada.
	 */
	public void transform() {

		int x, y, i;
		Complexo[] linha = new Complexo[largura];
		for (x = 0; x < largura; ++x)
			linha[x] = new Complexo();
		Complexo[] coluna = new Complexo[altura];
		for (y = 0; y < altura; ++y)
			coluna[y] = new Complexo();

		int direction;
		if (espectral)
			direction = -1; // transformada inversa
		else
			direction = 1; // transformada

		// Executa a FFT em cada linha
		for (y = 0; y < altura; ++y) {
			for (i = y * largura, x = 0; x < largura; ++x, ++i) {
				linha[x].real = data[i].real;
				linha[x].im = data[i].im;
			}
			reordena(linha, largura);
			fft(linha, largura, log2L, direction);
			for (i = y * largura, x = 0; x < largura; ++x, ++i) {
				data[i].real = linha[x].real;
				data[i].im = linha[x].im;
			}
		}

		// Executa a FFT em cada coluna
		for (x = 0; x < largura; ++x) {
			for (i = x, y = 0; y < altura; ++y, i += largura) {
				coluna[y].real = data[i].real;
				coluna[y].im = data[i].im;
			}
			reordena(coluna, altura);
			fft(coluna, altura, log2A, direction);
			for (i = x, y = 0; y < altura; ++y, i += largura) {
				data[i].real = coluna[y].real;
				data[i].im = coluna[y].im;
			}
		}

		if (espectral)
			espectral = false;
		else
			espectral = true;

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

		if (espectral)
			throw new FourierException("cannot transfer spectral data to an image");

		if (image == null)
			image = new BufferedImage(largura, altura,
					BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = image.getRaster();

		int w = Math.min(image.getWidth(), largura);
		int h = Math.min(image.getHeight(), altura);

		// Se a imagem destino é maior, seta cada pixel como zero
		if (w < image.getWidth() || h < image.getHeight())
			for (int y = 0; y < image.getHeight(); ++y)
				for (int x = 0; x < image.getWidth(); ++x)
					raster.setSample(x, y, 0, 0);

		// Copia o componente real dos dados para a imagem destino
		int i = 0, value;
		for (int y = 0; y < altura; ++y)
			for (int x = 0; x < largura; ++x, ++i) {
				value =(int) Math.max(0, Math.min(255, bias
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
	public BufferedImage getEspectro() throws FourierException {

		if (!espectral)
			throw new FourierException(FourierException.DOMINIO_FREQUENCIA);

		// Calcula a magnitude e encontra o máximo
		double[] magData = new double[largura * altura];
		double maximum = calculateMagnitudes(magData);

		// imagem a ser retornada
		BufferedImage image = new BufferedImage(largura, altura,
				BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = image.getRaster();

		// Converte os numeros complexos em pixels no dominio do espaço, para que a imagem
		// possua uma representação visual compreensível.
		double escala = 255.0 / Math.log(maximum + 1.0);
		int x2 = largura / 2, y2 = altura / 2;
		int sx, sy, valor;
		for (int y = 0; y < altura; ++y) {
			sy = shift(y, y2);
			for (int x = 0; x < largura; ++x) {
				sx = shift(x, x2);
				valor = (int) Math.round(escala
						* Math.log(magData[sy * largura + sx] + 1.0));
				raster.setSample(x, y, 0, valor);
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
	public BufferedImage getUnshiftedEspectro() throws FourierException {

		if (!espectral)
			throw new FourierException(FourierException.DOMINIO_FREQUENCIA);

		// Calcula a magnitude e encontra o máximo
		double[] magData = new double[largura * altura];
		double maximum = calculateMagnitudes(magData);
		BufferedImage image = new BufferedImage(largura, altura,
				BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = image.getRaster();

		double escala = 255.0 / Math.log(maximum + 1.0);
		int i = 0, valor;
		for (int y = 0; y < altura; ++y)
			for (int x = 0; x < largura; ++x, ++i) {
				valor = (int) Math.round(escala * Math.log(magData[i] + 1.0));
				raster.setSample(x, y, 0, valor);
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
	public double getMagnitude(int u, int v) throws FourierException {
		if (!espectral)
			throw new FourierException(FourierException.DOMINIO_FREQUENCIA);
		if (u >= 0 && u < largura && v >= 0 && v < altura)
			return data[v * largura + u].getMagnitude();
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
	public double getFase(int u, int v) throws FourierException {
		if (!espectral)
			throw new FourierException(FourierException.DOMINIO_FREQUENCIA);
		if (u >= 0 && u < largura && v >= 0 && v < altura)
			return data[v * largura + u].getFase();
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
	public void setMagnitude(int u, int v, double mag) throws FourierException {
		if (!espectral)
			throw new FourierException(FourierException.DOMINIO_FREQUENCIA);
		if (u >= 0 && u < largura && v >= 0 && v < altura) {
			int i = v * largura + u;
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
	public void setFase(int u, int v, double fase) throws FourierException {
		if (!espectral)
			throw new FourierException(FourierException.DOMINIO_FREQUENCIA);
		if (u >= 0 && u < largura && v >= 0 && v < altura) {
			int i = v * largura + u;
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
	private static void reordena(Complexo[] data, int n) {
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
					data[j].real = (double) (data[i].real - tmpr);
					data[i].real += (double) tmpr;
					data[j].im = (double) (data[i].im - tmpi);
					data[i].im += (double) tmpi;
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
	public static final int shift(int d, int d2) {
		return (d >= d2 ? d - d2 : d + d2);
	}

	/**
	 * Extrai a magnitude dos dados espectrais. 
	 * Armazenando-os em um array dado
	 * @param mag array que armazena os dados espectrais
	 * @return magnitude maxima
	 */
	private double calculateMagnitudes(double[] mag) {
		double maximum = 0.0f;
		for (int i = 0; i < data.length; ++i) {
			mag[i] = data[i].getMagnitude();
			if (mag[i] > maximum)
				maximum = mag[i];
		}
		return maximum;
	}

	/**
	 * Retorna o valor complexo da imagem no domínio da freqüência de acordo com o índice passado como parâmetro
	 * @param i o índice.
	 * @return o valor indicado pelo índice.
	 */
	public Complexo getData(int i){
		return data[i];
	}

	/**
	 * Modifica o valor complexo da imagem no domínio da freqüência.
	 * @param c o novo valor complexo.
	 * @param i o índice cujo conteúdo será modificado.
	 */
	public void setData(Complexo c, int i){
		data[i] = c;
	}

	/**
	 * Retorna a imagem de entrada em escala de cinza
	 * @return a imagem de entrada em escala de cinza
	 */
	public BufferedImage getGrayImage() {
		return grayImage;
	}

	/**
	 * Modifica a imagem de entrada, que está na escala de cinza
	 * @param grayImage a nova imagem, em escala de cinza
	 */
	public void setGrayImage(BufferedImage grayImage) {
		this.grayImage = grayImage;
	}
	
	public Complexo[] getData(){
		return data;
	}
	
	public void diferenca(FourierImagem f0){
		
		for (int i = 0; i < f0.getAltura()*getLargura(); i ++){
			this.data[i] = data[i].menos(f0.getData()[i]);
		}
		
	}


}
