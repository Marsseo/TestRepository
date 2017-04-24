package ch18.exam21;


import java.io.*;

public class ObjectOutputExample {
    public static void main(String[] args) throws Exception{
            FileOutputStream fos = new FileOutputStream("src/ch18/exam21/object.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            Member member = new Member("감자바",30);
            member.nation = "미국";
            oos.writeObject(member);
            
            oos.close();
            fos.close();            
    }
}
