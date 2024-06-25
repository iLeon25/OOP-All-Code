package hr.fer.oop.zi.z1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class BillionaireLoader {
	
	public static List<BillionaireDatum> load (String path) throws IOException {
		Path p = Paths.get(path);
		List<BillionaireDatum> outputLoaded = new LinkedList<BillionaireDatum>();
		
		try (BufferedReader buff = Files.newBufferedReader(p)) {
			String line = buff.readLine();
			
			while ((line = buff.readLine()) != null) {
				String[] split = line.split("\t");
				int rank = Integer.parseInt(split[0]);
				String name = split[1];
				int worth, age;
				
				if (split.length != 5) {
					worth = Integer.parseInt(split[2]);
					String country = split[3]; 
					outputLoaded.add(new BillionaireDatum(rank, name, worth, country));
					
				} else {
					age = Integer.parseInt(split[2]);
					worth = Integer.parseInt(split[3]);
					String country = split[4]; 
					outputLoaded.add(new BillionaireDatum(rank, name, age, worth, country));
				}
			}
		}
		
		return outputLoaded;
	}
 
}
