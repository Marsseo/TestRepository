package ch18.homework1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {
	public static void main(String[] args) throws IOException{
		File file = new File("C:/Temp/file.txt");
		FileWriter fw = new FileWriter(file, true);//���� ���� ���� �����߰��ϱ� ���ؼ� �Ű��� true �߰�
		fw.write("FileWriter�� �ѱ۷ε� "+"\r\n");
		fw.write("���ڿ��� �ٷ� ����� �� �ִ�."+"\r\n");
		fw.flush();
		fw.close();
		System.out.println("������ ����Ǿ����ϴ�.");
		
	}
}
