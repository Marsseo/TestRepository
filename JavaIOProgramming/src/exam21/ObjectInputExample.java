package ch18.exam21;


import java.io.*;

public class ObjectInputExample {
    public static void main(String[] args) throws Exception{
            FileInputStream fis = new FileInputStream("src/ch18/exam21/object.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            Member m = (Member)ois.readObject();
            
            System.out.println(m.getName()+" ("+m.getAge()+")");
            System.out.println(m.nation);
            
            ois.close();
            fis.close();
    }
}
