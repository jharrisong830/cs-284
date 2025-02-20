
public class GrowthRate {
	
	public static int search(int[] x, int target) { //linear (proportional to n = len x)
		for(int i=0; i< x.length; i++) { //len x
			if (x[i]==target) return i;
		}
		return -1; // target not found
	}
	
	public static boolean areDifferent(int[] x, int[] y) { 
		for(int i=0; i<x.length; i++) { //len x
			if (search(y, x[i]) != -1) //len y 
				return false;
			}
		return true; 
		//let n  = len x
		//let m - len y 
		//m * n 
	}
	

	public static boolean areUnique(int[] x) { 
		for(int i=0; i<x.length; i++) { //len x
			for(int j=0; j<x.length; j++) { //len x
				if (i != j && x[i] == x[j])
					return false; 
			}
		}
		return true; 
	} //(len x)^2 or n^2
	
	
	public static boolean areUnique2(int[] x) { 
		for(int i=0; i<x.length; i++) { //0 //1 //2 //3 //4 //try 5, loop stop
			for(int j=i+1; j<x.length; j++) { //1, 2, 3, 4, try 5, loop stop //2, 3, 4, try 5, loop stop //3, 4, try 5, loop stop //4, try 5, loop stop //try 5, loop stop
				if (i != j && x[i] == x[j])
					return false; 
			}
		}
		return true; 
	}
	//len x == 2, y=1
	//len x == 3, y=3
	//len x == 5, y=10

	//PATTERN: (len x-1)+(len x-2)+...+2+1
	//each pair [(len x-1) and 1] add up to be (len x)
	//FORMULA: (len x) * [(len x-1)/2]
	
	public static void f(int[] x) {
		for(int i=1; i < x.length; i *= 2) {
			System.out.println(x[i]);
		}
	}
	
	
	
	
	//for midterm
	//first try finding T polynomial, and then find the big-O class
	
	
	public static void p2(int n) {
		//a
		int counter=0;
		for(int i=0; i<n; i++) {
			for(int j=n; j>i; j--) { //depends on i, executes n times when i=0, n-1 when i=1, etc.
				counter++;
				System.out.println(counter);
				for(int k=0; k<2; k++) { //multiply by 2 at the end
					System.out.println("Hello world");
				}
			}
		}
		// n + (n-1) + (n-2) + (n-3) + ... + 1
		// = [(n-1)*(n+1)]/2
		// = 2[(n-1)*(n+1)]/2 for the k loop, multiply by 2
		// = (n-1)*(n+1)
		// = n^2 - 3n + 2   <------ T POLYNOMIAL
		// = O(n) = n^2     <------ BIG-O CLASS
		
		
		//b
		for(int i=1; i<n; i=i*2) { //log n
			for(int j=1; j<i; j=j*2) { //also related to logs, but terminates based on i
				System.out.println("Hello world!");
			}
		}
		// 0 + 1 + 2 + 3 + 4 + 5 + ... + (log(n-1) - 1) + log(n-1)
		// [(log(n) - 1)*(log(n) - 1)]/2        (t-polynomial)
		// O(n)
		
		
		
		
		//FORMULa FOR T POLYNOMIAL
		// (number of iterations) * (first iteration + last iteration)      / 2
		
	
	}
	
	
	
	public static void midtermQuiz(int n) {
		int counter=0;
		for (int i = 0; i<n; i++) {
			for (int j = n -1; j >=i; j --) {
				counter++;
				System .out . println (" hello "+ counter);
			}
		}
		/*for (int i = 0; i<n; i++) {
			for (int j = 0; j <5; j++) {
				System .out . println (" hello ");
			}
		}*/
	}
	
	public static void main(String[] args) {
		p2(6);
	}

}
