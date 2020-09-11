package P1_ej5;

/**
 * The type Complex.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class Complex {
  private double realPart = 0;
  private double imaginaryPart = 0;

  /**
   * Instantiates a new Complex.
   *
   * @param realPart the real part
   * @param imaginaryPart the imaginary part
   */
  public Complex(double realPart, double imaginaryPart) {
    this.realPart = realPart;
    this.imaginaryPart = imaginaryPart;
  }

  /** Instantiates a new Complex. */
  public Complex() {
    realPart = 0;
    imaginaryPart = 0;
  }

  /**
   * Instantiates a new Complex.
   *
   * @param complex the complex
   */
  public Complex(Complex complex) {
    realPart = complex.getRealPart();
    imaginaryPart = complex.getImaginaryPart();
  }

  /**
   * Gets real part.
   *
   * @return the real part
   */
  public double getRealPart() {
    return realPart;
  }

  /**
   * Sets real part.
   *
   * @param realPart the real part
   */
  public void setRealPart(double realPart) {
    this.realPart = realPart;
  }

  /**
   * Gets imaginary part.
   *
   * @return the imaginary part
   */
  public double getImaginaryPart() {
    return imaginaryPart;
  }

  /**
   * Sets imaginary part.
   *
   * @param imaginaryPart the imaginary part
   */
  public void setImaginaryPart(double imaginaryPart) {
    this.imaginaryPart = imaginaryPart;
  }

  // Java no soporta la sobrecarga de operadores como en C++
  // webgrafia -->
  // https://www.varsitytutors.com/hotmath/hotmath_help/topics/operations-with-complex-numbers
  // webgrafia --> https://www.math-only-math.com/modulus-of-a-complex-number.html

  /**
   * Addition complex.
   *
   * @param complex_1 the complex 1
   * @param complex_2 the complex 2
   * @return the complex
   */
  public static Complex addition(Complex complex_1, Complex complex_2) {
    return new Complex(
        complex_1.getRealPart() + complex_2.getRealPart(),
        complex_1.getImaginaryPart() + complex_2.getImaginaryPart());
  }

  /**
   * Substraction complex.
   *
   * @param complex_1 the complex 1
   * @param complex_2 the complex 2
   * @return the complex
   */
  public static Complex substraction(Complex complex_1, Complex complex_2) {
    return new Complex(
        complex_1.getRealPart() - complex_2.getRealPart(),
        complex_1.getImaginaryPart() - complex_2.getImaginaryPart());
  }

  /**
   * Module double.
   *
   * @param complex the complex
   * @return the double
   */
  public static double module(Complex complex) {
    return Math.sqrt(
        complex.getRealPart() * complex.getRealPart()
            + complex.getImaginaryPart() * complex.getImaginaryPart());
  }

  /**
   * Multiplication complex.
   *
   * @param complex_1 the complex 1
   * @param complex_2 the complex 2
   * @return the complex
   */
  public static Complex multiplication(Complex complex_1, Complex complex_2) {
    return new Complex(
        complex_1.getRealPart() * complex_2.getRealPart()
            - complex_1.getImaginaryPart() * complex_2.getImaginaryPart(),
        complex_1.getRealPart() * complex_2.getImaginaryPart()
            + complex_1.getImaginaryPart() * complex_2.getRealPart());
  }

  /**
   * Division complex.
   *
   * @param complex_1 the complex 1
   * @param complex_2 the complex 2
   * @return the complex
   */
  public static Complex division(Complex complex_1, Complex complex_2) {
    return new Complex(
        (complex_1.getRealPart() * complex_2.getRealPart()
                + complex_1.getImaginaryPart() * complex_2.getImaginaryPart())
            / (complex_2.getRealPart() * complex_2.getRealPart()
                + complex_2.getImaginaryPart() * complex_2.getImaginaryPart()),
        (complex_1.getImaginaryPart() * complex_2.getRealPart()
                - complex_1.getRealPart() * complex_2.getImaginaryPart())
            / (complex_2.getRealPart() * complex_2.getRealPart()
                + complex_2.getImaginaryPart() * complex_2.getImaginaryPart()));
  }

  /** Print complex. */
  public void printComplex() {
    System.out.print(realPart + (imaginaryPart < 0 ? "" : "+") + imaginaryPart + "i \n\n");
  }
}
