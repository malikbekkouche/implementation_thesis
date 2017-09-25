import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.function.IntToDoubleFunction;
import java.util.Random;
public class TestChromaticTree{
	public static void main(String []args){
		ConcurrentChromaticTreeMap<Integer, String> map = new ConcurrentChromaticTreeMap<Integer, String>();
		//LogicalOrderingAVL<Integer, String> map = new LogicalOrderingAVL(Integer.MIN_VALUE, Integer.MAX_VALUE);
		//LockBasedFriendlyTreeMap<Integer, String> map = new LockBasedFriendlyTreeMap<Integer, String>();
		
		
		int totalOperation = (int)Math.pow(2, 20);
		int numOfThread = (int)Math.pow(2, 4);
		//testMap(4, 10000, 1990, map);
		Mark7("TestChromaticTreeMap 1 thread",
				i -> testMap(1, totalOperation/1, 500, map));
		Mark7("TestChromaticTreeMap 2 threads",
				i -> testMap(2, totalOperation/2, 500, map));
		Mark7("TestChromaticTreeMap 4 thread",
				i -> testMap(4, totalOperation/4, 500, map));
		Mark7("TestChromaticTreeMap 8 thread",
				i -> testMap(8, totalOperation/8, 500, map));
		//testMap(10, 100000, 500, map);//(threadCount, perThread, range, map)
//		List<Integer> lst = new LinkedList<Integer>();		
//		//map.transformToList(lst);
//		int summ = 0;
//		for(Integer i : lst){
//			summ += i;
//		}
//		System.out.println("sum = " + summ);	
//				System.out.println(map.put(2, "abc"));		
//				System.out.println(map.put(7, "def"));		
//				System.out.println(map.put(8, "xyz"));		
		return;
	}
	private static double testMap(int threadCount, int perThread, int range,
			final ConcurrentChromaticTreeMap<Integer, String> map){
		//	final LogicalOrderingAVL<Integer, String> map){
		//  final LockBasedFriendlyTreeMap<Integer, String> map ){
		Thread[] threads = new Thread[threadCount];
		AtomicIntegerArray threadCounter = new AtomicIntegerArray(threadCount);	
		AtomicInteger sumOfPut = new AtomicInteger(0);
		AtomicInteger sumOfGet = new AtomicInteger(0);
		AtomicInteger sumOfRemove = new AtomicInteger(0);
		for(int t = 0; t < threadCount; t++){
			final int myThread = t;			
			threads[t] = new Thread(() ->{
				Random rand = new Random();
				for(int i = 0; i < perThread; i++){//PUT
					Integer key = rand.nextInt(range);
					//String result = map.put(key, myThread + "_" + i);
					String result = map.putIfAbsent(key, myThread + "_" + i);
					sumOfPut.incrementAndGet();
					if(result == null){
						//System.out.println("PUT " + key);
						threadCounter.addAndGet(myThread, key);	
					}
				}

				for(int k = 0; k < perThread; k++){//GET
					Integer key = rand.nextInt(range);
					String result = map.get(key); 
					if(result !=null){
						sumOfGet.incrementAndGet();
					}
				}

				for(int x = 0; x < perThread; x++){//REMOVE
					Integer key = rand.nextInt(range);
					String result = map.remove(key); 
					sumOfRemove.incrementAndGet();
					if(result != null){										
						threadCounter.addAndGet(myThread, -key);
						//System.out.println("REMOVE " + key);
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
		int sumOfArray = 0;
		for(int i = 0; i < threadCount; i++){
			sumOfArray += threadCounter.get(i);
		}
		//System.out.println("sumOfArray = " + sumOfArray);
		//System.out.println("Sum of PUT = " + sumOfPut.get());
		//System.out.println("Sum of GET = " + sumOfGet.get());
		//System.out.println("Sum of REMOVE = " + sumOfRemove.get());
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
		System.out.printf("%-25s %15.1f us %10.2f %10d%n", msg, mean, sdev, count);
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


