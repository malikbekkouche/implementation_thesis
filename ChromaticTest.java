import javax.xml.ws.Holder;
class ChromaticTest {
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
		testFind(tree);
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
}