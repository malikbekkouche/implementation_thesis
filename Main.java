import java.util.Random;
class Main {
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
		//tree.put(5,7);
		for(int j=0;j<10;j++){
			//System.out.println("PUT "+j);
			tree.put(j,j);
		}

		System.out.println("-------------------------------");
		System.out.println("-----------END OF PUT----------");
		System.out.println("-------------------------------");

		ConcurrentChromaticTreeMap<Integer,Integer> snap=tree.snapshot();


		Thread t4=new Thread(() -> {
			Random r=new Random();
			//int s=0;
			//while(true){
			for(int s=0;s<1000;s++){
			//s++;
			if(s==500)
			System.out.println(s + " sssss");
			//System.out.println("while");
			for(int j=0;j<10;j++){
				Integer x =snap.get(j);

				if(x==null)
					System.out.println(j +" is null");
				else if(x!=j)
					System.out.println(j +" is not x "+x+" s: ");
				assert x!=null;
				assert x==j;
			}

			}
		});
		t4.start();



		int threadCount=4;
		Thread[] t=new Thread[threadCount];
		for(int j=0;j<threadCount;j++){
			t[j]=new Thread(() -> {

			Random r=new Random();
			//while(true){
				for(int v=0;v<100;v++){
				int x=r.nextInt(10);
				tree.remove(x);
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

		/*Thread t3=new Thread(() -> {
				//System.out.println("while");
				for(int j=0;j<10000;j++){
					Integer x =tree.get(j);

					if(x==null)
						System.out.println(j +" is null");
					else if(x!=j+1)
						System.out.println(j +" is not x "+x);
					assert x!=null;
					assert x==j+1;

			}
		});
		t3.start();*/
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

		Thread t2=new Thread(() -> {
			Random r=new Random();
			//int s=0;
			//while(true){
			//for(int s=0;s<1000;s++){
				//s++;
				//if(s==1000)
					//System.out.println(s + " sssss");
				//System.out.println("while");
				for(int j=0;j<10;j++){
					Integer x =snap.get(j);

					if(x==null)
						System.out.println(j +" is null");
					else if(x!=j)
						System.out.println(j +" is not x "+x+" s: ");
					assert x!=null;
					assert x==j;
				}

			//}
		});
		//t2.start();
		System.out.println("-------------------------------");
		System.out.println("-------------FINISH---------------");
		System.out.println("-------------------------------");
		ConcurrentChromaticTreeMap.Node r=snap.root.left.left;
		System.out.println("root "+r.key);
		System.out.println("left "+r.left.key);
		System.out.println("right "+r.right.key);
		System.out.println("left left "+r.left.left.key);
		System.out.println("right tight "+r.right.right.key);
		System.out.println("left right "+r.left.right.key);
		System.out.println("right left "+r.right.left.key);
		System.out.println("left right left "+r.left.right.left.key);
		System.out.println("left right right "+r.left.right.right.key);
		System.out.println("right left left"+r.right.left.left.key);
		System.out.println("right left right"+r.right.left.right.key);
		System.out.println("left left left"+r.left.left.left.key);
		System.out.println("left left right"+r.left.left.right.key);
		System.out.println("right tight right"+r.right.right.right.key);
		System.out.println("right tight left"+r.right.right.left.key);

		
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

