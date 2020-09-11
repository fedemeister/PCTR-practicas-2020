package ej2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type Imp monitor.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class ImpMonitor {
  private int numberAvailablePrinters;
  private final boolean[] vectorOfFreePrinters = {true, true, true};
  private final ReentrantLock reentrantLock;
  private final Condition conditionBusy;

  /** Instantiates a new Imp monitor. */
  public ImpMonitor() {
    this.numberAvailablePrinters = 3;
    reentrantLock = new ReentrantLock();
    conditionBusy = reentrantLock.newCondition();
  }

  /**
   * Take printer.
   *
   * @return the int of the printer.
   */
  int takePrinter() {
    reentrantLock.lock();

    while (numberAvailablePrinters == 0) {
      try {
        conditionBusy.await();
      } catch (InterruptedException I) {
        I.printStackTrace();
      }
    }
    int i = 0;
    while (!vectorOfFreePrinters[i]) {
      i++;
    }
    System.out.println("Impresora " + (i + 1) + " obtenida.");

    vectorOfFreePrinters[i] = false;
    numberAvailablePrinters--;

    reentrantLock.unlock();
    return i;
  }

  /**
   * Drop printer.
   *
   * @param i the
   */
  void dropPrinter(int i) {
    reentrantLock.lock();
    vectorOfFreePrinters[i] = true;
    numberAvailablePrinters++;
    conditionBusy.signal();
    reentrantLock.unlock();
  }
}
