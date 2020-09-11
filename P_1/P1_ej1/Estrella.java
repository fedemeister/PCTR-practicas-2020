package P1_ej1;

/**
 * The type Estrella.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class Estrella extends CuerpoAstrofisico {
  /** The enum Tipo. */
  enum Tipo {
    /** Protoestrella tipo. */
    PROTOESTRELLA,
    /** Gigante roja tipo. */
    GIGANTE_ROJA,
    /** Enana blanca tipo. */
    ENANA_BLANCA,
    /** Enana negra tipo. */
    ENANA_NEGRA,
    /** Estrella de neutrones tipo. */
    ESTRELLA_DE_NEUTRONES
  }

  /** Edad en miles de millones de años */
  private long age;

  /**
   * Instantiates a new Estrella.
   *
   * @param name the name
   * @param radio the radio
   * @param temperature the temperature
   * @param mass the mass
   * @param gravity the gravity
   * @param age the age
   */
  public Estrella(
      String name, long radio, double temperature, long mass, double gravity, long age) {
    super(name, radio, temperature, mass, gravity);
    this.age = age;
  }

  /**
   * Gets age.
   *
   * @return the age
   */
  public long getAge() {
    return age;
  }

  /**
   * Sets age.
   *
   * @param age the age
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void setAge(long age) throws IllegalArgumentException {
    if (age < 0) {
      throw new IllegalArgumentException("Edad no válida");
    }
    this.age = age;
  }
}
