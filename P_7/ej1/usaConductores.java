package ej1;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * The type Usa conductores.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class usaConductores implements Runnable {
  private static final int TAM = 50;
  private static Conductores conductores = new Conductores(TAM);
  private final int tipoOperacion;
  /** The Random. */
  Random random = new Random();

  /**
   * Instantiates a new Usa conductores.
   *
   * @param tipoOperacion the tipo operacion
   * @param conductores the conductores
   */
  public usaConductores(int tipoOperacion, Conductores conductores) {
    this.tipoOperacion = tipoOperacion;
    usaConductores.conductores = conductores;
  }

  @Override
  public void run() {
    switch (tipoOperacion) {
      case 0:
        conductores.addConductor(random.nextInt(TAM), "nombre por defecto", true);
        break;
      case 1:
        conductores.deleteConductor(random.nextInt(TAM));
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + tipoOperacion);
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    // Todos los conductores empiezan su carnet de conducir con 10 puntos y activos
    for (int i = 0; i < TAM; ++i) {
      conductores.addConductor(i, "nombre_" + i, true);
    }

    Conductor[] vectorConductores = conductores.getDataBase();

    Arrays.stream(vectorConductores).map(Conductor::toString).forEachOrdered(System.out::println);

    IntStream.range(0, TAM)
        .mapToObj(i -> new Random())
        .forEachOrdered(
            random ->
                vectorConductores[(int) (Math.random() * TAM)].setLicensePoints(
                    random.nextInt(10)));

    System.out.print("\n\nActualización del día siguiente con los cambios en los puntos. \n");

    Arrays.stream(vectorConductores).map(Conductor::toString).forEachOrdered(System.out::println);

    Thread thread_1 = new Thread(new usaConductores(1, conductores));
    Thread thread_2 = new Thread(new usaConductores(1, conductores));
    Thread thread_3 = new Thread(new usaConductores(1, conductores));
    Thread thread_4 = new Thread(new usaConductores(1, conductores));
    thread_1.start();
    thread_2.start();
    thread_3.start();
    thread_4.start();
    try {
      thread_1.join();
      thread_2.join();
      thread_3.join();
      thread_4.join();
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }
    System.out.print("\n\nActualización DESPUÉS DE LOS HILOS \n");
    for (Conductor vectorConductore : vectorConductores) {
      System.out.println(vectorConductore.toString());
    }
    System.out.print("\n\nConductores que están activos en la DGT \n");
    for (Conductor vectorConductore : vectorConductores) {
      if (vectorConductore.isActive()) {
        System.out.println(vectorConductore.toString());
      }
    }
  }
}
