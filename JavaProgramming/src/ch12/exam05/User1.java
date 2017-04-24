package ch12.exam05;

public class User1 extends Thread{
	
	private Calculator calculator;
	
	public User1(){
		super("User1");
	}
	
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}



	@Override
	public void run() {
		calculator.setMemonry(100);
	}
}
