package ch06.exam04;

public class Car {
	//Field
	Body body = new Body();
	Dashboard dashboard = new Dashboard();
	Engine engine = new Engine();
	Seat[] seats = {new Seat(), new Seat(), new Seat(), new Seat()};
	Tire[] tires = {new Tire("오른쪽 앞"), new Tire("왼쪽 앞"), new Tire("오른쪽 뒤"), new Tire("왼쪽 뒤")};
	String makeDay;
	String color;
	int speed;
	
	
	//Constructor
	Car(String makeDay, String color){
		this.makeDay = makeDay;
		this.color = color;
	}
		//Method
	void start(){
		engine.start();
	}
	void run() {
		while(true){

			for(int i=0;i<tires.length;i++){
				tires[i].roll();
				}
			this.setSpeed(60);
			dashboard.display(speed);
			break;
		}
	}
	void setSpeed(int speed){
		this.speed = speed;
	}
	
	void stop(){
		engine.stop();
	}
	
}
