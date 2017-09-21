import java.util.*;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicLong;
class CountdownTest{

  public static void main(String[] args) {

    int currentNbrThreads=0;

		//int nbrOp=new Double(Math.pow(2,32)).intValue();

		//for(volatile int i=0;i<nbrThreads.length;i++){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
  	ArrayList<AtomicLong> results=new ArrayList<AtomicLong>();


    Object lock=new Object();
    Timer timer = new Timer(); //new timer
    int[] nbrThreads={1,2,4,8,16,32,64};
    TestThread[] threads=new TestThread[nbrThreads[currentNbrThreads]];
    MyCountdown task=new MyCountdown(lock,timer,tree,results,threads,currentNbrThreads,nbrThreads);
    timer.scheduleAtFixedRate(task, 1000, 1000); // =  timer.scheduleAtFixedRate(task, delay, period);
    try {
      lock.wait();
    }catch (Exception e) {

    }

    for(AtomicLong l : results){
      System.out.println(l.get());
    }



	//} //for trheads

  }
}

class MyCountdown extends TimerTask{
  private Object lock;
  private Timer timer;
  private ConcurrentChromaticTreeMap<Integer,Integer> tree;
  private ArrayList<AtomicLong> results;
  private TestThread[] threads;
  private int cnt;
  private int[] nbrThreads;

  int nbrTries=10;



  public MyCountdown(Object l,Timer timer,ConcurrentChromaticTreeMap<Integer,Integer> tree,ArrayList<AtomicLong> results,TestThread[] threads,
  int cnt,int[] nbrThreads){
    lock=l;
    this.timer=timer;
    this.tree=tree;
    this.results=results;
    this.threads=threads;
    this.cnt=cnt;
    this.nbrThreads=nbrThreads;
  }

  public void run() {



      //for (int k=0; k<nbrTries ; k++) {

    System.out.println(nbrTries);
    nbrTries--;
    if (nbrTries == 0){
      timer.cancel();
      timer.purge();
      for (int j=0;j<nbrThreads[cnt] ;j++ ) {
        threads[j].stopIt();
      }
      synchronized (lock){
        lock.notify();
      }

    }
      AtomicLong counter=new AtomicLong(0);
      results.add(counter);
      for (int j=0;j<nbrThreads[cnt] ;j++ ) {
          threads[j]=new TestThread(tree,counter);
      }
    for (int j=0;j<nbrThreads[cnt] ;j++ ) {
      threads[j].start();
    }



  }

}

class TestThread extends Thread {
  private AtomicLong counter;
	private ConcurrentChromaticTreeMap<Integer,Integer> tree;
	int range=1000;
	int put=5,remove=10,get=100-put-remove;
	int nbrOp;
  private volatile boolean keepRunning=true;

  public void stopIt(){
    keepRunning=false;
  }


	public TestThread(ConcurrentChromaticTreeMap<Integer,Integer> tree,AtomicLong count){
		this.tree=tree;
    this.counter=count;
	}

	public void run(){ // may not count all the operations but will make counter less of a bottleneck
		Random r=new Random();

    while(keepRunning){
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
	}

	// public int put(){
	// 	return (nbrOp*put/100);
	// }
	// public int remove(){
	// 	return (nbrOp*remove/100);
	// }
  //
	// public int get(){
	// 	return (nbrOp*(100-put-remove)/100);
	// }
}
