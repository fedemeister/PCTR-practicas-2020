package ej1;

import java.util.stream.IntStream;

/**
 * The type Usamonitor impresion.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class UsamonitorImpresion extends Thread {
  private static MonitorImpresion monitorImpresion;
  private static int[] eachPrinterCounter;
  private static final int nPrinters = 3;
  private static final int repetitions = 280995;
  private static final int nTasks = 6;

  public void run() {
    int idPrinter;
    for (int i = 0; i < repetitions; ++i) {
      idPrinter = monitorImpresion.takePrint();
      System.out.println("Tarea-->" + this.getName() + ":impresora-->" + idPrinter);
      ++eachPrinterCounter[idPrinter];
      monitorImpresion.dropPrint(idPrinter);
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    monitorImpresion = new MonitorImpresion(nPrinters);
    eachPrinterCounter = new int[nPrinters];
    int totalValue = 0;

    Thread[] vectorTasks =
        IntStream.range(0, nTasks).mapToObj(i -> new UsamonitorImpresion()).toArray(Thread[]::new);

    IntStream.range(0, nTasks).forEachOrdered(i -> vectorTasks[i].start());

    IntStream.range(0, nTasks)
        .forEachOrdered(
            i -> {
              try {
                vectorTasks[i].join();
              } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
              }
            });

    for (int i = 0; i < nPrinters; i++) {
      System.out.println("\tcounter[" + i + "] = " + eachPrinterCounter[i]);
      totalValue += eachPrinterCounter[i];
    }
    System.out.println("totalValue = " + totalValue);
    System.out.println("Valor esperado: " + (nTasks * repetitions));
  }
}
