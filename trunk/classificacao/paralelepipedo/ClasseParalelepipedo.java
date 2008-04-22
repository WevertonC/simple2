package paralelepipedo;

import imagem.Cor;
import exceptions.NumeroForaDeFaixaException;


public class ClasseParalelepipedo {
	private int minTonCinza;
	private int maxTonCinza;
	private int minTonCinzaR;
	private int maxTonCinzaR;
	private int minTonCinzaG;
	private int maxTonCinzaG;
	private int minTonCinzaB;
	private int maxTonCinzaB;	
	private String rotulo;
	private boolean monocromatico;
	private boolean rgb;
	private boolean temRed;
	private boolean temGreen;
	private boolean temBlue;
	private Cor cor;
	
	public ClasseParalelepipedo(int _minTonCinza, int _maxTonCinza, String _rotulo, Cor corClassificacao) throws NumeroForaDeFaixaException {
		if (_minTonCinza > _maxTonCinza) {
			throw new NumeroForaDeFaixaException();
		} 
		minTonCinza = verificaTon(_minTonCinza);
		maxTonCinza = verificaTon(_maxTonCinza);
		rotulo = _rotulo;
		cor = corClassificacao;
		monocromatico = true;
		rgb = false;
		temRed = false;
		temGreen = false;
		temBlue = false;
	}
	
	public ClasseParalelepipedo(String _minTonCinzaR, String _maxTonCinzaR,
								String _minTonCinzaG, String _maxTonCinzaG,
								String _minTonCinzaB, String _maxTonCinzaB,								
			                    String _rotulo, Cor corClassificacao) {
		rotulo = _rotulo;
		cor = corClassificacao;
		rgb = true;
		monocromatico = false;
		setMinMaxTonCinzaR(_minTonCinzaR, _maxTonCinzaR);
		setMinMaxTonCinzaG(_minTonCinzaG, _maxTonCinzaG);
		setMinMaxTonCinzaB(_minTonCinzaB, _maxTonCinzaB);
	}
	
	public boolean isRGB() {
		return rgb;
	}
	
	public boolean isMonocromatico() {
		return monocromatico;
	}
	
	public boolean hasRed() {
		return temRed;
	}
	
	public boolean hasGreen() {
		return temGreen;
	}
	
	public boolean hasBlue() {
		return temBlue;
	}
	
	private int verificaTon(int tonCinza) throws NumeroForaDeFaixaException {
		if (tonCinza >= 0 || tonCinza <= 255) {
			return tonCinza;
		}
		throw new NumeroForaDeFaixaException();
	}

	public int getMaxTonCinza() {
		return maxTonCinza;
	}

	public void setMaxTonCinza(int maxTonCinza) {
		this.maxTonCinza = maxTonCinza;
	}

	public int getMinTonCinza() {
		return minTonCinza;
	}

	public void setMinTonCinza(int minTonCinza) {
		this.minTonCinza = minTonCinza;
	}

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public int getMaxTonCinzaB() {
		return maxTonCinzaB;
	}

	public void setMinMaxTonCinzaB(String minTonCinzaB, String maxTonCinzaB) {
		try {
			temBlue = true;
			this.maxTonCinzaB = Integer.parseInt(maxTonCinzaB);
			this.minTonCinzaB = Integer.parseInt(minTonCinzaB);
		}catch (Exception e) {
			temBlue = false;
		}
	}

	public int getMaxTonCinzaG() {
		return maxTonCinzaG;
	}

	public void setMinMaxTonCinzaG(String minTonCinzaG, String maxTonCinzaG) {
		try {
			temGreen = true;
			this.maxTonCinzaG = Integer.parseInt(maxTonCinzaG);
			this.minTonCinzaG = Integer.parseInt(minTonCinzaG);
		}catch (Exception e) {
			temGreen = false;
		}
	}

	public int getMaxTonCinzaR() {
		return maxTonCinzaR;
	}

	public void setMinMaxTonCinzaR(String minTonCinzaR, String maxTonCinzaR) {
		try {
			temRed = true;
			this.maxTonCinzaR = Integer.parseInt(maxTonCinzaR);
			this.minTonCinzaR = Integer.parseInt(minTonCinzaR);
		}catch (Exception e) {
			temRed = false;
		}
	}

	public int getMinTonCinzaB() {
		return minTonCinzaB;
	}

	public int getMinTonCinzaG() {
		return minTonCinzaG;
	}

	public int getMinTonCinzaR() {
		return minTonCinzaR;
	}

}
