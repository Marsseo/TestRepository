package ch09.exam07;

public class Example {
	public static void main(String[] args) {
		/*
		class B2 extends A.B{
			@Override
			void bMethod() {
				System.out.println("B2-bMethod");
			}
		}
		A.B b = new B2();
		b.bMethod();
		*/
		
		A.B b = new A.B(){
			@Override
			void bMethod() {
				System.out.println("B2-bMethod");
			}
		};
		b.bMethod();
	}	
}
