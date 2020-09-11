package ej1;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Random;

/**
 * The type C bono loto.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class cBonoLoto implements Serializable {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    Random random = new Random();
    int[] myBet = new int[6];

    for (int i = 0; i < myBet.length; ++i) {
      myBet[i] = random.nextInt(50);
    }

    try {
      iBonoLoto servidor = (iBonoLoto) Naming.lookup("Servidor");
      System.out.println("myBet = " + Arrays.toString(myBet));
      if (servidor.compApuesta(myBet)) {
        System.out.println("¡HAS GANADO!");
      } else System.out.println("Has perdido...");
      System.out.println("ganadores = " + Arrays.toString(servidor.getGanadores()));

    } catch (RemoteException | NotBoundException | MalformedURLException e) {
      e.printStackTrace();
    }
  }
}
