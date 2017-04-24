package ch14.exam07;

import java.util.function.*;

public class SupplierExample {

	public static void main(String[] args) {
		
		method1(new Consumer<String>(){

			@Override
			public void accept(String t) {
				for(int i=0;i<5;i++){
					System.out.println(t);
				}
				
			}
			
		});
		System.out.println("");
		method1(a->{
			for(int i=0;i<3;i++){
				System.out.println("°ú¸ñ: "+a);
			}
		});
		System.out.println("");
		method2((a,b)->System.out.println(a+"-"+b));
		
		method3((a,b)->{
			for(int j=0;j<b;j++){
				System.out.println(a);
			}
			});
	}
	
	public static void method1(Consumer<String> arg){
		arg.accept("Java");
	}
	public static void method2(BiConsumer<String, String> arg){
		arg.accept("IoT", "Java");
	}
	public static void method3(ObjIntConsumer<String> arg){
		arg.accept("È«±æµ¿", 3);
	}

}
