package map;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<Integer, String>();
		
		java.util.Random random = new java.util.Random(123456);
		for (int i = 0; i < 24; i++) {
			int r = random.nextInt(10000);			
			map.put(r, "");
		}
	}
}
