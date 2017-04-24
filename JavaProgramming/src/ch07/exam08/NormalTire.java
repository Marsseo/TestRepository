package ch07.exam08;

public class NormalTire extends Tire{
	//Field
	//Constructor
	NormalTire() {
		System.out.println("Normal Tire 객체 생성");
	}
	//Method
	@Override
	void roll() {
		System.out.println("일반 타이어가 굴러갑니다.");
	}	
	
}
