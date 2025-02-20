
public class ExceptionTest {
	
	public static int[] makeArray(int len)
	{
		if (len < 0) {
			throw new IllegalArgumentException(
					"Len must be non-negative!");
		}
		return new int[len];
	}
	
	public static void main(String[] args) {
		try {
			int[] data = makeArray(-9);
			for (int i: data) {
				System.out.println(i);
			}
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("SOMETHING IMPORTANT HERE");
	}
}
