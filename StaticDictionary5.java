/**
 *  This is an implementation of the non-blocking, concurrent binary search tree of
 *  Faith Ellen, Panagiota Fatourou, Eric Ruppert and Franck van Breugel.
 *
 *  Copyright (C) 2011  Trevor Brown, Joanna Helga
 *  Contact Trevor Brown (tabrown@cs.toronto.edu) with any questions or comments.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;



public class StaticDictionary5<K extends Comparable<? super K>, V> {

	//--------------------------------------------------------------------------------
	// Class: Node
	//--------------------------------------------------------------------------------
	protected final static class Node<E extends Comparable<? super E>, V> {
		final E key;
		final V value;
		volatile Node<E,V> left;
		volatile Node<E,V> right;
		volatile Info<E,V> info;

		/** FOR MANUAL CREATION OF NODES (only used directly by testbed) **/
		Node(final E key, final V value, final Node<E,V> left, final Node<E,V> right) {
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
			this.info = null;
		}

		/** TO CREATE A LEAF NODE **/
		Node(final E key, final V value) {
			this(key, value, null, null);
		}

		/** TO CREATE AN INTERNAL NODE **/
		Node(final E key, final Node<E,V> left, final Node<E,V> right) {
			this(key, null, left, right);
		}
	}

	//--------------------------------------------------------------------------------
	// Class: Info, DInfo, IInfo, Mark, Clean
	// May 25th: trying to make CAS to update field static
	// instead of using <state, Info>, we extends Info to all 4 states
	// to see a state of a node, see what kind of Info class it has
	//--------------------------------------------------------------------------------
	protected static abstract class Info<E extends Comparable<? super E>, V> {
	}

	protected final static class DInfo<E extends Comparable<? super E>, V> extends Info<E,V> {
		final Node<E,V> p;
		final Node<E,V> l;
		final Node<E,V> gp;
		final Info<E,V> pinfo;

		DInfo(final Node<E,V> leaf, final Node<E,V> parent, final Node<E,V> grandparent, final Info<E,V> pinfo) {
			this.p = parent;
			this.l = leaf;
			this.gp = grandparent;
			this.pinfo = pinfo;
		}
	}

	protected final static class IInfo<E extends Comparable<? super E>, V> extends Info<E,V> {
		final Node<E,V> p;
		final Node<E,V> l;
		final Node<E,V> newInternal;

		IInfo(final Node<E,V> leaf, final Node<E,V> parent, final Node<E,V> newInternal){
			this.p = parent;
			this.l = leaf;
			this.newInternal = newInternal;
		}
	}

	protected final static class Mark<E extends Comparable<? super E>, V> extends Info<E,V> {
		final DInfo<E,V> dinfo;

		Mark(final DInfo<E,V> dinfo) {
			this.dinfo = dinfo;
		}
	}

	protected final static class Clean<E extends Comparable<? super E>, V> extends Info<E,V> {}

	//--------------------------------------------------------------------------------
	// DICTIONARY
	//--------------------------------------------------------------------------------
	private static final AtomicReferenceFieldUpdater<Node, Node> leftUpdater = AtomicReferenceFieldUpdater.newUpdater(Node.class, Node.class, "left");
	private static final AtomicReferenceFieldUpdater<Node, Node> rightUpdater = AtomicReferenceFieldUpdater.newUpdater(Node.class, Node.class, "right");
	private static final AtomicReferenceFieldUpdater<Node, Info> infoUpdater = AtomicReferenceFieldUpdater.newUpdater(Node.class, Info.class, "info");

	final Node<K,V> root;

	public StaticDictionary5() {
		// to avoid handling special case when <= 2 nodes,
		// create 2 dummy nodes, both contain key null
		// All real keys inside BST are required to be non-null
		root = new Node<K,V>(null, new Node<K,V>(null, null), new Node<K,V>(null, null));
	}

	public StaticDictionary5(final Node<K,V> root) {
		this.root = root;
	}

	public final int transformToList(List<K> list){
		return  transformTreeToList(root.left.left, list);
	}
	public int transformTreeToList(final Node<K,V> node, List<K> list){
		if(node == null) return 0;
		if(node != null && node.left == null && node.right == null){
			list.add((K)node.key);			
		}
		return transformTreeToList(node.left, list) + transformTreeToList(node.right, list);			
	}
	
	public int transformTreeToMap(Map<K,V> map){
		return transformTreeToMap_(root.left.left,map);
	}
	
	public int transformTreeToMap_(final Node<K,V> node, Map<K,V> map){
		if(node == null) return 0;
		if(node != null && node.left == null && node.right == null){
			map.put(node.key, node.value);			
		}
		return transformTreeToMap_(node.left, map) + transformTreeToMap_(node.right, map);			
	}

	public boolean checkOrderTree(){
		return checkOrderOfTree(root.left.left);
	}

	public boolean checkOrderOfTree(final Node<K,V> node){		
		if(node == null) return true;
		else{
			if(node.left!=null && node.right!=null){				
				if(node.key.compareTo(node.left.key) < 0 || node.key.compareTo((K) node.right.key) > 0){
					return false;
				}
			}else if(node.left!=null){
				if(node.key.compareTo(node.left.key) < 0)
					return false;
			}else if(node.right!=null){
				if(node.key.compareTo((K) node.right.key) > 0)
					return false;
			}
		}		
		return checkOrderOfTree(node.left) && checkOrderOfTree(node.right);
	}
	
	
	//--------------------------------------------------------------------------------
	// PUBLIC METHODS:
	// - find   : boolean
	// - insert : boolean
	// - delete : boolean
	//--------------------------------------------------------------------------------

	/** PRECONDITION: k CANNOT BE NULL **/
	public final boolean containsKey(final K key) {
		if (key == null) throw new NullPointerException();
		Node<K,V> l = root.left;
		while (l.left != null) {
			l = (l.key == null || key.compareTo(l.key) < 0) ? l.left : l.right;
		}
		return (l.key != null && key.compareTo(l.key) == 0) ? true : false;
	}

	/** PRECONDITION: k CANNOT BE NULL **/
	public final V get(final K key) {
		if (key == null) throw new NullPointerException();
		Node<K,V> l = root.left;
		while (l.left != null) {
			l = (l.key == null || key.compareTo(l.key) < 0) ? l.left : l.right;
		}
		return (l.key != null && key.compareTo(l.key) == 0) ? l.value : null;
	}

	// Insert key to dictionary, returns the previous value associated with the specified key,
	// or null if there was no mapping for the key
	/** PRECONDITION: k CANNOT BE NULL **/
	public final V putIfAbsent(final K key, final V value){
		Node<K,V> newInternal;
		Node<K,V> newSibling, newNode;

		/** SEARCH VARIABLES **/
		Node<K,V> p;
		Info<K,V> pinfo;
		Node<K,V> l;
		/** END SEARCH VARIABLES **/

		newNode = new Node<K,V>(key, value);

		while (true) {

			/** SEARCH **/
			p = root;
			pinfo = p.info;
			l = p.left;
			while (l.left != null) {
				p = l;
				l = (l.key == null || key.compareTo(l.key) < 0) ? l.left : l.right;
			}
			pinfo = p.info;                             // read pinfo once instead of every iteration
			if (l != p.left && l != p.right) continue;  // then confirm the child link to l is valid
			// (just as if we'd read p's info field before the reference to l)
			/** END SEARCH **/

			if (key.equals(l.key)) {
				return l.value;	// key already in the tree, no duplicate allowed
			} else if (!(pinfo == null || pinfo.getClass() == Clean.class)) {
				help(pinfo);
			} else {
				newSibling = new Node<K,V>(l.key, l.value);
				if (l.key == null || key.compareTo(l.key) < 0){	// newinternal = max(ret.l.key, key);
					newInternal = new Node<K,V>(l.key, newNode, newSibling);					
				}
				else{
					newInternal = new Node<K,V>(key, newSibling, newNode);					
				}

				
				final IInfo<K,V> newPInfo = new IInfo<K,V>(l, p, newInternal);

				// try to IFlag parent
				if (infoUpdater.compareAndSet(p, pinfo, newPInfo)) {
					helpInsert(newPInfo);
					return null;
				} else {
					// if fails, help the current operation
					// [CHECK]
					// need to get the latest p.info since CAS doesnt return current value
					help(p.info);
				}
			}
		}
	}

	// Insert key to dictionary, return the previous value associated with the specified key,
	// or null if there was no mapping for the key
	/** PRECONDITION: k CANNOT BE NULL **/
	public final V put(final K key, final V value) {
		Node<K, V> newInternal;
		Node<K, V> newSibling, newNode;
		IInfo<K, V> newPInfo;
		V result;

		/** SEARCH VARIABLES **/
		Node<K, V> p;
		Info<K, V> pinfo;
		Node<K, V> l;
		/** END SEARCH VARIABLES **/
		newNode = new Node<K, V>(key, value);

		while (true) {

			/** SEARCH **/
			p = root;
			pinfo = p.info;
			l = p.left;
			while (l.left != null) {
				p = l;
				l = (l.key == null || key.compareTo(l.key) < 0) ? l.left : l.right;//**original
				//l = (l.key == null || key.compareTo(l.key) < 0) ? l.right : l.left;//**MUTATION
			}
			pinfo = p.info;                             // read pinfo once instead of every iteration
			if (l != p.left && l != p.right) continue;  // then confirm the child link to l is valid
			// (just as if we'd read p's info field before the reference to l)
			/** END SEARCH **/

			if (!(pinfo == null || pinfo.getClass() == Clean.class)) {
				help(pinfo);
			} else {
				if (key.equals(l.key)) {
					// key already in the tree, try to replace the old node with new node
					newPInfo = new IInfo<K, V>(l, p, newNode);
					result = l.value;
				} else {
					// key is not in the tree, try to replace a leaf with a small subtree
					newSibling = new Node<K, V>(l.key, l.value);
					if (l.key == null || key.compareTo(l.key) < 0) // newinternal = max(ret.l.key, key);
					{//**MUTATION also work here - change newInternal up and down
						newInternal = new Node<K, V>(l.key, newNode, newSibling);//newInternal = new Node<K, V>(l.key, newNode, newSibling)
					} else {
						newInternal = new Node<K, V>(key, newSibling, newNode);//newInternal = new Node<K, V>(key, newSibling, newNode)
					}

					newPInfo = new IInfo<K, V>(l, p, newInternal);
					result = null;
				}

				// try to IFlag parent
				if (infoUpdater.compareAndSet(p, pinfo, newPInfo)) {
					helpInsert(newPInfo);
					return result;
				} else {
					// if fails, help the current operation
					// need to get the latest p.info since CAS doesnt return current value
					help(p.info);
				}
			}
		}
	}

	// Delete key from dictionary, return the associated value when successful, null otherwise
	/** PRECONDITION: k CANNOT BE NULL **/
	public final V remove(final K key){

		/** SEARCH VARIABLES **/
		Node<K,V> gp;
		Info<K,V> gpinfo;
		Node<K,V> p;
		Info<K,V> pinfo;
		Node<K,V> l;
		/** END SEARCH VARIABLES **/

		while (true) {

			/** SEARCH **/
			gp = null;
			gpinfo = null;
			p = root;
			pinfo = p.info;
			l = p.left;
			while (l.left != null) {
				gp = p;
				p = l;
				l = (l.key == null || key.compareTo(l.key) < 0) ? l.left : l.right;
			}
			// note: gp can be null here, because clearly the root.left.left == null
			//       when the tree is empty. however, in this case, l.key will be null,
			//       and the function will return null, so this does not pose a problem.
			if (gp != null) {
				gpinfo = gp.info;                               // - read gpinfo once instead of every iteration
				if (p != gp.left && p != gp.right) continue;    //   then confirm the child link to p is valid
				pinfo = p.info;                                 //   (just as if we'd read gp's info field before the reference to p)
				if (l != p.left && l != p.right) continue;      // - do the same for pinfo and l
			}
			/** END SEARCH **/

			if (!key.equals(l.key)) return null;
			if (!(gpinfo == null || gpinfo.getClass() == Clean.class)) {
				help(gpinfo);
			} else if (!(pinfo == null || pinfo.getClass() == Clean.class)) {
				help(pinfo);
			} else {
				// try to DFlag grandparent
				final DInfo<K,V> newGPInfo = new DInfo<K,V>(l, p, gp, pinfo);

				if (infoUpdater.compareAndSet(gp, gpinfo, newGPInfo)) {
					if (helpDelete(newGPInfo)) return l.value;
				} else {
					// if fails, help grandparent with its latest info value
					help(gp.info);
				}
			}
		}
	}

	//--------------------------------------------------------------------------------
	// PRIVATE METHODS
	// - helpInsert
	// - helpDelete
	//--------------------------------------------------------------------------------

	private void helpInsert(final IInfo<K,V> info){
		(info.p.left == info.l ? leftUpdater : rightUpdater).compareAndSet(info.p, info.l, info.newInternal);
		infoUpdater.compareAndSet(info.p, info, new Clean());
	}

	private boolean helpDelete(final DInfo<K,V> info){
		final boolean result;

		result = infoUpdater.compareAndSet(info.p, info.pinfo, new Mark<K,V>(info));
		final Info<K,V> currentPInfo = info.p.info;
		// if  CAS succeed or somebody else already succeed helping, the helpMarked
		if (result || (currentPInfo.getClass() == Mark.class && ((Mark<K,V>) currentPInfo).dinfo == info)) {
			helpMarked(info);
			return true;
		} else {
			help(currentPInfo);
			infoUpdater.compareAndSet(info.gp, info, new Clean());
			return false;
		}
	}

	private void help(final Info<K,V> info) {
		if (info.getClass() == IInfo.class)     helpInsert((IInfo<K,V>) info);
		else if(info.getClass() == DInfo.class) helpDelete((DInfo<K,V>) info);
		else if(info.getClass() == Mark.class)  helpMarked(((Mark<K,V>)info).dinfo);
	}

	private void helpMarked(final DInfo<K,V> info) {
		final Node<K,V> other = (info.p.right == info.l) ? info.p.left : info.p.right;
		(info.gp.left == info.p ? leftUpdater : rightUpdater).compareAndSet(info.gp, info.p, other);
		infoUpdater.compareAndSet(info.gp, info, new Clean());
	}

	/**
	 *
	 * DEBUG CODE (FOR TESTBED)
	 *
	 */

	/**
	 * If size() returns -1, then concurrent access prevented its successful read.
	 * To ensure it returns the tree size, call it using the pattern:
	 *  <code>
	 *      while((sz = tree.size()) == -1) {}
	 *  </code>
	 * This allows the caller to decide whether it should block.
	 */
	public final int size() {
		Node root = getSnapshot();
		if (root == null) return -1;
		return sequentialSize(root);
	}

	@Override
	public final StaticDictionary5 clone() {
		Node newroot = null;
		for (;;) if ((newroot = getSnapshot()) != null) return new StaticDictionary5(newroot);
	}

	/**
	 * WARNING: This assumes that there are no concurrent accesses occurring.
	 * If concurrent accesses can occur, use size() (or this on a clone()).
	 */
	public int sequentialSize(final Node node) {
		if (node == null) return 0;
		if (node.left == null && node.key != null) return 1;
		return sequentialSize(node.left) + sequentialSize(node.right);
	}

	private void readRefs(final Node node, final HashMap<Node, Pair<Node, Node>> refs) {
		if (node == null) return;
		refs.put(node, new Pair<Node, Node>(node.left, node.right));
		readRefs(node.left, refs);
		readRefs(node.right, refs);
	}

	private boolean checkRefs(final Node node, final HashMap<Node, Pair<Node, Node>> refs) {
		if (node == null) return true;
		Pair<Node, Node> p = refs.get(node);
		if (!p.equals(new Pair<Node, Node>(node.left, node.right))) return false;
		return checkRefs(p.key, refs) && checkRefs(p.value, refs);
	}

	private Node buildRefs(final Node node, final HashMap<Node, Pair<Node, Node>> refs) {
		if (node == null) return null;
		Pair<Node, Node> children = refs.get(node);
		return new Node(node.key, node.value,
				buildRefs(children.key, refs),
				buildRefs(children.value, refs));
	}

	private Node getSnapshot() {
		final HashMap<Node, Pair<Node, Node>> refs = new HashMap<Node, Pair<Node, Node>>();
		readRefs(root, refs);
		if (!checkRefs(root, refs)) return null;
		return buildRefs(root, refs);
	}

	class Pair<K,V> {
		K key;
		V value;
		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		@Override
		public boolean equals(Object o) {
			if (o == null || !o.getClass().equals(getClass())) return false; // CAN DO AWAY WITH THIS AT THE COST OF TYPE SAFETY
			Pair<K,V> p = (Pair<K,V>) o;
			return p.key == key && p.value == value;
		}
	}

	private int sumDepths(Node node, int depth) {
		if (node == null) return 0;
		return depth + sumDepths(node.left, depth+1) + sumDepths(node.right, depth+1);
	}

	public final int getSumOfDepths() {
		return sumDepths(root, 0);
	}

	/**
	 * WARNING: This assumes that there are no concurrent accesses occurring.
	 * If concurrent accesses can occur, use size() (or this on a clone()).
	 */
	public final int sequentialSize() {
		return sequentialSize(root);
	}


}

