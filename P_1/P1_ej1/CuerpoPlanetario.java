package P1_ej1;

import java.util.LinkedList;

/**
 * The type Cuerpo planetario.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class CuerpoPlanetario extends CuerpoAstrofisico {
  private String system;
  private boolean habitable;

  private LinkedList<Satelite> satelitesList;

  /**
   * Instantiates a new Cuerpo planetario.
   *
   * @param name the name
   * @param radio the radio
   * @param temperature the temperature
   * @param mass the mass
   * @param gravity the gravity
   * @param system the system
   * @param habitable the habitable
   * @param satelitesList the satelites list
   */
  public CuerpoPlanetario(
      String name,
      long radio,
      double temperature,
      long mass,
      double gravity,
      String system,
      boolean habitable,
      LinkedList<Satelite> satelitesList) {
    super(name, radio, temperature, mass, gravity);
    this.system = system;
    this.habitable = habitable;
    this.satelitesList = satelitesList;
  }

  /**
   * Instantiates a new Cuerpo planetario.
   *
   * @param name the name
   * @param radio the radio
   * @param temperature the temperature
   * @param mass the mass
   * @param gravity the gravity
   * @param sistema the sistema
   * @param habitable the habitable
   */
  public CuerpoPlanetario(
      String name,
      long radio,
      double temperature,
      long mass,
      double gravity,
      String sistema,
      boolean habitable) {
    super(name, radio, temperature, mass, gravity);
    this.system = sistema;
    this.habitable = habitable;
  }

  /**
   * Gets system.
   *
   * @return the system
   */
  public String getSystem() {
    return system;
  }

  /**
   * Sets system.
   *
   * @param system the system
   */
  public void setSystem(String system) {
    this.system = system;
  }

  /**
   * Is habitable boolean.
   *
   * @return the boolean
   */
  public boolean isHabitable() {
    return habitable;
  }

  /**
   * Sets habitable.
   *
   * @param habitable the habitable
   */
  public void setHabitable(boolean habitable) {
    this.habitable = habitable;
  }

  /**
   * Gets satelites list.
   *
   * @return the satelites list
   */
  public LinkedList<Satelite> getSatelitesList() {
    return satelitesList;
  }

  /**
   * Sets satelites list.
   *
   * @param satelitesList the satelites list
   */
  public void setSatelitesList(LinkedList<Satelite> satelitesList) {
    this.satelitesList = satelitesList;
  }

  /**
   * Add satelite.
   *
   * @param satelite the satelite
   */
  public void addSatelite(Satelite satelite) {
    satelitesList.add(satelite);
  }
}
