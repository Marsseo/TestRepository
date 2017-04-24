package ch18.exam25.client;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientExample1 {
    public static void main(String[] args) {
       
            Socket socket = null;
            try {
                //how1
                //socket = new Socket("192.168.3.17", 50001);
                
                //how2
                socket = new Socket();
                socket.connect(new InetSocketAddress("192.168.3.17",50001));
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
             if(!socket.isClosed()){  // 소켓 연결 확인
                try { socket.close(); } catch (IOException ex) { } // 닫는 예외처리
             }
    }
}

