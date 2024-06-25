package hr.fer.oop.triple;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Triple implements Comparable<Triple>{
	private String first, second, third;

	public Triple(String first, String second, String third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	public String getFirst() {
		return first;
	}
	
	public String getSecond() {
		return second;
	}
	
	public String getThird() {
		return third;
	}

	@Override
	public int compareTo(Triple o) {
		List<String> local = new LinkedList<>();
		List<String> imports = new LinkedList<>();
		
		local.add(first);
		local.add(second);
		local.add(third);
		imports.add(o.getFirst());
		imports.add(o.getSecond());
		imports.add(o.getThird());
		
		Collections.sort(local);
		Collections.sort(imports);
		
		if (local.get(0).equals(imports.get(0))) {
			if (local.get(1).equals(imports.get(1))) {
				return local.get(2).compareTo(imports.get(2));
				
			} else {
				return local.get(1).compareTo(imports.get(1));
			}
		} else {
			return local.get(0).compareTo(imports.get(0));
		}
	}
}
