package ch11.exam06;

public class TimeExample {

	public static void main(String[] args) {
		
		int sum=0;
		
		//long startTime = System.currentTimeMillis();
		long startTime = System.nanoTime();
		System.out.println(startTime);
		for(int i=0;i<10000;i++){
			sum+=i;
		}
		//long endTime = System.currentTimeMillis();
		long endTime = System.nanoTime();
		System.out.println(endTime);
		
		System.out.println(endTime-startTime);
	}

}
