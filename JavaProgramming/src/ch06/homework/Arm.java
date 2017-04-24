package ch06.homework;

public class Arm {
	//Field
	String direction;
	Hand hand = new Hand(direction);
	//Constructor
	Arm(String direction){
		this.direction = direction;
	}
	//Method
	void hold(){
		hand.hold(direction);
	}

}
