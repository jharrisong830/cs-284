import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TreapTest {

	@Test
	void testAdd() {
		Treap<Integer> t1=new Treap<Integer>();
		
		//tests that a node can be added and a random priority can be generated
		assertTrue(t1.add(99));
		t1.delete(99);
		
		//tests that a node can be added
		assertTrue(t1.add (4 ,19));
		assertTrue(t1.add (2 ,31));
		assertTrue(t1.add (6 ,70));
		assertTrue(t1.add (1 ,84));
		assertTrue(t1.add (3 ,12));
		assertTrue(t1.add (5 ,83));
		assertTrue(t1.add (7 ,26));
		
		//tests that add returns false when a duplicate key is attempting to be added
		assertFalse(t1.add(4,23));
		assertFalse(t1.add(5,24));
		assertFalse(t1.add(1,25));
		
		//tests that add returns false when a node with a duplicate priority is attempting to be added
		assertFalse(t1.add(92,19));
		assertFalse(t1.add(91,31));
		assertFalse(t1.add(90,70));
		
		//tests that an exception is thrown when the given key is null
		boolean errorThrow=false;
		try {
			t1.add(null);
		}
		catch(IllegalArgumentException e) {
			errorThrow=true;
		}
		assertTrue(errorThrow);
		
		//tests that the resulting treap matches what it is supposed to be
		String theString="(key=1, priority=84)\n"
						+"-null\n"
						+"-(key=5, priority=83)\n"
						+"--(key=2, priority=31)\n"
						+"---null\n"
						+"---(key=4, priority=19)\n"
						+"----(key=3, priority=12)\n"
						+"-----null\n"
						+"-----null\n"
						+"----null\n"
						+"--(key=6, priority=70)\n"
						+"---null\n"
						+"---(key=7, priority=26)\n"
						+"----null\n"
						+"----null\n";
		
		assertEquals(theString, t1.toString());
	}
	
	
	@Test
	void testDelete() {
		Treap<Character> t2=new Treap<Character>();
		t2.add('z',47);
		t2.add('w',32);
		t2.add('v',21);
		t2.add('x',25);
		t2.add('p',99);
		t2.add('u',75);
		t2.add('r',40);
		
		//tests that the resulting treap matches what it is supposed to be
		String addString="(key=p, priority=99)\n"
				+"-null\n"
				+"-(key=u, priority=75)\n"
				+"--(key=r, priority=40)\n"
				+"---null\n"
				+"---null\n"
				+"--(key=z, priority=47)\n"
				+"---(key=w, priority=32)\n"
				+"----(key=v, priority=21)\n"
				+"-----null\n"
				+"-----null\n"
				+"----(key=x, priority=25)\n"
				+"-----null\n"
				+"-----null\n"
				+"---null\n";
		
		assertEquals(addString, t2.toString());
		
		//tests that a node can be deleted
		assertTrue(t2.delete('z'));
		
		//tests that delete returns false when a duplicate key is attempting to be deleted
		assertFalse(t2.delete('a'));
		
		//tests that the resulting treap matches what it is supposed to be
		String deleteString="(key=p, priority=99)\n"
				+ "-null\n"
				+ "-(key=u, priority=75)\n"
				+ "--(key=r, priority=40)\n"
				+ "---null\n"
				+ "---null\n"
				+ "--(key=w, priority=32)\n"
				+ "---(key=v, priority=21)\n"
				+ "----null\n"
				+ "----null\n"
				+ "---(key=x, priority=25)\n"
				+ "----null\n"
				+ "----null\n";
		
		assertEquals(deleteString, t2.toString());
		
		//tests that a root node can be deleted
		assertTrue(t2.delete('p'));
		
		//tests that a node can be deleted and then readded (the array list is removing/adding/keeping track of priorities as expected
		assertTrue(t2.delete('x'));
		assertTrue(t2.add('x',25));
		
		//tests that an exception is thrown when the given key is null
		boolean errorThrow=false;
		try {
			t2.delete(null);
		}
		catch(IllegalArgumentException e) {
			errorThrow=true;
		}
		assertTrue(errorThrow);
	}
	
	
	@Test
	void testFind() {
		Treap<Integer> t3=new Treap<Integer>();
		t3.add (4 ,19);
		t3.add (2 ,31);
		t3.add (6 ,70);
		t3.add (1 ,84);
		t3.add (3 ,12);
		t3.add (5 ,83);
		t3.add (7 ,26);
		
		//tests that find returns true when given key is in the treap
		assertTrue(t3.find(4));
		assertTrue(t3.find(2));
		assertTrue(t3.find(6));
		assertTrue(t3.find(1));
		assertTrue(t3.find(3));
		assertTrue(t3.find(5));
		assertTrue(t3.find(7));
		
		//tests that find returns false when given key is not in the treap
		assertFalse(t3.find(23));
		assertFalse(t3.find(24));
		assertFalse(t3.find(25));
		
		//tests that an exception is thrown when the given key is null
		boolean errorThrow=false;
		try {
			t3.find(null);
		}
		catch(IllegalArgumentException e) {
			errorThrow=true;
		}
		assertTrue(errorThrow);
	}

}
