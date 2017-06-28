package sensingcar.coap.server.resource;

import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FrontTireResource extends CoapResource{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontTireResource.class);
	
	private PCA9685 pca9685;
	private SG90ServoPCA9685Duration rotation;
	private final int maxAngle = 130;
	private final int minAngle = 50;
	private int curAngle;
	

	public FrontTireResource() throws Exception {
		super("fronttire");
		pca9685 = PCA9685.getInstance();
		
		rotation = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_00);
		setAngle(90);
	}
	
	private void setAngle(int angle){
		if(angle<minAngle) angle = minAngle;
		if(angle>maxAngle) angle = maxAngle;
		curAngle = angle;
		rotation.setAngle(curAngle);
	}

	@Override
	public void handleGET(CoapExchange exchange) {
		
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		// json 의 이렇게 온다고 가정
		// { "command":"change", "anlge":"90" }
		// { "command":"status" }
		try{
			String requestJson = exchange.getRequestText();
			JSONObject requestJsonObject = new JSONObject(requestJson);

			String command = requestJsonObject.getString("command");

			if(command.equals("change")){
				
				int jangle = Integer.parseInt(requestJsonObject.getString("angle"));
				setAngle(jangle);
				
			}else if(command.equals("status")){

			}
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "success");
			responseJsonObject.put("angle", String.valueOf(curAngle));

			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}catch(Exception e){
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "fail");
			responseJsonObject.put("angle", String.valueOf(curAngle));

			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}
	}
	
	
}
