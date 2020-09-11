package ej3;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
 * The type Atomic server.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class atomicServer implements Runnable {
  /** The Atomic long. */
  static AtomicLong atomicLong = new AtomicLong(0);

  /** The Unsafe variable. */
  static int unsafeVariable = 0;

  /** The Socket. */
  Socket socket;

  /**
   * Instantiates a new Atomic server.
   *
   * @param socket the socket
   */
  public atomicServer(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    try {
      BufferedReader bufferedReader =
          new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String datos = bufferedReader.readLine();
      int j;
      int i = Integer.parseInt(datos);
      for (j = 1; j <= 10000; j++) {
        atomicLong.incrementAndGet();
        unsafeVariable++;
      }
      socket.close();
      System.out.println("Hilo cierra su conexion...");
    } catch (Exception e) {
      System.out.println("Error en run() de atomicServer.java");
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    int nThreads = 4;
    int port = 28995;
    int maxRequests = 15;
    ThreadPoolExecutor threadPoolExecutor =
        new ThreadPoolExecutor(
            nThreads, nThreads, 7000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    try {
      ServerSocket serverSocket = new ServerSocket(port, 3000);
      int counterOfRequests = 0;
      while (counterOfRequests < maxRequests) {
        System.out.println("Esperando solicitud de conexion...");
        Socket accept = serverSocket.accept();
        counterOfRequests++;
        System.out.println("Recibida solicitud de conexion...");
        threadPoolExecutor.execute(new atomicServer(accept));
        System.out.println("atomicLong = " + atomicLong);
        System.out.println("unsafeVariable = " + unsafeVariable);
      }
    } catch (Exception e) {
      System.out.println("Error en sockets.de atomicServer.java..");
    }
  }
}
