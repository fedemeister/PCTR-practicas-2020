package ej1;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

/**
 * The type S bono loto.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class sBonoLoto extends UnicastRemoteObject implements iBonoLoto, Serializable {
  /** The Ganadores. */
  public int[] ganadores = new int[6];

  /**
   * Instantiates a new S bono loto.
   *
   * @throws RemoteException the remote exception
   */
  public sBonoLoto() throws RemoteException {

    Random random = new Random();

    for (int i = 0; i < ganadores.length; ++i) {
      ganadores[i] = random.nextInt(50);
    }
  }

  @Override
  public int[] getGanadores() throws RemoteException {
    return ganadores;
  }

  @Override
  public boolean compApuesta(int[] apuesta) throws RemoteException {
    boolean ganador = true;
    for (int i = 0; i < ganadores.length; ++i)
      if (ganadores[i] != apuesta[i]) {
        ganador = false;
      }
    return ganador;
  }

  @Override
  public void resetServidor() throws RemoteException {
    Random random = new Random();
    for (int i = 0; i < ganadores.length; ++i) {
      ganadores[i] = random.nextInt(50);
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {

    try {
      // create a RMI registry on localhost at port 1099
      Registry registry = LocateRegistry.createRegistry(1099);

      System.out.println("RMI registry is running on port 1099");

      // create an instance of the service object
      iBonoLoto service = new sBonoLoto();

      System.out.println("Binding iBonoLoto...");

      // bind it in the RMI registry
      registry.rebind("Servidor", service);

      System.out.println("iBonoLoto is ready.");

      System.out.println(
          "Wait for 10 seconds for any incoming client call before terminating the RMI registry...");

      // sleep 10000 seconds
      Thread.sleep(10000000);

      // unbind the service object
      registry.unbind("montecarlo");

      // remove the service object from the registry
      UnicastRemoteObject.unexportObject(service, true);

      System.out.println("Shutting down the RMI registry...");

      // shut down the registry
      UnicastRemoteObject.unexportObject(registry, true);

      System.out.println("iBonoloto has stopped.");
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("Servidor Remoto Preparado");
  }
}
