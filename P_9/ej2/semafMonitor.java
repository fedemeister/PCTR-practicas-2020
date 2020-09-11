package ej2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type Semaf monitor.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class semafMonitor {
  private int S;
  private final ReentrantLock reentrantLock;
  /** The Condition. */
  Condition condition;

  /**
   * Instantiates a new Semaf monitor.
   *
   * @param valor the valor
   */
  public semafMonitor(int valor) {
    this.S = valor;
    reentrantLock = new ReentrantLock();
    condition = reentrantLock.newCondition();
  }

  /** Wait s. */
  public void waitS() {
    reentrantLock.lock();
    while (S == 0) {
      try {
        condition.await();
      } catch (InterruptedException I) {
        I.printStackTrace();
      }
    }
    S--;
    reentrantLock.unlock();
  }

  /** Signal s. */
  public void signalS() {
    reentrantLock.lock();
    condition.signal();
    S++;
    reentrantLock.unlock();
  }
}
