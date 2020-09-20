package ru.advancedtraining.logging.ex04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerSyslogExample {
  private static final Logger logger = LoggerFactory.getLogger(LoggerSyslogExample.class);
  private long counter = 0;

  public static void main(String[] args) throws InterruptedException {
    new LoggerSyslogExample().loop();
  }

  private void loop() throws InterruptedException {
    while (!Thread.currentThread().isInterrupted()) {
      logger.error("msg for Syslog:{}", counter);
      counter++;
      Thread.sleep(3_000);
    }
  }
}
