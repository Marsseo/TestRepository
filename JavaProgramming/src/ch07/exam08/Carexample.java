package ch07.exam08;

public class Carexample {

	public static void main(String[] args) {
		
		Car car = new Car(new SnowTire());
			
		car.run();
		
		car.setTire(new NormalTire());
		car.run();
		
		
		car.setTire(new SuperTire());
		car.run();
	
	}

}
