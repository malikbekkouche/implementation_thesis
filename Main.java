class Main {
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
		//tree.put(5,7);
		System.out.println("put "+tree.put(8,5));
		System.out.println("put "+tree.put(4,5));
		System.out.println("put "+tree.put(18,3));
		System.out.println("put "+tree.put(1,5));
		System.out.println("put "+tree.put(2,2));
		System.out.println("put "+tree.put(15,88));
		System.out.println("put "+tree.put(22,22));
		System.out.println("put "+tree.put(9,77));
		System.out.println("-------------------------------");
		System.out.println("-----------END OF PUT----------");
		System.out.println("-------------------------------");
		//System.out.println("remove "+tree.remove(1));
		ConcurrentChromaticTreeMap<Integer,Integer> snap=tree.snapshot();
		System.out.println("update "+tree.put(8,32));
		System.out.println("update "+tree.put(15,11));
		System.out.println("put "+tree.put(9,69));
		
		
		System.out.println("update "+tree.put(18,18));
		System.out.println("update "+tree.put(1,77));
		//System.out.println("put "+tree.put(9,69));
		System.out.println("put "+tree.put(22,69));
		System.out.println("-------------------------------");
		System.out.println("snap "+snap.get(8));
		System.out.println("snap "+snap.get(9));
		System.out.println("snap "+snap.get(15));
		System.out.println("snap "+snap.get(18));
		System.out.println("snap "+snap.get(1));
		System.out.println("snap "+snap.get(22));
		System.out.println("-------------------------------");
		System.out.println("tree "+tree.get(8));
		System.out.println("tree "+tree.get(15));
		System.out.println("tree "+tree.get(18));
		System.out.println("tree "+tree.get(1));
		System.out.println("tree "+tree.get(9));
		
		//System.out.println("get "+snap.get(8));
		//System.out.println("remove "+tree.remove(8));
		//System.out.println("get "+tree.get(8));
		//System.out.println("update "+tree.put(8,8));
		//System.out.println("get snap "+tree.get(8));
		//System.out.println(tree.search(8,true).n.value);
	}
}

