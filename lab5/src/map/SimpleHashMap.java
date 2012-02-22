package map;

public class SimpleHashMap<K,V> implements Map<K,V> {
	
	public static final int INITIAL_CAPACITY = 16;
	public static final double INITIAL_LOAD_FACTOR = 0.75;
	private Entry<K,V>[] table;
	private int size;
	
	/** Constructs an empty hashmap with the default initial capacity (16)
	 *  and the default load factor (0.75). */
	public SimpleHashMap() {
		table = (Entry<K,V>[]) new Entry[INITIAL_CAPACITY];
		size = 0;
	}
	
	/** Constructs an empty hashmap with the specified initial capacity
	 *  and the default load factor (0.75). */
	public SimpleHashMap(int capacity) {
		table = (Entry<K,V>[]) new Entry[capacity];
	}
	
	@Override
	public V get(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K arg0, V arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}
	
	private static class Entry<K,V> implements Map.Entry<K,V> {
		
		private K key;
		private V value;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public V setValue(V value) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public String toString() {
			return key.toString() + "=" + value.toString();
		}
		
	}

}
