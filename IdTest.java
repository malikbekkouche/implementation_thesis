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
import java.util.concurrent.CyclicBarrier;
class IdTest{

  public static void main(String[] args) {

    int currentNbrThreads=2;

		//int nbrOp=new Double(Math.pow(2,32)).intValue();
    int[] nbrThreads={1,2,4,8,16,32,64};
	int nbrTries=6;
	

		for(int i=0;i<nbrThreads.length;i++){
			long[] tries=new long[nbrTries];
			for(int k=0;k<nbrTries;k++){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
  	ArrayList<AtomicLong> results=new ArrayList<AtomicLong>();
    TestThread[] threads=new TestThread[nbrThreads[i]];
	CyclicBarrier barrier=new CyclicBarrier(nbrThreads[i]+1);

    AtomicLong al=new AtomicLong();
    AtomicBoolean keepRunning=new AtomicBoolean(true);
    IdCreator id=new IdCreator();
    


  for (int j=0;j<nbrThreads[i] ;j++ ) {
	  AtomicLong counter=new AtomicLong(0);
      results.add(counter);
        threads[j]=new TestThread(tree,counter,keepRunning,id,barrier);
    }
  for (int j=0;j<nbrThreads[i] ;j++ ) {
    threads[j].start();
  }
  
  ExecutorService es=Executors.newSingleThreadExecutor();
    Future<Void> ft=es.submit(new Task(id));
	try{
		barrier.wait();
	}catch(Exception e){}
  
  try{
      ft.get(10, TimeUnit.SECONDS);
  }catch(Exception e){
    ft.cancel(true);
    keepRunning.set(false);
  }

  es.shutdownNow();

  long result=0;
  for(AtomicLong l : results){
    result+=l.get();
  }
  
  System.out.println(result/10/1000);
  System.out.println("id : "+id.getId());
	tries[k]=result/10/1000;
			} //tries
			long r=0;
			for(long l : tries)
				r+=l;
		System.out.println("threads : " + nbrThreads[i]);
		System.out.println("average : "+r/nbrTries);

	} //for trheads

  }
}

class IdCreator {
  private static int range=500;
  private volatile int id=0;
  int x=100;

  public int getId(){
    return id;
  }
  
  	public int[] rangePut(){
		AtomicInteger newId=new AtomicInteger(id);
		return new int[]{newId.get()-(newId.get()/10),newId.get()+(newId.get()/10)};
	}
	
	public int[] rangeGet(){
		AtomicInteger newId=new AtomicInteger(id);
		return new int[]{newId.get()-(newId.get()/30),newId.get()+(newId.get()/10)};
	}

  public void increment(){
    //for(int i=0;i<x;i++)
      id++;
    try{
      Thread.sleep(10);
    }catch(Exception e){

    }
  }

  

}

class Task implements Callable<Void>{
  IdCreator id;

  public Task(IdCreator id){
    this.id=id;
  }
  
  

  public Void call(){
    boolean t=true;
    while(t){
      id.increment();
    }
    return null;
  }
}



class TestThread extends Thread {
  private AtomicLong counter;
	private ConcurrentChromaticTreeMap<Integer,Integer> tree;
	int range=1000;
	int put=10,remove=5,get=100-put-remove;
  private AtomicBoolean keepRunning;
  private Future<Void> ft;
  private IdCreator id;
  CyclicBarrier barrier;

	public TestThread(ConcurrentChromaticTreeMap<Integer,Integer> tree,AtomicLong count,AtomicBoolean run, IdCreator id,CyclicBarrier barrier){
		this.tree=tree;
    this.counter=count;
    keepRunning=run;
    this.id=id;
	this.barrier=barrier;
	}
	


	public void run(){ // may not count all the operations but will make counter less of a bottleneck
		Random r=new Random();
		try{
		barrier.wait();
	}catch(Exception e){}

    while(keepRunning.get()){
		int[] range= id.rangePut();
		for(int i=0;i<put;i++)
			tree.put(ThreadLocalRandom.current().nextInt(range[0], range[1] + 1),0);
    counter.addAndGet(put);
		range=id.rangeGet();
		for(int i=0;i<get;i++)
			tree.get(ThreadLocalRandom.current().nextInt(range[0], range[1] + 1));
    counter.addAndGet(get);
    range=id.rangeGet();
		for(int i=0;i<remove;i++)
			tree.remove(ThreadLocalRandom.current().nextInt(range[0], range[1] + 1));
    counter.addAndGet(remove);
	Thread.yield();
  }
  
	}



}
