package ej4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Algoritmo incorrecto de Hyman. El error de este algoritmo se da si un proceso llega a la línea
 * turno := X;. y se para. Luego otro proceso se encontrara que es su turno y saltandose el while.
 * Esto permite que los dos procesos puedan entrar en la sección crítica.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class algHyman implements Runnable {
  /** The . */
  int i;

  /** The enum State. */
  static enum State {
    /**
     * Indica que el proceso está ocioso con respecto a la sección crítica. es decir, no está ni
     * desea entrar en la sección crítica
     */
    RESTOPROCESO,
    /** Regleja la situación de que el proceso desea entrar en su sección crítiica */
    QUIEREENTRAR
  }

  /** The C 0. */
  static volatile State c0 = State.RESTOPROCESO;

  /** The C 1. */
  static volatile State c1 = State.RESTOPROCESO;

  /** The Turn. */
  static int turn = 0;

  /** The Shared resource. */
  static volatile int sharedResource = 0;

  /**
   * Instantiates a new Alg hyman.
   *
   * @param i the
   */
  public algHyman(int i) {
    this.i = i;
  }

  public void run() {
    IntStream.range(0, 100000).forEachOrdered(i == 0 ? (i -> processP0()) : (i -> processP1()));
  }

  /** Process P0. */
  void processP0() {
    c0 = State.QUIEREENTRAR;
    while (turn != 0) {
      while (c1 == State.QUIEREENTRAR) {}
      turn = 0;
    }
    sharedResource++;
    c0 = State.RESTOPROCESO;
  }

  /** Process P1. */
  void processP1() {
    c1 = State.QUIEREENTRAR;
    while (turn != 1) {
      while (c0 == State.QUIEREENTRAR) {}
      turn = 1;
    }
    sharedResource++;
    c1 = State.RESTOPROCESO;
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
    long iniCrono = System.nanoTime();
    newCachedThreadPool.execute(new algHyman(0));
    newCachedThreadPool.execute(new algHyman(1));
    newCachedThreadPool.shutdown();
    try {
      newCachedThreadPool.awaitTermination(100, TimeUnit.SECONDS);
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }
    long totalTime = System.nanoTime() - iniCrono;
    System.out.println("totalTime = " + totalTime);
    System.out.println("sharedResource = " + sharedResource);
    System.out.println("Has to be = " + 2 * 100000);
  }
}
