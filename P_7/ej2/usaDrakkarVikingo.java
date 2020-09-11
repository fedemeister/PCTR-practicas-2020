package ej2;

import java.util.stream.IntStream;

/**
 * The type Usa drakkar vikingo.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class usaDrakkarVikingo implements Runnable {
  private final int idViking;
  private final int typeOfViking;
  /** The Monitor drakkar vikingo. */
  DrakkarVikingo monitorDrakkarVikingo;

  /**
   * Instantiates a new Usa drakkar vikingo.
   *
   * @param idViking the id viking
   * @param typeOfViking the type of viking
   * @param monitorDrakkarVikingo the monitor drakkar vikingo
   */
  usaDrakkarVikingo(int idViking, int typeOfViking, DrakkarVikingo monitorDrakkarVikingo) {
    this.idViking = idViking;
    this.typeOfViking = typeOfViking;
    this.monitorDrakkarVikingo = monitorDrakkarVikingo;
  }

  public void run() {
    while (true) {
      switch (this.typeOfViking) {
        case 0:
          monitorDrakkarVikingo.eat(this.idViking); // Vikingo que come. Vikingo normal
          break;

        case 1:
          monitorDrakkarVikingo.cook(); // Vikingo que cocina.
          break;

        default:
          throw new IllegalStateException("Unexpected value: " + this.typeOfViking);
      }
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    int numberOfVikings = 25;
    DrakkarVikingo monitorDrakkarVikingo = new DrakkarVikingo();


    new Thread(new usaDrakkarVikingo(-1, 1, monitorDrakkarVikingo))
        .start();

    IntStream.range(0, numberOfVikings)
        .forEachOrdered(
            i -> {
              new Thread(new usaDrakkarVikingo(i, 0, monitorDrakkarVikingo)).start();
            });
  }
}
