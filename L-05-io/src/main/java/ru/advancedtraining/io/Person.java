package ru.advancedtraining.io;

import java.io.Serializable;

/**
 * Implements Serializable - обязательное условие для сериализации.
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 206125185905603871L; // нельзя забывать про serialVersionUID

    private final int age;
    private final String name;
    private final transient String hidden; //transient - поле будет пропущено при сериализации

  //  public String newField ="ddd";  //опыт с полем

    // Важно заметить то , сколько раз вызывается конструктор и сколько объектов создается
    Person(int age, String name, String hidden) {
        System.out.println("new Person");
        this.age = age;
        this.name = name;
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", hidden='" + hidden + '\'' +
   //             ", newField='" + newField + '\'' +
                '}';
    }
}
