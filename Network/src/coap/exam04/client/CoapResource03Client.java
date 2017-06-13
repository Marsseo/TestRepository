package coap.exam04.client;

import java.util.Random;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;


public class CoapResource03Client {
	
	private CoapClient coapClient;
	private CoapObserveRelation coapObserveRelation;
	
	public CoapResource03Client() {
		coapClient = new CoapClient();
	}
	
	public void observe(){
		coapClient.setURI("coap://192.168.3.54/resource03");
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
	
	public static void main(String[] args) {
		CoapResource03Client client = new CoapResource03Client();
		client.observe();
		try{Thread.sleep(1000);}catch(Exception e){}
		client.shutdown();
	}
}
