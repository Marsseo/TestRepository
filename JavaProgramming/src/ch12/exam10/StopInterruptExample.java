package ch12.exam10;

public class StopInterruptExample {

	public static void main(String[] args) {
		
		
		//how2
		PrintThread2 pt2 = new PrintThread2();
		pt2.start();
		try {pt2.sleep(2000); } catch (InterruptedException e) {}
		pt2.interrupt();
						
				
				
			
	}

}
