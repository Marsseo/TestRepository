package ch12.exam08;

public class JoinExample {

	public static void main(String[] args) {
		
		SumThread thread = new SumThread();
		thread.start();
		
		
		try {
			thread.join();
		} catch (InterruptedException e) {	}
		
		long result = thread.getSum();
		System.out.print(result);
		
	}

}
