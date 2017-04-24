package ch15.exam08;

import java.util.*;

public class TreeSetExample {

	public static void main(String[] args) {
		TreeSet<Person> set = new TreeSet<>(new CompareByAge());
		
		set.add(new Person("홍길동",45));
		set.add(new Person("강자바",55));
		set.add(new Person("박지원",13));
		
		
	
		for(Person p: set){
			System.out.println(p.getName()+"("+p.getAge()+")");
		}
	}

}
