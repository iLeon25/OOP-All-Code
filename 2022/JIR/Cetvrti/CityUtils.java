package hr.fer.oop.t4;

import java.util.Collections; 
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CityUtils {

	//big city == population > 1 000 000
	public static long countBigCitiesInACountry(Stream<City> cities, String countryName) {
		return cities.filter((c) -> c.getPopulation() > 1000000)
				     .filter((c) -> c.getCountryName().equalsIgnoreCase(countryName))
				     .count();

	}
	
	// northest City == city with max lat
	public static City northestCityInACountry(Stream<City> cities, String countryISO2) {
	
		return cities.filter(c -> c.getCountryISO2().equals(countryISO2))
					 .max((c1, c2) -> (Double.compare(c1.getLat(), c2.getLat()))).get();
//		List<City> lat = new LinkedList<City>();
//		lat = cities.filter((c) -> c.getCountryISO2().equalsIgnoreCase(countryISO2))
//                .collect(Collectors.toList());
//		
//		Comparator<City> latComparator = (o1, o2) -> {
//			double r = o2.getLat() - o1.getLat();
//			if (r < 0) {
//				return -1;
//			}
//			if (r > 0) {
//				return 1;
//			}
//			return 0;
//		};
//		Collections.sort(lat, latComparator);
//		
//		
//		return lat.get(0); // zasto ovo ne radi?
	}
	
	// returns ascii name!
	public static Stream<String> capitalCitiesStartingWithALetter(Stream<City> cities, char letter) {
		List<String> names = cities.filter((c) -> c.getAsciiName().startsWith("" + letter))
				.map((c) -> c.getName()).collect(Collectors.toList());
		
		
		
		return names.stream();
	}

}
