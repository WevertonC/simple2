package simple.modules.fourier.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import simple.modules.fourier.core.FourierImagem;
import simple.modules.fourier.exceptions.FourierException;

import junit.framework.TestCase;

public class FourierImagemTest extends TestCase {

	BufferedImage src;
	FourierImagem f0;
	
	public void startUp(){
		try {
			src = ImageIO.read(new File("./images/lena.bmp"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			f0 = new FourierImagem(src);
		} catch (FourierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testFourierImage() {

		try {
			src = ImageIO.read(new File("./images/lena.bmp"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		FourierImagem f = new FourierImagem();
		try {
			f = new FourierImagem(src);
		} catch (FourierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(f);
		
		/* Imagem com problemas de dimensão */
		f = null;

		try {
			src = ImageIO.read(new File("./images/lena-error.bmp"));
			f = new FourierImagem(src);
			fail();
		} catch (FourierException e) {
			assertEquals(e.getMessage(),FourierException.DIMENSAO_IMAGEM);
		} catch (IOException e2){
		}
		
		assertNull(f);
		
		/* Imagem que não existe no arquivo */
		
		try {
			src = ImageIO.read(new File("./images/not-lena.bmp"));
			f = new FourierImagem(src);
			fail();
		} catch (FourierException e) {
			assertEquals(e.getMessage(),FourierException.DIMENSAO_IMAGEM);
		} catch (IOException e2){
		}
		
		assertNull(f);
		


	}

	public void testGetWidth() {
		startUp();
		assertEquals(f0.getLargura(),512);
		assertEquals(f0.getLargura(),f0.getAltura());
	}

	public void testGetHeight() {
		startUp();
		assertEquals(f0.getAltura(),512);
		assertEquals(f0.getLargura(),f0.getAltura());
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
