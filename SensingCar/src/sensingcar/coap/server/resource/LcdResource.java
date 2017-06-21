package sensingcar.coap.server.resource;

import hardware.lcd.LCD1602;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LcdResource extends CoapResource{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LcdResource.class);
	
	private LCD1602 lcd;
	private String curLine0;
	private String curLine1;

	public LcdResource() throws Exception {
		super("lcd");
		
		lcd = new LCD1602(0x27);
		
		setText("RPi-17-2","kkk");

	}
	private void setText(String line0, String line1){
		lcd.clear();
		lcd.write(0, 0, line1);
		lcd.write(1, 0, line1);
		curLine0 = line0;
		curLine1 = line1;
	}
	
	
	

	@Override
	public void handleGET(CoapExchange exchange) {
		
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		// json 의 이렇게 온다고 가정
		// { "command":"change", "line0":"xxx", "line1":"xxx" }
		// { "command":"status" }
		try{
			String requestJson = exchange.getRequestText();
			JSONObject requestJsonObject = new JSONObject(requestJson);

			String command = requestJsonObject.getString("command");

			if(command.equals("change")){
				
				String line0 = requestJsonObject.getString("line0");
				String line1 = requestJsonObject.getString("line1");
				setText(line0, line1);
				
			}else if(command.equals("status")){

			}
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "success");
			responseJsonObject.put("line0", curLine0);
			responseJsonObject.put("line1", curLine1);

			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}catch(Exception e){
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "fail");
			responseJsonObject.put("line0", curLine0);
			responseJsonObject.put("line1", curLine1);

			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}
	}
	
	
}
