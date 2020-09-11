package P1_ej1;

/**
 * The type Luna.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class Luna extends Satelite {

  /**
   * Instantiates a new Luna.
   *
   * @param temperature Temperatura en grados Kelvin (K)
   */
  public Luna(double temperature) {
    super("Luna", 1737, temperature, (long) 7349E22, 1.62);
  }
}
