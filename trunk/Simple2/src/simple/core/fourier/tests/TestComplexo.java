package simple.core.fourier.tests;

import junit.framework.TestCase;
import simple.core.fourier.Complexo;


public class TestComplexo extends TestCase {

	public void testGetMagnitude() {
		Complexo c = new Complexo();
		assertTrue(0 == c.getMagnitude());
		c = new Complexo(-1,-1);
		// Há truncamento
		assertEquals(c.getMagnitude(),(float)Math.sqrt(2));
		c = new Complexo(4,3);
		assertEquals((float)5,c.getMagnitude());
		
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
	/*	Complexo c = new Complexo();
		c.setPolar(0, 0);
		assertEquals(c.getR(),(float)0);
		assertEquals(c.getI(),(float)0);
		
		c = new Complexo(1,-1);
		c.setPolar(1, Math.PI);
		assertEquals(c.getR(),(float)-1);
		assertTrue(c.getI() < 0.5); */

		
	}

}
