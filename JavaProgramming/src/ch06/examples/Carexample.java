package ch06.examples;

public class Carexample {

	public static void main(String[] args) {
		Car myCar = new Car();
		System.out.println("����ȸ��: "+myCar.company);
		System.out.println();
		
		Car car1 = new Car("�ڰ���");
		System.out.println("����ȸ��: "+car1.company);
		System.out.println("�𵨸�: "+car1.model);
		System.out.println();
		
		Car car2 = new Car("�ڰ���", "����");
		System.out.println("����ȸ��: "+car2.company);
		System.out.println("�𵨸�: "+car2.model);
		System.out.println("����: "+car2.color);
		System.out.println();
		
		Car car3 = new Car("�ý�", "����", 200);
		System.out.println("����ȸ��: "+car3.company);
		System.out.println("�𵨸�: "+car3.model);
		System.out.println("����: "+car3.color);
		System.out.println("�ְ�ӵ�: "+car3.maxSpeed);
		

	}

}
