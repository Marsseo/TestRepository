package ch12.exam05;

public class User2 extends Thread{
	
	private Calculator calculator;
	
	public User2(){
		super("User2");
	}
	
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}



	@Override
	public void run() {
		calculator.setMemonry(50);
	}
}
