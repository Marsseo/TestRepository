package ch09.exam04;

public class A {
	//Field
	int field;
	//Constructor
	//Method
	//Nested Class
	//��ø ��� Ŭ����
	class B {
		//Field
		int field;
		//Constructor
		//Method
		void method(){
			A.this.field = 10;
		}
	}
	
}
