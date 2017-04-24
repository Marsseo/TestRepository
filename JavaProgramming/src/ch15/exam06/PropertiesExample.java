package ch15.exam06;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PropertiesExample {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Properties pro = new Properties();
		String path = PropertiesExample.class.getResource("database.properties").getPath();
		pro.load(new FileReader(path));
		
		System.out.println(path);
		
		
		String driver = pro.getProperty("driver");
		String url = pro.getProperty("url");
		String username = pro.getProperty("username");
		String manager = pro.getProperty("manager");
		
		System.out.println(driver);
		System.out.println(username);
		System.out.println(url);
		System.out.println(manager);
	}

}
