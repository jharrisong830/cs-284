import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TestSort {

	@Test
	void test1() { //tests an unsorted array of integers
		Integer[] array=new Integer[]{99, 77, 88, 44, 11, 0, 22, 66, 33, 55};
		Sort.sort(array);
		assertEquals(Arrays.toString(array), "[0, 11, 22, 33, 44, 55, 66, 77, 88, 99]");
	}
	
	@Test
	void test2() { //tests an unsorted array of characters
		Character[] array=new Character[]{'z', 'a', 'x', 'c', 'y', 'b'};
		Sort.sort(array);
		assertTrue(Arrays.toString(array).equals("[a, b, c, x, y, z]"));
	}
	
	@Test
	void test3() { //tests if the sort still works with duplicate values
		Integer[] array=new Integer[]{4, 3, 2, 2, 5, 1};
		Sort.sort(array);
		assertEquals(Arrays.toString(array), "[1, 2, 2, 3, 4, 5]");
	}
	
	@Test
	void test4() { //tests sort with only 0, 1, 2, and 3 values
		Integer[] array0=new Integer[]{};
		Sort.sort(array0);
		assertTrue(Arrays.toString(array0).equals("[]"));
		
		Integer[] array1=new Integer[]{0};
		Sort.sort(array1);
		assertEquals(Arrays.toString(array1), "[0]");
		
		Integer[] array2=new Integer[]{2, 1};
		Sort.sort(array2);
		assertTrue(Arrays.toString(array2).equals("[1, 2]"));
		
		Integer[] array3=new Integer[]{3, 5, 4};
		Sort.sort(array3);
		assertEquals(Arrays.toString(array3), "[3, 4, 5]");
	}
	
	@Test
	void test5() { //tests than an error is thrown if an array is null
		boolean errorThrow=false;
		try {
			Integer[] array=null;
			Sort.sort(array);
		}
		catch(IllegalArgumentException e) {
			errorThrow=true;
		}
		assertTrue(errorThrow);
	}
	
}