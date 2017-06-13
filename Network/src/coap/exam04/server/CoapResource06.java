package coap.exam04.server;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import hardware.buzzer.ActiveBuzzer;
import hardware.convertor.PCF8591;
import hardware.motor.DCMotor;
import hardware.motor.PCA9685;
import hardware.sensor.GasSensor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;


public class CoapResource06 extends CoapResource {
	
	private double value;
	private boolean state;
	PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN3);
	GasSensor gs = new GasSensor(pcf8591, RaspiPin.GPIO_23);
	PCA9685 pca = PCA9685.getInstance();

	DCMotor m1 = new DCMotor(RaspiPin.GPIO_00, RaspiPin.GPIO_01, pca, PCA9685.PWM_05);
	DCMotor m2 = new DCMotor(RaspiPin.GPIO_02, RaspiPin.GPIO_03, pca, PCA9685.PWM_04);

	ActiveBuzzer test = new ActiveBuzzer(RaspiPin.GPIO_24);
	
	public CoapResource06() throws Exception {
		super("resource06");
		
//		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN3);
//		GasSensor gs = new GasSensor(pcf8591, RaspiPin.GPIO_23);
//		PCA9685 pca = PCA9685.getInstance();
//		
//		DCMotor m1 = new DCMotor(RaspiPin.GPIO_00, RaspiPin.GPIO_01, pca, PCA9685.PWM_05);
//		DCMotor m2 = new DCMotor(RaspiPin.GPIO_02, RaspiPin.GPIO_03, pca, PCA9685.PWM_04);
//		
//		ActiveBuzzer test = new ActiveBuzzer(RaspiPin.GPIO_24);
		
		// 관찰 기능 활성화
		setObservable(true);
		//관찰 기능을 제공한다라는 것을 클라이언트가 알 수 있도록 설정
		getAttributes().setObservable();
		
		Thread th = new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						value = gs.getValue();
//						System.out.println("gas="+value);
						changed();
						//디지털 핀을 이용한 제어
//						gs.setGpioPinListenerDigital(new GpioPinListenerDigital() {
//							@Override
//							public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
//							if(event.getState()==PinState.LOW){
//								// 불꽃이 감지됨 이벤트 처리
//								System.out.println("************ 가스검출***************");
//								changed();
//								test.on();
//								m1.forward();
//								m2.forward();
//								m1.setSpeed(2500);
//								m2.setSpeed(2500);
//								try {Thread.sleep(1000);} catch (InterruptedException ex) {}
//							}else{	
//								System.out.println("*************** 정상*******************");
//								}
//							}
//						});
						
						try{Thread.sleep(1000);}catch(Exception e){}
					} catch (Exception ex) {}
				}
				
			}
			
		};
		th.start();
	}

	
	@Override
	public void handleGET(CoapExchange exchange) {
//		String  a = exchange.getRequestText();
//		System.out.println(a);
		
			if(value>160 && state){

				exchange.respond("************ 가스검출 ***************");
				test.on();
				m1.forward();
				m2.forward();
				m1.setSpeed(2500);
				m2.setSpeed(2500);
				try {Thread.sleep(1000);} catch (InterruptedException ex) {}
				state = !state;
			}else if(!state){

				exchange.respond("*************** 정상 ******************");
				test.off();
				m1.stop();
				m2.stop();
				state = !state;
			}
		}
	
	
}
