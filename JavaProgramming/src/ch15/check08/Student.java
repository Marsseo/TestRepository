package ch15.check08;

public class Student {
	public int studentNum;
	public String name;
	
	public Student(int studentNum, String name) {

		this.studentNum = studentNum;
		this.name = name;
	}
	@Override
	public int hashCode() {
	
		return studentNum;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Student){
			Student s = (Student)obj;
			return (s.studentNum==studentNum);
		}
		return false;
	}
	
}
