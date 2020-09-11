package P3_ej2;

import java.util.Arrays;
import java.util.Random;

/**
 * The type Mat vector concurrente.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class matVectorConcurrente implements Runnable {
  /** The Tam. */
  static final int TAM = 10000; // buen troleo la matriz de 1000000

  /** The Vector b. */
  static final int[] vectorB = new int[TAM];

  /** The Matrix a. */
  static final int[][] matrixA = new int[TAM][TAM];

  /** The Vector y. */
  static final int[] vectorY = new int[TAM];

  private final int startRow;
  private final int endRow;

  /**
   * Instantiates a new Mat vector concurrente.
   *
   * @param startRow the start row
   * @param endRow the end row
   * @param vectorB
   * @param matrixA
   * @param vectorY
   */
  public matVectorConcurrente(
      int startRow, int endRow, int[] vectorB, int[][] matrixA, int[] vectorY) {
    this.startRow = startRow;
    this.endRow = endRow;
  }

  @Override
  public void run() {
    for (int i = startRow; i < endRow; i++) {
      for (int j = 0; j < vectorY.length; j++) {
        vectorY[i] += vectorB[i] * matrixA[i][j];
      }
    }
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
    int numHilos = 1;
    Thread[] hilos = new Thread[numHilos];


    // Número de hebras = 2
    long iniCronom = System.currentTimeMillis();
    Runnable dosHebras_hilo1 = new matVectorConcurrente(0, 4999, vectorB, matrixA, vectorY);
    Runnable dosHebras_hilo2 = new matVectorConcurrente(5000, 9999, vectorB, matrixA, vectorY);

    Thread h1_dosHebras_hilo1 = new Thread(dosHebras_hilo1);
    Thread h2_dosHebras_hilo2 = new Thread(dosHebras_hilo2);
    h1_dosHebras_hilo1.start();
    h2_dosHebras_hilo2.start();

    try {
      h1_dosHebras_hilo1.join();
      h2_dosHebras_hilo2.join();
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }
    long finCronom = System.currentTimeMillis(); // se para el cronometro
    System.out.println(2 + " hebras: " + (finCronom - iniCronom) + " ms");



    // Número de hebras = 4
    iniCronom = System.currentTimeMillis();
    Runnable cuatroHebras_hilo1 = new matVectorConcurrente(0, 2499, vectorB, matrixA, vectorY);
    Runnable cuatroHebras_hilo2 = new matVectorConcurrente(2500, 4999, vectorB, matrixA, vectorY);
    Runnable cuatroHebras_hilo3 = new matVectorConcurrente(5000, 7499, vectorB, matrixA, vectorY);
    Runnable cuatroHebras_hilo4 = new matVectorConcurrente(7500, 9999, vectorB, matrixA, vectorY);

    Thread h1_cuatroHebras_hilo1 = new Thread(cuatroHebras_hilo1);
    Thread h2_cuatroHebras_hilo2 = new Thread(cuatroHebras_hilo2);
    Thread h3_cuatroHebras_hilo3 = new Thread(cuatroHebras_hilo3);
    Thread h4_cuatroHebras_hilo4 = new Thread(cuatroHebras_hilo4);

    h1_cuatroHebras_hilo1.start();
    h2_cuatroHebras_hilo2.start();
    h3_cuatroHebras_hilo3.start();
    h4_cuatroHebras_hilo4.start();
    try {
      h1_cuatroHebras_hilo1.join();
      h2_cuatroHebras_hilo2.join();
      h3_cuatroHebras_hilo3.join();
      h4_cuatroHebras_hilo4.join();
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }

    finCronom = System.currentTimeMillis(); // se para el cronometro
    System.out.println(4 + " hebras: " + (finCronom - iniCronom) + " ms");


    // Número de hebras = 8
    iniCronom = System.currentTimeMillis();

    Runnable ochoHebras_hilo1 = new matVectorConcurrente(0, 1299, vectorB, matrixA, vectorY);
    Runnable ochoHebras_hilo2 = new matVectorConcurrente(1300, 2599, vectorB, matrixA, vectorY);
    Runnable ochoHebras_hilo3 = new matVectorConcurrente(2600, 3799, vectorB, matrixA, vectorY);
    Runnable ochoHebras_hilo4 = new matVectorConcurrente(3800, 4999, vectorB, matrixA, vectorY);
    Runnable ochoHebras_hilo5 = new matVectorConcurrente(5000, 6199, vectorB, matrixA, vectorY);
    Runnable ochoHebras_hilo6 = new matVectorConcurrente(6200, 7399, vectorB, matrixA, vectorY);
    Runnable ochoHebras_hilo7 = new matVectorConcurrente(7400, 8599, vectorB, matrixA, vectorY);
    Runnable ochoHebras_hilo8 = new matVectorConcurrente(8600, 9999, vectorB, matrixA, vectorY);

    Thread h1_ochoHebras_hilo1 = new Thread(ochoHebras_hilo1);
    Thread h2_ochoHebras_hilo2 = new Thread(ochoHebras_hilo2);
    Thread h3_ochoHebras_hilo3 = new Thread(ochoHebras_hilo3);
    Thread h4_ochoHebras_hilo4 = new Thread(ochoHebras_hilo4);
    Thread h5_ochoHebras_hilo5 = new Thread(ochoHebras_hilo5);
    Thread h6_ochoHebras_hilo6 = new Thread(ochoHebras_hilo6);
    Thread h7_ochoHebras_hilo7 = new Thread(ochoHebras_hilo7);
    Thread h8_ochoHebras_hilo8 = new Thread(ochoHebras_hilo8);

    h1_ochoHebras_hilo1.start();
    h2_ochoHebras_hilo2.start();
    h3_ochoHebras_hilo3.start();
    h4_ochoHebras_hilo4.start();
    h5_ochoHebras_hilo5.start();
    h6_ochoHebras_hilo6.start();
    h7_ochoHebras_hilo7.start();
    h8_ochoHebras_hilo8.start();
    try {
      h1_ochoHebras_hilo1.join();
      h2_ochoHebras_hilo2.join();
      h3_ochoHebras_hilo3.join();
      h4_ochoHebras_hilo4.join();
      h5_ochoHebras_hilo5.join();
      h6_ochoHebras_hilo6.join();
      h7_ochoHebras_hilo7.join();
      h8_ochoHebras_hilo8.join();
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }

    finCronom = System.currentTimeMillis(); // se para el cronometro
    System.out.println(8 + " hebras: " + (finCronom - iniCronom) + " ms");

    // Número de hebras = 16
    iniCronom = System.currentTimeMillis();
    Runnable dieciseisHebras_hilo1 = new matVectorConcurrente(0, 999, vectorB, matrixA, vectorY);
    Runnable dieciseisHebras_hilo2 =
        new matVectorConcurrente(1000, 1999, vectorB, matrixA, vectorY);
    Runnable dieciseisHebras_hilo3 =
        new matVectorConcurrente(2000, 2999, vectorB, matrixA, vectorY);
    Runnable dieciseisHebras_hilo4 =
        new matVectorConcurrente(3000, 3999, vectorB, matrixA, vectorY);
    Runnable dieciseisHebras_hilo5 =
        new matVectorConcurrente(4000, 4999, vectorB, matrixA, vectorY);
    Runnable dieciseisHebras_hilo6 =
        new matVectorConcurrente(5000, 5999, vectorB, matrixA, vectorY);
    Runnable dieciseisHebras_hilo7 =
        new matVectorConcurrente(6000, 6999, vectorB, matrixA, vectorY);
    Runnable dieciseisHebras_hilo8 =
        new matVectorConcurrente(7000, 7999, vectorB, matrixA, vectorY);
    Runnable dieciseisHebras_hilo9 =
        new matVectorConcurrente(8000, 8999, vectorB, matrixA, vectorY);
    Runnable dieciseisHebras_hilo10 =
        new matVectorConcurrente(9000, 9999, vectorB, matrixA, vectorY);
    Runnable dieciseisHebras_hilo11 =
        new matVectorConcurrente(4000, 4999, vectorB, matrixA, vectorY);
    Runnable dieciseisHebras_hilo12 =
        new matVectorConcurrente(5000, 5999, vectorB, matrixA, vectorY);
    Runnable dieciseisHebras_hilo13 =
        new matVectorConcurrente(6000, 6999, vectorB, matrixA, vectorY);
    Runnable dieciseisHebras_hilo14 =
        new matVectorConcurrente(7000, 7999, vectorB, matrixA, vectorY);
    Runnable dieciseisHebras_hilo15 =
        new matVectorConcurrente(8000, 8999, vectorB, matrixA, vectorY);
    Runnable dieciseisHebras_hilo16 =
        new matVectorConcurrente(9000, 9999, vectorB, matrixA, vectorY);

    Thread h1_dieciseisHebras_hilo1 = new Thread(dieciseisHebras_hilo1);
    Thread h2_dieciseisHebras_hilo2 = new Thread(dieciseisHebras_hilo2);
    Thread h3_dieciseisHebras_hilo3 = new Thread(dieciseisHebras_hilo3);
    Thread h4_dieciseisHebras_hilo4 = new Thread(dieciseisHebras_hilo4);
    Thread h5_dieciseisHebras_hilo5 = new Thread(dieciseisHebras_hilo5);
    Thread h6_dieciseisHebras_hilo6 = new Thread(dieciseisHebras_hilo6);
    Thread h7_dieciseisHebras_hilo7 = new Thread(dieciseisHebras_hilo7);
    Thread h8_dieciseisHebras_hilo8 = new Thread(dieciseisHebras_hilo8);
    Thread h9_dieciseisHebras_hilo9 = new Thread(dieciseisHebras_hilo9);
    Thread h10_dieciseisHebras_hilo10 = new Thread(dieciseisHebras_hilo10);
    Thread h11_dieciseisHebras_hilo11 = new Thread(dieciseisHebras_hilo11);
    Thread h12_dieciseisHebras_hilo12 = new Thread(dieciseisHebras_hilo12);
    Thread h13_dieciseisHebras_hilo13 = new Thread(dieciseisHebras_hilo13);
    Thread h14_dieciseisHebras_hilo14 = new Thread(dieciseisHebras_hilo14);
    Thread h15_dieciseisHebras_hilo15 = new Thread(dieciseisHebras_hilo15);
    Thread h16_dieciseisHebras_hilo16 = new Thread(dieciseisHebras_hilo16);

    h1_dieciseisHebras_hilo1.start();
    h2_dieciseisHebras_hilo2.start();
    h3_dieciseisHebras_hilo3.start();
    h4_dieciseisHebras_hilo4.start();
    h5_dieciseisHebras_hilo5.start();
    h6_dieciseisHebras_hilo6.start();
    h7_dieciseisHebras_hilo7.start();
    h8_dieciseisHebras_hilo8.start();
    h9_dieciseisHebras_hilo9.start();
    h10_dieciseisHebras_hilo10.start();
    h11_dieciseisHebras_hilo11.start();
    h12_dieciseisHebras_hilo12.start();
    h13_dieciseisHebras_hilo13.start();
    h14_dieciseisHebras_hilo14.start();
    h15_dieciseisHebras_hilo15.start();
    h16_dieciseisHebras_hilo16.start();

    try {
      h1_dieciseisHebras_hilo1.join();
      h1_dieciseisHebras_hilo1.join();
      h2_dieciseisHebras_hilo2.join();
      h3_dieciseisHebras_hilo3.join();
      h4_dieciseisHebras_hilo4.join();
      h5_dieciseisHebras_hilo5.join();
      h6_dieciseisHebras_hilo6.join();
      h7_dieciseisHebras_hilo7.join();
      h8_dieciseisHebras_hilo8.join();
      h9_dieciseisHebras_hilo9.join();
      h10_dieciseisHebras_hilo10.join();
      h11_dieciseisHebras_hilo11.join();
      h12_dieciseisHebras_hilo12.join();
      h13_dieciseisHebras_hilo13.join();
      h14_dieciseisHebras_hilo14.join();
      h15_dieciseisHebras_hilo15.join();
      h16_dieciseisHebras_hilo16.join();
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }
    finCronom = System.currentTimeMillis(); // se para el cronometro
    System.out.println(16 + " hebras: " + (finCronom - iniCronom) + " ms");
    //printVectorY();
  }
}
