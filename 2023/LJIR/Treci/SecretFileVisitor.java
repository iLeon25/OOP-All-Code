package hr.fer.oop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

//A2

public class SecretFileVisitor implements FileVisitor<Path>{
	private String decryptionKey;
	private int secretFileCount;
	private List<Path> foundFiles;
	
	
	public SecretFileVisitor(String decryptionKey) {
		this.decryptionKey = decryptionKey;
		foundFiles = new LinkedList<Path>();
		secretFileCount = 0;
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
			String line = decryptText(file);
			if (line.contains(decryptionKey)) {
				secretFileCount++;
				foundFiles.add(file);
			}
		}
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		System.out.println(exc.getStackTrace());
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		return FileVisitResult.CONTINUE;
	}
	
	public int getSecretFileCount() {
		return secretFileCount;
	}
	
	public List<Path> getFoundFiles() {
		return foundFiles;
	}
	
	public String decryptText(Path file) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		try (BufferedReader bfr = new BufferedReader(new InputStreamReader(Files.newInputStream(file)))) {
			String line = "";
			while ((line = bfr.readLine()) != null) {
				sb.append(decrypt(line));
			}
		}
		return sb.toString();
	}
	
	private String decrypt(String encryptedText) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(encryptedText);
		
		return sb.reverse().toString();
	}
}
 
