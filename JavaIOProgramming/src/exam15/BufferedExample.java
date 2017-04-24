package ch18.exam15;

import java.io.*;

public class BufferedExample {
    public static void main(String[] args) throws Exception {
            OutputStream os = new FileOutputStream("src/ch18/exam15/test.txt");
            BufferedOutputStream bos = new BufferedOutputStream(os);
            byte[] data = "가나다".getBytes();
            
            long startTime = System.nanoTime();
            for(int i=0 ; i<1000;i++){
                os.write(data);
            }
            long endTime = System.nanoTime();
           
            System.out.println(endTime-startTime);
           
            startTime = System.nanoTime();
            for(int i=0 ; i<1000;i++){
                bos.write(data);
            }
            endTime = System.nanoTime();
           
            System.out.println(endTime-startTime);
            
            bos.flush();
            bos.close();
            os.close();
            
    }
}
