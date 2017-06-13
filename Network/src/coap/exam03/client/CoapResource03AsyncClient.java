package coap.exam03.client;


import java.io.IOException;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;


public class CoapResource03AsyncClient {
	
	CoapClient coapClient;
	
	public CoapResource03AsyncClient(){
		coapClient = new CoapClient();
	}
	
	public void get(String kind, int value){
		
		String queryString = "kind="+kind+"&angle="+value;
		
		coapClient.setURI("coap://192.168.3.54/resource02?"+queryString);
		
		coapClient.get(new CoapHandler(){
			@Override
			public void onLoad(CoapResponse response) {
				if(response.getCode() == CoAP.ResponseCode.CONTENT){
					String text = response.getResponseText();
					System.out.println(value+"도의 거리: "+text);
				}
			}
			@Override
			public void onError() {
				
			}
		});
	}
	
	public void post(int angle){
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("kind", "Ultrasonic");
		jsonObject.put("angle", angle);
		
		String json = jsonObject.toString();
		
		System.out.println(json);
		
		coapClient.setURI("coap://192.168.3.54/resource02");
		
		coapClient.post(new CoapHandler() {
			@Override
			public void onLoad(CoapResponse response) {
				if(response.getCode() == CoAP.ResponseCode.CONTENT){
					String text = response.getResponseText();
					System.out.println(angle+"도의 거리: "+text);
			}
		}

			@Override
			public void onError() {
			}
		}, json, MediaTypeRegistry.TEXT_PLAIN);
		
		
		
//		String queryString = "kind=Ultrasonic&angle="+value;
//		
//		coapClient.setURI("coap://192.168.3.54/resource02");
//		CoapResponse response = coapClient.post(queryString, MediaTypeRegistry.TEXT_PLAIN);
//
//		if(response.getCode() == CoAP.ResponseCode.CONTENT){
//			return response.getResponseText();
//		}else{
//			return null;
//		}
	}
	
	
	public void shutdown(){
		coapClient.shutdown();
	}
	
	public static void main(String[] args) throws IOException {
		CoapResource03AsyncClient client = new CoapResource03AsyncClient();
		
//		for(int i=10;i<180;i+=10){
//		String text = client.get("Ultrasonic", i);		
//		System.out.println(i+"도의 거리: "+text);
//		try {	Thread.sleep(50); } catch (InterruptedException ex) {}
//	}
//		for(int i=180;i>10;i-=10){
//		String text = client.get("Ultrasonic", i);		
//		System.out.println(i+"도의 거리: "+text);
//		try {	Thread.sleep(50); } catch (InterruptedException ex) {}
//	}
	
	for(int i=10;i<180;i+=10){
		client.post(i);
		try {	Thread.sleep(500); } catch (InterruptedException ex) {}
	}
		System.in.read();
		client.shutdown();
		
	}
}
