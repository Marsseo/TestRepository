package ch06.homework;

public class Head {
	//Field
	Body body = new Body();
	//Constructor
	//Method
	void turn(String direction){
			System.out.print("���� "+direction+"���� �� �� ");
			body.turn(direction);
		}
}
