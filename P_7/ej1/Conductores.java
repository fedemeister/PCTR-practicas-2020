package ej1;

import java.util.Random;

/**
 * The type Conductores.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
// Monitor
public class Conductores {
  private final Conductor[] vectorConductores;
  private int actual = 0;

  /**
   * Instantiates a new Conductores.
   *
   * @param t the t
   */
  public Conductores(int t) {
    vectorConductores = new Conductor[t];
  }

  /**
   * Get data base conductor [ ].
   *
   * @return the conductor [ ]
   */
  public Conductor[] getDataBase() {
    return vectorConductores;
  }

  /**
   * Add conductor.
   *
   * @param id the id
   * @param name the name
   * @param active the active
   */
  synchronized void addConductor(int id, String name, boolean active) {
    vectorConductores[actual++] = new Conductor(id, name, 10, active);
  }

  /**
   * Delete conductor.
   *
   * @param id the id
   */
  synchronized void deleteConductor(int id) {
    boolean flag = false;
    int i = 0;
    while (i < vectorConductores.length && !flag) {
      Conductor conductor = vectorConductores[i];
      if (conductor.getId() == id) {
        vectorConductores[i].setActive(false);
        flag = true;
      }
      ++i;
    }
  }
}
