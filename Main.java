class Main {
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
		//tree.put(5,7);
		System.out.println("put "+tree.put(8,5));
		System.out.println("put "+tree.put(4,5));
		System.out.println("put "+tree.put(18,3));
		System.out.println("put "+tree.put(1,5));
		//System.out.println("put "+tree.put(15,88));
		//System.out.println("remove "+tree.remove(1));
		ConcurrentChromaticTreeMap<Integer,Integer> snap=tree.snapshot();
		System.out.println("put "+tree.remove(8));
		System.out.println("-------------------------------");
		System.out.println("get "+snap.get(8));
		//System.out.println("remove "+tree.remove(8));
		//System.out.println("get "+tree.get(8));
		//System.out.println("update "+tree.put(8,8));
		//System.out.println("get snap "+tree.get(8));
		//System.out.println(tree.search(8,true).n.value);
	}
}

