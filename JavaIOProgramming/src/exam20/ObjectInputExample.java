package ch18.exam20;

import java.io.*;

public class ObjectInputExample {
    public static void main(String[] args) throws Exception{
            FileInputStream fis = new FileInputStream("src/ch18/exam20/object.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            Member m = (Member)ois.readObject();
            
            System.out.println(m.getName()+" ("+m.getAge()+")");
            
            ois.close();
            fis.close();
    }
}
