import java.util.AbstractQueue;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Queue;

public class MinHeap<E> extends AbstractQueue<E> implements Queue<E> {
	public MinHeap() {
		
	}
	public MinHeap(Comparator<E> cmp) {
		
	}
	public int size() {
		return 0;
	}
	public boolean isEmpty() {
		return false;
	}
	public boolean offer(E x) {
		return false;
	}
	public E peek() {
		return null;
	}
	public E poll() {
		return null;
	}
	public Iterator<E> iterator() {
		return null;
	}
	public HeapEntry<E> insert(E x) {
		return null;
	}
	public void decreaseKey(HeapEntry<E> e, E newValue) {
		
	}
	public void increaseKey(HeapEntry<E> e, E newValue) {
		
	}
	public void delete(HeapEntry<E> e) {
		
	}

	private static class HeapEntry<E>{

	}
	/* Internal auxiliary method to percolate item up the heap.
	@param index the index at which the percolate starts
	*/
	private void percolateUp(int index){
		
	}
	/* Internal auxiliary method to percolate item down the heap.
	@param index the index at which the percolate starts.
	*/
	private void percolateDown(int index){
		
	}
}