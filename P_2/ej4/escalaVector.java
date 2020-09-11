package ej4;

import java.util.Arrays;
import java.util.Random;

/**
 * The type Escala vector.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class escalaVector {

  /**
   * Fill vector.
   *
   * @param v the v
   */
  static void fillVector(int[] v) {
    Random random = new Random();
    Arrays.setAll(v, i -> random.nextInt(100));
  }

  /**
   * Scale vector.
   *
   * @param v the v
   * @param n the n
   */
  static void scaleVector(int[] v, int n) {
    for (int i = 0; i < v.length; i++) {
      v[i] *= n;
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    int[] vector = new int[1000];
    fillVector(vector);
    Random random = new Random();
    var beforeScale = Arrays.toString(vector);
    scaleVector(vector, random.nextInt(50));
    var afterScale = Arrays.toString(vector);

    System.out.println("beforeScale = " + beforeScale);
    System.out.println("afterScale = " + afterScale);
  }
}
