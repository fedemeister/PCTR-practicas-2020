package ej2;

/**
 * The type Matriz.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class Matriz {
  private final int sizeMatrix;
  private final double[][] matrixGenerated;

  /**
   * Instantiates a new Matriz.
   *
   * @param tam the tam
   */
  public Matriz(int tam) {
    this.sizeMatrix = tam;
    matrixGenerated = new double[sizeMatrix][sizeMatrix];
  }

  /**
   * Gets element.
   *
   * @param file the file
   * @param column the column
   * @return the element
   */
  public double getElement(int file, int column) {
    return (this.matrixGenerated[file][column]);
  }

  /**
   * Sets element.
   *
   * @param file the file
   * @param column the column
   * @param value the value
   */
  public void setElement(int file, int column, double value) {
    this.matrixGenerated[file][column] = value;
  }

  /** Gen random matrix. */
  public void genRandomMatrix() {
    for (int i = 0; i < sizeMatrix; i++) {
      for (int j = 0; j < sizeMatrix; j++) {
        matrixGenerated[i][j] = Math.random();
      }
    }
  }

  /**
   * Make transpose matriz.
   *
   * @return the matriz
   */
  public Matriz makeTranspose() {
    Matriz transposeMatrix = new Matriz(sizeMatrix);
    for (int i = 0; i < sizeMatrix; i++) {
      for (int j = 0; j < sizeMatrix; j++) {
        transposeMatrix.matrixGenerated[j][i] = matrixGenerated[i][j];
      }
    }
    return transposeMatrix;
  }
}
