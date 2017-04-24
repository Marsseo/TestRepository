package ch18.exam13;

import java.io.*;


public class InputStreamReadExample {
    public static void main(String[] args) throws IOException {
        /*
            InputStream is = System.in;
            InputStreamReader isr = new InputStreamReader(is);
            
            int data = isr.read();
            System.out.println((char)data);
        */
        InputStream is = new FileInputStream("src/ch18/exam13/test2.txt");
        InputStreamReader isr = new InputStreamReader(is, "EUC-KR");
        int data = isr.read();
        System.out.println((char)data);
    }
}
