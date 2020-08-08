package ru.advancedtraining.behavioral.chain;

public class ApplicationReader extends ApplProcessor {

  @Override
  protected void processInternal(Application application) {
    application.addHistoryRecord("Заявление рассмотрено");
  }

  @Override
  public String getProcessorName() {
    return "Рассмотрение заявления";
  }
}
