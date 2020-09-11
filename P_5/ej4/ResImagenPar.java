package ej4;

import java.util.Arrays;
import java.util.concurrent.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;
import java.util.stream.IntStream;

/**
 * The type Res imagen par.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class ResImagenPar implements Runnable {
  private static int TAM;
  private static int[][] matrixA;
  private static int[][] matrixSolution;
  private final int startRow;
  private final int endRow;

  /**
   * Instantiates a new Res imagen par.
   *
   * @param startRow the start row
   * @param endRow the end row
   */
  public ResImagenPar(int startRow, int endRow) {
    this.startRow = startRow;
    this.endRow = endRow;
  }

  /**
   * Print vector.
   *
   * @param vector the vector
   */
  static void printVector(int[] vector) {
    System.out.println(Arrays.toString(vector));
  }

  private static void createImage() throws IOException {

    File f = new File("src/ej4/uca_gris_procesada.png");
    BufferedImage bufferedImage = ImageIO.read(f);
    TAM = bufferedImage.getWidth();
    matrixA = new int[TAM][TAM];

    int i = 0;
    while (i < TAM) {
      for (int j = 0; j < TAM; j++) {
        Color colors = new Color(bufferedImage.getRGB(i, j));
        matrixA[i][j] = ((colors.getRed() + colors.getGreen() + colors.getBlue()) / 3) * 20 / 255;
      }
      printVector(matrixA[i]);
      i++;
    }
  }

  public void run() {
    int beforeI;
    int beforeJ;

    int i = startRow;
    while (i < endRow) {
      for (int j = 0; j < TAM; ++j) {
        beforeI = i - 1;
        beforeJ = j - 1;
        if (beforeI < 0) beforeI = TAM - 1; // para que no se salga
        if (beforeJ < 0) beforeJ = TAM - 1;
        matrixSolution[i][j] =
            (4 * matrixA[i][j]
                    - matrixA[(i + 1) % TAM][j]
                    - matrixA[i][(j + 1) % TAM]
                    - matrixA[beforeI][j]
                    - matrixA[i][beforeJ])
                / 8;
      }
      ++i;
    }
  }

    private static void ejecutar() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int cb = 0; // coeficiente de bloqueo = 0 es cuando las tareas son independientes.
        int numTasks = availableProcessors / (1 - cb);
        if (numTasks > TAM) {
            numTasks = TAM;
        }
        int ventana = TAM / numTasks;
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(numTasks);

        long iniCronom = System.currentTimeMillis();

        IntStream.range(0, numTasks - 1)
                .mapToObj(i -> new ResImagenPar((i * ventana), (i + 1) * ventana))
                .forEach(newFixedThreadPool::execute);
        newFixedThreadPool.execute(new ResImagenPar((numTasks - 1) * ventana, numTasks));
        newFixedThreadPool.shutdown();
        try {
            newFixedThreadPool.awaitTermination(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException I) {
            I.printStackTrace();
        }

        long finCronom = System.currentTimeMillis();

        System.out.println("Paralelo con " + numTasks + " hilos: " + (finCronom - iniCronom) + " ms");
    }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   * @throws Exception the exception
   */
  public static void main(String[] args) throws Exception {
    createImage();
    matrixSolution = new int[TAM][TAM];
    ejecutar();

    try {
      BufferedImage bufferedImage = new BufferedImage(TAM, TAM, BufferedImage.TYPE_BYTE_GRAY);
      for (int i = 0; i < TAM; i++) {
        for (int j = 0; j < TAM; j++) {
          int a = (matrixSolution[i][j] * 250 / 20);
          System.out.print(matrixSolution[i][j] + " ");
          bufferedImage.setRGB(i, j, a);
        }
        System.out.println("");
      }
      System.out.println("");
      ImageIO.write(bufferedImage, "png", new File("src/ej4/resaltado.png"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
