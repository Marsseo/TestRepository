package ch11.exam10;

public class StirngSplitExample {
	public static void main(String[] args){
		String text = "ȫ�浿&�̼���,�ڿ���,���ڹ�-�ָ�ȣ";
		
		String[] names = text.split("&|,|-");
		
		for (String name : names){
			System.out.println(name);
		}
	}
}
