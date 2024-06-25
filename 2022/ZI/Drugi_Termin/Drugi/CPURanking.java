package hr.fer.oop.zi.zad3;

import java.util.Comparator;


public class CPURanking {
	
	public static Comparator<CPU> hasHyperthreadingFirst () {
		return new Comparator<CPU>() {
			
			@Override
			public int compare(CPU o1, CPU o2) {
				int r = 0;
				
				if (o1.hasHyperthreading() && !o2.hasHyperthreading()) {
					r = -1;
					
				} else if (!o1.hasHyperthreading() && o2.hasHyperthreading()) {
					r = 1;
				}
				
				return r;
			}
		};
	}
	
	public static Comparator<CPU> withinBudget (int budget) {
		return new Comparator<CPU>() {
			
			@Override
			public int compare(CPU o1, CPU o2) {
				int r = 0;
				
				if (o1.getCost() < budget && o2.getCost() > budget) {
					r = -1;
					
				} else if (o1.getCost() > budget && o2.getCost() < budget) {
					r = 1;
				}
				return r;
			}
		};
	}
	
	public static Comparator<CPU> byPotentialSpeed () {
		Comparator<CPU> byClockSpeed = (a, b) -> Double.compare(b.getClockSpeed(), a.getClockSpeed());
		
		Comparator<CPU> byNoOfCores = (a, b) -> Integer.compare(b.getCores(), a.getCores());
		
		return (a, b) -> byClockSpeed.thenComparing(byNoOfCores).thenComparing(hasHyperthreadingFirst()).compare(a, b);
	}
		
	public static Comparator<CPU> byCostEfficiency() {
		return new Comparator<CPU>() {
			
			@Override
			public int compare(CPU o1, CPU o2) {
				double efficiency1, efficiency2;
				efficiency1 = o1.getCost() / (o1.getClockSpeed() * o1.getCores());
				efficiency2 = o2.getCost() / (o2.getClockSpeed() * o2.getCores());
				
				int r = 0;
				
				r = Double.compare(efficiency1, efficiency2);
				
				if (r > 0) {
					r = 1;
					
				} else if (r < 0) {
					r = -1;
				}
				return r;
			}
		};
	}
	
	public static Comparator<CPU> byAffordabilityAndEfficiency(int budget) {
		return (a, b) -> withinBudget(budget).thenComparing(byCostEfficiency())
											 .thenComparing(byPotentialSpeed())
											 .thenComparing(hasHyperthreadingFirst())
											 .compare(a, b);
	}

}
