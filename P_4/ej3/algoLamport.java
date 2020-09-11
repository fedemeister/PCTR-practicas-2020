package ej3;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Algo lamport.* * Basado en el algoritmo página 73 del libro Programación Concurrente ->
 * José Tomás Palma Méndez * [Pal03] * *
 *
 * <p>El algoritmo siguiente soluciona el problema de la exclusión mútua para N procesos y se *
 * puede usar en entornos distrubuidos donde un proceso puede acceder a la memoria de otro proceso *
 * sólo para leer. * *
 *
 * <p>Un inconveniente de este algoritmo es que los números que cogen los procesos pueden aumentar *
 * a lo largo de la ejecución, con lo que puede que sobrepase la capacidad de cualquier tipo de *
 * datos con el que se representen estos números. Por lo tanto, esta solución no debe ser usada en *
 * modelos de alto grado de concurrencia.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class algoLamport implements Runnable { // ALGORITMO DE LA PANADERÍA
  /** The number of iterations. */
  static final int iterations = 20000000;

  /** The Number of process. */
  static int numberOfProcess;

  /** The Turn. */
  static volatile int turn = 0;

  /** The Shared resource. */
  static volatile int sharedResource = 0;

  /** The Scanner. */
  static Scanner scanner = new Scanner(System.in);

  /** The constant ticket. */
  // Variables globales
  static int[] ticket = new int[numberOfProcess];

  /** The Entering. */
  static boolean[] entering = new boolean[numberOfProcess];

  /** The . */
  int i; // Índice de cada hilo

  /**
   * Instantiates a new Algo lamport.
   *
   * @param i the
   */
  algoLamport(int i) { // Constructor
    this.i = i;
    ticket[this.i] = 0;
    entering[this.i] = false;
  }

  /**
   * Gets max of ticket.
   *
   * @param ticket the ticket
   * @return the max of ticket
   */
  public static int getMaxOfTicket(int[] ticket) {
    int max = ticket[0];
    for (int i = 0; i < numberOfProcess; i++) {
      if (ticket[i] > max) {
        max = ticket[i];
      }
    }
    return max;
  }

  /** Lock. */
  public void lock() {
    // LOCKER
    entering[this.i] = true;
    // int max = Arrays.stream(ticket).max().getAsInt();
    int max = getMaxOfTicket(ticket);

    ticket[this.i] = max + 1;
    entering[this.i] = false;

    for (int w = 0; w < numberOfProcess; w++) {
      if (w != this.i) {
        while (entering[w]) {
          Thread.yield();
        }
        while (ticket[w] != 0
            && (ticket[this.i] > ticket[w] || (ticket[this.i] == ticket[w] && this.i > w)))
          Thread.yield();
      }
    }
  }

  /** Unlock. */
  public void unlock() {
    ticket[this.i] = 0;
  }

  public void run() {
    for (int i = 0; i < iterations; i++) {
      lock();
      // Sección crítica
      sharedResource++;
      // Fin de la sección crítica
      unlock();
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    System.out.print("Número de hilos: ");
    numberOfProcess = scanner.nextInt();
    long iniCrono = System.nanoTime();
    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(numberOfProcess);
    ticket = new int[numberOfProcess];
    entering = new boolean[numberOfProcess];

    double t = System.currentTimeMillis();
    for (int i = 0; i < numberOfProcess; i++) {
      newFixedThreadPool.execute(new algoLamport(i));
    }
    newFixedThreadPool.shutdown();
    while (!newFixedThreadPool.isTerminated()) {}
    long totalTime = System.nanoTime() - iniCrono;
    System.out.println("totalTime = " + totalTime);
    System.out.println("sharedResource = " + sharedResource);
    System.out.println("Has to be = " + numberOfProcess * iterations);

  }
}
