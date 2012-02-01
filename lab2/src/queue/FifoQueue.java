package queue;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> 
implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {

	}

	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	x the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E x) {
		if(x == null){
			throw new NullPointerException();
		}
		QueueNode<E> node = new QueueNode<E>(x);
		if(isEmpty()){
			last = node;
		}
		node.next = last.next;
		last.next = node;
		last = node;
		size++;
		return true;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if (isEmpty()) {
			return null;
		}
		E e = last.next.element;
		last.next = size == 1 ? null : last.next.next;
		size--;
		return e;
	}

	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(isEmpty()){
			return null;
		}
		return last.next.element;
	}


	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}
	
	public void append(FifoQueue<E> q) {
		if(q.equals(this)){
			throw new UnsupportedOperationException();
		}
		if(q.isEmpty()){
			return; //do nothing
		}
		if(this.isEmpty()){
			last = q.last;
		}else{
			QueueNode<E> oldLast = last,
			thisFirst = last.next,
					qFirst = q.last.next;
			
			//this.last = q.last
			last = q.last;
			
			//new last set first
			last.next = thisFirst;
			
			oldLast.next = qFirst;
		}
		size += q.size;
		q.size = 0;
		q.last = null;
	}
	
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		
		/* Konstruktor */
		private QueueIterator() {
			//pos = null;
			if(!isEmpty()){
				pos = last.next;
			}
		}
		
		public boolean hasNext() {
			return  pos != null;
		}
		
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E e = pos.element;
			pos = (last == pos) ? null : pos.next;
			return e;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
