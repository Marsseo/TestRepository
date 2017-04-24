package ch14.examples;

public class UsingLocalVariable {
	void method(int arg){
		int localVar = 40;
		//arg = 31;
		//localVar = 41;
		
		MyFunctionalInterface fi = ()->{
			System.out.println(""+arg);
			System.out.println(""+localVar+"\n");
		};
		fi.method();
		
	}
}
