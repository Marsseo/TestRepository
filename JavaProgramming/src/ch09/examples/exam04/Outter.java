package ch09.examples.exam04;

public class Outter {
	public void method1(final int arg){
		final int localVariable = 1;
		//arg = 100;
		//localVariable = 100;
		class Inner{
			public void method(){
				int result = arg + localVariable;
			}
		}
	}
	public void method2(final int arg){
		int localVariable = 1;
		//arg = 100;
		//localVariable = 100;
		class Inner{
			public void method(){
				int result = arg + localVariable;
			}
		}
	}
}
