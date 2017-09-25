import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ThreadLocalRandom;
class FixedOpTest {
	public static void main(String[] args){
		int[] nbrThreads={1,2,4,8,16,32,64};
		int nbrTries=6;
		//int nbrOp=new Double(Math.pow(2,25)).intValue();
		int cnt=0;

		  for(int i=0;i<nbrThreads.length;i++){
			ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
			TestThread[] threads=new TestThread[nbrThreads[i]];
			long[] times=new long[nbrTries];
			AtomicBoolean keepRunning=new AtomicBoolean(true);
			ArrayList<AtomicLong> results=new ArrayList<AtomicLong>();
			for (int k=0; k<nbrTries ; k++) {
				for (int j=0;j<nbrThreads[i] ;j++ ) {
					AtomicLong operations=new AtomicLong(0);
					results.add(operations);
					threads[j]=new TestThread(tree,keepRunning,operations);
				}


				ExecutorService es=Executors.newSingleThreadExecutor();
		    Future<Void> ft=es.submit(new Task());

			for (int j=0;j<nbrThreads[i] ;j++ ) {
				threads[j].start();
			}
			try{
		      ft.get(10, TimeUnit.SECONDS);
		  }catch(Exception e){
		    ft.cancel(true);
		    keepRunning.set(false);
		   // System.out.println("Terminated!");
		  }

		  es.shutdownNow();
			long totalOp=0;
			for (int j=0;j<nbrThreads[i] ;j++ ) {
				totalOp+=results.get(j).get();
			}
			times[k]=totalOp;
			System.out.println(times[k]);
		} //tries
		long total=0;
		System.out.println("Threads : "+nbrThreads[i]);
		for (int k=0;k<nbrTries ;k++ ) {
			total+=times[k];
		}
		double average=total/nbrTries;
		System.out.println("\n"+" average : "+average);
		System.out.println("throughput : "+average/10/1000);
	  } //for trheads

	}
}

class Task implements Callable<Void> {
	public Void call(){
		boolean t=true;
		while(t){
			try {
				Thread.sleep(10);

			}catch(Exception e){
				//System.out.println("error");
			}
		}
		return null;
	}
}

class TestThread extends Thread {
	private ConcurrentChromaticTreeMap<Integer,Integer> tree;
	int range=1000;
	int put=10,remove=5,get=100-put-remove;
	int nbrOp;
	AtomicBoolean keepRunning;
	AtomicLong result;


	public TestThread(ConcurrentChromaticTreeMap<Integer,Integer> tree,AtomicBoolean kr,AtomicLong r){
		this.tree=tree;
		keepRunning=kr;
		result=r;
	}

	public void run(){
		Random r=new Random();
		while(keepRunning.get()){
			for(int i=0;i<put;i++)
				tree.put(r.nextInt(range+1),0);
			result.addAndGet(put);
			for(int i=0;i<get;i++)
				tree.get(r.nextInt(range+1));
			result.addAndGet(get);
			for(int i=0;i<remove;i++)
				tree.remove(r.nextInt(range+1));
			result.addAndGet(remove);

			}

	}


}
