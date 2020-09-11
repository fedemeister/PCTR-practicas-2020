package ej3;

import java.net.Socket;

/**
 * The type Cliente contador.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class clienteContador {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    int port = 28995;
    int nRequests = 10;

    try {
      for (int i = 0; i < nRequests; ++i) {
        System.out.println("Realizando conexion.....");
        Socket socket = new Socket("localhost", port);
        System.out.println("Conexión realizada con éxito a: " + socket);

        System.out.println("Cerrando conexion.....");
        socket.close();
      }
    } catch (Exception e) {
      System.out.println("Error en sockets de clienteContador.kava");
    }
  }
}
