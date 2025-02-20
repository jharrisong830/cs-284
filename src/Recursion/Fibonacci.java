//fib(0) = 0 
//fib(1) = 1
//fib(n) = fib(n-1) + fib(n-2)
//0, 1, 1, 2, 3, 5, 8, 13 .....
public class Fibonacci {
	
	/** Recursive method to calculate Fibonacci numbers
 	pre: n >= 0
 	@param n The position of the Fibonacci number being calculated
 	@return The Fibonacci number
	 */
	public static int fibonacci(int n) {
		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;
		else
			return fibonacci(n-1) + fibonacci(n-2);
	}
	
	/** Recursive O(n) method to calculate Fibonacci numbers
 	pre: n >= 1
 	@param fibCurrent The current Fibonacci number
 	@param fibPrevious The previous Fibonacci number
 	@param n The count of Fibonacci numbers left to calculate
 	@return The value of the Fibonacci number calculated so far
	 */
	private static int fibo(int fibCurrent, int fibPrevious, int n) {
		if (n == 1)
			return fibCurrent;
		else
			return fibo(fibCurrent + fibPrevious, fibCurrent, n- 1); 
	}
	//fibo(1, 0, 4)
	
	/** Wrapper method for calculating Fibonacci numbers
 	pre: n >= 0
 	@param n The position of the desired Fibonacci number
 	@return The value of the nth Fibonacci number
	 */
	public static int fibonacciStart(int n) {
		if (n == 0)
			return 0;
		else
			return fibo(1, 0, n);
	}
	
	public static void main(String[] args) {
		System.out.println(fibonacci(4)); 
	}
}
