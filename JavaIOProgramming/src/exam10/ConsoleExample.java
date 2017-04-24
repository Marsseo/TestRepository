package ch18.exam10;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleExample {
    public static void main(String[] args) throws IOException{
        //int keycode = System.in.read();
        
         InputStream is = System.in;
         //int keycode = is.read();
        
         byte[] keycode = new byte[100];
         int readBytes = is.read(keycode);
         System.out.println(Arrays.toString(keycode));
         String str = new String(keycode, 0, readBytes-2);
         System.out.println(str);
         
         Scanner scanner = new Scanner(System.in);
         
         String str2 = scanner.nextLine();
         System.out.println(str2);
         
         Console console = System.console();
         String str3 = console.readLine();
         System.out.println(str3);
         
         
    }
}
