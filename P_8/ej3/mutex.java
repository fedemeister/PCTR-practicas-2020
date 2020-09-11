package ej3;

/**
 * The type Mutex.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class mutex extends Thread {
  private static int n = 0;
  private static monitorSemaforo S = new monitorSemaforo(1);
  private final int i;

  /**
   * Instantiates a new Mutex.
   *
   * @param id the id
   */
  public mutex(int id) {
    this.i = id;
  }

  @Override
  public void run() {
    switch (i) {
      case 0:
        for (int i = 0; i < 1000000; i++) {
          S.waitS();
          n++;
          S.signalS();
        }
        break;

      case 1:
        for (int i = 0; i < 1000000; i++) {
          S.waitS();
          n--;
          S.signalS();
        }
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + i);
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    mutex thread_1 = new mutex(1);
    mutex thread_2 = new mutex(0);

    thread_1.start();
    thread_2.start();

    try {
      thread_1.join();
      thread_2.join();
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }

    System.out.println("n = " + n);
  }
}
