import java.util.Random;
class Test{
	int nbrThreads=10,nbrOp=1000000,min=0,max=1000;
	Thread[] threads;
	public static void main(String [] args){
		final ConcurrentChromaticTreeMap<Integer,String> tree=new ConcurrentChromaticTreeMap();
		tree.put(5,"first");
		
		System.out.println(tree.put(4,"second"));
		System.out.println(tree.remove(5));
		System.out.println(tree.put(4,"third"));
		System.out.println(tree.remove(4));
		
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
	
	
