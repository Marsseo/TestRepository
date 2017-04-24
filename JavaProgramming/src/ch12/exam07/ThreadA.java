package ch12.exam07;

public class ThreadA extends Thread{
	
	private boolean stop;
	private boolean work = true;
	
	@Override
	public void run() {
		while(!stop){
			if(work){
				System.out.println("ThreadA �۾� ��...");
			}else{
				ThreadA.yield();
			}
		}
		System.out.println("ThreadA �۾� ����");
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public void setWork(boolean work) {
		this.work = work;
	}
	
}
