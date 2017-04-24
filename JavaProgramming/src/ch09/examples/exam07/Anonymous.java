package ch09.examples.exam07;

public class Anonymous {
	Person field = new Person(){
		void work(){
			System.out.println("����մϴ�.");
		}
		@Override
		void wake() {
			System.out.println("6�� �Ͼ�ϴ�.");
			work();
		}
	};
	
	void method1(){
		Person localVar = new Person(){
			void walk(){
				System.out.println("��å�մϴ�.");
			}
			@Override
			void wake() {
				System.out.println("7�� �Ͼ�ϴ�.");
				walk();
			}
		};
		localVar.wake();
	}
	void method2(Person person){
		person.wake();
	}
}
