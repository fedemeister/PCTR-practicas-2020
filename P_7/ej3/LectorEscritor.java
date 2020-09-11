package ej3;

// monitor para Reader Writer con prioridad para los lectores

/**
 * The type Reader Writer.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class LectorEscritor {

  private int nReaders = 0;
  /** The Someone writing. */
  boolean someoneWriting = false;

  /** The Shared Resource. */
  public int sharedResource = 0;

  /**
   * Start write. Prioridad en la lectura. Mientras haya algún proceso leyendo no se puede escribir
   * Si hay alguien escribiendo también se queda esperando.
   */
  public synchronized void startWrite() {
    while (someoneWriting || nReaders != 0) {
      try {
        wait();
      } catch (InterruptedException I) {
        I.printStackTrace();
      }
    }
    someoneWriting = true;
  }

  /** Start read. */
  public synchronized void startRead() {
    while (someoneWriting) {
      try {
        wait();
      } catch (InterruptedException I) {
        I.printStackTrace();
      }
    }
    nReaders++;
  }

  /** End write. */
  public synchronized void endWrite() {
    someoneWriting = false;
    notifyAll();
  }

  /** End read. */
  public synchronized void endRead() {
    nReaders--;
    if (nReaders == 0) {
      notifyAll();
    }
  }
}
