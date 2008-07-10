package simple.facade;

import simple.core.fourier.Controller;

public class Facade {
	
	private Controller controller;
	
	public Facade(){
		this.controller = new Controller();
	}
	
	public void fft(String imagePath, String outputFileName){
		controller.fft(imagePath, outputFileName);		
	}
	
}
