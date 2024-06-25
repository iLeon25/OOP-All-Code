package hr.fer.oop.stats;

import java.util.Collection; 
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.AbstractMap.SimpleEntry;


public class Lambdas {
	
    public static BiConsumer<Stream<String>, Map<String, Map<String, Double>>> loadFromStream = (lines, map) -> 
    	lines.map((string) -> string.split("\t")).forEach(array -> {
    												map.compute(array[1], (key, value) -> {
    													if (value == null) {
    														value = new HashMap<>();
    													}
    													value.put(array[0], Double.parseDouble(array[2]));
    													return value;
    												});
    	});
     

    public static BiFunction<Map<String, Map<String, Double>>, String, Entry<String, Double>> getMinForTime = (map, time) -> 
    	map.get(time).entrySet().stream().min((o1, o2) -> Double.compare(o1.getValue(), o2.getValue())).get();
    
    	// ne vidim zasto ovo ne bi radilo?
  

    public static BiFunction<Map<String, Map<String, Double>>, String, Collection<Entry<String, Double>>> getValuesForCountry = (map, country) -> {
		return map.entrySet().stream()
							 .map(e -> new SimpleEntry<>(e.getKey(), e.getValue().get(country)))
							 .collect(Collectors.toList());
    };
}









