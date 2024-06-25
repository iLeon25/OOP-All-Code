package hr.fer.oop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException; 
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class FileVisitorPasswordFinder implements FileVisitor<Path>{
	public final String resultDirectoryPath;
	private Map<String, Long> nameAndSize;
	
	public FileVisitorPasswordFinder(String resultDirectoryPath) {
		this.resultDirectoryPath = resultDirectoryPath;
		nameAndSize = new HashMap<>();
	}
	
	public String getResultDirectoryPath() {
		return resultDirectoryPath;
	}
	
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		if (attrs.isDirectory()) {
			return FileVisitResult.CONTINUE;
		}
		
		if (Files.isRegularFile(file)) {
			if (file.getFileName().toString().endsWith(".txt")) {
				
				try (BufferedReader buff = Files.newBufferedReader(file)) {
					String line = "";
					boolean hasPassword = false;
					try (BufferedWriter buffW = Files.newBufferedWriter(Path.of(resultDirectoryPath, file.getFileName().toString()))) {
						while ((line = buff.readLine()) != null) {
							if (line.contains("password")) {
								hasPassword = true;
								buffW.append(line);
								buffW.append("\n");
							}
						}
					}
					
					if (hasPassword) 
						System.out.println(file.getFileName().toString() + " " + attrs.size());
				}
			}
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		System.out.println("Womp");
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		return FileVisitResult.CONTINUE;
	}
}
