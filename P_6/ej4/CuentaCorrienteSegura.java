package ej4;

/**
 * The type Cuenta corriente segura.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class CuentaCorrienteSegura {
  private double saldo;

  /**
   * Instantiates a new Cuenta corriente segura.
   *
   * @param saldo the saldo
   */
  public CuentaCorrienteSegura(double saldo) {
    this.saldo = saldo;
  }

  /**
   * Gets saldo.
   *
   * @return the saldo
   */
  public double getSaldo() {
    return saldo;
  }

  /**
   * Retirar.
   *
   * @param r the r
   */
  public void retirar(double r) {
    synchronized (this) {
      System.out.println("El saldo previo al reintegro es: " + saldo);
      if (saldo > r) {
          saldo -= r;
          System.out.println("\t\t RETIRADA DE " + r);
          System.out.println("El saldo despues del reintegro es: " + saldo + "\n");
      }
      else {
          System.out.println("No hay saldo suficiente para completar la transaccion");
      }
    }
  }

  /**
   * Ingresar.
   *
   * @param i the
   */
  public void ingresar(double i) {
    synchronized (this) {
      System.out.println("El saldo previo al ingreso es: " + saldo);
      saldo += i;
      System.out.println("\t\t INGRESO DE " + i);
      System.out.println("El saldo después del ingreso es: " + saldo + "\n");
    }
  }
}
