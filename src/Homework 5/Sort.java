//John Graham
//I pledge my honor that I have abided by the Stevens Honor System.
import java.util.HashSet;
import java.util.Arrays;

public class Sort {

	private static class Interval {
		//data fields
		private int lower;
		private int upper;
		
		//constructor
		public Interval(int lower, int upper) {
			this.lower=lower;
			this.upper=upper;
		}
		
		public int getLower() {
			return lower;
		}

		public int getUpper() {
			return upper;
		}

		public boolean equals(Object o) {
			if(((Interval)o).lower==this.lower && ((Interval)o).upper==this.upper) {
				return true;
			}
			else {
				return false;
			}
		}

		public int hashCode() {
			return lower*lower+upper;
		}
	}

	/**Sorts an array in an increasing order.
	 * @param array, the array to be sorted*/
	public static <T extends Comparable <T>> void sort(T[] array) {
		if(array==null) {
			throw new IllegalArgumentException("sort: input array cannot be null");
		}
		HashSet<Interval> hashSet=new HashSet<Interval>();
		hashSet.add(new Interval(0, array.length-1));
		while(!hashSet.isEmpty()) {
			Interval sortInterval=hashSet.iterator().next();
			hashSet.remove(sortInterval);
			int[] medianOfThree=new int[]{sortInterval.getLower(), (sortInterval.getUpper()+sortInterval.getLower())/2, sortInterval.getUpper()};
			if(sortInterval.getUpper()-sortInterval.getLower()==1 && array[sortInterval.getUpper()].compareTo(array[sortInterval.getLower()])<1) {
				swap(array, sortInterval.getLower(), sortInterval.getUpper());
			}
			else if(sortInterval.getUpper()-sortInterval.getLower()>1) {
				bubble(array, medianOfThree);
				int part=partition(array, sortInterval.getLower(), sortInterval.getUpper());
				hashSet.add(new Interval(sortInterval.getLower(), part-1));
				hashSet.add(new Interval(part+1, sortInterval.getUpper()));
			}
		}


	}
	
	/**Performs a bubble sort (used for the median-of-three process). (taken from lecture slides)
	 * @param table, the array
	 * @param medianOfThree, an array of the three indexes of the array that we want to find the median of*/
	public static <T extends Comparable<T>> void bubble(T[] table, int[] medianOfThree) { //bubble sort code from slides
		int pass = 1;
		boolean exchanges = false;
		do {
			// Invariant: Elements after table.length-pass+1
			// are in place.
			exchanges = false;
			// Compare each pair of adjacent elements.
			for (int i = 0; i < medianOfThree.length - pass; i++) {
				int currentIndex=medianOfThree[i];
				int nextIndex=medianOfThree[i+1];
				if (table[currentIndex].compareTo(table[nextIndex]) > 0) {
					// Exchange pair.
					T temp = table[currentIndex];
					table[currentIndex] = table[nextIndex];
					table[nextIndex] = temp;
					exchanges = true;
				}
			}
			pass++;
		} while (exchanges);
		swap(table, medianOfThree[0], medianOfThree[1]);
	}
	
	/**Performs a partition of an array so that sort can be performed on smaller subarrays. (taken from lecture slides)
	 * @param table, the array
	 * @param first, the lower bound index
	 * @param last, the upper bound index
	 * @return int, the index at which the partition should occur*/
	private static <T extends Comparable<T>> int partition(T[] table, int first, int last) { //partition code from slides
		T pivot = table[first];
		int up = first;
		int down = last;
		do {
			while ((up<last) && (pivot.compareTo(table[up])>=0)) {
				up++; }
			while (pivot.compareTo(table[down]) < 0) {
				down--;
			}
			if (up < down) { // if up is to the left of down.
				swap(table, up, down);
			}
		} while (up < down); // Repeat while up is left of down.
		swap(table, first, down);
		return down;
	}
	
	/**Swaps two items in an array given that the item at the first index is greater than the item at the last index.
	 * @param table, the array
	 * @param first, the index of the greater item that comes first in the array 
	 * @param last, the index of the lesser item that comes last in the array*/
	private static <T extends Comparable <T>> void swap(T[] table, int first, int last) {
		T temp=table[first];
		table[first]=table[last];
		table[last]=temp;
	}
	
	
	
	
	public static void main(String[] args) {
		//Integer[] a = {3,2,1};
		//Integer[] a = {9,2,5,6,7,4,3,8,1};
		//Integer[] a = {10,9,8,7,6,5,4,3,2,1};
		//Integer[] a = {2,5,3,0,2,3,0,3};
		//Integer[] a = {3,4,7,1,8,5,2,9,0,6};
		//Integer[] a  = {3,4,7,1,5,8,2,9,0,6};
		Integer[] a = {5,4,7,1,8,3,2,9,0,6};

		System.out.println("Original: ");
		for (int i=0; i<a.length; i++) {
			System.out.print(a[i] + " ");
		}        
		sort(a);
		System.out.println("\nSorted: ");
		for (int i=0; i<a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
	
}
