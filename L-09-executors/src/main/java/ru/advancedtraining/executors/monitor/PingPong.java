package ru.advancedtraining.executors.monitor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Krylov Sergey (sergey.krylov@lanit-tercom.com) (17.10.2020)
 */
public class PingPong {
	private static final Logger logger = LoggerFactory.getLogger(PingPong.class);
	private String last = "PONG"; // не требуется volatile т.к happens before у нас работает уже

	private synchronized void action(String message) {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			try {
				// spurious wakeup https://en.wikipedia.org/wiki/Spurious_wakeup
				// поэтому не if
				while (last.equals(message)) {
					this.wait(); // тут happens before
				}

				logger.info(message);
				last = message;
				sleep();
				notifyAll();
				logger.info("after notify, i:{}", i);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
				throw new NotInterestingException(ex);
			}
		}
	}

	public static void main(String[] args) {
		PingPong pingPong = new PingPong();
		new Thread(() -> pingPong.action("ping")).start();
		new Thread(() -> pingPong.action("PONG")).start();
	}

	private static void sleep() {
		try {
			Thread.sleep(1_000);
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
			Thread.currentThread().interrupt();
		}
	}

	private static class NotInterestingException extends RuntimeException {
		NotInterestingException(InterruptedException ex) {
			super(ex);
		}
	}
}