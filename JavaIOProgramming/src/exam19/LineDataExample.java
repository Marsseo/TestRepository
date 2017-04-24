package ch18.exam19;

import java.io.*;
        
public class LineDataExample {
    public static void main(String[] args) throws IOException {
            String str = "abcde\r\n12345\r\n가나다라마\r\n";

            //write how1
            FileWriter fw = new FileWriter("src/ch18/exam18/test.txt");
            fw.write(str);
            fw.close();
            
            //write how2
            FileOutputStream fos = new FileOutputStream("src/ch18/exam18/test.txt");
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(str);
            osw.close();
            fos.close();
            
            //write how3
            FileOutputStream fos2 = new FileOutputStream("src/ch18/exam18/test.txt");
            PrintStream ps = new PrintStream(fos2);
            //PrintWriter pw = new PrintWriter(fos2);
            ps.println(str);
            ps.close();
            fos2.close();
            
            //read
            FileInputStream fis = new FileInputStream("src/ch18/exam18/test.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            
            while(true){
                String line = br.readLine();
                if(line == null) break;
                System.out.println(line);
            }            
            br.close();
            isr.close();
            fis.close();
    }
}
