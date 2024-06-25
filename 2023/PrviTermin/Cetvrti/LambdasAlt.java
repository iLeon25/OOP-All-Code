import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lambdas {

   public static BiConsumer<Stream<String>, Map<String, Collection<Entry<Integer, Long>>>> loadFromStream = (lines, map) -> {
   	lines.map(line -> line.split(",")).forEach(array -> map.compute(array[0], (k, v) -> {
   		if (v == null) {
   			v = new LinkedList<>();
   		}
   		v.add(new SimpleEntry<>(Integer.parseInt(array[1]), Long.parseLong(array[2])));
   		return v;
   	}));
   		
   	};
   	
//      String input = lines.collect(Collectors.joining());
//      String[] splitter = input.split(",");          
//      map.compute(splitter[0], (key, value) -> {
//      if (value == null) {
//          value = new HashMap<K, V>();
//      }
//            	
//        	
//     });   zas ovo ne mogu ovako?  
   
   public static BiFunction<Map<String, Collection<Entry<Integer, Long>>>, Integer, Collection<Entry<String, Long>>> getValuesForYear = (map, year) -> 
   	map.entrySet().stream().map(mapEntry -> {
   		for (Entry<Integer, Long> entry : mapEntry.getValue()) {
   			if (entry.getKey().equals(year)) {
   				return new SimpleEntry<>(mapEntry.getKey(), entry.getValue());
   			} 
   		}
   		return null;
   	}).collect(Collectors.toList());
		
   

   public static BiFunction<Map<String, Collection<Entry<Integer, Long>>>, String, Entry<Integer, Long>> getMinForCountry = (map, country) -> {
   	return map.get(country).stream().sorted(Comparator.comparing(Entry::getValue)).findFirst().get();
   };
}
