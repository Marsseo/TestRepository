package ch12.check10;

public class ThreadExample {

	public static void main(String[] args) {
		Thread thread = new MovieThread();
		thread.setDaemon(true);
		thread.start();
		
		try {
			thread.sleep(3000);
		} catch (InterruptedException e) {}

	}

}
