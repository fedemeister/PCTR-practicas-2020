package ej1;

import java.util.Arrays;

/**
 * The type Monitor impresion.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class MonitorImpresion {
  private int numberAvailablePrinters;
  private final boolean[] vectorOfFreePrinters;

  /**
   * Instantiates a new Monitor impresion.
   *
   * @param numImpresoras the num impresoras
   */
  public MonitorImpresion(int numImpresoras) {
    numberAvailablePrinters = numImpresoras;
    vectorOfFreePrinters = new boolean[numImpresoras];

    Arrays.fill(vectorOfFreePrinters, true);
  }

  /**
   * Drop print.
   *
   * @param idPrinter the id printer
   */
  public synchronized void dropPrint(int idPrinter) {
    vectorOfFreePrinters[idPrinter] = true;
    ++numberAvailablePrinters;
    notifyAll();
  }

  /**
   * Take print.
   *
   * @return the number of the printer taked.
   */
  public synchronized int takePrint() {
    while (numberAvailablePrinters == 0)
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    int idPrinter = 0;
    while (!vectorOfFreePrinters[idPrinter]) {
      ++idPrinter;
    }
    vectorOfFreePrinters[idPrinter] = false;
    if (numberAvailablePrinters >= 0) {
      --numberAvailablePrinters;
    } else {
      numberAvailablePrinters = 0;
    }
    return idPrinter;
  }
}
