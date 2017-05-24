package test;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.convertor.PCF8591;
import hardware.led.RgbLedDigital;
import hardware.sensor.FlameSensor;
import hardware.sensor.Photoresistor;


public class FlamesensorBuzzerDualLedTest {
	public static void main(String[] args) throws InterruptedException, Exception {
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN1);
		FlameSensor ts = new FlameSensor(pcf8591, RaspiPin.GPIO_04);
		RgbLedDigital led = new RgbLedDigital(RaspiPin.GPIO_01, RaspiPin.GPIO_02, RaspiPin.GPIO_00);
		ActiveBuzzer ab = new ActiveBuzzer(RaspiPin.GPIO_05);
		
		ts.setGpioPinListenerDigital(e->{
			if(e.getState()==PinState.LOW){
				led.rgb(true, false, false);
				ab.on();
			}else{
				led.rgb(false, true, false);
				ab.off();
			}
		});
		
		while(true){
			double value = ts.getValue();
			System.out.print("값: "+value);
			if(value<100){}			
			Thread.sleep(1000);
		}
	}
}
