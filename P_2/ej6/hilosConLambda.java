package ej6;

/**
 * The type Hilos con lambda.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class hilosConLambda {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {

    Runnable runnable_1 =
        () -> {
          for (int i = 0; i < 10000; i++) {
            n++;
          }
        };
    Runnable runnable_2 =
        () -> {
          for (int i = 0; i < 10000; i++) {
            n--;
          }
        };

    Thread thread_1 = new Thread(runnable_1);
    Thread thread_2 = new Thread(runnable_2);

    thread_1.start();
    thread_2.start();

    try {
      thread_1.join();
      thread_2.join();
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }

    System.out.println("hilosConLambda.getN() = " + hilosConLambda.getN());
  }

  private static long n = 0;

  /**
   * Gets n.
   *
   * @return the n
   */
  public static long getN() {
    return n;
  }

  /**
   * Sets n.
   *
   * @param n the n
   */
  public static void setN(long n) {
    hilosConLambda.n = n;
  }
}
