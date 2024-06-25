package hr.fer.oop.bag;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionBag<T> extends AbstractBag<T> {

    final Collection<T> collection = new ArrayList<>();

    @Override
    public void add(T e) {
       collection.add(e);
    }

    @Override
    public void remove(T e) {
        collection.remove(e);
    }

    @Override
    public Collection<T> toCollection() {
        return collection;
    }

    @Override
    public int frequency(Object o) {
       return (int)collection.stream()
    		   .filter((a) -> {
    			   if (a == null) {
    				   return o == null;
    			   }
    			   return a.equals(o);
    		   }).count();
    }

    @Override
    public int size() {
        return collection.size();
    }        
}
