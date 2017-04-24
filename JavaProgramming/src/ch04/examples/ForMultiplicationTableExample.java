package ch04.examples;

public class ForMultiplicationTableExample {

	public static void main(String[] args) {
		for(int m = 2;m<=9;m++){
			System.out.println("*** "+m+"�� ***");
			for(int i = 1;i<=9;i++){
				System.out.println(m+ "X" + i + " = "+ (m*i));
			}
		}

	}

}
