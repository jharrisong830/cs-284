import java.util.HashSet;
import java.util.Set;

public class SetDemo {

	public static void main(String[] args) {
		String[] listA = {"Lemon", "Apple", "Orange", "Lemon"};
		Set<String> setA = new HashSet<>();
		Set<String> setAcopy = new HashSet<>();
		
		for (String s : listA) {
			setA.add(s);
			setAcopy.add(s);
		}
		
		System.out.println(setA);

		System.out.println(setA.add("Berry"));
		
		String[] listB = {"Grape", "Apple", "Peach"};
		Set<String> setB = new HashSet<>();
		
		for (String s : listB) {
			setB.add(s);
		}

		System.out.println(setB);
		System.out.println(setA.addAll(setB));
		System.out.println(setA);
		System.out.println(setAcopy.retainAll(setB));
		System.out.println(setAcopy);
		
		System.out.println(setA);
		System.out.println(setA.contains("Apple"));

		//setA.retainAll(setB);
		//System.out.println(setA);
		
		//System.out.println(setA.remove("Apple"));
		//System.out.println(setA);			
	}
}
