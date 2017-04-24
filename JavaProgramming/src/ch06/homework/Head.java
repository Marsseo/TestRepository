package ch06.homework;

public class Head {
	//Field
	Body body = new Body();
	//Constructor
	//Method
	void turn(String direction){
			System.out.print("고개를 "+direction+"으로 돈 후 ");
			body.turn(direction);
		}
}
