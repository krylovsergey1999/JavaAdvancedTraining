package ru.advancedtraining.collections;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

//TODO please FIXME with ConcurrentHashMap
// Вопросы:
// - Какую коллекцию будем менять и на какую?
// - Фиксим тест сейчас!
// - *Для какого сценария по нагрузке больше всего подходит ConcurrentHashMap?
public class FixMe4WithConcurrentHashMapUnitTest {
	private static final Logger logger = LoggerFactory.getLogger(FixMe4WithConcurrentHashMapUnitTest.class);

	private static final int ITERATIONS_COUNT = 1000;

	@Test
	public void testConcurrentHashMapWorksGreat() throws InterruptedException {

		final Map<String, String> map = new ConcurrentHashMap<>();
		final CountDownLatch latch = new CountDownLatch(1);
		List<Throwable> throwables = new ArrayList<>();

		Thread t1 = new Thread(() -> {
			try {
				latch.await();
				for (int i = 0; i < ITERATIONS_COUNT; i++) {
					logger.info("starting adding email " + i);
					String s = randomAlphabetic(10) + "@gmail.com";
					map.put(s, s);
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
					map.forEach((k, v) -> logger.info(k));
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
