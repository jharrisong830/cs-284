public class Student extends Person {
	
	private int gradYear;
	
	public Student(String firstName, String last, int age, String ID, int gradYear) {
		super(firstName, last, age, ID);
		this.gradYear = gradYear;
	}
	
	public int getGradYear() {
		return this.gradYear;
	}
	
	public void setGradYear(int gradYear) {
		this.gradYear = gradYear;
	}
	
	public String getCredentials() {
		return super.getCredentials() + "\n" + "Grad year: " + this.gradYear;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student s = new Student("Josh", "Stanley", 21, "1234", 2023);
		s.setGradYear(2020);
		int age = s.getAge();
		System.out.println("age: " + age);
		System.out.println(s.getCredentials());
	}

}