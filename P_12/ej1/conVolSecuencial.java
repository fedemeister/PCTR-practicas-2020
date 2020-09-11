package ej1;

import java.util.Scanner;

/** The type Con vol secuencial. */
public class conVolSecuencial {
  /** Instantiates a new Con vol secuencial. */
  public conVolSecuencial() {}

  /**
   * Print matrix.
   *
   * @param m the m
   */
  static void printMatrix(int[][] m) {
    for (int[] ints : m) {
      for (int j = 0; j < m.length; j++) {
        System.out.print(ints[j] + " ");
      }
      System.out.println();
    }
    System.out.println();
    System.out.println();
  }

  /**
   * Element int.
   *
   * @param m the m
   * @param i the
   * @param j the j
   * @return the int
   */
  int element(int[][] m, int i, int j) {
    return i < 0 || j < 0 || i > m.length - 1 || j > m.length - 1 ? 0 : m[i][j];
  }

  /**
   * Valid result int.
   *
   * @param n the n
   * @return the int
   */
  int validResult(int n) {
    if (n < 0) {
      return 0;
    }
    if (n > 255) {
      return 255;
    } else return n;
  }

  /**
   * Calc int.
   *
   * @param m the m
   * @param c the c
   * @param x the x
   * @param y the y
   * @return the int
   */
  int calc(int[][] m, int[][] c, int x, int y) {
    int suma = 0;
    int fil = 0;
    int col;
    for (int i = x - 1; i <= x + 1; i++) {
      col = 0;
      for (int j = y - 1; j <= y + 1; j++) {
        suma += element(m, i, j) * c[fil][col];
        col++;
      }
      fil++;
    }
    return suma;
  }

  /**
   * Convolution int [ ] [ ].
   *
   * @param M the m
   * @param C the c
   * @return the int [ ] [ ]
   */
  int[][] convolution(int[][] M, int[][] C) {
    int[][] res = new int[M.length][M.length];
    int sizeM = M.length;
    for (int i = 0; i < sizeM; i++) {
      for (int j = 0; j < sizeM; j++) {
        int aux = calc(M, C, i, j);
        res[i][j] = validResult(aux);
      }
    }
    return res;
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    conVolSecuencial conVolSecuencial = new conVolSecuencial();
    int[][] M = getMatrixM();

    int[][] ejemplo = getMatrixExample();

    int[][] enfoque = getMatrixEnfoque(0, 1);

    int[][] realbordes = getMatrix(0, 0, 0, 1, 1, 0);

    int[][] detecbordes = getMatrixDetecBordes();

    int[][] sobel = getMatrix(-1, 0, 1, 2, 0, 2);

    int[][] sharpen = getMatrix(1, -2, 1, 2, 5, -2);

    int op;
    int[][] kernel = new int[0][0];
    int[][] convolucionada;
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
        convolucionada = conVolSecuencial.convolution(M, kernel);
        System.out.println("Matriz Convolucionada");
        printMatrix(convolucionada);
        System.out.println();
        System.out.println();
      }

    } while (op != 0);
  }

  private static int[][] getMatrixDetecBordes() {
    return new int[][] {
      {0, 1, 0},
      {1, -4, 1},
      {0, 1, 0}
    };
  }

  private static int[][] getMatrix(int i, int i2, int i3, int i4, int i5, int i6) {
    return new int[][] {
      {i, i2, i3},
      {-i4, i5, i6},
      {i, i2, i3}
    };
  }

  private static int[][] getMatrixEnfoque(int i, int i2) {
    return new int[][] {
      {i, -i2, i},
      {-i2, 5, -i2},
      {i, -i2, i}
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
}
