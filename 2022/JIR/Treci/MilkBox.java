package hr.fer.oop.t3;


import java.util.List;

import hr.fer.oop.t3.items.Milk;

public class MilkBox extends Box<Milk>{
	private Milk firstMilk;

	public MilkBox(Milk firstMilk) {
		super(List.of(firstMilk));
		this.firstMilk = firstMilk;
	}
	
	public boolean add (Milk m) {
		if (m.getType() == firstMilk.getType()) {
			content.add(m);
			return true;
		}
		return false;
	}
}
