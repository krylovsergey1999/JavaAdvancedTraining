package ru.advancedtraining.nio;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class PathExample {
    public static void main(String[] args) throws IOException {
        new PathExample().go();
    }

    private void go() throws IOException {
        Path shareXml = Paths.get("L-06-nio/src/main/resources/share.xml");
        System.out.println("FileName:" + shareXml.getFileName());
        System.out.println("FileSystem:" + shareXml.getFileSystem());
        System.out.println("Parent:" + shareXml.getParent());
        System.out.println("isAbsolute:" + shareXml.isAbsolute());
        System.out.println("realPath:" + shareXml.toRealPath());

        Path notExists = Paths.get("L-06-nio/src/main/resources/NotExists.xml");
        try {
            System.out.println("realPath:" + notExists.toRealPath());
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }
}
