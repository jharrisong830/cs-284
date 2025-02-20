public class Person {
	
	private String firstName;
	private String lastName;
	private int age;
	private String CWID;
	
	public Person(String firstName, String last, int age, String ID) {
		this.firstName = firstName;
		this.lastName = last;
		this.age = age;
		this.CWID = ID;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getID() {
		return this.CWID;
	}
	
	public void setFirstName(String first) {
		this.firstName = first;
	}
	
	public void setLastName(String last) {
		this.lastName = last;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setID(String ID) {
		this.CWID = ID;
	}
	
	public String getCredentials() {
		return this.firstName + " " + this.lastName + "\n" + "Age: " + this.age + "\n" + "CWID: " + this.CWID;
	}

	public static void main(String[] args) {
		Person p = new Person("Billy", "Bob", 19, "12857322");
		int age = p.getAge();
		p.setAge(++age);
		System.out.println(p.getCredentials());
	}

}
