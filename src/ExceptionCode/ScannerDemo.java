import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;

public class ScannerDemo {
	//play with calling this
	public static void processPositiveInteger(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException(
					"Invalid: received negative int");
		}
	}
	
	public static int getIntValue(Scanner scan) {
		int nextInt = 0; // next int value 
		boolean validInt = false; // flag for valid input 
		while(!validInt) {
			try {
				System.out.println("Enter number of donuts: "); 
				nextInt = scan.nextInt();
				validInt = true;
			} catch (InputMismatchException ex) { 
				scan.nextLine(); // clear buffer 
				System.out.println("Bad data-enter an integer");
			} 
		}
		return nextInt; 
		}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = getIntValue(scan);
		System.out.println("Number of donuts is: " + num);
	}
	
}
