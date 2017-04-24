package ch18.exam20;

import java.io.*;

public class ObjectOutputExample {
    public static void main(String[] args) throws Exception{
            FileOutputStream fos = new FileOutputStream("src/ch18/exam20/object.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            Member member = new Member("감자바",30);
            oos.writeObject(member);
            
            oos.close();
            fos.close();            
    }
}
