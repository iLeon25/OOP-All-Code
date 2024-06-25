package hr.fer.oop.ZI2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor<Path>{
	private String filename;
	private String firstLine = null, folderName = null;

	public MyFileVisitor(String filename) {
		this.filename = filename;
	}
	
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		if (attrs.isDirectory()) {
			return FileVisitResult.CONTINUE;
		}
		
		if (file.getFileName().toString().equals(filename)) {
			
			try (BufferedReader bf = Files.newBufferedReader(file)) {
				firstLine = bf.readLine();
			}
			folderName = file.getParent().toAbsolutePath().toString();
		}
		
		
		return FileVisitResult.CONTINUE;
	}
	
	public String getFirstLine() throws IOException {
		if (firstLine == null) {
			throw new IOException(String.format("File %s does not exist!", filename));
		}
		return firstLine;
	}
	
	public String getFolderName() throws IOException {
		if (folderName == null) {
			throw new IOException(String.format("File %s does not exist!", filename));
		}
		return folderName;
	}
}
