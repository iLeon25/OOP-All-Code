package hr.fer.oop;

import java.util.LinkedList;
import java.util.List;

public class HTable<T> {
	private List<TableEntry<List<T>>> list;

	public HTable() {
		list = new LinkedList<>();
	}
	
	public void add(T newElement) {
		int hash = newElement.hashCode();
		
		if (contains(newElement)) 
			throw new DuplicateElementException("Element is already in HTable");
			
		//System.out.println(list);
		for (var v : list) {
			if (v.getFirst() == hash) {
				List<T> pomocna = v.getSecond();
				pomocna.add(newElement);
				v.setSecond(pomocna);
				return;
			} 
		}
		List<T> newInner = new LinkedList<>();
		newInner.add(newElement);
		list.add(new TableEntry<List<T>>(hash, newInner));
	}
	
	public boolean contains(T needle) {
		int hash = needle.hashCode();
		
		for (TableEntry<List<T>> tableEntry : list) {
			if (tableEntry.getFirst() == hash) {
				if (tableEntry.getSecond().contains(needle)) {
					return true;
				}
			}
		}
		return false;
	}	
}
