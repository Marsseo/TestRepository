package ch18.exam23;

import java.io.*;

public class ObjectInputExample {
    public static void main(String[] args) throws Exception{
            FileInputStream fis = new FileInputStream("src/ch18/exam23/object.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            VVIP vvip = (VVIP)ois.readObject();
            
            System.out.println(vvip.getName()+" ("+vvip.getMemberShipNo()+")");
            System.out.println("등급: "+vvip.getGrade()+"\n나이: "+vvip.getAge());
            
            ois.close();
            fis.close();
    }
}
