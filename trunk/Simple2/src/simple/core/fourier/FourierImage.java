package simple.core.fourier;
/***********************************************************************************
ImageFFT.java - Computes the FFT of an image

Parte integrante do software 'Processamento de Imagens Digitais'

Descrição:

    Computes the FFT of an image, and the inverse FFT of its frequency domain 
    representation. The transformed data can be inspected or filtered before 
    performing the inverse transform.
    @author Nick Efford
    @version 1.3 [1999/08/09]
    @see FFTException
    @see java.awt.image.BufferedImage 

Informações do CVS:
       $Source$:
       $Revision$:
       $Date$:

***************************************************************************/

// pacote AWT
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Computes the FFT of an image, and the inverse FFT of its frequency
 * domain representation.  The transformed data can be inspected or
 * filtered before performing the inverse transform.
 *
 */

public class FourierImage {

  public static final int NO_WINDOW = 1;

  private static final String NO_DATA = "no spectral data available";
  private static final String INVALID_PARAMS = "invalid filter parameters";
  private static final double TWO_PI = 2.0*Math.PI;

  /** Complex storage for results of FFT. */
  private Complex[] data;

  /** base-2 logarithm of transform width. */
  private int log2w;

  /** base-2 logarithm of transform height. */
  private int log2h;

  /** Width of transform. */
  private int width;

  /** Height of transform. */
  private int height;

  /** Windowing function applied to image data. */
  private int window;

  /** Indicates whether we have spectral or spatial data. */
  private boolean spectral = false;
  
  private BufferedImage grayImage;

  /**
   * Creates an ImageFFT for the specified image, applying the
   * specified windowing function to the data.
   * @param image input image
   * @param win windowing function
   * @exception FourierException if the image is not 8-bit greyscale.
   */

  public FourierImage(BufferedImage image) throws FourierException {

	  
    if (image.getType() != BufferedImage.TYPE_BYTE_GRAY){
    	/*ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
    	ColorConvertOp op = new ColorConvertOp(cs, null);
    	grayImage = op.filter(image, null); */
    	//areaImagem.imagem = imagemCinza;
    	//http://www.arquivodecodigos.net/arquivo/visualizar_dica.php?qual_dica=2149
    	throw new FourierException("image must be 8-bit greyscale");
    	
    } else {
    	grayImage = image;
    }
       
    // Compute dimensions, allowing for zero padding
    log2w = powerOfTwo(grayImage.getWidth());
    log2h = powerOfTwo(grayImage.getHeight());
    width = 1 << log2w;
    height = 1 << log2h;

    // Allocate storage for results of FFT

    data = new Complex[width*height];
    for (int i = 0; i < data.length; ++i)
      data[i] = new Complex();

    Raster raster = grayImage.getRaster();
    double xc = grayImage.getWidth()/2.0, yc = grayImage.getHeight()/2.0;
    double r, rmax = Math.min(xc, yc);
  
        for (int y = 0; y < grayImage.getHeight(); ++y)
          for (int x = 0; x < grayImage.getWidth(); ++x)
            data[y*width+x].real = raster.getSample(x, y, 0);

    }

  /**
   * @return width of FFT.
   */

  public int getWidth() {
    return width;
  }


  /**
   * @return height of FFT.
   */

  public int getHeight() {
    return height;
  }


  /**
   * @return current windowing function.
   */

  public int getWindow() {
    return window;
  }

  /**
   * @return true if data are spectral, false if data are spatial.
   */

  public boolean isSpectral() {
    return spectral;
  }

  /**
   * @return information string for an ImageFFT object.
   */

  public String toString() {
    String s = new String(getClass().getName() + ": " + width + "x" + height +
     (spectral ? ", frequency domain" : ", spatial domain"));
    return s;
  }

  /**
   * Transforms data via a forward or inverse FFT, as appropriate.
   * An inverse transform is computed if the previous transform was
   * in the forward direction; otherwise, the forward transform
   * is computed.
   */

  public void transform() {

    int x, y, i;
    Complex[] row = new Complex[width];
    for (x = 0; x < width; ++x)
      row[x] = new Complex();
    Complex[] column = new Complex[height];
    for (y = 0; y < height; ++y)
      column[y] = new Complex();

    int direction;
    if (spectral)
      direction = -1;   // inverse transform
    else
      direction = 1;    // forward transform

    // Perform FFT on each row

    for (y = 0; y < height; ++y) {
      for (i = y*width, x = 0; x < width; ++x, ++i) {
        row[x].real = data[i].real;
        row[x].im = data[i].im;
      }
      reorder(row, width);
      fft(row, width, log2w, direction);
      for (i = y*width, x = 0; x < width; ++x, ++i) {
        data[i].real = row[x].real;
        data[i].im = row[x].im;
      }
    }

    // Perform FFT on each column

    for (x = 0; x < width; ++x) {
      for (i = x, y = 0; y < height; ++y, i += width) {
        column[y].real = data[i].real;
        column[y].im = data[i].im;
      }
      reorder(column, height);
      fft(column, height, log2h, direction);
      for (i = x, y = 0; y < height; ++y, i += width) {
        data[i].real = column[y].real;
        data[i].im = column[y].im;
      }
    }

    if (spectral)
      spectral = false;
    else
      spectral = true;

  }

  /**
   * Converts stored data into an image.
   * @param image destination image, or null
   * @return FFT data as an image.
   * @exception FourierException if the data are in spectral form; an
   *  image can be created only from data in the spatial domain.
   */

  public BufferedImage toImage(BufferedImage image) throws FourierException {
    return toImage(image, 0);
  }

  /**
   * Converts stored data into an image.
   * @param image destination image, or null
   * @param bias constant value added to data
   * @return FFT data as an image.
   * @exception FourierException if the data are in spectral form; an
   *  image can be created only from data in the spatial domain.
   */

  public BufferedImage toImage(BufferedImage image, int bias)
   throws FourierException {

    if (spectral)
      throw new FourierException("cannot transfer spectral data to an image");

    if (image == null)
      image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    WritableRaster raster = image.getRaster();

    int w = Math.min(image.getWidth(), width);
    int h = Math.min(image.getHeight(), height);

    // If destination image is bigger, zero it

    if (w < image.getWidth() || h < image.getHeight())
      for (int y = 0; y < image.getHeight(); ++y)
        for (int x = 0; x < image.getWidth(); ++x)
          raster.setSample(x, y, 0, 0);

    // Copy real component of data to destination image

    int i = 0, value;
    for (int y = 0; y < height; ++y)
      for (int x = 0; x < width; ++x, ++i) {
        value = Math.max(0, Math.min(255, bias + Math.round(data[i].real)));
        raster.setSample(x, y, 0, value);
      }

    return image;

  }


  /**
   * Returns the amplitude spectrum of an image, as another image.
   * The data are shifted such that the DC component is at the image
   * centre, and scaled logarithmically so that low-amplitude
   * detail is visible.
   * @return shifted spectrum, as an image.
   * @exception FourierException if spectral data are not available
   * (e.g. because last transform was in the inverse direction).
   */

  public BufferedImage getSpectrum() throws FourierException {

    if (!spectral)
      throw new FourierException(NO_DATA);

    // Collect magnitudes and find maximum

    float[] magData = new float[width*height];
    float maximum = calculateMagnitudes(magData);
    BufferedImage image =
     new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    WritableRaster raster = image.getRaster();

    // Shift, rescale and copy to image

    double scale = 255.0 / Math.log(maximum + 1.0);
    int x2 = width/2, y2 = height/2;
    int sx, sy, value;
    for (int y = 0; y < height; ++y) {
      sy = shift(y, y2);
      for (int x = 0; x < width; ++x) {
        sx = shift(x, x2);
        value = (int) Math.round(scale*Math.log(magData[sy*width+sx]+1.0));
        raster.setSample(x, y, 0, value);
      }
    }

    return image;

  }


  /**
   * Returns the amplitude spectrum of an image, as another image.
   * The data are unshifted and are scaled logarithmically so that
   * low-amplitude detail is visible.
   * @return unshifted spectrum, as an image.
   * @exception FourierException if spectral data are not available
   * e.g. because last transform was in the inverse direction).
   */

  public BufferedImage getUnshiftedSpectrum() throws FourierException {

    if (!spectral)
      throw new FourierException(NO_DATA);

    // Collect magnitudes and find maximum

    float[] magData = new float[width*height];
    float maximum = calculateMagnitudes(magData);
    BufferedImage image =
     new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    WritableRaster raster = image.getRaster();

    // Rescale and copy to image

    double scale = 255.0 / Math.log(maximum + 1.0);
    int i = 0, value;
    for (int y = 0; y < height; ++y)
      for (int x = 0; x < width; ++x, ++i) {
        value = (int) Math.round(scale*Math.log(magData[i]+1.0));
        raster.setSample(x, y, 0, value);
      }

    return image;

  }


  /**
   * Computes magnitude for any point in the spectrum.
   * @param u horizontal spatial frequency
   * @param v vertical spatial frequency
   * @return magnitude at the specified point, or zero if point
   * does not exist.
   * @exception FourierException if spectral data are not available.
   */

  public float getMagnitude(int u, int v) throws FourierException {
    if (!spectral)
      throw new FourierException(NO_DATA);
    if (u >= 0 && u < width && v >= 0 && v < height)
      return data[v*width+u].getMagnitude();
    else
      return 0.0f;
  }


  /**
   * Computes phase for any point in the spectrum.
   * @param u horizontal spatial frequency
   * @param v vertical spatial frequency
   * @return phase at the specified point, or zero if point does not exist.
   * @exception FourierException if spectral data are not available.
   */

  public float getPhase(int u, int v) throws FourierException {
    if (!spectral)
      throw new FourierException(NO_DATA);
    if (u >= 0 && u < width && v >= 0 && v < height)
      return data[v*width+u].getPhase();
    else
      return 0.0f;
  }


  /**
   * Modifies magnitude at any point in the spectrum.
   * @param u horizontal spatial frequency
   * @param v vertical spatial frequency
   * @param mag new magnitiude for specified point
   * @exception FourierException if spectral data are not available.
   */

  public void setMagnitude(int u, int v, float mag) throws FourierException {
    if (!spectral)
      throw new FourierException(NO_DATA);
    if (u >= 0 && u < width && v >= 0 && v < height) {
      int i = v*width+u;
      data[i].setPolar(mag, data[i].getPhase());
    }
  }


  /**
   * Modifies phase at any point in the spectrum.
   * @param u horizontal spatial frequency
   * @param v vertical spatial frequency
   * @param phase new phase for specified point
   * @exception FourierException if spectral data are not available.
   */

  public void setPhase(int u, int v, float phase) throws FourierException {
    if (!spectral)
      throw new FourierException(NO_DATA);
    if (u >= 0 && u < width && v >= 0 && v < height) {
      int i = v*width+u;
      data[i].setPolar(data[i].getMagnitude(), phase);
    }
  }

  /**
   * Computes the power of two for which the corresponding value
   * equals or exceeds the specified integer.
   * @param n integer value
   * @return power of two
   */

  private static int powerOfTwo(int n) {
   int i = 0, m = 1;
   while (m < n) {
     m <<= 1;
     ++i;
   }
   return i;
  }


  /**
   * Reorders an array of data to prepare for an FFT.  The element at
   * index i is swapped with the element at an index given by the
   * bit-reversed value of i.
   * @param data array of complex values
   * @param n number of values
   */

  private static void reorder(Complex[] data, int n) {
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
   * Performs a one-dimensional FFT on the specified data.
   * @param data input data, already reordered by bit-reversal
   * @param size number of data elements
   * @param log2n base-2 logarithm of number of elements
   * @param dir direction of transform (1 = forward, -1 = inverse)
   */

  private static void fft(Complex[] data, int size, int log2n, int dir) {

    double angle, wtmp, wpr, wpi, wr, wi, tmpr, tmpi;
    int n = 1, n2;
    for (int k = 0; k < log2n; ++k) {

      n2 = n;
      n <<= 1;
      angle = (-TWO_PI/n) * dir;
      wtmp = Math.sin(0.5*angle);
      wpr = -2.0*wtmp*wtmp;
      wpi = Math.sin(angle);
      wr = 1.0;
      wi = 0.0;

      for (int m = 0; m < n2; ++m) {
        for (int i = m; i < size; i += n) {
          int j = i + n2;
          tmpr = wr*data[j].real - wi*data[j].im;
          tmpi = wi*data[j].real + wr*data[j].im;
          data[j].real = (float) (data[i].real - tmpr);
          data[i].real += (float) tmpr;
          data[j].im = (float) (data[i].im - tmpi);
          data[i].im += (float) tmpi;
        }
        wtmp = wr;
        wr = wtmp*wpr - wi*wpi + wr;
        wi = wi*wpr + wtmp*wpi + wi;
      }

    }

    // Rescale results of inverse transform

    if (dir == -1)
      for (int i = 0; i < size; ++i) {
        data[i].real /= size;
        data[i].im /= size;
      }

  }


  /**
   * Shifts a coordinate relative to a centre point.
   * @param d coordinate
   * @param d2 centre point
   * @return shifted coordinate.
   */

  private static final int shift(int d, int d2) {
    return (d >= d2 ? d-d2 : d+d2);
  }


  /**
   * Collects magnitudes from spectral data, storing them
   * in the specified array.
   * @param mag destination for magnitudes
   * @return maximum magnitude.
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
