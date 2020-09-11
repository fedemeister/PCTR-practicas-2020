package ej1;

/**
 * The type Hebra.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
class hebra extends Thread {
  private static long n = 0;
  private static final int NUM_ITERATIONS = 100000000;
  private final boolean incr;

  /**
   * Instantiates a new Hebra.
   *
   * @param incr the incr
   */
  public hebra(boolean incr) {
    this.incr = incr;
    n = n;
  }

  /**
   * Is incr boolean.
   *
   * @return the boolean
   */
  public boolean isIncr() {
    return incr;
  }

  /**
   * Gets n.
   *
   * @return the n
   */
  public static long getN() {
    return n;
  }

  /**
   * Sets n.
   *
   * @param n the n
   */
  public static void setN(long n) {
    hebra.n = n;
  }

  // Defino el metodo run
  public void run() {
    for (long cont = 0; cont < NUM_ITERATIONS; cont++) {
      if (isIncr()) {
        n++;
      } else {
        n--;
      }
    }
  }
}
