public class PalindromeChecker {
	private String inputString;
	private LinkedStack<Character> charStack = new LinkedStack<Character>();
	
	public PalindromeChecker(String str) {
		inputString = str;
		fillStack();
	}
	
	private void fillStack() {
		for (int i = 0; i < inputString.length(); i++) {
			if (inputString.charAt(i) != ' ') {
				charStack.push(inputString.charAt(i));
			}
		}
	}
	
	private String buildReverse() {
		StringBuilder sb = new StringBuilder();
		while (!charStack.empty()) {
			sb.append(charStack.pop());
		}
		return sb.toString();
	}
	
	public boolean isPalindrome() {
		return inputString.equalsIgnoreCase(buildReverse());
	}
	
	public static void main(String[] args) {
		PalindromeChecker p = new PalindromeChecker("kayak");
		System.out.println(p.isPalindrome());
	}
}
