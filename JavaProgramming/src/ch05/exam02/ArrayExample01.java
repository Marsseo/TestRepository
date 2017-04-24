package ch05.exam02;

public class ArrayExample01 {

	public static void main(String[] args) {
		int[] v1 = {10, 20, 30};
		
		int[] v2;
		v2 = new int[]{10, 20, 30};
		

		sum(new int[]{10, 20, 30});
		System.out.println(sum(v1));
		System.out.println(sum(v2));
		//System.out.println();
	}
	public static int sum(int[] args){
		int sum = 0;
		for(int i = 0; i<args.length;i++){
			sum += args[i];
		}
		return sum;
	}
}
