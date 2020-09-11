package ej1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The type Servidor hilosin pool.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class ServidorHilosinPool extends Thread {
  /** The Socket. */
  Socket socket;

  /**
   * Instantiates a new Servidor hilosin pool.
   *
   * @param socket the socket
   */
  public ServidorHilosinPool(Socket socket) {
    this.socket = socket;
    this.start();
  }

  @Override
  public void run() {
    try {
      BufferedReader bufferedReader =
          new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String readLine = bufferedReader.readLine();
      int j;
      int i = Integer.parseInt(readLine);
      for (j = 1; j <= 20; j++) {
        System.out.println("El hilo " + this.getName() + " escribiendo el dato " + i);
        sleep(5000);
      }
      socket.close();
      System.out.println("El hilo " + this.getName() + "cierra su conexion...");
    } catch (Exception e) {
      System.out.println("Error...");
    }
  } // run

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    int i;
    int puerto = 28995;
    try {
      ServerSocket chuff = new ServerSocket(puerto, 3000);

      while (true) {
        System.out.println("Esperando solicitud de conexion...");
        Socket socket = chuff.accept();
        System.out.println("Recibida solicitud de conexion...");
        new ServidorHilosinPool(socket); // aqui se lanza el hilo
      } // aqui es donde se crean los hilos y hay latencia
    } catch (Exception e) {
      System.out.println("Error en sockets...");
    }
  }
}
