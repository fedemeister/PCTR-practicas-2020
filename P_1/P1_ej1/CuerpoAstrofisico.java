package P1_ej1;

/**
 * The type Cuerpo astrofisico.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class CuerpoAstrofisico {

  private String name;
  private final long radio;
  private double temperature;
  private final long mass;
  private final double gravity;

  /**
   * Instantiates a new Cuerpo astrofisico.
   *
   * @param name the name
   * @param radio the radio in kilometres (Km)
   * @param temperature the temperature in Kelvin (K)
   * @param mass the mass in kilograms
   * @param gravity the gravity in metre per second squared m/s²
   */
  public CuerpoAstrofisico(String name, long radio, double temperature, long mass, double gravity) {
    this.name = name;
    this.radio = radio;
    this.temperature = temperature;
    this.mass = mass;
    this.gravity = gravity;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets radio.
   *
   * @return the radio
   */
  public long getRadio() {
    return radio;
  }

  /**
   * Gets temperature.
   *
   * @return the temperature
   */
  public double getTemperature() {
    return temperature;
  }

  /**
   * Sets temperature.
   *
   * @param temperature the temperature
   */
  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  /**
   * Gets mass.
   *
   * @return the mass
   */
  public long getMass() {
    return mass;
  }

  /**
   * Gets gravity.
   *
   * @return the gravity
   */
  double getGravity() {
    return gravity;
  }
}
