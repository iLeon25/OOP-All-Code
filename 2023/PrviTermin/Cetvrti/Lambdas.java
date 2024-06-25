package hr.fer.oop.stats;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.AbstractMap.SimpleEntry;

public class Lambdas { 
	
	public static BiConsumer<Stream<String>, Map<String, Collection<Entry<Integer, Long>>>> loadFromStream = (lines, map) -> {
		lines.map((string) -> string.split(",")).forEach(array -> {
														map.compute(array[0], (key, value) -> {
															if (value == null) {
																value = new LinkedList<>();
															}
															value.add(new SimpleEntry<Integer, Long>(Integer.parseInt(array[1]), Long.parseLong(array[2])));
															return value;
														});
		});
	};
	
	public static BiFunction<Map<String, Collection<Entry<Integer, Long>>>, Integer, Collection<Entry<String, Long>>> getValuesForYear = (map, year) -> 
		map.entrySet().stream().map((entry) -> {
									for (Entry<Integer, Long> e : entry.getValue()) {
										if (e.getKey().equals(year))
											return new SimpleEntry<>(entry.getKey(), e.getValue());

									}
									return null;
		}).collect(Collectors.toList());
		
		
		
	public static BiFunction<Map<String, Collection<Entry<Integer, Long>>>, String, Entry<Integer, Long>> getMinForCountry = (map, country) -> 
		
		map.get(country).stream().min(new Comparator<Entry<Integer, Long>>() {

			@Override
			public int compare(Entry<Integer, Long> o1, Entry<Integer, Long> o2) {	
				return (int) (o1.getValue() - o2.getValue());
			}
			
		}).get();
}
