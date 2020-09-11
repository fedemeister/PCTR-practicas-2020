package ej3;

/**
 * The type Usa lector escritor.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class usaLectorEscritor {
  /** The constant monitorLectorEscritor. */
  public static LectorEscritor monitorLectorEscritor = new LectorEscritor();

  /**
   * The type Reader.*
   *
   * @author Federico Carrillo Chaves
   * @version 1.3
   */
  static class Reader extends Thread {
    public void run() {
      monitorLectorEscritor.startRead();
      System.out.println("Lector-" + this.getId() + " leyendo");
      monitorLectorEscritor.endRead();
    }
  }

  /**
   * The type Writer.*
   *
   * @author Federico Carrillo Chaves
   * @version 1.3
   */
  static class Writer extends Thread {
    public void run() {
      monitorLectorEscritor.startWrite();
      monitorLectorEscritor.sharedResource--;
      System.out.println("Escritor-" + this.getId() + " escribiendo");
      monitorLectorEscritor.endWrite();
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    Reader reader_1 = new Reader();
    Reader reader_2 = new Reader();
    Reader reader_3 = new Reader();
    Reader reader_4 = new Reader();
    Reader reader_5 = new Reader();
    Reader reader_6 = new Reader();
    Reader reader_7 = new Reader();
    Reader reader_8 = new Reader();
    Reader reader_9 = new Reader();
    Reader reader_10 = new Reader();
    Reader reader_11 = new Reader();
    Reader reader_12 = new Reader();

    Writer writer_1 = new Writer();
    Writer writer_2 = new Writer();
    Writer writer_3 = new Writer();
    Writer writer_4 = new Writer();
    Writer writer_5 = new Writer();
    Writer writer_6 = new Writer();
    Writer writer_7 = new Writer();
    Writer writer_8 = new Writer();
    Writer writer_9 = new Writer();
    Writer writer_10 = new Writer();
    Writer writer_11 = new Writer();
    Writer writer_12 = new Writer();

    writer_1.start();
    writer_2.start();
    writer_3.start();
    writer_4.start();
    writer_5.start();
    writer_6.start();
    writer_7.start();
    writer_8.start();
    writer_9.start();
    writer_10.start();
    writer_11.start();
    writer_12.start();

    reader_1.start();
    reader_2.start();
    reader_3.start();
    reader_4.start();
    reader_5.start();
    reader_6.start();
    reader_7.start();
    reader_8.start();
    reader_9.start();
    reader_10.start();
    reader_11.start();
    reader_12.start();

    try {
      reader_1.join();
      reader_2.join();
      reader_3.join();
      reader_4.join();
      reader_5.join();
      reader_6.join();
      reader_7.join();
      reader_8.join();
      reader_9.join();
      reader_10.join();
      reader_11.join();
      reader_12.join();

    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }

    try {
      writer_1.join();
      writer_2.join();
      writer_3.join();
      writer_4.join();
      writer_5.join();
      writer_6.join();
      writer_7.join();
      writer_8.join();
      writer_9.join();
      writer_10.join();
      writer_11.join();
      writer_12.join();
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }
  }
}
