package ru.advancedtraining.behavioral.memento;


class Memento {
  private final State state;

  Memento(State state) {
    this.state = new State(state);
  }

  State getState() {
    return state;
  }
}
