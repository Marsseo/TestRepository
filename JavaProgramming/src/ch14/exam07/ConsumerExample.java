package ch14.exam07;

import java.util.function.*;

public class ConsumerExample {

	public static void main(String[] args) {
		
		method1(()->"ȫ�浿");
		
		method2(()->3);
		
	}	
	public static void method1(Supplier<String> sup){
		String result = sup.get();
		System.out.println(result);
	}
	
	public static void method2(IntSupplier sup){
		int result = sup.getAsInt();
		System.out.println(result);
	}
	
}
