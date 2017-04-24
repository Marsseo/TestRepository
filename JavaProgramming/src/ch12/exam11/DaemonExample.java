package ch12.exam11;

public class DaemonExample {

	public static void main(String[] args) {
		
		System.out.println("워드 프로세스를 시작함");
		AutoSaveThread ast = new AutoSaveThread();
		ast.setDaemon(true);
		ast.start();
		
		try{Thread.sleep(3000);}catch(Exception e){}
		
		System.out.println("워드 프로세스를 종료함");
		
	}

}
