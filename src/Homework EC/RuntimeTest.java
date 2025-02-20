import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

class RuntimeTest {

	@Test
	void testEg1() {
		Runtime r = new Runtime();
		r.readFromFile("eg1.pgm");
		r.run();
		assertEquals(r.toString(), "Pgm   : [push 5.0, push 3.4567, add, pop m0, exit]\n"
								 + "Pc    : 6\n"
								 + "Stack : []\n"
								 + "Memory: [8.4567, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]\n"
								 + "------------------------------------------------\n");
	}
	
	@Test
	void testEg2() {
		Runtime r = new Runtime();
		r.readFromFile("eg2.pgm");
		r.run();
		assertEquals(r.toString(), "Pgm   : [push 5.0, pop m0, push m0, push m0, label l2, dec, jmpz done, pop m0, push m0, mul, push m0, jmp l2, label done, pop m0, exit]\n"
								 + "Pc    : 16\n"
								 + "Stack : [120.0]\n"
								 + "Memory: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]\n"
								 + "------------------------------------------------\n");
	}
	
	@Test
	void testEg3() {
		Runtime r = new Runtime();
		r.readFromFile("eg3.pgm");
		boolean errorThrow=false;
		try {
			r.run();
		}
		catch(EmptyStackException e) {
			errorThrow=true;
		}
		assertTrue(errorThrow);
	}
	
	@Test
	void testEg4() {
		Runtime r = new Runtime();
		r.readFromFile("eg4.pgm");
		r.run();
		assertEquals(r.toString(), "Pgm   : [push 5.0, pop m0, exit]\n"
								 + "Pc    : 4\n"
								 + "Stack : []\n"
								 + "Memory: [5.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]\n"
								 + "------------------------------------------------\n");
	}
	
	@Test
	void testPushPop() {
		Runtime r = new Runtime();
		r.readFromFile("testPushPop.txt");
		r.run();
		assertEquals(r.toString(), "Pgm   : [push 6.9, pop m0, push m0, push 2.0, pop m1, pop m2, push 2.84, exit]\n"
								 + "Pc    : 9\n"
								 + "Stack : [2.84]\n"
								 + "Memory: [6.9, 2.0, 6.9, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]\n"
								 + "------------------------------------------------\n");
	}
	
	@Test
	void testOperations() {
		Runtime r = new Runtime();
		r.readFromFile("testOperations.txt");
		r.run();
		assertEquals(r.toString(), "Pgm   : [push 1.0, push 2.0, push 3.0, push 4.0, push 5.0, push 6.0, push 7.0, push 8.0, add, pop m0, sub, pop m1, mul, pop m2, div, pop m3, exit]\n"
								 + "Pc    : 18\n"
								 + "Stack : []\n"
								 + "Memory: [15.0, 1.0, 12.0, 2.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]\n"
								 + "------------------------------------------------\n");
	}
	
	@Test
	void testMisc() {
		Runtime r = new Runtime();
		r.readFromFile("testMisc.txt");
		r.run();
		assertEquals(r.toString(), "Pgm   : [label iftherestofthecodedoesntworkthenthiswillbeaninfiniteloop, push 8.0, label decrement, dec, jmpz finished, jmp decrement, jmp iftherestofthecodedoesntworkthenthiswillbeaninfiniteloop, label finished, exit]\n"
								 + "Pc    : 10\n"
								 + "Stack : [0.0]\n"
								 + "Memory: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]\n"
								 + "------------------------------------------------\n");
	}

}
