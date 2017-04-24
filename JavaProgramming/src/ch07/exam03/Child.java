package ch07.exam03;

public class Child extends Parent {
	String firstName;
	
	public Child(String lastName, String firstName) {
		super(lastName);
		this.firstName = firstName;
	}
	
	void play() {
		System.out.println("³î¾Æ¿ä");
	}
	
	@Override
	void sound() {
		System.out.println("³¥³¥");
	}
	
}
