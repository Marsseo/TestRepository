package coap.exam02.server;

import com.pi4j.io.gpio.RaspiPin;
import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import hardware.sensor.UltrasonicSensor;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;


public class CoapResource02 extends CoapResource{
	
	private SG90ServoPCA9685Duration servo;
	private PCA9685 pca9685;
	private UltrasonicSensor ultra;
	int distance;
	
	public CoapResource02() throws Exception{
		super("resource02");
		pca9685 = PCA9685.getInstance();
		servo = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_11);
		ultra = new UltrasonicSensor(RaspiPin.GPIO_28, RaspiPin.GPIO_29);
		
		Thread th = new Thread(){
			@Override
			public void run() {
				while(true){
					distance = ultra.getDistance(25);
					try {	Thread.sleep(100); } catch (InterruptedException ex) {}
				}
			}
			
		};
		th.start();
	}

	@Override
	public void handleGET(CoapExchange exchange) {
		String name = exchange.getRequestOptions().getUriQuery().get(0).split("=")[1];
		String angle = exchange.getRequestOptions().getUriQuery().get(1).split("=")[1];
		
		int realAngle = Integer.valueOf(angle);
		System.out.println(name + angle + realAngle);
		
		if(name.equals("Ultrasonic")){
			servo.setAngle(realAngle);
			try {	Thread.sleep(300); } catch (InterruptedException ex) {}
			exchange.respond(distance+"cm");
		}
		
		exchange.respond("Ok");
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
//		String queryString = exchange.getRequestText();
//		System.out.println(queryString);
//		String name = queryString.split("&")[0].split("=")[1];
//		String angle = queryString.split("&")[1].split("=")[1];
//		
//		int realAngle = Integer.valueOf(angle);

		String json = exchange.getRequestText();
		JSONObject jsonObject = new JSONObject(json);
		
		String name = jsonObject.getString("kind");
		int realAngle = jsonObject.getInt("angle");
		
		if(name.equals("Ultrasonic")){
			servo.setAngle(realAngle);
			try {	Thread.sleep(300); } catch (InterruptedException ex) {}
			exchange.respond(distance+"cm");
		}
				
	}
	
	
	
}
