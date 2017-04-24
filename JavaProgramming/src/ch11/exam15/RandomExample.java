package ch11.exam15;

import java.util.Random;

public class RandomExample {
	public static void main(String[] args){
		int a = (int)(Math.random()*6+1);
		
		int maxNum = 6;
		
		int b = (int)(Math.random()*maxNum)+1;
		System.out.println(a);
		Random c = new Random();
		System.out.println(c.nextInt(maxNum)+1);
	}
}
