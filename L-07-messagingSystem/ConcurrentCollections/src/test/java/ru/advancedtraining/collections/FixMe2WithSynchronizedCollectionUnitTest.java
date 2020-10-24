package ru.advancedtraining.collections;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static java.lang.System.out;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

//TODO please FIXME with synchronized collection
// Вопросы:
// - Какую коллекцию будем синхронизировать и как?
// - Фиксим тест сейчас!
// - Разбираем результаты фикса.
// - Какие проблемы остаются в коде?
// - *Что особенного в методе join() в точки зрения видимости?
public class FixMe2WithSynchronizedCollectionUnitTest {
	private static final Logger logger = LoggerFactory.getLogger(FixMe2WithSynchronizedCollectionUnitTest.class);

	private static final int ITERATIONS_COUNT = 1000;

	@Test
	public void testSyncCollectionWorksGreat() throws InterruptedException {

		final List<String> list = new ArrayList<>();
		final CountDownLatch latch = new CountDownLatch(1);
		List<Throwable> throwables = Collections.synchronizedList(new ArrayList<>());

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
					// Это упадет с багой
//					for (String email : list) {
//						logger.info(email);
//					}
					// Это сработает, т.к не завязано на итератор
					for(var j = 0; j < list.size(); j++) {
						logger.info(list.get(j));
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
