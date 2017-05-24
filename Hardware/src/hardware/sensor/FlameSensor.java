package hardware.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import hardware.convertor.PCF8591;


public class FlameSensor {
	
	private PCF8591 pcf8591;
	private GpioPinDigitalInput gpinput;
	
	public FlameSensor(PCF8591 pcf8591, Pin pinNo){
		this.pcf8591 = pcf8591;
		GpioController gpc = GpioFactory.getInstance();
		gpinput = gpc.provisionDigitalInputPin(pinNo);
		gpinput.setShutdownOptions(true, PinState.LOW);
	}
	
	public double getValue() throws Exception{
		int analogVal = pcf8591.analogRead();
		return analogVal;
	}
	public void setGpioPinListenerDigital(GpioPinListenerDigital listener){
		gpinput.addListener(listener);
	}
	
	public PinState getState(){
		return gpinput.getState();
	}
	
	public static void main(String[] args) throws InterruptedException, Exception {
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN1);
		FlameSensor ts = new FlameSensor(pcf8591, RaspiPin.GPIO_04);
		
		// 방법 2 디지털 D0를 이용
		ts.setGpioPinListenerDigital(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if(event.getState()==PinState.LOW){
					// 불꽃이 감지됨 이벤트 처리
					System.out.println("!!!!!!!!!!!!!!!!!화재발생!!!!!!!!!!!!!!!!!!");
				}else{	
					System.out.println("~~~~~~암것아님~~~~~~~");
				}
			}
		});
		
		// 방법 1 아날로그 A0를 이용하여 확인
		while(true){
			double value = ts.getValue();
			System.out.print("값: "+value);
			if(value<100){}			
			Thread.sleep(1000);
		}
				
	}
	
}
