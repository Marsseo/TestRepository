package ch09.exam02;

public class A {
	//Field
	int aField;
	//Constructor
	A(){
		//local class
		class D{
			void dMethod(){
				
			aField = 10;
			}
		}
	}
	//Method
	void aMethod(){
		//local class
		class E{
			//Field
			//Constructor
			//Method
			void eMethod(){
				
				aField = 10;
				}
		}
	}
	//Nested Class
	//��ø ��� Ŭ����
	class B {
		//Field
		//Constructor
		//Method
		void bMethod(){
			
			aField = 10;
			}
	}
	static class C {
		//Field
		//Constructor
		//Method
		void cMethod(){
				//aField = 10;		
			}
	}
}
