package ej2;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/** The type Con vol paralela. */
public class conVolParalela implements Runnable {
  /**
   * Print matrix.
   *
   * @param mat the mat
   */
  public static void printMatrix(int[][] mat) {
    int size = mat.length;
    for (int[] ints : mat) {
      for (int j = 0; j < size; j++) {
        System.out.print(ints[j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  /** The Begin. */
  int begin;

  /** The End. */
  int end;

  /** The Matriz. */
  int[][] matriz;

  /** The Kernel. */
  int[][] kernel;

  /** The Solution. */
  int[][] solution;

  /**
   * Instantiates a new Con vol paralela.
   *
   * @param begin the begin
   * @param end the end
   * @param matriz the matriz
   * @param kernel the kernel
   * @param solution the solution
   */
  conVolParalela(int begin, int end, int[][] matriz, int[][] kernel, int[][] solution) {
    this.begin = begin;
    this.end = end;
    this.matriz = matriz;
    this.kernel = kernel;
    this.solution = solution;
  }

  /**
   * Elemento int.
   *
   * @param matrix the matrix
   * @param i the
   * @param j the j
   * @return the int
   */
  int elemento(int[][] matrix, int i, int j) {
    return i < 0 || j < 0 || i > matrix.length - 1 || j > matrix.length - 1 ? 0 : matrix[i][j];
  }

  /**
   * Resultadovalido int.
   *
   * @param n the n
   * @return the int
   */
  int resultadovalido(int n) {
    if (n < 0) {
      return 0;
    }
    return Math.min(n, 255);
  }

  /**
   * Calculo int.
   *
   * @param m the m
   * @param c the c
   * @param x the x
   * @param y the y
   * @return the int
   */
  int calculo(int[][] m, int[][] c, int x, int y) {
    int suma = 0;
    int fil = 0;
    int col = 0;
    for (int i = x - 1; i <= x + 1; i++) {
      col = 0;
      for (int j = y - 1; j <= y + 1; j++) {
        suma += elemento(m, i, j) * c[fil][col];
        col++;
      }
      fil++;
    }
    return suma;
  }

  public void run() {
    for (int i = begin; i < end; i++) {
      for (int j = 0; j < matriz.length; j++) {
        solution[i][j] = resultadovalido(calculo(matriz, kernel, i, j));
      }
    }
    System.out.println("recibo:" + begin + "-" + end);
  }

  private static int[][] getMatrixDetecBordes() {
    return new int[][] {
      {0, 1, 0},
      {1, -4, 1},
      {0, 1, 0}
    };
  }

  private static int[][] getMatrix(int i2, int i3, int i4, int i5, int i6, int i7) {
    return new int[][] {
      {i2, i3, i4},
      {-i5, i6, i7},
      {i2, i3, i4}
    };
  }

  private static int[][] getMatrixEnfoque() {
    return new int[][] {
      {0, -1, 0},
      {-1, 5, -1},
      {0, -1, 0}
    };
  }

  private static int[][] getMatrixExample() {
    return new int[][] {
      {-2, -1, 0},
      {-1, 1, 1},
      {0, 1, 2}
    };
  }

  private static int[][] getMatrixM() {
    return new int[][] {
      {35, 40, 41, 45, 50},
      {40, 40, 42, 46, 52},
      {42, 46, 50, 55, 55},
      {48, 52, 56, 58, 60},
      {56, 60, 65, 70, 75}
    };
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int[][] M = getMatrixM();

    int[][] ejemplo = getMatrixExample();

    int[][] enfoque = getMatrixEnfoque();

    int[][] realbordes = getMatrix(0, 0, 0, 1, 1, 0);

    int[][] detecbordes = getMatrixDetecBordes();

    int[][] sobel = getMatrix(-1, 0, 1, 2, 0, 2);

    int[][] sharpen = getMatrix(1, -2, 1, 2, 5, -2);

    int op;
    int[][] kernel = new int[0][0];
    int[][] convolucionada = new int[M.length][M.length];
    do {

      System.out.println("Introduce una opcion: ");
      System.out.println();
      System.out.println();
      System.out.println("0-Salir");
      System.out.println("1-Ejemplo");
      System.out.println("2-Enfoque");
      System.out.println("3-Detectar bordes");
      System.out.println("4-Realzar bordes");
      System.out.println("5-Sobel");
      System.out.println("6-Sharpen");
      op = scanner.nextInt();
      switch (op) {
        case 0:
          break;
        case 1:
          System.out.println("Matriz del ejemplo");
          kernel = ejemplo;
          break;
        case 2:
          System.out.println("Matriz para enfoque");
          kernel = enfoque;
          break;
        case 3:
          System.out.println("Matriz para detectar bordes");
          kernel = detecbordes;
          break;
        case 4:
          System.out.println("Matriz para realzar bordes");
          kernel = realbordes;
          break;
        case 5:
          System.out.println("Matriz Sobel");
          kernel = sobel;
          break;
        case 6:
          System.out.println("Matriz Sharpen");
          kernel = sharpen;
          break;
      }

      System.out.println();
      if (op != 0) {
        System.out.println("Matriz Original");
        printMatrix(M);
        System.out.println("Matriz Kernel");
        printMatrix(kernel);

        int size = M.length;
        int nThreads = Runtime.getRuntime().availableProcessors();
        conVolParalela[] tareas = new conVolParalela[nThreads];
        int window = size / nThreads;
        int nTasks = tareas.length;

        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(nThreads);

        for (int i = 0; i < nTasks - 1; i++) {
          tareas[i] = new conVolParalela(i * window, (i + 1) * window, M, kernel, convolucionada);
          newFixedThreadPool.execute(tareas[i]);
        }

        tareas[nTasks - 1] =
            new conVolParalela((nTasks - 1) * window, size, M, kernel, convolucionada);

        newFixedThreadPool.execute(tareas[nTasks - 1]);

        newFixedThreadPool.shutdown();

        try {
          newFixedThreadPool.awaitTermination(20000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException I) {
          I.printStackTrace();
        }

        System.out.println("Matriz Convolucionada");
        printMatrix(convolucionada);
        System.out.println();
        System.out.println();
      }

    } while (op != 0);
  }
}
