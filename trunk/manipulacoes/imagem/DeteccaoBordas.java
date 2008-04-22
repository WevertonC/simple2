package imagem;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.swing.ImageIcon;

/**
 * Classe que executa os filtros laplaciano, de sobel, de roberts,
 * de prewitt e Frei e Chen.
 * @author Ricardo Madeira Fernandes
 *
 */
public class DeteccaoBordas extends ImageIcon{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BufferedImage bufferedImage;
	
	private String tipoMascara;
	
	private final float LAPLACE_H[] = new float[] {0.0F,-1.0F,0.0F,
                                                   -1.0F,4.0F,-1.0F,
                                                   0.0F,-1.0F,0.0F};

	private final float LAPLACE_V[] = new float[] {0.0F,-1.0F,0.0F,
                                                   -1.0F,4.0F,-1.0F,
                                                   0.0F,-1.0F,0.0F};
	
	private final float SOBEL_H[] = new float[] {1.0F,0.0F,-1.0F,
                                                 2.0F,0.0F,-2.0F,
                                                 1.0F,0.0F,-1.0F};		

	private final float SOBEL_V[] = new float[] {-1.0F,-2.0F,-1.0F,
                                                 0.0F,0.0F,0.0F,
                                                 1.0F,2.0F,1.0F};

	private final float ROBERTS_H[] = new float[] {0.0F,0.0F,-1.0F,
												   0.0F,1.0F,0.0F,
								                   0.0F,0.0F,0.0F};		
	
	private final float ROBERTS_V[] = new float[] {-1.0F,0.0F,0.0F,
			                       	               0.0F,1.0F,0.0F,
			                                       0.0F,0.0F,0.0F};

	private final float PREWITT_H[] = new float[] {1.0F,0.0F,-1.0F,
								                   1.0F,0.0F,-1.0F,
								                   1.0F,0.0F,-1.0F};
	
	private final float PREWITT_V[] = new float[] {-1.0F,-1.0F,-1.0F,
			                                       0.0F,0.0F,0.0F,
			                                       1.0F,1.0F,1.0F};
	
	private final float FREICHEN_H[] = new float[] {1.0F,0.0F,-1.0F,
			                                        1.414F,0.0F,-1.414F,
			                                        1.0F,0.0F,-1.0F};


	private final float FREICHEN_V[] = new float[] {-1.0F,-1.414F,-1.0F,
                                                    0.0F,0.0F,0.0F,
                                                    1.0F,1.414F,1.0F};	
	
	
	
	
	public DeteccaoBordas(Image image, String tipoMascara) {
		super(image);
		setTipoMascara(tipoMascara);
		bufferedImage = new BufferedImage(getIconWidth(), getIconHeight(),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D bufferedGraphics = bufferedImage.createGraphics();
		bufferedGraphics.drawImage(getImage(), null, null);
		bufferedImage = detectaBorda(image);
	}
	
	public String getTipoMascara(){
		return this.tipoMascara;
	}
	
	public void setTipoMascara(String tipoMascara){
		this.tipoMascara = tipoMascara;
	}

	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(Image image) {		
		super.setImage(image);
		bufferedImage = new BufferedImage(getIconWidth(), getIconHeight(),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D bg = bufferedImage.createGraphics();
		bg.drawImage(getImage(), null, null);
		bufferedImage = detectaBorda(image);
	}
	
	private BufferedImage detectaBorda(Image src) {
		
		PlanarImage img0 = (PlanarImage)JAI.create("AWTImage",src);

		
		String mascara = getTipoMascara();
		
		float[] horizontal = getMascaraHorizontal(mascara);
		float[] vertical = getMascaraVertical(mascara);
		
		KernelJAI kern_h = new KernelJAI(3,3,horizontal);
		KernelJAI kern_v = new KernelJAI(3,3,vertical);
		
		PlanarImage img1 = 
			(PlanarImage)JAI.create("gradientmagnitude", img0,kern_h,kern_v);
		
		return img1.getAsBufferedImage();
	}
	
	public float[] getMascaraHorizontal(String mascara){
		if(mascara.equals("sobel")){
			return this.SOBEL_H;
		}else if(mascara.equals("roberts")){
			return this.ROBERTS_H;
		}else if(mascara.equals("prewitt")){
			return this.PREWITT_H;
		}else if(mascara.equals("freichen")){
			return this.FREICHEN_H;
		}else if(mascara.equals("laplace")){
			return this.LAPLACE_H;
		}
		return null;
	}
	
	public float[] getMascaraVertical(String mascara){
		if(mascara.equals("sobel")){
			return this.SOBEL_V;
		}else if(mascara.equals("roberts")){
			return this.ROBERTS_V;
		}else if(mascara.equals("prewitt")){
			return this.PREWITT_V;
		}else if(mascara.equals("freichen")){
			return this.FREICHEN_V;
		}else if(mascara.equals("laplace")){
			return this.LAPLACE_V;
		}		
		return null;
	}
	

}
