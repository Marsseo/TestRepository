package ch18.homework1;

import java.io.FileInputStream;

public class FileInputStreamExample {

	public static void main(String[] args) {
		try{
			FileInputStream fis = new FileInputStream("D:/IotCourse/NetbinsWorkspace/JavaProgramming/src/ch18/examples/FileInputStreamExample.java");
			int data;
			while((data = fis.read()) != -1){
				System.out.write(data);
			}
			fis.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
