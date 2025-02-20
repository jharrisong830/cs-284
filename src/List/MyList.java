import java.util.Arrays;

public class MyList<E> {
	//Data fields 
	/** The default initial capacity */
	private static final int INITIAL_CAPACITY  = 10; 
	
	/** The underlying data array */
	private E[] theData; 
	
	/** The current size */
	private int size = 0; 
	
	/** The current capacity */
	private int capacity = 0; 
	
	/** Constructor */
	public MyList() {
		capacity = INITIAL_CAPACITY; 
		theData = (E[]) new Object[capacity];
	}
	
	/** Insert the new item at the position indicated by the value of size.
	 * Increment size. 
	 * @return true for successful insertion. 
	 */	public boolean add(E anEntry) {
		if (size == capacity) {
			reallocate();
		}
		theData[size] = anEntry;
		size++;
		return true;
	}
	
	/** Insert the new item at the position indicated by the value of index.
	 * Increment size
	 */
	public void add(int index, E anEntry) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(index);
		}
		if (size == capacity) {
			reallocate();
		}
		for (int i = size; i > index; i--) {
			theData[i] = theData[i - 1];
		}
		theData[index] = anEntry;
		size++;
	}
	
	/** @return the item at the given index
	 * TODO idk if this is right????????????
	 */
	public E get(int index) {
		return theData[index];
	}
	
	/** Set the item at given index to newValue. 
	 * @return the item previously stored at the index
	 * TODO idk if this is right?????????????
	 */
	public E set(int index, E newValue) {
		E oldValue=theData[index];
		theData[index]=newValue;
		return oldValue;
	}
	
	/** Remove the item at given index. 
	 * @return the item previously stored at the index
	 */
	public E remove(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(index);
		}
		E returnVal = theData[index];
		for (int i = index+1; i < size; i++) {
			theData[i-1] = theData[i];
		}
		size--; 
		return returnVal;
	}
	
	/** Creates new array twice the size of current array
	 * Copies the elements of the old array to the new one. 
	 */
	public void reallocate() {
		capacity = 2 * capacity;
		theData = Arrays.copyOf(theData, capacity);
	}
	
	public static void main(String[] args) {
		MyList<Integer> l1 = new MyList<Integer>(); 
		l1.add(1);
	}
}
