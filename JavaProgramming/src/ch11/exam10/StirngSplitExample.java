package ch11.exam10;

public class StirngSplitExample {
	public static void main(String[] args){
		String text = "홍길동&이소훙,박연수,김자바-최명호";
		
		String[] names = text.split("&|,|-");
		
		for (String name : names){
			System.out.println(name);
		}
	}
}
