package ej2;

import java.util.concurrent.*;

/**
 * The type Usamonitor cadena.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class UsamonitorCadena implements Runnable {

  private final int id;
  private final MonitorCadena_1 buffer_1;
  private final MonitorCadena_1 buffer_2;

  /**
   * Instantiates a new Usamonitor cadena.
   *
   * @param t the t
   * @param buffer_1 the buffer 1
   * @param buffer_2 the buffer 2
   */
  public UsamonitorCadena(int t, MonitorCadena_1 buffer_1, MonitorCadena_1 buffer_2) {
    this.id = t;
    this.buffer_1 = buffer_1;
    this.buffer_2 = buffer_2;
  }

  public void run() {
    switch (id) {
      case 0:
        Matriz matriz_procesoA = new Matriz(10);
        matriz_procesoA.genRandomMatrix();
        buffer_1.insert(matriz_procesoA);
        break;
      case 1:
        Matriz matriz_procesoB = buffer_1.extract();
        matriz_procesoB = matriz_procesoB.makeTranspose();
        buffer_2.insert(matriz_procesoB);
        break;
      case 2:
        Matriz matriz_procesoC = buffer_2.extract();
        double mainDiagonalProduct = 1;
        for (int i = 0; i < 10; i++) {
          mainDiagonalProduct *= matriz_procesoC.getElement(i, i);
        }
        System.out.println("mainDiagonalProduct = " + mainDiagonalProduct);
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + id);
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    int nThreads = 10;
    Object lockObject = new Object();
    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(nThreads);

    MonitorCadena_1 buffer_1 = new MonitorCadena_1(100); // procesoA
    MonitorCadena_1 buffer_2 = new MonitorCadena_1(50); // procesoB
    for (int i = 0; i < nThreads; i++) {
      newFixedThreadPool.submit(new UsamonitorCadena(i % 3, buffer_1, buffer_2)); // procesoC
    }
    newFixedThreadPool.shutdown();
    while (!newFixedThreadPool.isTerminated()) {}
  }
}
