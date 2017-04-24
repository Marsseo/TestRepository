package ch06.homework;

public class Hand {
	//Field
	String direction;
	//Constructor
	Hand(String direction){
		this.direction = direction;
	}
	//Method
	void hold(String direction){
			System.out.println(direction+"손으로 대상을 잡습니다.");
		
	}
}
