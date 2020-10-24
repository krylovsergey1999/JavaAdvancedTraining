package ru.advancedtraining.collections;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

//TODO please FIXME with BlockingQueue
// Вопросы:
// - Что делает это многопоточное приложение?
// - Какие есть проблемы в данном многопоточном приложении?
// - Запустим приложение прямо сейчас!
// - Фиксим тест сейчас!
// - *Для какого сценария по нагрузке больше всего подходит BlockingQueue?
public class FixMe5WithBlockingQueueTest {
	private static final Logger logger = LoggerFactory.getLogger(FixMe5WithBlockingQueueTest.class);

	private static final int ITERATIONS_COUNT = 1000;

	@Test
	public void testBlockingQueueWorksGreat() throws InterruptedException {

		var queue = new ArrayBlockingQueue<Integer>(10);
		final CountDownLatch latch = new CountDownLatch(1);
		List<Throwable> throwables = new ArrayList<>();

		Thread t1 = new Thread(() -> {
			try {
				latch.await();

				for (int i = 0; i < ITERATIONS_COUNT; i++) {
					logger.info(String.valueOf(queue.take()));
				}
			} catch (Throwable throwable) {
				throwables.add(throwable);
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				latch.await();
				for (int i = 0; i < ITERATIONS_COUNT; i++) {
					queue.put(i);
				}
			} catch (Throwable throwable) {
				throwables.add(throwable);
			}
		});

		t1.start();
		t2.start();

		latch.countDown();

		t1.join();
		t2.join();

		assertThat(throwables).withFailMessage(throwables.toString()).isEmpty();
	}
}
