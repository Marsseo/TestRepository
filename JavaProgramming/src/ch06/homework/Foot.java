package ch06.homework;

public class Foot {
	//Field
	String direction;
	//Constructor
	Foot(String direction){
		this.direction = direction;
	}
	//Method
	void walk(String direction){
		System.out.println(direction+"발로 한 걸음 걷습니다.");
	}
}
