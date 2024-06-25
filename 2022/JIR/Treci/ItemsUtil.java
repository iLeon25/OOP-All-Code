package hr.fer.oop.t3;

import java.time.LocalDate; 
import hr.fer.oop.t3.items.Beverage;
import hr.fer.oop.t3.items.Perishable;

public class ItemsUtil {
	public static double totalVolume (Box<? extends Beverage> box) {
		double total = 0;
		for (var v : box.content) {
			total += v.getVolume();
		}

		return total;
	}
	
	
	public static LocalDate useBefore (Box<? extends Perishable> box) {
		if (box.content == null) {
			return null;
		}
		
		Perishable min = null;
		
		for (var v : box.content) {
			if (min == null || v.getBestBefore().isBefore(min.getBestBefore())) {
				min = v;
			}
		}
		
		return min.getBestBefore();
	}
}
