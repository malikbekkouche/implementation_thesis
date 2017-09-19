import java.util.*;
class ItrTest {
	public static void main(String[] args){
		StaticDictionary5<Integer,Integer> tree=new StaticDictionary5();
		tree.put(5,9);
		tree.put(7,1);
		tree.put(8,9);
		tree.put(3,1);
		
		MyThread t1=new MyThread(tree);
		MyThread t2=new MyThread(tree);
		t1.start();
		t2.start();
		
		try{
			t1.join();
			t2.join();
		}catch(Exception e){}
		
		ArrayList<Integer> list=tree.iterator();
		for(int i : list)
			System.out.println(i+"");
	}
	
	
}
class MyThread extends Thread {
	private StaticDictionary5<Integer,Integer> tree;
	
	public MyThread(StaticDictionary5<Integer,Integer> t){
		tree=t;
	}
	
	public void run(){
		Random r=new Random();
		for(int i=0;i<1000;i++)
			tree.put(r.nextInt(),i);
	}
}