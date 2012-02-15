import static org.junit.Assert.*;

import java.util.Comparator;

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
		assertTrue("Min element is not at top",heap.peek() == 4);
		assertTrue("Element 4 should have ben polled",heap.poll() == 4);
		assertTrue("Element 5 should have ben polled",heap.poll() == 5);
		assertTrue("Element 9 should have ben polled",heap.poll() == 9);
		assertTrue("Element 10 should have ben polled",heap.poll() == 10);
		assertTrue("Element 15 should have ben polled",heap.poll() == 15);
		assertTrue("Element 17 should have ben polled",heap.poll() == 17);
	}
	@Test
	public void testInsert(){
		heap.insert(10);
		assertEquals("Incorrect size",1,heap.size());
	}
	
	@Test
	public void testDecreaseKey(){
		MinHeap.HeapEntry<Integer> he = heap.insert(10);
		heap.offer(5);
		heap.decreaseKey(he, 2);
		assertTrue("Min element should be decreased heap entry", heap.poll() == 2);
		assertEquals("Size should be one, is " + heap.size(),heap.size(),1);
	}
	
	@Test
	public void testIncreaseKey(){
		MinHeap.HeapEntry<Integer> he = heap.insert(5);
		heap.offer(10);
		heap.increaseKey(he, 20);
		assertTrue("Min element should be 10", heap.poll() == 10);
		assertEquals("Size should be one, is " + heap.size(),heap.size(),1);
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

}
