package ch15.check08;

import java.util.*;

public class HashSetExample {

	public static void main(String[] args) {
		Set<Student> set = new HashSet<>();
		
		set.add(new Student(1,"ȫ�浿"));
		set.add(new Student(2,"�ſ��"));
		set.add(new Student(1,"���ο�"));
		
		Iterator<Student> iterator = set.iterator();
		while(iterator.hasNext()){
			Student s = iterator.next();
			System.out.println(s.studentNum+":"+s.name);
		}

	}

}
