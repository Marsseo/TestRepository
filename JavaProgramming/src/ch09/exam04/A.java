package ch09.exam04;

public class A {
	//Field
	int field;
	//Constructor
	//Method
	//Nested Class
	//ÁßÃ¸ ¸â¹ö Å¬·¡½º
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
