package ej2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * The interface Libros.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public interface iLibros extends Remote {
  public Libro consultBook(int id) throws RemoteException;

  public String getBook(int id) throws RemoteException;

  public List<Libro> printBooks() throws RemoteException;

  public String insertBook(Libro libro) throws RemoteException;

  public String insertBook(int id, String title, String author, int edition) throws RemoteException;
}
