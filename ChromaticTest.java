import javax.xml.ws.Holder;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
class ChromaticTest {
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
		//testRangeTo(tree);
		testFindMin(tree);
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
	
	public static void testSucc(ConcurrentChromaticTreeMap tree){
		System.out.println(tree.add(5,6));
		System.out.println(tree.add(8,7));
		System.out.println(tree.successor(4).key);
		System.out.println(tree.successor(5).key);
		System.out.println(tree.add(7,7));
		System.out.println(tree.successor(1).key);
		System.out.println(tree.successor(5).key);
		System.out.println(tree.successor(10).key);
		
	}
	
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
		ArrayList<SimpleEntry<Integer,Integer>> list=tree.rangeTo(16);
		for(SimpleEntry<Integer,Integer> i : list)
			System.out.println("key : "+i.getKey()+" - "+i.getValue());
		
	}

	public static void testFindMin(ConcurrentChromaticTreeMap tree){
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