package ch14.exam08;

import java.util.List;
import java.util.Arrays;
import java.util.function.*;

public class LambdaExpressionExample {
	
	private static List<Student> list = Arrays.asList(
			new Student("»´±Êµø", 90, 96),
			new Student("±Ë¿⁄πŸ", 95, 93),
			new Student("»´¿⁄πŸ", 85, 89)
			);
	
	public static void main(String[] args){
		printString(s->s.getName());
		printInt(s->s.getEnglishScore());
		printInt(Student::getMathScore);
		double a = avg(s->(s.getEnglishScore()+s.getMathScore())/2);
		System.out.println("√— ∆Ú±’¿∫ "+a+"¿‘¥œ¥Ÿ.");
	}
	
	public static void printString(Function<Student, String> function){
		for(Student student : list){
			System.out.print(function.apply(student)+" ");
		}
		System.out.println();
	}
	public static void printInt(ToIntFunction<Student> function){
		for(Student student : list){
			System.out.print(function.applyAsInt(student)+" ");
		}
		System.out.println();
	}
	public static double avg(ToIntFunction<Student> function){
		int sum=0;
		for(Student student : list){
			sum+= function.applyAsInt(student);
		}
		double result = (double)sum/list.size();
		return result;
	}
}
