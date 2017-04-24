package ch14.examples;

public class UsingThis {
	public int outterField = 10;
	
	class Inner{
		int innerField = 20;
	
		void method(){
			MyFunctionalInterface fi = ()->{
				System.out.println("outterFeild "+outterField);
				System.out.println("outterFeild "+UsingThis.this.outterField+"\n");
				
				System.out.println("innerField "+innerField);
				System.out.println("innerField "+this.innerField+"\n");
			innerField = 30;
			outterField =20;
			};
			fi.method();
		}
	}
}
