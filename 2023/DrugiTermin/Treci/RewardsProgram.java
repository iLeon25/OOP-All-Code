package hr.fer.oop;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RewardsProgram {
	private List<Consumer> consumers = new LinkedList<>();
	
	public void addConsumer (String fn, String ln, int rp, int my) {
		consumers.add(new Consumer(fn, ln, rp, my));
	}
	
	public List<Consumer> getConsumers() {
		return consumers;
	}
	
	public void removeConsumers (List<String> removalNames) {
		Iterator it = consumers.iterator();
		
		try {
		while (it.hasNext()) {
			for (String string : removalNames) {
				if (it.next().equals(string)) {
					it.remove();
				}
			}
		}
		
		}catch (Exception e) {
			//System.out.println("oyy laddie");
		}
	}
	
	public void rankConsumers() {
		
		class Comparer implements Comparator<Consumer> {
			
			Comparator<Consumer> byRewardPoints = (a, b) -> {
				return (b.getRewardPoints() - a.getRewardPoints());
			};
			
			Comparator<Consumer> byMembershipYears = (a, b) -> {
				return (b.getMembershipYears() - a.getMembershipYears());
			};
			
			Comparator<Consumer> byLastName = (a, b) -> {
				return (a.getLastName().compareTo(b.getLastName()));
			};
			
			
			@Override
			public int compare(Consumer o1, Consumer o2) {
				
				return byRewardPoints.thenComparing(byMembershipYears)
						.thenComparing(byLastName).compare(o1, o2);
			}
		}
		Collections.sort(consumers, new Comparer());
	}
}
