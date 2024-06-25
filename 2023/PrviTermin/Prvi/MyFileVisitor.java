package hr.fer.oop.ZI2023;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor<Path>{
	private int noOfDirectories;
	private int noOfFiles;
	private long largestFileSize;
	private String[] extensions;

	public MyFileVisitor(String[] extensions) {
		this.extensions = extensions;
		noOfFiles = 0;
		noOfDirectories = 0;
	}
	

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		noOfDirectories++;
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		if (attrs.isDirectory()) {
			return FileVisitResult.CONTINUE;
		}
		if (Files.isRegularFile(file)) {
			for (String ext : extensions) {
				if (file.getFileName().toString().endsWith(ext)) {
					noOfFiles++;
				}
			}
			if (attrs.size() > largestFileSize) {
				largestFileSize = attrs.size();
			}
		}
		
		return FileVisitResult.CONTINUE;
	}
	
	public int getNumFiles() {
		return noOfFiles;
	}
	
	public int getNumFolders() {
		return noOfDirectories;
	}
	
	public long getLargestFileSize() {
		return largestFileSize;
	}
}
