package ch18.exam16;

import ch18.exam15.*;
import java.io.*;

public class BufferedExample {
    public static void main(String[] args) throws Exception {
            OutputStream os = new FileOutputStream("src/ch18/exam16/test.txt");
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            String data = "가나다";
            
           
            
                bw.write(data);
           
            
           
                
            
            //bw.flush();
            //osw.flush();
            //os.flush();
            
            bw.close();
            osw.close();
            os.close();
            
    }
}
