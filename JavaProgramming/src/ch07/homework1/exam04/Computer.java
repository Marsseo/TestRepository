package ch07.homework1.exam04;

public class Computer extends Calculator{
	
	@Override
	double circleArea(double r) {
		System.out.println("Computer ��ü�� areaCircle() ����");
		return Math.PI*r*r;
	}
	 
}
