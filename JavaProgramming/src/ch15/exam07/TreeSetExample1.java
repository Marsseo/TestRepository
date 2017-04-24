package ch15.exam07;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample1 {

	public static void main(String[] args) {
		TreeSet<Integer> set = new TreeSet<>();
		
		set.add(87);
		set.add(98);
		set.add(75);
		set.add(95);
		set.add(80);
		
		int min = set.first();
		int max = set.last();
		
		System.out.println("���� ���� ����: "+min);
		System.out.println("���� ���� ����: "+max);
		System.out.println("---------------------------");
		
		int s1 = set.lower(new Integer(95));
		System.out.println("95�� �Ʒ� ����:"+s1);
		System.out.println("---------------------------");
		
		int s2 = set.higher(new Integer(95));
		System.out.println("95�� ���� ����:"+s2);
		System.out.println("---------------------------");
		
		int s3 = set.floor(new Integer(95));
		System.out.println("95�� �̰ų� �ٷ� �Ʒ� ����:"+s3);
		System.out.println("---------------------------");
		
		int s4 = set.ceiling(new Integer(85));
		System.out.println("85�� �̰ų� �ٷ� ���� ����:"+s4);
		System.out.println("---------------------------");
		
		while(!set.isEmpty()){
			int s = set.pollFirst();
			System.out.println("(���� ��ü��: "+set.size()+")");
		}
		
		Set<Integer> set2 = set.subSet(80,true, 90, false);
		for(int score:set2){
			System.out.println(score);
		}
		System.out.println("---------------------------");
		
		Set<Integer> set3 = set.descendingSet();
		for(int score:set3){
			System.out.println(score);
		}
		System.out.println("---------------------------");
		
		Set<Integer> set4 = set.descendingSet().descendingSet();
		for(int score:set4){
			System.out.println(score);
		}
	}

}
