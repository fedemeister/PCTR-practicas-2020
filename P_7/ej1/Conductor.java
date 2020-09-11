package ej1;

/**
 * The type Conductor.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class Conductor {
  private int id;
  private String name;
  private int licensePoints;
  private boolean active;

  /**
   * Instantiates a new Conductor.
   *
   * @param id the id
   * @param name the name
   * @param licensePoints the license points
   * @param active the active
   */
  public Conductor(int id, String name, int licensePoints, boolean active) {
    this.id = id;
    this.name = name;
    this.licensePoints = licensePoints;
    this.active = active;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(int id) {
    this.id = id;
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
   * Is active boolean.
   *
   * @return the boolean
   */
  public boolean isActive() {
    return active;
  }

  /**
   * Sets active.
   *
   * @param active the active
   */
  public void setActive(boolean active) {
    this.active = active;
  }

  /**
   * Gets license points.
   *
   * @return the license points
   */
  public int getLicensePoints() {
    return licensePoints;
  }

  /**
   * Sets license points.
   *
   * @param licensePoints the license points
   */
  public void setLicensePoints(int licensePoints) {
    this.licensePoints = licensePoints;
  }

  @Override
  public String toString() {
    return "Conductor{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", licensePoints="
        + licensePoints
        + ", active="
        + active
        + '}';
  }
}
