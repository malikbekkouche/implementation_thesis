class SeqMain{
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
		//tree.put(5,7);
		for(int i=0;i<1000;i++)
			tree.put(i,i);
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
		for(int i=0;i<100;i++)
			tree.remove(i);
		System.out.println("-------------------------------");
		for(int i=0;i<100;i++){
			Integer x=snap.get(i);
			if(x==null)
				System.out.println(i+" is null");
			else if (i!=x)
				System.out.println(i+" is different than "+x);
		}
		System.out.println("-------------------------------");
	/* 	System.out.println("get 8,88 "+snap.get(8));
		System.out.println("get 4,44 "+snap.get(4));
		System.out.println("get 18,1818 "+snap.get(18));
		System.out.println("get 2,22 "+snap.get(2));  */
		System.out.println("-------------------------------");
		
	}
}