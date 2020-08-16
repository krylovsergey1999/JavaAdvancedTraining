package ru.advancedtraining.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DemoSerialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        deserialize(serialize());
    }

    /**
     * Превращение объекта в массив байтов.
     */
    private static byte[] serialize() throws IOException {
        try (var byteArrayOutputStream = new ByteArrayOutputStream();
             var objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)
        ) {
            var person = new Person(12, "SerialPersonForArray", "value");
            // поле hidden т.к transient не сериализуется
            System.out.println("Serializing:" + person);
            objectOutputStream.writeObject(person); // используя обертку записываем в output stream
            return byteArrayOutputStream.toByteArray();
        }
    }

    /**
     * Превращение массива байтов в объект.
     */
    private static void deserialize(byte[] data) throws IOException, ClassNotFoundException {
        try (var byteArrayInputStream = new ByteArrayInputStream(data)) {
            var objectInputStream = new ObjectInputStream(byteArrayInputStream);
            var person = (Person) objectInputStream.readObject(); // тут конструктор не вызовется
            System.out.println("deSerialized person:" + person);
        }
    }
}
