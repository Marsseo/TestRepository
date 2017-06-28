package sensingcar.coap.server.resource;

import com.pi4j.io.gpio.RaspiPin;
import hardware.convertor.PCF8591;
import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import hardware.sensor.ThermistorSensor;
import hardware.sensor.UltrasonicSensor;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ThermistorResource extends CoapResource{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ThermistorResource.class);
	
	private PCF8591 pcf8591;

	public static double curTemperature;
	
	//private static ThermistorResource instance;
	
	private ThermistorSensor tms;

	public ThermistorResource() throws Exception {
		super("thermistorsensor");
		
		setObservable(true);
		getAttributes().setObservable();
		setObserveType(CoAP.Type.NON);
		
		pcf8591 = new PCF8591(0x48, PCF8591.AIN1);
		tms = new ThermistorSensor(pcf8591);
		

		Thread thread = new Thread(){
			@Override
			public void run() {
				while(true){
					try{
						curTemperature = tms.getValue();
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
	
//	public static ThermistorResource getInstance(){
//		return instance;
//	}

	@Override
	public void handleGET(CoapExchange exchange) {
		JSONObject responseJsonObject = new JSONObject();
		responseJsonObject.put("temperature", String.valueOf(curTemperature) );
		
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
			responseJsonObject.put("temperature", String.valueOf(curTemperature));
			
			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}catch(Exception e){
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "fail");
			responseJsonObject.put("temperature", String.valueOf(curTemperature));

			
			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}
	}
	
	
}
