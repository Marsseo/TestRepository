package test.buzzer;

import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.convertor.PCF8591;
import hardware.sensor.ThermistorSensor;


public class BuzzerThermistor{
	
	public static void main(String[] args) throws Exception {
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN1);
		ThermistorSensor ts = new ThermistorSensor(pcf8591);
		ActiveBuzzer ab = new ActiveBuzzer(RaspiPin.GPIO_00);
		
		while(true){
			double thvalue = ts.getValue();
			System.out.printf("값 : %.2f\n", thvalue);
			Thread.sleep(1500);
			if(thvalue>=31.5) ab.on();
			else ab.off();
		}
	}
}
