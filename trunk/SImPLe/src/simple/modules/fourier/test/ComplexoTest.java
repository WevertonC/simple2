package simple.modules.fourier.test;

import simple.modules.fourier.core.Complexo;
import junit.framework.TestCase;

public class ComplexoTest extends TestCase {

	public void testComplexo() {
		Complexo c = new Complexo();
		assertFalse(c == null);
		assertEquals(c.getReal(),(double)0);
		assertEquals(c.getIm(),(double)0);
		c.setReal(3);
		assertEquals((double)3,c.getReal());
		c.setIm(3);
		assertEquals((double)3,c.getIm());
	}

	public void testComplexoFloatFloat() {
		Complexo c = new Complexo(0,0);
		
		// Nulo
		assertFalse(c == null);
		assertEquals(c.getReal(),(double)0);
		assertEquals(c.getIm(),(double)0);
		
		// Negativo
		c = new Complexo(-3,-200);
		assertEquals(c.getReal(),(double)-3);
		assertEquals(c.getIm(),(double)-200);
		
		// Positivo
		c = new Complexo(3,500);
		assertEquals(c.getReal(),(double)3);
		assertEquals(c.getIm(),(double)500);
		
		// Variações
		c = new Complexo((double)0.5,500);
		assertEquals(c.getReal(),(double)1/2);
		assertEquals(c.getIm(),(double)500);
	}

	public void testGetMagnitude() {
		Complexo c = new Complexo();
		assertEquals(c.getMagnitude(),(double)0);
		c = new Complexo(3,4);
		assertEquals(c.getMagnitude(),(double)5);
		c = new Complexo(0,4);
		assertEquals(c.getMagnitude(),(double)4);
		c = new Complexo(0,-4);
		assertEquals(c.getMagnitude(),(double)4);
		c = new Complexo(-3,-4);
		assertEquals(c.getMagnitude(),(double)5);
		c = new Complexo(-3,0);
		assertEquals(c.getMagnitude(),(double)3);
	}

	public void testGetFase() {
		Complexo c = new Complexo();
		assertEquals(c.getFase(),(double)0);
		c = new Complexo((double)(Math.PI/2),2);
		assertTrue(1 - c.getFase() <= (double) 0.5);
		c = new Complexo((double)(Math.PI),2);
		assertFalse(1 - c.getFase() >= (double) 1);
		
	}

	public void testSetPolar() {
		// Trivial
		Complexo c = new Complexo();
		c.setPolar(0, 0);
		assertEquals(c.getReal(),(double)0);
		assertEquals(c.getIm(),(double)0);
		
		c = new Complexo(1,-1);
		c.setPolar(1, Math.PI);
		assertEquals(c.getReal(),(double)-1);
		assertTrue(c.getIm() < 0.5);
	}


	public void testSwapWith() {
		Complexo c0 = new Complexo();
		Complexo c2 = new Complexo(1,-1);
		c0.swapWith(c2);
		
		assertEquals(c0.getReal(),(double)1);
		assertEquals(c0.getIm(),(double)-1);
		assertEquals(c2.getReal(),(double)0);
		assertEquals(c2.getIm(),(double)0);
	}

}
