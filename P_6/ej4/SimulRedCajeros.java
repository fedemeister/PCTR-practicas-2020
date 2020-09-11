package ej4;

import java.util.concurrent.*;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * The interface Interface hacer ingreso.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
interface Interface_hacerIngreso {
  /**
   * Ingresar.
   *
   * @param ingreso the ingreso
   */
  void ingresar(double ingreso);
}

/**
 * The interface Interface hacer retirada.
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
interface Interface_hacerRetirada {
  /**
   * Retirar.
   *
   * @param retirada the retirada
   */
  void retirar(double retirada);
}

/**
 * The type Simul red cajeros.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class SimulRedCajeros implements Runnable {
  private final CuentaCorrienteSegura cuentaCorrienteSegura;
  private final Random random;
  private final int option;

  /**
   * Instantiates a new Simul red cajeros.
   *
   * @param cuentaCorrienteSegura the cuenta corriente segura
   * @param option the option
   */
  public SimulRedCajeros(CuentaCorrienteSegura cuentaCorrienteSegura, int option) {
    this.cuentaCorrienteSegura = cuentaCorrienteSegura;
    random = new Random(System.currentTimeMillis());
    this.option = option;
  }

  @Override
  public void run() {
    int i = 0;
    do {
      switch (option) {
        case 0:
          Interface_hacerRetirada hacerReintegro =
              (double retirada) -> {
                cuentaCorrienteSegura.retirar(retirada);
              };
          hacerReintegro.retirar(random.nextInt(100));
          break;
        case 1:
          Interface_hacerIngreso hacerIngreso =
              (double ingreso) -> {
                cuentaCorrienteSegura.ingresar(ingreso);
              };
          hacerIngreso.ingresar((int) (random.nextDouble() * 100));
          break;
        default:
          break;
      }
      ++i;
    } while (i < 100);
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    CuentaCorrienteSegura cuentaCorrienteSegura = new CuentaCorrienteSegura(7500.00);
    int nThreads = 2;
    ThreadPoolExecutor newFixedThreadPool =
        (ThreadPoolExecutor) Executors.newFixedThreadPool(nThreads);

    IntStream.range(0, nThreads)
        .mapToObj(i -> new SimulRedCajeros(cuentaCorrienteSegura, i))
        .forEachOrdered(newFixedThreadPool::submit);

    newFixedThreadPool.shutdown();
    try {
      newFixedThreadPool.awaitTermination(10000, TimeUnit.MILLISECONDS);
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }
    System.out.println("El saldo final es: " + cuentaCorrienteSegura.getSaldo());
  }
}
