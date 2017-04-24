package ch08.exam01;

public class Vehicle {
	
	private Manual manual;
	
	Vehicle(Manual manual){
		this.manual = manual;
	}
	public void move(){
		manual.turnOn();
		manual.setSpeed(10);
		manual.run();
		manual.turnOff();
	}
}
