package sensingcar.coap.server.resource;

import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BuzzerResource extends CoapResource{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BuzzerResource.class);
	
	private static BuzzerResource instance;
	
	private ActiveBuzzer buzzer;
	private String curstatus;

	public BuzzerResource() throws Exception {
		super("buzzer");
		
		instance = this;
		buzzer = new ActiveBuzzer(RaspiPin.GPIO_24);
		off();
	}
	
	public void on(){
		buzzer.on();
		curstatus = "on";
	}
	public void off(){
		buzzer.off();
		curstatus = "off";
	}
	
	public static BuzzerResource getInstance(){
		return instance;
	}	

	@Override
	public void handleGET(CoapExchange exchange) {
		
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		// json 의 이렇게 온다고 가정
		// { "command":"change", "status":"on" }
		// { "command":"status" }
		try{
			String requestJson = exchange.getRequestText();
			JSONObject requestJsonObject = new JSONObject(requestJson);

			String command = requestJsonObject.getString("command");

			if(command.equals("change")){
				
				String status = requestJsonObject.getString("status");
				if(status.equals("on")) on();
				else off();
				
			}else if(command.equals("status")){

			}
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "success");
			responseJsonObject.put("status", String.valueOf(curstatus));

			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}catch(Exception e){
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "fail");
			responseJsonObject.put("status", String.valueOf(curstatus));

			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}
	}
	
	
}
