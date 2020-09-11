package ej2;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type S libros.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class sLibros extends UnicastRemoteObject implements iLibros {
  private final List<Libro> database;
  private final ReentrantLock reentrantLock = new ReentrantLock();

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   * @throws Exception the exception
   */
  public static void main(String[] args) throws Exception {
    iLibros objremoto = new sLibros();
    Naming.rebind("libroserver", objremoto);
    System.out.println("Servidor iniciado");
  }

  /**
   * Instantiates a new S libros.
   *
   * @throws RemoteException the remote exception
   */
  public sLibros() throws RemoteException {
    super();
    database = new ArrayList<Libro>();
    System.out.println("Base de datos creada");
  }

  @Override
  public String insertBook(Libro libro) throws RemoteException {
    String mes;
    reentrantLock.lock();

    if (!database.contains(libro)) {
      database.add(libro);
      mes = "Libro añadido correctamente";
    } else mes = "Ese libro ya se encuentra en la base de datos";

    reentrantLock.unlock();

    return mes;
  }

  @Override
  public String insertBook(int id, String title, String author, int edition)
      throws RemoteException {
    String aux;
    reentrantLock.lock();
    Libro L = new Libro(id, title, author, edition);
    if (!database.contains(L)) {
      database.add(L);
      aux = "insertBook --> OK";
    } else aux = "El libro se encontraba en la biblioteca. No se ha añadido";
    reentrantLock.unlock();
    return aux;
  }

  @Override
  public Libro consultBook(int id) throws RemoteException {
    boolean found = false;
    int i = 0;
    Libro aux = new Libro();
    Iterator it = database.iterator();

    while (!found && it.hasNext()) {

      aux = (Libro) it.next();
      if (aux.getid() == id) {
        found = true;
      }

      // it.next();

    }

    if (!found) System.out.println("Libro no encontrado");
    return aux;
  }

  @Override
  public String getBook(int id) throws RemoteException {
    String libro;
    reentrantLock.lock();
    Libro aux;
    Iterator<Libro> it = database.iterator();
    boolean found = false;
    while (it.hasNext() && !found) {
      aux = it.next();
      if (aux.getid() == id) {
        found = true;
        it.remove();
      }
    }
    if (!found) {
      libro = "Libro no encontrado";
    } else {
      libro = "Libro " + id + " correctamente extraido";
    }
    reentrantLock.unlock();
    return libro;
  }

  @Override
  public List<Libro> printBooks() throws RemoteException {
    reentrantLock.lock();
    if (database.isEmpty()) {
      System.out.println("No hay libros en la BD");
    }
    reentrantLock.unlock();

    return database;
  }
}
