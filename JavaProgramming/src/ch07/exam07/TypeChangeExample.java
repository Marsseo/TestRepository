package ch07.exam07;

public class TypeChangeExample {

	public static void main(String[] args) {
		Tire tire1 = new Tire();
		NormalTire tire2 = new NormalTire();
		SnowTire tire3 = new SnowTire();
		
		//자동 타입 변환
		tire1 = tire2;
		tire1 = tire3;
		//강제 타입 변환
		tire2 = (NormalTire)tire1;
		tire3 = (SnowTire)tire1;
		
		//tire2 = tire3; 강제변환으로도 안됨
		//tire3 = tire2;
		
		
		
	}
	static void run(Tire tire){
		
	}

}
