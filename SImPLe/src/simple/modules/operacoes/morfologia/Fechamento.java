package simple.modules.operacoes.morfologia;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Fechamento {
	
	//dilata��o - eros�o
	public static Image fechamento4(ImageIcon image) {
		return Erosao.erosao4(new ImageIcon(Dilatacao.dilatacao4(image)));
	}
	
	//dilata��o - eros�o
	public static Image fechamento8(ImageIcon image) {
		return Erosao.erosao8(new ImageIcon(Dilatacao.dilatacao8(image)));
	}
}
