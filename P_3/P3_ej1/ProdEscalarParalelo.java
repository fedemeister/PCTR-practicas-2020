package P3_ej1;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * The type Prod escalar paralelo.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class ProdEscalarParalelo extends Thread {
  private final int idHebra;
  private final int inicio;
  private final int finall;

  private final int[] vector_1;
  private final int[] vector_2;
  private final int[] prodParcial;

  /**
   * Instantiates a new Prod escalar paralelo.
   *
   * @param idHebra the thread identifier
   * @param inicio the begin of the interval
   * @param finall the end of the interval
   * @param vector_1 the vector 1
   * @param vector_2 the vector 2
   * @param prodParcial the parcial result
   */
  public ProdEscalarParalelo(
      int idHebra, int inicio, int finall, int[] vector_1, int[] vector_2, int[] prodParcial) {
    this.idHebra = idHebra;
    this.inicio = inicio;
    this.finall = finall;
    this.vector_1 = vector_1;
    this.vector_2 = vector_2;
    this.prodParcial = prodParcial;
  }

  @Override
  public void run() {
    for (int i = inicio; i < finall + 1; ++i) {
      prodParcial[idHebra] += (vector_1[i] * vector_2[i]);
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    final int N = 1000000;
    int[] vector_1 = new int[N];
    int[] vector_2 = new int[N];
    int solution = 0;
    Random random = new Random(2); // utilizamos semilla para tener los vectores = en cada iter.

    IntStream.range(0, N)
        .forEachOrdered(
            i -> {
              vector_1[i] = random.nextInt(50);
              vector_2[i] = random.nextInt(50);
            });

    // 2 hebras
    long iniCronom = System.currentTimeMillis();

    int numHebras = 2;
    int[] productoParcial = new int[numHebras];

    Thread dosHebras_hilo1 =
        new ProdEscalarParalelo(0, 0, 499999, vector_1, vector_2, productoParcial);
    Thread dosHebras_hilo2 =
        new ProdEscalarParalelo(1, 500000, 999999, vector_1, vector_2, productoParcial);

    dosHebras_hilo1.start();
    dosHebras_hilo2.start();
    try {
      dosHebras_hilo1.join();
      dosHebras_hilo2.join();
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }

    for (int i = 0; i < numHebras; ++i) {
      solution += productoParcial[i];
    }

    long finCronom = System.currentTimeMillis(); // se para el cronometro
    System.out.println(numHebras + " hebras: " + (finCronom - iniCronom) + " ms");
    System.out.println("\t\t" + solution);

    // 4 hebras
    iniCronom = System.currentTimeMillis();

    numHebras = 4;
    productoParcial = new int[numHebras];
    Thread cuatroHebras_hilo1 =
        new ProdEscalarParalelo(0, 0, 249999, vector_1, vector_2, productoParcial);
    Thread cuatroHebras_hilo2 =
        new ProdEscalarParalelo(1, 250000, 499999, vector_1, vector_2, productoParcial);
    Thread cuatroHebras_hilo3 =
        new ProdEscalarParalelo(2, 500000, 749999, vector_1, vector_2, productoParcial);
    Thread cuatroHebras_hilo4 =
        new ProdEscalarParalelo(3, 750000, 999999, vector_1, vector_2, productoParcial);

    cuatroHebras_hilo1.start();
    cuatroHebras_hilo2.start();
    cuatroHebras_hilo3.start();
    cuatroHebras_hilo4.start();
    try {
      cuatroHebras_hilo1.join();
      cuatroHebras_hilo2.join();
      cuatroHebras_hilo3.join();
      cuatroHebras_hilo4.join();
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }

    solution = 0; // reiniciamos sol
    for (int i = 0; i < numHebras; ++i) {
      solution += productoParcial[i];
    }

    finCronom = System.currentTimeMillis(); // se para el cronometro
    System.out.println(numHebras + " hebras: " + (finCronom - iniCronom) + " ms");
    System.out.println("\t\t" + solution);

    // 6 hebras
    iniCronom = System.currentTimeMillis();

    numHebras = 6;
    productoParcial = new int[numHebras];
    Thread seisHebras_hilo1 =
        new ProdEscalarParalelo(0, 0, 169999, vector_1, vector_2, productoParcial);
    Thread seisHebras_hilo2 =
        new ProdEscalarParalelo(1, 170000, 329999, vector_1, vector_2, productoParcial);
    Thread seisHebras_hilo3 =
        new ProdEscalarParalelo(2, 330000, 489999, vector_1, vector_2, productoParcial);
    Thread seisHebras_hilo4 =
        new ProdEscalarParalelo(3, 490000, 649999, vector_1, vector_2, productoParcial);
    Thread seisHebras_hilo5 =
        new ProdEscalarParalelo(4, 650000, 809999, vector_1, vector_2, productoParcial);
    Thread seisHebras_hilo6 =
        new ProdEscalarParalelo(5, 810000, 999999, vector_1, vector_2, productoParcial);

    seisHebras_hilo1.start();
    seisHebras_hilo2.start();
    seisHebras_hilo3.start();
    seisHebras_hilo4.start();
    seisHebras_hilo5.start();
    seisHebras_hilo6.start();
    try {
      seisHebras_hilo1.join();
      seisHebras_hilo2.join();
      seisHebras_hilo3.join();
      seisHebras_hilo4.join();
      seisHebras_hilo5.join();
      seisHebras_hilo6.join();
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }

    solution = 0; // reiniciamos sol
    for (int i = 0; i < numHebras; ++i) {
      solution += productoParcial[i];
    }

    finCronom = System.currentTimeMillis(); // se para el cronometro
    System.out.println(numHebras + " hebras: " + (finCronom - iniCronom) + " ms");
    System.out.println("\t\t" + solution);

    // 8 hebras
    iniCronom = System.currentTimeMillis();

    numHebras = 8;
    productoParcial = new int[numHebras];
    Thread ochoHebras_hilo1 =
        new ProdEscalarParalelo(0, 0, 129999, vector_1, vector_2, productoParcial);
    Thread ochoHebras_hilo2 =
        new ProdEscalarParalelo(1, 130000, 259999, vector_1, vector_2, productoParcial);
    Thread ochoHebras_hilo3 =
        new ProdEscalarParalelo(2, 260000, 379999, vector_1, vector_2, productoParcial);
    Thread ochoHebras_hilo4 =
        new ProdEscalarParalelo(3, 380000, 499999, vector_1, vector_2, productoParcial);
    Thread ochoHebras_hilo5 =
        new ProdEscalarParalelo(4, 500000, 619999, vector_1, vector_2, productoParcial);
    Thread ochoHebras_hilo6 =
        new ProdEscalarParalelo(5, 620000, 739999, vector_1, vector_2, productoParcial);
    Thread ochoHebras_hilo7 =
        new ProdEscalarParalelo(6, 740000, 859999, vector_1, vector_2, productoParcial);
    Thread ochoHebras_hilo8 =
        new ProdEscalarParalelo(7, 860000, 999999, vector_1, vector_2, productoParcial);

    ochoHebras_hilo1.start();
    ochoHebras_hilo2.start();
    ochoHebras_hilo3.start();
    ochoHebras_hilo4.start();
    ochoHebras_hilo5.start();
    ochoHebras_hilo6.start();
    ochoHebras_hilo7.start();
    ochoHebras_hilo8.start();
    try {
      ochoHebras_hilo1.join();
      ochoHebras_hilo2.join();
      ochoHebras_hilo3.join();
      ochoHebras_hilo4.join();
      ochoHebras_hilo5.join();
      ochoHebras_hilo6.join();
      ochoHebras_hilo7.join();
      ochoHebras_hilo8.join();
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }

    solution = 0; // reiniciamos sol
    for (int i = 0; i < numHebras; ++i) {
      solution += productoParcial[i];
    }

    finCronom = System.currentTimeMillis(); // se para el cronometro
    System.out.println(numHebras + " hebras: " + (finCronom - iniCronom) + " ms");
    System.out.println("\t\t" + solution);

    // 10 hebras
    iniCronom = System.currentTimeMillis();

    numHebras = 10;
    productoParcial = new int[numHebras];

    Thread diezHebras_hilo1 =
        new ProdEscalarParalelo(0, 0, 99999, vector_1, vector_2, productoParcial);
    Thread diezHebras_hilo2 =
        new ProdEscalarParalelo(1, 100000, 199999, vector_1, vector_2, productoParcial);
    Thread diezHebras_hilo3 =
        new ProdEscalarParalelo(2, 200000, 299999, vector_1, vector_2, productoParcial);
    Thread diezHebras_hilo4 =
        new ProdEscalarParalelo(3, 300000, 399999, vector_1, vector_2, productoParcial);
    Thread diezHebras_hilo5 =
        new ProdEscalarParalelo(4, 400000, 499999, vector_1, vector_2, productoParcial);
    Thread diezHebras_hilo6 =
        new ProdEscalarParalelo(5, 500000, 599999, vector_1, vector_2, productoParcial);
    Thread diezHebras_hilo7 =
        new ProdEscalarParalelo(6, 600000, 699999, vector_1, vector_2, productoParcial);
    Thread diezHebras_hilo8 =
        new ProdEscalarParalelo(7, 700000, 799999, vector_1, vector_2, productoParcial);
    Thread diezHebras_hilo9 =
        new ProdEscalarParalelo(8, 800000, 899999, vector_1, vector_2, productoParcial);
    Thread diezHebras_hilo10 =
        new ProdEscalarParalelo(9, 900000, 999999, vector_1, vector_2, productoParcial);

    diezHebras_hilo1.start();
    diezHebras_hilo2.start();
    diezHebras_hilo3.start();
    diezHebras_hilo4.start();
    diezHebras_hilo5.start();
    diezHebras_hilo6.start();
    diezHebras_hilo7.start();
    diezHebras_hilo8.start();
    diezHebras_hilo9.start();
    diezHebras_hilo10.start();
    try {
      diezHebras_hilo1.join();
      diezHebras_hilo2.join();
      diezHebras_hilo3.join();
      diezHebras_hilo4.join();
      diezHebras_hilo5.join();
      diezHebras_hilo6.join();
      diezHebras_hilo7.join();
      diezHebras_hilo8.join();
      diezHebras_hilo9.join();
      diezHebras_hilo10.join();
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }

    solution = 0; // reiniciamos sol

    for (int i = 0; i < numHebras; ++i) {
      solution += productoParcial[i];
    }

    finCronom = System.currentTimeMillis(); // se para el cronometro
    System.out.println(numHebras + " hebras: " + (finCronom - iniCronom) + " ms");
    System.out.println("\t\t" + solution);
  }
}
