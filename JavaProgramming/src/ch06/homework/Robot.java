package ch06.homework;

public class Robot {
	//Field
	Head head = new Head();
	Body body = new Body();
	Arm[] arms = {new Arm("����"), new Arm("������")};
	Leg[] legs = {new Leg("����"), new Leg("������")};
	Height height = new Height();

	//Constructor
	
	Robot(int hi){
		System.out.println("�κ��� �����˴ϴ�.");
		height.tall(hi);
	}
	//Method
	void hold(String direction){
		
		if(direction=="Left"){
			arms[0].hold();
		}else if(direction=="Right"){
			arms[1].hold();
		}else {
			System.out.println("�߸��Է��ϼ̽��ϴ�. Left�� Right�� �־��ּ���");
		}
	}
	void walk(int step){
		int a = ((int)(Math.random()*10)+1)%2;
		int b = (a==0) ? 1:0;
		System.out.println();
			for(int i=0;i<(step/2);i++){
				legs[a].walk();
				legs[b].walk();
			}
			if(step%2==1){
				legs[a].walk();
				System.out.println();
				System.out.println(legs[a].direction+" : "+((step/2)+1)+"����");
				System.out.println(legs[b].direction+" : "+(step/2)+"����");
				System.out.println();
			} else{
				System.out.println();
				System.out.println(legs[a].direction+" : "+(step/2)+"����");
				System.out.println(legs[b].direction+" : "+(step/2)+"����");
				System.out.println();
			}
	}
	void turn(String direction){
		if(direction=="Left"){
			head.turn("����");
		}else if(direction=="Right"){
			head.turn("������");
		}else {
			System.out.println("�߸��Է��ϼ̽��ϴ�. Left�� Right�� �־��ּ���");
		}
	}
}
