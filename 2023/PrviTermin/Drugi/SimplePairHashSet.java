package hr.fer.oop.pair;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SimplePairHashSet implements Iterable<Pair>{
	
	private Map<Pair, Object> internalMap = new HashMap<>();

	public SimplePairHashSet() {
	}
	
	public Map<Pair, Object> getInternalMap() {
		return internalMap;
	}
	
	public int size () {
		return internalMap.size();
	}
	
	public boolean add (Pair p) {
		if (internalMap.containsKey(p)) 
			return false;
		
		internalMap.put(p, null);
		return true;

	}
	
	public boolean contains(Object o) {
		return internalMap.containsKey(o);
	}

	public boolean remove (Object o) {
		if (!internalMap.containsKey(o))
			return false;
		
		internalMap.remove(o);
		return true;
	}
	@Override
	public Iterator<Pair> iterator() {

		return internalMap.keySet().iterator();
	}
	
}
