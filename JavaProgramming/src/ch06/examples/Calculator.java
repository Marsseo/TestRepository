package ch06.examples;

public class Calculator {
	//field
	
	
	//Constructor
	
	
	//Method
	void powerOn() {
		System.out.println("전원을 켭니다.");
	}
	void powerOff() {
		System.out.println("전원을 끕니다.");
	}
	String info() {
		return "삼성전자-2017-03-28";
	}
	
	int plus(int x, int y) {
		return x+y;
	}
	double plus(double x, double y) {
		return x+y;
	}
	double divide(double x, double y){
		return x/y;
	}
	int[] changeArray(int x, int y, int z){
		int[] array = {x, y, z};
		return array;
	}
}
