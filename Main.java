class Main {
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
		tree.put(5,7);
		//ConcurrentChromaticTreeMap<Integer,Integer> snap=tree.snapshot();
		System.out.println(tree.put(8,1));
	}
}

