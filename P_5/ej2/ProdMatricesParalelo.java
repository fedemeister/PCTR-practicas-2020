package ej2;

import java.util.concurrent.*;
import java.util.*;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * The type Prod matrices paralelo.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class ProdMatricesParalelo implements Runnable {

  /** The constant N. */
  public static final int N = 1000; // 10^4 me peta incluso poniendo el heap a 2048 MB

  /** The constant matrixA. */
  public static final double[][] matrixA = new double[N][N];

  /** The constant matrixB. */
  public static final double[][] matrixB = new double[N][N];

  /** The constant matrixSolution. */
  public static double[][] matrixSolution = new double[N][N];

  private final int startRow;
  private final int endRow;

  /**
   * Instantiates a new Prod matrices paralelo.
   *
   * @param startRow the start row
   * @param endRow the end row
   */
  public ProdMatricesParalelo(int startRow, int endRow) {
    this.startRow = startRow;
    this.endRow = endRow;
  }

  @Override
  public void run() {
    for (int i = startRow; i < endRow; i++)
      for (int j = 0; j < N; j++) {
        matrixSolution[i][j] = 0;
        for (int k = 0; k < N; k++) {
          matrixSolution[i][j] += matrixA[i][k] * matrixB[k][j];
        }
      }
  }

  /**
   * Fill matrix.
   *
   * @param matrix the matriz
   */
  public static void fillMatrix(double[][] matrix) {
    Random random = new Random(2);
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        matrix[i][j] = random.nextInt(50);
      }
    }
  }

  /**
   * Show matrix.
   *
   * @param matrix the matriz
   */
  static void showMatrix(double[][] matrix) {
    for (int i = 0; i < N; ++i) {
      System.out.println(Arrays.toString(matrix[i]));
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {

    fillMatrix(matrixA);
    fillMatrix(matrixB);

    int availableProcessors = Runtime.getRuntime().availableProcessors();
    int cb = 0; // coeficiente de bloqueo = 0 es cuando las tareas son independientes.
    int numTasks = availableProcessors / (1 - cb);
    int window = N / numTasks;
    ProdMatricesParalelo[] vectorProdMatricesParalelo = new ProdMatricesParalelo[numTasks];

    IntStream.range(0, vectorProdMatricesParalelo.length - 1)
        .forEach(
            i ->
                vectorProdMatricesParalelo[i] =
                    new ProdMatricesParalelo(window * i, (i + 1) * window));

    vectorProdMatricesParalelo[numTasks - 1] = new ProdMatricesParalelo(window * (numTasks - 1), N);

    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(numTasks);

    long iniCronom = System.currentTimeMillis();
    Arrays.stream(vectorProdMatricesParalelo, 0, numTasks)
        .forEachOrdered(newFixedThreadPool::execute);

    newFixedThreadPool.shutdown();
    try {
      newFixedThreadPool.awaitTermination(10000, TimeUnit.MILLISECONDS);
    } catch (InterruptedException I) {
      I.printStackTrace();
    }
    long finCronom = System.currentTimeMillis();

    System.out.println("Paralelo con " + numTasks + " hilos: " + (finCronom - iniCronom) + " ms");
  }
}
