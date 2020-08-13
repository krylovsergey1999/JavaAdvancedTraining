package ru.advancedtraining.behavioral.state;


public class OffState implements State {
  @Override
  public State action() {
    System.out.println("темно");
    return StateProvider.getOnState();
  }
}
