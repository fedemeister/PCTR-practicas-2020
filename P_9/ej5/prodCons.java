package ej5;

/*Productor consumidor con semafotos*/

import java.util.Random;
import java.util.concurrent.*;

/**
 * The type Prod cons.*
 *
 * @author Federico Carrillo Chaves
 * @version 1.3
 */
public class prodCons extends Thread {
  private static final int bufferSize = 100;
  private static double[] buffer;
  private static int InPtr = 0;
  private static int OutPtr = 0;
  private static final int products = 110;
  private static final Semaphore semaphoreMutualExclution = new Semaphore(1);
  private static final Semaphore semaphoreElements = new Semaphore(0);
  private static final Semaphore semaphoreSpaces = new Semaphore(bufferSize);
  private static final Random random = new Random();

  private final int threadType;

  /**
   * Instantiates a new Prod cons.
   *
   * @param threadType the thread type
   */
  public prodCons(int threadType) {
    this.threadType = threadType;
  }

  public void run() {
    switch (threadType) {
      case 0: // productores
        {
          for (int i = 0; i < products; i++) {
            int nextInt = random.nextInt(10);
            try {
              semaphoreSpaces.acquire();
            } catch (InterruptedException I) {
              I.printStackTrace();
            }

            try {
              semaphoreMutualExclution.acquire();
            } catch (InterruptedException I) {
              I.printStackTrace();
            }
            buffer[InPtr] = nextInt;
            System.out.println("InPtr = " + InPtr + " nextInt = " + nextInt);
            // System.out.println("PRODUCTOR insertando " + buffer[InPtr] + " en buffer");
            InPtr = (InPtr + 1) % bufferSize;
            semaphoreMutualExclution.release();
            semaphoreElements.release();
          }
          break;
        }

      case 1: // consumidores
        {
          for (int i = 0; i < products; i++) {
            try {
              semaphoreElements.acquire();
            } catch (InterruptedException I) {
              I.printStackTrace();
            }

            try {
              semaphoreMutualExclution.acquire();
            } catch (InterruptedException I) {
              I.printStackTrace();
            }
            System.out.println("\tOutPtr = " + OutPtr + " value = " + buffer[OutPtr]);
            // System.out.println("CONSUMIDOR extrayendo " + buffer[OutPtr] + " de buffer");

            OutPtr = (OutPtr + 1) % bufferSize;
            semaphoreMutualExclution.release();
            semaphoreSpaces.release();
          }
          break;
        }
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    buffer = new double[bufferSize];
    prodCons thread_1 = new prodCons(0); // productor
    prodCons thread_2 = new prodCons(1); // consumidor
    prodCons thread_3 = new prodCons(0); // productor
    prodCons thread_4 = new prodCons(1); // consumidor

    thread_1.start();
    thread_2.start();
    thread_3.start();
    thread_4.start();

    try {
      thread_1.join();
      thread_2.join();
      thread_3.join();
      thread_4.join();
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }
  }
}
