import java.util.EmptyStackException;

public class LinkedStack<E> {
	private static class Node<E>{
		//data fields 
		private E data; 
		private Node<E> next;
		
		private Node(E dataItem) {
			data = dataItem;
			next = null;
		}
		
		private Node(E dataItem, Node<E> nodeRef) {
			data = dataItem;
			next = nodeRef;
		}
	}
	
	/** Reference to first stack node */
	private Node<E> topOfStackRef = null;
	
	/** Insert new item at top of the stack
	 * post: The new item is the top item on the stack.
     * All other items are one position lower.
     * @param obj The item to be inserted
     * @return The item that was inserted */
	public E push(E obj) {
		topOfStackRef = new Node<E>(obj, topOfStackRef);
		return obj;
	}
	
	/** See whether the stack is empty.
     * @return true if the stack is empty
     */
	public boolean empty() {
		return (topOfStackRef == null);
	}
	
	/** Remove and return the top item on the stack.
    * pre: The stack is not empty.
    * post: The top item on the stack has been
    * removed and the stack is one item smaller.
    * @return The top item on the stack
    * @throws EmptyStackException if stack is empty
    */
	public E pop() {
		if (empty()) {
			throw new EmptyStackException();
		}
		E rv = topOfStackRef.data;
		topOfStackRef = topOfStackRef.next;
		return rv;
	}
	
	/** Return the top item on the stack.
    * pre: The stack is not empty.
    * post: The stack remains unchanged.
    * @return The top item on the stack
    * @throws EmptyStackException if stack is empty
    */
	public E peek() {
		if (empty()) {
			throw new EmptyStackException();
		}
		return topOfStackRef.data;
	}
	
	public static void main(String[] args) {
		LinkedStack<String> s1 = new LinkedStack<String>();
		s1.push("3");
		s1.push("2");
		s1.push("1");
		System.out.println(s1.peek());
		System.out.println(s1.pop());
		System.out.println(s1.pop());
		System.out.println(s1.pop());

		
		LinkedStack <Integer > myStack = new LinkedStack <Integer >();
		myStack.push (1);
		myStack.push (2);
		myStack.push (3);
		int x = myStack.pop();
		int y = myStack.peek ();
		myStack.push(x+y);
		int z = myStack.pop();
		int w = myStack.pop();
		System.out.println("x ="+x);
		System.out.println("y ="+y);
		System.out.println("z ="+z);
		System.out.println("w ="+w);

	}
}
