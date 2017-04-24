package ch18.exam18;

import java.io.*;

public class PrimitiveDataExample {
    public static void main(String[] args) throws Exception{
            int value = 10;
            double value2 = 2.5;
            boolean value3 = false;
            String value4 = "자바";
            OutputStream os = new FileOutputStream("src/ch18/exam18/test.dat");
            DataOutputStream dos = new DataOutputStream(os);
            
            dos.writeInt(value);
            dos.writeDouble(value2);
            dos.writeBoolean(value3);
            dos.writeUTF(value4);
            dos.close();
            os.close();
            InputStream is = new FileInputStream("src/ch18/exam18/test.dat");
            DataInputStream dis = new DataInputStream(is);
            
            int a = dis.readInt();
            double b = dis.readDouble();
            boolean c = dis.readBoolean();
            String d = dis.readUTF();
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);
            System.out.println(d);
            
            dis.close();
            is.close();
    }
}
