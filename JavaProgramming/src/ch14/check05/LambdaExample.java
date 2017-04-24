package ch14.check05;

import java.util.function.IntBinaryOperator;

public class LambdaExample {
	private static int[] scores = {10, 50, 3};
	
	public static int maxOrMin(IntBinaryOperator operator){
		int result = scores[0];
		for(int i=1 ;i<scores.length;i++){
			result = operator.applyAsInt(result, scores[i]);
		}
		return result;
	}
	
	public static void main(String[] args){
		int max = maxOrMin(Math::max);
		int max1 = maxOrMin((x,y)->Math.max(x,y));
		System.out.println("최대값: "+max1);
		
		int min = maxOrMin(Math::min);
		int min1 = maxOrMin((x,y)->Math.min(x,y));
		System.out.println("최소값: "+min1);
	}
}
