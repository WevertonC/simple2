package simple.fourier.test;

import junit.framework.TestCase;
import simple.fourier.core.Complexo;

public class ComplexoTest extends TestCase {

	public void testComplexo() {
		Complexo c = new Complexo();
		assertFalse(c == null);
		assertEquals(c.getReal(),(float)0);
		assertEquals(c.getIm(),(float)0);
		c.setReal(3);
		assertEquals((float)3,c.getReal());
		c.setIm(3);
		assertEquals((float)3,c.getIm());
	}

	public void testComplexoFloatFloat() {
		Complexo c = new Complexo(0,0);
		
		// Nulo
		assertFalse(c == null);
		assertEquals(c.getReal(),(float)0);
		assertEquals(c.getIm(),(float)0);
		
		// Negativo
		c = new Complexo(-3,-200);
		assertEquals(c.getReal(),(float)-3);
		assertEquals(c.getIm(),(float)-200);
		
		// Positivo
		c = new Complexo(3,500);
		assertEquals(c.getReal(),(float)3);
		assertEquals(c.getIm(),(float)500);
		
		// Variações
		c = new Complexo((float)0.5,500);
		assertEquals(c.getReal(),(float)1/2);
		assertEquals(c.getIm(),(float)500);
	}

	public void testGetMagnitude() {
		Complexo c = new Complexo();
		assertEquals(c.getMagnitude(),(float)0);
		c = new Complexo(3,4);
		assertEquals(c.getMagnitude(),(float)5);
		c = new Complexo(0,4);
		assertEquals(c.getMagnitude(),(float)4);
		c = new Complexo(0,-4);
		assertEquals(c.getMagnitude(),(float)4);
		c = new Complexo(-3,-4);
		assertEquals(c.getMagnitude(),(float)5);
		c = new Complexo(-3,0);
		assertEquals(c.getMagnitude(),(float)3);
	}

	public void testGetFase() {
		Complexo c = new Complexo();
		assertEquals(c.getFase(),(float)0);
		c = new Complexo((float)(Math.PI/2),2);
		assertTrue(1 - c.getFase() <= (float) 0.5);
		c = new Complexo((float)(Math.PI),2);
		assertFalse(1 - c.getFase() >= (float) 1);
		
	}

	public void testSetPolar() {
		// Trivial
		Complexo c = new Complexo();
		c.setPolar(0, 0);
		assertEquals(c.getReal(),(float)0);
		assertEquals(c.getIm(),(float)0);
		
		c = new Complexo(1,-1);
		c.setPolar(1, Math.PI);
		assertEquals(c.getReal(),(float)-1);
		assertTrue(c.getIm() < 0.5);
	}


	public void testSwapWith() {
		Complexo c0 = new Complexo();
		Complexo c2 = new Complexo(1,-1);
		c0.swapWith(c2);
		
		assertEquals(c0.getReal(),(float)1);
		assertEquals(c0.getIm(),(float)-1);
		assertEquals(c2.getReal(),(float)0);
		assertEquals(c2.getIm(),(float)0);
	}

}
