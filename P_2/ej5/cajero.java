package ej5;

/**
 * The type Cajero.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class cajero implements Runnable {
  private final boolean ingresar;
  private final double quantity;
  private final cuentaCorriente cuentaCorriente;

  public void run() {
    if (this.ingresar) {
      this.cuentaCorriente.depositMoney(this.quantity);
    } else {
      this.cuentaCorriente.withdrawMoney(this.quantity);
    }
  }

  /**
   * Instantiates a new Cajero.
   *
   * @param ingresar the ingresar
   * @param cuentaCorriente the cuenta corriente
   * @param quantity the quantity
   */
  public cajero(boolean ingresar, cuentaCorriente cuentaCorriente, double quantity) {
    this.ingresar = ingresar;
    this.quantity = quantity;
    this.cuentaCorriente = cuentaCorriente;
  }
}
