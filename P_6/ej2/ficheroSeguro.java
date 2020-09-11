package ej2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * The type Fichero seguro.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class ficheroSeguro {
  /** The constant randomAccessFile. */
  public static volatile RandomAccessFile randomAccessFile = null;

  /** The constant nThread. */
  public static int nThread = 10;

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   * @throws FileNotFoundException the file not found exception
   */
  public static void main(String[] args) throws FileNotFoundException {
    randomAccessFile =
        new RandomAccessFile(
            //    "C:\\Users\\fedem\\Documents\\Practicas_IntelliJ\\P6-19_20\\my_file.txt", "rw");
            "my_file.txt", "rw");
    ThreadPoolExecutor threadPoolExecutor =
        new ThreadPoolExecutor(
            nThread, nThread, 5000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    threadPoolExecutor.prestartAllCoreThreads();

    IntStream.range(0, nThread)
        .mapToObj(i -> new MyRandomWriter(randomAccessFile))
        .forEachOrdered(threadPoolExecutor::execute);

    threadPoolExecutor.shutdown();

    boolean isEnd = false;
    while (!isEnd) {
      try {
        isEnd = threadPoolExecutor.awaitTermination(50000L, TimeUnit.MILLISECONDS);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    try {
      if (!(randomAccessFile == null)) {
        randomAccessFile.close();
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}

/**
 * The type My random writer.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
class MyRandomWriter implements Runnable {
  private final Random random = new Random();
  private final RandomAccessFile randomAccessFile; // usado como cerrojo.

  /**
   * Instantiates a new My random writer.
   *
   * @param randomAccessFile the random access file
   */
  public MyRandomWriter(RandomAccessFile randomAccessFile) {
    this.randomAccessFile = randomAccessFile;
  }

  public void run() {
    try {
      int nWritings = 200;
      for (int i = 0; i <= nWritings; ++i) {
        synchronized (randomAccessFile) {
          randomAccessFile.writeUTF(Integer.toString(random.nextInt()));
        }
      }
    } catch (FileNotFoundException ex) {
      System.out.println(ex.getMessage());
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
