package hr.fer.oop.zi.z1;

import java.util.Collections; 
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class BillionaireStats {

    // TODO: ovdje dodati potrebne materijale

	public static int countCountry(List<BillionaireDatum> dst, String country) { 
		return (int) dst.stream().filter((a) -> a.getCountry().equals(country)).count();
	
	}
	
	public static int countAgeRange(List<BillionaireDatum> dst, int lower, int upper) { 
		return (int) dst.stream().filter((bill) -> bill.getAge() >= lower && bill.getAge() <= upper).count();
	}
	
	public static Map<Integer, Long> makeHistogram(List<BillionaireDatum> dst, int binWidth) {
		dst.sort((a, b) -> b.getWorth().compareTo(a.getWorth()));
		int noOfComparments = (dst.get(0).getWorth() - dst.get(dst.size() - 1).getWorth()) / binWidth;
		
		Map<Integer, Long> outputMap = new LinkedHashMap<>();
		long counter = 0;
		
		for (int i = 0; i < noOfComparments; i++) {
			counter = 0;
			for (BillionaireDatum billionaireDatum : dst) {
				if (((billionaireDatum.getWorth() >= (dst.get(0).getWorth() + i * binWidth)) && 
						((billionaireDatum.getWorth() < (dst.get(0).getWorth() + ((i + 1) * binWidth)))))) {
					counter++;
				}
			}
			outputMap.put(dst.get(0).getWorth() + i * binWidth, counter);
		}
		
		return outputMap;
	}
	
}
