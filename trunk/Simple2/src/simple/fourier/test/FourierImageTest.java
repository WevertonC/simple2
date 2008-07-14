package simple.fourier.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import simple.fourier.core.FourierImage;
import simple.fourier.exceptions.FourierException;
import junit.framework.TestCase;

public class FourierImageTest extends TestCase {

	BufferedImage src;
	FourierImage f0;
	
	public void startUp(){
		try {
			src = ImageIO.read(new File("./images/lena-gray.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			f0 = new FourierImage(src);
		} catch (FourierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testFourierImage() {

		try {
			src = ImageIO.read(new File("./images/lena-gray.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		FourierImage f = new FourierImage();
		try {
			f = new FourierImage(src);
		} catch (FourierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(f);
		
		/* Imagem com problemas de dimensão */
		f = null;

		try {
			src = ImageIO.read(new File("./images/lena-error.jpg"));
			f = new FourierImage(src);
			fail();
		} catch (FourierException e) {
			assertEquals(e.getMessage(),FourierException.DIMENSAO_IMAGEM);
		} catch (IOException e2){
		}
		
		assertNull(f);
		
		/* Imagem que não existe no arquivo */
		
		try {
			src = ImageIO.read(new File("./images/lena-invalid.jpg"));
			f = new FourierImage(src);
			fail();
		} catch (FourierException e) {
			assertEquals(e.getMessage(),FourierException.DIMENSAO_IMAGEM);
		} catch (IOException e2){
		}
		
		assertNull(f);
		


	}

	public void testGetWidth() {
		startUp();
		assertEquals(f0.getWidth(),512);
		assertEquals(f0.getWidth(),f0.getHeight());
	}

	public void testGetHeight() {
		startUp();
		assertEquals(f0.getHeight(),512);
		assertEquals(f0.getWidth(),f0.getHeight());
	}

	public void testIsSpectral() {
		startUp();
		assertFalse(f0.isEspectral());
		f0.transform();
		assertTrue(f0.isEspectral());
	}


	public void testGetMagnitude() {
		startUp();
		try {
			assertFalse(f0.getMagnitude(0, 0) == (float)0);
			fail();
		} catch (FourierException e) {
			assertEquals(e.getMessage(),FourierException.DOMINIO_FREQUENCIA);
		}
		
		
		f0.transform();
		try {
			assertFalse(f0.getMagnitude(0, 0) == (float)0);
		} catch (FourierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testGetFase() {
		startUp();
		try {
			assertFalse(f0.getFase(0, 0) == (float)0);
			fail();
		} catch (FourierException e) {
			assertEquals(e.getMessage(),FourierException.DOMINIO_FREQUENCIA);
		}
		
		
		f0.transform();
		try {
			assertFalse(f0.getFase(0, 0) != (float)0);
		} catch (FourierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
