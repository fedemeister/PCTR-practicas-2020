package P3_ej2;

import java.util.Arrays;
import java.util.Random;

/**
 * The type Mat vector.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class MatVector {
  /** The Tam. */
  static final int TAM = 10000; // buen troleo la matriz de 1000000

  /** The Vector b. */
  static int[] vectorB = new int[TAM];

  /** The Matrix a. */
  static int[][] matrixA = new int[TAM][TAM];

  /** The Vector y. */
  static int[] vectorY = new int[TAM];

  /**
   * Product beetween two matrix.
   *
   * @param mat_A the mat a
   * @param vec_B the vec b
   * @return the result of the product
   */
  static int[] product(int[][] mat_A, int[] vec_B) {
    int[] vec_Y = new int[TAM];
    for (int i = 0; i < mat_A.length; ++i) {
      for (int j = 0; j < vec_B.length; ++j) {
        vec_Y[i] += vec_B[i] * mat_A[i][j];
      }
    }
    return vec_Y;
  }

  /** Print matrix. */
  static void printMatrix() {
    System.out.println("Matriz A\n");
    for (int i = 0; i < TAM; ++i) System.out.println(Arrays.toString(matrixA[i]));
  }

  /** Print vector b. */
  static void printVectorB() {
    System.out.println("Vector B\n" + Arrays.toString(vectorB));
  }

  /** Print vector y. */
  static void printVectorY() {
    System.out.println("Vector Y\n" + Arrays.toString(vectorY));
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    Random random = new Random(2);
    for (int i = 0; i < TAM; ++i) {
      vectorB[i] = random.nextInt(50);
      for (int j = 0; j < TAM; ++j) matrixA[i][j] = random.nextInt(50);
    }
    long iniCronom = System.currentTimeMillis();
    vectorY = product(matrixA, vectorB);
    long finCronom = System.currentTimeMillis(); // se para el cronometro

    printVectorY();

    System.out.println("Secuencial: " + (finCronom - iniCronom) + " ms");
  }
}
