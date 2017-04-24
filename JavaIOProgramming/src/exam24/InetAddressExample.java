package ch18.exam24;

import java.net.InetAddress;

public class InetAddressExample {
       public static void main(String[] args) throws Exception{
                InetAddress ia = InetAddress.getLocalHost();
                String address = ia.getHostAddress();
                System.out.println(address);
                
                InetAddress iaNaver = InetAddress.getByName("www.naver.com");
                String addressNaver = iaNaver.getHostAddress();
                System.out.println(addressNaver);
                
                InetAddress[] iaNavers = InetAddress.getAllByName("KOSA-L3-05");
                for(InetAddress i : iaNavers){
                    System.out.println(i.getHostAddress());
                }
    }
}
