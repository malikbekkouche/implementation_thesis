import java.util.*;
class FixedOpTest {
	public static void main(String[] args){
		int[] nbrThreads={1,2,4,8,16,32,64};
		int nbrTries=10;
		int nbrOp=new Double(Math.pow(2,25)).intValue();
		int cnt=0;

		//for(int i=0;i<nbrThreads.length;i++){
			ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
			TestThread[] threads=new TestThread[nbrThreads[cnt]];

			double[] times=new double[nbrTries];
			for (int k=0; k<nbrTries ; k++) {
				for (int j=0;j<nbrThreads[cnt] ;j++ ) {
					threads[j]=new TestThread(tree,nbrOp/nbrThreads[cnt]);
				}
				Timer t=new Timer();

			for (int j=0;j<nbrThreads[cnt] ;j++ ) {
				threads[j].start();
			}
			try{
				for (int j=0;j<nbrThreads[cnt] ;j++ ) {
					threads[j].join();
				}
			}catch(Exception e){}

			double time=t.check()*1e6;
			times[k]=time;
			System.out.println(times[k]);
		} //tries
		double total=0.0;
		System.out.println("Threads : "+nbrThreads[cnt]);
		for (int k=0;k<nbrTries ;k++ ) {
			total+=times[k];
		}
		double average=total/nbrTries;
		System.out.println("\n"+" average : "+average);
	//} //for trheads

	}
}

class TestThread extends Thread {
	private ConcurrentChromaticTreeMap<Integer,Integer> tree;
	int range=1000;
	int put=10,remove=5,get=100-put-remove;
	int nbrOp;


	public TestThread(ConcurrentChromaticTreeMap<Integer,Integer> tree,int nbrOp){
		this.tree=tree;
		this.nbrOp=nbrOp;
	}

	public void run(){
		Random r=new Random();
		for (int j=0;j<nbrOp/100 ;j++ ) {
			for(int i=0;i<put;i++)
				tree.put(r.nextInt(range+1),0);
			for(int i=0;i<get;i++)
				tree.get(r.nextInt(range+1));
			for(int i=0;i<remove;i++)
				tree.remove(r.nextInt(range+1));
		}

	}

	public int put(){
		return (nbrOp*put/100);
	}
	public int remove(){
		return (nbrOp*remove/100);
	}

	public int get(){
		return (nbrOp*(100-put-remove)/100);
	}
}
