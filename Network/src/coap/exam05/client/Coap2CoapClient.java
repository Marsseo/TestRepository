package coap.exam05.client;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;


public class Coap2CoapClient {
	
	private CoapClient coapClient;

	public Coap2CoapClient() {
		
		coapClient = new CoapClient();
		
	}
	
	public String coap2coap(){
		//Proxy의 접근 Uri
		coapClient.setURI("coap://192.168.3.54/coap2coap");
		//책과 다르게 지원하는 대상이 get 방식으로 운영되므로 get으로 바꿈
		// forward 되는 타겟 리소스의 통신 방법
		Request request = new Request(CoAP.Code.GET);
		// forward 되는 리소스의 uri
		request.getOptions().setProxyUri("coap://localhost/resource01");
		//통신
		CoapResponse response = coapClient.advanced(request);
		if(response.getCode()==CoAP.ResponseCode.CONTENT){
			return response.getResponseText();
		}else{
			return null;
		}
				
	}
	public void shutdown(){
			coapClient.shutdown();
		}
	
	public static void main(String[] args) {
		Coap2CoapClient client = new Coap2CoapClient();
		String next = client.coap2coap();
		System.out.println(next);
		client.shutdown();
	}
	
}
