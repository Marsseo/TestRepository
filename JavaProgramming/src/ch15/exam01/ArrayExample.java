package ch15.exam01;

import java.util.ArrayList;
import java.util.List;

public class ArrayExample {
	public static void main(String[] args){
		
		Student[] std = new Student[3];
		
		std[0] = new Student("s1");
		std[1] = new Student("s2");
		std[2] = new Student("s3");
		//std[3] = new Student("s4");
		
		List<Student> list = new ArrayList<>();
		for(int i=0;i<100;i++){
			list.add(new Student("s"+i));
			System.out.println("s"+i+": "+list.get(i));
		}
		list.remove(1);

	}
}
