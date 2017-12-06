import java.util.Random;
class SeqMain {
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
		tree.put(8,8);
		tree.put(4,4);
		tree.put(2,2);
		tree.put(18,18);
		tree.put(22,22);		
		ConcurrentChromaticTreeMap<Integer,Integer> snap=tree.snapshot();
		tree.remove(22);
		tree.remove(18);
		tree.remove(4);
		System.out.println("SNAP GET 18 = " + snap.get(18));
		System.out.println("SNAP GET 22 = " + snap.get(22));
		System.out.println("TREE GET 18 = " + tree.get(18));
		System.out.println("TREE GET 22 = " + tree.get(22));
		System.out.println("TREE GET 4 = " + tree.get(4));
		System.out.println("SNAP GET 4 = " + snap.get(4));		
		System.out.println("SNAP FINAL " + snap.root.left.left.key);
		System.out.println("SNAP FINAL " + snap.root.left.left.left.key);
		System.out.println("SNAP FINAL " + snap.root.left.left.left.left.key);
		
		
		System.out.println("SNAP FINAL " + snap.root.left.left.left.key);
		System.out.println("SNAP FINAL " + snap.root.left.left.right.key);		
		System.out.println("SNAP FINAL " + snap.root.left.left.right.right.key);
		System.out.println("SNAP FINAL " + snap.root.left.left.right.right.left.key);
		System.out.println("SNAP FINAL " + snap.root.left.left.right.right.right.lastGen);					
	}
}


