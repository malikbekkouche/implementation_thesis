class Main {
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
		/* System.out.println("update 8,8 "+tree.get(8));
		System.out.println("update 8,8 "+tree.get(4));
		System.out.println("update 8,8 "+tree.get(18));
		System.out.println("update 8,8 "+tree.get(2)); */
		System.out.println("-------------------------------");

		System.out.println("snap "+snap.get(2)); 

		System.out.println("-------------------------------");
		//System.out.println("get "+tree.get(4)); 
		System.out.println("-------------------------------");
		System.out.println("put "+tree.put(0,0));
		System.out.println("-------------------------------");
		System.out.println("-------------------------------");
		System.out.println("tree "+tree.get(0));
		System.out.println("-------------------------------");
		System.out.println("snap "+snap.get(0));
		System.out.println("-------------------------------");
		System.out.println("update "+tree.put(2,2));
		System.out.println("-------------------------------");
		System.out.println("put "+tree.put(-20,-20));
		System.out.println("-------------------------------");
		System.out.println("tree "+tree.get(-20));
		System.out.println("-------------------------------");
		System.out.println("snap "+snap.get(-20));
		System.out.println("-------------------------------");
		System.out.println("remove "+tree.remove(4));
		System.out.println("-------------------------------");
		//System.out.println("update "+tree.put(2,2));
		System.out.println("-------------------------------");
		System.out.println("tree "+tree.get(2));
		System.out.println("-------------------------------");
		System.out.println("tree "+tree.get(4));
		System.out.println("-------------------------------");
		System.out.println("snap "+snap.get(2));
		System.out.println("-------------------------------");
		System.out.println("snap "+snap.get(4));
		System.out.println("-------------------------------");
		//System.out.println("nooo "+snap.put(2,2));
		System.out.println("-------------------------------");
		
		
		
		
		//System.out.println("put "+tree.put(9,69));
		//System.out.println("update "+tree.put(15,11));
		
		
		
		//System.out.println("update "+tree.put(1,77));
		//System.out.println("put "+tree.put(9,69));
	/* 	System.out.println("put "+tree.put(22,69));
		System.out.println("put "+tree.put(25,22));
		System.out.println("put "+tree.put(30,33));
		System.out.println("put "+tree.put(50,55));
		 System.out.println("put "+tree.put(40,44));
		 System.out.println("put "+tree.put(3,951)); */
		 
		//System.out.println("put "+tree.put(50,55)); 
		
		System.out.println("-------------------------------");
		/* System.out.println("snap "+snap.get(8));
		System.out.println("snap "+snap.get(9));
		System.out.println("snap "+snap.get(15));
		System.out.println("snap "+snap.get(18));
		System.out.println("snap "+snap.get(1));
		System.out.println("snap "+snap.get(22));
		System.out.println("snap "+snap.get(25));
		System.out.println("snap "+snap.get(30));
		System.out.println("snap "+snap.get(40));
		System.out.println("snap "+snap.get(50)); 
		System.out.println("snap "+snap.get(3)); 
		System.out.println("-------------------------------");
		System.out.println("tree "+tree.get(8));
		System.out.println("tree "+tree.get(15));
		System.out.println("tree "+tree.get(18));
		System.out.println("tree "+tree.get(1));
		System.out.println("tree "+tree.get(9));
		
		System.out.println("tree "+tree.get(30));
		System.out.println("tree "+tree.get(25));
		System.out.println("tree "+tree.get(50));
		System.out.println("tree "+tree.get(40));
		System.out.println("tree "+tree.get(3)); */
		
		//System.out.println("get "+snap.get(8));
		//System.out.println("remove "+tree.remove(8));
		//System.out.println("get "+tree.get(8));
		//System.out.println("update "+tree.put(8,8));
		//System.out.println("get snap "+tree.get(8));
		//System.out.println(tree.search(8,true).n.value);
	}
}

