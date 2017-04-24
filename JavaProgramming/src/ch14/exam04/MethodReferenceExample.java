package ch14.exam04;

public class MethodReferenceExample {

	public static void main(String[] args) {
		
		method1(Math::max);
		
		method1(new FunctionalInterface1(){

			@Override
			public int method(int a, int b) {
				
				return Math.max(a, b);
			}
			
		});
		
		method1(Math::min);
		method1(new FunctionalInterface1(){

			@Override
			public int method(int a, int b) {
				return Math.min(a, b);
			}
			
		});
		
		method1(Calculator::Sum);
		method1((a,b)->Calculator.Sum(a,b));
		method1(new FunctionalInterface1(){

			@Override
			public int method(int a, int b) {
				return a+b;
			}
			
		});
		
		
		
		method1(Calculator::Multi);
		method1((a,b)->{return Calculator.Multi(a, b);});
		method1((a,b)-> Calculator.Multi(a, b));
		method1(new FunctionalInterface1(){

			@Override
			public int method(int a, int b) {
				return a*b;
			}
			
		});
		Calculator calc = new Calculator();
		method1((a,b)->{return calc.minus(a,b);});
		method1((a,b)->calc.minus(a,b));
		method1(calc::minus);
		
	}
	
	public static void method1(FunctionalInterface1 i){
		int result = i.method(3, 5);
		System.out.println(result);
	}
}
