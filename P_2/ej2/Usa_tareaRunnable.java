package ej2;

/**
 * The type Usa tarea runnable.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class Usa_tareaRunnable {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    tareaRunnable runnable_1 = new tareaRunnable(true);
    tareaRunnable runnable_2 = new tareaRunnable(false);

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
    System.out.println("runnable_1.getN() = " + runnable_1.getN());
    System.out.println("runnable_2.getN() = " + runnable_2.getN());
    System.out.println("es una variable static. es la misma para los 2");
  }
}
