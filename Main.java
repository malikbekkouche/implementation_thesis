import java.util.Random;
class Main {
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
		//tree.put(5,7);
		System.out.println("put "+tree.put(2,22));
		System.out.println("put "+tree.put(4,44));
		System.out.println("put "+tree.put(3000,30003000));		
		System.out.println("put "+tree.put(-4000,-40004000));
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
//		while(true){
//		System.out.println("REMOVE 2 = " + tree.remove(2));
//		System.out.println("REMOVE 4 = " + tree.remove(4));
//		System.out.println("REMOVE 8 = " + tree.remove(8));
//		System.out.println("REMOVE 18 = " + tree.remove(18));
//		}
		/* System.out.println("update 8,8 "+tree.get(8));
		System.out.println("update 8,8 "+tree.get(4));
		System.out.println("update 8,8 "+tree.get(18));
		System.out.println("update 8,8 "+tree.get(2)); */
		//System.out.println("-------------------------------");

		
		Thread t=new Thread(() -> {
			//Random r=new Random();
			while(true){
				tree.put(-4000,-4000);
				tree.put(3000,3000);
				tree.put(4,4);
				tree.put(2,2);
				tree.put(1,1);
				tree.put(7,7);
				tree.put(9,9);
			}
		});
		t.start();
		
	 	Thread t1=new Thread(() -> {
			Random r=new Random();
			while(true){
				tree.put(r.nextInt(),r.nextInt());
				tree.put(8,4);
				tree.put(r.nextInt(),r.nextInt());
				tree.put(18,9);
				tree.put(4,2);
				tree.put(2,1);
				tree.put(r.nextInt(),r.nextInt());
			}
		});
		t1.start(); 
		
		    Thread t0=new Thread(() -> {
			//Random r=new Random();
			while(true){
				tree.remove(8);
				tree.remove(-4000);
				tree.remove(4);
				tree.remove(2);
			}
		});
		t0.start();     

	 	Thread t2=new Thread(() -> {
			Random r=new Random();
			while(true){
				if(snap.get(-4000)!=-40004000)
					System.out.println("mismatch on 8");
				if(snap.get(2)!=22)
					System.out.println("mismatch on 2");
				if(snap.get(4)!=44)
					System.out.println("mismatch on 4");
				if(snap.get(3000)!=30003000)
					System.out.println("mismatch on 18");
				int i=r.nextInt();
				Integer x=snap.get(i);
				if(x!=null && (i!=3000 && i!=2 && i!=4 && i!=-4000))
					System.out.println("mismatch on 0");
			}
		});
		t2.start(); 
		
		
		
		
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
		
		//System.out.println("-------------------------------");
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

