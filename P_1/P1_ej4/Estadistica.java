package P1_ej4;

import java.util.Scanner;

/**
 * The type Estadistica.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class Estadistica {

  /**
   * Do average double.
   *
   * @param values the values
   * @return the double
   */
  public static double doAverage(int[] values) {
    double average = 0;
    for (int value : values) {
      average = average + value;
    }
    average = average / values.length;

    return average;
  }

  /**
   * Do mode double.
   *
   * @param values the values
   * @return the double
   */
  public static double doMode(int[] values) {
    int maxValue = 0;
    int maxCount = 0;

    for (int value : values) {
      int count = 0;
      for (int i : values) {
        if (i == value) ++count;
      }
      if (count > maxCount) {
        maxCount = count;
        maxValue = value;
      }
    }

    return maxValue;
  }

  /**
   * Do variance double.
   *
   * @param values the values
   * @return the double
   */
  public static double doVariance(int[] values) {
    double summation = 0;
    double average = doAverage(values);
    for (int value : values) {
      double aux = value - average;
      summation = aux * aux;
    }

    return summation / values.length;
  }

  /**
   * Do standard deviation double.
   *
   * @param values the values
   * @return the double
   */
  public static double doStandardDeviation(int[] values) {
    return Math.sqrt(doVariance(values));
  }

  /**
   * Display the menu and return the option pressed by the user.
   *
   * @param scanner Scanner(System.in)
   * @param vectorOfNumbers vector of numbers
   * @return the option pressed.
   */
  private static int displayMenu(Scanner scanner, int[] vectorOfNumbers) {
    int option;
    System.out.println("Introduce que resultado estadísitico desea: ");
    System.out.println("Pulse 1 para calcular la media");
    System.out.println("Pulse 2 para calcular la moda");
    System.out.println("Pulse 3 para calcular la varianza");
    System.out.println("Pulse 4 para calcular la desviación típica");
    System.out.println("\tPulse 5 para SALIR");

    option = scanner.nextInt();
    switch (option) {
      case 1:
        double average = doAverage(vectorOfNumbers);
        System.out.println("Media = " + average);
        break;

      case 2:
        double mode = doMode(vectorOfNumbers);
        System.out.println("Moda =  " + mode);
        break;

      case 3:
        double variance = doVariance(vectorOfNumbers);
        System.out.println("Varianza = " + variance);
        break;

      case 4:
        double standardDeviation = doStandardDeviation(vectorOfNumbers);
        System.out.println("Desviación típíca = " + standardDeviation);
        break;
      case 5:
        break;
      default:
        throw new IllegalStateException("Valor inesperado: " + option);
    }
    return option;
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int quantityOfNumbers;
    int option;
    do {
      System.out.println("Introduce cuántos números quieres: ");
      quantityOfNumbers = scanner.nextInt();
    } while (quantityOfNumbers <= 0);

    int[] vectorOfNumbers = new int[quantityOfNumbers];

    for (int i = 0; i < quantityOfNumbers; i++) {
      System.out.print("Introduce un número: ");
      vectorOfNumbers[i] = scanner.nextInt();
    }

    do {
      option = displayMenu(scanner, vectorOfNumbers);
    } while (option != 5);
  }
}
