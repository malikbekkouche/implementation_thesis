class Main {
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
		//tree.put(5,7);
		//ConcurrentChromaticTreeMap<Integer,Integer> snap=tree.snapshot();
		System.out.println("put "+tree.put(8,1));
		System.out.println("get "+tree.get(8));
		System.out.println(tree.search(8,true).n.key);
	}
}
