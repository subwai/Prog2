import static org.junit.Assert.*;

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

}
