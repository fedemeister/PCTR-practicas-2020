package P1_ej2;

import java.util.Scanner;

/**
 * The type Newton raphson.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class NewtonRaphson {
  /**
   * Function f(x) = cos(x) - (x^3)
   *
   * @param x the x
   * @return the solution for the function f(x) = cos(x) - (x^3)
   */
  double f(double x) {
    return (Math.cos(x) - Math.pow(x, 3));
  }

  /**
   * Function f(x) = cos(x) - (x^3)
   *
   * @param x the x
   * @return the solution for the Derivative of f(x) = cos(x) - (x^3)
   */
  double fDerivative(double x) {
    return (-Math.sin(x) - 3 * Math.pow(x, 2));
  }

  /**
   * Function g(x) = (x^2) - 5.
   *
   * @param x the x
   * @return the solution for the function g(x) = (x^2) - 5
   */
  double g(double x) {
    return (Math.pow(x, 2) - 5);
  }

  /**
   * Function g(x) = 2*x.
   *
   * @param x the x
   * @return the solution for the Derivative of g(x) = (x^2) - 5
   */
  double gDerivative(double x) {
    return (2 * x);
  }

  /**
   * The entry point of application.
   *
   * @param Args the input arguments
   */
  public static void main(String[] Args) {
    NewtonRaphson newtonRaphson = new NewtonRaphson();
    Scanner scanner = new Scanner(System.in);
    System.out.println("Elige la funcion a la que deseas aplicar el metodo:");
    System.out.println("1. cos(x)-x^3");
    System.out.println("2. x^2-5");
    int option = scanner.nextInt();
    int numberOfIterations;
    double initialAproximation;
    switch (option) {
      case 1:
        System.out.println("Introduce el número de iteraciones:");
        numberOfIterations = scanner.nextInt();
        do {
          System.out.println("Introduce aproximación entre [0,1]. Por ejemplo 0.4: ");
          initialAproximation = scanner.nextDouble();
        } while (initialAproximation < 0 || initialAproximation > 1);
        for (int i = 0; i < numberOfIterations; i++) {
          if (newtonRaphson.fDerivative(initialAproximation) != 0) {
            initialAproximation =
                initialAproximation
                    - newtonRaphson.f(initialAproximation)
                        / newtonRaphson.fDerivative(initialAproximation);
            System.out.println("Iteración: " + (i + 1) + ", Aproximación: " + initialAproximation);
          }
        }
        System.out.println("\tResultado final: " + initialAproximation);
        break;
      case 2:
        System.out.println("Introduce el número de iteraciones:");
        numberOfIterations = scanner.nextInt();
        do {
          System.out.println("Introduce aproximación entre [2,3]. Por ejemplo 2.6: ");
          initialAproximation = scanner.nextDouble();
        } while (initialAproximation < 2 || initialAproximation > 3);
        for (int i = 0; i < numberOfIterations; i++) {
          if (newtonRaphson.gDerivative(initialAproximation) != 0) {
            initialAproximation =
                initialAproximation
                    - newtonRaphson.g(initialAproximation)
                        / newtonRaphson.gDerivative(initialAproximation);
            System.out.println("Iteración: " + (i + 1) + ", Aproximación: " + initialAproximation);
          }
        }
        System.out.println("\tResultado final: " + initialAproximation);
        break;
    }
  }
}
