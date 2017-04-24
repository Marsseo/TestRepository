package ch15.check08;

import java.util.*;

public class HashSetExample {

	public static void main(String[] args) {
		Set<Student> set = new HashSet<>();
		
		set.add(new Student(1,"È«±æµ¿"));
		set.add(new Student(2,"½Å¿ë±Ç"));
		set.add(new Student(1,"Á¶¹Î¿ì"));
		
		Iterator<Student> iterator = set.iterator();
		while(iterator.hasNext()){
			Student s = iterator.next();
			System.out.println(s.studentNum+":"+s.name);
		}

	}

}
