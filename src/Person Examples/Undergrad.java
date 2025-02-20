public class Undergrad extends Student{
	
	private String currYear;
	//freshman, sophomore, junior, senior, fifth
	Undergrad(String firstName, String last, int age, String ID, int gradYear, String currentYear){
		super(firstName, last, age, ID, gradYear);
		setYear(currentYear);
	}
	
	public String getCurYear() {
		return currYear;
	}
	
	public void setYear(String newYear) {
		currYear = newYear;
	}

	public String getCredentials() {
		return super.getCredentials() + "\nI am currently a " + currYear + ".";	
	}
	
	public static void main(String[] args) {
		Person p = new Undergrad("bob", "hello", 21, "123", 2022, "freshman");
		Student u = new Undergrad("kate", "chen", 44, "567", 1000, "old");
		System.out.println(p.getCredentials() + "\n\n");
		System.out.println(u.getCredentials());
	}

}
