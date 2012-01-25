package set;

public class UniqueElements {

	public static int[] uniqueElements(int[] ints) {
		MaxSet<Integer> set = new MaxSet<Integer>();
		for (int i : ints) {
			set.add(i);
		}
		int[] sorted = new int[set.size()];
		for (int i = sorted.length - 1; i >= 0; i--) {
			Integer x = set.getMax();
			sorted[i] = x;
			set.remove(x);
		}
		return sorted;
	}
}
