package ru.advancedtraining.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class DemoIO {

    private static final String PERSON_FILE = "L-05-io/src/main/resources/person.bin";
    private static final String TEXT_FILE = "L-05-io/src/main/resources/textFile.txt";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Current dir: " + System.getProperty("user.dir"));
        // copyFile();
        writeObject();
        readObject();
       // writeTextFile();
        //readTextFile();

    }

    // Надо обратить внимание на цепочку декораторов
    private static void copyFile() throws IOException {
        try (var bufferedInputStream = new BufferedInputStream(new FileInputStream(TEXT_FILE));
             var zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(TEXT_FILE + "_copy.zip")))) {

            var zipEntry = new ZipEntry(TEXT_FILE);
            zipOut.putNextEntry(zipEntry);
            var buffer = new byte[2]; // специально маленький буфер, чтобы в дебаге посмотреть
            int size;
            while ((size = bufferedInputStream.read(buffer, 0, buffer.length)) > 0) {
                zipOut.write(buffer, 0, size);
            }
            zipOut.closeEntry();
        }
    }

    /**
     * Запись в файл объекта.
     */
    private static void writeObject() throws IOException {
        try (var fileOutputStream = new FileOutputStream(PERSON_FILE);
             var objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            var person = new Person(92, "SerialPerson", "hidden");
            System.out.println("Serializing:" + person);
            objectOutputStream.writeObject(person);
        }
    }

    /**
     * Чтение из файла объекта.
     */
    private static void readObject() throws IOException, ClassNotFoundException {
        try (var fileInputStream = new FileInputStream(PERSON_FILE);
             var objectInputStream = new ObjectInputStream(fileInputStream)) {

            var person = (Person) objectInputStream.readObject();
            System.out.println("Read object:" + person);
        }
    }

    /*
    Построчная запись в файл.
     */
    private static void writeTextFile() throws IOException {
        var line1 = "Hello Java, str1";
        var line2 = "Hello Java, str2";
        try (var bufferedWriter = new BufferedWriter(new FileWriter(TEXT_FILE))) {
            bufferedWriter.write(line1);
            bufferedWriter.newLine();
            bufferedWriter.write(line2);
        }
    }

    /*
     Построчное чтение файла.
     */
    private static void readTextFile() throws IOException {
        // FileReader - заточен на чтение текстовых файлов
        try (var bufferedReader = new BufferedReader(new FileReader(TEXT_FILE))) {
            System.out.println("text from the file:");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
