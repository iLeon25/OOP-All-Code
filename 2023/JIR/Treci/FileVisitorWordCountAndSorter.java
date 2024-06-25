package hr.fer.oop;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class FileVisitorWordCounterAndSorter implements FileVisitor<Path>{
	private String searchedWord;
	private Map<String, Integer> repAndLocation;
	private Map<Long, String> sizeAndLocation;
	
	public FileVisitorWordCounterAndSorter(String searchedWord) {
		this.searchedWord = searchedWord;
		repAndLocation = new HashMap<>();
		sizeAndLocation = new HashMap<>();
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
		
		
		if (file.getFileName().toString().endsWith(".txt")) {
			
			try (BufferedReader buff = Files.newBufferedReader(file)) {
				int counter = 0;
				String line = "";
				while ((line = buff.readLine()) != null) {
					if (line.contains(searchedWord)) {
						counter++;
					}
				}
				repAndLocation.put(file.getParent().toAbsolutePath().toString(), counter);
				sizeAndLocation.put(attrs.size(), file.getParent().toAbsolutePath().toString());
			}
			
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		return FileVisitResult.CONTINUE;
	}
	
	
	public void copyToSortedFilesFolder(String sortedFilesFolderPath) throws IOException  {
		List<Long> sizeList = new LinkedList<>();
		for (long key : sizeAndLocation.keySet()) {
			sizeList.add(key);
		}
		
		sizeList.sort((a, b) -> b.compareTo(a));
		int counter = 0;
		for (long l : sizeList) {
			counter++;
			String newPathName = sortedFilesFolderPath + counter + ".txt";
			Path newPath = Path.of(newPathName);
			
			try {
				Files.copy(Paths.get(sizeAndLocation.get(l)), newPath);
			} catch (NoSuchFileException e) {
				Files.createDirectories(Path.of(sortedFilesFolderPath));
				Files.copy(Paths.get(sizeAndLocation.get(l)), newPath);
			}
		}
	}
