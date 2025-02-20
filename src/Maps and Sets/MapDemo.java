import java.util.HashMap;
import java.util.Map;

public class MapDemo {

	public static void main(String[] args) {
		Map<String, String> aMap = new HashMap<>();
		aMap.put("J", "Jane");
		aMap.put("B", "Bill");
		aMap.put("S", "Sam");
		aMap.put("B1", "Bob");


		System.out.println("B maps to " + aMap.get("B"));
		System.out.println("Bill maps to " + aMap.get("Bill"));
		System.out.println(aMap.containsKey("B"));
		System.out.println(aMap.containsKey("Bill"));
		System.out.println(aMap.containsValue("Bill"));
		System.out.println(aMap.keySet());
		System.out.println(aMap.values());
		System.out.println(aMap.entrySet());
		System.out.println(aMap.toString());

	}

}
