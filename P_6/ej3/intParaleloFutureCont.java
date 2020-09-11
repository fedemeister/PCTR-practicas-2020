package ej3;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * The type Int paralelo future cont.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class intParaleloFutureCont implements Callable {
  private static int nPoints;
  private static final Scanner scanner = new Scanner(System.in);
  private final int range;

  /**
   * Instantiates a new Int paralelo future cont.
   *
   * @param range the range
   */
  public intParaleloFutureCont(int range) {
    this.range = range;
  }

  @Override
  public Integer call() {
    Integer counter = 0;
    for (int i = 0; i < range; i++) {
      double x = Math.random();
      double y = Math.random();

      if (y <= Math.sin(x)) {
        counter++;
      }
    }
    return counter;
  }

  private static void executingIntParaleloFutureCont(int nThreads) {
    double aprox = 0;
    long a = 0;
    int range = nPoints / nThreads;

    System.out.print("Número de puntos: ");
    nPoints = scanner.nextInt();

    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(nThreads);
    intParaleloFutureCont[] vectorIntParaleloFutureConts = new intParaleloFutureCont[nThreads];
    ArrayList<Future<Integer>> futureArrayList = new ArrayList<>(nThreads);
    for (int i = 0; i < nThreads; i++) {
      vectorIntParaleloFutureConts[i] = new intParaleloFutureCont(range);
    }

    long inicCronom = System.currentTimeMillis();
    Future aux;

    int[] results = new int[nThreads];
    for (int i = 0; i < nThreads; i++) {
      aux = newFixedThreadPool.submit(vectorIntParaleloFutureConts[i]);
      futureArrayList.add(aux);
    }

    newFixedThreadPool.shutdown();

    try {
      newFixedThreadPool.awaitTermination(50000L, TimeUnit.MILLISECONDS);
    } catch (InterruptedException I) {
      I.printStackTrace();
    }

    int it = 0;
    for (Future<Integer> iterator : futureArrayList) {
      try {
        results[it] = iterator.get();
      } catch (InterruptedException | CancellationException | ExecutionException I) {
        I.printStackTrace();
      }
      it++;
    }

    for (int resultado : results) {
      a += resultado;
    }

    aprox = a / (double) nPoints;

    System.out.println("aprox = " + aprox);

    long finCronom = System.currentTimeMillis();
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
            executingIntParaleloFutureCont(nThreads);
        }
    }
}
