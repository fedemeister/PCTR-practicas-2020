package ej1;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * The type Primos runnable.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class primosRunnable implements Runnable {
  /** The Cyclic barrier. */
  static CyclicBarrier cyclicBarrier;

  /** The Nprimos. */
  static int nprimos = 0;

  private final int start;
  private final int end;
  private final Object lock;

  /** The Vector primes. */
  static ArrayList vectorPrimes = new ArrayList<Integer>();

  /**
   * Instantiates a new Primos runnable.
   *
   * @param start the start
   * @param end the end
   * @param lock the lock
   */
  public primosRunnable(int start, int end, Object lock) {
    this.start = start;
    this.end = end;
    this.lock = lock;
    cyclicBarrier = new CyclicBarrier(11);
  }

  /**
   * Is prime boolean.
   *
   * @param number the number
   * @return the boolean
   */
  public boolean isPrime(int number) {
    boolean prime = true;
    for (int i = 2; i < number && prime; i++) {
      if (number % i == 0) {
        prime = false;
      }
    }
    return prime;
  }

  public void run() {
    int misprimos = 0;
    for (int i = start; i < end; i++) {
      if (i != 0 && i != 1 && isPrime(i)) {
        vectorPrimes.add(i);
        misprimos++;
      }
    }

    synchronized (lock) {
      nprimos += misprimos;
    }

    // System.out.println("misprimos = " + misprimos);

    try {
      cyclicBarrier.await();
    } catch (InterruptedException | BrokenBarrierException I) {
      I.printStackTrace();
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {

    int nThreads = 10;
    int rango = 100000;
    Object C = new Object();
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(nThreads);
    primosRunnable[] tasks = new primosRunnable[nThreads];
    int window = rango / nThreads;

    long iniCrono = System.nanoTime();
    for (int i = 0; i < nThreads - 1; i++) {
      tasks[i] = new primosRunnable(i * window, window * (i + 1), C);
      fixedThreadPool.execute(tasks[i]);
    }
    tasks[nThreads - 1] = new primosRunnable(window * (nThreads - 1), rango, C);

    fixedThreadPool.execute(tasks[nThreads - 1]);
    fixedThreadPool.shutdown();

    try {
      cyclicBarrier.await();
    } catch (InterruptedException | BrokenBarrierException I) {
      I.printStackTrace();
    }

    try {
      fixedThreadPool.awaitTermination(5000L, TimeUnit.MILLISECONDS);
    } catch (InterruptedException I) {
      I.printStackTrace();
    }

    long finCrono = System.nanoTime() - iniCrono;

    System.out.println("nprimos = " + nprimos);
    System.out.println("finCrono = " + finCrono + "ns");
    System.out.println("vectorPrimes = " + vectorPrimes);
  }
}
