package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
    int size;
    
	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		size = 0;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if(root == null){
			root = new BinaryNode<E>(x);
			size++;
			return true;
		}
		if(addToTree(x, root)){
			size++;
			return true;
		}
		return false;
	}
	
	private boolean addToTree(E x, BinaryNode<E> root) {
		int c = x.compareTo(root.element);
		if (c == 0 || root == null) {
			return false;
		}
		if (c < 0) {
			if (root.left == null) {
				root.left = new BinaryNode<E>(x);
				return true;
			} else {
				return addToTree(x, root.left);
			}
		} else {
			if (root.right == null) {
				root.right = new BinaryNode<E>(x);
				return true;
			} else {
				return addToTree(x, root.right);
			}
		}
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	private int height(BinaryNode<E> root){
		if(root == null){
			return 0;
		}
		return 1 + Math.max(height(root.left),height(root.right));
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}
	private void printTree(BinaryNode<E> root){
		if(root == null){
			return;
		}
		printTree(root.left);
		System.out.println(root.element.toString());
		printTree(root.right);
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		E[] a = (E[]) new Comparable[size()];
		toArray(root,a,0);
		root = buildTree(a,0,a.length-1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index].
	 * Returns the index of the last inserted element + 1 (the first empty
	 * position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if(n == null){
			return index;
		}
		index = toArray(n.left, a, index);
		a[index] = n.element;
		return toArray(n.right, a, index+1);
	}

	
	
	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		if(first > last){
			return null;
		}
		int middle = (first+last)/2;
		BinaryNode<E> root = new BinaryNode<E>(a[middle]);
		root.left = buildTree(a,first,middle-1);
		root.right = buildTree(a,middle+1,last);
		return root;
		
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
	public static void main(String[] args){
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.add(79);
		tree.add(45);
		tree.add(96);
		tree.add(82);
		tree.add(14);
		tree.add(58);
		tree.add(73);
		
		//duplicates
		tree.add(14);
		tree.add(79);
		tree.add(83);
		tree.add(10000);
		tree.add(1);
		tree.add(1337);
		tree.add(8);
		System.out.println("size: " + tree.size());
		System.out.println("height:" + tree.height());
		System.out.println("In order:");
		tree.printTree();
		System.out.println("------");
		BSTVisualizer viz = new BSTVisualizer("viz",500,500);
		viz.drawTree(tree);
		tree.rebuild();
		viz.drawTree(tree);
	}
	
}
