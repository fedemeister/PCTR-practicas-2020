package ej2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * El Algoritmo de Eisemberg-McGuire. Basado en el algoritmo página 70 y 71 del libro Programación
 * Concurrente -> José Tomás Palma * Méndez [Pal03] *
 *
 * <p>(1) Comprueba circularmente que ente el que tenía permiso para entrar y él, todos los *
 * procesos se encuentran en el resto del proceso. (2) Si alguno no está en el resto del proceso *
 * (Resto_i), vuelve a comprobar desde el principio. (3) Si hay algún proceso en la sección *
 * crítica, espera. (4) Hasta que ninguno está en la sección crítica e i tiene el turno o el que *
 * lo tiene está en Resto_i. (5) Da turno al primero que no está en el resto del proceso, es *
 * decir, al primero que quiere entrar. Si ninguno quiere entrar, se queda con el turno, ya que él *
 * no está en el resto del proceso.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class algEisenbergMcGuire implements Runnable {
  /** The Shared resource. */
  static volatile int sharedResource = 0;
  /** The Turn. */
  static volatile int turn = 0;
  /** The number of iterations. */
  static final int iterations = 20000000;
  /** The number of threads. */
  static final int NUMBER_OF_THREADS = 2;
  /** The constant newFixedThreadPool. */
  static ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

  /** The corresponding turn to each thread. It is initialized in the constructor. */
  int i;

  /** The enum State. */
  static enum State {
    /**
     * Indica que el proceso está ocioso con respecto a la sección crítica. es decir, no está ni
     * desea entrar en la sección crítica
     */
    RESTOPROCESO,
    /** Regleja la situación de que el proceso desea entrar en su sección crítiica */
    QUIEREENTRAR,
    /** Significa que el proceso se encuentra ejecutando su sección crítica. */
    ENSECCIONCRITICA;
  };

  /** The Flag. */
  State[] flag = new State[NUMBER_OF_THREADS];

  /**
   * Instantiates a new Alg eisenberg mc guire. Todos los elementos del array tendrán inicialmente
   * el valor RESTOPROCESO y el valor inicial de la variable i es indistinto, es decir, puede ser
   * cualquier entero entre 0 y N-1.
   *
   * @param i indica qué proceso puede entrar en la sección crítiica.
   */
  algEisenbergMcGuire(int i) {
    this.i = i;
    flag[this.i] = State.RESTOPROCESO;
  }

  public void run() {
    for (int i = 0; i < iterations; ++i) {
      int index;
      do {
        /* Seccion no critica */
        flag[this.i] = State.QUIEREENTRAR;
        index = turn;
        while (index != this.i) {
          if (flag[index] != State.RESTOPROCESO) {
            index = turn;
          } else {
            index = (index + 1) % NUMBER_OF_THREADS;
          }
        }
        flag[this.i] = State.ENSECCIONCRITICA;
        index = 0;
        while ((index < NUMBER_OF_THREADS)
            && ((index == this.i) || (flag[index] != State.ENSECCIONCRITICA))) {
          index = index + 1;
        }
      } while ((index < NUMBER_OF_THREADS)
          && ((turn != this.i) || (flag[turn] != State.RESTOPROCESO)));

      /* Seccion critica */
      turn = this.i;
      sharedResource++;
      /* Fin Seccion critica */
      index = (turn + 1) % NUMBER_OF_THREADS;
      while (flag[index] == State.RESTOPROCESO) {
        index = (index + 1) % NUMBER_OF_THREADS;
      }
      turn = index;
      flag[this.i] = State.RESTOPROCESO;
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args){
    long iniCrono = System.nanoTime();
    for (int i = 0; i < NUMBER_OF_THREADS; i++) {
      newFixedThreadPool.execute(new algEisenbergMcGuire(i));
    }
    newFixedThreadPool.shutdown();
    while (!newFixedThreadPool.isTerminated()) {}
    long totalTime = System.nanoTime() - iniCrono;
    System.out.println("totalTime = " + totalTime);
    System.out.println("sharedResource = " + sharedResource);
    System.out.println("Has to be = " + NUMBER_OF_THREADS * iterations);
  }
}
