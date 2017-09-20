import java.util.*;
class MalikTest {
	public static void main(String[] args){
		int[] nbrThreads={1,2,4,8,16,32,64};
		int nbrTries=10;
		int nbrOp=Math.pow(2,18);

		for(int i=0;i<nbrThreads.length;i++){
			ConcurrentChromaticTreeMap<Integer,Integer> tree=new ConcurrentChromaticTreeMap();
			TestThread[] threads=new TestThread[nbrThreads[i]];

			double[] times=new double[nbrTries];
			for (int k=0; k<nbrTries ; k++) {
				for (int j=0;j<nbrThreads[i] ;j++ ) {
					threads[j]=new TestThread(tree,nbrOp/nbrThreads[i]);
				}
				Timer t=new Timer();

			for (int j=0;j<nbrThreads[i] ;j++ ) {
				threads[j].start();
			}
			try{
				for (int j=0;j<nbrThreads[i] ;j++ ) {
					threads[j].join();
				}
			}catch(Exception e){}

			double time=t.check();
			times[k]=time;
		} //tries
		double total=0.0;
		System.out.println("Threads : "+nbrThreads[i]);
		for (int k=0;k<nbrTries ;k++ ) {
			System.out.println(times[k]);
			total+=times[k];
		}
		double average=total/nbrTries;
		System.out.println("\n"+" average : "+average);
	} //for trheads

	}
}

class TestThread extends Thread {
	private ConcurrentChromaticTreeMap<Integer,Integer> tree;
	int range=1000;
	int put=5,remove=10;


	public TestThread(ConcurrentChromaticTreeMap<Integer,Integer> tree){
		this.tree=tree;
	}

	public void run(){
		Random r=new Random();
		for(int i=0;i<put();i++)
			tree.put(r.nextInt(range+1),r.nextInt(range+1));
		for(int i=0;i<get();i++)
			tree.get(r.nextInt(range+1));
		for(int i=0;i<remove();i++)
			tree.remove(r.nextInt(range+1));
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
