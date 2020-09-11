package ej5;

/**
 * The type Cuenta corriente.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class cuentaCorriente {

  private double money;
  private final int id;

  /**
   * Instantiates a new Cuenta corriente.
   *
   * @param money the money
   * @param id_ the id
   */
  public cuentaCorriente(double money, int id_) {

    this.money = money;
    this.id = id_;
  }

  /**
   * Gets money.
   *
   * @return the money
   */
  public double getMoney() {
    return money;
  }

  /**
   * Sets money.
   *
   * @param money the money
   */
  public void setMoney(double money) {
    this.money = money;
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
   * Withdraw money.
   *
   * @param quantity the quantity
   */
  public void withdrawMoney(double quantity) {
    this.money -= quantity;
  }

  /**
   * Deposit money.
   *
   * @param quantity the quantity
   */
  public void depositMoney(double quantity) {
    this.money += quantity;
  }
}
