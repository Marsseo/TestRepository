package coap.exam01.server;

import java.net.InetSocketAddress;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;


public class CoapResourceServer {
	
	private CoapServer coapServer;
	
	public CoapResourceServer(){
		coapServer = new CoapServer();
		InetSocketAddress bindToAddress = new InetSocketAddress("192.168.3.54", 5683);
		coapServer.addEndpoint(new CoapEndpoint(bindToAddress));
		coapServer.add(new CoapResource01());
		coapServer.start();
	}
	
	
	public void shutdown(){
		coapServer.stop();
		coapServer.destroy();
	}
	
	public static void main(String[] args) {
		CoapResourceServer server = new CoapResourceServer();
		System.out.println("CoAP server is listening on port: 5683");
	}
	
}
