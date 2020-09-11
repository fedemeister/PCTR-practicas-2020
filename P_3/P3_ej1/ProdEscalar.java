package P3_ej1;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * The type Prod escalar.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class ProdEscalar {
  /** The constant N. */
  public static final int N = 1000000;

  /** The constant vector_1. */
  public static int[] vector_1 = new int[N];

  /** The constant vector_2. */
  public static int[] vector_2 = new int[N];

  /**
   * In mathematics, the dot product or scalar product[note 1] is an algebraic operation that takes
   * two equal-length sequences of numbers (usually coordinate vectors) and returns a single number
   *
   * @param vector_1 the vector 1
   * @param vector_2 the vector 2
   * @return the result of the dot product
   */
  static int dotProduct(int[] vector_1, int[] vector_2) {
    int xEscalar = 0;

    for (int i = 0; i < vector_1.length; i++) {
      xEscalar = xEscalar + (vector_1[i] * vector_2[i]);
    }
    return xEscalar;
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    int solution = 0;
    Random random = new Random(2);

    IntStream.range(0, vector_1.length)
        .forEachOrdered(
            i -> {
              vector_1[i] = random.nextInt(50);
              vector_2[i] = random.nextInt(50);
            });

    long iniCronom = System.currentTimeMillis();
    solution = dotProduct(vector_1, vector_2);
    long finCronom = System.currentTimeMillis();
    System.out.println("Secuencial: " + (finCronom - iniCronom) + " ms");
    System.out.println("\t\t" + solution);
  }
}
