package ch06.homework;

public class RobotExample {

	public static void main(String[] args) {
		
		Robot myRobot = new Robot(178);
		
		myRobot.hold("Left");
		myRobot.turn("Right");
		System.out.println("20���� �ɾ�ϴ�.");
		myRobot.walk(20);
		myRobot.hold("Right");
		myRobot.turn("Left");
		
	}
}