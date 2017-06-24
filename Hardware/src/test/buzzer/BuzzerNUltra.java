
package test.buzzer;

import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.sensor.UltrasonicSensor;


public class BuzzerNUltra {
	

	public static void main(String[] args) throws InterruptedException {		
	
		ActiveBuzzer ab = new ActiveBuzzer(RaspiPin.GPIO_01);
		UltrasonicSensor uls = new UltrasonicSensor(RaspiPin.GPIO_00, RaspiPin.GPIO_02);
		
		while(true){
			int distance = uls.getDistance(25);
			if(distance < 20)	ab.on();
			else ab.off();
			System.out.println("거리(cm): "+distance);
			Thread.sleep(1000);
		}
	}
}
