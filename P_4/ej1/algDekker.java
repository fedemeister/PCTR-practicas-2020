package ej1;

/**
 * The Dekker Algorithm for 3 processes. Based on Table 4.2. Dekker’s algorithm of the Principles of
 * Concurrent and Distributed Programming, Second Edition. by M. Ben-Ari
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class algDekker {
  /** The number of iterations. */
  static final int iterations = 2000;
  /** The shared resource. */
  static volatile int enteroCompartido = 0;
  /** The constant wantp. Represent the desire of Thread P to enter in the critical section. */
  static volatile boolean wantp = false;
  /** The constant wantp. Represent the desire of Thread Q to enter in the critical section. */
  static volatile boolean wantq = false;
  /** The constant wantp. Represent the desire of Thread R to enter in the critical section. */
  static volatile boolean wantr = false;
  /** The constant turn. Which thread has the turn. */
  static volatile int turn = 1;

  /**
   * The type P.*
   *
   * @author Federico Carrillo Chaves
   * @version 1.3
   */
  class P extends Thread {
    public void run() {
      for (int i = 0; i < iterations; ++i) {
        /* Sección no critica */
        wantp = true;
        while (wantq || wantr) {
          if (turn != 1) {
            wantp = false;
            while (turn != 1) Thread.yield();
            wantp = true;
          }
        }

        /* Sección critica */
        ++enteroCompartido;
        /* Fin Sección critica */

        turn = 2;
        wantp = false;
      }
    }
  }

  /**
   * The type Q.*
   *
   * @author Federico Carrillo Chaves
   * @version 1.3
   */
  class Q extends Thread {
    public void run() {
      for (int i = 0; i < iterations; ++i) {
        /* Sección no critica */
        wantq = true;
        while (wantp || wantr) {
          if (turn != 2) {
            wantq = false;
            while (turn != 2) Thread.yield();
            wantq = true;
          }
        }

        /* Sección critica */
        --enteroCompartido;
        /* Fin Sección critica */

        turn = 3;
        wantq = false;
      }
    }
  }

  /**
   * The type R.*
   *
   * @author Federico Carrillo Chaves
   * @version 1.3
   */
  class R extends Thread {
    public void run() {
      for (int i = 0; i < iterations; ++i) {
        /* Sección no critica */
        wantr = true;
        while (wantp || wantq) {
          if (turn != 3) {
            wantr = false;
            while (turn != 3) Thread.yield();
            wantr = true;
          }
        }

        /* Sección critica */
        ++enteroCompartido;
        /* Fin Sección critica */

        turn = 1;
        wantr = false;
      }
    }
  }

  /** Instantiates a new Alg dekker. */
  algDekker() {
    Thread p = new P();
    Thread q = new Q();
    Thread r = new R();
    p.start();
    q.start();
    r.start();

    try {
      p.join();
      q.join();
      r.join();
      System.out.println("Recurso compartido = " + enteroCompartido);
      System.out.println("Debería ser = " + iterations);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    long iniCrono = System.nanoTime();
    new algDekker();
    long totalTime = System.nanoTime() - iniCrono;
    System.out.println("totalTime = " + totalTime);
  }
}
