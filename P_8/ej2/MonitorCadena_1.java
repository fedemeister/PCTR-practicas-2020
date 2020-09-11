package ej2;

/**
 * The type Monitor cadena 1.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class MonitorCadena_1 {
  private Matriz[] buffer = null;
  private int numSlots = 0;
  private int counter = 0;
  private int putIn = 0, takeOut = 0;

  /**
   * Instantiates a new Monitor cadena 1.
   *
   * @param numSlots the num slots
   */
  public MonitorCadena_1(int numSlots) {
    this.numSlots = numSlots;
    buffer = new Matriz[numSlots];
  }

  /**
   * Insert.
   *
   * @param value the value
   */
  public synchronized void insert(Matriz value) {
    while (counter == numSlots)
      try {
        wait();
      } catch (InterruptedException e) {
        System.err.println("wait interrumpido");
      }
    buffer[putIn] = value;
    putIn = (putIn + 1) % numSlots;
    counter++;
    notifyAll();
  }

  /**
   * Extract matriz.
   *
   * @return the matriz
   */
  public synchronized Matriz extract() {
    Matriz value;
    while (counter == 0)
      try {
        wait();
      } catch (InterruptedException e) {
        System.err.println("----wait_interrumpido-----");
      }
    value = buffer[takeOut];
    takeOut = (takeOut + 1) % numSlots;
    counter--;
    notifyAll();
    return value;
  }
}
