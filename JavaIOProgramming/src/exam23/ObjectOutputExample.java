package ch18.exam23;

import java.io.*;

public class ObjectOutputExample {
    public static void main(String[] args) throws Exception{
            FileOutputStream fos = new FileOutputStream("src/ch18/exam23/object.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            VVIP vvip = new VVIP(1,"grade A", "홍길동", 30);
            
            oos.writeObject(vvip);
            
            oos.close();
            fos.close();            
    }
}
