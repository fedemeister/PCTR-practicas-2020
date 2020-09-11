package ej2;

/**
 * The type Drakkar vikingo.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class DrakkarVikingo {
  private int numActualAnguilas;
  private final int numMaxAnguilas;
  private boolean hayQueCocinar;
  private boolean ocupedLine;

  /** Instantiates a new Drakkar vikingo. */
  DrakkarVikingo() {
    numMaxAnguilas = 8;
    ocupedLine = false;
    hayQueCocinar = false; // Indica que el cocinero no ha de cocinar al incio de la ejecución
    numActualAnguilas = numMaxAnguilas;
  }

  /** Cook. */
  public synchronized void cook() {
    try {
      while (!hayQueCocinar) {
        wait();
      }
      System.out.println("\n\tRellenándose la Marmita \n");
      numActualAnguilas = numMaxAnguilas;
      hayQueCocinar = false;
      notifyAll();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Eat.
   *
   * @param id the id
   */
  public synchronized void eat(int id) {
    try {
      while (ocupedLine) {
        wait();
      }

      ocupedLine = true; // coge esa fila

      while (numActualAnguilas == 0) {
        hayQueCocinar = true;
        notifyAll();
        wait();
      }
      System.out.printf("\n>Vikingo %d come --> Marmita = %d.\n", id, numActualAnguilas);
      numActualAnguilas--;
      ocupedLine = false;

      wait(1400);
      notifyAll();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
