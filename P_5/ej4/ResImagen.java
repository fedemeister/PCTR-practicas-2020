package ej4;

import java.util.Arrays;
import java.util.Random;

/**
 * The type Res imagen.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class ResImagen {
  /** The constant TAM. */
  public static int TAM = 100;

  /**
   * Fill image.
   *
   * @param matrix the matrix
   */
  public static void fillImage(int[][] matrix) {
    Random random = new Random(2);
    for (int i = 0; i < TAM; i++) {
      for (int j = 0; j < TAM; j++) {
        matrix[i][j] = random.nextInt(256);
      }
    }
  }

  /**
   * Print matrix.
   *
   * @param matrix the matrix
   */
  static void printMatrix(int[][] matrix) {
    for (int i = 0; i < TAM; ++i) {
      System.out.println(Arrays.toString(matrix[i]));
    }
  }

  /**
   * Do resaltado int [ ] [ ].
   *
   * @param matrix the matrix
   * @return El resaltado de la matriz
   */
  public static int[][] doResaltado(int[][] matrix) {
    int[][] matrixSolution = new int[TAM][TAM];
    int i = 0;
    while (i < matrix.length) {
      for (int j = 0; j < matrix.length; j++) {
        int beforeI = i - 1;
        int beforeJ = j - 1;
        if (beforeI < 0) beforeI = TAM - 1;
        if (beforeJ < 0) beforeJ = TAM - 1;
        matrixSolution[i][j] =
            (4 * matrix[i][j]
                    - matrix[(i + 1) % TAM][j]
                    - matrix[i][(j + 1) % TAM]
                    - matrix[beforeI][j]
                    - matrix[i][beforeJ])
                / 8;
        if (matrixSolution[i][j] < 0) matrixSolution[i][j] = 0;
      }
      i++;
    }
    return matrixSolution;
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    int[][] image = new int[TAM][TAM];
    fillImage(image);
    System.out.println("IMAGEN ORIGINAL");
    printMatrix(image);
    image = doResaltado(image);
    System.out.println("\n\n\nIMAGEN DESPUÉS DEL RESALTADO");
    printMatrix(image);
  }
}
