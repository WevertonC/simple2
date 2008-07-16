package simple.fourier.core;

/**
 * Representa��o de um numero complexo. Um n�mero complexo � composto por uma parte real e outra imagin�ria.
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
	 * Constr�i um n�mero complexo a partir de outro n�mero complexo j� instanciado.
	 * @param c
	 */
	public Complexo(Complexo c){
		this.real = c.real;
		this.im = c.im;
	}

	/**
	 * Constr�i um n�mero complexo a partir dos valores reais e imagin�rios passados como par�metro
	 * @param real valor real
	 * @param imaginario valor imaginario
	 */
	public Complexo(double real, double imaginario) {
		this.real = real;
		im = imaginario;
	}

	/**
	 * Retorna a magnitude de um n�mer complexo. A magnitude � equivalente � norma da representa��o
	 * vetorial deste n�mero.
	 * @return a magnitude do n�mero complexo
	 */
	public double getMagnitude() {
		return (double) Math.sqrt(real*real + im*im);
	}

	/**
	 * Retorna a fase de um n�mero. A fase � o arco-tangente, ou seja, o �ngulo entra a parte
	 * real e a parte imagin�ria.
	 * @return o �ngulo da fase, em radianos.
	 */
	public double getFase() {
		return (double) Math.atan2(im, real);
	}

	/**
	 * Coloca o n�mero complexo em termos de coordenadas polares
	 * @param r a norma da coordenada
	 * @param theta o �ngulo da coordeanda
	 */
	public void setPolar(double r, double theta) {
		real = (double)(r*Math.cos(theta));
		im = (double)(r*Math.sin(theta));
	}

	public String toString() {
		return new String(real + " + " + im + "i");
	}

	/**
	 * Troca as partes reais e imagin�rias de dois n�meros complexos.
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
	 * Retorna a parte real de um n�mero complexo
	 * @return a parte real do n�mero
	 */
	public double getReal() {
		return real;
	}

	/**
	 * Modifica a parte real de um n�mero complexo
	 * @param real o novo valor da parte real
	 */
	public void setReal(double real) {
		this.real = real;
	}

	/**
	 * Retonra a parte imagin�ria de um n�mero complexo
	 * @return a parte imagin�ria
	 */
	public double getIm() {
		return im;
	}

	/**
	 * Modifica a parte imagin�ria de um n�mero complexo
	 * @param im a nova parte imagin�ria
	 */
	public void setIm(double im) {
		this.im = im;
	}


}
