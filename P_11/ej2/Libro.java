package ej2;

import java.io.Serializable;

/**
 * The type Libro.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class Libro implements Serializable {
  private int id;
  private String author;
  private String title;
  private int edition;

  /**
   * Instantiates a new Libro.
   *
   * @param id the id
   * @param author the author
   * @param title the title
   * @param edition the edition
   */
  public Libro(int id, String author, String title, int edition) {
    this.id = id;
    this.author = author;
    this.title = title;
    this.edition = edition;
  }

  /** Instantiates a new Libro. */
  public Libro() {}

  /**
   * Gets id of the book.
   *
   * @return the id
   */
  public int getid() {
    return this.id;
  }

  @Override
  public String toString() {
    return "Libro{"
        + "id="
        + id
        + ", titulo='"
        + title
        + '\''
        + ", author='"
        + author
        + '\''
        + ", edition="
        + edition
        + '}';
  }
}
