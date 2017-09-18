import java.util.*;
class ItrTest {
	public static void main(String[] args){
		StaticDictionary5<Integer,Integer> tree=new StaticDictionary5();
		tree.put(5,9);
		tree.put(7,1);
		tree.put(8,9);
		tree.put(3,1);
		
		ArrayList<Integer> list=tree.iterator();
		for(int i : list)
			System.out.println(i+"");
	}
}