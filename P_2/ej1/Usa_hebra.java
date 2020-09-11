package ej1;

/**
 * The type Usa hebra.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
class Usa_hebra {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {

    hebra thread_1 = new hebra(true);
    hebra thread_2 = new hebra(false);

    thread_1.start();
    thread_2.start();

    try {
      thread_1.join();
      thread_2.join();
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }

    System.out.println("n = " + hebra.getN());
  }
}
