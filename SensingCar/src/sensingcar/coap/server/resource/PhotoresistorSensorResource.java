package sensingcar.coap.server.resource;

import hardware.convertor.PCF8591;
import hardware.sensor.Photoresistor;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PhotoresistorSensorResource extends CoapResource{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoresistorSensorResource.class);
	
	private PCF8591 pcf8591;

	private double curValue;
	
	
	
	private Photoresistor photores;

	public PhotoresistorSensorResource() throws Exception {
		super("photoresistorsensor");
		
		setObservable(true);
		getAttributes().setObservable();
		setObserveType(CoAP.Type.NON);
		
		pcf8591 = new PCF8591(0x48, PCF8591.AIN0);
		photores = new Photoresistor(pcf8591);
		

		Thread thread = new Thread(){
			@Override
			public void run() {
				while(true){
					try{
						curValue = photores.getValue();
						changed();
						Thread.sleep(1000);
					}catch(Exception e){
						LOGGER.info(e.toString());
					}
				}
			}
			
		}; 
		thread.start();
		
	}
	
	

	@Override
	public void handleGET(CoapExchange exchange) {
		JSONObject responseJsonObject = new JSONObject();
		responseJsonObject.put("photoresistor", String.valueOf(curValue) );
		
		String responseJson = responseJsonObject.toString();
		exchange.respond(responseJson);
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		// json 의 이렇게 온다고 가정
		// { "command":"change", "angle":"90" }
		// { "command":"status" }
		try{
			String requestJson = exchange.getRequestText();
			JSONObject requestJsonObject = new JSONObject(requestJson);

			String command = requestJsonObject.getString("command");

			if(command.equals("status")){

			}
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "success");
			responseJsonObject.put("photoresistor", String.valueOf(curValue));
			
			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}catch(Exception e){
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "fail");
			responseJsonObject.put("photoresistor", String.valueOf(curValue));

			
			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}
	}
	
	
}
