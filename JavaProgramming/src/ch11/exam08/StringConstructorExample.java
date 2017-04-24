package ch11.exam08;

import java.io.IOException;

public class StringConstructorExample {
	public static void main(String[] args) throws IOException{
		String s1 = "abc";
		String s2 = new String("abc");
		
		char[] charArry = {'a','b','c'};
		String s3 = new String(charArry);
		
		byte[] byteArry = {49, 50, 51};
		String s4 = new String(byteArry);
		System.out.println(s4);
		
		byte[] inputData = new byte[100];
		//int readByteNo = System.in.read(inputData);
		//String strData = new String(inputData, 0,readByteNo-2);
		
		byte[] byteArry2 = {49, 50, 51, 52, 53, 54, 55};
		String strData2 = new String(byteArry2, 3, 3);
		System.out.println(strData2);
		
	}
}
