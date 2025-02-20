
public class Division {
	public static void main(String[] args) {
		int[] num = {8, 4, 18, 32};
		int[] denom = {2, 0, 8, 4};
		for (int i = 0; i < num.length; i++) {
			System.out.println(num[i]/denom[i]);
			try {
				System.out.println(num[i]/denom[i]);
			}
			catch (ArithmeticException ex) {
				System.out.println("You can't divide by zero!");
			}
		}
	}
}
