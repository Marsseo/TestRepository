package ch06.homework;

public class Robot {
	//Field
	Head head = new Head();
	Body body = new Body();
	Arm[] arms = {new Arm("왼쪽"), new Arm("오른쪽")};
	Leg[] legs = {new Leg("왼쪽"), new Leg("오른쪽")};
	Height height = new Height();

	//Constructor
	
	Robot(int hi){
		System.out.println("로봇이 생성됩니다.");
		height.tall(hi);
	}
	//Method
	void hold(String direction){
		
		if(direction=="Left"){
			arms[0].hold();
		}else if(direction=="Right"){
			arms[1].hold();
		}else {
			System.out.println("잘못입력하셨습니다. Left나 Right를 넣어주세요");
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
				System.out.println(legs[a].direction+" : "+((step/2)+1)+"걸음");
				System.out.println(legs[b].direction+" : "+(step/2)+"걸음");
				System.out.println();
			} else{
				System.out.println();
				System.out.println(legs[a].direction+" : "+(step/2)+"걸음");
				System.out.println(legs[b].direction+" : "+(step/2)+"걸음");
				System.out.println();
			}
	}
	void turn(String direction){
		if(direction=="Left"){
			head.turn("왼쪽");
		}else if(direction=="Right"){
			head.turn("오른쪽");
		}else {
			System.out.println("잘못입력하셨습니다. Left나 Right를 넣어주세요");
		}
	}
}
