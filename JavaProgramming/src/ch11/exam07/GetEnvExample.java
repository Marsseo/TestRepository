package ch11.exam07;

import java.util.Map;
import java.util.Set;

public class GetEnvExample {

	public static void main(String[] args) {
		String userName = System.getenv("USERNAME");
		
		Map props = System.getenv();
		Set keys = props.keySet();
		for(Object objKey : keys){
			String key = (String)objKey;
			String value = System.getenv(key);
			System.out.println("["+key+"]"+value);
		}
	}

}
