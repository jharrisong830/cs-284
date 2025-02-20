//John Graham
//I pledge my honor that I have abided by the Stevens Honor System.

import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;

public class Treap<E extends Comparable<E>> {
	
	private static class Node<E extends Comparable <E>> {
		//Node data fields
		public E data; // key for the search
		public int priority; // random heap priority
		public Node <E> left;
		public Node <E> right;
		
		//Node constructor
		public Node(E dataItem, int priorityKey) {
			if(dataItem==null) {
				throw new IllegalArgumentException("Node: data cannot be null");
			}
			data=dataItem;
			priority=priorityKey;
			left=null;
			right=null;
		}
		
		/**A toString method for nodes.
		 * @return String, a string representation of a node*/
		public String toString() {
			return "(key="+data+", priority="+priority+")";
		}
		
		/**Performs a right rotation on a node.
		 * @return Node<E>, the root as a result of a right rotation on a node*/
		Node<E> rotateRight() {
			Node<E> temp=this.left;
			this.left=temp.right;
			temp.right=this;
			return temp;
		}
		
		/**Performs a left rotation on a node.
		 * @return Node<E>, the root as a result of a left rotation on a node*/
		Node<E> rotateLeft() {
			Node<E> temp=this.right;
			this.right=temp.left;
			temp.left=this;
			return temp;
		}
		
	}
	
	//Treap data fields
	private Random priorityGenerator;
	private Node<E> root;
	
	//Treap constructors
	public Treap() {
		priorityGenerator=new Random();
	}
	public Treap(long seed) {
		priorityGenerator=new Random(seed);
	}
	
	//Priority Array List & Index Finder
	ArrayList<Integer> priorityList=new ArrayList<Integer>();
	
	/**Finds whether or not a given priority value already exists in the treap.
	 * @param priority, the priority value that needs to be located
	 * @return int, the index at which the priority is (returns -1 if priority is not in the list)
	 */
	int indexFind(int priority) {
		for(int i=0; i<priorityList.size(); i++) {
			if(priorityList.get(i)==priority) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	
	//Main Operations
	
	
	/**Adds a node with a random priority to the treap.
	 * @param key, the key of the node to be added
	 * @return boolean, whether or not the node was successfully added*/
	boolean add(E key) {
		return add(key, priorityGenerator.nextInt(100));
	}
	
	/**Adds a node with a given priority to the treap.
	 * @param key, the key of the node to be added
	 * @param priority, the priority value of the node
	 * @return boolean, whether or not the node was successfully added*/
	boolean add(E key , int priority) {
		if(key==null) {
			throw new IllegalArgumentException("add: key cannot be null");
		}
		Node<E> currentNode=root;
		Node<E> prevNode=null;;
		if(currentNode==null) { //empty treap, the root is now the new node
			root=new Node<E>(key,priority);
			priorityList.add(priority);
			return true;
		}
		
		if(indexFind(priority)!=-1) { //duplicate priority, cannot add
			return false;
		}
		
		Stack<Node<E>> priorityStack=new Stack<Node<E>>();
		int i=0;
		while(currentNode!=null) {
			priorityStack.push(currentNode);
			i = currentNode.data.compareTo(key);
			if(i<0) { //key to be added is greater than the parent, move right
				prevNode=currentNode;
				currentNode=currentNode.right;
			}
			else if(i>0) { //key to be added is less than the parent, move left
				prevNode=currentNode;
				currentNode=currentNode.left;
			}
			else { //key is the same as parent, cannot add
				return false;
			}
		}
		if(i<0) {
			prevNode.right=new Node<E>(key,priority);
			currentNode=prevNode.right;
			reheap(priorityStack);
			priorityList.add(priority);
			return true;
		}
		else if(i>0) {
			prevNode.left=new Node<E>(key,priority);
			currentNode=prevNode.left;
			reheap(priorityStack);
			priorityList.add(priority);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**Helper function used to reheap the treap (so that the nodes are sorted by priority in a max-heap style).
	 * @param priorityStack, a stack of all of the nodes in the path of the add, from the root to the new node's parent
	 * @return Node<E>, bc why not?*/
	Node<E> reheap(Stack<Node<E>> priorityStack) {
		Node<E> currentParent=priorityStack.pop();
		Node<E> currentGrand=null;
		if(!priorityStack.isEmpty()) { //if stack is not empty after parent is popped, then there is a grandparent node
			currentGrand=priorityStack.peek();
		}
		if(currentParent.left!=null && (currentParent.priority < currentParent.left.priority)) {
			Node<E> rotatedNode=currentParent.rotateRight();
			if(currentGrand==null) { //parent is root
				root=rotatedNode;
				return rotatedNode;
			}
			else if(currentGrand.right==currentParent) {
				currentGrand.right=rotatedNode;
				return reheap(priorityStack);
			}
			else {
				currentGrand.left=rotatedNode;
				return reheap(priorityStack);
			}
		}
		else if(currentParent.right!=null && (currentParent.priority < currentParent.right.priority)) {
			Node<E> rotatedNode=currentParent.rotateLeft();
			if(currentGrand==null) { //parent is root
				root=rotatedNode;
				return rotatedNode;
			}
			else if(currentGrand.right==currentParent) {
				currentGrand.right=rotatedNode;
				return reheap(priorityStack);
			}
			else {
				currentGrand.left=rotatedNode;
				return reheap(priorityStack);
			}
		}
		else {
			return currentParent;
		}
	}
	
	
	/**Removes a node from the treap through a series of rotations, bubbling the node to be deleted down until it is a leaf.
	 * @param key, the key of the node to be deleted
	 * @return boolean, whether or not the node was successfully deleted*/
	boolean delete(E key) {
		if(key==null) {
			throw new IllegalArgumentException("delete: key cannot be null");
		}
		Node<E> currentNode=root;
		Node<E> prevNode=null;
		int i=0;
		
		while(currentNode!=null) {
			i = currentNode.data.compareTo(key);
			if(i<0) { //key to be removed is greater than the parent, move right
				prevNode=currentNode;
				currentNode=currentNode.right;
			}
			else if(i>0) { //key to be removed is less than the parent, move left
				prevNode=currentNode;
				currentNode=currentNode.left;
			}
			else { //key is the same as parent, found the key!
				int priority=currentNode.priority;
				deleteHelper(currentNode, prevNode);
				priorityList.remove(indexFind(priority)); //TODO
				return true;
			}
		}
		return false; //key could not be found :(
	}
	
	/**Helper for delete, which determines what rotations to make based on the node to be deleted's parents and children
	 * @param deleteNode, the node to be deleted
	 * @param prevNode
	 * @return Node<E>, bc why not?*/
	Node<E> deleteHelper(Node<E> deleteNode, Node<E> prevNode) {
		if(prevNode==null) { //deleting the root
			if(deleteNode.left==null & deleteNode.right==null) { //root is a leaf
				root=null;
				return deleteNode;
			}
			prevNode=deleteRoot(deleteNode);
			return deleteHelper(deleteNode, prevNode);
		}
		int i=deleteNode.data.compareTo(prevNode.data); //<0 delete is a left child, >0 delete is a right child
		if(deleteNode.left==null & deleteNode.right==null) { //its a leaf, delete
			if(prevNode.left==deleteNode) {
				prevNode.left=null;
				return deleteNode;
			}
			else { //prevNode.right==null
				prevNode.right=null;
				return deleteNode;
			}
		}
		else if(deleteNode.right==null) { //right child is null, rotate right
			prevNode=deleteRotateRight(deleteNode, prevNode, i);
			return deleteHelper(deleteNode, prevNode);
		}
		else if(deleteNode.left==null) { //left child is null, rotate left
			prevNode=deleteRotateLeft(deleteNode, prevNode, i);
			return deleteHelper(deleteNode, prevNode);
		}
		else { //left and right are both not null
			if(deleteNode.left.priority > deleteNode.right.priority) { //rotate right
				prevNode=deleteRotateRight(deleteNode, prevNode, i);
				return deleteHelper(deleteNode, prevNode);
			}
			else { //rotate left
				prevNode=deleteRotateLeft(deleteNode, prevNode, i);
				return deleteHelper(deleteNode, prevNode);
			}
		}
	}
	/**Performs a right rotation on the node to be deleted and updates fields accordingly.
	 * @param deleteNode, the node to be deleted
	 * @param prevNode, the parent of the node to be deleted
	 * @param i, compareTo result of deleteNode and prevNode
	 * @return Node<E>, the new parent of the node to be deleted after the rotation*/
	Node<E> deleteRotateRight(Node<E> deleteNode, Node<E> prevNode, int i) {
		Node<E> temp=deleteNode.rotateRight();
		if(i<0) { //delete is a left child of prev
			prevNode.left=temp;
			prevNode=temp;
			return prevNode;
		}
		else { //delete is a right child of prev
			prevNode.right=temp;
			prevNode=temp;
			return prevNode;
		}
	}
	
	/**Performs a left rotation on the node to be deleted and updates fields accordingly.
	 * @param deleteNode, the node to be deleted
	 * @param prevNode, the parent of the node to be deleted
	 * @param i, compareTo result of deleteNode and prevNode
	 * @return Node<E>, the new parent of the node to be deleted after the rotation*/
	Node<E> deleteRotateLeft(Node<E> deleteNode, Node<E> prevNode, int i) {
		Node<E> temp=deleteNode.rotateLeft();
		if(i<0) { //delete is a left child of prev
			prevNode.left=temp;
			prevNode=temp;
			return prevNode;
		}
		else { //delete is a right child of prev
			prevNode.right=temp;
			prevNode=temp;
			return prevNode;
		}
	}
	
	/**Performs the necessary rotation on the root of a treap to begin the process of deleting the root.
	 * @param deleteNode, the node to be deleted
	 * @return Node<E>, the new parent of the node to be deleted after the rotation (also the new root of the treap)*/
	Node<E> deleteRoot(Node<E> deleteNode) {
		if(deleteNode.right==null) { //right child is null, rotate right
			Node<E> temp=deleteNode.rotateRight();
			root=temp;
			return temp;
		}
		else if(deleteNode.left==null) { //left child is null, rotate left
			Node<E> temp=deleteNode.rotateLeft();
			root=temp;
			return temp;
		}
		if(deleteNode.left.priority > deleteNode.right.priority) { //rotate right
			Node<E> temp=deleteNode.rotateRight();
			root=temp;
			return temp;
		}
		else { //rotate left
			Node<E> temp=deleteNode.rotateLeft();
			root=temp;
			return temp;
		}
	}
	
	
	/**Finds whether or not a node with a given key is in the treap, starting from the root.
	 * @param root, the root of the treap
	 * @param key, the key of the node to be located
	 * @return boolean, whether or not the node with the given key could be found*/
	private boolean find(Node <E> root , E key) {
		if(key==null) {
			throw new IllegalArgumentException("find: key cannot be null");
		}
		if (root == null) { //base case
			return false;
		}
		int i = root.data.compareTo(key); //0, 1, -1
		if (i==0) { //root data is the same as key, true
			return true;
		}
		if (i<0) { //root data is less than the key, move right
			return find(root.right,key);
		} else { //root data is greater than the key, move left
			return find(root.left,key);
		}
	}
	
	/**Finds whether or not a node with a given key is in the treap.
	 * @param key, the key of the node to be located
	 * @return boolean, whether or not the node with the given key could be found*/
	public boolean find(E key) {
		return find(root,key);
	}
	
	
	
	/**A toString function for treaps.
	 * @param current, the current node
	 * @param depth, the current depth/level of the tree
	 * @return String, a string representation of the treap*/
	private String toString(Node<E> current, int depth) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<depth;i++) {
			sb.append("-");
		}
		if (current==null) {
			sb.append("null\n");
		} else {
			sb.append(current.toString()+"\n");
			sb.append(toString(current.left, depth+1)); //
			sb.append(toString(current.right,depth+1));
		}
		return sb.toString();
	}
	
	/**A toString function for treaps, starting at the root (depth 0).
	 * @return String, a string representation of the treap*/
	public String toString() {
		return toString(root,0);
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Treap<Character> testTree = new Treap <Character>();
		System.out.println(testTree.toString());
		System.out.println();
		System.out.println();
		/*testTree.add (4 ,19);
		System.out.println(testTree.toString());
		System.out.println();
		testTree.add (2 ,31);
		testTree.add (6 ,70);
		testTree.add (1 ,84);
		testTree.add (3 ,12);
		testTree.add (5 ,83);
		testTree.add (7 ,26);*/
		testTree.add('z',47);
		testTree.add('w',32);
		testTree.add('v',21);
		testTree.add('x',25);
		testTree.add('p',99);
		testTree.add('u',75);
		testTree.add('r',40);
		System.out.println(testTree.toString());
		
		System.out.println();
		System.out.println();
		
		testTree.delete('z');
		System.out.println(testTree.toString());
		
		
		/*System.out.println(testTree.find(6)); //true
		System.out.println(testTree.find(22)); //false
		System.out.println(testTree.find(4)); //true
		System.out.println(testTree.find(7)); //true*/
	}

}