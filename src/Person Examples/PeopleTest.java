import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PeopleTest {
	
	Person superperson = new Person("new", "person", 999, "999");

	@Test
	void personTest() {
		assertEquals(superperson.getAge(), 999);
		Person p = new Person("Robert", "Feliciano", 20, "123");
		assertTrue(p.getAge()==20);
		assertFalse(p.getLastName().equals("Joel"));
		assertNotSame(superperson, p);
		
	}
	
	
	@Test
	void studentTest() {
		Student s = new Student("Matt", "Kearny", 19, "546", 2025);
		assertTrue(s.getGradYear()==2025);
		assertEquals(s.getAge(), 19);
	}


	@Test
	void undergradTest() {
		Undergrad u = new Undergrad("Zeke", "Miller", 21, "789", 2023, "junior");
		assertTrue(u.getCurYear().equals("junior"));
	}

}
