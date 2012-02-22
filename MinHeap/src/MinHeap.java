import java.util.AbstractQueue;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MinHeap<E> extends AbstractQueue<E> implements Queue<E> {
	public static final int INITIAL_CAPACITY = 20;
	private HeapEntry<E>[] heap;
	private int size;
	private Comparator<E> cmp;
	
	/**
	 * Creates a MinHeap with the default initial capacity (11) 
	 * that orders its elements according to their natural ordering (using Comparable).
	 */
	public MinHeap() {
		heap = createHeapEntryVector(INITIAL_CAPACITY);
		size = 0;
		cmp = null;
	}
	
	/**
	 * Creates a MinHeap with the default initial capacity (11) 
	 * that orders its elements according to the provided comparator
	 * @param comparator the comparator that will be used to order this MinHeap
	 */
	public MinHeap(Comparator<E> comparator) {
		this();
		this.cmp = comparator;
	}
	
	/**
	 * Returns the number of elements currently in the MinHeap
	 */
	public int size() {
		return size;
	}
	
	/*
	 * Public methods below are already documented by the interface Queue
	 */
	
	public boolean offer(E x) {
		insert(x);
		return true;
	}
	
	public E peek() {
		if(isEmpty()){
			return null;
		}
		HeapEntry<E> x = heap[0];
		return x.obj;
	}
	
	public E poll() {
		if(isEmpty()){
			return null;
		}
		HeapEntry<E> top = getAt(0);
		E temp = top.obj;
		delete(top);
		return temp;
	}
	
	/** Adds the specified item to this heap.
		@param x The item to be added to this heap
		@return The HeapEntry object of the inserted item
	*/
	public HeapEntry<E> insert(E x) {
		if(x == null){
			throw new NullPointerException();
		}
		if(cmp == null && !(x instanceof Comparable)){
			throw new ClassCastException();
		}
		if (heap.length == size) {
			increaseCapacity();
		}
		HeapEntry<E> el = new HeapEntry<E>(x, size);
		heap[size++] = el;
		percolateUp(el);
		return el;
	}
	
	/** Changes the value of the specified HeapEntry object to
		newValue if the new value is less than the old value.
		@param he The HeapEntry whose value is to be changed
		@param newValue The new value of the specified HeapEntry object
		@throws IllegalArgumentException if the new value
				is greater than the old value
	*/
	public void decreaseKey(HeapEntry<E> he, E newValue) {
		if (compare(newValue, he.obj) < 0) {
			he.obj = newValue;
			percolateUp(he.pos);
		} else {
			throw new IllegalArgumentException();
		}
	}
	/** Changes the value of the specified HeapEntry object to
		newValue if the new value is greater than the old value.
		@param he The HeapEntry whose value is to be changed
		@param newValue The new value of the specified HeapEntry object
		@throws IllegalArgumentException if the new value
				is less than the old value
	 */
	public void increaseKey(HeapEntry<E> he, E newValue) {
		if (compare(newValue, he.obj) > 0) {
			he.obj = newValue;
			percolateDown(he.pos);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/** Deletes the specified HeapEntry object from this heap. */
	public void delete(HeapEntry<E> he) {
		if(!contains(he)){
			return;
		}
		HeapEntry<E> prevLast = getAt(size-1);
		switchPos(he, prevLast);
		heap[size-1] = null;
		size--;
		HeapEntry<E> parent = getParent(prevLast);
		if (parent == null || compare(prevLast.obj, parent.obj) >= 0) {
			percolateDown(prevLast.pos);
		} else {
			percolateUp(prevLast.pos);
		}
	}
	
	/** Internal auxiliary method to percolate item up the heap.
		@param index the index at which the percolate starts
	*/
	private void percolateUp(int index){
		if(index > (size-1)){
			return; //index out of bounds, silent
		}
		percolateUp(getAt(index));
	}
	
	private void percolateUp(HeapEntry<E> node){
		if(node == null){
			return; //reached top
		}
		HeapEntry<E> parent = getParent(node);
		if(parent == null || compare(parent.obj,node.obj) <= 0){
			return;
		}
		switchPos(parent,node);
		percolateUp(node);
	}
	
	private HeapEntry<E> getParent(HeapEntry<E> node){
		if(node.pos == 0){
			return null;
		}
		return getAt((node.pos - 1)/2);
	}
	
	/** Internal auxiliary method to percolate item down the heap.
		@param index the index at which the percolate starts.
	*/
	private void percolateDown(int index){
		if(index >= size){
			return; //index out of bounds, silent
		}
		HeapEntry<E> root = getAt(index);
		percolateDown(root);
	}
	//recursive method
	private void percolateDown(HeapEntry<E> root){
		if(root == null){
			return;
		}
		HeapEntry<E> minChild = findMinChild(root);
		if(minChild == null){
			return;
		}else if(compare(minChild.obj, root.obj) < 0){
			switchPos(root,minChild);
			percolateDown(root);
		}
	}
	private HeapEntry<E> findMinChild(HeapEntry<E> root){
		HeapEntry<E> left = getAt(2*root.pos+1),
					 right = getAt(2*root.pos+2);
		
		if (right != null && left != null) {
			if(compare(right.obj, left.obj) < 0) {
				return right;
			} else {
				return left;
			}
		}
		return left;
	}
	private void increaseCapacity() {
		HeapEntry<E>[] temp = createHeapEntryVector(size*2);
		for (int i = 0; i < heap.length; i++) {
			temp[i] = heap[i];
		}
		heap = temp;
	}
	private void switchPos(HeapEntry<E> e1, HeapEntry<E> e2){
		int tempPos = e1.pos;
		e1.pos = e2.pos;
		e2.pos = tempPos;
		heap[e1.pos] = e1;
		heap[e2.pos] = e2;
	}
	
	private boolean contains(HeapEntry<E> he){
		for(int i = 0; i < size; i++){
			if(heap[i].equals(he)){
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private HeapEntry<E>[] createHeapEntryVector(int size){
		return (HeapEntry<E>[]) new HeapEntry[size];
	}

	public static class HeapEntry<E>{
		private int pos;
		private E obj;
		private HeapEntry(E obj, int pos){
			this.obj = obj;
			this.pos = pos;
		}
		public E getObj(){
			return obj;
		}
		public int getPos(){
			return pos;
		}
		public String toString(){
			return obj.toString() + " at pos " + pos;
		}
	}
	public Iterator<E> iterator() {
		return new MinHeapIterator();
	}
	private class MinHeapIterator implements Iterator<E>{
		private int pos;
		public MinHeapIterator(){
			pos = 0;
		}
		@Override
		public boolean hasNext() {
			return size > pos;
		}

		@Override
		public E next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			return getAt(pos++).obj;
		}

		@Override
		public void remove() {
			delete(getAt(pos));
		}
	}
	
	private HeapEntry<E> getAt(int index) {
		if(index >= size){
			return null;
		}
		return heap[index];
	}
	
	@SuppressWarnings("unchecked")
	private int compare(E e, E other){
		if(cmp == null){
			Comparable<E> ce = (Comparable<E>)e;
			return ce.compareTo(other);
		} else {
			return cmp.compare(e, other);
		}
	}
}