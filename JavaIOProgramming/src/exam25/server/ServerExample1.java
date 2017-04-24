package ch18.exam25.server;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerExample1 {
    public static void main(String[] args) {
        
        ServerSocket serverSocket = null;// 소켓 변수 선언
        
        try {
            serverSocket = new ServerSocket(); // 소켓 생성
            
            //포트와 바인딩
            serverSocket.bind(new InetSocketAddress(50001));
            
            //연결 기다리기
            Socket socket = serverSocket.accept();
            
            //클라이언트의 정보 얻기
            InetSocketAddress isa = (InetSocketAddress)socket.getRemoteSocketAddress();
            System.out.println(isa.toString());
            System.out.println(isa.getHostName());
            
            //통신하기
            
            
           //연결끊기
           socket.close();
                       
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            //serverSocket이 50001을 사용하고 있느냐?
            if(serverSocket != null && !serverSocket.isClosed()){
                try {  serverSocket.close();// 소켓 닫기 (50001 해제)
                } catch (IOException ex1) {   }
            }
            
        }
}
