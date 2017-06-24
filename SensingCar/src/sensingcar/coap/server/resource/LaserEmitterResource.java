package sensingcar.coap.server.resource;

import com.pi4j.io.gpio.RaspiPin;
import hardware.laser.LaserEmitter;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LaserEmitterResource extends CoapResource{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LaserEmitterResource.class);
	
	private LaserEmitter laser;
	private String curstatus;

	public LaserEmitterResource() throws Exception {
		super("laser");
		
		laser = new LaserEmitter(RaspiPin.GPIO_25);
		off();
	}
	
	private void on(){
		laser.shot();
		curstatus = "on";
	}
	private void off(){
		laser.stop();
		curstatus = "off";
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
