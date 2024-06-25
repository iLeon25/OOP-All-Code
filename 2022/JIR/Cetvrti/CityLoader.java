package hr.fer.oop.t4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CityLoader {

	public static List<City> load(String path) throws IOException {

		Path filePath = Path.of(path);

		List<City> list = new ArrayList<>();

		// TO-DO: add code to load data from file to the list
		try (BufferedReader bf = Files.newBufferedReader(filePath)) {
			String line = "";
			while ((line = bf.readLine()) != null) {
				
				String[] split = line.split(",");
				String name = split[0].replace("\"", "");
				String asciiName = split[1].replace("\"", "");
				double lat = Double.parseDouble(split[2].replace("\"", ""));
				double lng = Double.parseDouble(split[3].replace("\"", ""));
				String countryName = split[4].replace("\"", "");
				String countryISO2 = split[5].replace("\"", "");
				String countryISO3 = split[6].replace("\"", "");
				CityType capitalType;
				String capitalTypeName = split[8].replace("\"", "");
				if (capitalTypeName.equalsIgnoreCase("primary")) {
					capitalType = CityType.PRIMARY;
					
				} else if (capitalTypeName.equalsIgnoreCase("minor")) {
					capitalType = CityType.MINOR;
					
				} else if (capitalTypeName.equalsIgnoreCase("unknown")) {
					capitalType = CityType.UNKNOWN;
					
				} else {
					
					capitalType = null;
				}
				
				int population = Integer.parseInt(split[9].replace("\"", ""));
				int id = Integer.parseInt(split[10].replace("\"", ""));
				
				list.add(new City(name, asciiName, lat, lng, countryName, countryISO2, countryISO3, capitalType, population, id));
			}
		}

		return list;

	}

}
