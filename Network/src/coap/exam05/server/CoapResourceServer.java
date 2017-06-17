package coap.exam05.server;

import coap.exam04.server.*;
import coap.exam02.server.*;
import coap.exam01.server.*;
import java.net.InetSocketAddress;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.proxy.DirectProxyCoapResolver;
import org.eclipse.californium.proxy.ProxyHttpServer;
import org.eclipse.californium.proxy.resources.ForwardingResource;
import org.eclipse.californium.proxy.resources.ProxyCoapClientResource;


public class CoapResourceServer {
	
	private CoapServer coapServer;
	
	public CoapResourceServer() throws Exception{
		coapServer = new CoapServer();
		InetSocketAddress bindToAddress1 = new InetSocketAddress("192.168.3.9", 5683);
		InetSocketAddress bindToAddress2 = new InetSocketAddress("localhost", 5683);
		coapServer.addEndpoint(new CoapEndpoint(bindToAddress1));
		coapServer.addEndpoint(new CoapEndpoint(bindToAddress2));
		
		coapServer.add(new CoapResource01());
		coapServer.add(new CoapResource02());
		coapServer.add(new CoapResource03());
		coapServer.add(new CoapResource04());
		coapServer.add(new CoapResource05());
		//coapServer.add(new CoapResource06());
		
		// coap -> coap 포워드 프록시 설정
		ForwardingResource coap2coap = new ProxyCoapClientResource("coap2coap");
		coapServer.add(coap2coap);
		
		// http -> coap 포워드 프록시 설정
		ProxyHttpServer httpServer = new ProxyHttpServer(9090);
		httpServer.setProxyCoapResolver(new DirectProxyCoapResolver(coap2coap));
		
		
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
