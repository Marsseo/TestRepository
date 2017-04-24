package ch12.exam10;

public class PrintThread2 extends Thread{
	

	
	@Override
	public void run(){
		try {
			while(true){
				System.out.println("실행중...");
				System.out.println("실행중...");
				//Thread.sleep(1);
				if(this.isInterrupted()){
					break;
				}
			}
			}catch (Exception e) {}
		System.out.println("자원 정리");
		System.out.println("실행 종료");
		
	}

		
	
}
