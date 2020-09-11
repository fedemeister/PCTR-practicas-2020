package ej1;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * The type Participante triatlon.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
class ParticipanteTriatlon implements Runnable {
  private static int[] vectorPartialResults;

  private final int id;
  private final CyclicBarrier swimming;
  private final CyclicBarrier cycling;
  private final CyclicBarrier running;

  /**
   * Instantiates a new Participante triatlon.
   *
   * @param id the id
   * @param partialResults the partial results
   * @param swimming the swimming barrier
   * @param cycling the cycling barrier
   * @param running the running barrier
   */
  public ParticipanteTriatlon(
      int id,
      int[] partialResults,
      CyclicBarrier swimming,
      CyclicBarrier cycling,
      CyclicBarrier running) {
    this.id = id;
    ParticipanteTriatlon.vectorPartialResults = partialResults;
    this.swimming = swimming;
    this.cycling = cycling;
    this.running = running;
  }

  public void run() {
    int res;
    try {
      res = (int) (Math.random() * 1000);
      Thread.sleep(res);
      System.out.println(
          "Fin natación: Thread.currentThread().getName() = " + Thread.currentThread().getName());
      vectorPartialResults[id] += res;
      swimming.await(); // barrera

      res = (int) (Math.random() * 1000);
      Thread.sleep(res);
      System.out.println(
          "Fin ciclismo: Thread.currentThread().getName() = " + Thread.currentThread().getName());
      vectorPartialResults[id] += res;
      cycling.await(); // barrera

      res = (int) (Math.random() * 1000);
      Thread.sleep(res);
      System.out.println(
          "Fin atletismo: Thread.currentThread().getName() = " + Thread.currentThread().getName());
      vectorPartialResults[id] += res;
      running.await(); // barrera

    } catch (InterruptedException | BrokenBarrierException e) {
      e.printStackTrace();
    }
  }
}

/**
 * The type Triatlon barreras.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class triatlonBarreras {
  /** The constant nParticipants. The number of participants. */
  public static int nParticipants = 100;

  /**
   * The constant partialResults. Partial results of each participant Cada tarea toma tiempos cuando
   * inicia y acaba cada posta, y acumula los tiempos totales para el triatlon completo en un array
   * de tiempos de 100 ranuras; una por cada tarea.
   */
  public static int[] vectorPartialResults = new int[nParticipants];

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    CyclicBarrier cyclicBarrier_swimming = new CyclicBarrier(nParticipants);
    CyclicBarrier cyclicBarrier_cycling = new CyclicBarrier(nParticipants);
    CyclicBarrier cyclingBarrier_running = new CyclicBarrier(nParticipants);

    ThreadPoolExecutor threadPoolExecutor =
        new ThreadPoolExecutor(
            nParticipants,
            nParticipants,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    threadPoolExecutor.prestartAllCoreThreads();

    IntStream.range(0, nParticipants)
        .mapToObj(
            i ->
                new ParticipanteTriatlon(
                    i,
                    vectorPartialResults,
                    cyclicBarrier_swimming,
                    cyclicBarrier_cycling,
                    cyclingBarrier_running))
        .forEachOrdered(threadPoolExecutor::execute);

    threadPoolExecutor.shutdown();

    try {
      while (!threadPoolExecutor.awaitTermination(30000, TimeUnit.MILLISECONDS))
        ;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    int min = vectorPartialResults[0];
    int idMin = 0;
    for (int i = 1; i < nParticipants; ++i)
      if (vectorPartialResults[i] < min) {
        min = vectorPartialResults[i];
        idMin = i;
      }
    System.out.println("resultadosParciales = " + Arrays.toString(vectorPartialResults));
    System.out.println("idMin = " + idMin);
    System.out.println("partialResults[idMin] = " + vectorPartialResults[idMin]);
    System.out.println(
        "\nGanador el participante = "
            + idMin
            + " con un resultado = "
            + vectorPartialResults[idMin]);
  }
}
