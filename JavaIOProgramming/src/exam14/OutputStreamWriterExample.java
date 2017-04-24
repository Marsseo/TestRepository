package ch18.exam14;

import java.io.*;

public class OutputStreamWriterExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
            OutputStream os = new FileOutputStream("src/ch18/exam14/test.txt");
            
            OutputStreamWriter osw = new OutputStreamWriter(os);
            
            osw.write("가");
            
            
            osw.close();
            os.close();
    }
}
