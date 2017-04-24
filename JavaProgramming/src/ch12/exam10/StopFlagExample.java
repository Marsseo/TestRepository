package ch12.exam10;

public class StopFlagExample {

	public static void main(String[] args) {
		
		
		//how1
		PrintThread1 pt1 = new PrintThread1();
		pt1.start();
		try {pt1.sleep(2000);} catch (InterruptedException e) {}
		pt1.setStop(true);
		
		
	}

}
