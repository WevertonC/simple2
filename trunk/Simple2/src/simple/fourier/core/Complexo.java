package simple.fourier.core;

/**
 * Representação de um numero complexo. Um número complexo é composto por uma parte real e outra imaginária.
 * 
 * 
 * @author Elloa B. Guedes - elloa@dsc.ufcg.edu.br
 * @author Odilon F. Lima Junior - odilonflj@dsc.ufcg.edu.br
 *
 */
public class Complexo {

	/** Parte real do numero complexo. */
	public double real;

	/** Parte imaginaria do numero complexo.*/
	public double im;

	/**
	 * Construtor vazio default.
	 */
	public Complexo() {}
	
	/**
	 * Constrói um número complexo a partir de outro número complexo já instanciado.
	 * @param c
	 */
	public Complexo(Complexo c){
		this.real = c.real;
		this.im = c.im;
	}

	/**
	 * Constrói um número complexo a partir dos valores reais e imaginários passados como parâmetro
	 * @param real valor real
	 * @param imaginario valor imaginario
	 */
	public Complexo(double real, double imaginario) {
		this.real = real;
		im = imaginario;
	}

	/**
	 * Retorna a magnitude de um númer complexo. A magnitude é equivalente à norma da representação
	 * vetorial deste número.
	 * @return a magnitude do número complexo
	 */
	public double getMagnitude() {
		return (double) Math.sqrt(real*real + im*im);
	}

	/**
	 * Retorna a fase de um número. A fase é o arco-tangente, ou seja, o ângulo entra a parte
	 * real e a parte imaginária.
	 * @return o ângulo da fase, em radianos.
	 */
	public double getFase() {
		return (double) Math.atan2(im, real);
	}

	/**
	 * Coloca o número complexo em termos de coordenadas polares
	 * @param r a norma da coordenada
	 * @param theta o ângulo da coordeanda
	 */
	public void setPolar(double r, double theta) {
		real = (double)(r*Math.cos(theta));
		im = (double)(r*Math.sin(theta));
	}

	public String toString() {
		return new String(real + " + " + im + "i");
	}

	/**
	 * Troca as partes reais e imaginárias de dois números complexos.
	 * @param value
	 */
	public void swapWith(Complexo value) {
		double temp = real;
		real = value.real;
		value.real = temp;
		temp = im;
		im = value.im;
		value.im = temp;
	}

	/**
	 * Retorna a parte real de um número complexo
	 * @return a parte real do número
	 */
	public double getReal() {
		return real;
	}

	/**
	 * Modifica a parte real de um número complexo
	 * @param real o novo valor da parte real
	 */
	public void setReal(double real) {
		this.real = real;
	}

	/**
	 * Retonra a parte imaginária de um número complexo
	 * @return a parte imaginária
	 */
	public double getIm() {
		return im;
	}

	/**
	 * Modifica a parte imaginária de um número complexo
	 * @param im a nova parte imaginária
	 */
	public void setIm(double im) {
		this.im = im;
	}


}
