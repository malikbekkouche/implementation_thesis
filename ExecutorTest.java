import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
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
    ExecutorService es=Executors.newSingleThreadExecutor();
    Future<Void> ft=es.submit(new Task());


    for (int i=0;i<nbrThreads[currentNbrThreads] ;i++ ) {
      AtomicLong counter=new AtomicLong(0);
      results.add(counter);
    }

    for (int j=0;j<nbrThreads[currentNbrThreads] ;j++ ) {
        threads[j]=new TestThread(tree,results.get(j),keepRunning);
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



	//} //for trheads

  }
}

class Task implements Callable<Void>{


  public Void call(){
    boolean t=true;
    while(t){

    }
    return null;
  }
}



class TestThread extends Thread {
  private AtomicLong counter;
	private ConcurrentChromaticTreeMap<Integer,Integer> tree;
	int range=1000;
	int put=5,remove=10,get=100-put-remove;
  private AtomicBoolean keepRunning;

	public TestThread(ConcurrentChromaticTreeMap<Integer,Integer> tree,AtomicLong count,AtomicBoolean run){
		this.tree=tree;
    this.counter=count;
    keepRunning=run;
	}

	public void run(){ // may not count all the operations but will make counter less of a bottleneck
		Random r=new Random();

    while(keepRunning.get()){
		for(int i=0;i<put;i++)
			tree.put(r.nextInt(range+1),r.nextInt(range+1));
    counter.addAndGet(put);
		for(int i=0;i<get;i++)
			tree.get(r.nextInt(range+1));
    counter.addAndGet(get);
		for(int i=0;i<remove;i++)
			tree.remove(r.nextInt(range+1));
    counter.addAndGet(remove);
  }
  System.out.println(keepRunning.get());
	}


}
