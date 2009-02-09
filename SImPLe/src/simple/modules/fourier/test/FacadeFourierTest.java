package simple.modules.fourier.test;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.io.File;

import javax.swing.ImageIcon;

import junit.framework.TestCase;
import simple.modules.fourier.exceptions.FourierException;
import simple.modules.fourier.facade.Facade;

public class FacadeFourierTest extends TestCase {

//	public void testFft() {
//		
//		Facade f = new Facade();
//		String path = "validacao/imagens/"; 
//		String imageFile = new String();
//		String imageFormat = ".jpg";
//		
//		for (int i = 1; i <= 60; i++){
//			
//			imageFile = new String("0");
//			
//			if (i < 10){
//				imageFile += "0" + i;
//			}
//			else {
////				imageFile += "" + i;
////			}
////				f.fft(path + imageFile + imageFormat);
////			
////			
////		}
////		
////	}
////	
//	
	private Double forOne(String im0, String im1){
		
		ImageIcon i0 = new ImageIcon(im0);
		ImageIcon i1 = new ImageIcon(im1);
		Image img0 = i0.getImage();
		Image img1 = i1.getImage();

		
		int altura = i0.getIconHeight();
		int largura = i0.getIconWidth();
		double acumulo = 0;
		
		int[] pixels0 = new int[altura*largura];
		int[] pixels1 = new int[altura*largura];

		PixelGrabber pg0 =  new PixelGrabber(img0, 0, 0, largura, altura, pixels0, 0, largura);
		PixelGrabber pg1 =  new PixelGrabber(img1, 0, 0, largura, altura, pixels1, 0, largura);

		try { 
			pg0.grabPixels(); 
			pg1.grabPixels();
		}
		catch (InterruptedException e)  {
			e.printStackTrace();
		}
		
		
		
		for (int i = 0; i < largura*altura; i++){
			Color c0 = new Color(pixels0[i]);
			Color c1 = new Color(pixels1[i]);
			acumulo += Math.abs(c1.getRed() - c0.getRed());
		}
		
		return new Double(acumulo/(512.0*512.0));
		
		
	}
//	
//	public void testTest(){
//		
//		String path = "validacao/imagens/"; 
//		String imageFile = new String();
//		String imageFormat = ".jpg";
//		Double d = new Double(0);
//		
//		for (int i = 1; i <= 60; i++){
//			
//			imageFile = new String("0");
//			
//			if (i < 10){
//				imageFile += "0" + i;
//			}
//			else {
//				imageFile += "" + i;
//			}
//			
//			double result = forOne(path + imageFile +"f"+imageFormat, path + imageFile +"-output"+imageFormat);
//			System.out.println(i +": " + result+";");
//			d +=  result;
//			
//		}
//		
//		System.out.println("Finalmentes: " + d/60.0);
//		
//	}
//	
//	public void testInverse(){
//		Facade f = new Facade();
//		f.ifft("validacao/imagens/p022.jpg");
//	}
	
//	public void testIFft() {
//		
//		Facade f = new Facade();
//		String path = "validacao/imagens/"; 
//		String imageFile = new String();
//		String imageFormat = ".jpg";
//		
//		for (int i = 1; i <= 60; i++){
//			
//			imageFile = new String("0");
//			
//			if (i < 10){
//				imageFile += "0" + i;
//			}
//			else {
//				imageFile += "" + i;
//			}
//				f.ifft(path + imageFile + imageFormat);
//			
//			
//		}
//		
//	}
	
	public void testcomparisonInverse(){
			
			String path = "validacao/imagens/"; 
			String imageFile = new String();
			String imageFormat = ".jpg";
			Double d = new Double(0);
			
			for (int i = 1; i <= 60; i++){
				
				imageFile = new String("0");
				
				if (i < 10){
					imageFile += "0" + i;
				}
				else {
					imageFile += "" + i;
				}
				
				double result = forOne(path + imageFile +imageFormat, path + imageFile +"-outputInverse"+imageFormat);
				System.out.println(i +": " + result+";");
				d +=  result;
				
			}
			
			System.out.println("Finalmentes Inversa: " + d/60.0);
			
	}

	
//	public void testImageJ(){
//		System.out.println(forOne("validacao/imagens/p022.jpg","validacao/imagens/i022.jpg"));
//		System.out.println(forOne("validacao/imagens/p022.jpg","validacao/imagens/a022.jpg"));
//		System.out.println(forOne("validacao/imagens/i022.jpg","validacao/imagens/a022.jpg"));
//		System.out.println("Inverso no SImPLE: " + forOne("validacao/imagens/p022.jpg","validacao/imagens/p022-outputInverse.jpg"));
//		System.out.println("Inverso no SImPLE - Inverso ImageJ " + forOne("validacao/imagens/i022.jpg","validacao/imagens/p022-outputInverse.jpg"));
//	}
	

}
