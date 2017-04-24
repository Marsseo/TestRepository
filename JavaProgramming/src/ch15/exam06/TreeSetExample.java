package ch15.exam06;

import java.util.*;

public class TreeSetExample {

	public static void main(String[] args) {
		TreeSet<Person> set = new TreeSet<>(Collections.reverseOrder());
		
		set.add(new Person("홍길동",45));
		set.add(new Person("강자바",25));
		set.add(new Person("박지원",31));
		
		
	
		for(Person p: set){
			System.out.println(p.getName()+"("+p.getAge()+")");
		}
	}

}
