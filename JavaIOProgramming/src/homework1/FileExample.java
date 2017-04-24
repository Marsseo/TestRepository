package ch18.homework1;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileExample {

	public static void main(String[] args) throws URISyntaxException, IOException {
		File dir = new File("C:/Temp/Dir");
		File file1 = new File("C:/Temp/file1.txt");
		File file2 = new File("C:/Temp/file2.txt");
		File file3 = new File(new URI("File:///C:/Temp/file3.txt"));
		
		if(dir.exists()==false) {dir.mkdirs();}
		if(file1.exists()==false) {file1.createNewFile();}
		if(file2.exists()==false) {file2.createNewFile();}
		if(file3.exists()==false) {file3.createNewFile();}
		
		File temp = new File("C:/Temp");
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd     a     HH:mm");
		File[] contents = temp.listFiles(); // ���� �迭�� temp�� ������ �ִ� ����Ʈ�� �Է�
		System.out.println("��¥                     �ð�                          ����                        ũ��                    �̸�");
		System.out.println("----------------------------------------------------");
		for(File f : contents){
			System.out.print(sdf.format(new Date(f.lastModified())));
			if(f.isDirectory()){
				System.out.print("\t<DIR>\t\t\t"+f.getName());
			}else {
				System.out.print("\t\t\t"+f.length()+"\t"+f.getName());
			}
			System.out.println();
		}
		

	}

}
