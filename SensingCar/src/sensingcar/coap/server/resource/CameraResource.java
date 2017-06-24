package sensingcar.coap.server.resource;

import com.pi4j.io.gpio.RaspiPin;
import hardware.laser.LaserEmitter;
import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CameraResource extends CoapResource{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CameraResource.class);
	
	private PCA9685 pca9685;
	private SG90ServoPCA9685Duration LRrotation;
	private SG90ServoPCA9685Duration UDrotation;
	
	private final int minLR = 10;
	private final int maxLR = 170;
	private final int minUD = 20;
	private final int maxUD = 170;
	
	private int curLR;
	private int curUD;

	public CameraResource() throws Exception {
		super("camera");
		
		pca9685 = PCA9685.getInstance();
		LRrotation = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_15);
		UDrotation = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_14);
		turnLR(100);
		turnUD(30);
	}
	
	private void turnLR(int angle){
		if(angle<minLR) angle = minLR;
		if(angle>maxLR) angle = maxLR;
		curLR = angle;
		LRrotation.setAngle(curLR);
	}
	
	private void turnUD(int angle){
		if(angle<minUD) angle = minUD;
		if(angle>maxUD) angle = maxUD;
		curUD = angle;
		UDrotation.setAngle(curUD);
	}
	

	@Override
	public void handleGET(CoapExchange exchange) {
		
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		// json 의 이렇게 온다고 가정
		// { "command":"change", "leftright":"90", "updown":"10" }
		// { "command":"status" }
		try{
			String requestJson = exchange.getRequestText();
			JSONObject requestJsonObject = new JSONObject(requestJson);

			String command = requestJsonObject.getString("command");

			if(command.equals("change")){
				
				int leftright = Integer.parseInt(requestJsonObject.getString("leftright"));
				int updown = Integer.parseInt(requestJsonObject.getString("updown"));
				turnLR(leftright);
				turnUD(updown);
				
			}else if(command.equals("status")){

			}
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "success");
			responseJsonObject.put("leftright", String.valueOf(curLR));
			responseJsonObject.put("updown", String.valueOf(curUD));

			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}catch(Exception e){
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "fail");
			responseJsonObject.put("leftright", String.valueOf(curLR));
			responseJsonObject.put("updown", String.valueOf(curUD));

			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}
	}
	
	
}
