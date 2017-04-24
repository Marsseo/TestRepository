package ch18.exam28;

import java.io.IOException;
import java.net.*;

public class SendExample {
    public static void main(String[] args) throws SocketException, IOException {
            DatagramSocket dgs = new DatagramSocket();
            
            System.out.println("발신");
            
            String strData = "Hello";
            byte[] data = strData.getBytes();
            
            DatagramPacket dgp = new DatagramPacket(
                    data,
                    data.length,
                    new InetSocketAddress("192.168.3.17", 50002)                    
            );
            dgs.send(dgp);
            dgs.close();
    }
}
