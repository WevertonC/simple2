package simple.core.fourier;

public class Complex {

  /** Real part of number. */
  public float real;

  /** Imaginary part of number. */
  public float im;

  public Complex() {}

  public Complex(float real, float imaginary) {
    this.real = real;
    im = imaginary;
  }

  public float getMagnitude() {
    return (float) Math.sqrt(real*real + im*im);
  }

  public float getPhase() {
    return (float) Math.atan2(im, real);
  }

  public void setPolar(double r, double theta) {
    real = (float)(r*Math.cos(theta));
    im = (float)(r*Math.sin(theta));
  }

  public String toString() {
    return new String(real + " + " + im + "i");
  }

  public void swapWith(Complex value) {
    float temp = real;
    real = value.real;
    value.real = temp;
    temp = im;
    im = value.im;
    value.im = temp;
  }
}
