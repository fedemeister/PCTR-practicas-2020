package ej1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * The type Servidor hilocon pool.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class ServidorHiloconPool implements Runnable {
  /** The Socket. */
  Socket socket;

  /**
   * Instantiates a new Servidor hilocon pool.
   *
   * @param socket the socket
   */
  public ServidorHiloconPool(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    try {
      BufferedReader bufferedReader =
          new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String readLine = bufferedReader.readLine();
      int j;
      int intValue = Integer.parseInt(readLine);
      for (j = 1; j <= 20; j++) {
        System.out.println("El hilo escribiendo el dato " + intValue);
      }
      socket.close();
      System.out.println("El hilo cierra su conexion...");
    } catch (Exception e) {
      System.out.println("Error...");
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    int numThreads = 4;
    int port = 28995;
    ThreadPoolExecutor threadPoolExecutor =
        new ThreadPoolExecutor(
            numThreads,
            numThreads,
            6000L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    try {
      ServerSocket serverSocket = new ServerSocket(port, 3000);

      while (true) {
        System.out.println("Esperando solicitud de conexion...");
        Socket cable = serverSocket.accept();
        System.out.println("Recibida solicitud de conexion...");
        threadPoolExecutor.execute(new ServidorHiloconPool(cable));
      }
    } catch (Exception e) {
      System.out.println("Error en sockets...");
    }
  }
}
