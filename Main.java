class Main {
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
		//tree.put(5,7);
		System.out.println("put "+tree.put(8,5));
		//ConcurrentChromaticTreeMap<Integer,Integer> snap=tree.snapshot();
		//System.out.println("put "+tree.put(8,1));
		System.out.println("get "+tree.get(8));
		System.out.println("remove "+tree.remove(8));
		Integer i = tree.get(8);
		if(i!=null)
			System.out.println("get snap ");
		//System.out.println(tree.search(8,true).n.value);
	}
}

