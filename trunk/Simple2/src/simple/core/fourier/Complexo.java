package simple.core.fourier;

public class Complexo {

  /** Parte real do numero. */
  public float real;

  /** Parte imaginaria do numero. */
  public float im;

  public Complexo() {}

  public Complexo(float real, float imaginario) {
    this.real = real;
    im = imaginario;
  }

  public float getMagnitude() {
    return (float) Math.sqrt(real*real + im*im);
  }

  public float getFase() {
    return (float) Math.atan2(im, real);
  }

  public void setPolar(double r, double theta) {
    real = (float)(r*Math.cos(theta));
    im = (float)(r*Math.sin(theta));
  }

  public String toString() {
    return new String(real + " + " + im + "i");
  }

  public void swapWith(Complexo value) {
    float temp = real;
    real = value.real;
    value.real = temp;
    temp = im;
    im = value.im;
    value.im = temp;
  }
}
