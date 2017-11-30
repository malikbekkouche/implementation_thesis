import java.util.Random;
class Main {
	public static void main(String[] args){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();

		Random r=new Random();
		//tree.put(5,7);
		System.out.println("put "+tree.put(r.nextInt(), r.nextInt()));
		System.out.println("put "+tree.put(2,22));
		System.out.println("put "+tree.put(4,44));
		System.out.println("put "+tree.put(3000,30003000));		
		System.out.println("put "+tree.put(-4000,-40004000));
		for(int i = 0; i < 1000; i++){
			tree.put(i, i);
		}



		ConcurrentChromaticTreeMap<Integer,Integer> snap=tree.snapshot();
		//		for(int i = 0; i < 50; i++){
		//			tree.put(i, i);
		//		}
		System.out.println("-------------------------------");
		System.out.println("-----------END OF PUT----------");
		System.out.println("-------------------------------");
		//ConcurrentChromaticTreeMap<Integer,Integer> snap=tree.snapshot();

		Thread t=new Thread(() -> {
			//Random r=new Random();
			while(true){								
				//				tree.put(r.nextInt(), r.nextInt());
				//				tree.put(r.nextInt(), r.nextInt());
				//				tree.put(r.nextInt(), r.nextInt());
				//				tree.put(r.nextInt(), r.nextInt());
				//				tree.put(r.nextInt(), r.nextInt());
				//				tree.put(r.nextInt(), r.nextInt());
				//				tree.put(r.nextInt(), r.nextInt());
				//				tree.put(r.nextInt(), r.nextInt());

				tree.remove(r.nextInt(1000));
				tree.remove(r.nextInt(1000));
				tree.remove(r.nextInt(1000));
				tree.remove(r.nextInt(1000));
				tree.remove(r.nextInt(1000));
			}
		});
		//t.start();

		Thread t1=new Thread(() -> {
			//Random r=new Random();
			while(true){

				//				tree.put(r.nextInt(), r.nextInt());
				//				tree.put(r.nextInt(), r.nextInt());
				//				tree.put(r.nextInt(), r.nextInt());
				//				tree.put(r.nextInt(), r.nextInt());
				//				tree.put(r.nextInt(), r.nextInt());
				//				tree.put(r.nextInt(), r.nextInt());
				//				tree.put(r.nextInt(), r.nextInt());
				//				tree.put(r.nextInt(), r.nextInt());

				tree.remove(r.nextInt(1000));
				tree.remove(r.nextInt(1000));
				tree.remove(r.nextInt(1000));
				tree.remove(r.nextInt(1000));
				tree.remove(r.nextInt(1000));
			}
		});

		//t1.start(); 

		Thread t0=new Thread(() -> {
			while(true){
				int rand = r.nextInt(100);
				for(int i = 0; i < 100; i++){
					tree.remove(rand);
				}

				for(int i = 0; i < 100; i++){
					System.out.println("RAND =  " + rand);
					assert(snap.get(rand)==rand);
				}
			}

		});

		t0.start();     


		Thread t3 = new Thread(() -> {
			while(true){
				for(int i = 1000; i < 2000; i++){
					tree.put(i, i);
				}
			}
		});

		//t3.start();

		Thread t2=new Thread(() -> {
			//Random r=new Random();
			while(true){
				for(int i = 0; i < 50; i++){
					System.out.println("CCCCCCCCCCCCCCCC");										
					if(snap.get(i)!=i){
						//System.out.println("Mismatch on " + i);
					}
					if(snap.get(i+1000)!=null){
						//System.out.println("Wrong put on " + i);
					}
					if(tree.get(i)!=null){
						//System.out.println(i + " is on live tree ");
					}else{
						//System.out.println(i + " is NOT on live tree ");
					}
				}							
			}
		});

		//t2.start(); 


		Thread t4 = new Thread(()->{
			System.out.println("RUNNING REMOVE ");
			while(true){
				for(int i = 0; i < 50; i++){				
					System.out.println("TREE GET " + tree.remove(i,i));
				}
				System.out.println("TREE REMOVE ");
			}			
		});

		//t4.start();
	}
}

