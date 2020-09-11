package P1_ej1;

/**
 * The type Satelite.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class Satelite extends CuerpoAstrofisico {

  /**
   * Instantiates a new Satelite.
   *
   * @param name the name
   * @param radio the radio
   * @param temperature the temperature
   * @param mass the mass
   * @param gravity the gravity
   */
  public Satelite(String name, long radio, double temperature, long mass, double gravity) {
    super(name, radio, temperature, mass, gravity);
  }
}
