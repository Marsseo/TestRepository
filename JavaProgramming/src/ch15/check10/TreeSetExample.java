package ch15.check10;

import java.util.*;

public class TreeSetExample {

	public static void main(String[] args) {
		
		TreeSet<Student> treeSet = new TreeSet<>();
		
		treeSet.add(new Student("blue", 96));
		treeSet.add(new Student("hong", 86));
		treeSet.add(new Student("white", 92));
		
		Student s = treeSet.last();
		System.out.println("�ְ�����: "+s.score);
		System.out.println("�ְ������� ���� ���̵�: "+s.id);

	}

}
