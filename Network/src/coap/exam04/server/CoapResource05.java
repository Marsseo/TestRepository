package coap.exam04.server;

import hardware.convertor.PCF8591;
import hardware.sensor.ThermistorSensor;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.Query.value;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;


public class CoapResource05 extends CoapResource {
	
	private double tem;
	
	public CoapResource05() {
		super("resource05");
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN1);
		ThermistorSensor ths = new ThermistorSensor(pcf8591);
		
		// 관찰 기능 활성화
		setObservable(true);
		//관찰 기능을 제공한다라는 것을 클라이언트가 알 수 있도록 설정
		getAttributes().setObservable();
		
		Thread th = new Thread(){
			@Override
			public void run() {
				double temp;
				
				while(true){
					try {
					temp = ths.getValue();
					tem = temp;
					changed();
					try{Thread.sleep(1000);}catch(Exception e){}
					} catch (Exception ex) {	}
				}
			}
				 
		};
		th.start();
	}

	
	@Override
	public void handleGET(CoapExchange exchange) {
		exchange.respond("온도: "+tem);
	}
			
	
	
}
