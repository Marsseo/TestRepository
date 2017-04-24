package ch18.exam17;

import java.io.*;

public class BufferedExample {
    public static void main(String[] args) throws Exception {
            InputStream is = System.in;
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
                     
            String line = br.readLine();
            
            System.out.println(line);
            br.close();
            isr.close();
            is.close();
         }
}
