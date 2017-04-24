package ch09.examples.exam02;

public class A {
	B field1 = new B();
	C field2 = new C();
	
	void method1(){
		B var1 = new B();
		C var2 = new C();
		
	}
	//static B field = new B();
	static C field = new C();
	static void method2(){
		//B var1 = new B();
		C var2 = new C();
	}
	class B{}
	static class C{}
}
