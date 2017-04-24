package ch06.exam04;

public class CarExample {

	public static void main(String[] args) {
			Car myCar = new Car("2017-03-28", "Black");
		
			myCar.start();
			myCar.run();
			myCar.stop();
			myCar.engine.start();
			myCar.engine.stop();
			myCar.dashboard.display(80);
			//Tire 필드값 읽기
			System.out.println(myCar.tires[0].location);
			myCar.tires[2].roll();
			//Tire 부품 교체
			myCar.tires[1] = new Tire("앞좌");
			myCar.run();
	}

}
