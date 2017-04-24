package ch06.homework;

public class RobotExample {

	public static void main(String[] args) {
		
		Robot myRobot = new Robot(178);
		
		myRobot.hold("Left");
		myRobot.turn("Right");
		System.out.println("20걸음 걸어갑니다.");
		myRobot.walk(20);
		myRobot.hold("Right");
		myRobot.turn("Left");
		
	}
}