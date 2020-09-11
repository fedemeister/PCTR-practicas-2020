package ej4;

import java.util.Arrays;
import java.util.Random;

import static ej4.escalaVector.fillVector;

/**
 * The type Escala v par.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class escalaVPar extends Thread {
  private static final int[] vector = new int[100000000];

  private static final int k = 5;

  private final int begin;
  private final int end;

  /**
   * Instantiates a new Escala v par.
   *
   * @param inicio the inicio
   * @param fin the fin
   */
  public escalaVPar(int inicio, int fin) {

    begin = inicio;
    end = fin;
  }

  public void run() {

    for (int i = begin; i < end; i++) {
      vector[i] *= k;
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    fillVector(vector);
    int availableProcessors = Runtime.getRuntime().availableProcessors();
    System.out.println("availableProcessors = " + availableProcessors);
    escalaVPar[] threads = new escalaVPar[availableProcessors];

    int parte = (vector.length / threads.length);


    for (int i = 0; i < threads.length - 1; i++) {
      threads[i] = new escalaVPar(i * parte, (i + 1) * parte);
      threads[i].start();
    }

    threads[threads.length - 1] = new escalaVPar((threads.length - 1) * parte, vector.length);
    threads[threads.length - 1].start();

    for (escalaVPar hilo : threads) {
      try {
        hilo.join();
      } catch (InterruptedException interruptedException) {
        interruptedException.printStackTrace();
      }

    }
  }

}
