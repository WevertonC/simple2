package simple.manipulacoes.util;

import java.awt.Point;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.renderable.ParameterBlock;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import javax.media.jai.Histogram;
import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.LookupTableJAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RasterFactory;
import javax.media.jai.TiledImage;

/**
 * Esta classe implementa um algoritmo de segmentação por crescimento de região.
 * @author Ricardo Madeira Fernandes
 */
public class RegionGrowingSegmentation extends ImageProcessing {
	// imagem de entrada e suas dimensoes.
	private PlanarImage input;
	private int width,height;
	//matriz com os valores de pixels
	private byte[][] pixels;
	private int[][] labels;
	// numero de passos do algoritmo ate um determinado momento
	private long position;
	//numero de regioes
	private int numberOfRegions;
	// contadores de pixels de cada regiao
	private Map<Integer,Integer> count;

	/**
	 * Construtor que pode pre-processar a imagem caso seja requerido.
	 * O pre-processamento inclui converter a imagem para o plano RGB,
	 * converter do RGB para tons de cinza e depois binarizar. Depois da imagem
	 * ser binarizada e aplicado um filtro para remover ruidos sal-and-pepper.
	 * @param im A imagem de entrada.
	 * @param invert Se quer inverter a imagem.
	 * @param preprocess Se quer pre-processar a imagem.
	 */
	public RegionGrowingSegmentation(PlanarImage im,boolean invert,boolean preprocess) {
		
		//se requerido, a imagem sera pre-processada
		if (preprocess) input = preprocess(im); 
		else input = im;
		if (invert)
			im = JAI.create("invert", im);
		Raster inputRaster = input.getData();
		//criar a estrutura de dados necessario para o algoritmo
		width = input.getWidth();
		height = input.getHeight();
		labels = new int[width][height];
		pixels = new byte[width][height];
		// preenche as estruturas de dados
		for(int h=0;h<height;h++)
			for(int w=0;w<width;w++)
			{
				pixels[w][h] = (byte)inputRaster.getSample(w,h,0);
				labels[w][h] = -1;
			}
		position = 0;
		count = new TreeMap<Integer, Integer>();
	}

	/*
	 * Converte uma imagem colorida para tons de cinza e depois para preto e branco
	 * limiarizando a imagem atraves do seu histograma.
	 */
	private PlanarImage preprocess(PlanarImage input)
	{
		//se a imagem for colorida converte para RGB
		if (input.getColorModel() instanceof IndexColorModel)
		{

			IndexColorModel icm = (IndexColorModel)input.getColorModel();

			int mapSize = icm.getMapSize();
			
			byte[][] lutData = new byte[3][mapSize];
			
			icm.getReds(lutData[0]);
			icm.getGreens(lutData[1]);
			icm.getBlues(lutData[2]);
			
			LookupTableJAI lut = new LookupTableJAI(lutData);
			//substitui a imagem original por uma convertida para RGB
			input = JAI.create("lookup", input, lut);
		}
		//verifica se precisa converter pra tons de cinza 
		if (input.getNumBands() > 1)
		{
			double[][] matrix = {{ 0.114, 0.587, 0.299, 0 }};
			ParameterBlock pb = new ParameterBlock();
			pb.addSource(input);
			pb.add(matrix);
			input = JAI.create("bandcombine", pb, null);
		}
		ParameterBlock pb = new ParameterBlock();
		pb.addSource(input);
		pb.add(null); // O ROI
		pb.add(1); pb.add(1); 
		pb.add(new int[]{256});
		pb.add(new double[]{0});
		pb.add(new double[]{256});
		//calcula o histograma da imagem
		PlanarImage dummyImage = JAI.create("histogram", pb);
		Histogram h = (Histogram)dummyImage.getProperty("histogram");
		double[] thresholds = h.getPTileThreshold(0.7); 

		pb = new ParameterBlock();
		pb.addSource(input);
		pb.add(thresholds[0]);

		//cria a imagem binarizada
		input = JAI.create("binarize", pb);

		float[] kernelMatrix =  { 0, 0, 0, 0, 0, 
				0, 0, 0, 0, 0, 
				0, 0, 1, 0, 0, 
				0, 0, 1, 0, 0, 
				0, 0, 0, 0, 0};

		KernelJAI kernel = new KernelJAI(5,5,kernelMatrix);

		ParameterBlock p = new ParameterBlock();
		p.addSource(input);
		p.add(kernel);

		input = JAI.create("dilate",p,null);

		p = new ParameterBlock();
		p.addSource(input);
		p.add(kernel);
		input = JAI.create("erode",p,null);

		p = new ParameterBlock();
		p.addSource(input);
		p.add(kernel);
		input = JAI.create("erode",p,null);
		p = new ParameterBlock();
		p.addSource(input);
		p.add(kernel);
		input = JAI.create("dilate",p,null);

		return input;
	}

	/**
	 * Executa o algoritmo de segmentacao por crescimento de regiao usando pilhas
	 * 1 - encontra um pixel que nao esta rotulado. roltula-o e armazena suas coordenadas
	 * em uma pilha.
	 * 2 - Enquanto existem pixels na pilha:
	 *  3 - desempilha um pixel
	 *  4 - verifica se seus vizinhos estao rotulados. se nao estao os rotula e
	 *  	coloca na pilha.
	 * 5 - repita os passos acima ate que nao tenha mais pixels para verificar.
	 */
	public void run()
	{
		numberOfRegions = 0;
		Stack<Point> mustDo = new Stack<Point>();
		for(int h=0;h<height;h++)
			for(int w=0;w<width;w++)
			{
				position++;
				// verifica se o pixel esta rotulado
				if (labels[w][h] < 0)
				{
					numberOfRegions++;
					mustDo.add(new Point(w,h));
					labels[w][h] = numberOfRegions; // rotula o pixel com a regiao que ele pertence
					count.put(numberOfRegions,1);
				}
				// verifica os pixels da pilha
				while(mustDo.size() > 0)
				{
					Point thisPoint = mustDo.get(0); mustDo.remove(0);
					// verifica 8-vizinhaca
					for(int th=-1;th<=1;th++)
						for(int tw=-1;tw<=1;tw++)
						{
							int rx = thisPoint.x+tw;
							int ry = thisPoint.y+th;
							// pula verificacao dos pixels fora da imagem.
							if ((rx < 0) || (ry < 0) || (ry>=height) || (rx>=width)) continue;
							if (labels[rx][ry] < 0) 
								if (pixels[rx][ry] == pixels[thisPoint.x][thisPoint.y])
								{ 
									mustDo.add(new Point(rx,ry));
									labels[rx][ry] = numberOfRegions;
									count.put(numberOfRegions, count.get(numberOfRegions)+1);
								}
						} // fim da verificacao da 8-vizinhaca
				} // fim da verificacao da pilha 
			} // fim da verificacao da image
		position = width*height;
	}

	/**
	 * Retorna o numero de regioes.
	 * @return O numero de regioes.
	 */
	public int getNumberOfRegions()
	{
		return numberOfRegions;
	}

	/**
	 * Retorna o numero de pixels de uma determinada regiao ou -1
	 * se o indice da regiao nao existe.
	 * @return O numero de pixels de uma determinada regiao. Se a regiao nao
	 * existe retorna -1.
	 */ 
	public int getPixelCount(int region)
	{
		Integer c = count.get(region);
		if (c == null) return -1; else return c;
	}

	/**
	 * Retorna o numero de passos para a segmentacao.
	 * @return O numero de passos para a segmentacao.
	 */
	public long getSize()
	{
		return width*height;
	}

	/**
	 * Retorna a posicao da imagem que esta sendo processada.
	 * @return A posicao da imagem que esta sendo processada.
	 */
	public long getPosition()
	{
		return position;
	}

	/**
	 * Verifica se a imagem ja foi processada.
	 * @return True se a imagem ja foi processada e false caso contrario.
	 */
	public boolean isFinished()
	{
		return (position == width*height);
	}

	/**
	 * Retorna a imagem de saida.
	 * @return A imagem de saida.
	 */
	public PlanarImage getOutput()
	{

		//cria uma nova imagem baseada no array de rotulos
		int[] imageDataSingleArray = new int[width*height];
		int count=0;
		for(int h=0;h<height;h++)
			for(int w=0;w<width;w++)
				imageDataSingleArray[count++] = labels[w][h];
		
		DataBufferInt dbuffer = new DataBufferInt(imageDataSingleArray,
				width*height);
		
		SampleModel sampleModel =
			RasterFactory.createBandedSampleModel(DataBuffer.TYPE_INT,width,height,1);

		ColorModel colorModel = PlanarImage.createColorModel(sampleModel);

		Raster raster = RasterFactory.createWritableRaster(sampleModel,dbuffer,new Point(0,0));

		TiledImage tiledImage = new TiledImage(0,0,width,height,0,0,sampleModel,colorModel);

		tiledImage.setData(raster);
		return tiledImage;
	}

	/**
	 * Retorna a imagem que esta sendo processada.
	 * @return A imagem que esta sendo processada.
	 */
	public PlanarImage getInternalImage() {
		return input;
	}
	

}
