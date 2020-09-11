package ej5;

import java.util.Scanner;
import java.util.concurrent.*;
import java.util.ArrayList;

/**
 * The type Num perfectos.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class numPerfectos implements Callable {

  private final int start;
  private final int end;

  /**
   * Instantiates a new Num perfectos.
   *
   * @param start the start
   * @param end the end
   */
  public numPerfectos(int start, int end) {
    this.start = start;
    this.end = end;
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    int availableProcessors = Runtime.getRuntime().availableProcessors();
    Scanner scanner = new Scanner(System.in);
    System.out.println(
        "Introduzca por teclado la cantidad de números a averiguar si son números perfectos: ");
    int numbers = scanner.nextInt();
    int window = numbers / availableProcessors;

    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(availableProcessors);
    Callable[] callableTasks = new Callable[availableProcessors];

    for (int i = 0; i < availableProcessors - 1; i++) {
      callableTasks[i] = new numPerfectos(i * window, (i + 1) * window);
    }

    callableTasks[availableProcessors - 1] =
        new numPerfectos(window * (availableProcessors - 1), numbers);

    ArrayList<Future<Integer>> futureArrayList = new ArrayList<Future<Integer>>();
    for (int i = 0; i < availableProcessors; i++)
      futureArrayList.add(newFixedThreadPool.submit(callableTasks[i]));

    int[] results = new int[availableProcessors];
    int perfectNumbers = 0;
    int it = 0;
    for (Future<Integer> iterator : futureArrayList) {
      try {
        results[it] = iterator.get();
      } catch (InterruptedException I) {
        I.printStackTrace();
      } catch (CancellationException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }

      System.out.println(
          "La hebra " + (it + 1) + " encontró " + results[it] + " números perfectos");

      it++;
    }

    newFixedThreadPool.shutdown();
    try {
      newFixedThreadPool.awaitTermination(100000, TimeUnit.MILLISECONDS);

    } catch (InterruptedException I) {
      I.printStackTrace();
    }

    for (int resultado : results) {
      perfectNumbers += resultado;
    }

    System.out.println("Hay " + perfectNumbers + " números perfectos del 0 al " + numbers);
  }

  @Override
  public Object call() throws Exception {
    Integer counter = 0;
    int aux = 0;
    int i = start;
    while (i < end) {
      aux = 0;
      int j = 1;
      while (j < i) {
        if (i % j == 0) {
          aux += j;
        }
        j++;
      }
      if (aux == i) {
        counter++;
        System.out.println("aux = " + aux);
      }
      i++;
    }
    return counter;
  }
}
