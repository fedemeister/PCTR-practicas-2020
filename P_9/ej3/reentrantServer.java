package ej3;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/*
El servidor crea un hilo por cada peticion del cliente y dicho hilos incrementan
la variable n que como es atomic hace sus incrementos en EM

Lo curioso es que la variable u tampoco falla en las pruebas que he hecho :(
*/

/**
 * The type Reentrant server.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class reentrantServer implements Runnable {
  /** The constant safeVariable. */
  static int safeVariable = 0;

  /** The constant unsafeVariable. */
  static int unsafeVariable = 0;

  /** The Reentrant lock. */
  ReentrantLock reentrantLock;

  /** The Socket. */
  Socket socket;

  /**
   * Instantiates a new Reentrant server.
   *
   * @param socket the socket
   */
  public reentrantServer(Socket socket) {
    this.socket = socket;
    reentrantLock = new ReentrantLock();
  }

  @Override
  public void run() {
    try {
      BufferedReader bufferedReader =
          new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String readLine = bufferedReader.readLine();
      int j;
      int i = Integer.parseInt(readLine);
      for (j = 1; j <= 10000; j++) {
        reentrantLock.lock();
        safeVariable++;
        reentrantLock.unlock();
        unsafeVariable++;
      }
      socket.close();
      System.out.println("El hilo cierra su conexion...");
    } catch (Exception e) {
      System.out.println("Error en run() de reentrantServer.java");
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    int port = 28995;
    int nThreads = 4;
    int maxRequests = 10;
    ThreadPoolExecutor threadPoolExecutor =
        new ThreadPoolExecutor(
            nThreads, nThreads, 8000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    try {
      ServerSocket serverSocket = new ServerSocket(port, 3000);
      int counterAcceptedRequests = 0;
      while (counterAcceptedRequests < maxRequests) {
        System.out.println("Esperando solicitud de conexión...");
        Socket accept = serverSocket.accept();
        counterAcceptedRequests++;
        System.out.println("Recibida solicitud de conexión...");
        threadPoolExecutor.execute(new reentrantServer(accept));
        System.out.println("safeVariable = " + safeVariable);
        System.out.println("unsafeVariable = " + unsafeVariable);
      }
    } catch (Exception e) {
      System.out.println("Error main the reentrantServer.java...");
    }
  }
}
