package ch06.examples;

public class Car {
	
	String company = "�����ڵ���";
	String model = "�׷���";
	String color = "Black";
	int maxSpeed = 350;
	int speed;
	
	Car() {
		
	}
	Car(String model){
		this(model, "����", 250);
	}
	Car(String model, String color){
		this(model, color, 250);
	}
	Car(String model, String color, int maxSpeed){
		this.model = model;
		this.color = color;
		this.maxSpeed = maxSpeed;
	}
}