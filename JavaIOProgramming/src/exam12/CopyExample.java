/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch18.exam12;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class CopyExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream("src/ch18/exam12/Lighthouse.jpg");
        FileOutputStream fos = new FileOutputStream("src/ch18/exam12/Lighthouse2.jpg");
        
        byte[] data = new byte[1024];
        int readBytes = -1;
        while(true){
            readBytes = fis.read(data);
            if(readBytes==-1) break;
            fos.write(data, 0, readBytes);
          }
        
        fos.flush();
        fos.close();
        fis.close();
        
    }
}
