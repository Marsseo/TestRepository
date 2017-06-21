package sensingcar.coap.server.resource;

import com.pi4j.io.gpio.RaspiPin;
import hardware.motor.DCMotor;
import hardware.motor.PCA9685;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BackTireResource extends CoapResource{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BackTireResource.class);
	
	private DCMotor mLeft;
	private DCMotor mRight;
	private PCA9685 pca9685;
	
	private final int maxStep = 4095;
	private final int minStep = 0;
	private int curStep;
	
	private String direction;

	public BackTireResource() throws Exception {
		super("backtire");
		pca9685 = PCA9685.getInstance();
		
		mLeft = new DCMotor(RaspiPin.GPIO_00, RaspiPin.GPIO_01, pca9685, PCA9685.PWM_05);
		mRight = new DCMotor(RaspiPin.GPIO_02, RaspiPin.GPIO_03, pca9685, PCA9685.PWM_04);
		
		forward();
	}
	
	public void forward(){
		mLeft.forward();
		mRight.forward();
		direction = "forward";
	}
	
	public void backward(){
		mLeft.backward();
		mRight.backward();
		direction = "backward";
	}
	
	public void setSpeed(int step){
		if(step<minStep) step = 0;
		if(step>maxStep) step = maxStep;
		curStep = step;
		mLeft.setSpeed(curStep);
		mRight.setSpeed(curStep);
	}
	
	public void stop(){
		mLeft.stop();
		mRight.stop();
	}

	@Override
	public void handleGET(CoapExchange exchange) {
		
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		// json 의 이렇게 온다고 가정
		// { "command":"change", "value":"forward", "speed":"1000" }
		// { "command":"status" }
		try{
			String requestJson = exchange.getRequestText();
			JSONObject requestJsonObject = new JSONObject(requestJson);

			String command = requestJsonObject.getString("command");

			if(command.equals("change")){
				String jdirection = requestJsonObject.getString("direction");
				int jspeed = Integer.parseInt(requestJsonObject.getString("speed"));
				if(jdirection.equals("forward")){
					forward();
				}else if(jdirection.equals("backward")){
					backward();
				}
				setSpeed(jspeed);
			}else if(command.equals("status")){

			}
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "success");
			responseJsonObject.put("direction", direction);
			responseJsonObject.put("speed", String.valueOf(curStep));

			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}catch(Exception e){
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "fail");
			responseJsonObject.put("direction", direction);
			responseJsonObject.put("speed", String.valueOf(curStep));

			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}
	}
	
	
}
