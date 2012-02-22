package map;

public class SimpleHashMap<K,V> implements Map<K,V> {
	
	public static final int INITIAL_CAPACITY = 16;
	private Entry<K,V>[] table;
	
	public SimpleHashMap() {
		table = (Entry<K,V>[]) new Entry[INITIAL_CAPACITY];
	}
	
	@Override
	public V get(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return 0;
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
