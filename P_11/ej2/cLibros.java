package ej2;

import java.rmi.*;
import java.util.Scanner;
import java.util.List;

/**
 * The type C libros.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class cLibros {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   * @throws Exception the exception
   */
  public static void main(String[] args) throws Exception {
    int option = 0;
    String answer;
    Scanner scanner = new Scanner(System.in);
    iLibros libroserver = (iLibros) Naming.lookup("libroserver");

    Libro libro_1 = new Libro(1, "random_author_1", "random_title_1", 5);
    Libro libro_2 = new Libro(2, "random_author_2", "random_title_2", 2);
    Libro libro_3 = new Libro(3, "random_author_3", "random_title_3", 1);
    Libro libro_4 = new Libro(4, "random_author_4", "random_title_4", 10);

    System.out.println("****BIBLIOTECA****");
    do {
      System.out.println("0-->Salir");
      System.out.println("1-->Consultar Libro");
      System.out.println("2-->Insertar Libro nuevo");
      System.out.println("3-->Insertar Libro");
      System.out.println("4-->Mostrar base de datos completa");
      System.out.println("5-->Insertar varios libros");
      System.out.println("6-->Extraer libro: ");
      System.out.println("INTRODUZCA UNA OPCION: ");
      option = scanner.nextInt();

      switch (option) {
        case 1:
          int x;
          System.out.println("Introduce el id del libro a consultar:");
          x = scanner.nextInt();
          Libro aux = libroserver.consultBook(x);
          System.out.println(aux.toString());
          break;
        case 2:
          answer = libroserver.insertBook(10, "Título nuevo", "Anónimo", 5);
          System.out.println(answer);
          System.out.println("Libro nuevo creado en la base de datos");
          break;

        case 3:
          answer = libroserver.insertBook(libro_1);
          System.out.println(answer);
          break;

        case 4:
          System.out.println("Libros de la base de datos:");
          System.out.println("");
          List<Libro> res = libroserver.printBooks();

          for (Libro l : res) {
            System.out.println(l.toString());
          }
          break;
        case 5:
          answer = libroserver.insertBook(libro_2);
          System.out.println(answer);
          answer = libroserver.insertBook(libro_3);
          System.out.println(answer);
          answer = libroserver.insertBook(libro_4);
          System.out.println(answer);
          break;
        case 6:
          System.out.println("Introduce el id del libro a extraer");
          int y;
          y = scanner.nextInt();
          answer = libroserver.getBook(y);
          System.out.println(answer);
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + option);
      }

      System.out.println("\n\n");
      System.out.println("\n\n");
    } while (option != 0);
  }
}
