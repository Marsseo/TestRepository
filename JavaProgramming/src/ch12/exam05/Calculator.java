package ch12.exam05;

public class Calculator {
	
	private int memonry;

	public int getMemonry() {
		return memonry;
	}

	public synchronized void setMemonry(int memonry) {
		this.memonry = memonry;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		System.out.println(Thread.currentThread().getName()+": "+this.memonry);
	}
	
	
	
}
