import java.util.Iterator;

public class MySLL<E> implements Iterable<E> {
	private static class Node<E>{
		//data fields 
		/**The reference to the data */
		private E data; 
		
		/**The reference to the next node */
		private Node<E> next; 
		
		//Constructors 
		/** Creates a new node with a null next field. 
		 * @param dataItem The data stored
		 */
		private Node(E dataItem) {
			data = dataItem;
			next = null;
		}
		
		/** Creates a new node that references another Node.
		 * @param dataItem The data stored
		 * @param nodeRef The node referenced by new node 
		 */
		private Node(E dataItem, Node<E> nodeRef) {
			data = dataItem;
			next = nodeRef;
		}
	}
	
	private static class ListIterator<E> implements Iterator<E> {
		private Node<E> current; //tracks our current place in the list
		
		/** Constructs a ListIterator that begins just before first */
		public ListIterator(Node<E> first) {
			current = first; //initializes where to start iterating from
		}
		
		@Override
		/** Returns true if next() will not throw an Exception */
		public boolean hasNext() {
			return current != null; //have we reached the end of the list?
		}
		
		@Override
		/** Returns the next object and moves the iterator forward */
		public E next() {
			E ret = current.data;
			current = current.next; //move the iterator one forward
			return ret;
		}
	}
	
	/** Reference to list head, the start of the list */
	private Node<E> head = null; 
	
	/** The number of items in the list */
	private int size = 0;
	
	/** Add item to the front of the list. 
	 * @param item The item to be added 
	 */
	public void addFirst(E item) {
		//Next field is the previous head
		head = new Node<E>(item, head); //what used to be first is now second
		size++; //increase size by 1
	}
	
	/** Insert the specified item at index
	 * @param index The position where item is to be inserted 
	 * @param item The item to be inserted 
	 * @throws IndexOutOfBoundsException if index is out of range 
	 */
	public void add(int index, E item) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(index);
		}
		//handle the case where we are updating the head of SLL
		if (index == 0) {
			addFirst(item);
		}
		else {
			Node<E> currentNode = head; //start at the head of the list
			//Iterate through the list using the next field
			//Find the Node we want to insert the element after.
			for (int i = 0; i < index - 1; i++) { //notice the index - 1 here
				currentNode = currentNode.next;
			}
			//create a new Node
			Node<E> node = new Node<E>(item, currentNode.next); 
			//next field is whatever follows currentNode
			currentNode.next = node; //insert the new Node after current Node
			size++;
		}
	}
	
	/** Remove the first node from the list. 
	 * @return The removed node's data or null if empty
	 */
	private E removeFirst() {
		Node<E> temp = head;
		if (temp == null) {
			return null;
		}
		head = temp.next;
		size--;
		return temp.data;
	}
	
	/** Remove the entry formerly at position index and returns it 
	 * @param index The position where the item is to be removed
	 * @return The removed node's data or null if empty 
	 * @throws IndexOutOfBoundsException if index is out of range 
	 */
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		if (index == 0) {
			return removeFirst();
		}
		Node<E> currentNode = head; //start at the head of the list
		//Iterate through the list using the next field
		//Find the Node we want to insert the element after.
		for (int i = 0; i < index - 1; i++) { //notice the index - 1 here
			currentNode = currentNode.next;
		}
		Node<E> tempNode = currentNode.next; 
		currentNode.next = currentNode.next.next;
		size--;
		return tempNode.data;
	}
	
	/** Gets the data at index
	 * @param index The position of the data we return
	 * @return The data at index
	 * @throws IndexOutOfBoundsException if index is out of range. 
	 */
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		Node<E> currentNode = head; //start at the head of the list
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		return currentNode.data;
	}
	
	/** Stores a reference to newValue in the element at position index
	 * @param index The position of the item to change
	 * @param newValue The new data
	 * @return The data previously at index
	 * @throws IndexOutOfBoundsException if index is out of range. 
	 */
	public E set(int index, E newValue) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		Node<E> currentNode = head; //start at the head of the list
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		E returnValue = currentNode.data;
		currentNode.data = newValue;
		return returnValue;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ListIterator<E>(head);
	}
	
	/** Creates a String representation of the SLL
	 * @return A String representing the SLL
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		//start iterating through the list from head
		Node<E> currentNode = head; 
		sb.append("[");
		//iterate through the entire list
		while (currentNode != null) {
			//append the string representation of the current element
			sb.append(currentNode.data.toString());
			sb.append(";");
			currentNode = currentNode.next; //step to the next Node
		}
		sb.append("]");
		return sb.toString();
	}
	
	
	//
	//QUIZ CODE STARTS HERE
	//
	
	//Exercise 2
	public E getLast() {
		Node<E> currentNode = head;
		if(currentNode==null) {
			return null;
		}
		for (int i = 0; i < size-1; i++) {
			currentNode = currentNode.next;
		}
		return currentNode.data;
	}
	
	//Exercise 3
	/**Removes the suffix of a list starting at index n
	 * 
	 * @param n The index at which we want to split the list
	 */
	public void take(int n) {
		if(n>size) {
			//does nothing, nothing is dropped from list
		}
		else {
			Node<E> currentNode=head;
			for(int i=0; i<n-1; i++) {
				currentNode=currentNode.next;
			}
			for(int i=n; i<size-1; i++) {
				currentNode=currentNode.next;
				currentNode=null;
				size--;
			}
		}
		//size=n;
	}
	

	public static void main(String args[]) {
		Node<Integer> n1 = new Node<Integer>(13);
		System.out.println(n1.data);
		System.out.println(n1.next);
		Node<Integer> n2 = new Node<Integer>(12, n1);
		System.out.println(n2.data);
		System.out.println(n2.next);
		System.out.println(n2.next.data);
		
		MySLL<Integer> l1 = new MySLL<Integer>();
		l1.addFirst(1);
		System.out.println(l1.head.data);
		l1.addFirst(0);
		System.out.println(l1.head.data);
		System.out.println(l1.head.next.data);
		l1.add(2,2);
		l1.add(3,3);
		System.out.println(l1.head.next.next.data);
		System.out.println(l1.toString());
		
		Iterator<Integer> it =l1.iterator();
		System.out.println(it.next());
		System.out.println(it.next());
		System.out.println(it.next());
		
		
		//
		//QUIZ CODE STARTS HERE
		//
		
		//Exercise 1
		MySLL<String> ex1=new MySLL<String>();
		ex1.add(0, "I");
		ex1.add(1, "am");
		ex1.add(2, "a");
		ex1.add(3, "list");
		System.out.println(ex1.toString());
		ex1.take(2);
		System.out.println(ex1.toString());
		

	}
}
