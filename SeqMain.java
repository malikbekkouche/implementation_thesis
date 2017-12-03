import java.util.Random;

class SeqMain{
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
		//tree.put(5,7);
		 /* for(int i=0;i<100;i++)
			tree.put(i,i);  */
		 tree.put(8,8);
		tree.put(4,4);
		tree.put(18,18);
		tree.put(2,2);
		tree.put(22,22);
		tree.put(1,1); 
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
		/* Random r=new Random();
		for(int i=0;i<15;i++){
		//while(true){
			int v=r.nextInt(200);
			System.out.println(v+"delete "+tree.remove(v,true));
			 
		} 
		for(int i=0;i<100;i++){
			
			 Integer x=snap.get(i);
			if(x==null)
				System.out.println(i+" is null");
			if(x!=i)
				System.out.println(x+" is different than "+i);
		}  */
		
		 /* for(int i=100;i<200;i++)
			tree.put(i,i+1); */ 
	 	tree.remove(8);
		System.out.println("-------------------------------");
		tree.remove(18);
		System.out.println("-------------------------------");
		tree.remove(22);
		System.out.println("-------------------------------");
		for(int i=0;i<20;i++){
			Integer x=snap.get(i);
			if(i==8 || i==18 ||i==22)
				assert x==i;
			else if(i==4 || i==1  || i==2)
				assert x==i;
			else 
				assert x==null;
			
			
		}
	/*	 for(int i=100;i<200;i++)
			assert tree.get(i)==i+1; 
		for(int i=00;i<20;i++){
			if(i==8 || i==18 || i==22)
				assert tree.get(i)==null;
			else if(i==4 || i==1 || i==2)
				assert tree.get(i)==i;
			else 
				assert tree.get(i)==null;
		} */
		
		System.out.println("-------------------------------");
	/* 	System.out.println("get 8,88 "+snap.get(8));
		System.out.println("get 4,44 "+snap.get(4));
		System.out.println("get 18,1818 "+snap.get(18));
		System.out.println("get 2,22 "+snap.get(2));  */
		System.out.println("-------------------------------");
		
	}
}