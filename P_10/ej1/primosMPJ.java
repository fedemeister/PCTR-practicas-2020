package ej1;

import mpi.*;
import java.lang.Math;
import java.util.Arrays;

/**
 * The type Primos mpj.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class primosMPJ {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    int numProcess;
    int myId;
    var n = 100000;
    var primeNumbers1 = new int[1];
    var primeNumbers2 = new int[1];
    var range = new int[1];
    range[0] = n;

    MPI.Init(args);

    var iniT = MPI.Wtime();

    myId = MPI.COMM_WORLD.Rank();
    numProcess = MPI.COMM_WORLD.Size();
    var r = new int[1];
    r[0] = range[0] / (int) numProcess;

    MPI.COMM_WORLD.Bcast(r, 0, 0, MPI.INT, 0);
    var top = (myId + 1) * r[0];

    for (int i = myId * r[0]; i < top; i++) {
      if (primosMPJ.isPrime(i)) {
        primeNumbers1[0]++;
      }
    }

    System.out.println("myId = " + myId);
    System.out.println("top = " + top);
    System.out.println("primeNumbers1 = " + Arrays.toString(primeNumbers1));

    MPI.COMM_WORLD.Reduce(primeNumbers1, 0, primeNumbers2, 0, 1, MPI.INT, MPI.SUM, 0);

    if (myId == 0) {
      System.out.println(primeNumbers2[0] + " primos encontrados");
      double time = MPI.Wtime() - iniT;
      double tiempo = time * Math.pow(10, 9);
      System.out.println("tiempo=" + tiempo);
    }

    MPI.Finalize();
  }

  /**
   * Is prime boolean.
   *
   * @param number the number
   * @return the boolean
   */
  public static boolean isPrime(int number) {
    boolean flagIsPrime = true;
    if (number == 0 || number == 1) {
      flagIsPrime = false;
    }
    for (int i = 2; i < number && flagIsPrime; i++) {
      if (number % i == 0) {
        flagIsPrime = false;
      }
    }
    return flagIsPrime;
  }
}
