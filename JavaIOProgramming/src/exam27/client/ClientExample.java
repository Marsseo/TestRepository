package ch18.exam27.client;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientExample {
    public static void main(String[] args) {
       
            Socket socket = null;
            try {
                //소켓 생성
                socket = new Socket();
                
                //연결요청
                socket.connect(new InetSocketAddress("192.168.3.18",50001));
                String str = null;
                
                //통신하기
                
                //보내기 ServerExample2로
                OutputStream os = socket.getOutputStream();
                String strData = "아이고";
                byte[] data = strData.getBytes("UTF-8");
                os.write(data);
                os.flush();
                System.out.println("데이터 보내기 성공");
              
                //받기 ServerExample2로부터
                InputStream is = socket.getInputStream();
                data = new byte[100];
                int readBytes = is.read(data);
                strData = new String(data, 0, readBytes);
                System.out.println("받은 데이터: "+strData);
                                                 
            } catch (IOException ex) {
                ex.printStackTrace();
            }
             if(!socket.isClosed()){  // 소켓 연결 확인
                try { socket.close(); } catch (IOException ex) { } // 닫는 예외처리
             }
    }
}

