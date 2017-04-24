package ch06.exam07;

public class Calculator {
	//Field
	static String model = "MI-84";
	static String makeday = "2017.03.29";
	static String info; 
	
	//static block
	static {
		info = model;
		info += "(";
		info += makeday;
		info += ")";
	}
	//Method
	static void method2() {
		System.out.println("method2()");
	}
}
