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
class ExecutorTest{

  public static void main(String[] args) {

    int currentNbrThreads=2;

		//int nbrOp=new Double(Math.pow(2,32)).intValue();

		//for(volatile int i=0;i<nbrThreads.length;i++){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
  	ArrayList<AtomicLong> results=new ArrayList<AtomicLong>();
    int[] nbrThreads={1,2,4,8,16,32,64};
    TestThread[] threads=new TestThread[nbrThreads[currentNbrThreads]];

    AtomicLong al=new AtomicLong();
    AtomicBoolean keepRunning=new AtomicBoolean(true);
    IdCreator id=new IdCreator();
    ExecutorService es=Executors.newSingleThreadExecutor();
    Future<Void> ft=es.submit(new Task(id));


  for (int i=0;i<nbrThreads[currentNbrThreads] ;i++ ) {
      AtomicLong counter=new AtomicLong(0);
      results.add(counter);
    }

  for (int j=0;j<nbrThreads[currentNbrThreads] ;j++ ) {
        threads[j]=new TestThread(tree,results.get(j),keepRunning,id);
    }
  for (int j=0;j<nbrThreads[currentNbrThreads] ;j++ ) {
    threads[j].start();
  }
  try{
      ft.get(10, TimeUnit.SECONDS);
  }catch(Exception e){
    ft.cancel(true);
    keepRunning.set(false);
    System.out.println("Terminated!");
  }

  es.shutdownNow();

  for(AtomicLong l : results){
    System.out.println(l.get());
  }
  System.out.println(id.getId());



	//} //for trheads

  }
}

class IdCreator {
  private static int range=500;
  private volatile int id=0;
  int x=10000;

  public int getId(){
    return id;
  }

  public void increment(){
    for(int i=0;i<x;i++)
      id++;
    try{
      Thread.sleep(0);
    }catch(Exception e){

    }
  }

  public int[] getRange(){
    AtomicInteger newId=new AtomicInteger(id);
    return new int[]{newId.get()-range,newId.get()+(range/4)};
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

	public TestThread(ConcurrentChromaticTreeMap<Integer,Integer> tree,AtomicLong count,AtomicBoolean run, IdCreator id){
		this.tree=tree;
    this.counter=count;
    keepRunning=run;
    this.id=id;
	}

	public void run(){ // may not count all the operations but will make counter less of a bottleneck
		Random r=new Random();

    while(keepRunning.get()){
		for(int i=0;i<put;i++)
			tree.put(id.getId(),r.nextInt(range+1));
    counter.addAndGet(put);
    int[] range=id.getRange();
		for(int i=0;i<get;i++)
			tree.get(ThreadLocalRandom.current().nextInt(range[0], range[1] + 1));
    counter.addAndGet(get);
    range=id.getRange();
		for(int i=0;i<remove;i++)
			tree.remove(ThreadLocalRandom.current().nextInt(range[0], range[1] + 1));
    counter.addAndGet(remove);
  }
  System.out.println(keepRunning.get());
	}



}
