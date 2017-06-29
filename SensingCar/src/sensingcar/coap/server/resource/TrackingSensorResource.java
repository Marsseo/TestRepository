package sensingcar.coap.server.resource;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import hardware.sensor.TrackingSensor;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TrackingSensorResource extends CoapResource{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TrackingSensorResource.class);
		
	private String curColor;
		
	private TrackingSensor trkss;

	public TrackingSensorResource() throws Exception {
		super("trackingsensor");
		
		trkss = new TrackingSensor(RaspiPin.GPIO_26);
		
		setObservable(true);
		getAttributes().setObservable();
		setObserveType(CoAP.Type.NON);
		
				

		Thread thread = new Thread(){
			@Override
			public void run() {
				while(true){
					try{
						PinState pinStatus = trkss.getStatus();
						if(pinStatus==PinState.HIGH) curColor="black";
						else curColor = "white";
						changed();
						Thread.sleep(1000);
					}catch(Exception e){
						LOGGER.info(e.toString());
					}
				}
			}
			
		}; 
		thread.start();
		
		trkss.setGpioPinListenerDigital(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				PinState pinstate = event.getState();
				if(pinstate.isHigh()) BuzzerResource.getInstance().off();
				else BuzzerResource.getInstance().on();
			}
		});
		
	}
	
	

	@Override
	public void handleGET(CoapExchange exchange) {
		JSONObject responseJsonObject = new JSONObject();
		responseJsonObject.put("tracking", curColor );
		
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
			responseJsonObject.put("tracking", curColor);
			
			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}catch(Exception e){
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "fail");
			responseJsonObject.put("tracking", curColor);

			
			String responseJson = responseJsonObject.toString();

			exchange.respond(responseJson);
		}
	}
	
	
}
