package P1_ej3;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Pseudocódigo: indicar opcion segun sea -> calcular la aprox. de f(x) = x o la aprox. de f(x) =
 * sin(x) indicar nº puntos, se almacena ese valor en n indicar el contador de puntos que cumplen la
 * condicion --> sum cada punto tiene coordenada X,Y por lo tanto generar 2 números aleatorios por
 * cada punto si el valor de Y está por debajo de la curva o la toca (Y es <= x o Y es <= sin(x))
 * sum = sum + 1 devolver los puntos que cumplen la condición divididos el número total de puntos
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class intDefinidaMonteCarlo {
  /** The A. */
  static final int A = 0;

  /** The B. */
  static final int B = 1;

  /** The Scanner. */
  static Scanner scanner = new Scanner(System.in);

  /**
   * The entry point of application.
   *
   * @param arg the input arguments
   */
  public static void main(String[] arg) {
    menu();
    System.out.print("Número de puntos: ");
  }

  /** Menu. */
  static void menu() {
    int option = 1, numberOfPoints;

    while (option != 0) {
      System.out.print("\n\n0.-Salir\n1.-Calcular x\n2.-Calcular sin(x)\n--> ");
      option = scanner.nextInt();
      System.out.print("Número de puntos:");
      numberOfPoints = scanner.nextInt();
      while (numberOfPoints < 0) {
        System.out.print("Número de puntos:");
        numberOfPoints = scanner.nextInt();
      }
      switch (option) {
        case 1:
          System.out.println("Solución: " + monteCarloMethod(1, numberOfPoints));
          break;
        case 2:
          System.out.println("Solución: " + monteCarloMethod(2, numberOfPoints));
          break;
      }
    }
  }

  /**
   * Random double.
   *
   * @return the double
   */
  static double random() {
    double rango;
    rango = B - A;
    return (Math.random() * rango + A);
  }

  /**
   * Monte carlo method double.
   *
   * @param option the option
   * @param numberOfPoints the number of points
   * @return the solution for the Monte Carlo method
   */
  static double monteCarloMethod(int option, int numberOfPoints) {
    double summation = 0;
    double solution = (float) (B - A) / numberOfPoints;
    if (option == 1) {
      summation = IntStream.rangeClosed(1, numberOfPoints).mapToDouble(i -> random()).sum();

    } else if (option == 2) {
      int i = 1;
      while (i <= numberOfPoints) {
        summation = (summation + Math.sin(random()));
        i++;
      }
    }
    return solution * summation;
  }
}
