import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.function.IntToDoubleFunction;

import javax.xml.ws.Holder;

import java.util.Random;
public class TestChromaticTree{
	public static AtomicIntegerArray _arrCounter; 
	public static int sumOfArray = 0;
	public static void main(String []args){
		ConcurrentChromaticTreeMap<Integer, String> map = new ConcurrentChromaticTreeMap<Integer, String>();		
		//LockbasedAVLTreeMap<Integer, String> map = new LockbasedAVLTreeMap<Integer, String>(); 
		//LogicalOrderingAVL<Integer, String> map = new LogicalOrderingAVL<Integer, String>(Integer.MIN_VALUE, Integer.MAX_VALUE);
		//SnapTreeMap<Integer, String> map = new SnapTreeMap<Integer, String>();
		//StaticDictionary5<Integer, String> map = new StaticDictionary5<Integer, String>();
		

		//ConcurrentChromaticTreeMap<Integer, String> snap = map.snapshot();
		int totalOperation = (int)Math.pow(2, 20);
		int numOfThread = (int)Math.pow(2, 4);
		int threadCount = 16;
		_arrCounter = new AtomicIntegerArray(threadCount);
		testMap(threadCount, 1000, 1000, map);
		int []arrCounter = new int[threadCount];
		for(int i = 0; i < threadCount; i++){
			arrCounter[i] = 0;
		}

		List<Integer> lst = new LinkedList<Integer>();	
		Map<Integer, String> map_ = new HashMap<Integer, String>();		
		map.transformToList(lst);
		map.transformTreeToMap(map_);
		for(String value : map_.values()){
			 arrCounter[Integer.parseInt(value.split("_")[0])]++;
		}
		
		//System.out.println("Check for tree array Counter ");
		for(int i = 0; i < threadCount; i++){
			//System.out.println(arrCounter[i]);
		}
		
		//System.out.println("Check order of tree = " + map.checkOrderTree());
		int summ = 0;
		for(Integer i : lst){
			summ += (int)i;
			////System.out.println("sum tree = " + i);
		}
		System.out.println("sum of Tree = " + summ + " - " + sumOfArray);	
		assert(summ == sumOfArray);
		for(int i = 0; i < threadCount; i++){
			assert(arrCounter[i] == _arrCounter.get(i));

		}
		//				//System.out.println(map.put(2, "abc"));		
		//				//System.out.println(map.put(7, "def"));		
		//				//System.out.println(map.put(8, "xyz"));		
		return;
	}
	private static double testMap(int threadCount, int perThread, int range,
			//	final SnapTreeMap<Integer, String> map){
			//  final StaticDictionary5<Integer, String> map){
			//	final LockbasedAVLTreeMap<Integer, String> map){
			//	final LogicalOrderingAVL<Integer, String> map ){
		     final ConcurrentChromaticTreeMap<Integer, String> map){				
			
		Thread[] threads = new Thread[threadCount];
		AtomicIntegerArray threadCounter = new AtomicIntegerArray(threadCount);
		AtomicIntegerArray arrayCounter = new AtomicIntegerArray(threadCount);	

		/* AtomicInteger sumOfPut = new AtomicInteger(0);
		AtomicInteger sumOfGet = new AtomicInteger(0);
		AtomicInteger sumOfRemove = new AtomicInteger(0); */

		for(int t = 0; t < threadCount; t++){
			final int myThread = t;		
			Random rand = new Random();
			threads[t] = new Thread(() ->{				
				for(int i = 0; i < perThread; i++){//PUT					
					Integer key = rand.nextInt(range);
					String result = map.put(key, myThread + "_" + i);
					//String result = map.putIfAbsent(key, myThread + "_" + i);
					if(result != null){
						int index = Integer.parseInt(result.split("_")[0]);
						arrayCounter.addAndGet(index, -1);
						////System.out.println("Thread " + myThread  + " IS SAME WITH " + index + " OF KEY = " + key);
						threadCounter.addAndGet(index, -key);
						threadCounter.addAndGet(myThread, key);
					}
					arrayCounter.addAndGet(myThread, 1);

					//sumOfPut.incrementAndGet();
					////System.out.println("Thread " + myThread + "_" + i + " PUT " + key + " value " + result);
					if(result == null){//this depend on every implementation
					//if(result != null){//this depend on every implementation						
						threadCounter.addAndGet(myThread, key);							
					}
				}

				  for(int k = 0; k < perThread; k++){//GET
					Integer key = rand.nextInt(range);
					String result = map.get(key); 
					/* if(result !=null){
						sumOfGet.incrementAndGet();
					} */
				}


				  for(int x = 0; x < perThread; x++){//REMOVE
					Integer key = rand.nextInt(range);
					String result = map.remove(key,true); 
					////System.out.println("Thread " + myThread + " DELETE " + key + " value " + result);
					//sumOfRemove.incrementAndGet();
					if(result != null){										
						threadCounter.addAndGet(myThread, -key);
						int index = Integer.parseInt(result.split("_")[0]);
							arrayCounter.addAndGet(index, -1);	
					}
				}  

			});
		}

		for(int k = 0; k < threadCount; k++){
			threads[k].start();
		}
		try{
			for(int m = 0; m < threadCount; m++){
				threads[m].join();
			}
		}catch(InterruptedException exn){

		}
		//		Iterator<java.util.Map.Entry<Integer, String>> it = map.entrySet().iterator();
		//		int sumOfAll = 0;
		//		while(it.hasNext()){
		//			sumOfAll += it.next().getKey().intValue();
		//		}
		int sumArray = 0;
		for(int i = 0; i < threadCount; i++){
			sumArray += threadCounter.get(i);		
			////System.out.println("ThreadCounter " +threadCounter.get(i));
		}
		
		//System.out.println("Check array counter ");
		/* for(int i = 0; i < threadCount; i++){
			//System.out.println("Array counter " + arrayCounter.get(i));				
		} */
		
		//System.out.println("sumOfThreadCounter = " + sumOfArray);
		//				//System.out.println("Sum of PUT = " + sumOfPut.get());
		//				//System.out.println("Sum of GET = " + sumOfGet.get());
		//				//System.out.println("Sum of REMOVE = " + sumOfRemove.get());
		_arrCounter = arrayCounter;
		sumOfArray = sumArray;
		return 0;
	}

	public static double Mark7(String msg, IntToDoubleFunction f) {
		int n = 10, count = 1, totalCount = 0;
		double dummy = 0.0, runningTime = 0.0, st = 0.0, sst = 0.0;
		do { 
			count *= 2;
			st = sst = 0.0;
			for (int j=0; j<n; j++) {
				Timer t = new Timer();
				for (int i=0; i<count; i++) 
					dummy += f.applyAsDouble(i);
				runningTime = t.check();
				double time = runningTime * 1e6 / count; // microseconds
				st += time; 
				sst += time * time;
				totalCount += count;
			}
		} while (runningTime < 0.25 && count < Integer.MAX_VALUE/2);
		double mean = st/n, sdev = Math.sqrt((sst - mean*mean*n)/(n-1));
		////System.out.printf("%-25s %15.1f us %10.2f %10d%n", msg, mean, sdev, count);
		return dummy / totalCount;
	}

	private static class Timer {
		private long start, spent = 0;
		public Timer() { play(); }
		public double check() { return (System.nanoTime()-start+spent)/1e9; }
		public void pause() { spent += System.nanoTime()-start; }
		public void play() { start = System.nanoTime(); }
	}

}


