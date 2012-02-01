package testqueue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import queue.FifoQueue;


public class TestAppendFifoQueue {
	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;
	
	@Before
	public void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		q1 = null;
		q2 = null;
	}
	
	@Test
	public void testTwoEmptyQueuesConcat() {
		q1.append(q2);
		assertTrue("The empty list 1 ended up not beeing empty", q1.isEmpty());
		assertTrue("The empty list 2 ended up not beeing empty", q2.isEmpty());
		assertEquals("The concatinated empty list had an element in first slot", q1.poll(), null);
	}
	
	@Test
	public void testEmptyQueueConcatFullQueue() {
		q2.offer(1);
		q2.offer(2);
		q1.append(q2);
		assertEquals("The concatinated list did not get the correct size", q1.size(), 2);
		assertEquals("The first element was at the wrong place", (int)q1.poll(), 1);
		assertEquals("The second element was at the wrong place", (int)q1.poll(), 2);
	}
	
	@Test
	public void testFullQueueConcatEmptyQueue() {
		q1.offer(1);
		q1.offer(2);
		assertEquals("The concatinated list did not get the correct size", q1.size(), 2);
		assertEquals("The first element was at the wrong place", (int)q1.poll(), 1);
		assertEquals("The second element was at the wrong place", (int)q1.poll(), 2);
	}
	
	@Test
	public void testTwoFullQueuesConcat() {
		q1.offer(1);
		q1.offer(2);
		q2.offer(3);
		q2.offer(4);
		assertEquals("The concatinated list did not get the correct size", q1.size(), 4);
		assertEquals("The first element was at the wrong place", (int)q1.poll(), 1);
		assertEquals("The second element was at the wrong place", (int)q1.poll(), 2);
		assertEquals("The third element was at the wrong place", (int)q1.poll(), 3);
		assertEquals("The forth element was at the wrong place", (int)q1.poll(), 4);
	}
}
