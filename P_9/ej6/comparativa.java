package ej6;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type Comparativa.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class comparativa {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    long n = 0;
    long limit = 50000000;
    Object object = new Object();

    usingSynchronized(n, limit, object);
    usingSemaphore(n, limit);
    usingReentrantLock(n, limit);
    usingAtomicInteger(limit);
  }

  /**
   * Time using reentrant lock.
   *
   * @param n the n
   * @param limit the limit
   */
  static void usingReentrantLock(long n, long limit) {
    long i;
    long totalTime;
    ReentrantLock reentrantLock = new ReentrantLock();
    long iniCrono = System.nanoTime();
    n = 0;
    for (i = 0; i < limit; i++) {
      reentrantLock.lock();
      n++;
      reentrantLock.unlock();
    }

    System.out.println("n = " + n);

    totalTime = System.nanoTime() - iniCrono;

    System.out.println("Con ReentranLock " + totalTime + " nanosegundos...");
  }

  /**
   * Time using semaphore.
   *
   * @param n the n
   * @param limit the limit
   */
  static void usingSemaphore(long n, long limit) {
    long i;
    long totalTime;
    n = 0;
    Semaphore semaphore = new Semaphore(1);
    long iniCrono = System.nanoTime();
    for (i = 0; i < limit; i++) {
      try {
        semaphore.acquire();
        n++;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      semaphore.release();
    }
    System.out.println("n = " + n);

    totalTime = System.nanoTime() - iniCrono;
    System.out.println("Con semaforo en " + totalTime + " nanosegundos...");
  }

  /**
   * Time using synchronized.
   *
   * @param n the n
   * @param limit the limit
   * @param object the object
   */
  static void usingSynchronized(long n, long limit, Object object) {
    long i;
    long totalTime;
    long iniCrono = System.nanoTime();
    for (i = 0; i < limit; i++)
      synchronized (object) {
        n++;
      }

    System.out.println("n = " + n);

    totalTime = System.nanoTime() - iniCrono;

    System.out.println("Con synchronized " + totalTime + " nanosegundos...");
  }

  /**
   * Time using atomic integer.
   *
   * @param limit the limit
   */
  static void usingAtomicInteger(long limit) {
    long i;
    long totalTime;
    AtomicInteger atomicInteger = new AtomicInteger(0);
    long iniCrono = System.nanoTime();
    for (i = 0; i < limit; i++) {
      atomicInteger.incrementAndGet();
    }
    totalTime = System.nanoTime() - iniCrono;

    System.out.println("atomicInteger = " + atomicInteger);

    System.out.println("Con Objeto Atomico " + totalTime + " nanosegundos...");
  }
}
