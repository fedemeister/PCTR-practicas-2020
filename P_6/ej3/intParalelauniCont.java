package ej3;

import java.util.Scanner;
import java.util.concurrent.*;

/**
 * The type Int paralelauni cont.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class intParalelauniCont implements Runnable {

  private static long a = 0;

  private static int nPoints;

  private static final Object object = new Object();

  private static final Scanner scanner = new Scanner(System.in);

  private final int range;

  /**
   * Instantiates a new Int paralelauni cont.
   *
   * @param range the range
   */
  public intParalelauniCont(int range) {
    this.range = range;
  }

  @Override
  public void run() {

    for (int i = 0; i < range; i++) {
      double x = Math.random();
      double y = Math.random();

      if (y <= Math.sin(x)) {
        synchronized (object) {
          a++;
        }
      }
    }
  }

  private static void executingIntParalelauniCont(int nThreads) {
    double aprox;
    int range = nPoints / nThreads;

    System.out.print("Número de puntos: ");
    nPoints = scanner.nextInt();

    a = 0;
    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(nThreads);
    intParalelauniCont[] vectorIntParalelauniCont = new intParalelauniCont[nThreads];
    for (int i = 0; i < nThreads; i++) {
      vectorIntParalelauniCont[i] = new intParalelauniCont(range);
    }

    long inicCronom = System.currentTimeMillis();

    for (int i = 0; i < nThreads; i++) {
      newFixedThreadPool.execute(vectorIntParalelauniCont[i]);
    }

    newFixedThreadPool.shutdown();

    try {
      newFixedThreadPool.awaitTermination(5000L, TimeUnit.MILLISECONDS);
    } catch (InterruptedException I) {
      I.printStackTrace();
    }

    aprox = a / (double) nPoints;

    System.out.println("La integral de la primera funcion es: " + aprox);
    long finCronom =
        System.currentTimeMillis(); // se para el cronometro, seria mejor en nanosegundos

    System.out.println((finCronom - inicCronom) + " milisegundos\n\n");
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
      executingIntParalelauniCont(nThreads);
    }
  }
}
