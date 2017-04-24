package ch12.exam07;

public class YieldExample {

	public static void main(String[] args) {
		ThreadA threadA = new ThreadA();
		ThreadB threadB = new ThreadB();
		
		threadA.start();
		threadB.start();
		
		try {ThreadA.sleep(10);} catch (InterruptedException e) {}
		threadA.setWork(false);
		try {ThreadA.sleep(10);} catch (InterruptedException e) {}
		
		threadA.setStop(true);
		threadB.setStop(true);
	}

}
