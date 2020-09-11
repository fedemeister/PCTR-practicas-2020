package ej2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The type Usa imp monitor.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class usaImpMonitor implements Runnable {
  private final ImpMonitor impMonitor;

  /**
   * Instantiates a new Usa imp monitor.
   *
   * @param impMonitor the imp monitor
   */
  public usaImpMonitor(ImpMonitor impMonitor) {
    this.impMonitor = impMonitor;
  }

  public void run() {
    int myPrinter = impMonitor.takePrinter();
    System.out.println("\tImprimiendo impresora = " + (myPrinter + 1));
    try {
      Thread.sleep(1000);
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }
    impMonitor.dropPrinter(myPrinter);
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    ImpMonitor monitor = new ImpMonitor();
    int nTasks = 100;
    int availableProcessors = Runtime.getRuntime().availableProcessors();
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(availableProcessors);
    usaImpMonitor[] vectorOfTasks = new usaImpMonitor[nTasks];

    for (int i = 0; i < nTasks; i++) {
      vectorOfTasks[i] = new usaImpMonitor(monitor);
      fixedThreadPool.execute(vectorOfTasks[i]);
    }

    fixedThreadPool.shutdown();

    try {
      fixedThreadPool.awaitTermination(5000L, TimeUnit.MILLISECONDS);
    } catch (InterruptedException I) {
		I.printStackTrace();
	}
  }
}
