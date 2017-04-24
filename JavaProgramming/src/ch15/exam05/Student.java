package ch15.exam05;

public class Student {
	public int sno;
	public String name;
	
	public Student(int sno, String name) {
		super();
		this.sno = sno;
		this.name = name;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Student){
			Student s = (Student)obj;
			return (name.equals(s.name)&&(sno==s.sno));
		}
		return super.equals(obj);
	}
	@Override
	public int hashCode() {
		
		return sno+name.hashCode();
	}
	
}
