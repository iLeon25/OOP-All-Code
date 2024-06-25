package hr.fer.oop.triple;

import java.util.Iterator; 
import java.util.Map;
import java.util.TreeMap;

class SimpleTripleTreeSet implements Iterable<Triple>{
    private Map<Triple, Object> internalMap;
    
    public SimpleTripleTreeSet() {
		internalMap = new TreeMap<Triple, Object>();
	}


	public Map<Triple, Object> getInternalMap() {
        return internalMap;
    }
    
	public boolean add (Triple t) {
		if (internalMap.containsKey(t)) 
			return false;
		
		internalMap.put(t, null);
		
		return true;
	}
	
	public int size () {
		return internalMap.size();
	}
	
	public boolean contains (Object o) {
		return internalMap.containsKey(o);
	}
	
	public boolean remove (Object o) {
		if (contains(o)) {
			internalMap.remove(o);
			return true;
		}
		return false;
	}
    
	@Override
	public Iterator<Triple> iterator() {
		return internalMap.keySet().iterator();
	}
}
