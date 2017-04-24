package ch07.homework1.exam07;

public class DriverExample {
	public static void main(String[] args){
		Driver driver = new Driver();
		
		Bus bus = new Bus();
		Taxi taxi = new Taxi();
		Vehicle vehicle = new Vehicle();
		
		driver.drive(bus);
		driver.drive(taxi);
		driver.drive(vehicle);		
	}
}
