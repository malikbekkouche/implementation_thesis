import java.util.Random;
import java.util.concurrent.atomic.*;
import java.util.*;
class FunctionalTest {
	
	public static void main(String [] args){
		int nbrThreads=10,nbrOp=100000,min=0,max=1000;
		//LogicalOrderingAVL<Integer,Integer> tree=new LogicalOrderingAVL<Integer,Integer>(Integer.MIN_VALUE,Integer.MAX_VALUE);
		//SnapTreeMap<Integer,Integer> tree=new SnapTreeMap();
		StaticDictionary5<Integer,Integer> tree=new StaticDictionary5();
		/*tree.put(5,1);
		tree.put(4,6);
		System.out.println(tree.remove(5));
		System.out.println(tree.put(4,7));
		System.out.println(tree.remove(4));*/
		
		Thread[] threads=new Thread[nbrThreads];
		AtomicIntegerArray count=new AtomicIntegerArray(nbrThreads);
		for(int i=0;i<nbrThreads;i++){
			threads[i]=new Thread(new LocalCounter(count,nbrOp,tree,i));
		}
		
		for(int i=0;i<nbrThreads;i++){
			threads[i].start();
		}
		
		for(int i=0;i<nbrThreads;i++){
			try{
			threads[i].join();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		//for snaptree
		/*Iterator<java.util.Map.Entry<Integer, Integer>> itr=tree.entrySet().iterator();
		int total1=0,total2=0;
		int[] count2=new int[nbrThreads];
		while(itr.hasNext()){
			count2[itr.next().getValue()]++;
		}
		for(int i=0;i<nbrThreads;i++)
			System.out.println(count.get(i)+" == "+count2[i]); */
			
		//for Staticdictionary
		ArrayList<Integer> list=tree.iterator();
		int[] count2=new int[nbrThreads];
		for(int i: list){
			count2[i]++;
		}
		for(int i=0;i<nbrThreads;i++)
			System.out.println(count.get(i)+" == "+count2[i]);
		
	}
	
	
		
		/*threads=new Thread[nbrThreads];
		for(int i=0;i<nbrThreads;i++){
			threads[i]=new Thread(new Runnable(){
				public void run(){
					final int index=i;
					Random r=new Random();
					for(int j=0;j<(nbrOp/10);j++)
						tree.put(r.nextInt(max-min+1),""+index);
					//for(int j=0;j<(nbrOp/10);j++)
					//	tree.remove(r.nextInt(max-min+1));
				}
				
			});
		}
		*/
		
}

class LocalCounter implements Runnable {
    private  AtomicIntegerArray counter;
	private int nbrOp; 
	private StaticDictionary5<Integer,Integer> tree;
	private int id;
    
    public LocalCounter(AtomicIntegerArray arr,int nbrOp, StaticDictionary5<Integer,Integer> tree,int id){
        this.counter = arr;
		this.nbrOp=nbrOp;
		this.tree=tree;
		this.id=id;
    }

    @Override
    public void run() {
        count();
    }

    private void count() {
        Random r=new Random();
		for(int i=0;i<nbrOp/2;i++){
			Integer result=tree.put(r.nextInt(),id);
			if(result==null)
				counter.getAndIncrement(id);
			else{
				counter.getAndIncrement(id);
				counter.getAndDecrement((result));
			}
			
		}
		for(int i=0;i<nbrOp/2;i++){
			Integer result=tree.remove(r.nextInt());
			if(result!=null)
				counter.getAndDecrement(result);
		}
		
    }

}