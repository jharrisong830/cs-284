import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AnagramsTest {

	@Test
	void testAddWord() {
		Anagrams a1=new Anagrams();
		a1.addWord("b");
		assertTrue(a1.anagramTable.containsKey((long)3));
		
		ArrayList<String> list1=new ArrayList<String>();
		list1.add("race");
		list1.add("care");
		a1.addWord("race");
		a1.addWord("care");
		assertTrue(a1.anagramTable.containsKey((long)6710));
		assertTrue(a1.anagramTable.get((long)6710).containsAll(list1));
	}
	
	@Test
	void testMaxEntries() {
		Anagrams a2=new Anagrams();
		ArrayList<String> list2=new ArrayList<String>();
		
		list2.add("post");
		list2.add("spot");
		list2.add("stop");
		list2.add("pots");
		list2.add("tops");
		list2.add("lemon");
		list2.add("melon");
		for(String i:list2) {
			a2.addWord(i);
		}
		
		assertTrue(a2.anagramTable.containsKey((long)11849687));
		assertTrue(a2.anagramTable.containsKey((long)33724427));
		assertTrue(a2.anagramTable.entrySet().contains(a2.getMaxEntries().get(0)));
		assertEquals(a2.getMaxEntries().toString(), "[11849687=[post, spot, stop, pots, tops]]");
	}

}
