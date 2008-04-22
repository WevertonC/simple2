package imagem;

public class Cor {
	
	private int corR;
	private int corG;
	private int corB;
	
	public Cor(int red, int green, int blue) {
		corR = red;
		corG = green;
		corB = blue;
	}

	public int getCorB() {
		return corB;
	}

	public void setCorB(int corB) {
		this.corB = corB;
	}

	public int getCorG() {
		return corG;
	}

	public void setCorG(int corG) {
		this.corG = corG;
	}

	public int getCorR() {
		return corR;
	}

	public void setCorR(int corR) {
		this.corR = corR;
	}

}
