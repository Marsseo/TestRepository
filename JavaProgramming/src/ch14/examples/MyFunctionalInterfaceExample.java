package ch14.examples;

public class MyFunctionalInterfaceExample {
	public static void main(String[] args){
		MyFunctionalInterface fi;
		
		fi = ()->{
			String str = "method call";
			System.out.println(str);
		};
		fi.method();
		
		fi = () -> { System.out.println("method call");};
		fi.method();
		
		fi = () ->System.out.println("method call");
		fi.method();
		
		MyFunctionalInterface2 fi2;
		fi2 = (x) -> {
			int result = x*5;
			System.out.println(result);
		};
		
		fi2.method(2);
		
		fi2 = (x)->{System.out.println(x*5);};
		fi2.method(2);
		
		fi2 = (x)->System.out.println(x*5);
		fi2.method(2);
		
		MyFunctionalInterface3 fi3;
		
		fi3 = (x,y)->{
			int result = x+y;
			return result;
		};
		
		System.out.println(fi3.method(2, 5));
		fi3 = (x,y)->{return x+y;};
		
		fi3 = (x,y)-> x+y;
		System.out.println(fi3.method(2, 5));
		
	}
}
