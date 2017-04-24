package ch09.exam07;

public class Example2 {
	public static void main(String[] args){
		/*
		class CImpl implements A.C{
			@Override
			public void cMethod() {
				System.out.println("CImpl-cMethod()");				
			}
		}
		A.C cimpl = new CImpl();
		cimpl.cMethod();
		*/
		
		A.C c = new A.C(){
			@Override
			public void cMethod() {
				System.out.println("CImpl-cMethod()");					
			}
		};
		c.cMethod();
		
	}
}
