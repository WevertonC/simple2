package simple.manipulacoes.imagem;

public class RGB implements ModeloCromatico{
	public final static int RGB = 1;
	public final static int RG = 2;
	public final static int GB = 3;
	public final static int RB = 4;
	public final static int R = 5;
	public final static int G = 6;
	public final static int B = 7;
	
	private boolean r;
	private boolean g;
	private boolean b;
	
	public RGB(boolean _r, boolean _g, boolean _b) {
		r = _r;
		g = _g;
		b = _b;
	}

	public boolean isB() {
		return b;
	}

	public void setB(boolean b) {
		this.b = b;
	}

	public boolean isG() {
		return g;
	}

	public void setG(boolean g) {
		this.g = g;
	}

	public boolean isR() {
		return r;
	}

	public void setR(boolean r) {
		this.r = r;
	}
	
	public int getCombinacaoDePlanosCromaticos() {
		if (isR() && !isG() && !isB()) {
			return R;
		}
		if (!isR() && isG() && !isB()) {
			return G;
		}
		if (!isR() && !isG() && isB()) {
			return B;
		}
		if (isR() && isG() && !isB()) {
			return RG;
		}
		if (isR() && !isG() && isB()) {
			return RB;
		}
		if (!isR() && isG() && isB()) {
			return GB;
		} else {
			return RGB;
		}
	}
}
