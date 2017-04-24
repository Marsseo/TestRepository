package ch14.check06;

import java.util.function.ToIntFunction;


public class LambdaExample {
	private static Student[] student = {
			new Student("홍길동",90,96),
			new Student("신용권",95,93),
			new Student("김자바",85,86)
	};
	public static double avg(ToIntFunction<Student> function){
		int sum = 0;
		 
		for(Student student : student){
			sum+= function.applyAsInt(student);
		}
		double result = (double)sum/student.length;		
				
		return result;
	}
	
	
	
	public static void main(String[] args){
		double englishAvg = avg( s ->s.getEnglishScore());
		double englishAvg1 = avg(Student::getEnglishScore);
		System.out.println("영어 평균 점수: "+englishAvg1);
		double mathAvg = avg(s->s.getMathScore());
		double mathAvg1 = avg(Student::getMathScore);
		System.out.println("영어 평균 점수: "+mathAvg1);
	}
	public static class Student{
		private String name;
		private int englishScore;
		private int mathScore;
		
		public Student(String name, int englishScore, int mathScore){
			this.name = name;
			this.englishScore = englishScore;
			this.mathScore = mathScore;
		}

		public String getName() {
			return name;
		}

		public int getEnglishScore() {
			return englishScore;
		}

		public int getMathScore() {
			return mathScore;
		}
		
	}
}
