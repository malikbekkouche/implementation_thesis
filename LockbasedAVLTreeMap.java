import java.util.Comparator;
import java.util.List;


public class LockbasedAVLTreeMap<K, V> {

	private Node root;
	private final Comparator<? super K> comparator;

	public LockbasedAVLTreeMap(){
		comparator = null;
	}
	private static class Node {
		public Object key;
		public Object value;
		private int balance;
		private int height;
		private Node left;
		private Node right;
		private Node parent;

		Node(final Object key, final Object value, Node parent) {
			this.key = key;
			this.value = value;
			this.parent = parent;
			this.right = null;
			this.left = null;
		}
	}

	public synchronized final V get(final K key) {
		final Comparable<? super K> k = comparable(key);
		if (root == null) {	            
			return null;
		}

		Node n = root;
		while (true) {
			if(k.compareTo((K)n.key) == 0)	            	
				return (V)n.value;

			Node parent = n;

			//boolean goLeft = n.key > key;
			boolean goLeft = k.compareTo((K)n.key) < 0;
			n = goLeft ? n.left : n.right;

			if (n == null) {
				return null;	                
			}
		}
	}


	private Comparable<? super K> comparable(final Object key) {
		if (key == null) {
			throw new NullPointerException();
		}
		if (comparator == null) {
			return (Comparable<? super K>)key;
		}
		return new Comparable<K>() {
			@SuppressWarnings("unchecked")
			public int compareTo(final K rhs) { return comparator.compare((K)key, rhs); }
		};
	}


	//***PUT - return previous value of node
	public synchronized final V put(final K key, final V value) {
		final Comparable<? super K> k = comparable(key);
		if (root == null) {
			root = new Node(key, value, null);
			return null;
		}

		Node n = root;
		while (true) {			
			if(k.compareTo((K)n.key) == 0){
				V temp = (V)n.value;
				n.value = value;
				return temp;//key is already existed
			}

			Node parent = n;

			//boolean goLeft = n.key > key;
			boolean goLeft = k.compareTo((K)n.key) < 0;
			n = goLeft ? n.left : n.right;

			if (n == null) {
				if (goLeft) {
					parent.left = new Node(key, value, parent);
				} else {
					parent.right = new Node(key, value, parent);
				}
				rebalance(parent);
				return null;
			}
		}  	
		
	}
	public final int transformToList(List<K> list){
		return  transformTreeToList(root, list);
	}

//	public int transformTreeToList(final Node node, List<K> list){
//		if (node == null) return 0;
//		if (node.left == null && node.key != null){
//			list.add((K) node.key);        	
//			return 1;        
//		}
//		return transformTreeToList(node.left, list) + transformTreeToList(node.right, list);
//	}
	
	public int transformTreeToList(final Node node, List<K> list){
		if(node == null) return 0;
		if(node != null){
			list.add((K)node.key);			
		}
		return transformTreeToList(node.left, list) + transformTreeToList(node.right, list);			
	}
	
	

	private void delete(Node node) {
		if (node.left == null && node.right == null) {
			if (node.parent == null) {
				root = null;
			} else {
				Node parent = node.parent;
				if (parent.left == node) {
					parent.left = null;
				} else {
					parent.right = null;
				}
				rebalance(parent);
			}
			return;
		}

		if (node.left != null) {
			Node child = node.left;
			while (child.right != null) child = child.right;
			node.key = child.key;
			delete(child);
		} else {
			Node child = node.right;
			while (child.left != null) child = child.left;
			node.key = child.key;
			delete(child);
		}
	}

	public synchronized V remove(final K key) {
		final Comparable<? super K> k = comparable(key);
		if (root == null)
			return null;

		Node child = root;
		while (child != null) {
			Node node = child;
			child = k.compareTo((K)node.key) >= 0 ? node.right : node.left; 
			if(k.compareTo((K)node.key) == 0){
				V temp = (V)node.value;
				delete(node);
				return temp;
			}
		}
		return null;
	}

	private void rebalance(Node n) {
		setBalance(n);

		if (n.balance == -2) {
			if (height(n.left.left) >= height(n.left.right))
				n = rotateRight(n);
			else
				n = rotateLeftThenRight(n);

		} else if (n.balance == 2) {
			if (height(n.right.right) >= height(n.right.left))
				n = rotateLeft(n);
			else
				n = rotateRightThenLeft(n);
		}

		if (n.parent != null) {
			rebalance(n.parent);
		} else {
			root = n;
		}
	}

	private Node rotateLeft(Node a) {

		Node b = a.right;
		b.parent = a.parent;

		a.right = b.left;

		if (a.right != null)
			a.right.parent = a;

		b.left = a;
		a.parent = b;

		if (b.parent != null) {
			if (b.parent.right == a) {
				b.parent.right = b;
			} else {
				b.parent.left = b;
			}
		}

		setBalance(a, b);

		return b;
	}

	private Node rotateRight(Node a) {

		Node b = a.left;
		b.parent = a.parent;

		a.left = b.right;

		if (a.left != null)
			a.left.parent = a;

		b.right = a;
		a.parent = b;

		if (b.parent != null) {
			if (b.parent.right == a) {
				b.parent.right = b;
			} else {
				b.parent.left = b;
			}
		}

		setBalance(a, b);

		return b;
	}

	private Node rotateLeftThenRight(Node n) {
		n.left = rotateLeft(n.left);
		return rotateRight(n);
	}

	private Node rotateRightThenLeft(Node n) {
		n.right = rotateRight(n.right);
		return rotateLeft(n);
	}

	private int height(Node n) {
		if (n == null)
			return -1;
		return n.height;
	}

	private void setBalance(Node... nodes) {
		for (Node n : nodes) {
			reheight(n);
			n.balance = height(n.right) - height(n.left);
		}
	}

	public void printBalance() {
		printBalance(root);
	}

	private void printBalance(Node n) {
		if (n != null) {
			printBalance(n.left);
			System.out.printf("%s ", n.balance);
			printBalance(n.right);
		}
	}

	private void reheight(Node node) {
		if (node != null) {
			node.height = 1 + Math.max(height(node.left), height(node.right));
		}
	}


}
