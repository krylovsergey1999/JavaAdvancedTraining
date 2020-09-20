package ru.advancedtraining.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FilesExample {
	public static void main(String[] args) throws IOException {
		new FilesExample().go();
	}

	private void go() throws IOException {
		Path path = Paths.get("L-06-nio/src/main/resources/share.xml");
		boolean pathExists = Files.exists(path);
		System.out.println("pathExists:" + pathExists);

		Path pathNE = Paths.get("L-06-nio/src/main/resources/NE.xml");
		boolean pathNotExists = Files.exists(pathNE);
		System.out.println("\npathExists:" + pathNotExists);

		Path tmpDir = Paths.get("L-06-nio/tmp");
		if (!Files.exists(tmpDir)) {
			Files.createDirectory(tmpDir);
		}

		Path source = Paths.get("L-06-nio/src/main/resources/share.xml");
		Path destination = Paths.get("L-06-nio/tmp/share.xml");

		//Files.copy(source, destination);


//    long size = Files.size(path);
//    System.out.println("\nfile size: " + size);
//
//
//    System.out.println("\ncontentAll:");
//
//    try (Stream<String> stream = Files.lines(path)) {
//      stream.forEach(System.out::println);
//    }
//
//    System.out.println("\nfiltered:");

//    try (var stream = Files.lines(path)) {
//      stream.filter(line -> line.contains("share"))
//              .map(String::toUpperCase)
//              .forEach(System.out::println);
//    }
//
//    String testString = "Test-Test-Data-String";
//    Files.write(Paths.get("L-06-nio/tmp/newFile.txt"), testString.getBytes());
	}
}
