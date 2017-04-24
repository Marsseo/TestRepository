package ch18.homework2;

import java.io.*;

public class BufferedInputStreamExample {
    public static void main(String[] args) throws Exception{
          FileInputStream fis = new FileInputStream("src/ch18/homework2/Hydrangeas.jpg");
            BufferedInputStream bis = new BufferedInputStream(fis);
           
            
            long startTime = System.nanoTime();
            
            while(fis.read()!=-1 ){}
            long endTime = System.nanoTime();
                   
            
           
            System.out.println(endTime-startTime);
           
            startTime = System.nanoTime();
            while(bis.read()!=-1){}
            endTime = System.nanoTime();
           
            System.out.println(endTime-startTime);
            
            
            bis.close();
            fis.close();
    }
}
