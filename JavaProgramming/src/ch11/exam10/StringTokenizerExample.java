package ch11.exam10;

import java.util.StringTokenizer;

public class StringTokenizerExample {

	public static void main(String[] args) {
		String text = "È«±æµ¿/ÀÌ¼öÈ«/¹Ú¿¬¼ö";
		
		StringTokenizer st = new StringTokenizer(text,"/");
		
		int countTokens = st.countTokens();
		for(int i=0;i<countTokens;i++){
			String token = st.nextToken();
			System.out.println(token);
		}
		System.out.println();
		
		st = new StringTokenizer(text,"/");
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			System.out.println(token);
		}
	}

}
