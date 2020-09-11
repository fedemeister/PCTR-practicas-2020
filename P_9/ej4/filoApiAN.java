package ej4;

import java.util.Arrays;
import java.util.concurrent.locks.*;

/**
 * The type Filo api an.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class filoApiAN {
  private final int nPhilosophers;
  private final ReentrantLock reentrantLock;
  private final int[] forks;
  private final Condition[] conditions;

  /**
   * Instantiates a new Filo api an.
   *
   * @param nPholosophers the number of pholosophers
   */
  public filoApiAN(int nPholosophers) {
    nPhilosophers = nPholosophers;
    conditions = new Condition[nPhilosophers];
    reentrantLock = new ReentrantLock();
    forks = new int[nPhilosophers];

    Arrays.fill(forks, 2); // todos los tenedores libres.

    for (int i = 0; i < nPhilosophers; i++) {
      conditions[i] = reentrantLock.newCondition();
    }
  }

  /**
   * Release forks.
   *
   * @param i the the fork index
   */
  public void releaseForks(int i) {
    reentrantLock.lock();
    try {
      int beforeI;
      if (i == 0) {
        beforeI = nPhilosophers - 1;
      } else {
        beforeI = i - 1;
      }
      forks[(i + 1) % nPhilosophers] += 1;
      forks[beforeI] = forks[beforeI] + 1;
      if (forks[(i + 1) % nPhilosophers] == 2) {
        conditions[(i + 1) % nPhilosophers].signal();
      }
      if (forks[beforeI] == 2) {
        conditions[beforeI].signal();
      }

    } finally {
      reentrantLock.unlock();
    }
  }

  /**
   * Take forks.
   *
   * @param i the fork index
   */
  public void takeForks(int i) {
    reentrantLock.lock();
    int beforeI;
    if (i == 0) {
      beforeI = nPhilosophers - 1;
    } else {
      beforeI = i - 1;
    }
    try {
      while (forks[i] != 2) {
        try {
          conditions[i].await(); // se bloquea si el tenedor derecho no está disponible.
        } catch (InterruptedException I) {
          I.printStackTrace();
        }
      }
      forks[(i + 1) % nPhilosophers] -= 1;
      forks[beforeI] -= 1;
    } finally {
      reentrantLock.unlock();
    }
  }
}
