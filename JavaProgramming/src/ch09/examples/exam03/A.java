package ch09.examples.exam03;

public class A {
	int field1;

	
	void method1(){
		
	}
	//static B field = new B();
	static int field2 = 10;
	static void method2(){
		
	}
	class B{
		void method() {
			field1 = 10;
			method1();
			
			field2 = 10;
			method2();
		}
	}
	static class C{
		void method(){
			//field1 = 10;
			//method1();
			
			field2 = 10;
			method2();
		}
	}
	
}
