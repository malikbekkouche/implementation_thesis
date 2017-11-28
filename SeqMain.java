class SeqMain{
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
		//tree.put(5,7);
		System.out.println("put "+tree.put(2,22));
		System.out.println("put "+tree.put(4,44));
		System.out.println("put "+tree.put(8,88));		
		System.out.println("put "+tree.put(18,1818));
		 // System.out.println("put "+tree.put(1,11));
		//System.out.println("put "+tree.put(2,22));
		// System.out.println("put "+tree.put(15,1515));
		// System.out.println("put "+tree.put(22,2222));
		/*System.out.println("put "+tree.put(9,77));
		System.out.println("put "+tree.put(25,25));
		System.out.println("put "+tree.put(30,30));
		System.out.println("put "+tree.put(40,40));
		System.out.println("put "+tree.put(50,50));
		System.out.println("put "+tree.put(3,11)); */
		System.out.println("-------------------------------");
		System.out.println("-----------END OF PUT----------");
		System.out.println("-------------------------------");
		//System.out.println("remove "+tree.remove(1));
		ConcurrentChromaticTreeMap<Integer,Integer> snap=tree.snapshot();
		System.out.println("update 8,8 "+tree.put(8,8));
		System.out.println("update 4,4 "+tree.put(4,4));
		System.out.println("update 18,18 "+tree.put(18,18));
		System.out.println("remove 2,2 "+tree.remove(2));
		System.out.println("-------------------------------");
		 System.out.println("get 8,8 "+tree.get(8));
		System.out.println("get 4,4 "+tree.get(4));
		System.out.println("get 18,18 "+tree.get(18));
		System.out.println("get 2,null "+tree.get(2)); 
		System.out.println("-------------------------------");
		System.out.println("get 8,88 "+snap.get(8));
		System.out.println("get 4,44 "+snap.get(4));
		System.out.println("get 18,1818 "+snap.get(18));
		System.out.println("get 2,22 "+snap.get(2)); 
		System.out.println("-------------------------------");
		
	}
}