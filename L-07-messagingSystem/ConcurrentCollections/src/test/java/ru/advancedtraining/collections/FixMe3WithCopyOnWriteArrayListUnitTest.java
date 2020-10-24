package ru.advancedtraining.collections;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

//TODO please FIXME with CopyOnWriteArrayList
// Вопросы:
// - Какую коллекцию будем менять и на какую?
// - Фиксим тест сейчас!
// - *Для какого сценария по нагрузке больше всего подходит CopyOnWriteArrayList?
public class FixMe3WithCopyOnWriteArrayListUnitTest {
	private static final Logger logger = LoggerFactory.getLogger(FixMe3WithCopyOnWriteArrayListUnitTest.class);

	private static final int ITERATIONS_COUNT = 1000;

	@Test
	public void testCopyOnWriteArrayListWorksGreat() throws InterruptedException {

		final List<String> list = new CopyOnWriteArrayList<>();
		final CountDownLatch latch = new CountDownLatch(1);
		List<Throwable> throwables = new CopyOnWriteArrayList<>();

		Thread t1 = new Thread(() -> {
			try {
				latch.await();
				for (int i = 0; i < ITERATIONS_COUNT; i++) {
					logger.info("starting adding email " + i);
					list.add(randomAlphabetic(10) + "@gmail.com");
					logger.info("finishing adding email " + i);
				}
			} catch (Throwable throwable) {
				throwables.add(throwable);
			}
		});
		Thread t2 = new Thread(() -> {
			try {
				latch.await();
				for (int i = 0; i < ITERATIONS_COUNT; i++) {
					logger.info("starting read iteration " + i);
					for (String email : list) {
						logger.info(email);
					}
					logger.info("finishing read iteration " + i);
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
