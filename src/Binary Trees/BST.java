public class BST<E extends Comparable<E>> extends BTree<E> {
	BST() {
		super();
	}
	
	BST(E data) {
		super(data);
	}
	
	BST(E data, BST<E> left, BST<E> right) {
		super(data,left,right);
	}
	
	/** Recursive add method.
 	@param current The local root of the subtree
 	@param item The object to be inserted
 	@return The new local root that now contains the
 	inserted item
	 */
	private Node<E> add(E item, Node<E> current) {
		if (current == null) { //base case
			return new Node<>(item);
		}
		int i = current.data.compareTo(item); //0, 1, -1
		if (i==0) { 
			throw new IllegalStateException("add: duplicate key");
		}
		if (i<0) { //-1
			current.right = add(item,current.right);
			return current;
		} else { // 1
			current.left = add(item,current.left);
			return current;
		}
	}
	
	/** Wrapper method add.
 	pre: The object to insert must implement the Comparable interface.
 	@param target The Comparable object being inserted
	 */
	public void add(E item) {
		root = add(item,root);
	}
	
	/** Wrapper method find.
 	pre: The target object must implement the Comparable interface.
 	@param target The Comparable object being sought
 	@return The object, if found, otherwise null
	 */
	
	public E find(E target) {
		return find(root, target);
	}
	/** Recursive find method.
 	@param localRoot The local subtree's root
 	@param target The object being sought
 	@return The object, if found, otherwise null
	 */
	
	private E find(Node<E> localRoot, E target) {
		if (localRoot == null) {
			return null;
		}
		int cmpResult = target.compareTo(localRoot.data);
		if (cmpResult == 0) { //found it!
			return localRoot.data;
		}
		if (cmpResult > 0) {
			return find(localRoot.right, target);
		}
		else {
			return find(localRoot.left, target);
		}
	}
	
	/** Starter method delete.
 	post: The object is not in the tree.
 	@param target The object to be deleted
	 */
	public void remove(E target) {
		root = remove(target, root);
	}
	
	/** Recursive delete method.
 	post: The item is not in the tree;
 	@param localRoot The root of the current subtree
 	@param item The item to be deleted
 	@return The modified local root that does not contain
 	the item
	 */
	private Node<E> remove(E item, Node<E> current) {
		if (current == null) {
			throw new IllegalArgumentException("remove: item not in the tree");
		}
		int i = item.compareTo(current.data);
		if (i < 0) {
			current.left = remove(item,current.left);
			return current;
		}
		if (i > 0) {
			current.right = remove(item,current.right);
			return current;
		}
		// found the node to be removed
		// perform case analysis
		// current has no children
		if (current.left == null && current.right == null) {
			return null;
		}
		// current has only one child
		if (current.left == null) {
			return current.right;
		}
		if (current.right == null) {
			return current.left;
		}
		// current has two children 
		if (current.left.right == null) {
			current.left.right=current.right; //update current.left
			return current.left;
		}
		current.data = getMaxAndRemove(current.left);
		return current;
	}
	
	private E getMax(Node<E> current) {
		//TODO
		return null;
	}
	
	/**
	 * Return max element in the BST and remove it.
	 * Current is assumed non-empty and has a non-empty right child.
	 * @return
	 */
	private E getMaxAndRemove(Node<E> current) {
		//TODO
		return null;
	}
	
	public static void main(String[] args) {
		BST<Integer> t1 = new BST<>(7,new BST<>(), new BST<>());
		BST<Integer> t2 = new BST<>(33,new BST<>(27,new BST<>(), new 
				BST<>()),new BST<>(44,new BST<>(), new BST<>()));
		BST<Integer> t3 = new BST<>(23,t1,t2);
		//t3.add(1);
		System.out.println(t3);
	}
}