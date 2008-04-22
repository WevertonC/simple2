package morfologia;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Abertura {
	
	//erosão - dilatação
	public static Image abertura4(ImageIcon image) {
		return Dilatacao.dilatacao4(new ImageIcon(Erosao.erosao4(image)));
	}
	
	//erosão - dilatação
	public static Image abertura8(ImageIcon image) {
		return Dilatacao.dilatacao8(new ImageIcon(Erosao.erosao8(image)));
	}
}
