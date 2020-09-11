package P1_ej1;

/**
 * The type Tierra.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class Tierra extends CuerpoPlanetario {

  /**
   * Instantiates a new Tierra.
   *
   * @param temperature the temperature
   */
  public Tierra(double temperature) {
    super("Tierra", 6371, temperature, (long) 5972E24, 9.8, "Sistema Solar", true);
    addSatelite(new Luna(244));
  }

  // public void addSatelite(satelite sat) { satelites.add(sat); }
  // addSatelite(new luna());
}
