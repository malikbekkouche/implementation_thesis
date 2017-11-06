import javax.xml.ws.Holder;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
class ChromaticTest {
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
		testConcurrentRange(tree);
	}
	
	public static void testConcurrentRange(ConcurrentChromaticTreeMap tree){
		tree.put(7,5);
		tree.put(1,66);
		tree.put(22,11);
		CyclicBarrier barrier=new CyclicBarrier(2);
		Thread t=new Thread(()-> {
			try{
			final ConcurrentChromaticTreeMap arbre=tree;
			
			for(int i=0;i<10000;i++){
				arbre.put(15,15);
				//System.out.println("print");
			}
			
			barrier.await();}catch(Exception e){System.out.println("thread");}
		}
		);
		t.start();
		tree.successor(10,barrier);
		


		//testRangeTo(tree);
		//testFindMin(tree);

		//testContains(tree);
		testRangeTo(tree);

		testContains(tree);


	}
	
	public static void testContains(ConcurrentChromaticTreeMap tree){
		System.out.println(tree.contains(0));
		tree.add(0,7);
		System.out.println(tree.contains(0));
		System.out.println(tree.contains(7));
	}
	
	public static void testFindOrAdd(ConcurrentChromaticTreeMap tree){
		System.out.println(tree.findOrAdd(0,0));
		System.out.println(tree.findOrAdd(0,7));
		System.out.println(tree.findOrAdd(1,7));
	}
	
	public static void testRemove(ConcurrentChromaticTreeMap tree){
		tree.put(5,5);
		tree.put(9,7);
		tree.put(7,5);
		tree.put(1,7);
		tree.put(77,5);
		tree.put(0,9);
		tree.put(4,5);
		tree.put(11,7);
		System.out.println(tree.remove(0));
		System.out.println(tree.remove(12));
	}

	
	public static void testFindMin(ConcurrentChromaticTreeMap tree){
		tree.put(5,5);
		tree.put(9,7);
		tree.put(7,5);
		tree.put(1,7);
		tree.put(77,5);
		tree.put(0,9);
		tree.put(4,5);
		tree.put(11,7);
		System.out.println(tree.findMin());
	}
	
	public static void testDeleteMin(ConcurrentChromaticTreeMap tree){
		tree.put(5,5);
		tree.put(9,7);
		tree.put(7,5);
		tree.put(1,7);
		tree.put(77,5);
		//tree.put(0,9);
		tree.put(4,5);
		tree.put(11,7);
		System.out.println(tree.deleteMin());
	}
	
	public static void testFindMax(ConcurrentChromaticTreeMap tree){
		tree.put(5,5);
		tree.put(9,7);
		tree.put(7,5);
		tree.put(1,7);
		tree.put(77,5);
		tree.put(0,9);
		tree.put(4,5);
		tree.put(11,7);
		System.out.println(tree.findMax());
	}
	
	public static void testDeleteMax(ConcurrentChromaticTreeMap tree){
		/* tree.put(5,5);
		tree.put(9,7);
		tree.put(7,5);
		tree.put(1,7);
		tree.put(77,5);
		tree.put(0,9); */
		tree.put(4,5);
		tree.put(11,7);
		System.out.println(tree.deleteMax());
		System.out.println(tree.deleteMax());
		System.out.println(tree.deleteMax());

	}
	
	public static void testAdd(ConcurrentChromaticTreeMap tree){
		System.out.println(tree.add(5,6));
		System.out.println(tree.add(5,7));
		System.out.println(tree.get(5));
	}
	
	public static void testUpdate(ConcurrentChromaticTreeMap tree){
		System.out.println(tree.add(5,6));
		System.out.println(tree.update(5,7));
		System.out.println(tree.get(5));
	}
	
	public static void testUpdateWithValue(ConcurrentChromaticTreeMap tree){
		System.out.println(tree.add(5,6));
		Holder<Integer> i=new Holder(new Integer(0));
		System.out.println(tree.update(5,7,i));
		System.out.println("int "+i.value);
		System.out.println(tree.get(5));
	}
	
	public static void testUpdateOrAdd(ConcurrentChromaticTreeMap tree){
		System.out.println(tree.add(5,6));
		System.out.println(tree.updateOrAdd(5,7));
		System.out.println(tree.get(5));
		System.out.println(tree.updateOrAdd(1,2));
		System.out.println(tree.get(1));
	}
	
	public static void testUpdateOrAddWithValue(ConcurrentChromaticTreeMap tree){
		System.out.println(tree.add(5,6));
		Holder<Integer> i=new Holder(new Integer(0));
		System.out.println(tree.updateOrAdd(5,7,i));
		System.out.println("int "+i.value);
		System.out.println(tree.get(5));
	}
	
	public static void testFind(ConcurrentChromaticTreeMap tree){
		System.out.println(tree.add(5,6));
		Holder<Integer> i=new Holder(new Integer(0));
		System.out.println(tree.find(5,i));
		System.out.println("int "+i.value);
		System.out.println(tree.get(5));
	}
	
	public static void testAddAll(ConcurrentChromaticTreeMap tree){
		ArrayList<SimpleEntry<Integer,Integer>> list=new ArrayList();
		list.add(new SimpleEntry(4,4));
		list.add(new SimpleEntry(5,5));
		list.add(new SimpleEntry(6,6));
		tree.addAll(list);
		
		
		System.out.println(tree.get(5));
	}
	
	/* public static void testSucc(ConcurrentChromaticTreeMap tree){
		System.out.println(tree.add(5,6));
		System.out.println(tree.add(8,7));
		System.out.println(tree.successor(4).key);
		System.out.println(tree.successor(5).key);
		System.out.println(tree.add(7,7));
		System.out.println(tree.successor(1).key);
		System.out.println(tree.successor(5).key);
		System.out.println(tree.successor(10).key);
		
	} */
	
	public static void testPred(ConcurrentChromaticTreeMap tree){
		System.out.println(tree.add(5,6));
		System.out.println(tree.add(8,7));
		//System.out.println(tree.predecessor(4).key);
		//System.out.println(tree.predecessor(5).key);
		System.out.println(tree.add(7,7));
		System.out.println(tree.predecessor(6).key);
		System.out.println(tree.predecessor(8).key);
		System.out.println(tree.predecessor(10).key);
		
	}
	
	
	public static void testRangeFrom(ConcurrentChromaticTreeMap tree){
		System.out.println(tree.add(5,6));
		System.out.println(tree.add(8,7));
		System.out.println(tree.add(7,7));
		ArrayList<SimpleEntry<Integer,Integer>> list=tree.rangeFrom(6);
		for(SimpleEntry<Integer,Integer> i : list)
			System.out.println("key : "+i.getKey()+" - "+i.getValue());
		
	}
	
	public static void testRangeFromTo(ConcurrentChromaticTreeMap tree){
		System.out.println(tree.add(5,6));
		System.out.println(tree.add(8,7));
		System.out.println(tree.add(7,7));
		System.out.println(tree.add(9,6));
		System.out.println(tree.add(1,7));
		System.out.println(tree.add(17,7));
		System.out.println(tree.add(99,0));
		System.out.println(tree.add(15,4));
		ArrayList<SimpleEntry<Integer,Integer>> list=tree.rangeFromTo(8,16);
		for(SimpleEntry<Integer,Integer> i : list)
			System.out.println("key : "+i.getKey()+" - "+i.getValue());
		
	}
	
	public static void testRangeTo(ConcurrentChromaticTreeMap tree){
		System.out.println(tree.add(5,6));
		System.out.println(tree.add(8,7));
		System.out.println(tree.add(7,7));
		System.out.println(tree.add(9,6));
		System.out.println(tree.add(1,7));
		System.out.println(tree.add(17,7));
		System.out.println(tree.add(99,0));
		System.out.println(tree.add(15,4));
		System.out.println(tree.add(16,4));
		ArrayList<SimpleEntry<Integer,Integer>> list=tree.rangeTo(16);
		for(SimpleEntry<Integer,Integer> i : list)
			System.out.println("key : "+i.getKey()+" - "+i.getValue());
		CyclicBarrier bcc = new CyclicBarrier(10);
		
	}

	public static void testSuccessor(ConcurrentChromaticTreeMap tree){
		System.out.println(tree.add(11,11));
		System.out.println(tree.add(23,23));
		System.out.println(tree.add(3,3));
		System.out.println(tree.add(4,4));
		System.out.println(tree.add(5,5));
		System.out.println(tree.add(63,6));
		System.out.println(tree.add(7,1));
//		System.out.println(tree.findMin().getKey());
//		System.out.println(tree.findMin().getValue());
//		System.out.println(tree.findMax().getKey());
//		System.out.println(tree.findMax().getValue());
//		System.out.println(tree.deleteMin().getKey());
//		System.out.println(tree.deleteMax().getKey());
//		
//		System.out.println(tree.findMin().getValue());
//		System.out.println(tree.findMax().getKey());
		Holder<Integer> out = new Holder<Integer>();
		System.out.println(tree.trySuccessor(5, out));
		System.out.println(out.value);
		System.out.println(tree.tryWeakSuccessor(5, out));
		System.out.println(out.value);
		System.out.println(tree.tryPredecessor(5, out));
		System.out.println(out.value);
		System.out.println(tree.tryWeakPredecessor(7, out));
		System.out.println(out.value);
		
		
		
	}
}