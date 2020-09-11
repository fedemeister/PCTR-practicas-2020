package ej1;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * The type Cliente multiple.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class clienteMultiple {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {

    int port = 28995;
    int requestsNumber = 500;
    long iniCronom = System.currentTimeMillis();

    for (int i = 0; i < requestsNumber; i++) {
      try {
        int number = (int) (Math.random() * 10);
        System.out.println("Realizando conexion...");
        Socket cable = new Socket("localhost", port);
        System.out.println("Realizada conexion a " + cable);
        PrintWriter outputPrintWriter =
            new PrintWriter(new BufferedWriter(new OutputStreamWriter(cable.getOutputStream())));
        outputPrintWriter.println(number);
        outputPrintWriter.flush();
        System.out.println("Cerrando conexion...");
        cable.close();

      } // try
      catch (Exception e) {
        System.out.println("Error en sockets...");
      }
    }
    long finCronom =
        System.currentTimeMillis(); // se para el cronometro, seria mejor en nanosegundos
    System.out.println((finCronom - iniCronom) + " milisegundos");
  }
}
