package ch18.exam28;

import java.io.IOException;
import java.net.*;

public class ReceiveExample {
    public static void main(String[] args) throws SocketException, IOException {
            // UDP 통신의 경우 수신부는 포트번호가 반드시 지정되어 있어야 함
            DatagramSocket dgs = new DatagramSocket(50002);
            
            System.out.println("수신");
                        
            DatagramPacket dgp = new DatagramPacket(new byte[100], 100);
            
            dgs.receive(dgp);
            
            byte[] data = dgp.getData();
            int readBytes = dgp.getLength();
            String strData = new String(data, 0, readBytes);
            System.out.println("받은 데이터: "+strData);
            
            dgs.close();
    }
}
