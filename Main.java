class Main {
	public static void main(String[] args){
		Gen g=new Gen();
		Gen f=new Gen();
		System.out.println(g.gen);
		System.out.println(f.gen);
	}
}

class Gen {
	private static int counter=0;
	public int gen;
	public Gen(){
		gen=counter;
		counter++;
	}
}