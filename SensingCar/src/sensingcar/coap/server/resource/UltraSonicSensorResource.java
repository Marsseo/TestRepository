package sensingcar.coap.server.resource;

import com.pi4j.io.gpio.RaspiPin;
import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import hardware.sensor.UltrasonicSensor;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UltraSonicSensorResource extends CoapResource{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UltraSonicSensorResource.class);
	
	private PCA9685 pca9685;
	private SG90ServoPCA9685Duration rotation;
	private int curAngle;
	private int curDistance;
	
	private static int minAngle = 15; 
	private static int maxAngle = 160;
	
	private UltrasonicSensor uts;

	public UltraSonicSensorResource() throws Exception {
		super("ultrasonic");
		
		setObservable(true);
		getAttributes().setObservable();
		setObserveType(CoAP.Type.NON);
		
		pca9685 = PCA9685.getInstance();
		
		rotation = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_11);
		
		uts = new UltrasonicSensor(RaspiPin.GPIO_28, RaspiPin.GPIO_29);
		
		setAngle(90);
		Thread thread = new Thread(){
			@Override
			public void run() {
				int count = 1;
				while(true){
					try{
						curDistance = uts.getDistance(ThermistorResource.curTemperature);
						if(count==2){
							changed();
							count=0;
						}
						count++;
						Thread.sleep(500);
					}catch(Exception e){
						LOGGER.info(e.toString());
					}
				}
			}
			
		}; 
		thread.start();
		
	}
	
	private void setAngle(int angle){
		if(angle<minAngle) angle = minAngle;
		if(angle>maxAngle) angle = maxAngle;
		curAngle = angle;
		rotation.setAngle(curAngle);
		
	}

	@Override
	public void handleGET(CoapExchange exchange) {
		JSONObject responseJsonObject = new JSONObject();
		responseJsonObject.put("angle", String.valueOf(curAngle) );
		responseJsonObject.put("distance", String.valueOf(curDistance) );
		
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

			if(command.equals("change")){
				
				int jangle = Integer.parseInt(requestJsonObject.getString("angle"));
				setAngle(jangle);
				try{Thread.sleep(1000);}catch(Exception e){}
				curDistance = uts.getDistance(ThermistorResource.curTemperature);
				
			}else if(command.equals("status")){

			}
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "success");
			responseJsonObject.put("angle", String.valueOf(curAngle));
			responseJsonObject.put("distance", String.valueOf(curDistance) );
			
			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}catch(Exception e){
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "fail");
			responseJsonObject.put("angle", String.valueOf(curAngle));
			responseJsonObject.put("distance", String.valueOf(curDistance) );
			
			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}
	}
	
	
}
