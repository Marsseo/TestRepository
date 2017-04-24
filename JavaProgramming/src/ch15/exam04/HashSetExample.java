package ch15.exam04;

import java.util.*;

public class HashSetExample {

	public static void main(String[] args) {
		
		Set<String> set = new HashSet<String>();
		
		set.add("Java");
		set.add("JDBC");
		set.add("Servlet/JSP");
		set.add("Java");
		set.add("iBATIS");
		
		int size = set.size();
		System.out.println("ÃÑ °´Ã¼¼ö: "+size);
		
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()){
			String element = iterator.next();
			System.out.println("\t"+element);
		}
		
		set.remove("JDBC");
		set.remove("iBATIS");
		
		System.out.println("ÃÑ °´Ã¼¼ö: "+set.size());
		while(iterator.hasNext()){
			String element = iterator.next();
			System.out.println("\t"+element);
		}
		
		set.clear();
		if(set.isEmpty()){System.out.println("ºñ¾î ÀÖÀ½");}
		

	}

}
