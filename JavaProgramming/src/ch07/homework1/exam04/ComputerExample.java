package ch07.homework1.exam04;

public class ComputerExample {
	public static void main(String[] args){
		int r = 10;
		
		Calculator calculator = new Calculator();
		System.out.println("������ : "+calculator.circleArea(r));
		System.out.println();
		
		Computer computer = new Computer();
		System.out.println("������ : "+computer.circleArea(r));		
		
	}
}
