import java.util.Random;
class Main {
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
		//tree.put(5,7);
		for(int j=0;j<100000;j++){
			//System.out.println("PUT "+j);
			tree.put(j,j);
		}
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

		Thread t2=new Thread(() -> {
			Random r=new Random();
			int s=0;
			while(true){
				s++;
				if(s==1000)
					System.out.println(s + " sssss");
				//System.out.println("while");
				for(int j=0;j<100000;j++){
					Integer x =snap.get(j);
					
					if(x==null)
						System.out.println(j +" is null");
					else if(x!=j)
						System.out.println(j +" is not x "+x+" s: "+s);
					assert x!=null;
					assert x==j;
					//Integer y=tree.get(j);
					/* if(y!=j+1)
						System.out.println(y+" (tree) is different than "+(j+1)); */
				}
				//System.out.println("2");
				/* for(int j=-500;j<0;j++)
					assert snap.get(j)==null;
				for(int j=1000;j<1500;j++)
					assert snap.get(j)==null;
				int i=r.nextInt();
				Integer x=snap.get(i);
				if(x!=null && (i!=3000 && i!=2 && i!=4 && i!=-4000))
					System.out.println("mismatch on 0");
				if(snap.get(10000)!=null)
					System.out.println("mismatch 33"); */
				//assert (tree.get(i)==null || tree.get(i)==2*i);
				//System.out.println("check");
			}
		});
		t2.start(); 

		int threadCount=8;
		Thread[] t=new Thread[threadCount];
		for(int j=0;j<threadCount;j++){
			t[j]=new Thread(() -> {

			Random r=new Random();
			//while(true){
				for(int v=0;v<10000;v++){
				//System.out.println("remove");
				//for(int k=0;k<10000;k++){
				//int x=r.nextInt(100000);
				//Integer c=(tree.put(x,x+1));
				//c=c+1;
				//tree.put(r.nextInt(),r.nextInt());
				//for(int i=500;i<1000;i++)
				//int x=r.nextInt(1000);
				//System.out.println("thread");
					//System.out.println("removage "+x);
					//System.out.println("remove "+x+" "+tree.remove(x));
					//System.out.println("put "+tree.put(x,x+1));
				int x=r.nextInt(100000);
				tree.remove(x);
					//System.out.println("remove "+x +" "+tree.remove(x));
					//x=r.nextInt(1000);
					//tree.put(x,x+1);
					
					/* if(tree.get(x)!=null)
						System.out.println("tree error "+x); */
					//Integer c=snap.get(x);
					 /* if(c==null )
						System.out.println("snap null error "+x); */
					/*else if(c!=x)
						System.out.println(c +" is not x thread1"); */
					if(v==1000)
						System.out.println("v "+v);
			}
		});
		}
		
	 	for(int j=0;j<threadCount;j++){
			t[j].start();
		}
		
		try {        
		for(int j=0;j<threadCount;j++){
			t[j].join();
		}
		//t2.join();
		}catch(Exception e){}
		System.out.println("-------------------------------");
		System.out.println("-------------END---------------");
		System.out.println("-------------------------------");

		 /*  for(int j=0;j<100000;j++){
			//System.out.println("get");
					Integer x =snap.get(j);
					if(x==null)
						System.out.println(j +" is null");
					else if(x!=j)
						System.out.println(j +" is not x "+x);
					//Integer y=tree.get(j);
					/* if(y!=j+1)
						System.out.println(y+" (tree) is different than "+(j+1)); 
				}  */
		System.out.println("-------------------------------");
		System.out.println("-------------FINISH---------------");
		System.out.println("-------------------------------");
		
		System.out.println("root "+snap.root.left.left.key);
		System.out.println("left "+snap.root.left.left.left.key);
		System.out.println("right "+snap.root.left.left.right.key);
		System.out.println("left left "+snap.root.left.left.left.left.key);
		System.out.println("right tight "+snap.root.left.left.right.right.key);
		
		System.out.println("-------------------------------");
		System.out.println("-------------TREE---------------");
		System.out.println("-------------------------------");
		System.out.println("root "+tree.root.left.left);
		
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
