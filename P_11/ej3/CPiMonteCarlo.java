package ej3;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * The type C pi monte carlo.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class CPiMonteCarlo implements Serializable {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    try {
      iPiMonteCarlo serverMontecarlo = (iPiMonteCarlo) Naming.lookup("montecarlo");

      int option;
      do {
        System.out.println("Elija una opción: ");
        System.out.println("1. Introducir puntos.");
        System.out.println("2. Reiniciar cálculo.");
        System.out.println("3. Consultar valor de aproximacion.");
        System.out.println("0. Salir.");
        option = scanner.nextInt();

        switch (option) {
          case 0:
            break;
          case 1:
            System.out.print("¿Cuántos puntos más?: ");
            int puntos = scanner.nextInt();
            serverMontecarlo.morePoints(puntos);
            break;
          case 2:
            serverMontecarlo.reset();
            break;
          case 3:
            serverMontecarlo.getAprox();
            break;
          default:
            throw new IllegalStateException("Unexpected value: " + option);
        }
      } while (option != 0);
      scanner.close();
    } catch (NotBoundException | MalformedURLException | RemoteException e) {
      e.printStackTrace();
    }
  }
}
