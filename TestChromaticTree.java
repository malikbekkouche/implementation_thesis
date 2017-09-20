import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.Random;
public class TestChromaticTree{
	public static void main(String []args){
		ConcurrentChromaticTreeMap<Integer, String> map = new ConcurrentChromaticTreeMap<Integer, String>();
		//LogicalOrderingAVL<Integer, String> map = new LogicalOrderingAVL(Integer.MIN_VALUE, Integer.MAX_VALUE); 
		testMap(10, 1000, 10000, map);
		List<Integer> lst = new LinkedList<Integer>();
		System.out.println(map.root.left.left.key);		
		map.transformToList(lst);
		int summ = 0;
		for(Integer i : lst){
			summ += i;
		}
		System.out.println("sum = " + summ);
//		System.out.println(map.put(2, "abc"));		
//		System.out.println(map.put(7, "def"));		
//		System.out.println(map.put(8, "xyz"));		
		return;
	}
	

	private static double testMap(int threadCount, int perThread, int range,
			final ConcurrentChromaticTreeMap<Integer, String> map){
		//	final LogicalOrderingAVL<Integer, String> map){
		Thread[] threads = new Thread[threadCount];
		AtomicIntegerArray threadCounter = new AtomicIntegerArray(threadCount);		
		for(int t = 0; t < threadCount; t++){
			final int myThread = t;
			threads[t] = new Thread(() ->{
				Random rand = new Random();
				for(int i = 0; i < perThread; i++){
					Integer key = rand.nextInt(range);					
					if(map.put(key, myThread + "_" + i) == null){
						threadCounter.addAndGet(myThread, key);
						//System.out.println("ADD" + key);
					}
				}
//
//				for(int j = 0; j < perThread; j++){
//					Integer key = rand.nextInt(range);					
//					if(map.remove(key) != null){										
//						threadCounter.addAndGet(myThread, -key);	
//						//System.out.println("SUB" + key);
//					}
//				}
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
		System.out.println("sumOfArray = " + sumOfArray);
		return 0;
	}

}


