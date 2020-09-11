package ej4;

import java.util.concurrent.*;

/**
 * The type Usa filo api an.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class usaFiloApiAn implements Runnable {
  private final filoApiAN table;
  private final int index;

  /**
   * Instantiates a new Usa filo api an.
   *
   * @param filoApiAN the filo api an
   * @param index the index
   */
  public usaFiloApiAn(filoApiAN filoApiAN, int index) {
    this.table = filoApiAN;
    this.index = index;
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    int nPhilosophers = 5;
    filoApiAN table = new filoApiAN(nPhilosophers);
    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(nPhilosophers);
    for (int i = 0; i < nPhilosophers; i++) {
      newFixedThreadPool.execute(new usaFiloApiAn(table, i));
    }
    newFixedThreadPool.shutdown();
    try {
      newFixedThreadPool.awaitTermination(8000L, TimeUnit.MILLISECONDS);
    } catch (InterruptedException I) {
      I.printStackTrace();
    }
  }

  public void run() {
    while (true) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      table.takeForks(this.index);
      try {
        System.out.println("Comiendo Filósofo.index =  " + this.index);
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      table.releaseForks(this.index);
    }
  }
}
