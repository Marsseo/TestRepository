package ch09.examples.exam07;

public class AnonymousExample {

	public static void main(String[] args) {
		Anonymous anony = new Anonymous();
		
		anony.field.wake();
		
		anony.method1();
		
		anony.method2(new Person(){
			void study(){
				System.out.println("�����մϴ�.");
			}
			@Override
			void wake() {
				System.out.println("8�� �Ͼ�ϴ�.");
				study();
			}
		});

	}

}
