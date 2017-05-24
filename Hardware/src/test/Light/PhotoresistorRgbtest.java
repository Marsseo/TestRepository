package test.Light;

import com.pi4j.io.gpio.RaspiPin;
import hardware.convertor.PCF8591;
import hardware.led.RgbLedDigital;
import hardware.sensor.Photoresistor;


public class PhotoresistorRgbtest {
	
	public static void main(String[] args) throws Exception {
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN1);
		Photoresistor ts = new Photoresistor(pcf8591);
		RgbLedDigital led = new RgbLedDigital(RaspiPin.GPIO_01, RaspiPin.GPIO_02, RaspiPin.GPIO_00);

		while(true){
			double value= ts.getValue();
			Thread.sleep(1000);
			System.out.println("현재밝기 : "+value);
			if(value>=50 && value<100) led.rgb(true, false, false);
			else if(value>=100) led.rgb(false, true, false);
			else led.rgb(false, false, true);
		}
	}
	
}
