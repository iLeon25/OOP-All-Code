package hr.fer.oop.ZI2024.zad1;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

public class MyFileVisitor implements FileVisitor<Path>{
	private List<Item> items = new LinkedList<Item>();
	private double totalPrice = 0;
	private int totalQuantity; 
	
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		if (attrs.isDirectory()) {
			return FileVisitResult.CONTINUE;
		}
			
		if(file.getFileName().toString().endsWith(".txt")) { 
			if (Files.isRegularFile(file)) {
				
				try (BufferedReader buff = Files.newBufferedReader(file)) {
					String line;
					
					while ((line = buff.readLine()) != null) {
						String[] split = line.split("#");
						if (split.length != 3) {
							throw new Exception();
							
						}
						String itemName;
						int itemQuantity;
						double price;
						
						try {
							itemName = split[0];
							itemQuantity = Integer.parseInt(split[1]);
							price = Double.parseDouble(split[2]);
							
							
						} catch (Exception e) {
							throw new Exception();
						}
						
						
						totalQuantity += itemQuantity;
						totalPrice += price * itemQuantity;
						
						items.add(new Item(itemName, itemQuantity, price));
					}
					
					
				} catch (Exception e) {
					
				}
			
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
	
	
	public List<Item> getItems() {
		return items;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public int getTotalQuantity() {
		return totalQuantity;
	}
}
