package ch06.homework;

public class Leg {
	//Field
	String direction;
	Foot foot = new Foot(direction);
	//Constructor
	Leg(String direction){
		this.direction = direction;
	}
	//Method
	void walk(){
		foot.walk(direction);
	}
}
