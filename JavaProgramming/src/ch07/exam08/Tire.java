package ch07.exam08;

public abstract class Tire {
	
	int deameter;
	Tire(){
		System.out.println("Tire ��ü ����");
	}
	
	public int getDeameter() {
		return deameter;
	}


	public void setDeameter(int deameter) {
		this.deameter = deameter;
	}


	abstract void roll();
}
