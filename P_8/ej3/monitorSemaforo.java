package ej3;

/**
 * The type Monitor semaforo.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class monitorSemaforo {
  private static int S;

  /**
   * Instantiates a new Monitor semaforo.
   *
   * @param semaphoreInitialValue the semaphore initial value
   */
  public monitorSemaforo(int semaphoreInitialValue) {
    S = semaphoreInitialValue;
  }

  /** Wait s. */
  public synchronized void waitS() {
    while (S == 0) {
      try {
        wait();
      } catch (InterruptedException I) {
        I.printStackTrace();
      }
    }
    S--;
  }

  /** Signal s. */
  public synchronized void signalS() {
    notifyAll();
    S++;
  }
}
