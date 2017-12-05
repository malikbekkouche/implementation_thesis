import java.util.Random;
class Ain {
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
		//tree.put(5,7);
		for(int j=0;j<30;j++){
			//System.out.println("PUT "+j);
			tree.put(j,j);
		}
		
		System.out.println("-------------------------------");
		System.out.println("-----------END OF PUT----------");
		System.out.println("-------------------------------");
		//System.out.println("remove "+tree.remove(1));
		//tree.printTree();
		System.out.println("-------------------------------");
		System.out.println("-----------END OF print----------");
		System.out.println("-------------------------------");
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
		/* Random r = new Random();
		for(int i = 0; i < 50; i++){
			int x=r.nextInt(30);
		System.out.println("REMOVE " + x + " FROM TREE " + tree.remove(x));
		for(int j = 0; j < 30; j++){
			Integer c=snap.get(j);
			if(c!=null && c!=j){
				System.out.println("Error on snap get " + j);
			}else if(c==null){
				System.out.println("Error on snap get null " + j);
			}
			
			//System.out.println("GET " + i + " FROM SNAP = " + snap.get(50));	
		}
		System.out.println("-------------------------------");
		System.out.println("-------------------------------");
		System.out.println("-------------------------------");
		}
		//System.out.println("REMOVE 60 FROM TREE " + tree.remove(60));
		//System.out.println("REMOVE 70 FROM TREE " + tree.remove(70));
		for(int j = 0; j < 30; j++){
			Integer c=snap.get(j);
			if(c!=null && c!=j){
				System.out.println("Error on snap get " + j);
			}else if(c==null){
				System.out.println("Error on snap get null " + j);
			}
			
			//System.out.println("GET " + i + " FROM SNAP = " + snap.get(50));	
		}
		 */
		 
		 System.out.println("remove "+ 19+" "+tree.remove(19));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 6+" "+tree.remove(6));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 19+" "+tree.remove(19));
		 //System.out.println("---------------------------------");
		 System.out.println("remove "+ 20+" "+tree.remove(20));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 14+" "+tree.remove(14));
		 //System.out.println("---------------------------------");
		 System.out.println("remove "+ 29+" "+tree.remove(29));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 19+" "+tree.remove(19));
		 //System.out.println("---------------------------------");
		 System.out.println("remove "+ 28+" "+tree.remove(28));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 20+" "+tree.remove(20));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 4+" "+tree.remove(4));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 3+" "+tree.remove(3));
		 //System.out.println("---------------------------------");
		 System.out.println("remove "+ 23+" "+tree.remove(23));
		 //System.out.println("---------------------------------");
		 System.out.println("remove "+ 21+" "+tree.remove(21));
		 //System.out.println("---------------------------------");
		 System.out.println("remove "+ 8+" "+tree.remove(8));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 2+" "+tree.remove(2));
		 //System.out.println("---------------------------------");
		 System.out.println("remove "+ 24+" "+tree.remove(24));
		 //System.out.println("---------------------------------");
		 System.out.println("remove "+ 11+" "+tree.remove(11));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 0+" "+tree.remove(0));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 2+" "+tree.remove(2));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 28+" "+tree.remove(28));
		 //System.out.println("---------------------------------");
		 System.out.println("remove "+ 17+" "+tree.remove(17));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 28+" "+tree.remove(28));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 0+" "+tree.remove(0));
		 //System.out.println("---------------------------------");
		 System.out.println("remove "+ 25+" "+tree.remove(25));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 14+" "+tree.remove(14));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 7+" "+tree.remove(7));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 2+" "+tree.remove(2));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 3+" "+tree.remove(3));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 17+" "+tree.remove(17));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 14+" "+tree.remove(14));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 1+" "+tree.remove(1));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 4+" "+tree.remove(4));
		 //System.out.println("---------------------------------");
		 System.out.println("remove "+ 16+" "+tree.remove(16));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 24+" "+tree.remove(24));
		 //System.out.println("---------------------------------");
		 System.out.println("remove "+ 27+" "+tree.remove(27));
		 //System.out.println("---------------------------------");
		 System.out.println("remove "+ 18+" "+tree.remove(18));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 6+" "+tree.remove(6));
		 //System.out.println("---------------------------------");
		 System.out.println("remove "+ 26+" "+tree.remove(26));
		 //System.out.println("---------------------------------");
		 System.out.println("remove "+ 22+" "+tree.remove(22));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 1+" "+tree.remove(1));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 17+" "+tree.remove(17));
		 //System.out.println("---------------------------------");
		 //System.out.println("remove "+ 27+" "+tree.remove(27));
		 //System.out.println("---------------------------------");
		// System.out.println("remove "+ 28+" "+tree.remove(28));
		 System.out.println("---------------------------------");
		 System.out.println("remove "+ 10+" "+tree.remove(10));
		 System.out.println("---------------------------------");
		 for(int j = 0; j < 30; j++){
			Integer c=snap.get(j);
			if(c!=null && c!=j){
				System.out.println("Error on snap get " + j);
			}else if(c==null){
				System.out.println("Error on snap get null " + j);
			}
			
			//System.out.println("GET " + i + " FROM SNAP = " + snap.get(50));	
		}
//		System.out.println("---------------------------------");
//		System.out.println("---------------------------------");
//		System.out.println("---------------------------------");
//		 System.out.println("remove "+ 18+" "+tree.remove(18));
//		 System.out.println("---------------------------------");
//		 System.out.println("remove "+ 11+" "+tree.remove(11));
//		 System.out.println("---------------------------------");
//		 System.out.println("remove "+ 23+" "+tree.remove(23));
//		 System.out.println("---------------------------------");
//		 System.out.println("remove "+ 6+" "+tree.remove(6));
//		 System.out.println("---------------------------------");
//		 System.out.println("---------------------------------");
//		 System.out.println("---------------------------------");
//		 System.out.println("---------------------------------");
//		 for(int j = 0; j < 30; j++){
//			Integer c=snap.get(j);
//			if(c!=null && c!=j){
//				System.out.println("Error on snap get " + j);
//			}else if(c==null){
//				System.out.println("Error on snap get null " + j);
//			}
//			
//			//System.out.println("GET " + i + " FROM SNAP = " + snap.get(50));	
//		}
		 //System.out.println("remove "+ +" "+tree.remove());
		
		
		/* for(int i = 0; i < 31; i++){
			Integer c=snap.get(i);
			if(c!=null && c!=i){
				System.out.println("Error on snap get " + i);
			}else if(c==null){
				System.out.println("Error on snap get null " + i);
			}
			
			//System.out.println("GET " + i + " FROM SNAP = " + snap.get(50));	
		} */
		
		/* for(int i = 0; i < 10; i++){
			if(tree.get(i) != null){
				System.out.println("Error on tree get " + i);
				System.out.println("Tree get " + tree.get(i));
			}
			//System.out.println("GET " + i + " FROM TREE = " + tree.get(50));	
		} */
		
//		System.out.println("GET 60 FROM SNAP " + snap.get(60));
//		System.out.println("GET 70 FROM SNAP " + snap.get(70));

//		int threadCount=4;
//		Thread[] t=new Thread[threadCount];
//		for(int j=0;j<threadCount;j++){
//			t[j]=new Thread(() -> {
//
//			Random r=new Random();
//			while(true){
//				//for(int k=0;k<10000;k++){
//				//int c=r.nextInt();
//				//tree.put(c,2*c);
//				//tree.put(r.nextInt(),r.nextInt());
//				//for(int i=500;i<1000;i++)
//					int x=r.nextInt(1000);
//				//System.out.println("thread");
//					//System.out.println("removage "+x);
//					//System.out.println("remove "+x+" "+tree.remove(x));
//					//System.out.println("put "+tree.put(x,x+1));
//					
//					tree.remove(x);
//					//tree.put(x,x+1);
//					//x=r.nextInt(1000);
//					/* if(tree.get(x)!=null)
//						System.out.println("tree error "+x); */
//					//Integer c=snap.get(x);
//					 /* if(c==null )
//						System.out.println("snap null error "+x); */
//					/*else if(c!=x)
//						System.out.println(c +" is not x thread1"); */
//			}
//		});
//		}
		
//	 	for(int j=0;j<threadCount;j++){
//			t[j].start();
//		}
		
		        

//	 	Thread t2=new Thread(() -> {
//			Random r=new Random();
//			int s=0;
//			while(true){
//				//System.out.println("while");
//				for(int j=0;j<100;j++){
//					Integer x =snap.get(j);
//					if(x==null)
//						System.out.println(j +" is null");
//					else if(x!=j)
//						System.out.println(j +" is not x "+x+" s: "+s);
//					Integer y=tree.get(j);
//					/* if(y!=j+1)
//						System.out.println(y+" (tree) is different than "+(j+1)); */
//				}
//				for(int j=-500;j<0;j++)
//					assert snap.get(j)==null;
//				for(int j=1000;j<1500;j++)
//					assert snap.get(j)==null;
//				int i=r.nextInt();
//				Integer x=snap.get(i);
//				if(x!=null && (i!=3000 && i!=2 && i!=4 && i!=-4000))
//					System.out.println("mismatch on 0");
//				if(snap.get(10000)!=null)
//					System.out.println("mismatch 33");
//				//assert (tree.get(i)==null || tree.get(i)==2*i);
//			}
//		});
		//t2.start(); 
		
		
		
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

