package ch18.homework1;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileOutputStreamExample {

	public static void main(String[] args) throws Exception{
		String originalFileName = "\"D:/IotCourse/NetbinsWorkspace/JavaProgramming/src/ch18/examples/tml-image.jpg";
		String targetName = "C:/Temp/newImage.jpg";
		
		FileInputStream fis = new FileInputStream(originalFileName);
		FileOutputStream fos = new FileOutputStream(targetName);
		
		int readByteNo;
		byte[] readBytes = new byte[100];
		
		while((readByteNo=fis.read(readBytes))!=-1){
			fos.write(readBytes, 0, readByteNo);
		}
		
		fos.flush();
		fos.close();
		fis.close();
		
		System.out.println("���簡 �� �Ǿ����ϴ�.");
	}

}
