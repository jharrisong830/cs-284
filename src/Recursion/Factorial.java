
public class Factorial {
	
	/** Recursive factorial method. 
 	precondition: n >= 0
 	@param n The integer whose factorial is being computed
 	@return n!
	 */
	public static int factorial(int n) {
		if (n == 0) {
			//new Throwable().printStackTrace();
			return 1;
		}
		return n * factorial(n-1); 
	}
	//factorial(4)
	//4 * factorial(3)
	//4 * 3 * factorial(2)
	//4 * 3 * 2 * factorial(1)
	//4 * 3 * 2 * 1 * factorial(0)
	//4 * 3 * 2 * 1 * 1

	
	/** Tail recursive factorial method. 
 	precondition: n >= 0
 	@param n The integer whose factorial is being computed
 	@param a accumulator for the already processed part of the factorial 
 	@return n!
	 */
	private static int f_helper(int n, int a) {
		if (n == 0) {
			return a;
		}
		else {
			return f_helper(n-1, n * a);
		}
	}
	//f_helper(4, 1)
	//f_helper(3, 4)
	//f_helper(2, 12)
	//f_helper(1, 24)
	//f_helper(0, 24)
	//return 24

	
	/** Wrapper for tail recursive factorial method. 
 	precondition: n >= 0
 	@param n The integer whose factorial is being computed
 	@return n!
	 */
	public static int factorial_tail_recursive(int n) {
		return f_helper(n, 1);
	}
	
	/** Iterative factorial method. 
 	precondition: n >= 0
 	@param n The integer whose factorial is being computed
 	@return n!
	 */
	public static int factorial_it(int n) {
		int r = 1;
		while (n > 0) {
			r*=n;
			n--;
		}
		return r;
	}
	
	public static void main(String[] args) {
		System.out.println(factorial(-2)); 
	}
}