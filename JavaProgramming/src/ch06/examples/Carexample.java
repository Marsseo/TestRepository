package ch06.examples;

public class Carexample {

	public static void main(String[] args) {
		Car myCar = new Car();
		System.out.println("제작회사: "+myCar.company);
		System.out.println();
		
		Car car1 = new Car("자가용");
		System.out.println("제작회사: "+car1.company);
		System.out.println("모델명: "+car1.model);
		System.out.println();
		
		Car car2 = new Car("자가용", "빨강");
		System.out.println("제작회사: "+car2.company);
		System.out.println("모델명: "+car2.model);
		System.out.println("색깔: "+car2.color);
		System.out.println();
		
		Car car3 = new Car("택시", "검정", 200);
		System.out.println("제작회사: "+car3.company);
		System.out.println("모델명: "+car3.model);
		System.out.println("색깔: "+car3.color);
		System.out.println("최고속도: "+car3.maxSpeed);
		

	}

}
