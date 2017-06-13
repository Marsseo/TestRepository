package coap.exam04.server;

import coap.exam02.server.*;
import coap.exam01.server.*;
import java.net.InetSocketAddress;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;


public class CoapResourceServer {
	
	private CoapServer coapServer;
	
	public CoapResourceServer() throws Exception{
		coapServer = new CoapServer();
		InetSocketAddress bindToAddress = new InetSocketAddress("192.168.3.54", 5683);
		coapServer.addEndpoint(new CoapEndpoint(bindToAddress));
		
		coapServer.add(new CoapResource01());
		coapServer.add(new CoapResource02());
		coapServer.add(new CoapResource03());
		coapServer.add(new CoapResource04());
		coapServer.add(new CoapResource05());
		coapServer.add(new CoapResource06());
		
		coapServer.start();
	}
	
	
	public void shutdown(){
		coapServer.stop();
		coapServer.destroy();
	}
	
	public static void main(String[] args) throws Exception {
		CoapResourceServer server = new CoapResourceServer();
		System.out.println("CoAP server is listening on port: 5683");
	}
	
}
