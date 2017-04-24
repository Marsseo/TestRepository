package ch18.exam25.server;

import java.io.*;
import java.net.*;

public class ServerExample2 {
    public static void main(String[] args) {
        
        ServerSocket serverSocket = null;// 소켓 변수 선언
        
        try {
            serverSocket = new ServerSocket(); // 소켓 생성
            
            //포트와 바인딩
            serverSocket.bind(new InetSocketAddress(50001));
            
            while(true){
                //연결 기다리기
                Socket socket = serverSocket.accept();

                //통신하기

                //받기 ClientExample2로부터
                InputStream is = socket.getInputStream();
                byte[] data = new byte[100];
                int readBytes = is.read(data);
                
                //데이터가 없이 종료할 경우 예외처리
                if(readBytes == -1) throw new IOException("클라이언트가 정상 종료됨");
                
                String strData = new String(data, 0, readBytes, "UTF-8");
                System.out.println("받은 데이터: "+strData);

                //보내기 ClientExample2로
                OutputStream os = socket.getOutputStream();
                data = strData.getBytes();
                    os.write(data);
                    os.flush();
                System.out.println("데이터 보내기 성공");

                //연결끊기
                socket.close();
            }
                       
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
            //serverSocket이 50001을 사용하고 있느냐?
            if(serverSocket != null && !serverSocket.isClosed()){
                try {  serverSocket.close();// 소켓 닫기 (50001 해제)
                } catch (IOException ex1) {   }
            }
            
        }
}
