package coap.exam02.client;


import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;


public class CoapResource02Client {
	
	CoapClient coapClient;
	
	public CoapResource02Client(){
		coapClient = new CoapClient();
	}
	
	public String get(String kind, int value){
		
		String queryString = "kind="+kind+"&angle="+value;
		
		coapClient.setURI("coap://192.168.3.54/resource02?"+queryString);
		CoapResponse response = coapClient.get();

		if(response.getCode() == CoAP.ResponseCode.CONTENT){
			return response.getResponseText();
		}else{
			return null;
		}
	}
	
	public String post(int angle){
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("kind", "Ultrasonic");
		jsonObject.put("angle", angle);
		
		String json = jsonObject.toString();
		
		System.out.println(json);
		
		coapClient.setURI("coap://192.168.3.54/resource02");
		CoapResponse response = coapClient.post(json, MediaTypeRegistry.TEXT_PLAIN);

		if(response.getCode() == CoAP.ResponseCode.CONTENT){
			return response.getResponseText();
		}else{
			return null;
		}
		
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
	
	public static void main(String[] args) {
		CoapResource02Client client = new CoapResource02Client();
		
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
		String text = client.post(i);		
		System.out.println(i+"도의 거리: "+text);
		try {	Thread.sleep(50); } catch (InterruptedException ex) {}
	}
	
		client.shutdown();
		
	}
}
