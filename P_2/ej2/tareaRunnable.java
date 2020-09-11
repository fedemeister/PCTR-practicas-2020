package ej2;

/**
 * The type Tarea runnable.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class tareaRunnable implements Runnable {
  private static final int NUM_ITERATIONS = 100000000;
  private static long n = 0;
  /** The Incrementing. */
  final boolean incrementing;

  /**
   * Instantiates a new Tarea runnable.
   *
   * @param incrementing the incrementing
   */
  public tareaRunnable(boolean incrementing) {
    this.incrementing = incrementing;
  }

  /** Incremento. */
  public void incremento() {
    n++;
  }

  /** Decremento. */
  public void decremento() {
    n--;
  }

  /**
   * Gets n.
   *
   * @return the n
   */
  public long getN() {
    return n;
  }

  /**
   * Sets n.
   *
   * @param n the n
   */
  public void setN(long n) {
    tareaRunnable.n = n;
  }

  public void run() {
    if (this.incrementing) {
      for (int i = 0; i < NUM_ITERATIONS; i++) {
        incremento();
      }
    }
    else {
      for (int i = 0; i < NUM_ITERATIONS; i++) {
        decremento();
      }
    }
  }
}
