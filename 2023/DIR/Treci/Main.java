package hr.fer.oop;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
	public static void main (String[] args) throws IOException {
		System.out.println(System.getProperty("user.dir"));
		//new File("/Users/Korisnik/desktop/testniprimjer/test/zbjxb").createNewFile();
		FileVisitorPasswordFinder pf = new FileVisitorPasswordFinder("/Users/Korisnik/desktop/testniprimjer/test");
		Path path = Path.of("/Users/Korisnik/Downloads/FER-OOP Exams master 2022_23-oop2023_DR_ver1_oop2023_DR_ver1_exercise3/src/main/resources");
		Files.walkFileTree(path, pf);
	}
}
