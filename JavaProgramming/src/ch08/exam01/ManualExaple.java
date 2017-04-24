package ch08.exam01;

public class ManualExaple {

	public static void main(String[] args) {
		
		Manual bike = new Car();
		bike.turnOn();
		bike.setSpeed(10);
		bike.run();
		bike.turnOff();

	}
	public static void move(Manual menu){
		menu.turnOn();
		menu.setSpeed(10);
		menu.run();
		menu.turnOff();
	}

}
