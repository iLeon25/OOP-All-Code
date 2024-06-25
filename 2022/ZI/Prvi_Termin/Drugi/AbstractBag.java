package hr.fer.oop.bag;

public abstract class AbstractBag<T> implements Bag<T> {

    /**
     * Compares the specified object with this bag for equality. Returns
     * {@code true} if the given object is also a bag and the two bags contain
     * the same elements.
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        Bag<T> castedObj;
        
        try {
        	castedObj = (Bag<T>) obj;
        } catch (ClassCastException e) {
        	return false;
		}
        
        if (castedObj.size() != this.size()) {
        	return false;
        }
        
        for (T t : this.toCollection()) {
        	if (this.frequency(t) != castedObj.frequency(t)) 
        		return false;
        }
        return true;
        
    }

    /**
     * Returns the hash code value for this bag. Two bags that contain the same
     * group of elements (irrespective of their order) should return the same
     * hash code.
     *
     * @return the hash code value for this bag
     */
    @Override
    public int hashCode() {
        int sum = 0;
        for(T el: this.toCollection()){
            if(el == null)
                sum += 1;
            else
                sum += el.hashCode();
        }
        return sum;
    }
}
