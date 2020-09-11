package ej3;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Pseudocódigo: indicar opcion segun sea -> calcular la aprox. de f(x) = x o la aprox. de f(x) =
 * sin(x) indicar nº puntos, se almacena ese valor en n indicar el contador de puntos que cumplen la
 * condicion --> sum cada punto tiene coordenada X,Y por lo tanto generar 2 números aleatorios por
 * cada punto si el valor de Y está por debajo de la curva o la toca (Y es <= x o Y es <= sin(x))
 * sum = sum + 1 devolver los puntos que cumplen la condición divididos el número total de puntos
 *
 * <p>Versión paralela de grano grueso utilizando la Ecuacion de Subramanian,
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class intParalelomultiCont implements Runnable {
  /** The constant nPoints. */
  public static int nPoints;

  /** The constant object. */
  public static final Object object = new Object();

  /** The constant a. */
  public static long a;

  /** The Scanner. */
  static Scanner scanner = new Scanner(System.in);

  /** The Range. */
  public int range;

  /**
   * Instantiates a new Int paralelomulti cont.
   *
   * @param range the range
   */
  public intParalelomultiCont(int range) {
    this.range = range;
  }

  @Override
  public void run() {
    int cont = 0;
    for (int i = 0; i < range; i++) {
      double x = Math.random();
      double y = Math.random();
      if (y <= Math.sin(x)) {
        cont++;
      }
    }
    synchronized (object) {
      a = a + cont;
    }
  }

  private static void executingIntParaleloMultiCont(int nThreads) {
    int range;
    double solution = 0;
    System.out.print("Número de puntos: ");
    nPoints = scanner.nextInt();
    range = nPoints / nThreads;
    a = 0;

    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(nThreads);
    intParalelomultiCont[] vectorIntParalelomultiConts =
        IntStream.range(0, nThreads)
            .mapToObj(i -> new intParalelomultiCont(range))
            .toArray(intParalelomultiCont[]::new);

    long iniCronom = System.currentTimeMillis();

    Arrays.stream(vectorIntParalelomultiConts, 0, nThreads)
        .forEachOrdered(newFixedThreadPool::execute);

    newFixedThreadPool.shutdown();

    try {
      newFixedThreadPool.awaitTermination(50000L, TimeUnit.MILLISECONDS);
    } catch (InterruptedException I) {
      I.printStackTrace();
    }

    solution = a / ((double) nPoints);

    System.out.println("La integral de la primera funcion es: " + solution);
    long finCronom = System.currentTimeMillis();

    System.out.println((finCronom - iniCronom) + " milisegundos\n\n\n");
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    int cb = 0;
    int nCores = Runtime.getRuntime().availableProcessors();
    int nThreads = nCores / (1 - cb);
    while (true) {
      executingIntParaleloMultiCont(nThreads);
    }
  }
}
