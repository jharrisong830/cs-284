public class BTree<E> {
	
	static class Node<E>{
		
		//data fields 
		/**The reference to the data */
		E data; 
		/**Reference to the left child */
		Node<E> left;
		/**Reference to the right child */
		Node<E> right;
		
		//Constructors 
		
		/** Creates a new node with a null next field. 
		 * @param dataItem The data stored
		 */
		Node(E dataItem) {
			data = dataItem;
			left = null;
			right = null;
		}
		
		/** Creates a new node with a null next field. 
		 * @param dataItem The data stored
		 * @param leftNode the node stored to the left of current Node
		 * @param rightNode the node stored to the right of current Node
		 */
		Node(E dataItem, Node<E> leftNode, Node<E> rightNode) {
			data = dataItem;
			left = leftNode;
			right = rightNode;
		}
		
		// Methods
		/** Return a string representation of the node.
		 * @return A string representation of the data fields
		 */
		public String toString() {
			return data.toString();
		}
	}
	
	// data fields
	
	/** The root of the binary tree */
	protected Node<E> root;
	/** The number of nodes in the binary tree */
	private int size;
	
	//Constructors
	public BTree() {
		root = null;
		size = 0;
	}
	
	public BTree(E root) {
		this.root = new Node<E>(root);
		size = 1;
	}
	
	/** Constructs a new binary tree with data in its root leftTree
	 * as its left subtree and rightTree as its right subtree.
	 */
	public BTree(E root, BTree<E> leftTree, BTree<E> rightTree) {
		this.root = new Node<E>(root, leftTree.root, rightTree.root);
		size = 1 + leftTree.size + rightTree.size;
	}
	
	/** Recursive function to the height of a BTree.
	 * @param current the local root 
	 * @return height of the BTree
	 */
	private int height(Node<E> current) {
		if (current == null) {
			return 0;
		}
		return 1 + Math.max(height(current.left), height(current.right));
	}
	/** Finds the height of a BTree.
	 * @return height of the BTree
	 */
	
	public int height() {
		return height(root);
	}
	
	/** Determine whether this tree is a leaf.
	 * @return true if the root has no children
	 */
	
	public boolean isLeaf() {
		return (root.left == null && root.right == null);
	}
	
	private int noOfLeaves(Node<E> current) {
		if(current==null) {
			return 0;
		}
		else if(current.left==null && current.right==null) {
			return 1;
		}
		else {
			return noOfLeaves(current.left) + noOfLeaves(current.right);
		}
	}
	
	/** Finds the number of leaves in the tree. 
	 * @return The number of leaves in the tree. 
	 */
	public int noOfLeaves() {
		return noOfLeaves(root);
	}
	
	/** Converts a subtree to a string.
	 * Performs a preorder traversal.
	 * @param current The local root
	 * @param depth The depth
	 */
	private String toString(Node<E> current, int depth) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<depth;i++) {
			sb.append("-");
		}
		if (current==null) {
			sb.append("null\n");
		} else {
			sb.append(current.data.toString()+"\n");
			sb.append(toString(current.left, depth+1)); //
			sb.append(toString(current.right,depth+1));
		}
		return sb.toString();
	}
	
	public String toString() {
		return toString(root,0);
	}
	
	public static void main(String[] args) {
		BTree<Integer> t1 = new BTree<Integer>(7,new BTree<Integer>(), new 
				BTree<Integer>());

		
		BTree<Integer> t2 = new BTree<Integer>(33,
				new BTree<Integer>(27,new BTree<Integer>(), new 
						BTree<Integer>()),
				new BTree<Integer>());
		BTree<Integer> t3 = new BTree<Integer>(23,t1,t2);
		System.out.println(t3.toString());

	}
}