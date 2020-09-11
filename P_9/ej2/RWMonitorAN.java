package ej2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type Rw monitor an.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class RWMonitorAN {
  /** The constant sharedResource. */
  static int sharedResource = 0;

  /** The N readers. */
  volatile int nReaders = 0;

  /** The Someone writing. */
  volatile boolean someoneWriting = false;

  private final ReentrantLock reentrantLock;
  /** The Readyto read. */
  Condition ReadytoRead;

  /** The Readyto write. */
  Condition ReadytoWrite;

  /** Instantiates a new Rw monitor an. */
  public RWMonitorAN() {
    reentrantLock = new ReentrantLock();
    ReadytoRead = reentrantLock.newCondition();
    ReadytoWrite = reentrantLock.newCondition();
  }

  /** Start write. */
  void StartWrite() {
    reentrantLock.lock();

    while (someoneWriting || (nReaders != 0)) // preferencia para los lectores
    try {
        ReadytoWrite.await();
      } catch (InterruptedException e) {
      e.printStackTrace();
    }
    someoneWriting = true;
    System.out.println("someoneWriting = " + someoneWriting);
    reentrantLock.unlock();
  }

  /** Start read. */
  void StartRead() {
    reentrantLock.lock();
    while (someoneWriting)
      try {
        ReadytoRead.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    nReaders = nReaders + 1;
    System.out.println("nReaders = " + nReaders);
    ReadytoRead.signalAll(); // todos pueden leer
    reentrantLock.unlock();
  }

  /** End write.
   * El escritor cuando termina avisa a otros escritores y a los lectores.*/
  void EndWrite() {
    reentrantLock.lock();

    try {
      someoneWriting = false;
      ReadytoWrite.signal(); // despierto a 1 esritor
      ReadytoRead.signalAll(); // despierto a  TODOS los lectores
      System.out.println("someoneWriting = " + someoneWriting);
    } finally {
      reentrantLock.unlock();
    }
  }

  /** End read. */
  void EndRead() {
    reentrantLock.lock();
    nReaders = nReaders - 1;
    if (nReaders == 0)
      ReadytoWrite.signal(); // solo despierto al Writer si ya no hay ningun Reader detras mia
    System.out.println("nReaders = " + nReaders);
    reentrantLock.unlock();
  }
}
