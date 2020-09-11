package P1_ej5;

import java.util.Scanner;

/**
 * The type Use complex.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class useComplex {

  /**
   * Display the menu and return the option pressed by the user.
   *
   * @param scanner Scanner(System.in)
   * @param complex_1 the complex 1
   * @param complex_2 the complex 2
   * @return the option pressed.
   */
  private static int displayMenu(Scanner scanner, Complex complex_1, Complex complex_2) {
    int option;

    Complex complex;

    System.out.println("Introduce que resultado estadísitico desea: ");
    System.out.println("Pulse 1 para calcular SUMA");
    System.out.println("Pulse 2 para calcular RESTA");
    System.out.println("Pulse 3 para calcular MULTIPLICACIÓN");
    System.out.println("Pulse 4 para calcular DIVISIÓN");
    System.out.println("Pulse 5 para calcular PRODUCTO");
    System.out.println("\tPulse 6 para SALIR");
    option = scanner.nextInt();
    if (option != 6) {
      keyboardIntroductionComplexNumbers(scanner, complex_1, complex_2);
    }

    switch (option) {
      case 1:
        complex = Complex.addition(complex_1, complex_2);
        complex.printComplex();
        break;

      case 2:
        complex = Complex.substraction(complex_1, complex_2);
        complex.printComplex();
        break;

      case 3:
        complex = Complex.multiplication(complex_1, complex_2);
        complex.printComplex();
        break;

      case 4:
        complex = Complex.division(complex_1, complex_2);
        complex.printComplex();
        break;

      case 5:
        complex = Complex.multiplication(complex_1, complex_2);
        complex.printComplex();
        break;

      case 6:
        break;

      default:
        throw new IllegalStateException("Valor inesperado: " + option);
    }
    return option;
  }

  /**
   * @param scanner Scanner(System.in)
   * @param complex_1 the complex 1
   * @param complex_2 the complex 2
   */
  private static void keyboardIntroductionComplexNumbers(
      Scanner scanner, Complex complex_1, Complex complex_2) {
    System.out.println("Introduzca parte real del primer complejo: ");
    complex_1.setRealPart(scanner.nextDouble());
    System.out.println("Introduzca parte imaginaria del primer complejo: ");
    complex_1.setImaginaryPart(scanner.nextDouble());
    System.out.println("Introduzca parte real del segundo complejo: ");
    complex_2.setRealPart(scanner.nextDouble());
    System.out.println("Introduzca parte imaginaria del segundo complejo: ");
    complex_2.setImaginaryPart(scanner.nextDouble());
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int option = 0;

    Complex complex_1 = new Complex();
    Complex complex_2 = new Complex();

    do {
      option = displayMenu(scanner, complex_1, complex_2);
    } while (option != 6);
  }
}
