package ru.advancedtraining.behavioral.chain;

public class Demo {
    public static void main(String[] args) {
        Application appl = new Application();

        ApplProcessor input = new ApplicationInput();
        ApplProcessor reader = new ApplicationReader();
        ApplProcessor result = new ApplicationResult();

        input.setNext(reader);
        reader.setNext(result);

        input.process(appl);
        appl.printHistory();
    }
}
