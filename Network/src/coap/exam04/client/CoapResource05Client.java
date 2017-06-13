package coap.exam04.client;

import java.io.IOException;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;


public class CoapResource05Client {
	
	private CoapClient coapClient;
	private CoapObserveRelation coapObserveRelation;
	
	public CoapResource05Client() {
		coapClient = new CoapClient();
	}
	
	public void observe(){
		coapClient.setURI("coap://192.168.3.54/resource05");
		coapObserveRelation = coapClient.observe(new CoapHandler() {
			@Override
			public void onLoad(CoapResponse response) {
				String text = response.getResponseText();
				System.out.println(text);
			}

			@Override
			public void onError() {
				
			}
		});
	}
	
	public void shutdown(){
		coapObserveRelation.proactiveCancel();
		coapClient.shutdown();
	}
	
	public static void main(String[] args) throws IOException {
		CoapResource05Client client = new CoapResource05Client();
		client.observe();
		System.in.read();
		client.shutdown();
	}
}
