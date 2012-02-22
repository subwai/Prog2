import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class TestMinHeap {
	MinHeap<Integer> heap;
	
	@Before
	public void setUp(){
		heap = new MinHeap<Integer>();
	}
	
	@Test
	public void testSize() {
		heap.offer(10);
		assertEquals("Incorrect size",1,heap.size());
	}
	@Test
	public void testPosition(){
		heap.offer(10);
		heap.offer(5);
		heap.offer(15);
		heap.offer(17);
		heap.offer(4);
		heap.offer(9);
		assertTrue("peek should return 4",4 == heap.peek());
		this.testPollInteger(new Integer[]{4,5,9,10,15,17});
	}
	@Test
	public void testPeek(){
		assertTrue("peek should return null when empty",heap.peek() == null);
		heap.offer(1);
		assertTrue("peek should return 1",heap.peek() == 1);
	}
	@Test
	public void testInsert(){
		heap.insert(10);
		this.testPollInteger(new Integer[]{10});
	}
	@Test
	public void testDelete(){
		heap.insert(10);
		heap.insert(5);
		MinHeap.HeapEntry<Integer> he = heap.insert(15);
		heap.insert(20);
		heap.insert(9);
		heap.insert(18);
		heap.insert(8);
		heap.delete(he);
		testPollInteger(new Integer[]{5,8,9,10,18,20});
	}
	
	@Test
	public void testDecreaseKey(){
		MinHeap.HeapEntry<Integer> he = heap.insert(10);
		heap.offer(5);
		heap.decreaseKey(he, 2);
		testPollInteger(new Integer[]{2,5});
	}
	
	@Test
	public void testIncreaseKey(){
		MinHeap.HeapEntry<Integer> he = heap.insert(5);
		heap.offer(10);
		heap.increaseKey(he, 20);
		testPollInteger(new Integer[]{10,20});
	}
	
	@Test
	public void testComparator(){
		MinHeap<DummyInteger>heap2 = 
				new MinHeap<DummyInteger>(new DummyIntegerComparator());
		
		DummyInteger min = new DummyInteger(5);
		heap2.offer(new DummyInteger(10));
		heap2.offer(min);
		heap2.offer(new DummyInteger(15));
		assertTrue("Min element should be 5", heap2.poll() == min);

	}
	@Test
	public void testNotComparable(){
		try{
			MinHeap<DummyInteger>heap2 = 
					new MinHeap<DummyInteger>();
			heap2.offer(new DummyInteger(10));
			heap2.offer(new DummyInteger(20));
			
			fail("Should have thrown exception");
		}catch(ClassCastException ex){/*should get here*/}
	}
	
	private class DummyIntegerComparator implements Comparator<DummyInteger>{
		@Override
		public int compare(DummyInteger arg0, DummyInteger arg1) {
			return (arg0).getVal() - (arg1).getVal();
		}
		
	}
	private class DummyInteger{
		private int i;
		public DummyInteger(int i){
			this.i = i;
		}
		public int getVal(){
			return i;
		}
	}
	@Test
	public void testPreferComparator(){
		heap = new MinHeap<Integer>(new ReverseIntegerComparator());
		Integer max = new Integer(20);
		heap.offer(10);
		heap.offer(max);
		heap.offer(15);
		assertTrue("Should have used comparator, not compareTo", 
				heap.poll() == max);

	}
	//verify that comparator is being used by reversing sort.
	private class ReverseIntegerComparator implements Comparator<Integer>{
		@Override
		public int compare(Integer arg0, Integer arg1) {
			return (arg1).intValue() - (arg0).intValue();
		}
		
	}
	@Test
	public void testIterator(){
		Iterator<Integer> itr = heap.iterator();
		assertFalse("empty heap should not have next",itr.hasNext());
		heap.offer(10);
		heap.offer(10);
		heap.offer(10);
		itr = heap.iterator();
		assertTrue("heap should not be empty",itr.hasNext());
		int times = 0;
		for(Integer i: heap){
			times++;
		}
		assertTrue("Should have 3 elements",times == 3);
		
	}
	
	private void testPollInteger(Integer[] input_arr){
		List<Integer> a = new ArrayList<Integer>();
		Collections.addAll(a, input_arr);
		
		//find min
		int min = (Integer)Collections.min(a);
		
		while(!heap.isEmpty()){
			Integer curr = heap.poll();
			a.remove(curr); //remove first occurance
			assertTrue("Wrong poll order",min <= curr);
			min = curr;
		}
		assertEquals("Did not poll all items",0,a.size());
	}
	
	@Test
	public void testInternalOrder(){
		heap.offer(5);
		heap.offer(4);
		heap.offer(10);
		heap.offer(11);
		heap.offer(9);
		heap.offer(7);
		heap.poll();
		List<Integer> heap_arr = new ArrayList<Integer>();
		for(Integer val: heap){
			heap_arr.add(val);
		}
		recursive_check(heap_arr,0);
		testPollInteger(new Integer[]{5,7,9,10,11});
	}
	
	private void recursive_check(List<Integer> heap_arr, int i){
		if(i >= heap_arr.size()){
			return;
		}
		Integer parent = heap_arr.get((i - 1)/2);
		Integer child = heap_arr.get(i);
		assertFalse("Parent should not be higher than child",parent > child);
		recursive_check(heap_arr,2*i+1);
		recursive_check(heap_arr,2*i+2);
	}
	
	@Test
	public void testInsertNull(){
		try{
			heap.offer(null);
			fail("should throw NullPointerException");
		}catch (NullPointerException ex){}
	}
	
	@Test
	public void testDoubleDeleteHeapEntry(){
		MinHeap.HeapEntry<Integer> he = heap.insert(10);
		heap.offer(20);
		heap.delete(he);
		heap.delete(he);
		assertTrue("qwe",20 == heap.peek());
		assertEquals("qwe",1,heap.size());
		
	}
}
