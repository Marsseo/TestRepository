package ch15.exam05;

import java.util.*;

public class HashMapExample2 {

	public static void main(String[] args) {
		
		Map<Student, Integer> map = new HashMap<>();
		
		map.put(new Student(1,"�ſ��"), 85);
		map.put(new Student(1,"�ſ��"), 85);
		
		
		System.out.println("�� Entry ��: "+map.size());
		
		
		
	}

}
