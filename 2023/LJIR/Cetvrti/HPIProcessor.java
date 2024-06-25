package hr.fer.oop.hpi;

import java.util.AbstractMap.SimpleEntry; 
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HPIProcessor {

	public static Function<Stream<String>, Set<HousePriceIndex>> loadFromStream = lines -> 
		lines.map((a) -> a.split("#")).map((array) -> {
								String region = array[1];
								RegionType regionType = null;
								
								switch(Integer.parseInt(array[0])) {
									case 1 -> {
										regionType = RegionType.COUNTRY;
									}
									case 2 -> {
										regionType = RegionType.LARGE_REGION;
									}
									case 3 -> {
										regionType = RegionType.SMALL_REGION;
										
									}
									case 4 -> {
										regionType = RegionType.CITY;
									}
								}
								return new HousePriceIndex(region, regionType, array[2], Double.parseDouble(array[3]));
		}).collect(Collectors.toSet());
	
	
	
	
	public static BiFunction<Set<HousePriceIndex>, RegionType, Map<String, Double>> collectionToMap = (set, regionType) -> {
		return set.stream().filter((a) -> a.getRegionType() == regionType)
				.collect(Collectors.toMap((a) -> a.getRegion().concat("#").concat(a.getTime()), (a) -> a.getValue()));

	};
		
	
	
	public static BiFunction<Map<String, Double>, String, Collection<Entry<String, Double>>> getValuesForTime = (map, time) -> 
		map.entrySet().stream().filter((a) -> a.getKey().contains(time))
							   .map((a) -> {
								   return new SimpleEntry<>(a.getKey().split("#")[0], a.getValue());
								   
							   }).sorted(new Comparator<Entry<String, Double>>() {

								@Override
								public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
									double r = Double.compare(o1.getValue(), o2.getValue());
									
									if (r > 0) {
										return 1;
									} else if (r < 0) {
										return -1;
									} else {
										return 0;
									}
								}
								   
							}).collect(Collectors.toList());

}
