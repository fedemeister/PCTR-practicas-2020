package ej3;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type S pi monte carlo.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class SPiMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo, Serializable {
  private static final long serialVersionUID = 1L;

  private int nPoints;
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    try {
      // create a RMI registry on localhost at port 1099
      Registry registry = LocateRegistry.createRegistry(1111);
      System.out.println("RMI registry is running on port 1111");
      iPiMonteCarlo service = new SPiMonteCarlo();
      System.out.println("Binding iPiMonteCarlo...");

      // bind it in the RMI registry
      registry.rebind("montecarlo", service);

      System.out.println("iPiMonteCarlo is ready.");

      System.out.println(
              "Wait for 10 seconds for any incoming client call before terminating the RMI registry...");

      // sleep 10 seconds
      Thread.sleep(10000);

      // unbind the service object
      registry.unbind("montecarlo");

      // remove the service object from the registry
      UnicastRemoteObject.unexportObject(service, true);

      System.out.println("Shutting down the RMI registry...");

      // shut down the registry
      UnicastRemoteObject.unexportObject(registry, true);

      System.out.println("iBonoloto has stopped.");

    } catch (RemoteException | InterruptedException | NotBoundException e) {
      e.printStackTrace();
    }
  }
  private double aproximation;
  private int summation;

  private final ReentrantLock reentrantLock;

  /**
   * Instantiates a new S pi monte carlo.
   *
   * @throws RemoteException the remote exception
   */
  public SPiMonteCarlo() throws RemoteException {
    super();
    nPoints = 0;
    aproximation = 0;
    summation = 0;
    reentrantLock = new ReentrantLock();
  }

  public void reset() throws RemoteException {
    nPoints = 0;
    aproximation = 0;
    summation = 0;
    System.out.println("Se ha reinicado el servidor");
  }

  public void getAprox() throws RemoteException {
    System.out.println("aproximation = " + aproximation);
    System.out.println("nPoints = " + nPoints);
  }

  public void morePoints(int n) throws RemoteException {
    reentrantLock.lock();
    nPoints += n;
    reentrantLock.unlock();

    for (int i = 0; i < n; i++) {
      double x = Math.random();
      double y = Math.random();

      if (Math.pow(x, 2) + Math.pow(y, 2) <= 1) {
        reentrantLock.lock();
        summation++;
        reentrantLock.unlock();
      }
    }

    reentrantLock.lock();
    aproximation = (double) 4 * summation / nPoints;
    reentrantLock.unlock();
  }
}
