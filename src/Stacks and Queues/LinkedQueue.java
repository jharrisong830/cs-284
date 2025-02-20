public class LinkedQueue<E> {
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
	// data fields
	private Node<E> front = null;
	private Node<E> tail = null;
	private int size = 0;
	
	/** * Insert an item at the rear of the queue.
	 * @post item is added to the rear of the queue.
	 * @param item The element to add
	 * @return true (always successful)
	 */
	public boolean offer(E obj) { //add
		if (front == null) {
			front = new Node<E>(obj);
			tail = front;  //keep track of tail
		}
		else {
			tail.next = new Node<E>(obj);
			tail = tail.next; //update tail
		}
		size++;
		return true;
	}
	
	/** See whether the queue is empty.
     * @return true if the queue is empty
     */
	public boolean isEmpty() {
		if(front==null) {
			return true;
		}
		return false;
	}
	
	/** Remove the entry at the front of the queue and return it
	 * if the queue is not empty.
	 * @post front references item that was second in the queue. 
     * @return The item removed if successful, or null if not */
	public E poll() { //remove/pop
		E temp=peek();
		if(temp==null) {
			return null;
		}
		front=front.next;
		size--;
		return temp;
	}
	
	/** Return the item at the front of the queue without removing it.
	 * @return The item at the front of the queue if successful;
	 *return null if the queue is empty
	 */
	public E peek() {
		if(isEmpty()) {
			return null;
		}
		return front.data;
	}
	
	public static void main(String[] args) {
		//Midterm Recap Quiz Question 3
		LinkedQueue < Integer > myQueue = new LinkedQueue < Integer >() ;
		myQueue.offer(10);
		myQueue.offer(1);
		int x=myQueue.poll();
		myQueue.offer(5);
		int y=myQueue.peek();
		int w=myQueue.poll();
		myQueue.offer(x+y);
		int z=myQueue.peek();
		System.out.println("x = "+x);
		System.out.println("y = "+y);
		System.out.println("w = "+w);
		System.out.println("z = "+z);
	}
 
}