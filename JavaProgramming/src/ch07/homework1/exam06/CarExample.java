package ch07.homework1.exam06;

public class CarExample {
 public static void main(String[] args){
	 Car car = new Car();
	 
	 for(int i = 1; i<=10; i++){
		 int problemLocation = car.run();
	 
		 switch(problemLocation){
		 case 1:
			 System.out.println("");
			 car.frontLeftTire = new HankookTire("�տ���", 15);
			 break;
		 case 2:
			 System.out.println("");
			 car.frontRightTire = new KumhoTire("�տ���", 13);
			 break;
		 case 3:
			 System.out.println("");
			 car.backLeftTire = new HankookTire("�տ���", 14);
			 break;
		 case 4:
			 System.out.println("");
			 car.backRightTire = new KumhoTire("�տ���", 17);
			 break;
		 }
		 System.out.println("------------------------------");
	 }
 }
}
