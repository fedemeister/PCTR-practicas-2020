package ej5;

/**
 * The type Red cajeros.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class redCajeros {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   * @throws InterruptedException the interrupted exception
   */
  public static void main(String[] args) throws InterruptedException {

    cuentaCorriente cuentaCorriente = new cuentaCorriente(0, 280995);

    int n = 1000;

    Thread[] threads = new Thread[n];
    cajero[] cajeros = new cajero[n];

    for (int i = 0; i < n; i++) {
      cajeros[i] =
          i % 2 != 0
              ? new cajero(false, cuentaCorriente, 100)
              : new cajero(true, cuentaCorriente, 100);
      threads[i] = new Thread(cajeros[i]);
      threads[i].start();
    }

    for (Thread thread : threads) {
      thread.join();
    }

    System.out.println("cuentaCorriente.getMoney() = " + cuentaCorriente.getMoney());
  }
}
