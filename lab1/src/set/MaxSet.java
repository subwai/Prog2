package set;

import java.util.NoSuchElementException;
import java.util.Iterator;


public class MaxSet<E extends Comparable<E>> extends ArraySet<E> {
	private E maxElement;
	
	/**
	 * Constructs a new empty set.
	 */
	public MaxSet() {
		super();
	}
	
	/** Returns the currently largest element in this set. 
	pre: the set is not empty 
	post: the set is unchanged 
	@return the currently largest element in this set 
	@throws NoSuchElementException if this set is empty 
	*/ 
	public E getMax() {
		if (maxElement == null) {
			throw new NoSuchElementException();
		}
		return maxElement;
	}
	
	/** 
	 * Adds the specified element to this set, if it is not already present.
	 * post: x is added to the set if it is not already present
	 * @param  x the element to be added
	 * @return true if the specified element was added
	 */
	public boolean add(E x) {
		boolean returnValue = super.add(x);
		if(returnValue){
			if (maxElement == null || maxElement.compareTo(x) < 0) {
				maxElement = x;
			}
		}
		return returnValue;
	}
	
	/** 
	 * Removes the specified element from this set if it is present. 
	 * post: 	x is removed if it was present
	 * @param 	x the element to remove - if present
	 * @return true if the set contained the specified element
	 */
	public boolean remove(Object x) {
		boolean returnValue = super.remove(x);
		if(returnValue && maxElement.equals(x)){
			maxElement = null;
			
			Iterator<E> itr = super.iterator();
			while(itr.hasNext()){
				E el = itr.next();
				if(maxElement == null || maxElement.compareTo(el) < 0){
					maxElement = el;
				}
			}
		}
		return returnValue;
	}	
}