package ru.advancedtraining.jmm;

/**
 * @author Krylov Sergey (sergey.krylov@lanit-tercom.com) (17.10.2020)
 */
public class CounterVolatile {
	private volatile int count = 0;
	private static final int LIMIT = 100_000_000;

	public static void main(String[] args) throws InterruptedException {
		new CounterVolatile().go();
	}

	private void inc() {
		for (int i = 0; i < LIMIT; i++) {
			count++;
		}
	}

	private void go() throws InterruptedException {
		Thread thread1 = new Thread(this::inc);
		Thread thread2 = new Thread(this::inc);
		Thread thread3 = new Thread(this::inc);

		thread1.start();
		thread2.start();
		thread3.start();

		thread1.join();
		thread2.join();
		thread3.join();
		System.out.println("CounterBroken:" + count);
	}
}
