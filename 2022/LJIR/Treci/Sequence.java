package hr.fer.oop.ljir.z3;

import java.io.BufferedReader; 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class Sequence {

	private String name;
	private String sequence;
	private String quality;

	public Sequence(String name, String sequence, String quality) {
		super();
		this.name = name;
		this.sequence = sequence;
		this.quality = quality;
	}

	public String getName() {
		return name;
	}

	public String getSequence() {
		return sequence;
	}

	public String getQuality() {
		return quality;
	}
	
	public String toString() {
		return "---SEQUENCE---\n"
			 + "Name: " + this.name + "\n"
			 + "Seq : " + this.sequence + "\n"
			 + "Qual: " + this.quality + "\n";
	}

	public boolean equals(Object other) {
		if (! (other instanceof Sequence)) return false;
		Sequence otherSeq = (Sequence)other;
		
		if (!this.name.equals(otherSeq.getName())) return false;
		if (!this.sequence.equals(otherSeq.getSequence())) return false;
		if (!this.quality.equals(otherSeq.getQuality())) return false;
		return true;
	}

	public double avgSeqQuality()  {
		int length = sequence.length();
		double score = 0;
		
		for (int i = 0; i < length; i++) {
			score += quality.charAt(i);
			
		}
		
		return score / length;
	}
	
	public static List<Sequence> loadFromFile(String path) throws IOException {
		List<Sequence> loadList = new LinkedList<>();
		String name = null, sequence = null, quality = null;
		
		int counter = 0;
		try (BufferedReader biff = Files.newBufferedReader(Path.of(path))) {
			String line = "";
			
			while ((line = biff.readLine()) != null) {
				if (counter == 4) {
					counter = 0;
				}
				
				if (counter == 0) {
					name = line;
					
				} else if (counter == 2) {
					sequence = line;
					
				} else if (counter == 3) {
					quality = line;
				}
				if (counter == 3) {
					loadList.add(new Sequence(name, sequence, quality));
				}
				
				counter++;
			}
		}
		
//		List<String> dnaList = new ArrayList<String>();
//		int counter = 1;
//		
//		try (BufferedReader bf = Files.newBufferedReader(Path.of(path))) {
//			String line = "";
//			while ((line = bf.readLine()) != null) {
//				counter++;
//				if (counter % 4 == 0) {
//					dnaList.add(line);
//				}
//			}
//		}
		
		
		return loadList;
	}
	
}
