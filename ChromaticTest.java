import javax.xml.ws.Holder;
class ChromaticTest {
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
		testUpdateWithValue(tree);
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
}