import java.util.*;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicLong;
class CountdownTest{

  public static void main(String[] args) {


		//int nbrOp=new Double(Math.pow(2,32)).intValue();

		//for(volatile int i=0;i<nbrThreads.length;i++){
		ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
  	ArrayList<AtomicLong> results=new ArrayList<AtomicLong>();


    Object lock=new Object();
    Timer timer = new Timer(); //new timer
    MyCountdown task=new MyCountdown(lock,timer,tree,results);
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
  int[] nbrThreads={1,2,4,8,16,32,64};
  int nbrTries=10;
  int currentNbrThreads=0;

  public MyCountdown(Object l,Timer timer,ConcurrentChromaticTreeMap<Integer,Integer> tree,ArrayList<AtomicLong> results){
    lock=l;
    this.timer=timer;
    this.tree=tree;
    this.results=results;
  }

  public void run() {
    System.out.println(nbrTries);
    nbrTries--;
    if (nbrTries == -1){
      timer.cancel();
      timer.purge();
      synchronized (lock){
        lock.notify();
      }

    }
    AtomicLong counter=new AtomicLong(0);
    results.add(counter);
    TestThread[] threads=new TestThread[nbrThreads[currentNbrThreads]];

      //for (int k=0; k<nbrTries ; k++) {
    for (int j=0;j<nbrThreads[currentNbrThreads] ;j++ ) {
        threads[j]=new TestThread(tree,counter);
    }
    for (int j=0;j<nbrThreads[currentNbrThreads] ;j++ ) {
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


	public TestThread(ConcurrentChromaticTreeMap<Integer,Integer> tree,AtomicLong count){
		this.tree=tree;
    this.counter=count;
	}

	public void run(){ // may not count all the operations but will make counter less of a bottleneck
		Random r=new Random();
    while(true){
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
