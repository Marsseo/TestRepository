package sensingcar.coap.server.resource;

import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.led.RgbLedPWM;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RGBLedResource extends CoapResource{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RGBLedResource.class);
	
	private RgbLedPWM rgbLed;
	private int curRed;
	private int curGreen;
	private int curBlue;

	public RGBLedResource() throws Exception {
		super("rgbled");
		
		rgbLed = new RgbLedPWM(RaspiPin.GPIO_04,RaspiPin.GPIO_05,RaspiPin.GPIO_06);
		
		setColor(0, 0, 0);
	}
	
	private void setColor(int red, int green, int blue){
		
		rgbLed.ledColorSet(red, green, blue);
		curRed = red;
		curGreen = green;
		curBlue = blue;
	}
	

	@Override
	public void handleGET(CoapExchange exchange) {
		
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		// json 의 이렇게 온다고 가정
		// { "command":"change", "red":"100", "green":"100", "blue":"100" }
		// { "command":"status" }
		try{
			String requestJson = exchange.getRequestText();
			JSONObject requestJsonObject = new JSONObject(requestJson);

			String command = requestJsonObject.getString("command");

			if(command.equals("change")){
				
				int red = Integer.parseInt(requestJsonObject.getString("red"));
				int green = Integer.parseInt(requestJsonObject.getString("green"));
				int blue = Integer.parseInt(requestJsonObject.getString("blue"));
				
				setColor(red, green, blue);
				
			}else if(command.equals("status")){

			}
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "success");
			responseJsonObject.put("red", String.valueOf(curRed));
			responseJsonObject.put("green", String.valueOf(curGreen));
			responseJsonObject.put("blue", String.valueOf(curBlue));

			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}catch(Exception e){
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "fail");
			responseJsonObject.put("red", String.valueOf(curRed));
			responseJsonObject.put("green", String.valueOf(curGreen));
			responseJsonObject.put("blue", String.valueOf(curBlue));

			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}
	}
	
	
}
